import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class FicheEmprunt
{
	private User user;
	private int docId;
	private Date dateEmprunt;
	private int nbWeek;
	private int nbDay;
	private String bd;
	private int credits;
	private SimpleDateFormat formater = null;
	private Date now;
	private Date end;
	
	private JFrame frame = new JFrame("Fiche Emprunt");
	private JPanel pTotal = new JPanel();
	private JPanel pTotal2 = new JPanel();
	private JPanel pDateEmprunt = new JPanel();
	private JPanel pDateRendu = new JPanel();
	private JPanel pTitre = new JPanel();
	private JPanel pUser = new JPanel();
	private JLabel lTitle;
	private JLabel lDebut;
	private JLabel lFin;
	private JLabel lUser;
	private SqlHelper sqlHelper;

	public FicheEmprunt () {
		
	}
	public static Date ajouterJouretSemaine(Date date, int nbDay, int nbWeek) {
		  Calendar cal = Calendar.getInstance(); 
		  cal.setTime(date);
		  cal.add(Calendar.DAY_OF_YEAR, nbDay);
		  cal.add(Calendar.WEEK_OF_YEAR, nbWeek);
		 // cal.set(Calendar.HOUR_OF_DAY, 0);
		  //cal.add(Calendar.HOUR, -13);
		  return cal.getTime();
	}

	
	public FicheEmprunt(User user, int docId, int nbWeek, int nbDay, String bd, int Credit) {
		super();
		this.sqlHelper = new SqlHelper();
		this.user = user;
		this.docId = docId;
		this.nbWeek = nbWeek;
		this.nbDay = nbDay;
		this.bd = bd;
		this.credits = Credit;
		System.out.println(Credit);
		this.now = new Date();
		this.formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.end = ajouterJouretSemaine(this.now, this.nbDay, this.nbWeek);
		this.removeFromStock();
		this.insert();
		this.dealMoney();
		this.addUI();
	}
	
	public void addUI(){
		frame.setMinimumSize(new Dimension(640,350));
		frame.pack();
		pTotal.setLayout(new BorderLayout());
		pTotal2.setLayout(new GridLayout(3,1));
		pDateEmprunt.setLayout(new GridBagLayout());
		pUser.setLayout(new GridBagLayout());
		pTitre.setBackground(Color.CYAN);
		
		lTitle = new JLabel("Bienvenu chez nous"); //a toi de mettre ce qui faut dedans
		lDebut = new JLabel("Date de l'emprunt : 10/05/2017");
		lFin = new JLabel("Date de rendu : 10/06/2017");
		lUser = new JLabel("Bertrand de la Noe");
		
		lTitle.setFont(new Font(lTitle.getFont().getFontName(),lTitle.getFont().getStyle(),20));
		lUser.setFont(new Font(lTitle.getFont().getFontName(),lTitle.getFont().getStyle(),15));
		
		pTitre.add(lTitle);
		pTotal.add(pTitre, BorderLayout.NORTH);
		pDateEmprunt.add(lDebut);
		pDateRendu.add(lFin);
		pUser.add(lUser);
		
		pTotal2.add(pUser);
		pTotal2.add(pDateEmprunt);
		pTotal2.add(pDateRendu);
		
		pTotal.add(pTotal2, BorderLayout.CENTER);
		frame.add(pTotal);
		frame.setVisible(true);
	}
	
	public void removeFromStock () {
		int stock;
		sqlHelper.connect();
		String sqlsearch = "SELECT * FROM " + bd + " where ID = '" + docId + "'";
		sqlHelper.searchsql(sqlsearch);
		stock = sqlHelper.getInt("nbexemplaire");
		String sqlupdate = "UPDATE " + bd + " SET NBEXEMPLAIRE =" + (stock-1) + " WHERE ID =" + docId;
		sqlHelper.updatesql(sqlupdate);
		sqlHelper.disconnect();
	}
	
	
	
	public void addToStock (int docId, String bd) {
		
	}

	public void insert () {
		String sqlinsert = "INSERT INTO FICHE (USERID,DOCID,TYPEDOC,DATEEMPRUNT,DATEFIN) " +
                   "VALUES ('" + user.getId() + "',"
                   		 + "'" + docId + "',"
                   		 + "'" + bd + "',"
                   		 + "'" + formater.format(now) + "',"
                   		 + "'" + formater.format(end) + "');"; 
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database Musie successfully");
		      Statement stmt = connexion.createStatement();
		      System.out.println(sqlinsert);
	    	  stmt.executeUpdate(sqlinsert);
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      //System.exit(0);
		    }
	}
	
	public void dealMoney () {
		int creditBefore;		
		String sqlCount = "SELECT * FROM LOGIN where ID  = '" + user.getId() + "'";
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      Statement stmt = connexion.createStatement();
		      ResultSet rs = stmt.executeQuery(sqlCount);
		      creditBefore = rs.getInt("credit");
		      System.out.println(creditBefore);
		      String sqlUpdate = "UPDATE LOGIN SET CREDIT =" + (creditBefore-this.credits) + " WHERE ID =" + user.getId();
		      System.out.println(sqlUpdate);
		      stmt.execute(sqlUpdate);
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      //System.exit(0);
		    }
	}
	
	public void delete () {
		
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
}
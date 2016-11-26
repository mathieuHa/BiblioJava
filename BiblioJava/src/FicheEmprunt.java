import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class FicheEmprunt implements ActionListener{
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
	private String debut;
	private String fin;
	private String prenom;
	private String titre;
	private JButton annuler = new JButton("Annuler");
	private JButton ok = new JButton("Ok");
	private JPanel pButton = new JPanel();

	public FicheEmprunt () {

	}
	public static Date ajouterJouretSemaine(Date date, int nbDay, int nbWeek) {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, nbDay);
		cal.add(Calendar.WEEK_OF_YEAR, nbWeek);
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

	public void stringo()
	{
		sqlHelper.connect();
		String sqlsearch = "SELECT * FROM " + "FICHE" + " where ID = '" + docId + "'";
		sqlHelper.searchsql(sqlsearch);
		debut = sqlHelper.getString("dateemprunt");
		fin = sqlHelper.getString("datefin");
		sqlsearch = "SELECT * FROM " + "LOGIN" + " where ID = '" + user.getId()+ "'";
		sqlHelper.searchsql(sqlsearch);
		prenom = sqlHelper.getString("username");
		sqlsearch = "SELECT * FROM " + bd + " where ID = '" + docId + "'";
		sqlHelper.searchsql(sqlsearch);
		titre = sqlHelper.getString("titre");
		sqlHelper.disconnect();
	}

	public void addUI(){
		frame.setMinimumSize(new Dimension(640,350));
		frame.pack();
		pTotal.setLayout(new BorderLayout());
		pTotal2.setLayout(new GridLayout(3,1));
		pDateEmprunt.setLayout(new GridBagLayout());
		pUser.setLayout(new GridBagLayout());
		pTitre.setBackground(Color.CYAN);

		this.stringo();  

		lTitle = new JLabel(titre); 
		lDebut = new JLabel("Date de l'emprunt : " + debut);
		lFin = new JLabel("Date de rendu : " + fin);
		lUser = new JLabel(prenom);

		lTitle.setFont(new Font(lTitle.getFont().getFontName(),lTitle.getFont().getStyle(),20));
		lUser.setFont(new Font(lTitle.getFont().getFontName(),lTitle.getFont().getStyle(),15));

		pTitre.add(lTitle);
		pTotal.add(pTitre, BorderLayout.NORTH);
		pDateEmprunt.add(lDebut);
		pDateRendu.add(lFin);
		pUser.add(lUser);
		pButton.add(ok);
		pButton.add(annuler);

		ok.addActionListener(this);
		annuler.addActionListener(this);

		pTotal2.add(pUser);
		pTotal2.add(pDateEmprunt);
		pTotal2.add(pDateRendu);

		pTotal.add(pTotal2, BorderLayout.CENTER);
		pTotal.add(pButton, BorderLayout.SOUTH);
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

	public void addToStock () {
		int stock;
		sqlHelper.connect();
		String sqlsearch = "SELECT * FROM " + bd + " where ID = '" + docId + "'";
		sqlHelper.searchsql(sqlsearch);
		stock = sqlHelper.getInt("nbexemplaire");
		String sqlupdate = "UPDATE " + bd + " SET NBEXEMPLAIRE =" + (stock+1) + " WHERE ID =" + docId;
		sqlHelper.updatesql(sqlupdate);
		sqlHelper.disconnect();
	}

	public void insert () {
		String sqlinsert = "INSERT INTO FICHE (USERID,DOCID,TYPEDOC,DATEEMPRUNT,DATEFIN) " +
				"VALUES ('" + user.getId() + "',"
				+ "'" + docId + "',"
				+ "'" + bd + "',"
				+ "'" + formater.format(now) + "',"
				+ "'" + formater.format(end) + "');";

		sqlHelper.connect();
		System.out.println(sqlinsert);
		sqlHelper.updatesql(sqlinsert);
		sqlHelper.disconnect();
	}

	public void dealMoney () {
		int creditBefore;		
		sqlHelper.connect();
		String sqlsearch = "SELECT * FROM LOGIN where ID  = '" + user.getId() + "'";
		sqlHelper.searchsql(sqlsearch);
		creditBefore = sqlHelper.getInt("credit");
		String sqlupdate = "UPDATE LOGIN SET CREDIT =" + (creditBefore-this.credits) + " WHERE ID =" + user.getId();
		sqlHelper.updatesql(sqlupdate);
		sqlHelper.disconnect();
	}

	public void delete () {
		sqlHelper.connect();
		String sqlsearch = "DELETE FROM " + "FICHE" + " where ID = '" + docId + "'";
		sqlHelper.updatesql(sqlsearch);
		sqlHelper.disconnect();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == ok){frame.dispose();}
		if (arg0.getSource() == annuler)
		{
			this.addToStock();
			this.delete();
			frame.dispose();
		}
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

}
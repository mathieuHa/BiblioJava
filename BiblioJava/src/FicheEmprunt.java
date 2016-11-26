import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import javax.swing.JOptionPane;

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

	public FicheEmprunt () {
		
	}
	public static Date ajouterJouretSemaine(Date date, int nbDay, int nbWeek) {
		  Calendar cal = Calendar.getInstance(); 
		  cal.setTime(date);
		  cal.add(Calendar.DAY_OF_MONTH, nbDay);
		  cal.add(Calendar.WEEK_OF_MONTH, nbWeek);
		  return cal.getTime();
	}

	
	public FicheEmprunt(User user, int docId, int nbWeek, int nbDay, String bd, int Credit) {
		super();
		this.user = user;
		this.docId = docId;
		this.nbWeek = nbWeek;
		this.nbDay = nbDay;
		this.bd = bd;
		this.credits = Credit;
		this.now = new Date();
		this.formater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		this.end = ajouterJouretSemaine(this.now, this.nbDay, this.nbWeek);
		this.removeFromStock();
		this.insert();
		this.dealMoney();
	}
	
	public void removeFromStock () {
		int stock;
		String sqlCount = "SELECT * FROM " + bd + " where ID = '" + docId + "'";
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      Statement stmt = connexion.createStatement();
		      ResultSet rs = stmt.executeQuery(sqlCount);
		      stock = rs.getInt("nbexemplaire");
		      String sqlUpdate = "UPDATE " + bd + " SET NBEXEMPLAIRE =" + (stock-1) + " WHERE ID =" + docId; 
		      stmt.execute(sqlUpdate);
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
	}
	
	
	
	public void addToStock (int docId, String bd) {
		
	}

	public void insert () {
		String sqlinsert = "INSERT INTO FICHE (USERID,DOCID,DATEEMPRUNT,DATEFIN) " +
                   "VALUES ('" + user.getId() + "',"
                   		 + "'" + docId + "',"
                   		 + "'" + formater.format(now) + "',"
                   		 + "'" + formater.format(end) + "');"; 
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database Musie successfully");
		      Statement stmt = connexion.createStatement();
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
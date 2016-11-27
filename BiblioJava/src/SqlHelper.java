import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlHelper {
	
	private Connection connexion;
	private Statement stmt;
	private ResultSet rs;
	
	public void connect (){
		try {
			Class.forName("org.sqlite.JDBC");
			connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
			stmt = connexion.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	
	public void disconnect(){
		 try {
			stmt.close();
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void searchsql(String sqlsearch){
		rs = null;
		try {
			rs = stmt.executeQuery(sqlsearch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatesql(String sqlupdate){
		try {
			stmt.execute(sqlupdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getNext () {
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getInt (String value) {
		try {
			return rs.getInt(value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getString (String value) {
		try {
			return rs.getString(value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void createTable () {
	    this.connect();
        String sqllogin = "CREATE TABLE LOGIN " +
              "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
              " USERNAME           TEXT    NOT NULL, " + 
              " CREDIT             INT     NOT NULL, " + 
              " PASSWORD           TEXT    NOT NULL)";
        String sqlvideo = "CREATE TABLE VIDEO " +
              "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
              " TITRE              TEXT    NOT NULL, " +
              " ANNEE              TEXT    NOT NULL, " +
              " IMAGE              TEXT    NOT NULL, " +
              " DESCRIPTION        TEXT    NOT NULL, " +
              " LANGUAGE           TEXT    NOT NULL, " +
              " NBEMPRUNT          INT     NOT NULL, " +
              " NBEXEMPLAIRE       INT     NOT NULL, " +
              " NOTE               TEXT    NOT NULL, " +
              " TARIF		       INT     NOT NULL) ";
      
        String sqlaudio = "CREATE TABLE AUDIO " +
              "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
              " TITRE              TEXT    NOT NULL, " +
              " AUTEUR             TEXT    NOT NULL, " +
              " ALBUM              TEXT    NOT NULL, " +
              " IMAGE              TEXT    NOT NULL, " +
              " NBEMPRUNT          INT     NOT NULL, " +
              " NBEXEMPLAIRE       INT     NOT NULL, " +
              " TARIF		       INT     NOT NULL) ";
      
        String sqlfiche = "CREATE TABLE FICHE " +
              "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
              " USERID             INT     NOT NULL, " + 
              " DOCID              INT     NOT NULL, " +
              " TYPEDOC            TEXT     NOT NULL, " +
              " DATEEMPRUNT        TEXT    NOT NULL, " +
              " DATEFIN            TEXT    NOT NULL)  ";
      
      
        String sqllivre = "CREATE TABLE LIVRE " +
              "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
              " TITRE              TEXT    NOT NULL, " +
              " AUTEUR             TEXT    NOT NULL, " +
              " ANNEE              TEXT    NOT NULL, " +
              " CATEGORY           TEXT    NOT NULL, " +
              " IMAGE              TEXT    NOT NULL, " +
              " NBEMPRUNT          INT     NOT NULL, " +
              " NBEXEMPLAIRE       INT     NOT NULL, " +
              " DESCRIPTION        TEXT    NOT NULL, " +
              " TARIF		       INT     NOT NULL) ";
	      
	     this.updatesql(sqllivre);
	     this.updatesql(sqlfiche);
	     this.updatesql(sqlaudio);
	     this.updatesql(sqlvideo);
	     this.updatesql(sqllogin);
	}
	
	public void fillTables (){
		this.connect();
		String sqlaudiorequest = "UPDATE AUDIO SET NBEXEMPLAIRE = 2";
		String sqlvideorequest = "UPDATE VIDEO SET NBEXEMPLAIRE = 2";
		String sqllivrerequest = "UPDATE LIVRE SET NBEXEMPLAIRE = 2";
		String sqlaudiorequest1 = "UPDATE AUDIO SET TARIF = 1";
		String sqlvideorequest1 = "UPDATE VIDEO SET TARIF = 3";
		String sqllivrerequest1 = "UPDATE LIVRE SET TARIF = 2";
		this.updatesql(sqllivrerequest1);
		this.updatesql(sqllivrerequest);
		this.updatesql(sqlaudiorequest1);
		this.updatesql(sqlaudiorequest);
		this.updatesql(sqlvideorequest1);
		this.updatesql(sqlvideorequest);
		this.disconnect();
	}
	
}

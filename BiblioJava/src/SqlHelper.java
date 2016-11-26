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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public void disconnect(){
		 try {
			stmt.close();
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchsql(String sqlsearch){
		rs = null;
		try {
			rs = stmt.executeQuery(sqlsearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatesql(String sqlupdate){
		try {
			stmt.execute(sqlupdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getNext () {
		try {
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public int getInt (String value) {
		try {
			return rs.getInt(value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getString (String value) {
		try {
			return rs.getString(value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
}

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FicheMusique extends FicheDoc {

	public FicheMusique(int Id) {
		super(Id);
		req(Id);
		super.addUI();
		// TODO Auto-generated constructor stub
	}
	
	public void req (int Id){
		String sqlsearch = "SELECT * FROM AUDIO where ID = " + Id;
		 try {
			Class.forName("org.sqlite.JDBC");
			Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
	        System.out.println("Opened database Musique successfully");
	        Statement stmt = connexion.createStatement();
	        ResultSet rs = stmt.executeQuery(sqlsearch);
	        titre =rs.getString("titre");
	        description = rs.getString("description");
	        image = rs.getString("image");
	        stmt.close();
		    connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}

}

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

public class FicheLivre extends FicheDoc {

	public FicheLivre(int Id) {
		super(Id);
		req(Id);
		// TODO Auto-generated constructor stub
	}
	public void req (int Id){
		String sqlsearch = "SELECT * FROM LIVRE where ID = " + Id;
		 try {
			Class.forName("org.sqlite.JDBC");
			Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
	        System.out.println("Opened database Video successfully");
	        Statement stmt = connexion.createStatement();
	        ResultSet rs = stmt.executeQuery(sqlsearch);
	        labelTitre.setText(rs.getString("titre"));
	        labelDescription.setText(rs.getString("description"));
	        try {
				labelImage = new JLabel( (Icon) new ImageIcon( new URL(rs.getString("image")) ) );
				System.out.println(rs.getString("image"));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				labelImage = new JLabel("erreur");
				e.printStackTrace();
			}
	        stmt.close();
		    connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}

}

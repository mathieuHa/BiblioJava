import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FicheDoc {
	private JFrame jf;
	private JPanel panel;
	private JLabel labelTitre;
	private JPanel panelTitre;
	private JLabel labelDescription;
	private JLabel labelImage;
	private String image;
	
	public FicheDoc (int Id){
		jf = new JFrame ();
		jf.setTitle("A");
		jf.setMinimumSize(new Dimension(450,600));
		
		jf.setVisible(true);
		
		panel = new JPanel(new BorderLayout());
		panelTitre = new JPanel();
		labelTitre = new JLabel("Titre");
		labelImage = new JLabel ("erreur");
		labelDescription = new JLabel("Description");
		
		
		req(Id);
		
		panel.add(labelTitre,BorderLayout.NORTH);
		panel.add(labelDescription,BorderLayout.CENTER);
		panel.add(labelImage,BorderLayout.SOUTH);
		jf.add(panel);
		
	}
	
	public void req (int Id){
		String sqlsearch = "SELECT * FROM VIDEO where ID = " + Id;
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

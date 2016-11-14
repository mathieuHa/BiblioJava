import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AddMusique extends AddDocument implements ActionListener{

	private JPanel panel;
	private Audio newMusique;

	public AddMusique() {
		super("Musique");
		panel = new JPanel(new GridLayout(4,1));
		super.ajouterValiderB(panel);
		boutonvalider.addActionListener(this);
		this.getContentPane().add(panel);
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent arg0) { // formatter integer only autorisé a faire
		super.actionPerformed(arg0);
		if (!docIsOK){
			
		}
		else {
			newMusique = new Audio (newDoc);
			String sqltest = "SELECT COUNT(*) AS sum FROM AUDIO where TITRE='"+newMusique.getTitre()+"'";
			String sqlinsert = "INSERT INTO AUDIO (CODE,TITRE,AUTEUR,ANNEE,EMPRUNTABLE,EMPRUNTE,"
												+ "NBEMPRUNT,NBEXEMPLAIRE,DUREEEMPRUNT,TARIF) " +
	                   "VALUES ('" + newMusique.getCode() + "',"
	                   		 + "'" + newMusique.getTitre() + "',"
	                   		 + "'" + newMusique.getAuteur() + "',"
	                   		 + "'" + newMusique.getAnnee() + "',"
	                   		 + "'" + newMusique.isEmpruntable() + "',"
	                   		 + "'" + newMusique.isEmprunte() + "',"
	                   		 + "'" + newMusique.getNbEmprunt() + "',"
	                   		 + "'" + newMusique.getNbExemplaire() + "',"
	                   		 + "'" + newMusique.getDureeEmprunt() + "',"
	                   		 + "'" + newMusique.getTarif() + "');"; 
			try {
			      Class.forName("org.sqlite.JDBC");
			      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
			      System.out.println("Opened database Musie successfully");
			      Statement stmt = connexion.createStatement();
			      ResultSet rs = stmt.executeQuery(sqltest);
			      //System.out.println(rs.getInt("sum"));
			      if (rs.getInt("sum")!=0){
			    	  System.out.println("existe deja");
			    	  JOptionPane.showMessageDialog(null, "titre already exist", "Attention", JOptionPane.WARNING_MESSAGE);
			      } else {
			    	  stmt.executeUpdate(sqlinsert);
			    	  System.out.println("ajout");
			      }
			      stmt.close();
			      connexion.close();
			    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
		}
	}

}

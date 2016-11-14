import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddLivre extends AddDocument implements ActionListener{

	private JPanel panel;
	private JPanel panelAddNbPages;
	private JLabel labelNbPages;
	private JTextField fieldNbPages;
	private Livre newLivre;

	public AddLivre() {
		super("Livre");
		
		panel = new JPanel(new GridLayout(4,1));
		panelAddNbPages = new JPanel();
		
		panelAddNbPages.setLayout(new BoxLayout(panelAddNbPages,BoxLayout.X_AXIS));
		
		labelNbPages = new JLabel ("NbPages");
		
		fieldNbPages = new JTextField();
		
		fieldNbPages.setPreferredSize(new Dimension(200, 25));
		fieldNbPages.setMaximumSize(new Dimension(200, 25));
		
		panelAddNbPages.add(Box.createHorizontalStrut(50));
		panelAddNbPages.add(labelNbPages);
		panelAddNbPages.add(Box.createHorizontalGlue());
		panelAddNbPages.add(fieldNbPages);
		panelAddNbPages.add(Box.createHorizontalStrut(50));
		
		panel.add(panelAddNbPages);
		boutonvalider.addActionListener(this);
		super.ajouterValiderB(panel);
		this.getContentPane().add(panel,BorderLayout.CENTER);
		
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent arg0) { // formatter integer only autorisé a faire
		super.actionPerformed(arg0);
		if (!docIsOK){
			
		}
		else if (fieldNbPages.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "NbPages empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else {
			newLivre = new Livre (newDoc, Integer.parseInt(fieldNbPages.getText()));
			String sqltest = "SELECT COUNT(*) AS sum FROM Livre where TITRE='"+newLivre.getTitre()+"'";
			String sqlinsert = "INSERT INTO LIVRE (CODE,TITRE,AUTEUR,ANNEE,EMPRUNTABLE,EMPRUNTE,"
												+ "NBEMPRUNT,NBEXEMPLAIRE,NBPAGE,DUREEEMPRUNT,TARIF) " +
	                   "VALUES ('" + newLivre.getCode() + "',"
	                   		 + "'" + newLivre.getTitre() + "',"
	                   		 + "'" + newLivre.getAuteur() + "',"
	                   		 + "'" + newLivre.getAnnee() + "',"
	                   		 + "'" + newLivre.isEmpruntable() + "',"
	                   		 + "'" + newLivre.isEmprunte() + "',"
	                   		 + "'" + newLivre.getNbEmprunt() + "',"
	                   		 + "'" + newLivre.getNbExemplaire() + "',"
	                   		 + "'" + newLivre.getNbPage() + "',"
	                   		 + "'" + newLivre.getDureeEmprunt() + "',"
	                   		 + "'" + newLivre.getTarif() + "');"; 
			try {
			      Class.forName("org.sqlite.JDBC");
			      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
			      System.out.println("Opened database successfully");
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

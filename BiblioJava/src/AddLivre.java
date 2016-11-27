
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddLivre extends AddDocument implements ActionListener{
	private SqlHelper sqlHelper;
	private JPanel panel;
	private JPanel panelAddNbPages;
	
	
	private JLabel labelAuteur = new JLabel("Auteur");
	private JLabel labelAnnee = new JLabel("Annee");
	private JLabel labelCategory = new JLabel("Categorie");
	private JLabel labelImage = new JLabel("Image URL");
	private JLabel labelNbPages = new JLabel("Nb pages");
	private JLabel labelDescription = new JLabel("Description");
	private JTextField fieldNbPages = new JTextField();
	private JTextArea fieldDescription = new JTextArea();
	private JTextField fieldAuteur = new JTextField();
	private JTextField fieldAnnee = new JTextField();
	private JTextField fieldImage = new JTextField();
	private JTextField fieldCategory = new JTextField();
	private Livre newLivre;
	/*			x  " TITRE              TEXT    NOT NULL, " +
                  " AUTEUR             TEXT    NOT NULL, " +
                  " ANNEE              TEXT    NOT NULL, " +
                  " CATEGORY           TEXT    NOT NULL, " +
                  " IMAGE              TEXT    NOT NULL, " +
                  " NBEMPRUNT          INT     NOT NULL, " +
                x  " NBEXEMPLAIRE       INT     NOT NULL, " +
                  " DESCRIPTION        TEXT    NOT NULL, " +
                x  " TARIF		       INT     NOT NULL) ";
    */
	public AddLivre() {
		super("Livre");
		
		fieldDescription.setPreferredSize(new Dimension(200, 200));
		fieldAuteur.setPreferredSize(new Dimension(200, 25));
		fieldAnnee.setPreferredSize(new Dimension(200, 25));
		fieldImage.setPreferredSize(new Dimension(200, 25));
		fieldCategory.setPreferredSize(new Dimension(200, 25));
		
		c.gridx = 0; c.gridy++;
		pane.add(labelAuteur,c);
		c.gridx = 1;
		pane.add(fieldAuteur, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelAnnee, c);
		c.gridx = 1;
		pane.add(fieldAnnee, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelImage, c);
		c.gridx = 1;
		pane.add(fieldImage, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelCategory, c);
		c.gridx = 1;
		pane.add(fieldCategory, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelDescription, c);
		c.gridx = 1; 
		pane.add(fieldDescription, c);
		
		this.add(pane, BorderLayout.CENTER);
		this.add(boutonvalider,BorderLayout.SOUTH);
		this.setVisible(true);
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
			sqlHelper.connect();
			sqlHelper.searchsql(sqltest);
			if (sqlHelper.getInt("sum")!=0){
				System.out.println("existe deja");
				JOptionPane.showMessageDialog(null, "titre already exist", "Attention", JOptionPane.WARNING_MESSAGE);
			} else {
				sqlHelper.updatesql(sqlinsert);
				System.out.println("ajout");
			}
			sqlHelper.disconnect();
		}
	}
	
}

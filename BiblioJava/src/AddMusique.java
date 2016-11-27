
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddMusique extends AddDocument implements ActionListener{

	private JPanel panel;
	private Audio newMusique;
	
	private JLabel labelAuteur = new JLabel("Auteur");
	private JLabel labelAlbum = new JLabel("Album");
	private JLabel labelImage = new JLabel("Image URL");
	private JTextField fieldAuteur = new JTextField();
	private JTextField fieldAlbum = new JTextField();
	private JTextField fieldImage = new JTextField();
	public AddMusique() {
		super("Musique");
		 /*" TITRE              TEXT    NOT NULL, " +
         " AUTEUR             TEXT    NOT NULL, " +
         " ALBUM              TEXT    NOT NULL, " +
         " IMAGE              TEXT    NOT NULL, " +
        x " NBEMPRUNT          INT     NOT NULL, " +
        x " NBEXEMPLAIRE       INT     NOT NULL, " +
        x " TARIF		       INT     NOT NULL) ";*/
		fieldImage.setPreferredSize(new Dimension(200, 25));
		fieldAuteur.setPreferredSize(new Dimension(200, 25));
		fieldAlbum.setPreferredSize(new Dimension(200, 25));
		c.gridx = 0; c.gridy++;
		pane.add(labelAlbum, c);
		c.gridx = 1;
		pane.add(fieldAlbum, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelAuteur, c);
		c.gridx = 1;
		pane.add(fieldAuteur, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelImage, c);
		c.gridx = 1;
		pane.add(fieldImage, c);
		
		this.add(pane, BorderLayout.CENTER);
		this.add(boutonvalider,BorderLayout.SOUTH);
		this.setVisible(true);
		/*panel = new JPanel(new GridLayout(4,1));
		super.ajouterValiderB(panel);
		boutonvalider.addActionListener(this);
		this.getContentPane().add(panel);
		// TODO Auto-generated constructor stub*/
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

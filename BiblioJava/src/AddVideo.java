
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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddVideo extends AddDocument implements ActionListener{

	private JPanel panel;
	private JButton boutontest;
	private JPanel panelAddDuree;
	private JPanel panelAddMentionLegale;
	private JLabel labelDuree;
	private JLabel labelMentionLegale;
	private JTextField fieldDuree;
	private JTextField fieldMentionLegale;
	private Video newVideo;

	private JLabel labelAnnee = new JLabel("Annee");
	private JLabel labelLangage = new JLabel("Langage");
	private JLabel labelImage = new JLabel("Image URL");
	private JLabel labelNote = new JLabel("Note");
	private JLabel labelDescription = new JLabel("Description");
	private JTextArea fieldDescription = new JTextArea();
	private JTextField fieldAnnee = new JTextField();
	private JTextField fieldNote = new JTextField();
	private JTextField fieldImage = new JTextField();
	private JTextField fieldLangage = new JTextField();
	public AddVideo() {
		super("Video");
		
		fieldDescription.setPreferredSize(new Dimension(200, 200));
		fieldNote.setPreferredSize(new Dimension(200, 25));
		fieldAnnee.setPreferredSize(new Dimension(200, 25));
		fieldImage.setPreferredSize(new Dimension(200, 25));
		fieldLangage.setPreferredSize(new Dimension(200, 25));
		
		c.gridx = 0; c.gridy++;
		pane.add(labelAnnee,c);
		c.gridx = 1;
		pane.add(fieldAnnee, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelNote, c);
		c.gridx = 1;
		pane.add(fieldNote, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelImage, c);
		c.gridx = 1;
		pane.add(fieldImage, c);
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
		else if (fieldDuree.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Duree empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else if (fieldMentionLegale.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Duree empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else {
			newVideo = new Video (newDoc, Integer.parseInt(fieldDuree.getText()), fieldMentionLegale.getText());
			String sqltest = "SELECT COUNT(*) AS sum FROM VIDEO where TITRE='"+newVideo.getTitre()+"'";
			String sqlinsert = "INSERT INTO VIDEO (CODE,TITRE,AUTEUR,ANNEE,EMPRUNTABLE,EMPRUNTE,"
												+ "NBEMPRUNT,NBEXEMPLAIRE,MENTIONLEGALE,DUREEFILM,DUREEEMPRUNT,TARIF) " +
	                   "VALUES ('" + newVideo.getCode() + "',"
	                   		 + "'" + newVideo.getTitre() + "',"
	                   		 + "'" + newVideo.getAuteur() + "',"
	                   		 + "'" + newVideo.getAnnee() + "',"
	                   		 + "'" + newVideo.isEmpruntable() + "',"
	                   		 + "'" + newVideo.isEmprunte() + "',"
	                   		 + "'" + newVideo.getNbEmprunt() + "',"
	                   		 + "'" + newVideo.getNbExemplaire() + "',"
	                   		 + "'" + newVideo.getMentionLegale() + "',"
	                   		 + "'" + newVideo.getDureeFilm() + "',"
	                   		 + "'" + newVideo.getDureeEmprunt() + "',"
	                   		 + "'" + newVideo.getTarif() + "');"; 
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

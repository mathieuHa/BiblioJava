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

	public AddVideo() {
		super("Video");
		// TODO Auto-generated constructor stub
		panel = new JPanel(new GridLayout(4,1));
		panelAddDuree = new JPanel();
		panelAddMentionLegale = new JPanel();
		
		panelAddDuree.setLayout(new BoxLayout(panelAddDuree,BoxLayout.X_AXIS));
		panelAddMentionLegale.setLayout(new BoxLayout(panelAddMentionLegale,BoxLayout.X_AXIS));
		
		labelDuree = new JLabel ("Duree");
		labelMentionLegale = new JLabel ("Mentions Legales");
		
		fieldDuree = new JTextField();
		fieldMentionLegale = new JTextField();
		
		fieldDuree.setPreferredSize(new Dimension(200, 25));
		fieldDuree.setMaximumSize(new Dimension(200, 25));
		fieldMentionLegale.setPreferredSize(new Dimension(200, 25));
		fieldMentionLegale.setMaximumSize(new Dimension(200, 25));
		
		panelAddDuree.add(Box.createHorizontalStrut(50));
		panelAddDuree.add(labelDuree);
		panelAddDuree.add(Box.createHorizontalGlue());
		panelAddDuree.add(fieldDuree);
		panelAddDuree.add(Box.createHorizontalStrut(50));
		
		panelAddMentionLegale.add(Box.createHorizontalStrut(50));
		panelAddMentionLegale.add(labelMentionLegale);
		panelAddMentionLegale.add(Box.createHorizontalGlue());
		panelAddMentionLegale.add(fieldMentionLegale);
		panelAddMentionLegale.add(Box.createHorizontalStrut(50));
		
		panel.add(panelAddDuree);
		panel.add(panelAddMentionLegale);
		boutonvalider.addActionListener(this);
		super.ajouterValiderB(panel);
		
		this.getContentPane().add(panel);

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


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddVideo extends AddDocument implements ActionListener{

	private JLabel labelAnnee = new JLabel("Annee");
	private JLabel labelLangage = new JLabel("Langage");
	private JLabel labelImage = new JLabel("Image URL");
	private JLabel labelNote = new JLabel("Note");
	private JLabel labelDescription = new JLabel("Description");
	private JTextArea fieldDescription = new JTextArea();
	private JFormattedTextField fieldAnnee = new JFormattedTextField( NumberFormat.getNumberInstance() );
	private JFormattedTextField fieldNote = new JFormattedTextField( NumberFormat.getNumberInstance() );
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
		pane.add(labelLangage, c);
		c.gridx = 1;
		pane.add(fieldLangage, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelImage, c);
		c.gridx = 1;
		pane.add(fieldImage, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelDescription, c);
		c.gridx = 1;
		pane.add(fieldDescription, c);
		
		this.add(pane, BorderLayout.CENTER);
		boutonvalider.addActionListener(this);
		this.add(boutonvalider,BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) { // formatter integer only autorisé a faire
		super.actionPerformed(arg0);
		if (!docIsOK){
			
		}
		else if (fieldDescription.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Desc empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else if (fieldImage.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Img empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else if (fieldAnnee.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Annee empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else if (fieldNote.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Note empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else if (fieldLangage.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "langue empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else {
			/*" TITRE              TEXT    NOT NULL, " +
                  " ANNEE              TEXT    NOT NULL, " +
                  " IMAGE              TEXT    NOT NULL, " +
                  " DESCRIPTION        TEXT    NOT NULL, " +
                  " LANGUAGE           TEXT    NOT NULL, " +
                  " NBEMPRUNT          INT     NOT NULL, " +
                  " NBEXEMPLAIRE       INT     NOT NULL, " +
                  " NOTE               TEXT    NOT NULL, " +
                  " TARIF		       INT     NOT NULL) ";*/
			//newVideo = new Video (newDoc, Integer.parseInt(fieldDuree.getText()), fieldMentionLegale.getText());
			String sqltest = "SELECT COUNT(*) AS sum FROM VIDEO where TITRE='"+fieldTitre.getText().toString()+"'";
			String sqlinsert = "INSERT INTO VIDEO (TITRE, ANNEE, IMAGE, DESCRIPTION,LANGUAGE,"
					+ "NBEMPRUNT, NBEXEMPLAIRE,NOTE,TARIF) " +
					"VALUES ('" + fieldTitre.getText().toString() + "',"
					+ "'" + fieldAnnee.getText() + "',"
					+ "'" + fieldImage.getText().toString() + "',"
					+ "'" + fieldDescription.getText().toString() + "',"
					+ "'" + fieldLangage.getText().toString() + "',"
					+ "'" + fieldNbEmprunt.getText() + "',"
					+ "'" + fieldNbExemplaire.getText() + "',"
					+ "'" + fieldNote.getText() + "',"
					+ "'" + fieldTarif.getText() + "');";
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
			this.dispose();
		}

	}
	
}


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddMusique extends AddDocument implements ActionListener{

	private JLabel labelAuteur = new JLabel("Auteur");
	private JLabel labelAlbum = new JLabel("Album");
	private JLabel labelImage = new JLabel("Image URL");
	private JTextField fieldAuteur = new JTextField();
	private JTextField fieldAlbum = new JTextField();
	private JTextField fieldImage = new JTextField();
	public AddMusique() {
		super("Musique");
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
		boutonvalider.addActionListener(this);
		this.add(boutonvalider,BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) { // formatter integer only autorisé a faire
		super.actionPerformed(arg0);
		if (!docIsOK){
			
		}
		else if (fieldAuteur.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "NbPages empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else if (fieldImage.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "NbPages empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else if (fieldAlbum.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "NbPages empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else {
			/*" TITRE              TEXT    NOT NULL, " +
	         " AUTEUR             TEXT    NOT NULL, " +
	         " ALBUM              TEXT    NOT NULL, " +
	         " IMAGE              TEXT    NOT NULL, " +
	        x " NBEMPRUNT          INT     NOT NULL, " +
	        x " NBEXEMPLAIRE       INT     NOT NULL, " +
	        x " TARIF		       INT     NOT NULL) ";*/
			//newMusique = new Audio (newDoc);
			String sqltest = "SELECT COUNT(*) AS sum FROM AUDIO where TITRE='"+fieldTitre.getText().toString()+"'";
			String sqlinsert = "INSERT INTO AUDIO (TITRE,AUTEUR,ALBUM,IMAGE,"
												+ "NBEMPRUNT,NBEXEMPLAIRE,TARIF) " +
	                   "VALUES ('" + fieldTitre.getText().toString() + "',"
	                   		 + "'" + fieldAuteur.getText().toString() + "',"
	                   		 + "'" + fieldAlbum.getText().toString() + "',"
	                   		 + "'" + fieldImage.getText().toString() + "',"
	                   		 + "'" + fieldNbEmprunt.getText() + "',"
	                   		 + "'" + fieldNbExemplaire.getText() + "',"
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

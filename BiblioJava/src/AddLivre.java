
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

@SuppressWarnings("serial")
public class AddLivre extends AddDocument implements ActionListener{
	private SqlHelper sqlHelper;
	
	
	private JLabel labelAuteur = new JLabel("Auteur");
	private JLabel labelAnnee = new JLabel("Annee");
	private JLabel labelCategory = new JLabel("Categorie");
	private JLabel labelImage = new JLabel("Image URL");
	private JLabel labelDescription = new JLabel("Description");
	private JTextArea fieldDescription = new JTextArea();
	private JTextField fieldAuteur = new JTextField();
	private JFormattedTextField fieldAnnee = new JFormattedTextField( NumberFormat.getNumberInstance() );
	private JTextField fieldImage = new JTextField();
	private JTextField fieldCategory = new JTextField();
	private Livre newLivre;
	
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
		else if (fieldCategory.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "NbPages empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else if (fieldDescription.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "NbPages empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else if (fieldImage.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "NbPages empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else if (fieldAnnee.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "NbPages empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
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
		if (arg0.getSource() == boutonvalider) {
			
			//newLivre = new Livre (newDoc, Integer.parseInt(fieldNbPages.getText()));
			String sqltest = "SELECT COUNT(*) AS sum FROM LIVRE where TITRE='"+fieldTitre.getText().toString()+"'";
			String sqlinsert = "INSERT INTO LIVRE (TITRE, AUTEUR, ANNEE, CATEGORY, IMAGE, NBEMPRUNT,"
					+ "NBEXEMPLAIRE, DESCRIPTION, TARIF) " +
	                   "VALUES ('" + fieldTitre.getText().toString() +"',"
	                   		 + "'" + fieldAuteur.getText().toString() + "',"
	                   		 + "'" + fieldAnnee.getText() + "',"
	                   		 + "'" + fieldCategory.getText().toString() + "',"
	                   		 + "'" + fieldImage.getText().toString() + "',"
	                   		 + "'" + fieldNbEmprunt.getText() + "',"
	                   		 + "'" + fieldNbExemplaire.getText() + "',"
	                   		 + "'" + fieldDescription.getText().toString() + "',"
	                   		 + "'" + fieldTarif.getText() + "')";
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

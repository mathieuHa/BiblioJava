import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddDocument extends JFrame implements ActionListener{
	Document newDoc;
	private JPanel panel;
	private JPanel panelAdd;
	private JPanel panelAddTitre;
	private JPanel panelAddAuteur;
	private JPanel panelAddAnnee;
	private JPanel panelAddNbExemplaire;
	private JLabel labelTitre;
	private JLabel labelAuteur;
	private JLabel labelAnnee;
	private JLabel labelNbExemplaire;
	private JTextField fieldTitre;
	private JTextField fieldAuteur;
	private JTextField fieldAnnee;
	private JTextField fieldNbExemplaire;
	private JPanel contentPane;
	protected JButton boutonvalider;
	protected boolean docIsOK;
	public AddDocument(String title) {
		docIsOK = false;
		this.setTitle("Add " + title);
		this.setMinimumSize(new Dimension(450,600));
		boutonvalider = new JButton("Valider");
		panel = new JPanel ();
		panel.setLayout(new GridLayout(2,1));
		panelAdd = new JPanel();
		panelAdd.setLayout(new GridLayout(4,1));
		panelAddTitre = new JPanel();
		panelAddAuteur = new JPanel();
		panelAddAnnee = new JPanel();
		panelAddNbExemplaire = new JPanel();
		
		panelAddTitre.setLayout(new BoxLayout(panelAddTitre,BoxLayout.X_AXIS));
		panelAddAuteur.setLayout(new BoxLayout(panelAddAuteur,BoxLayout.X_AXIS));
		panelAddAnnee.setLayout(new BoxLayout(panelAddAnnee,BoxLayout.X_AXIS));
		panelAddNbExemplaire.setLayout(new BoxLayout(panelAddNbExemplaire,BoxLayout.X_AXIS));
		
		labelTitre = new JLabel ("Titre");
		labelAuteur = new JLabel ("Auteur");
		labelAnnee = new JLabel ("Annee");
		labelNbExemplaire = new JLabel ("Nombre Exemplaires");
		
		fieldTitre = new JTextField();
		fieldAuteur = new JTextField();
		fieldAnnee = new JTextField();
		fieldNbExemplaire = new JTextField();
		fieldTitre.setPreferredSize(new Dimension(200, 25));
		fieldTitre.setMaximumSize(new Dimension(200, 25));
		fieldAuteur.setPreferredSize(new Dimension(200, 25));
		fieldAuteur.setMaximumSize(new Dimension(200, 25));
		fieldAnnee.setPreferredSize(new Dimension(200, 25));
		fieldAnnee.setMaximumSize(new Dimension(200, 25));
		fieldNbExemplaire.setPreferredSize(new Dimension(200, 25));
		fieldNbExemplaire.setMaximumSize(new Dimension(200, 25));
		
		panelAddTitre.add(Box.createHorizontalStrut(50));
		panelAddTitre.add(labelTitre);
		panelAddTitre.add(Box.createHorizontalGlue());
		panelAddTitre.add(fieldTitre);
		panelAddTitre.add(Box.createHorizontalStrut(50));
		
		panelAddAuteur.add(Box.createHorizontalStrut(50));
		panelAddAuteur.add(labelAuteur);
		panelAddAuteur.add(Box.createHorizontalGlue());
		panelAddAuteur.add(fieldAuteur);
		panelAddAuteur.add(Box.createHorizontalStrut(50));
		
		panelAddAnnee.add(Box.createHorizontalStrut(50));
		panelAddAnnee.add(labelAnnee);
		panelAddAnnee.add(Box.createHorizontalGlue());
		panelAddAnnee.add(fieldAnnee);
		panelAddAnnee.add(Box.createHorizontalStrut(50));
		
		panelAddNbExemplaire.add(Box.createHorizontalStrut(50));
		panelAddNbExemplaire.add(labelNbExemplaire);
		panelAddNbExemplaire.add(Box.createHorizontalGlue());
		panelAddNbExemplaire.add(fieldNbExemplaire);
		panelAddNbExemplaire.add(Box.createHorizontalStrut(50));
		
		
		panelAdd.add(panelAddTitre);
		panelAdd.add(panelAddAuteur);
		panelAdd.add(panelAddAnnee);
		panelAdd.add(panelAddNbExemplaire);
		
		
		panel.add(panelAdd);
		
		//boutonvalider.addActionListener(this);
		
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	protected void ajouterValiderB (JPanel pane){
		JPanel panelB = new JPanel ();
		panelB.setLayout(new BoxLayout(panelB,BoxLayout.X_AXIS));
		panelB.add(Box.createHorizontalGlue());
		panelB.add(boutonvalider);
		panelB.add(Box.createHorizontalGlue());
		pane.add(panelB);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { // formatter integer only autorisé a faire
		// TODO Auto-generated method stub
		if (arg0.getSource() == boutonvalider) {
			if (fieldTitre.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Titre empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else if (fieldAuteur.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Auteur empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else if (fieldAnnee.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Annee empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else if (fieldNbExemplaire.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Nb Exemplaire empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else {
				newDoc = new Document (fieldTitre.getText(),fieldAuteur.getText(),Integer.parseInt(fieldAnnee.getText()),Integer.parseInt(fieldNbExemplaire.getText()));
				System.out.println("ok doc");
				docIsOK = true;
			}
		}
	}
	
	
}

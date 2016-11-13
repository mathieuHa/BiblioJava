import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddDocument extends JFrame {
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
	private JPanel panelAddTitreL;
	private JPanel panelAddAuteurL;
	private JPanel panelAddAnneeL;
	private JPanel panelAddNbExemplaireL;
	private JPanel panelAddTitreF;
	private JPanel panelAddAuteurF;
	private JPanel panelAddAnneeF;
	private JPanel panelAddNbExemplaireF;

	public AddDocument(String title) {
		this.setTitle("Add " + title);
		this.setMinimumSize(new Dimension(450,600));
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
		//panelAdd.add(panelAddTitreF);
		panelAdd.add(panelAddAuteur);
		//panelAdd.add(panelAddAuteurF);
		panelAdd.add(panelAddAnnee);
		//panelAdd.add(panelAddAnneeF);
		panelAdd.add(panelAddNbExemplaire);
		//panelAdd.add(panelAddNbExemplaireF);
		
		panel.add(panelAdd);
		
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	
}

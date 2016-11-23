import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FicheDoc {
	protected JFrame jf;
	protected JPanel panel;
	protected JLabel labelTitre;
	protected JPanel panelTitre;
	protected JTextArea labelDescription;
	protected JLabel labelImage;
	protected JPanel panelAuteur;
	protected JPanel panelDescription;
	protected JPanel panelExemplaire;
	protected JLabel labelNbExemplaire;
	protected JLabel labelNbEmprunt;
	protected JPanel panelCommun;
	protected JTextArea labelAuteur;
	private JScrollPane scrollDescription;
	protected JPanel panelAuteurplusImage;
	protected JPanel panelImage;
	protected String image;
	protected String auteur;
	protected String description;
	protected String titre; 
	
	public FicheDoc (int Id){
		System.out.println("new fiche DOC");
		
		
	}
	
	public void addUI (){
		jf = new JFrame ();
		jf.setTitle("Détail Article");
		jf.setMinimumSize(new Dimension(600,800));
		jf.setResizable(false);
		jf.setVisible(true);
		setPanel(new JPanel(new BorderLayout()));
		panelTitre = new JPanel();
		panelAuteur = new JPanel ();
		panelDescription = new JPanel ();
		panelExemplaire = new JPanel ();
		panelExemplaire.setLayout(new BoxLayout(panelExemplaire,BoxLayout.X_AXIS));
		
		labelTitre = new JLabel("Titre");
		labelImage = new JLabel ("erreur");
		labelDescription = new JTextArea("Description");
		labelNbExemplaire = new JLabel ("Nb Exemplaire");
		labelNbEmprunt    = new JLabel ("Nb Emprunt" );
		//labelAuteur = new JLabel ("Auteur ");
		//scrollDescription = new JScrollPane (labelDescription);
		//scrollDescription.setPreferredSize(new Dimension(400,200));
		labelDescription.setLineWrap(true);
		labelDescription.setWrapStyleWord(true);
		labelDescription.setEditable(false);
		labelDescription.setPreferredSize(new Dimension(400,200));
		
		
		//req(Id);
		panelTitre.add(labelTitre);
		panelTitre.setBackground(Color.CYAN);
		panelExemplaire.add(Box.createHorizontalStrut(30));
		panelExemplaire.add(labelNbExemplaire);
		panelExemplaire.add(Box.createHorizontalStrut((int) (100-labelNbExemplaire.getPreferredSize().getWidth())));
		panelExemplaire.add(labelNbEmprunt);
		panelExemplaire.add(Box.createHorizontalGlue());
		
		
		try {
			labelImage = new JLabel( (Icon) new ImageIcon( new URL(image) ) );
		} catch (MalformedURLException e) {
			labelImage = new JLabel("erreur");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		labelTitre.setText(titre);
		labelDescription.setText(description);
		//labelAuteur.setText(auteur);
		
		panelImage = new JPanel();
		panelImage.add(labelImage);
		
		panelDescription.add(labelDescription);
		panelAuteur.add(labelAuteur);
		
		panelAuteurplusImage = new JPanel ();
		panelAuteurplusImage.setLayout(new BoxLayout(panelAuteurplusImage,BoxLayout.X_AXIS));
		
		panelAuteurplusImage.add(Box.createHorizontalStrut(50));
		panelAuteurplusImage.add(panelAuteur);
		//panelAuteurplusImage.add(Box.createHorizontalStrut((int) (200-panelAuteur.getPreferredSize().getWidth())));
		panelAuteurplusImage.add(panelImage);
		
		panelCommun = new JPanel(new GridLayout(2,1));
		panelCommun.add(panelAuteurplusImage);
		panelCommun.add(panelDescription);
		//panelCommun.add(panelExemplaire);
		
		
		getPanel().add(panelTitre,BorderLayout.NORTH);
		getPanel().add(panelCommun,BorderLayout.CENTER);
		//panel.add(labelImage,BorderLayout.SOUTH);
		jf.add(getPanel());
		
	}
	
	protected void updatePanel(){
		panel.updateUI();
		panelCommun.updateUI();
		panelImage.updateUI();
		panelAuteurplusImage.updateUI();
		System.out.println("update");
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	
}

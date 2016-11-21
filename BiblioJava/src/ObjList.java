import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class ObjList {
	private JPanel obj;
	
	private JLabel labelauteur;
	private JLabel labeltitle;
	private JLabel labelduree;

	private JLabel NbE;

	private JButton buttonVP;

	private JLabel labelannee;

	private JLabel labelcategory;

	private JLabel labelnote;

	private JLabel labeldescription;

	private JLabel labelimage;
	
	
	
	public ObjList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjList (String titre, String auteur, int annee, int duree, int nbExemplaire, JPanel Pane, int width)
	{
		obj = new JPanel();
		obj.setLayout(new BoxLayout(obj,BoxLayout.X_AXIS));
		labeltitle = new JLabel(titre);
		labelauteur = new JLabel(auteur);
		labelannee = new JLabel ("" + annee);
		labelduree = new JLabel("" + duree);
		NbE = new JLabel("" + nbExemplaire);
		try {
			labelimage = new JLabel( (Icon) new ImageIcon( new URL( "http://bilad.fr/model_fichiers/img_surgele.jpg" ) ) );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			labelimage = new JLabel("erreur");
			e.printStackTrace();
		}
		buttonVP = new JButton("Voir Plus");
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labelimage);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelimage.getPreferredSize().getWidth()));
		obj.add(labeltitle);
		System.out.println(width);
		obj.add(Box.createHorizontalStrut(width/7-(int)labeltitle.getPreferredSize().getWidth()));
		obj.add(labelauteur);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelauteur.getPreferredSize().getWidth()));
		obj.add(labelannee);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelannee.getPreferredSize().getWidth()));
		obj.add(labelduree);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelduree.getPreferredSize().getWidth()));
		obj.add(NbE);
		obj.add(Box.createHorizontalStrut(width/7-(int)NbE.getPreferredSize().getWidth()));
		obj.add(buttonVP);
		obj.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Pane.add(obj);
		//System.out.println("ici");
	}
	

	public ObjList (String titre, String auteur, String annee, String category,String image, int nbExemplaire, JPanel Pane, int width)
	{
		int sizetext = 20;
		obj = new JPanel();
		obj.setLayout(new BoxLayout(obj,BoxLayout.X_AXIS));
		labeltitle = (titre.length() > sizetext) ? new JLabel(titre.substring(0, sizetext)+"..") : new JLabel(titre);
		labelauteur = (auteur.length() > sizetext) ? new JLabel(auteur.substring(0, sizetext)+"..") : new JLabel(auteur);
		labelannee = (annee.length() > sizetext) ? new JLabel(annee.substring(0, sizetext)+"..") : new JLabel (annee);
		labelcategory = (category.length() > sizetext) ? new JLabel(category.substring(0, sizetext)+"..") : new JLabel (category);
		NbE = new JLabel(""+nbExemplaire);
		labelimage = new JLabel("erreur");
		try {
			labelimage = new JLabel( (Icon) new ImageIcon( new URL(image) ) );
			labelimage.setMaximumSize(new Dimension(80,100));
			labelimage.setPreferredSize(labelimage.getMaximumSize());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			labelimage = new JLabel("erreur");
			e.printStackTrace();
		}
		buttonVP = new JButton("Voir Plus");
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labelimage);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelimage.getPreferredSize().getWidth()));
		obj.add(labeltitle);
		obj.add(Box.createHorizontalStrut(width/7-(int)labeltitle.getPreferredSize().getWidth()));
		obj.add(labelauteur);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelauteur.getPreferredSize().getWidth()));
		obj.add(labelannee);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelannee.getPreferredSize().getWidth()));
		obj.add(labelcategory);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelcategory.getPreferredSize().getWidth()));
		obj.add(NbE);
		obj.add(Box.createHorizontalStrut(width/7-(int)NbE.getPreferredSize().getWidth()));
		obj.add(buttonVP);
		obj.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Pane.add(obj);
		System.out.println("ici");
	}
	
	public JButton getButtonVP() {
		return buttonVP;
	}

	public void setButtonVP(JButton buttonVP) {
		this.buttonVP = buttonVP;
	}

	public JPanel helpVideo(int width, JButton bouton)
	{
		obj = new JPanel();
		obj.setLayout(new BoxLayout(obj,BoxLayout.X_AXIS));
		labeltitle = new JLabel("Titre");
		labelannee = new JLabel ("Année");
		labelnote = new JLabel ("Note");
		labeldescription = new JLabel ("Description");
		labelimage = new JLabel ("Couverture");
		NbE = new JLabel("Nombre exemplaire");
		bouton.setText("Reset");
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labelimage);
		System.out.println(width);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelimage.getPreferredSize().getWidth()));
		obj.add(labeltitle);
		System.out.println(width);
		obj.add(Box.createHorizontalStrut(width/7-(int)labeltitle.getPreferredSize().getWidth()));
		obj.add(labeldescription);
		obj.add(Box.createHorizontalStrut(width/7-(int)labeldescription.getPreferredSize().getWidth()));
		obj.add(labelannee);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelannee.getPreferredSize().getWidth()));
		obj.add(labelnote);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelnote.getPreferredSize().getWidth()));
		obj.add(NbE);
		obj.add(Box.createHorizontalStrut(width/7-(int)NbE.getPreferredSize().getWidth()));
		obj.add(bouton);
		//System.out.println("ici");
		return obj;
	}
	
	public JPanel helpMusique(int width, JButton bouton)
	{
		obj = new JPanel();
		obj.setLayout(new BoxLayout(obj,BoxLayout.X_AXIS));
		labeltitle = new JLabel("Titre");
		labelauteur = new JLabel("Auteur");
		labelannee = new JLabel ("Année");
		labelimage = new JLabel ("Couverture");
		NbE = new JLabel("Nombre exemplaire");
		bouton.setText("Reset");
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labeltitle);
		System.out.println(width);
		obj.add(Box.createHorizontalStrut(width/7-(int)labeltitle.getPreferredSize().getWidth()));
		obj.add(labelauteur);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelauteur.getPreferredSize().getWidth()));
		obj.add(labelannee);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelannee.getPreferredSize().getWidth()));
		obj.add(NbE);
		obj.add(Box.createHorizontalStrut(width/7-(int)NbE.getPreferredSize().getWidth()));
		obj.add(Box.createHorizontalStrut(width/7));
		obj.add(bouton);
		//obj.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		//System.out.println("ici");
		return obj;
	}
	
	public JPanel helpLivre(int width, JButton bouton)
	{
		obj = new JPanel();
		obj.setLayout(new BoxLayout(obj,BoxLayout.X_AXIS));
		labeltitle = new JLabel("Titre");
		labelauteur = new JLabel("Auteur");
		labelannee = new JLabel ("Année");
		labelcategory = new JLabel ("Catégorie");
		labelimage = new JLabel ("Couverture");
		NbE = new JLabel("Nombre exemplaire");
		bouton.setText("Reset");
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labelimage);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelimage.getPreferredSize().getWidth()));
		obj.add(labeltitle);
		obj.add(Box.createHorizontalStrut(width/7-(int)labeltitle.getPreferredSize().getWidth()));
		obj.add(labelauteur);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelauteur.getPreferredSize().getWidth()));
		obj.add(labelannee);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelannee.getPreferredSize().getWidth()));
		obj.add(labelcategory);
		obj.add(Box.createHorizontalStrut(width/7-(int)labelcategory.getPreferredSize().getWidth()));
		obj.add(NbE);
		obj.add(Box.createHorizontalStrut(width/7-(int)NbE.getPreferredSize().getWidth()));
		//obj.add(Box.createHorizontalStrut(width/6));
		obj.add(bouton);
		//obj.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		//System.out.println("ici");
		return obj;
	}
	
	
	public JPanel getObj() {
		return obj;
	}
	public void setObj(JPanel obj) {
		this.obj = obj;
	}
}

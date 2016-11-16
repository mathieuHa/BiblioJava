import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ObjList {
	private JPanel obj;
	
	private JLabel labelauteur;
	private JLabel labeltitle;
	private JLabel labelduree;

	private JLabel NbE;

	private JButton buttonVP;

	private JLabel labelannee;
	
	
	
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
		buttonVP = new JButton("Voir Plus");
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labeltitle);
		System.out.println(width);
		obj.add(Box.createHorizontalStrut(width/6-(int)labeltitle.getPreferredSize().getWidth()));
		obj.add(labelauteur);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelauteur.getPreferredSize().getWidth()));
		obj.add(labelannee);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelannee.getPreferredSize().getWidth()));
		obj.add(labelduree);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelduree.getPreferredSize().getWidth()));
		obj.add(NbE);
		obj.add(Box.createHorizontalStrut(width/6-(int)NbE.getPreferredSize().getWidth()));
		obj.add(buttonVP);
		obj.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Pane.add(obj);
		//System.out.println("ici");
	}
	public ObjList (String titre, String auteur, int annee, int nbExemplaire, JPanel Pane, int width)
	{
		obj = new JPanel();
		obj.setLayout(new BoxLayout(obj,BoxLayout.X_AXIS));
		labeltitle = new JLabel(titre);
		labelauteur = new JLabel(auteur);
		labelannee = new JLabel ("" + annee);
		NbE = new JLabel("" + nbExemplaire);
		buttonVP = new JButton("Voir Plus");
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labeltitle);
		System.out.println(width);
		obj.add(Box.createHorizontalStrut(width/6-(int)labeltitle.getPreferredSize().getWidth()));
		obj.add(labelauteur);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelauteur.getPreferredSize().getWidth()));
		obj.add(labelannee);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelannee.getPreferredSize().getWidth()));
		obj.add(NbE);
		obj.add(Box.createHorizontalStrut(width/6-(int)NbE.getPreferredSize().getWidth()));
		obj.add(buttonVP);
		obj.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Pane.add(obj);
		//System.out.println("ici");
	}
	
	public JPanel helpVideo(int width, JButton bouton)
	{
		obj = new JPanel();
		obj.setLayout(new BoxLayout(obj,BoxLayout.X_AXIS));
		labeltitle = new JLabel("Titre");
		labelauteur = new JLabel("Auteur");
		labelannee = new JLabel ("Année");
		labelduree = new JLabel("Durée");
		NbE = new JLabel("Nombre exemplaire");
		bouton.setText("Reset");
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labeltitle);
		System.out.println(width);
		obj.add(Box.createHorizontalStrut(width/6-(int)labeltitle.getPreferredSize().getWidth()));
		obj.add(labelauteur);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelauteur.getPreferredSize().getWidth()));
		obj.add(labelannee);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelannee.getPreferredSize().getWidth()));
		obj.add(labelduree);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelduree.getPreferredSize().getWidth()));
		obj.add(NbE);
		obj.add(Box.createHorizontalStrut(width/6-(int)NbE.getPreferredSize().getWidth()));
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
		NbE = new JLabel("Nombre exemplaire");
		bouton = new JButton("Reset");
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labeltitle);
		System.out.println(width);
		obj.add(Box.createHorizontalStrut(width/6-(int)labeltitle.getPreferredSize().getWidth()));
		obj.add(labelauteur);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelauteur.getPreferredSize().getWidth()));
		obj.add(labelannee);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelduree.getPreferredSize().getWidth()));
		obj.add(NbE);
		obj.add(Box.createHorizontalStrut(width/6-(int)NbE.getPreferredSize().getWidth()));
		obj.add(Box.createHorizontalStrut(width/6));
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
		NbE = new JLabel("Nombre exemplaire");
		bouton = new JButton("Reset");
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labeltitle);
		System.out.println(width);
		obj.add(Box.createHorizontalStrut(width/6-(int)labeltitle.getPreferredSize().getWidth()));
		obj.add(labelauteur);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelauteur.getPreferredSize().getWidth()));
		obj.add(labelannee);
		obj.add(Box.createHorizontalStrut(width/6-(int)labelannee.getPreferredSize().getWidth()));
		obj.add(NbE);
		obj.add(Box.createHorizontalStrut(width/6-(int)NbE.getPreferredSize().getWidth()));
		obj.add(Box.createHorizontalStrut(width/6));
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

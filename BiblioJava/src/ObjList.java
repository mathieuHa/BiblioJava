import java.awt.Color;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
	private JLabel NbE;
	private JButton buttonVP;
	private JLabel labelannee;
	private JLabel labelcategory;
	private JLabel labelnote;
	private JLabel labeldescription;
	private JLabel labelimage;
	private JLabel labeldateEmprunt;
	private JLabel labeldateFin;
	private JLabel labeltype;
	private JLabel labelTimer;
	private SwingWorker<Object, Object> sw; 
	private boolean cancel = false;
	private String bd;
	private String id;
	
	public void Sstart (String dateF){
		sw = new SwingWorker<Object, Object>(){
			@Override
			protected Object doInBackground() throws Exception {
				while (cancel == false){
					Thread.sleep(100);
					labelTimer.setText(Biblio.compteur(dateF));
					labelTimer.updateUI();
				}
				return cancel;
			}
		};
		sw.execute();
	}
	
	public ObjList() {
		super();
	}
	
	public void updateTitre (ArrayList<ObjList> objL) {
		int sizetext = 30;
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database FICHE successfully");
		      Statement stmt = connexion.createStatement();
		      for (ObjList o : objL){
			      String sqltitre = "SELECT * FROM "+ o.getBd() + " where id="+o.getId();
			      //ResultSet rs = stmt.executeQuery(sqlsearch);
			      ResultSet rsTitre;
				  rsTitre = stmt.executeQuery(sqltitre);
				  String titre = rsTitre.getString("titre");
				  o.getLabeltitle().setText((titre.length() > sizetext) ? titre.substring(0, sizetext)+".." : titre);
				  rsTitre.close();
			  }
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     // System.exit(0);
		    }
		
	}

	





	public ObjList (String titre, String type, String dateEmprunt, String dateFin, int width, JPanel Pane)
	{
		bd = type;
		id = titre;
		obj = new JPanel();
		obj.setLayout(new BoxLayout(obj,BoxLayout.X_AXIS));
		labeltype = new JLabel (type);
		labeltitle = new JLabel(titre);
		labeldateEmprunt = new JLabel (dateEmprunt.toString());
		labeldateFin = new JLabel(dateFin.toString());
		//NbE = new JLabel("" + nbExemplaire);
		buttonVP = new JButton("Voir Plus");
		setLabelTimer(new JLabel(Biblio.compteur(dateFin)));
		obj.add(Box.createHorizontalStrut(25));
		obj.add(labeltitle);
		obj.add(Box.createHorizontalStrut(width/7-30));
		obj.add(labeltype);
		obj.add(Box.createHorizontalStrut(width/7-(int)labeltype.getPreferredSize().getWidth()));
		obj.add(labeldateEmprunt);
		obj.add(Box.createHorizontalStrut(width/7-(int)labeldateEmprunt.getPreferredSize().getWidth()));
		obj.add(labeldateFin);
		obj.add(Box.createHorizontalStrut(width/7-(int)labeldateFin.getPreferredSize().getWidth()));
		obj.add(labelTimer);
		obj.add(Box.createHorizontalStrut(width/7));
		obj.add(buttonVP);
		obj.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Pane.add(obj);
		System.out.println("finobj");
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
		//labelimage = new JLabel("erreur");
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
		obj.add(bouton);
		return obj;
	}
	
	public JLabel getLabeltitle() {
		return labeltitle;
	}

	public void setLabeltitle(JLabel labeltitle) {
		this.labeltitle = labeltitle;
	}
	
	public JButton getButtonVP() {
		return buttonVP;
	}

	public void setButtonVP(JButton buttonVP) {
		this.buttonVP = buttonVP;
	}
	
	public JPanel getObj() {
		return obj;
	}
	public void setObj(JPanel obj) {
		this.obj = obj;
	}

	public JLabel getLabelTimer() {
		return labelTimer;
	}

	public void setLabelTimer(JLabel labelTimer) {
		this.labelTimer = labelTimer;
	}
	
	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	
	public SwingWorker<Object, Object> getSw() {
		return sw;
	}

	public void setSw(SwingWorker<Object, Object> sw) {
		this.sw = sw;
	}
	
	public String getBd() {
		return bd;
	}





	public void setBd(String bd) {
		this.bd = bd;
	}





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}




}

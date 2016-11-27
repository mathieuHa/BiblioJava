import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelBiblioUi implements ActionListener{
	
	
	private JPanel panel;
	private JPanel panelSearch;
	private JTextArea textAreaResult;
	private JScrollPane scroll;
	private JButton bouton;
	private JButton boutonOK;
	private JTextField jtf;
	private JLabel jlb;
	private JPanel panelResult;
	private JButton boutonReset;
	private JPanel panelhelp;
	private int nbResult;
	private SqlHelper sqlHelper;
	private ArrayList<JButton> lButton;
	private ArrayList<Integer> lInteger;
	private ArrayList<ObjList> lobj;
	private ObjList obj;
	private JFrame frame;
	private User user;
	private String bd;
	private String sqlsearch;
	private JPanel panelFicheVoir;
	private JLabel labelMesFiches;
	private Container panelFiches;
	private JPanel panelTitre;
	
	

	public PanelBiblioUi (ObjList obj, JFrame frame, SqlHelper sqlHelper, User user, String titre, String bd) {
		this.sqlHelper = sqlHelper;
		this.obj = obj;
		this.frame = frame;
		this.user = user;
		this.bd = bd;
		lButton = new ArrayList<JButton>();
	    lInteger = new ArrayList<Integer>();
	    lobj = new ArrayList<ObjList>();
		panel = new JPanel();
		panelSearch = new JPanel();
	    panel.setBackground(Color.pink);	
	    panel.setLayout(new BorderLayout());
	    
	    textAreaResult = new JTextArea();
		scroll = new JScrollPane(textAreaResult);
		
		bouton = new JButton(titre);
		bouton.addActionListener(this);
		boutonOK = new JButton("ok");
		boutonOK.addActionListener(this);
		
		jtf = new JTextField();
	    jtf.setPreferredSize(new Dimension(300, 30));
	    
	    jlb = new JLabel   ("Recherche   " + titre);
	    
	    if (user.getUsername().equals("ADMIN"))	{
	    	labelMesFiches = new JLabel ("Les Utilisateurs");
	    }
	    else{
	    	labelMesFiches = new JLabel ("Mes Fiches Emprunts");
	    }
	    labelMesFiches.setFont(new Font(labelMesFiches.getFont().getFontName(),Font.ROMAN_BASELINE,24));
	    if (bd.equals("FICHE")||bd.equals("LOGIN")) panelSearch.add(labelMesFiches);
	    else {
	    	panelSearch.add(jlb);
	    	panelSearch.add(jtf);
	    	panelSearch.add(boutonOK);
	    }
	    panel.add(panelSearch,BorderLayout.NORTH);
	    
	    panelResult = new JPanel(new GridLayout(6,1));
	    panelResult.setMaximumSize(new Dimension(600,300));
	    panelResult.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	    
	    boutonReset = new JButton("ici");
	    boutonReset.addActionListener(this);
	    
	    selectPanelHelper();
	    
	    
	    panelResult.add(panelhelp);
	    panel.add(panelResult,BorderLayout.CENTER);
	    panel.add(panelColor(Color.DARK_GRAY), BorderLayout.EAST);
	    panel.add(panelColor(Color.DARK_GRAY), BorderLayout.WEST);
	    panel.add(panelColor(Color.DARK_GRAY), BorderLayout.SOUTH);
	}
	
	public JButton getBouton() {
		return bouton;
	}

	public void setBouton(JButton bouton) {
		this.bouton = bouton;
	}

	public JButton getBoutonOK() {
		return boutonOK;
	}

	public void setBoutonOK(JButton boutonOK) {
		this.boutonOK = boutonOK;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JPanel getPanelSearch() {
		return panelSearch;
	}

	public void setPanelSearch(JPanel panelSearch) {
		this.panelSearch = panelSearch;
	}

	public JPanel getPanelResult() {
		return panelResult;
	}

	public void setPanelResult(JPanel panelResult) {
		this.panelResult = panelResult;
	}

	public JPanel getPanelhelp() {
		return panelhelp;
	}

	public void setPanelhelp(JPanel panelhelp) {
		this.panelhelp = panelhelp;
	}

	public JPanel panelColor (Color c) {
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, nbResult));
		pane.setBackground(c);
		pane.add(Box.createHorizontalStrut(30));
		pane.add(Box.createVerticalStrut(30));
		return pane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == boutonOK){
			nbResult = 0;
			clearSearch(panelResult,panelhelp);
			lButton.clear();
			lInteger.clear();
			//lobj.clear();
			sqlHelper.connect();
			sqlsearch = "SELECT * FROM " + bd + " where TITRE LIKE '" +"%"+ jtf.getText() +"%"+ "'";
			System.out.println(sqlsearch);
			sqlHelper.searchsql(sqlsearch);
			while ( sqlHelper.getNext() && nbResult <5) {
				selectBDDObject();
				lButton.add(obj.getButtonVP());
				lInteger.add(sqlHelper.getInt("id"));
				obj.getButtonVP().addActionListener(this);
				nbResult++;
			}
			sqlHelper.disconnect();
			panelResult.updateUI();
		}
		if (arg0.getSource() == bouton && bouton.getText().equals("Compte") && user.getUsername().equals("ADMIN")){
			nbResult = 0;
			clearSearch(panelResult,panelhelp);
			lButton.clear();
			lInteger.clear();
			sqlHelper.connect();
			sqlsearch = "SELECT * FROM LOGIN";
			System.out.println(sqlsearch);
			sqlHelper.searchsql(sqlsearch);
			while ( sqlHelper.getNext() && nbResult <5) {
				selectBDDObject();
				lButton.add(obj.getButtonVP());
				lInteger.add(sqlHelper.getInt("id"));
				obj.getButtonVP().addActionListener(this);
				nbResult++;
			}
			sqlHelper.disconnect();
			panelResult.updateUI();
		}
		else if (arg0.getSource() == bouton && bouton.getText().equals("Compte")) {
			nbResult = 0;
	        clearSearch(panelResult,panelhelp);
	        lButton.clear();
	        lInteger.clear();
	        String sqlsearch = "SELECT * FROM FICHE where userid="+user.getId();
	        sqlHelper.connect();
			sqlHelper.searchsql(sqlsearch);
	        for (ObjList o : lobj) o.setCancel(true); 
			lobj.clear();
		    while ( sqlHelper.getNext() && nbResult <5) {
		    	obj = new ObjList(sqlHelper.getString("docId"),sqlHelper.getString("typedoc"),sqlHelper.getString("dateemprunt"),sqlHelper.getString("datefin"),frame.getWidth()-100,panelResult);
		    	obj.Sstart(sqlHelper.getString("datefin"));
		    	lButton.add(obj.getButtonVP());
		    	lInteger.add(sqlHelper.getInt("id"));
		    	obj.getButtonVP().addActionListener(this);
		    	nbResult++;
		    	lobj.add(obj);
			}
		    sqlHelper.disconnect();
			panel.updateUI();
			obj.updateTitre(lobj);
		} else if (boutonReset == arg0.getSource()){
			clearSearch(panelResult,panelhelp);
			panelResult.updateUI();
		}
		else {
			if (!lButton.isEmpty()){
				int cpt = 0;
				for (JButton jb : lButton){
					cpt++;
					if (jb == arg0.getSource()) selectBDDFiche(cpt);
				}
			}
		}
	}
	
	private void selectBDDObject() {
		if (bd.equals("AUDIO")){
			obj = new ObjList(sqlHelper.getString("titre"),sqlHelper.getString("album"),sqlHelper.getString("auteur"),"",sqlHelper.getString("image"),sqlHelper.getInt("nbExemplaire"),panelResult,frame.getWidth()-100);
		} else if (bd.equals("VIDEO")) {
			obj = new ObjList(sqlHelper.getString("titre"),sqlHelper.getString("description"),sqlHelper.getString("annee"),sqlHelper.getString("note"),sqlHelper.getString("image"),sqlHelper.getInt("nbExemplaire"),panelResult,frame.getWidth()-100);
		} else if (bd.equals("LIVRE")) {
			obj = new ObjList(sqlHelper.getString("titre"),sqlHelper.getString("auteur"),sqlHelper.getString("annee"),sqlHelper.getString("category"),sqlHelper.getString("image"),sqlHelper.getInt("nbExemplaire"),panelResult,frame.getWidth()-100);
		} else if (bd.equals("FICHE")){
			obj = new ObjList(sqlHelper.getString("docId"),sqlHelper.getString("typedoc"),sqlHelper.getString("dateemprunt"),sqlHelper.getString("datefin"),frame.getWidth()-100,panelResult);
	    	obj.Sstart(sqlHelper.getString("datefin"));
	    	lobj.add(obj);
		} else if (bd.equals("LOGIN")){
			obj = new ObjList(sqlHelper.getString("username"),"",sqlHelper.getInt("credit"),frame.getWidth()-100,panelResult);
	    	//obj.Sstart(sqlHelper.getString("datefin"));
	    	//lobj.add(obj);
		}
		
	}
	
	private void selectBDDFiche (int cpt) {
		if (bd.equals("AUDIO")){
			new FicheMusique(lInteger.get(cpt-1).intValue(),user);
		} else if (bd.equals("VIDEO")) {
			new FicheVid(lInteger.get(cpt-1).intValue(),user);
		} else if (bd.equals("LIVRE")){
			new FicheLivre(lInteger.get(cpt-1).intValue(),user);
		} else if (bd.equals("FICHE")){
			new FicheEmprunt(lInteger.get(cpt-1).intValue(),user);
		} else if (bd.equals("LOGIN")){
			this.afficheEmprunt(user,lInteger.get(cpt-1).intValue());
		}
	}
	
	private void afficheEmprunt(User user, int id){
		JFrame fr = new JFrame ("Emprunts");
		fr.setSize(1000, 600);
		JPanel pan = new JPanel(new GridLayout(12,1));
		nbResult = 0;
		panelTitre = new JPanel();
        panelTitre.add(new JLabel("Liste des Emprunts"));
        panelTitre.setBackground(Color.LIGHT_GRAY);
        pan.add(panelTitre);
        pan.add(obj.helpFiche(fr.getWidth()+200, null));
        String sqlsearch = "SELECT * FROM FICHE where userid="+id;
        System.out.println(sqlsearch);
        sqlHelper.connect();
		sqlHelper.searchsql(sqlsearch);
		System.out.println("kk");
        for (ObjList o : lobj) o.setCancel(true); 
		//lobj.clear();
	    while ( sqlHelper.getNext() && nbResult <10) {
	    	obj = new ObjList(sqlHelper.getString("docId"),sqlHelper.getString("typedoc"),sqlHelper.getString("dateemprunt"),sqlHelper.getString("datefin"),fr.getWidth()+200,pan);
	    	obj.Sstart(sqlHelper.getString("datefin"));
	    	obj.getButtonVP().setVisible(false);
	    	//lButton.add(obj.getButtonVP());
	    	//lInteger.add(sqlHelper.getInt("id"));
	    	//obj.getButtonVP().addActionListener(this);
	    	nbResult++;
	    	System.out.println("kk");
	    	lobj.add(obj);
		}
	    sqlHelper.disconnect();
		pan.updateUI();
		fr.add(pan);
		obj.updateTitre(lobj);
		fr.setResizable(false);
		fr.setVisible(true);
		
		
	}
	
	private void selectPanelHelper(){
		if (bd.equals("AUDIO")){
			panelhelp = obj.helpMusique(frame.getWidth()-100,boutonReset);
		} else if (bd.equals("VIDEO")) {
			panelhelp = obj.helpVideo(frame.getWidth()-100,boutonReset);
		} else if (bd.equals("LIVRE")){
			panelhelp = obj.helpLivre(frame.getWidth()-100,boutonReset);
		} else if (bd.equals("FICHE")){
			panelhelp = obj.helpFiche(frame.getWidth()-100,boutonReset);
		} else if (bd.equals("LOGIN")){
			panelhelp = obj.helpAdmin(frame.getWidth()-100,boutonReset);
		} else panelhelp = obj.helpLivre(frame.getWidth()-100,boutonReset);
	}
	
	
	public void clearSearch (JPanel paneR, JPanel paneAdd){
		paneR.removeAll();
		paneR.add(paneAdd);
	}
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
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
	private ObjList obj;
	private JFrame frame;
	private User user;
	private String bd;
	
	

	public PanelBiblioUi (ObjList obj, JFrame frame, SqlHelper sqlHelper, User user, String titre, String bd) {
		this.sqlHelper = sqlHelper;
		this.obj = obj;
		this.frame = frame;
		this.user = user;
		this.bd = bd;
		lButton = new ArrayList<JButton>();
	    lInteger = new ArrayList<Integer>();
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
	    
	    panelSearch.add(jlb);
	    panelSearch.add(jtf);
	    panelSearch.add(boutonOK);
	    
	    panel.add(panelSearch,BorderLayout.NORTH);
	    
	    panelResult = new JPanel(new GridLayout(6,1));
	    panelResult.setMaximumSize(new Dimension(600,300));
	    panelResult.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	    
	    boutonReset = new JButton("ici");
	    boutonReset.addActionListener(this);
	    
	    panelhelp = obj.helpVideo(frame.getWidth()-100,boutonReset); 
	    
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
		// TODO Auto-generated method stub
		if (arg0.getSource() == boutonOK){
			nbResult = 0;
			clearSearch(panelResult,panelhelp);
			lButton.clear();
			lInteger.clear();
			sqlHelper.connect();
			String sqlsearch = "SELECT * FROM " + bd + " where TITRE LIKE '" +"%"+ jtf.getText() +"%"+ "'";
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
		else if (boutonReset == arg0.getSource()){
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
		} else {
			obj = new ObjList(sqlHelper.getString("titre"),sqlHelper.getString("auteur"),sqlHelper.getString("annee"),sqlHelper.getString("category"),sqlHelper.getString("image"),sqlHelper.getInt("nbExemplaire"),panelResult,frame.getWidth()-100);
		}
	}
	
	private void selectBDDFiche (int cpt) {
		if (bd.equals("AUDIO")){
			new FicheMusique(lInteger.get(cpt-1).intValue(),user);
		} else if (bd.equals("VIDEO")) {
			new FicheVid(lInteger.get(cpt-1).intValue(),user);
		} else {
			new FicheLivre(lInteger.get(cpt-1).intValue(),user);
		}
	}
	
	public void clearSearch (JPanel paneR, JPanel paneAdd){
		paneR.removeAll();
		paneR.add(paneAdd);
	}
}

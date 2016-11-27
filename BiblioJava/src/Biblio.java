import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Biblio implements ActionListener{
	private ArrayList<User> listUser;
	private Autentification autentification;
	private User user;
	private JFrame frame;
	private JPanel panel;
	private JPanel panelcompte;
	private JButton boutonCompte;
	private JPanel panelbouton;
	private CardLayout cl;
	private JPanel panelcompteAdd;
	private JButton boutonCompteAddMusique;
	private JButton boutonCompteAddLivre;
	private JButton boutonCompteAddVideo;
	private JPanel panelAddmin;
	private AddDocument add;
	private int nbResult;
	private ArrayList<ObjList> lobj;
	private ObjList obj;
	private JPanel panelFiches;
	private JLabel labelMesFiches;
	private JPanel panelFicheVoir;
	private ArrayList<JButton> lButtonC;
	private ArrayList<Integer> lIntegerC;
	private Calendar calendar = Calendar.getInstance();
	private SqlHelper sqlHelper = new SqlHelper();
	private PanelBiblioUi panelFilm;
	private PanelBiblioUi panelMusique;
	private PanelBiblioUi panelLivre;
	private JButton boutonBuy;
	private CarteBleu achat;
	
	public Biblio () {
		//sqlHelper.createTable();
		//sqlHelper.fillTables();
	    connection ();
	    lButtonC = new ArrayList<JButton>();
	    lIntegerC = new ArrayList<Integer>();
	    lobj = new ArrayList<ObjList>();
	}

	
	
	public void connection () {
		autentification = new Autentification();
	    SwingWorker sw = new SwingWorker(){
	        protected Object doInBackground() throws Exception {
	          while (autentification.getOk()==false){
	        	  // attente de l'authentification
	        	  try {
	                  Thread.sleep(10);
	                } catch (InterruptedException e) {
	                  e.printStackTrace();
	                } 
	          }
	          return null;
	       }
	        public void done(){            
	          if(SwingUtilities.isEventDispatchThread()){
	        	  user = autentification.getUser();
	        	  user.getDB();
	        	  System.out.println(user.toString());
	        	  afficheLibrairy();
	          }
	        }         
	      };
	      sw.execute();
	}
	
	private void setupFrameUI () {
		frame = new JFrame ("LibraryGUI");
		frame.setMinimumSize(new Dimension(1200,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLayout(new BorderLayout());
		cl = new CardLayout();
		panelbouton = new JPanel();
		panelbouton.setBackground(Color.gray);	
	    obj = new ObjList();
	    panel = new JPanel();
	    panel.setLayout(cl);
	}
	
	private void setupCompteUI() {
		panelcompte = new JPanel();
		panelcompteAdd = new JPanel();
	    panelcompte.setBackground(Color.blue);	
	    panelcompte.setLayout(new BorderLayout());
	    
	    boutonCompteAddMusique = new JButton("Add Musique");
	    boutonCompteAddMusique.addActionListener(this);
	    boutonCompteAddLivre = new JButton("Add Livre");
	    boutonCompteAddLivre.addActionListener(this);
	    boutonCompteAddVideo = new JButton("Add Video");
	    boutonCompteAddVideo.addActionListener(this);
	    
	    panelAddmin = new JPanel ();
		panelAddmin.setBackground(Color.darkGray);	
	    
	    panelAddmin.setLayout(new BoxLayout(panelAddmin,BoxLayout.X_AXIS));
	    panelAddmin.add(Box.createHorizontalGlue());
	    panelAddmin.add(boutonCompteAddLivre);
	    panelAddmin.add(Box.createHorizontalGlue());
	    panelAddmin.add(boutonCompteAddMusique);
	    panelAddmin.add(Box.createHorizontalGlue());
	    panelAddmin.add(boutonCompteAddVideo);
	    panelAddmin.add(Box.createHorizontalGlue());
	    
	    panelFiches = new JPanel (new GridLayout(10,1));
	    
	    panelFicheVoir = new JPanel();
	    labelMesFiches = new JLabel ("Mes Fiches");
	    labelMesFiches.setFont(new Font(labelMesFiches.getFont().getFontName(),Font.ROMAN_BASELINE,20));
	    panelFicheVoir.add(labelMesFiches);
	    panelFiches.add(panelFicheVoir);    
	    
	    boutonCompte = new JButton("Compte");
	    boutonCompte.addActionListener(this);
	    
	    boutonBuy = new JButton ("Acheter des Crédit");
	    boutonBuy.addActionListener(this);
	    
	    panelcompte.add(panelFiches,BorderLayout.CENTER);
	    panelcompte.add(panelAddmin,BorderLayout.SOUTH);
	    
	}
	
	private void finishFrameUI(){
		panel.add(panelcompte, "compte");
	    panel.add(panelLivre.getPanel(), "livre");
	    panel.add(panelMusique.getPanel(), "musique");
	    panel.add(panelFilm.getPanel(), "video");
	    
	    panelbouton.add(boutonCompte);
	    panelbouton.add(panelFilm.getBouton());
	    panelbouton.add(panelLivre.getBouton());
	    panelbouton.add(panelMusique.getBouton());
	    panelbouton.add(boutonBuy);
	    panelFilm.getBouton().addActionListener(this);
	    panelLivre.getBouton().addActionListener(this);
	    panelMusique.getBouton().addActionListener(this);
	    
	    
	    frame.add(panelbouton, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	 
	public void afficheLibrairy (){
		setupFrameUI();
		panelFilm = new PanelBiblioUi(obj,frame,sqlHelper,user,"Video","VIDEO");
		panelLivre = new PanelBiblioUi(obj,frame,sqlHelper,user,"Livre","LIVRE");
		panelMusique = new PanelBiblioUi(obj,frame,sqlHelper,user,"Musique","AUDIO");
		setupCompteUI();
		finishFrameUI();
		new UpdateDataBase();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == boutonCompte){
			cl.show(panel, "compte");
	        nbResult = 0;
	        clearSearch(panelFiches,panelFicheVoir);
	        lButtonC.clear();
	        lIntegerC.clear();
	        String sqlsearch = "SELECT * FROM FICHE where userid="+user.getId();
	        sqlHelper.connect();
			sqlHelper.searchsql(sqlsearch);
	        for (ObjList o : lobj) o.setCancel(true); 
			lobj.clear();
		    while ( sqlHelper.getNext() && nbResult <8) {
		    	obj = new ObjList(sqlHelper.getString("docId"),sqlHelper.getString("typedoc"),sqlHelper.getString("dateemprunt"),sqlHelper.getString("datefin"),frame.getWidth()-100,panelFiches);
		    	obj.Sstart(sqlHelper.getString("datefin"));
		    	lButtonC.add(obj.getButtonVP());
		    	lIntegerC.add(sqlHelper.getInt("id"));
		    	obj.getButtonVP().addActionListener(this);
		    	nbResult++;
		    	lobj.add(obj);
			}
		    sqlHelper.disconnect();
			panelFiches.updateUI();
			obj.updateTitre(lobj);
		}
		else if (arg0.getSource() == panelMusique.getBouton()){
			cl.show(panel, "musique");
		}
		else if (arg0.getSource() == panelLivre.getBouton()){
			cl.show(panel, "livre");
		}
		else if (arg0.getSource() == panelFilm.getBouton()){
			cl.show(panel, "video");
			System.out.println("dans filme");
		}
		else if (arg0.getSource() == boutonCompteAddMusique){
			add = new AddMusique ();
		}
		else if (arg0.getSource() == boutonCompteAddVideo){
			add = new AddVideo ();
		}
		else if (arg0.getSource() == boutonCompteAddLivre){
			add = new AddLivre ();
		}
		else if (arg0.getSource() == boutonBuy){
			achat = new CarteBleu(user,sqlHelper);
		}
	}
	
	public void clearSearch (JPanel paneR, JPanel paneAdd){
		paneR.removeAll();
		paneR.add(paneAdd);
	}
	
	public ArrayList<User> getListUser() {
		return listUser;
	}

	public void setListUser(ArrayList<User> listUser) {
		this.listUser = listUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

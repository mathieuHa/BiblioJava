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
	private Connection connexion;
	private Statement stmt;
	private User user;
	private JFrame frame;
	private JPanel panel;
	private JPanel panellivre;
	private JPanel panelmusique;
	private JPanel panelfilms;
	private JPanel panelcompte;
	private JButton boutonCompte;
	private JButton boutonMusique;
	private JButton boutonVideo;
	private JButton boutonLivre;
	private JPanel panelbouton;
	private JTextField jtf;
	private CardLayout cl;
	private JTextField jtfLivre;
	private JTextField jtfMusique;
	private JTextField jtfFilm;
	private JLabel jlbLivre;
	private JLabel jlbFilm;
	private JLabel jlbMusique;
	private JButton boutonOKVideo;
	private JButton boutonOKLivre;
	private JButton boutonOKMusique;
	private JPanel panelfilmsSearch;
	private JPanel panelcompteSearch;
	private JPanel panelmusiqueSearch;
	private JPanel panellivreSearch;
	private JPanel panelcompteAdd;
	private JButton boutonCompteAdd;
	private JButton boutonCompteAddMusique;
	private JButton boutonCompteAddLivre;
	private JButton boutonCompteAddVideo;
	private JPanel panelAddmin;
	private AddDocument add;
	private JTextArea textAreaResult;
	private int nbResult;
	private ArrayList<JButton> lButtonV;
	private ArrayList<Integer> lIntegerV;
	private ArrayList<JButton> lButtonM;
	private ArrayList<Integer> lIntegerM;
	private ArrayList<JButton> lButtonL;
	private ArrayList<Integer> lIntegerL;
	private ArrayList<ObjList> lobj;
	private JTextArea textAreaResultMusique;
	private JScrollPane scrollMusique;
	private JTextArea textAreaResultAudio;
	private JScrollPane scrollAudio;
	private JScrollPane scrollLivre;
	private JTextArea textAreaResultLivre;
	private JTextArea textAreaResultFilm;
	private JScrollPane scrollFilm;
	private JPanel panelfilmB;
	private ObjList obj;
	private JLabel title;
	private JLabel auteur;
	private JLabel duree;
	private JLabel NbE;
	private JButton buttonVP;
	private JPanel panelfilmResult;
	private JPanel panelfilmhelp;
	private JPanel panelmusiqueResult;
	private JPanel panellivreResult;
	private JPanel panellivrehelp;
	private JPanel panelmusiquehelp;
	private JButton boutonmusiqueReset;
	private JButton boutonfilmReset;
	private JButton boutonlivrehelp;
	private JButton boutonlivreReset;
	private JPanel panelFiches;
	private JLabel labelMesFiches;
	private JPanel panelFicheVoir;
	private ArrayList<JButton> lButtonC;
	private ArrayList<Integer> lIntegerC;
	private static SimpleDateFormat formater;
	private Calendar calendar = Calendar.getInstance();
	private SqlHelper sqlHelper = new SqlHelper();
	private ResultSet rs;
	
	public Biblio () {
		//create_table();
		fill_tables();
	    connection ();
	    lButtonV = new ArrayList<JButton>();
	    lIntegerV = new ArrayList<Integer>();
	    lButtonL = new ArrayList<JButton>();
	    lIntegerL = new ArrayList<Integer>();
	    lButtonM = new ArrayList<JButton>();
	    lIntegerM = new ArrayList<Integer>();
	    lButtonC = new ArrayList<JButton>();
	    lIntegerC = new ArrayList<Integer>();
	    lobj = new ArrayList<ObjList>();
	    
		System.out.println("OK");
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
	      //On lance le SwingWorker
	      sw.execute();
	}
	
	public void fill_tables (){
		try {
		      Class.forName("org.sqlite.JDBC");
		      connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database successfully creat table");
		      stmt = connexion.createStatement();
		      String sqlaudiorequest = "UPDATE AUDIO SET NBEXEMPLAIRE = 2";
		      String sqlvideorequest = "UPDATE VIDEO SET NBEXEMPLAIRE = 2";
		      String sqllivrerequest = "UPDATE LIVRE SET NBEXEMPLAIRE = 2";
		      String sqlaudiorequest1 = "UPDATE AUDIO SET TARIF = 1";
		      String sqlvideorequest1 = "UPDATE VIDEO SET TARIF = 3";
		      String sqllivrerequest1 = "UPDATE LIVRE SET TARIF = 2";
		      
		      stmt.executeUpdate(sqlvideorequest);
		      stmt.executeUpdate(sqlaudiorequest);
		      stmt.executeUpdate(sqllivrerequest);
		      stmt.executeUpdate(sqlvideorequest1);
		      stmt.executeUpdate(sqlaudiorequest1);
		      stmt.executeUpdate(sqllivrerequest1);
		      stmt.close();
		      connexion.close();
		      
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
	}
	
	public void create_table () {
		connexion = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
	      System.out.println("Opened database successfully creat table");
	      stmt = connexion.createStatement();
	      String sqllogin = "CREATE TABLE LOGIN " +
                  "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                  " USERNAME           TEXT    NOT NULL, " + 
                  " CREDIT             INT     NOT NULL, " + 
                  " PASSWORD           TEXT    NOT NULL)";
	      String sqlvideo = "CREATE TABLE VIDEO " +
                  "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                  " TITRE              TEXT    NOT NULL, " +
                  " ANNEE              TEXT    NOT NULL, " +
                  " IMAGE              TEXT    NOT NULL, " +
                  " DESCRIPTION        TEXT    NOT NULL, " +
                  " LANGUAGE           TEXT    NOT NULL, " +
                  " NBEMPRUNT          INT     NOT NULL, " +
                  " NBEXEMPLAIRE       INT     NOT NULL, " +
                  " NOTE               TEXT    NOT NULL, " +
                  " TARIF		       INT     NOT NULL) ";
	      
	      String sqlaudio = "CREATE TABLE AUDIO " +
                  "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                  " TITRE              TEXT    NOT NULL, " +
                  " AUTEUR             TEXT    NOT NULL, " +
                  " ALBUM              TEXT    NOT NULL, " +
                  " IMAGE              TEXT    NOT NULL, " +
                  " NBEMPRUNT          INT     NOT NULL, " +
                  " NBEXEMPLAIRE       INT     NOT NULL, " +
                  " TARIF		       INT     NOT NULL) ";
	      
	      String sqlfiche = "CREATE TABLE FICHE " +
                  "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                  " USERID             INT     NOT NULL, " + 
                  " DOCID              INT     NOT NULL, " +
                  " TYPEDOC            TEXT     NOT NULL, " +
                  " DATEEMPRUNT        TEXT    NOT NULL, " +
                  " DATEFIN            TEXT    NOT NULL)  ";
          
	      
	      String sqllivre = "CREATE TABLE LIVRE " +
                  "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                  " TITRE              TEXT    NOT NULL, " +
                  " AUTEUR             TEXT    NOT NULL, " +
                  " ANNEE              TEXT    NOT NULL, " +
                  " CATEGORY           TEXT    NOT NULL, " +
                  " IMAGE              TEXT    NOT NULL, " +
                  " NBEMPRUNT          INT     NOT NULL, " +
                  " NBEXEMPLAIRE       INT     NOT NULL, " +
                  " DESCRIPTION        TEXT    NOT NULL, " +
                  " TARIF		       INT     NOT NULL) ";
	      
	      stmt.executeUpdate(sqllogin);
	      stmt.executeUpdate(sqlfiche);
	      stmt.executeUpdate(sqlvideo);
	      stmt.executeUpdate(sqlaudio);
	      stmt.executeUpdate(sqllivre);
	      stmt.close();
	      connexion.close();
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    user = new User();
	    
		System.out.println("OK");
	}
	
	public void afficheLibrairy (){
		frame = new JFrame ("LibraryGUI");
		frame.setMinimumSize(new Dimension(1200,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLayout(new BorderLayout());
		
		cl = new CardLayout();
		
		panelbouton = new JPanel();
		panelbouton.setBackground(Color.gray);	
		panelAddmin = new JPanel ();
		panelAddmin.setBackground(Color.darkGray);	
		
		textAreaResultMusique = new JTextArea();
		scrollMusique = new JScrollPane(textAreaResultMusique);
		textAreaResultFilm = new JTextArea();
		scrollFilm = new JScrollPane(textAreaResultFilm);
		textAreaResultLivre = new JTextArea();
		scrollLivre = new JScrollPane(textAreaResultLivre);
		
		panelfilms = new JPanel();
		panelfilmsSearch = new JPanel();
	    panelfilms.setBackground(Color.pink);	
	    panelfilms.setLayout(new BorderLayout());
		panelcompte = new JPanel();
		panelcompteAdd = new JPanel();
	    panelcompte.setBackground(Color.blue);	
	    panelcompte.setLayout(new BorderLayout());
	    panelmusique = new JPanel();
	    panelmusiqueSearch = new JPanel();
	    panelfilmsSearch.setBackground(Color.lightGray);
	    panelmusique.setBackground(Color.red);	
	    panelmusique.setLayout(new BorderLayout());
	    panellivre = new JPanel();
	    panellivreSearch = new JPanel();
	    panellivre.setBackground(Color.green);
	    panellivre.setLayout(new BorderLayout());
	    
	    boutonCompteAddMusique = new JButton("Add Musique");
	    boutonCompteAddMusique.addActionListener(this);
	    boutonCompteAddLivre = new JButton("Add Livre");
	    boutonCompteAddLivre.addActionListener(this);
	    boutonCompteAddVideo = new JButton("Add Video");
	    boutonCompteAddVideo.addActionListener(this);
	    
	    panelAddmin.setLayout(new BoxLayout(panelAddmin,BoxLayout.X_AXIS));
	    panelAddmin.add(Box.createHorizontalGlue());
	    panelAddmin.add(boutonCompteAddLivre);
	    panelAddmin.add(Box.createHorizontalGlue());
	    panelAddmin.add(boutonCompteAddMusique);
	    panelAddmin.add(Box.createHorizontalGlue());
	    panelAddmin.add(boutonCompteAddVideo);
	    panelAddmin.add(Box.createHorizontalGlue());
	    
	    panelFiches = new JPanel (new GridLayout(10,1));
	    labelMesFiches = new JLabel ("Mes Fiches");
	    labelMesFiches.setFont(new Font(labelMesFiches.getFont().getFontName(),Font.ROMAN_BASELINE,20));
	    panelFicheVoir = new JPanel();
	    panelFicheVoir.add(labelMesFiches);
	    
	    
	    
	    panelFiches.add(panelFicheVoir);
	    
	    
	    boutonCompte = new JButton("Compte");
	    boutonVideo = new JButton("Video");
	    boutonMusique = new JButton("Musique");
	    boutonLivre = new JButton("Livre");
	    
	    boutonOKVideo = new JButton("ok");
	    boutonOKLivre = new JButton("ok");
	    boutonOKMusique = new JButton("ok");
	    
	    boutonCompte.addActionListener(this);
	    boutonMusique.addActionListener(this);
	    boutonLivre.addActionListener(this);
	    boutonVideo.addActionListener(this);
	    
	    boutonOKVideo.addActionListener(this);
	    boutonOKMusique.addActionListener(this);
	    boutonOKLivre.addActionListener(this);
	    
	    jtfLivre = new JTextField();
	    jtfMusique = new JTextField();
	    jtfFilm = new JTextField();
	    jtfLivre.setPreferredSize(new Dimension(300, 30));
	    jtfFilm.setPreferredSize(new Dimension(300, 30));
	    jtfMusique.setPreferredSize(new Dimension(300, 30));
	    
	    jlbLivre = new JLabel  ("Recherche  Livre");
	    jlbFilm = new JLabel   ("Recherche   Film");
	    jlbMusique = new JLabel("Recherche Musique");
	    
	    panelmusiqueSearch.add(jlbMusique);
	    panellivreSearch.add(jlbLivre);
	    panelfilmsSearch.add(jlbFilm);
	    
	    panelmusiqueSearch.add(jtfMusique);
	    panellivreSearch.add(jtfLivre);
	    panelfilmsSearch.add(jtfFilm);
	    
	    panelmusiqueSearch.add(boutonOKMusique);
	    panellivreSearch.add(boutonOKLivre);
	    panelfilmsSearch.add(boutonOKVideo);
	    
	    panelmusique.add(panelmusiqueSearch,BorderLayout.NORTH);
	    panellivre.add(panellivreSearch,BorderLayout.NORTH);
	    panelfilms.add(panelfilmsSearch,BorderLayout.NORTH);
	    panelcompte.add(panelAddmin,BorderLayout.SOUTH);
	    
	    panelfilmResult = new JPanel(new GridLayout(6,1));
	    panelfilmResult.setMaximumSize(new Dimension(600,300));
	    panelfilmResult.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	    
	    panelmusiqueResult = new JPanel(new GridLayout(6,1));
	    panelmusiqueResult.setMaximumSize(new Dimension(600,300));
	    panelmusiqueResult.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	    
	    panellivreResult = new JPanel(new GridLayout(6,1));
	    panellivreResult.setMaximumSize(new Dimension(600,300));
	    panellivreResult.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	    
	    obj = new ObjList();
	    boutonfilmReset = new JButton("ici");
	    boutonfilmReset.addActionListener(this);
	    boutonlivreReset = new JButton("ici");
	    boutonlivreReset.addActionListener(this);
	    boutonmusiqueReset = new JButton("ici");
	    boutonmusiqueReset.addActionListener(this);
	    panelfilmhelp = obj.helpVideo(frame.getWidth()-100,boutonfilmReset); 
	    panelmusiquehelp = obj.helpMusique(frame.getWidth()-100,boutonmusiqueReset); 
	    panellivrehelp = obj.helpLivre(frame.getWidth()-100,boutonlivreReset); 
	    
	    
	    panelfilmResult.add(panelfilmhelp);
	    panelfilms.add(panelfilmResult,BorderLayout.CENTER);
	    panelfilms.add(panelColor(Color.DARK_GRAY), BorderLayout.EAST);
	    panelfilms.add(panelColor(Color.DARK_GRAY), BorderLayout.WEST);
	    panelfilms.add(panelColor(Color.DARK_GRAY), BorderLayout.SOUTH);
	    
	    panelmusiqueResult.add(panelmusiquehelp);
	    panelmusique.add(panelmusiqueResult,BorderLayout.CENTER);
	    panelmusique.add(panelColor(Color.DARK_GRAY), BorderLayout.EAST);
	    panelmusique.add(panelColor(Color.DARK_GRAY), BorderLayout.WEST);
	    panelmusique.add(panelColor(Color.DARK_GRAY), BorderLayout.SOUTH);
	    
	    panellivreResult.add(panellivrehelp);
	    panellivre.add(panellivreResult,BorderLayout.CENTER);
	    panellivre.add(panelColor(Color.DARK_GRAY), BorderLayout.EAST);
	    panellivre.add(panelColor(Color.DARK_GRAY), BorderLayout.WEST);
	    panellivre.add(panelColor(Color.DARK_GRAY), BorderLayout.SOUTH);
	    
	    panelcompte.add(panelFiches,BorderLayout.CENTER);
	    
	    //panelfilms.add(scrollFilm,BorderLayout.CENTER);
	    //panelmusique.add(scrollMusique);
	    //panellivre.add(scrollLivre);
	    
	    panelbouton.add(boutonCompte);
	    panelbouton.add(boutonMusique);
	    panelbouton.add(boutonLivre);
	    panelbouton.add(boutonVideo);
	    
	    panel = new JPanel();
	    panel.setLayout(cl);
	    
	    panel.add(panelcompte, "compte");
	    panel.add(panellivre, "livre");
	    panel.add(panelmusique, "musique");
	    panel.add(panelfilms, "video");
	    
	    frame.add(panelbouton, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);

		UpdateDataBase updb = new UpdateDataBase();
		
		//AIzaSyAT7eTEjPXHy8XGbk5-_thfHG638n_fcYY
	}
	
	public static String compteur (String end){
		formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date datenow = new Date();
		Date dateEnd = null;
		try {
			dateEnd = formater.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time =  dateEnd.getTime() - datenow.getTime();
		long day  = 0;
		long hour = 0;
		long min  = 0;
		long sec  = 0;
		datenow.setTime(time);
		time/=1000;
		day = time/(24*60*60);
		hour = time / (60*60) - day*24;
		min = time / 60 - hour*60 - day*24*60;
		sec = time - day*24*60*60 - hour*60*60 - min*60;
		return day + " jours " + hour + ":" + min + ":" + sec;
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
		    	System.out.println(compteur (sqlHelper.getString("datefin")));
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
		else if (arg0.getSource() == boutonMusique){
			cl.show(panel, "musique");
		}
		else if (arg0.getSource() == boutonLivre){
			cl.show(panel, "livre");
		}
		else if (arg0.getSource() == boutonVideo){
			cl.show(panel, "video");
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
		else if (arg0.getSource() == boutonOKVideo){
		    nbResult = 0;
		    clearSearch(panelfilmResult,panelfilmhelp);
		    lButtonV.clear();
		    lIntegerV.clear();
		    sqlHelper.connect();
		    String sqlsearch = "SELECT * FROM VIDEO where TITRE LIKE '" +"%"+ jtfFilm.getText() +"%"+ "'";
			sqlHelper.searchsql(sqlsearch);
			while ( sqlHelper.getNext() && nbResult <5) {
		    	obj = new ObjList(sqlHelper.getString("titre"),sqlHelper.getString("description"),sqlHelper.getString("annee"),sqlHelper.getString("note"),sqlHelper.getString("image"),sqlHelper.getInt("nbExemplaire"),panelfilmResult,frame.getWidth()-100);
		    	lButtonV.add(obj.getButtonVP());
		    	lIntegerV.add(sqlHelper.getInt("id"));
		    	obj.getButtonVP().addActionListener(this);
		    	nbResult++;
			}
			sqlHelper.disconnect();
			panelfilmResult.updateUI();
			}
		else if (arg0.getSource() == boutonOKMusique){
	        nbResult = 0;
	        clearSearch(panelmusiqueResult,panelmusiquehelp);
	        lButtonM.clear();
	        lIntegerM.clear();
	        sqlHelper.connect();
	        String sqlsearch = "SELECT * FROM AUDIO where TITRE LIKE '" + "%" +jtfMusique.getText() + "%" + "'";
	        sqlHelper.searchsql(sqlsearch);
        	while ( sqlHelper.getNext() && nbResult<5) {
	    	    obj = new ObjList(sqlHelper.getString("titre"),sqlHelper.getString("album"),sqlHelper.getString("auteur"),"",sqlHelper.getString("image"),sqlHelper.getInt("nbExemplaire"),panelmusiqueResult,frame.getWidth()-100);
	    	    lButtonM.add(obj.getButtonVP());
		        lIntegerM.add(sqlHelper.getInt("id"));
		        System.out.println(lButtonM.size());
		        obj.getButtonVP().addActionListener(this);
	    	    nbResult++;
		    }
        	sqlHelper.disconnect();
	        panelmusiqueResult.updateUI();
		}
		else if (arg0.getSource() == boutonOKLivre){
			
	        nbResult = 0;
	        clearSearch(panellivreResult,panellivrehelp);
	        lButtonL.clear();
	        lIntegerL.clear();
	        sqlHelper.connect();
	        String sqlsearch = "SELECT * FROM LIVRE where TITRE LIKE '" + "%" + jtfLivre.getText() + "%"  + "'";
	        sqlHelper.searchsql(sqlsearch);
	        while ( sqlHelper.getNext() && nbResult <5) {
	        	obj = new ObjList(sqlHelper.getString("titre"),sqlHelper.getString("auteur"),sqlHelper.getString("annee"),sqlHelper.getString("category"),sqlHelper.getString("image"),sqlHelper.getInt("nbExemplaire"),panellivreResult,frame.getWidth()-100);
	        	lButtonL.add(obj.getButtonVP());
		    	lIntegerL.add(sqlHelper.getInt("id"));
		    	System.out.println(lButtonL.size());
		    	obj.getButtonVP().addActionListener(this);
		    	nbResult++;
	        }
	        sqlHelper.disconnect();
	        panellivreResult.updateUI();
		}
		else if (boutonfilmReset == arg0.getSource()){
			clearSearch(panelfilmResult,panelfilmhelp);
			panelfilmResult.updateUI();
		}
		else if (boutonmusiqueReset == arg0.getSource()){
			clearSearch(panelmusiqueResult,panelmusiquehelp);
			panelmusiqueResult.updateUI();
		}
		else if (boutonlivreReset == arg0.getSource()){
			clearSearch(panellivreResult,panellivrehelp);
			panellivreResult.updateUI();
		}
		else {
			if (!lButtonV.isEmpty()){
				int cpt = 0;
				for (JButton jb : lButtonV){
					cpt++;
					if (jb == arg0.getSource()) new FicheVid(lIntegerV.get(cpt-1).intValue(),user);
				}
			}
			if (!lButtonL.isEmpty()){
				int cpt = 0;
				for (JButton jb1 : lButtonL){
					cpt++;
					if (jb1 == arg0.getSource()) new FicheLivre(lIntegerL.get(cpt-1).intValue(),user);
				}
			}	
			if (!lButtonM.isEmpty()){
				int cpt = 0;
				for (JButton jb2 : lButtonM){
					cpt++;
					if (jb2 == arg0.getSource()) new FicheMusique(lIntegerM.get(cpt-1).intValue(),user);
				}
			}	
		}
		
		
	}
	
	public void clearSearch (JPanel paneR, JPanel paneAdd){
		paneR.removeAll();
		paneR.add(paneAdd);
	}
	
	public String returnEntry(String str, ResultSet rs){
		try {
			String  s = rs.getString(str);
			return str.toUpperCase() + " = " + s;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return null;
	}
	
	public JPanel panelColor (Color c) {
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, nbResult));
		pane.setBackground(c);
		pane.add(Box.createHorizontalStrut(30));
		pane.add(Box.createVerticalStrut(30));
		return pane;
	}
}

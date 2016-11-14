import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private ArrayList<JButton> lButton;
	private ArrayList<Integer> lInteger;
	private JTextArea textAreaResultMusique;
	private JScrollPane scrollMusique;
	private JTextArea textAreaResultAudio;
	private JScrollPane scrollAudio;
	private JScrollPane scrollLivre;
	private JTextArea textAreaResultLivre;
	private JTextArea textAreaResultFilm;
	private JScrollPane scrollFilm;
	
	public Biblio () {
		
	    connection ();
	    lButton = new ArrayList<JButton>();
	    lInteger = new ArrayList<Integer>();
	    create_table();
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
	                  Thread.sleep(100);
	                } catch (InterruptedException e) {
	                  e.printStackTrace();
	                } 
	          }
	          return null;
	       }
	        public void done(){            
	          if(SwingUtilities.isEventDispatchThread()){
	        	  user = autentification.getUser();
	        	  System.out.println(user.toString());
	        	  afficheLibrairy();
	          }
	        }         
	      };
	      //On lance le SwingWorker
	      sw.execute();
	}
	
	public void create_table () {
		connexion = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
	      System.out.println("Opened database successfully");
	      stmt = connexion.createStatement();
	      String sqllogin = "CREATE TABLE LOGIN " +
                  "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                  " USERNAME           TEXT    NOT NULL, " + 
                  " PASSWORD           TEXT    NOT NULL)";
	      String sqlvideo = "CREATE TABLE VIDEO " +
                  "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                  " CODE               TEXT    NOT NULL, " + 
                  " TITRE              TEXT    NOT NULL, " +
                  " AUTEUR             TEXT    NOT NULL, " +
                  " ANNEE              INT     NOT NULL, " +
                  " EMPRUNTABLE        TINYINT NOT NULL, " +
                  " EMPRUNTE           TINYINT NOT NULL, " +
                  " NBEMPRUNT          INT     NOT NULL, " +
                  " NBEXEMPLAIRE       INT     NOT NULL, " +
                  " MENTIONLEGALE      TEXT    NOT NULL, " +
                  " DUREEFILM          INT     NOT NULL, " +
                  " DUREEEMPRUNT       INT     NOT NULL, " +
                  " TARIF		       INT     NOT NULL) ";
	      
	      String sqlaudio = "CREATE TABLE AUDIO " +
                  "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                  " CODE               TEXT    NOT NULL, " + 
                  " TITRE              TEXT    NOT NULL, " +
                  " AUTEUR             TEXT    NOT NULL, " +
                  " ANNEE              INT     NOT NULL, " +
                  " EMPRUNTABLE        TINYINT NOT NULL, " +
                  " EMPRUNTE           TINYINT NOT NULL, " +
                  " NBEMPRUNT          INT     NOT NULL, " +
                  " NBEXEMPLAIRE       INT     NOT NULL, " +
                  " DUREEEMPRUNT       INT     NOT NULL, " +
                  " TARIF		       INT     NOT NULL) ";
	      
	      String sqllivre = "CREATE TABLE LIVRE " +
                  "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                  " CODE               TEXT    NOT NULL, " + 
                  " TITRE              TEXT    NOT NULL, " +
                  " AUTEUR             TEXT    NOT NULL, " +
                  " ANNEE              INT     NOT NULL, " +
                  " EMPRUNTABLE        TINYINT NOT NULL, " +
                  " EMPRUNTE           TINYINT NOT NULL, " +
                  " NBEMPRUNT          INT     NOT NULL, " +
                  " NBEXEMPLAIRE       INT     NOT NULL, " +
                  " NBPAGE             INT     NOT NULL, " +
                  " DUREEEMPRUNT       INT     NOT NULL, " +
                  " TARIF		       INT     NOT NULL) ";
	      
	      //stmt.executeUpdate(sqllogin);
	      //stmt.executeUpdate(sqlvideo);
	      //stmt.executeUpdate(sqlaudio);
	      //stmt.executeUpdate(sqllivre);
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
		frame.setMinimumSize(new Dimension(800,480));
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
	    
	    panelfilms.add(scrollFilm);
	    panelmusique.add(scrollMusique);
	    panellivre.add(scrollLivre);
	    
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
	}
	
	JLabel addImage (String path){
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("img/"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			return picLabel;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == boutonCompte){
			cl.show(panel, "compte");
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
			System.out.println("add musique");
			add = new AddMusique ();
		}
		else if (arg0.getSource() == boutonCompteAddVideo){
			System.out.println("add video");
			add = new AddVideo ();
		}
		else if (arg0.getSource() == boutonCompteAddLivre){
			System.out.println("add livre");
			add = new AddLivre ();
		}
		else if (arg0.getSource() == boutonOKVideo){
			System.out.println("recherche video");
			String sqlsearch = "SELECT * FROM VIDEO where TITRE LIKE '" +"%"+ jtfFilm.getText() +"%"+ "'";
			textAreaResultFilm.setText("");
			try {
			      Class.forName("org.sqlite.JDBC");
			      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
			      System.out.println("Opened database Video successfully");
			      Statement stmt = connexion.createStatement();
			      ResultSet rs = stmt.executeQuery(sqlsearch);
			      nbResult = 0;
			      while ( rs.next() ) {
			    	  textAreaResultFilm.append(returnEntry("code",rs) + " ");
			    	  textAreaResultFilm.append(returnEntry("titre",rs)+ " ");
			    	  textAreaResultFilm.append(returnEntry("auteur",rs)+ " ");
			    	  textAreaResultFilm.append(returnEntry("annee",rs)+ " \n");
			    	  nbResult++;
				      }
			      
			      stmt.close();
			      connexion.close();
			    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
			}
	else if (arg0.getSource() == boutonOKMusique){
		System.out.println("recherche musique" + jtfMusique.getText());
		String sqlsearch = "SELECT * FROM AUDIO where TITRE LIKE '" + "%" +jtfMusique.getText() + "%" + "'";
		textAreaResultMusique.setText("");
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database Musique successfully");
		      Statement stmt = connexion.createStatement();
		      ResultSet rs = stmt.executeQuery(sqlsearch);
		      nbResult = 0;
		      lButton.clear();
		      lInteger.clear();
		      System.out.println("OK111");
		      while ( rs.next() ) {
		    	  JButton jtemp = new JButton();
		    	  jtemp.addActionListener(this);
		    	  System.out.println("OK113");
		    	  textAreaResultMusique.append(returnEntry("code",rs) + " ");
		    	  textAreaResultMusique.append(returnEntry("titre",rs)+ " ");
		    	  textAreaResultMusique.append(returnEntry("auteur",rs)+ " ");
		    	  textAreaResultMusique.append(returnEntry("annee",rs)+ " \n");
		    	  System.out.println("OK114");
		    	  lInteger.add(new Integer(Integer.parseInt(rs.getString("ID"))));
		    	  System.out.println("OK115");
		    	  lButton.add(jtemp);
		    	  panelmusique.add(jtemp,BorderLayout.EAST);
		    	  nbResult++;
			      }
		      System.out.println("OK111");
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		}
	else if (arg0.getSource() == boutonOKLivre){
		System.out.println("recherche livre");
		String sqlsearch = "SELECT * FROM LIVRE where TITRE='" + jtfLivre.getText() + "'";
		textAreaResultLivre.setText("");
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database Livre successfully");
		      Statement stmt = connexion.createStatement();
		      ResultSet rs = stmt.executeQuery(sqlsearch);
		      nbResult = 0;
		      while ( rs.next() ) {
		    	  textAreaResultLivre.append(returnEntry("code",rs) + " ");
		    	  textAreaResultLivre.append(returnEntry("titre",rs)+ " ");
		    	  textAreaResultLivre.append(returnEntry("auteur",rs)+ " ");
		    	  textAreaResultLivre.append(returnEntry("annee",rs)+ " \n");
		    	  nbResult++;
			      }
		  
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		}
		else if (!lButton.isEmpty()){
			for (JButton jb : lButton){
				if (jb == arg0.getSource()){
					System.out.println("OJ");
				}
			}
		}
		
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
}

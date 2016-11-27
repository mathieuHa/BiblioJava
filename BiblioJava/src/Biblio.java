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
	private JPanel panelbouton;
	private CardLayout cl;
	private JButton boutonCompteAddMusique;
	private JButton boutonCompteAddLivre;
	private JButton boutonCompteAddVideo;
	private AddDocument add;
	private ArrayList<ObjList> lobj;
	private ObjList obj;
	private ArrayList<JButton> lButtonC;
	private ArrayList<Integer> lIntegerC;
	private Calendar calendar = Calendar.getInstance();
	private SqlHelper sqlHelper = new SqlHelper();
	private PanelBiblioUi panelFilm;
	private PanelBiblioUi panelMusique;
	private PanelBiblioUi panelLivre;
	private JButton boutonBuy;
	private CarteBleu achat;
	private PanelBiblioUi panelUser;
	private PanelBiblioUi panelAdmin;
	private JPanel paneUserInfo;
	private JLabel credit;
	private JLabel userName;
	
	public Biblio () {
		//sqlHelper.createTable();
		sqlHelper.fillTables();
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
		frame.setResizable(false);
		frame.pack();
		frame.setLayout(new BorderLayout());
		cl = new CardLayout();
		panelbouton = new JPanel();
		panelbouton.setBackground(Color.LIGHT_GRAY);	
	    obj = new ObjList();
	    panel = new JPanel();
	    panel.setLayout(cl);
	    
	    panelFilm = new PanelBiblioUi(obj,frame,sqlHelper,user,"Video","VIDEO");
		panelLivre = new PanelBiblioUi(obj,frame,sqlHelper,user,"Livre","LIVRE");
		panelMusique = new PanelBiblioUi(obj,frame,sqlHelper,user,"Musique","AUDIO");
		panelUser = new PanelBiblioUi(obj,frame,sqlHelper,user,"Compte","FICHE");
		panelAdmin = new PanelBiblioUi(obj,frame,sqlHelper,user,"Compte","LOGIN");
	}
	
	private void finishFrameUI(){
		if (user.getUsername().equals("ADMIN"))		panel.add(panelAdmin.getPanel(),"compte");
		else 										panel.add(panelUser.getPanel(), "compte");
	    panel.add(panelLivre.getPanel(), "livre");
	    panel.add(panelMusique.getPanel(), "musique");
	    panel.add(panelFilm.getPanel(), "video");
	    
	    if (user.getUsername().equals("ADMIN"))	    panelbouton.add(panelAdmin.getBouton());
	    else										panelbouton.add(panelUser.getBouton());
	    panelbouton.add(panelFilm.getBouton());
	    panelbouton.add(panelLivre.getBouton());
	    panelbouton.add(panelMusique.getBouton());
	    boutonBuy = new JButton ("Acheter des Crédits");
	    boutonBuy.addActionListener(this);
	    panelbouton.add(boutonBuy);
	    if (user.getUsername().equals("ADMIN")) 	panelAdmin.getBouton().addActionListener(this);
	    else										panelUser.getBouton().addActionListener(this);
	    panelFilm.getBouton().addActionListener(this);
	    panelLivre.getBouton().addActionListener(this);
	    panelMusique.getBouton().addActionListener(this);
	    
	    if (user.getUsername().equals("ADMIN"))		panelAdmin.getBouton().setFont(new Font(panelAdmin.getBouton().getFont().getFontName(),Font.ROMAN_BASELINE,18));
	    panelUser.getBouton().setFont(new Font(panelUser.getBouton().getFont().getFontName(),Font.ROMAN_BASELINE,18));
	    panelFilm.getBouton().setFont(new Font(panelFilm.getBouton().getFont().getFontName(),Font.ROMAN_BASELINE,18));
	    panelLivre.getBouton().setFont(new Font(panelLivre.getBouton().getFont().getFontName(),Font.ROMAN_BASELINE,18));
	    panelMusique.getBouton().setFont(new Font(panelMusique.getBouton().getFont().getFontName(),Font.ROMAN_BASELINE,18));
	    boutonBuy.setFont(new Font(boutonBuy.getFont().getFontName(),Font.ROMAN_BASELINE,18));
	    
	    //panelUser.getBouton().getText().
	    
	    paneUserInfo = new JPanel();
	    paneUserInfo.setBackground(Color.LIGHT_GRAY); 
	    paneUserInfo.setLayout(new BorderLayout());
	    credit = new JLabel("Crédits : " + Integer.toString(user.getCredit())+"   ");
	    userName = new JLabel("   Nom d'utilisateur : " + user.getUsername().toUpperCase());
	    userName.setFont(new Font(userName.getFont().getFontName(),Font.ROMAN_BASELINE,24));
	    paneUserInfo.add(userName, BorderLayout.WEST);
	    paneUserInfo.add(panelbouton, BorderLayout.CENTER);
	    credit = new JLabel("Crédits : " + Integer.toString(user.getCredit())+"   ");
	    credit.setFont(new Font(credit.getFont().getFontName(),Font.ROMAN_BASELINE,24));
	    paneUserInfo.add(credit, BorderLayout.EAST);
	    frame.add(paneUserInfo, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	 
	public void afficheLibrairy (){
		setupFrameUI();
		
		//setupCompteUI();
		finishFrameUI();
		new UpdateDataBase();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (user.getUsername().equals("ADMIN"))	{
			if (arg0.getSource() == panelAdmin.getBouton()){
				cl.show(panel, "compte");
			}
		}
		else {
			if (arg0.getSource() == panelUser.getBouton()){
				cl.show(panel, "compte");
			} 
		}
		if (arg0.getSource() == panelMusique.getBouton()){
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
		sqlHelper.connect();
		String sqls = "SELECT * FROM " + "LOGIN" + " where ID = '" + user.getId() + "'";
		sqlHelper.searchsql(sqls);
		credit.setText("Crédits : " + sqlHelper.getString("credit")+"   ");
		sqlHelper.disconnect();
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

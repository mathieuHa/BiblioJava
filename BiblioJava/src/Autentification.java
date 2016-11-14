import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Autentification implements ActionListener{
	private JFrame frame;
	private JPanel panelbouton;
	private JTextField fieldlogin;
	private JTextField fieldpassword;
	private JLabel textlogin;
	private JLabel textpassword;
	private JButton boutonconnexion;
	private JButton boutoninscription;
	private JPanel panelpaswd;
	private JPanel panelname;
	private Statement stmt = null;
	private User user;
	private Boolean ok = false;
	
	public Autentification () {
		frame = new JFrame ("autentification");
		frame.setMinimumSize(new Dimension(640,480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLayout(new GridLayout(3, 1));
		
		new JOptionPane();
		user = new User();
	    
		panelname = new JPanel();
		panelpaswd = new JPanel();
		panelbouton = new JPanel();
		panelbouton.setLayout(new BoxLayout(panelbouton,BoxLayout.X_AXIS));
		panelname.setLayout(new BoxLayout(panelname,BoxLayout.X_AXIS));
		panelpaswd.setLayout(new BoxLayout(panelpaswd,BoxLayout.X_AXIS));
		textlogin = new JLabel    ("username     :");
		textpassword = new JLabel ("mot de passe :");
		fieldlogin = new JTextField();
		fieldpassword = new JTextField();
		boutonconnexion = new JButton("connexion");
		boutoninscription = new JButton("inscription");
		
		boutonconnexion.addActionListener(this);
		boutoninscription.addActionListener(this);
		
		fieldlogin.setPreferredSize(new Dimension(150, 25));
		fieldlogin.setMaximumSize(fieldlogin.getPreferredSize());
		fieldpassword.setPreferredSize(new Dimension(150, 25));
		fieldpassword.setMaximumSize(fieldpassword.getPreferredSize());
		
		panelname.add(Box.createHorizontalGlue());
		panelname.add(textlogin);
		panelname.add(Box.createHorizontalStrut(50));
		panelname.add(fieldlogin);
		panelname.add(Box.createHorizontalGlue());
		
		panelpaswd.add(Box.createHorizontalGlue());
		panelpaswd.add(textpassword);
		panelpaswd.add(Box.createHorizontalStrut(50));
		panelpaswd.add(fieldpassword);
		panelpaswd.add(Box.createHorizontalGlue());
		
		panelbouton.add(Box.createHorizontalGlue());	
		panelbouton.add(boutonconnexion);
		panelbouton.add(Box.createHorizontalStrut(30));
		panelbouton.add(boutoninscription);
		panelbouton.add(Box.createHorizontalGlue());	
		
		frame.add(panelname);
		frame.add(panelpaswd);
		frame.add(panelbouton);
		frame.setVisible(true);
		afficheBDDVideo();
		afficheBDDAudio();
		afficheBDDLivre();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == boutonconnexion){
			if (fieldlogin.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "username empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else if (fieldpassword.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "password empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println("nouvelle connexion");
				String sqltest = "SELECT COUNT(*) AS sum FROM LOGIN where USERNAME='"+fieldlogin.getText()+"'";
				String sqlquery = "SELECT * FROM LOGIN where USERNAME='"+fieldlogin.getText()+"'";
				try {
				      Class.forName("org.sqlite.JDBC");
				      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
				      System.out.println("Opened database successfully");
				      stmt = connexion.createStatement();
				      ResultSet rs = stmt.executeQuery(sqltest);
				      //System.out.println(rs.getInt("sum"));
				      if (rs.getInt("sum")==0){
				    	  JOptionPane.showMessageDialog(null, "username does not exist", "Attention", JOptionPane.WARNING_MESSAGE);
				      } else {
					      rs = stmt.executeQuery(sqlquery);
					      if (rs.next()) {
						         int id = rs.getInt("id");
						         String  username = rs.getString("username");
						         String  password = rs.getString("password");
						         if (username.equals(fieldlogin.getText()) && password.equals(fieldpassword.getText())){
						        	 System.out.println("connexion");
						        	 user.setLogin(fieldlogin.getText());
						        	 user.setPassword(fieldpassword.getText());
						        	 ok = true;
						        	 frame.dispose();
						         }
						         else{
						        	 JOptionPane.showMessageDialog(null, "wrong password", "Attention", JOptionPane.WARNING_MESSAGE);
						         }
					      }		
				      }
				      stmt.close();
				      connexion.close();
				    } catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				
				}
			}
			
		}
		else if (arg0.getSource() == boutoninscription){
			if (fieldlogin.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "username empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else if (fieldpassword.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "password empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println("nouvelle inscription");
				String sqltest = "SELECT COUNT(*) AS sum FROM LOGIN where USERNAME='"+fieldlogin.getText()+"'";
				String sqlinsert = "INSERT INTO LOGIN (USERNAME,PASSWORD) " +
		                   "VALUES ('"+fieldlogin.getText()+"','"+fieldpassword.getText()+"');"; 
				try {
				      Class.forName("org.sqlite.JDBC");
				      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
				      System.out.println("Opened database successfully");
				      stmt = connexion.createStatement();
				      ResultSet rs = stmt.executeQuery(sqltest);
				      //System.out.println(rs.getInt("sum"));
				      if (rs.getInt("sum")!=0){
				    	  System.out.println("existe deja");
				    	  JOptionPane.showMessageDialog(null, "username already exist", "Attention", JOptionPane.WARNING_MESSAGE);
				      } else {
				    	  stmt.executeUpdate(sqlinsert);
				    	  System.out.println("ajout");
				      }
				      stmt.close();
				      connexion.close();
				    } catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				    }
			}
		}
	}
	
	public void afficheBDDUser () {
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database successfully");
		      stmt = connexion.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM LOGIN;" );
		      while ( rs.next() ) {
		         int id = rs.getInt("id");
		         String  username = rs.getString("username");
		         String  password = rs.getString("password");
		         System.out.println( "ID = " + id );
		         System.out.println( "USERNAME = " + username );
		         System.out.println( "PASSWORD = " + password );
		         System.out.println();
		      }
		      rs.close();
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	public void afficheBDDAudio () {
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database successfully");
		      stmt = connexion.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM AUDIO;" );
		      while ( rs.next() ) {
		         int id = rs.getInt("id");
		         afficheEntry("code",rs);
		         afficheEntry("titre",rs);
		         afficheEntry("auteur",rs);
		         afficheEntry("annee",rs);
		         afficheEntry("emprunte",rs);
		         afficheEntry("empruntable",rs);
		         afficheEntry("nbemprunt",rs);
		         afficheEntry("nbexemplaire",rs);
		         afficheEntry("dureeemprunt",rs);
		         afficheEntry("tarif",rs);
		         System.out.println();
		      }
		      rs.close();
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	public void afficheBDDVideo () {
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database successfully");
		      stmt = connexion.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM VIDEO;" );
		      while ( rs.next() ) {
		         int id = rs.getInt("id");
		         afficheEntry("code",rs);
		         afficheEntry("titre",rs);
		         afficheEntry("auteur",rs);
		         afficheEntry("annee",rs);
		         afficheEntry("emprunte",rs);
		         afficheEntry("empruntable",rs);
		         afficheEntry("nbemprunt",rs);
		         afficheEntry("nbexemplaire",rs);
		         afficheEntry("dureeemprunt",rs);
		         afficheEntry("tarif",rs);
		         afficheEntry("dureefilm",rs);
		         afficheEntry("mentionlegale",rs);
		         System.out.println();
		      }
		      rs.close();
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	public void afficheBDDLivre () {
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database successfully");
		      stmt = connexion.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM LIVRE;" );
		      while ( rs.next() ) {
		         int id = rs.getInt("id");
		         afficheEntry("code",rs);
		         afficheEntry("titre",rs);
		         afficheEntry("auteur",rs);
		         afficheEntry("annee",rs);
		         afficheEntry("emprunte",rs);
		         afficheEntry("empruntable",rs);
		         afficheEntry("nbemprunt",rs);
		         afficheEntry("nbexemplaire",rs);
		         afficheEntry("dureeemprunt",rs);
		         afficheEntry("tarif",rs);
		         afficheEntry("nbpage",rs);
		         System.out.println();
		      }
		      rs.close();
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	public void afficheEntry(String str, ResultSet rs){
		try {
			String  s = rs.getString(str);
			System.out.println(str.toUpperCase() + " = " + s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}
}

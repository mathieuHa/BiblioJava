
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Autentification implements ActionListener {
	private JFrame frame;
	private JPanel panelbouton;
	private JTextField fieldlogin;
	private JTextField fieldpassword;
	private JLabel textlogin;
	private JLabel textpassword;
	private JButton boutonconnexion;
	private JButton boutoninscription;
	private JPanel pane;
	private ImagePanel panelname;
	private Statement stmt = null;
	private User user;
	private Boolean ok = false;
	private JPanel paneltxt;
	private Border line;
	private JLabel lTitre;
	

	public Autentification () {
		new JOptionPane();
		user = new User();
		
		line = BorderFactory.createLineBorder(Color.WHITE, 3);
		frame = new JFrame ("Autentification");
		frame.setMinimumSize(new Dimension(640,350));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		pane = new JPanel();
		pane.setBackground(Color.GRAY);
		lTitre = new JLabel("Bienvenu dans notre Bibliotheque");
		pane.add(lTitre);
		lTitre.setFont(new Font(lTitre.getFont().getFontName(),lTitre.getFont().getStyle(),20));
		frame.add(pane,BorderLayout.NORTH);
		
		textlogin = new JLabel    ("username       :");
		textlogin.setOpaque(true);
		textlogin.setBackground(Color.WHITE);
		textlogin.setBorder(line);
		textpassword = new JLabel ("mot de passe :");
		textpassword.setOpaque(true);
		textpassword.setBackground(Color.WHITE);
		textpassword.setBorder(line);
		fieldlogin = new JTextField();
		fieldpassword = new JTextField();
		
		boutonconnexion = new JButton("connexion");
		boutonconnexion.addActionListener(this);
		boutoninscription = new JButton("inscription");
		boutoninscription.addActionListener(this);

		panelname = new ImagePanel();
		panelname.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(25,10,10,10);

		c.gridx = 0; c.gridy = 0;
		panelname.add(textlogin,c);
		c.gridx = 1; 
		panelname.add(fieldlogin,c);
		c.gridx = 0; c.gridy++;
		panelname.add(textpassword,c);
		c.gridx = 1;
		panelname.add(fieldpassword,c);
		fieldlogin.setPreferredSize(new Dimension(150, 25));
		fieldlogin.setMaximumSize(fieldlogin.getPreferredSize());
		fieldpassword.setPreferredSize(new Dimension(150, 25));
		fieldpassword.setMaximumSize(fieldpassword.getPreferredSize());
		c.gridx = 0; c.gridy++;
		panelname.add(boutoninscription,c);
		c.gridx = 1; 
		panelname.add(boutonconnexion,c);
		
		this.user = user;
		frame.add(panelname);
		//afficheBDDVideo();
		//afficheBDDAudio();
		//afficheBDDLivre();


		
		frame.setVisible(true);
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
						         String  pass = fieldpassword.getText();
									try {
							            // Create MessageDigest instance for MD5
							            MessageDigest md = MessageDigest.getInstance("MD5");
							            //Add password bytes to digest
							            md.update(pass.getBytes());
							            //Get the hash's bytes 
							            byte[] bytes = md.digest();
							            //This bytes[] has bytes in decimal format;
							            //Convert it to hexadecimal format
							            StringBuilder sb = new StringBuilder();
							            for(int i=0; i< bytes.length ;i++)
							            {
							                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
							            }
							            //Get complete hashed password in hex format
							            pass = sb.toString();
							        } 
							        catch (NoSuchAlgorithmException e) 
							        {
							            e.printStackTrace();
							        }
						         if (username.equals(fieldlogin.getText()) && password.equals(pass)){
						        	 System.out.println("connexion");
						        	 user.setLogin(fieldlogin.getText());
						        	 user.setId(id);
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
				String Password = null;
				try {
		            // Create MessageDigest instance for MD5
		            MessageDigest md = MessageDigest.getInstance("MD5");
		            //Add password bytes to digest
		            md.update(fieldpassword.getText().getBytes());
		            //Get the hash's bytes 
		            byte[] bytes = md.digest();
		            //This bytes[] has bytes in decimal format;
		            //Convert it to hexadecimal format
		            StringBuilder sb = new StringBuilder();
		            for(int i=0; i< bytes.length ;i++)
		            {
		                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		            }
		            //Get complete hashed password in hex format
		            Password = sb.toString();
		        } 
		        catch (NoSuchAlgorithmException e) 
		        {
		            e.printStackTrace();
		        }
				String sqltest = "SELECT COUNT(*) AS sum FROM LOGIN where USERNAME='"+fieldlogin.getText()+"'";
				String sqlinsert = "INSERT INTO LOGIN (USERNAME,CREDIT,PASSWORD) " +
		                   "VALUES ('"+fieldlogin.getText()+"','25','"+Password+"');"; 
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
		         //afficheEntry("auteur",rs);
		         afficheEntry("annee",rs);
		         afficheEntry("emprunte",rs);
		         afficheEntry("empruntable",rs);
		         afficheEntry("nbemprunt",rs);
		         afficheEntry("nbexemplaire",rs);
		         afficheEntry("dureeemprunt",rs);
		         afficheEntry("tarif",rs);
		         //afficheEntry("dureefilm",rs);
		         //afficheEntry("mentionlegale",rs);
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
		         //afficheEntry("nbpage",rs);
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


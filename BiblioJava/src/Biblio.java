import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Biblio {
	private ArrayList<User> listUser;
	private Autentification autentification;
	private Connection connexion;
	private Statement stmt;
	private User user;
	
	public Biblio () {
		connexion = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
	      System.out.println("Opened database successfully");
	      stmt = connexion.createStatement();
	      String sql = "CREATE TABLE LOGIN " +
                  "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                  " USERNAME           TEXT    NOT NULL, " + 
                  " PASSWORD           TEXT    NOT NULL)";
	      
	      //stmt.executeUpdate(sql);
	      stmt.close();
	      connexion.close();
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    user = new User();
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
	          }
	        }         
	      };
	      //On lance le SwingWorker
	      sw.execute();
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
}

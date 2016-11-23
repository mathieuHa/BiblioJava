import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	private String username;
	private int credit;
	
	public User(String login) {
		super();
		this.username = login;
	}
	
	public void getDB(){
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database successfully login credit");
		      Statement stmt = connexion.createStatement();
		      String sqluser = "SELECT * FROM LOGIN where USERNAME='"+this.getUsername()+"'";
		      System.out.println(sqluser);
		      ResultSet rs = stmt.executeQuery(sqluser);
		      this.setCredit(rs.getInt("credit"));
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", credit=" + credit + "]";
	}

	public User(String username, int credit) {
		this.username = username;
		this.credit = credit;
	}

	public User() {
		super();
	}
	
	public User(User user) {
		super();
		this.username = user.getUsername();
	}

	
	public String getUsername() {
		return username;
	}
	public void setLogin(String login) {
		this.username = login;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
}

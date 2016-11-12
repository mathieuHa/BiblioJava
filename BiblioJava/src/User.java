
public class User {

	private String username;
	private String password;
	
	public User(String login, String password) {
		super();
		this.username = login;
		this.password = password;
	}
	
	public User() {
		super();
	}
	
	public User(User user) {
		super();
		this.username = user.getUsername();
		this.password = user.getPassword();
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}
	public void setLogin(String login) {
		this.username = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

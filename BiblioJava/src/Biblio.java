import java.util.ArrayList;

public class Biblio {
	private ArrayList<User> listUser;
	private Autentification autentification;
	
	public Biblio () {
		autentification = new Autentification();
	}

	public ArrayList<User> getListUser() {
		return listUser;
	}

	public void setListUser(ArrayList<User> listUser) {
		this.listUser = listUser;
	}
}

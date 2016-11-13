import java.awt.GridLayout;

import javax.swing.JPanel;

public class AddMusique extends AddDocument {

	private JPanel panel;

	public AddMusique() {
		super("Musique");
		panel = new JPanel(new GridLayout(4,1));
		super.ajouterValiderB(panel);
		boutonvalider.addActionListener(this);
		this.getContentPane().add(panel);
		// TODO Auto-generated constructor stub
	}

}

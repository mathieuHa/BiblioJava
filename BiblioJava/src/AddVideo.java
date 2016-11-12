import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AddVideo extends AddDocument {

	private JPanel panel;
	private JButton boutontest;

	public AddVideo() {
		super("Video");
		// TODO Auto-generated constructor stub
		panel = new JPanel();
		boutontest = new JButton("test");
		this.getContentPane().add(boutontest,BorderLayout.NORTH);
	}
	
}

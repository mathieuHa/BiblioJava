import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AddDocument extends JFrame {
	private JPanel panel;

	public AddDocument(String title) {
		this.setTitle("Add " + title);
		this.setMinimumSize(new Dimension(400,600));
		panel = new JPanel ();
		panel.setLayout(new GridLayout(2,1));
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	
}

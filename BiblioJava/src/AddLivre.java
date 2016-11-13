import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddLivre extends AddDocument implements ActionListener{

	private JPanel panel;
	private JPanel panelAddNbPages;
	private JLabel labelNbPages;
	private JTextField fieldNbPages;

	public AddLivre() {
		super("Livre");
		
		panel = new JPanel(new GridLayout(4,1));
		panelAddNbPages = new JPanel();
		
		panelAddNbPages.setLayout(new BoxLayout(panelAddNbPages,BoxLayout.X_AXIS));
		
		labelNbPages = new JLabel ("NbPages");
		
		fieldNbPages = new JTextField();
		
		fieldNbPages.setPreferredSize(new Dimension(200, 25));
		fieldNbPages.setMaximumSize(new Dimension(200, 25));
		
		panelAddNbPages.add(Box.createHorizontalStrut(50));
		panelAddNbPages.add(labelNbPages);
		panelAddNbPages.add(Box.createHorizontalGlue());
		panelAddNbPages.add(fieldNbPages);
		panelAddNbPages.add(Box.createHorizontalStrut(50));
		
		panel.add(panelAddNbPages);
		boutonvalider.addActionListener(this);
		super.ajouterValiderB(panel);
		this.getContentPane().add(panel,BorderLayout.CENTER);
		
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent arg0) { // formatter integer only autorisé a faire
		super.actionPerformed(arg0);
		if (!docIsOK){
			
		}
		else if (fieldNbPages.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "NbPages empty", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		else {
			
		}
	}
	
}

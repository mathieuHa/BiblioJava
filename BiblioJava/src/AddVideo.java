import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddVideo extends AddDocument {

	private JPanel panel;
	private JButton boutontest;
	private JPanel panelAddDuree;
	private JPanel panelAddMentionLegale;
	private JLabel labelDuree;
	private JLabel labelMentionLegale;
	private JTextField fieldDuree;
	private JTextField fieldMentionLegale;

	public AddVideo() {
		super("Video");
		// TODO Auto-generated constructor stub
		panel = new JPanel(new GridLayout(4,1));
		panelAddDuree = new JPanel();
		panelAddMentionLegale = new JPanel();
		
		panelAddDuree.setLayout(new BoxLayout(panelAddDuree,BoxLayout.X_AXIS));
		panelAddMentionLegale.setLayout(new BoxLayout(panelAddMentionLegale,BoxLayout.X_AXIS));
		
		labelDuree = new JLabel ("Duree");
		labelMentionLegale = new JLabel ("Mentions Legales");
		
		fieldDuree = new JTextField();
		fieldMentionLegale = new JTextField();
		
		fieldDuree.setPreferredSize(new Dimension(200, 25));
		fieldDuree.setMaximumSize(new Dimension(200, 25));
		fieldMentionLegale.setPreferredSize(new Dimension(200, 25));
		fieldMentionLegale.setMaximumSize(new Dimension(200, 25));
		
		panelAddDuree.add(Box.createHorizontalStrut(50));
		panelAddDuree.add(labelDuree);
		panelAddDuree.add(Box.createHorizontalGlue());
		panelAddDuree.add(fieldDuree);
		panelAddDuree.add(Box.createHorizontalStrut(50));
		
		panelAddMentionLegale.add(Box.createHorizontalStrut(50));
		panelAddMentionLegale.add(labelMentionLegale);
		panelAddMentionLegale.add(Box.createHorizontalGlue());
		panelAddMentionLegale.add(fieldMentionLegale);
		panelAddMentionLegale.add(Box.createHorizontalStrut(50));
		
		panel.add(panelAddDuree);
		panel.add(panelAddMentionLegale);
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
	}
	
}

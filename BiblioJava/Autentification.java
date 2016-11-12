import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Autentification implements ActionListener{
	private JFrame frame;
	private JPanel paneltxt;
	private JPanel panelbouton;
	private JTextField fieldlogin;
	private JTextField fieldpassword;
	private JLabel textlogin;
	private JLabel textpassword;
	private JButton boutonconnexion;
	private JButton boutoninscription;
	private JPanel panelpaswd;
	private JPanel panelname;
	
	public Autentification () {
		frame = new JFrame ("autentification");
		frame.setMinimumSize(new Dimension(640,480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLayout(new GridLayout(3, 1));
	    
		panelname = new JPanel();
		panelpaswd = new JPanel();
		panelbouton = new JPanel();
		panelbouton.setLayout(new BoxLayout(panelbouton,BoxLayout.X_AXIS));
		panelname.setLayout(new BoxLayout(panelname,BoxLayout.X_AXIS));
		panelpaswd.setLayout(new BoxLayout(panelpaswd,BoxLayout.X_AXIS));
		textlogin = new JLabel    ("username     :");
		textpassword = new JLabel ("mot de passe :");
		fieldlogin = new JTextField();
		fieldpassword = new JTextField();
		boutonconnexion = new JButton("connexion");
		boutoninscription = new JButton("inscription");
		
		boutonconnexion.addActionListener(this);
		boutoninscription.addActionListener(this);
		
		fieldlogin.setPreferredSize(new Dimension(150, 25));
		fieldlogin.setMaximumSize(fieldlogin.getPreferredSize());
		fieldpassword.setPreferredSize(new Dimension(150, 25));
		fieldpassword.setMaximumSize(fieldpassword.getPreferredSize());
		
		panelname.add(Box.createHorizontalGlue());
		panelname.add(textlogin);
		panelname.add(Box.createHorizontalStrut(50));
		panelname.add(fieldlogin);
		panelname.add(Box.createHorizontalGlue());
		
		panelpaswd.add(Box.createHorizontalGlue());
		panelpaswd.add(textpassword);
		panelpaswd.add(Box.createHorizontalStrut(50));
		panelpaswd.add(fieldpassword);
		panelpaswd.add(Box.createHorizontalGlue());
		
		panelbouton.add(Box.createHorizontalGlue());	
		panelbouton.add(boutonconnexion);
		panelbouton.add(Box.createHorizontalStrut(30));
		panelbouton.add(boutoninscription);
		panelbouton.add(Box.createHorizontalGlue());	
		
		frame.add(panelname);
		frame.add(panelpaswd);
		frame.add(panelbouton);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

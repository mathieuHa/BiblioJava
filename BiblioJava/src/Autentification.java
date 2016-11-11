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
	private JTextArea textlogin;
	private JTextArea textpassword;
	private JButton boutonconnexion;
	private JButton boutoninscription;
	
	public Autentification () {
		frame = new JFrame ("autentification");
		frame.setMinimumSize(new Dimension(640,480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLayout(new GridLayout(2, 1));
	    
		paneltxt = new JPanel();
		panelbouton = new JPanel();
		paneltxt.setLayout(new BoxLayout(paneltxt,BoxLayout.Y_AXIS));
		panelbouton.setLayout(new BoxLayout(panelbouton,BoxLayout.X_AXIS));
		textlogin = new JTextArea ();
		textpassword = new JTextArea ();
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
		
		paneltxt.add(Box.createVerticalStrut(50));
		paneltxt.add(fieldlogin);
		paneltxt.add(Box.createVerticalStrut(100));
		paneltxt.add(fieldpassword);
		
		panelbouton.add(Box.createHorizontalGlue());	
		panelbouton.add(boutonconnexion);
		panelbouton.add(Box.createHorizontalStrut(30));
		panelbouton.add(boutoninscription);
		panelbouton.add(Box.createHorizontalGlue());	
		
		frame.add(paneltxt);
		frame.add(panelbouton);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

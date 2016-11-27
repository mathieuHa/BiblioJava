
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class CarteBleu implements ActionListener{
	private JFrame frame = new JFrame("Add Credit");
	private JPanel pane = new JPanel();
	private JPanel pTitre = new JPanel();
	private JPanel pButton = new JPanel();

	private JLabel lCredit = new JLabel ("Combien voulez vous de credit");
	private JLabel lSomme = new JLabel ("Prix");
	private JLabel lPrix = new JLabel("0€");
	private JLabel lTitre = new JLabel ("Payement sécurisé de votre commande");
	private JLabel lCarte = new JLabel ("N° carte");
	private JLabel lType = new JLabel ("Type carte");
	private JLabel lSecure = new JLabel ("Code de sécurité ");
	private JLabel lMasterCard = new JLabel ();
	private JLabel lVisa = new JLabel ();
	private JLabel lExpiration = new JLabel("Date d'expiration");
	private JFormattedTextField fCarte = new JFormattedTextField();
	private JTextField fSecure = new JTextField();
	private GridBagConstraints c = new GridBagConstraints();
	private JButton bOk = new JButton("Valider");
	private JButton bAnnuler = new JButton("Annuler");
	private JCheckBox cb1 = new JCheckBox();
	private JCheckBox cb2 = new JCheckBox();

	private Calendar d = Calendar.getInstance();
	private int s = d.get(Calendar.YEAR);
	private String[] annees = {Integer.toString(s),Integer.toString(s+1), Integer.toString(s+2),
			Integer.toString(s+3),Integer.toString(s+4),Integer.toString(s+5),Integer.toString(s+6),
			Integer.toString(s+7),Integer.toString(s+8),Integer.toString(s+9)};
	private JComboBox jYears = new JComboBox(annees);
	private String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private JComboBox jMonth = new JComboBox(month);
	private String[] credit = {"0","1","2","5","10","15","20"};
	private JComboBox jCredit = new JComboBox(credit);

	private String prix, mois, annee;
	private int add;
	private User user;
	private SqlHelper sqlhelper;

	public CarteBleu(User user, SqlHelper sqlHelper)
	{
		this.user = user;
		this.sqlhelper = sqlHelper;
		jYears.addItemListener(new ItemState());
		jMonth.addItemListener(new ItemState());
		jCredit.addItemListener(new ItemState());
		jYears.addActionListener(this);
		jMonth.addActionListener(this);
		jCredit.addActionListener(this);
		bOk.addActionListener(this);
		bAnnuler.addActionListener(this);
		cb1.addActionListener(this);
		cb2.addActionListener(this);

		Icon image = new ImageIcon( "mastercard.jpg" );
		lMasterCard.setIcon( image );
		image = new ImageIcon( "visa.jpg" );
		lVisa.setIcon( image );
		try{
			MaskFormatter tel2 = new MaskFormatter("####-####-####-####");
			fCarte = new JFormattedTextField(tel2);
		} catch(ParseException e){e.printStackTrace();}



		frame.setMinimumSize(new Dimension(450,450));
		frame.setLayout(new BorderLayout());
		c.insets = new Insets(10, 10, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;

		pTitre.setBackground(Color.GRAY);
		pTitre.add(lTitre);
		lTitre.setFont(new Font(lTitre.getFont().getFontName(),lTitre.getFont().getStyle(),20));
		frame.add(pTitre,BorderLayout.NORTH);

		pButton.add(bOk);
		pButton.add(bAnnuler);
		frame.add(pButton,BorderLayout.SOUTH);

		fCarte.setPreferredSize(new Dimension(200, 25));
		fSecure.setPreferredSize(new Dimension(200, 25));
		pane.setLayout(new GridBagLayout());

		c.gridx = 0; c.gridy = 0; c.gridwidth = 3; 
		pane.add(lCredit, c);
		c.gridx = 3; c.gridwidth = 2;
		pane.add(jCredit, c); 
		c.gridx = 0; c.gridy++; c.gridwidth = 1; 
		pane.add(lSomme, c);
		c.gridx = 3; c.gridwidth = 4;
		pane.add(lPrix, c); 
		c.gridx = 0; c.gridy++; c.gridwidth = 1; 
		pane.add(lCarte, c);
		c.gridx = 1; c.gridwidth = 4;
		pane.add(fCarte, c); 
		c.gridx = 0; c.gridy++;
		pane.add(lType, c);
		c.gridx = 1; c.gridwidth = 1;
		pane.add(cb1, c);
		c.gridx = 2; c.gridwidth = 1;
		pane.add(lVisa, c);
		c.gridx = 3; c.gridwidth = 1;
		pane.add(cb2, c);
		c.gridx = 4; c.gridwidth = 1;
		pane.add(lMasterCard, c);
		c.gridx = 0; c.gridy++; c.gridwidth = 1;
		pane.add(lExpiration, c);
		c.gridx = 1; c.gridwidth = 2;
		pane.add(jMonth, c);
		c.gridx = 3; c.gridwidth = 2;
		pane.add(jYears, c);
		c.gridx = 0; c.gridy++; c.gridwidth = 1;
		pane.add(lSecure, c);
		c.gridx = 1; c.gridwidth = 4;
		pane.add(fSecure, c);


		frame.add(pane, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	class ItemState implements ItemListener{

		public void itemStateChanged(ItemEvent e) {
			prix = (String) jCredit.getSelectedItem();
			add = Integer.parseInt(prix);
			
			lPrix.setText(add*1.5 + "0€");
			mois = (String) jMonth.getSelectedItem();
			annee = (String) jYears.getSelectedItem();
		}   
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == bOk)
		{
			if (!cb1.isSelected() && !cb2.isSelected()){
				JOptionPane.showMessageDialog(null, "Pas de selection VISA ou MASTERCARD", "Attention", JOptionPane.WARNING_MESSAGE);
			}else if (fCarte.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Numéro de carte incomplet", "Attention", JOptionPane.WARNING_MESSAGE);
			}else if (fSecure.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Code de sécurité invalide", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else{
				user.buyCredit (add,sqlhelper);
				frame.dispose();
			}
		}
		if (arg0.getSource() == bAnnuler)
		{
			frame.dispose();
		}
		if (arg0.getSource() == cb1)
		{
			cb2.setSelected(false);
		}
		if (arg0.getSource() == cb2)
		{
			cb1.setSelected(false);
		}
	}
}

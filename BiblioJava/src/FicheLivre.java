import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FicheLivre extends FicheDoc implements ActionListener{

	private JPanel panelCenterAetI;
	private JLabel labelResume;
	private JLabel labelAut;
	private JLabel labelAnne;
	private String annee;
	private JPanel panelAuteurEtDate;
	private JPanel panelDate;
	private JPanel panelBot;
	private JPanel panelCategory;
	private JTextArea labelCategory;
	private String category;
	private JLabel labelCat;
	private JLabel categoryL;
	private JPanel panelDispo;
	private JLabel labelDisp;
	private JLabel labelDisI;
	private JLabel labelDisIOK;
	private JLabel labelDisIKO;
	private int exmplaireDispo;
	private JPanel panelSelect;
	private JComboBox<String> comboWeek;
	private int tarif;
	private JLabel priceSelect;
	private JLabel dureeSelect;
	private JButton buttonAjouter;
	private int Jeton = 0;
	private int docId;
	private User user;
	private JComboBox<String> comboDay;
	
	public void setupFrame () {
		jf = new JFrame ();
		jf.setTitle("D�tail Article");
		jf.setMinimumSize(new Dimension(600,800));
		jf.setResizable(false);
		jf.setVisible(true);
	}
	public FicheLivre(int Id, User user) {
		super(Id);
		docId = Id;
		this.user = user;
		Jeton = user.getCredit();
		req(Id);
		
		setupFrame ();
		
		panel = new JPanel(new BorderLayout());
		panelTitre = new JPanel();
		panelAuteur = new JPanel ();
		panelDescription = new JPanel ();
		
		panelExemplaire = new JPanel ();
		panelExemplaire.setLayout(new BoxLayout(panelExemplaire,BoxLayout.X_AXIS));
		
		labelTitre = new JLabel("Titre");
		labelTitre.setFont(new Font(labelTitre.getFont().getFontName(),labelTitre.getFont().getStyle(),20));
		labelImage = new JLabel ("erreur");
		
		labelNbExemplaire = new JLabel ("Nb Exemplaire");
		labelNbEmprunt    = new JLabel ("Nb Emprunt" );
		
		labelResume = new JLabel( "R�sum� :");
		labelDescription = new JTextArea("Description");
		labelDescription.setFont(new Font(labelDescription.getFont().getFontName(),labelDescription.getFont().getStyle(),14));
		labelDescription.setLineWrap(true);
		labelDescription.setWrapStyleWord(true);
		labelDescription.setEditable(false);
		labelDescription.setMinimumSize(new Dimension(540,30));
		labelDescription.setColumns(32);
		labelDescription.setMaximumSize(new Dimension(540,100));
		
		labelDescription.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
		panelTitre.add(labelTitre);
		panelTitre.setBackground(Color.CYAN);
		panelExemplaire.add(Box.createHorizontalStrut(30));
		panelExemplaire.add(labelNbExemplaire);
		panelExemplaire.add(Box.createHorizontalStrut((int) (100-labelNbExemplaire.getPreferredSize().getWidth())));
		panelExemplaire.add(labelNbEmprunt);
		panelExemplaire.add(Box.createHorizontalGlue());
		
		try {
			labelImage = new JLabel( (Icon) new ImageIcon( new URL(image) ) );
		} catch (MalformedURLException e) {
			labelImage = new JLabel("erreur");
			e.printStackTrace();
		}
		labelTitre.setText(titre);
		labelDescription.setText(description);
		
		panelImage = new JPanel();
		panelImage.add(labelImage);
		
		panelDescription.setLayout(new BoxLayout(panelDescription,BoxLayout.Y_AXIS));
		panelDescription.add(labelResume);
		panelDescription.add(Box.createVerticalStrut(5));
		panelDescription.add(labelDescription);
		panelDescription.add(Box.createVerticalStrut(10));
		
		labelAuteur = new JTextArea (auteur);
		labelAut = new JLabel("Auteur :");
		labelAnne = new JLabel ("Date : " + annee);
		labelAnne.setFont(new Font(labelAnne.getFont().getFontName(),labelAnne.getFont().getStyle(),18));
		
		labelCat = new JLabel ("Cat�gorie :");
		labelCategory = new JTextArea (category);
		labelCategory.setMinimumSize(new Dimension(350,20));
		labelCategory.setMaximumSize(new Dimension(350,100));
		labelCategory.setColumns(20);
		labelCategory.setEditable(false);
		labelCategory.setWrapStyleWord(true);
		labelCategory.setLineWrap(true);
		labelCategory.setFont(new Font(labelCategory.getFont().getFontName(),Font.ITALIC,16));
		labelCategory.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		
		panelDate = new JPanel();
		panelDate.setLayout(new BoxLayout(panelDate, BoxLayout.X_AXIS));
		panelDate.add(Box.createHorizontalStrut(20));
		panelDate.add(labelAnne);
		panelDate.add(Box.createHorizontalGlue());
		
		labelAuteur.setMinimumSize(new Dimension(350,20));
		labelAuteur.setMaximumSize(new Dimension(350,100));
		labelAuteur.setColumns(20);
		labelAuteur.setEditable(false);
		labelAuteur.setWrapStyleWord(true);
		labelAuteur.setLineWrap(true);
		labelAuteur.setFont(new Font(labelAuteur.getFont().getFontName(),labelAuteur.getFont().getStyle(),16));
		labelAuteur.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		panelAuteur.setLayout(new BoxLayout(panelAuteur,BoxLayout.Y_AXIS));
		panelAuteur.add(panelDate);
		panelAuteur.add(labelCat);
		panelAuteur.add(labelCategory);
		panelAuteur.add(labelAut);
		panelAuteur.add(Box.createVerticalStrut(5));
		panelAuteur.add(labelAuteur);
		panelAuteur.add(Box.createHorizontalStrut(10));
		panelCommun = new JPanel(new GridLayout(3,1));
		
		panelAuteurplusImage = new JPanel ();
		panelAuteurplusImage.setLayout(new BoxLayout(panelAuteurplusImage,BoxLayout.X_AXIS));
		
		panelAuteurplusImage.add(Box.createHorizontalStrut(30));
		panelAuteurplusImage.add(panelAuteur);
		panelAuteurplusImage.add(Box.createHorizontalStrut((int) (250-panelAuteur.getPreferredSize().getWidth())));
		panelAuteurplusImage.add(panelImage);
		
		panelCenterAetI = new JPanel ();
		panelCenterAetI.setLayout(new BoxLayout(panelCenterAetI,BoxLayout.Y_AXIS));
		
		panelCenterAetI.add(Box.createVerticalStrut(10));
		panelCenterAetI.add(panelAuteurplusImage);
		panelCenterAetI.add(Box.createVerticalStrut(15));
		
		
		panelCommun.add(panelCenterAetI);
		panelCommun.add(panelDescription);
		//panelCommun.setBackground(Color.BLACK);
		
		panelBot = new JPanel();
		panelBot.setLayout(new GridLayout(3,1));
		
		panelDispo = new JPanel();
		panelDispo.setLayout(new BoxLayout(panelDispo,BoxLayout.X_AXIS));
		
		panelSelect = new JPanel();
		comboWeek = new JComboBox<String>();
		comboWeek.setFont(new Font(comboWeek.getFont().getFontName(),comboWeek.getFont().getStyle(),16));
		comboWeek.setPreferredSize(new Dimension(120,30));
		comboWeek.setMaximumSize(new Dimension(120,30));
		comboWeek.addItem(0 + " semaine");
		comboWeek.addItem(1 + " semaine");
		comboWeek.addItem(2 + " semaines");
		comboWeek.addItem(3 + " semaines");
		comboWeek.addItem(4 + " semaines");
		comboWeek.addItemListener(new ItemState());
		comboWeek.addActionListener(new ItemAction());
		
		comboDay = new JComboBox<String>();
		comboDay.setFont(new Font(comboDay.getFont().getFontName(),comboDay.getFont().getStyle(),16));
		comboDay.setPreferredSize(new Dimension(80,30));
		comboDay.setMaximumSize(new Dimension(80,30));
		comboDay.addItem(0 + " jour");
		comboDay.addItem(1 + " jour");
		comboDay.addItem(2 + " jours");
		comboDay.addItem(3 + " jours");
		comboDay.addItem(4 + " jours");
		comboDay.addItem(5 + " jours");
		comboDay.addItem(6 + " jours");
		comboDay.addItemListener(new ItemState());
		comboDay.addActionListener(new ItemAction());
		
		panelSelect.setLayout(new BoxLayout(panelSelect,BoxLayout.X_AXIS));
		
		priceSelect = new JLabel ("Price Total : 0 Cr�dits");
		priceSelect.setFont(new Font(priceSelect.getFont().getFontName(),Font.ROMAN_BASELINE,14));
		dureeSelect = new JLabel ("Dur�e : ");
		panelSelect.add(Box.createHorizontalStrut(20));
		panelSelect.add(dureeSelect);
		panelSelect.add(Box.createHorizontalStrut(20));
		panelSelect.add(comboWeek);
		panelSelect.add(Box.createHorizontalStrut(20));
		panelSelect.add(comboDay);
		panelSelect.add(Box.createHorizontalStrut(20));
		panelSelect.add(priceSelect);
		panelSelect.add(Box.createHorizontalGlue());
		
		labelDisp = new JLabel ("Disponibilit� : ");
		labelDisIOK = new JLabel ((Icon) (new ImageIcon("valOK.png")));
		labelDisIKO = new JLabel ((Icon) (new ImageIcon("valKO.png")));
		
		buttonAjouter = new JButton ("Emprunte");
		buttonAjouter.setForeground(Color.RED);
		buttonAjouter.setFont(new Font(buttonAjouter.getFont().getFontName(),buttonAjouter.getFont().getStyle(),18));
		
		panelDispo.add(labelDisp);
		if (exmplaireDispo > 0) {
			panelDispo.add(labelDisIOK);
		} else {
			panelDispo.add(labelDisIKO);
			buttonAjouter.setEnabled(false);
		}
		buttonAjouter.setEnabled(false);
		
		buttonAjouter.addActionListener(this);
		
		panelBot.add(panelDispo);
		panelBot.add(panelSelect);
		panelBot.add(buttonAjouter);
		panelCommun.add(panelBot);
		
		getPanel().add(panelTitre,BorderLayout.NORTH);
		getPanel().add(panelCommun,BorderLayout.CENTER);
		jf.add(getPanel());
	}
	
	class ItemAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("ActionListener : action sur " + comboWeek.getSelectedItem());
			
			priceSelect.setText("Price Total : " + ((int)((comboWeek.getSelectedItem().toString().charAt(0)-"0".charAt(0)) * tarif)) + " Cr�dits");
			if (comboWeek.getSelectedItem().toString().charAt(0)!='0' && comboDay.getSelectedItem().toString().charAt(0)!='0')
				buttonAjouter.setText("Emprunter pour " + comboWeek.getSelectedItem() + " et " +comboDay.getSelectedItem());
			else if (comboWeek.getSelectedItem().toString().charAt(0)!='0')
				buttonAjouter.setText("Emprunter pour " + comboWeek.getSelectedItem());
			else if (comboDay.getSelectedItem().toString().charAt(0)!='0')
				buttonAjouter.setText("Emprunter pour " +comboDay.getSelectedItem());
			else buttonAjouter.setText("Emprunter");
			if (Jeton <= (int)(comboWeek.getSelectedItem().toString().charAt(0)-"0".charAt(0)) * tarif || ( comboWeek.getSelectedItem().toString().charAt(0)=='0' && comboDay.getSelectedItem().toString().charAt(0)=='0')){
				buttonAjouter.setEnabled(false);
			}
			else if (exmplaireDispo > 0){
				buttonAjouter.setEnabled(true);
			}
		}               
	  }
	
	private class ItemState implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("�v�nement d�clench� sur : " + arg0.getItem());
		}               
	  }
	public void req (int Id){
		String sqlsearch = "SELECT * FROM LIVRE where ID = " + Id;
		 try {
			Class.forName("org.sqlite.JDBC");
			Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
	        System.out.println("Opened database Video successfully");
	        Statement stmt = connexion.createStatement();
	        ResultSet rs = stmt.executeQuery(sqlsearch);
	        titre =rs.getString("titre");
	        description = rs.getString("description");
	        image = rs.getString("image");
	        auteur = rs.getString("auteur");
	        annee = rs.getString("annee");
	        category = rs.getString("category");
	        exmplaireDispo = rs.getInt("NBEXEMPLAIRE");
	        tarif = rs.getInt("TARIF");
	        stmt.close();
		    connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	      
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==buttonAjouter) {
			new FicheEmprunt (user, docId,comboWeek.getSelectedItem().toString().charAt(0)-'0', comboDay.getSelectedItem().toString().charAt(0)-'0', "LIVRE", (comboWeek.getSelectedItem().toString().charAt(0)-'0') * tarif);
			jf.dispose();
		}
	}

}

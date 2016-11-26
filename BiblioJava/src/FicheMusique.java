import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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

public class FicheMusique extends FicheDoc implements ActionListener{

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
	private JComboBox<Integer> comboTime;
	private int tarif;
	private JLabel priceSelect;
	private JLabel dureeSelect;
	private JButton buttonAjouter;
	private int Jeton = 0;
	private String album;
	private JTextArea labelAlbum;
	private JLabel labelAlb;
	private User user;
	private int docId;
	private JComboBox<String> comboWeek;
	private JComboBox<String> comboDay;
	public FicheMusique(int Id, User user) {
		super(Id);
		this.user = user;
		this.docId = Id;
		Jeton = user.getCredit();
		req(Id);
		jf = new JFrame ();
		jf.setTitle("Détail Article");
		jf.setMinimumSize(new Dimension(600,800));
		jf.setResizable(false);
		jf.setVisible(true);
		setPanel(new JPanel(new BorderLayout()));
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
		
		//scrollDescription = new JScrollPane (labelDescription);
		//scrollDescription.setPreferredSize(new Dimension(400,200));
		
		//req(Id);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//labelImage.setMinimumSize(new Dimension(120,150));
		labelTitre.setText(titre);
		//labelDescription.setText(description);
		//labelAuteur.setText(auteur);
		
		panelImage = new JPanel();
		panelImage.add(labelImage);
		
		//panelDescription.setLayout(new BoxLayout(panelDescription,BoxLayout.Y_AXIS));
		//panelDescription.add(Box.createVerticalStrut(10));
		//panelDescription.add(labelResume);
		//panelDescription.add(Box.createVerticalStrut(5));
		//panelDescription.add(labelDescription);
		//panelDescription.add(Box.createVerticalStrut(10));
		
		labelAuteur = new JTextArea (auteur);
		labelAut = new JLabel("Auteur :");
		labelAnne = new JLabel ("Date : " + annee);
		labelAnne.setFont(new Font(labelAnne.getFont().getFontName(),labelAnne.getFont().getStyle(),18));
		
		labelAlb = new JLabel ("Album :");
		labelAlbum = new JTextArea (album);
		labelAlbum.setMinimumSize(new Dimension(150,20));
		labelAlbum.setMaximumSize(new Dimension(150,100));
		labelAlbum.setColumns(10);
		labelAlbum.setEditable(false);
		labelAlbum.setWrapStyleWord(true);
		labelAlbum.setLineWrap(true);
		labelAlbum.setFont(new Font(labelAlbum.getFont().getFontName(),Font.ITALIC,16));
		labelAlbum.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		
		
		
		/*panelDate = new JPanel();
		panelDate.setLayout(new BoxLayout(panelDate, BoxLayout.X_AXIS));
		panelDate.add(Box.createHorizontalStrut(20));
		panelDate.add(labelAnne);
		panelDate.add(Box.createHorizontalGlue());
		*/
		labelAuteur.setMinimumSize(new Dimension(150,20));
		labelAuteur.setMaximumSize(new Dimension(150,100));
		labelAuteur.setColumns(10);
		labelAuteur.setEditable(false);
		labelAuteur.setWrapStyleWord(true);
		labelAuteur.setLineWrap(true);
		labelAuteur.setFont(new Font(labelAuteur.getFont().getFontName(),labelAuteur.getFont().getStyle(),16));
		labelAuteur.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		panelAuteur.setLayout(new BoxLayout(panelAuteur,BoxLayout.Y_AXIS));
		panelAuteur.add(Box.createVerticalGlue());
		//panelAuteur.add(panelDate);
		panelAuteur.add(labelAlb);
		panelAuteur.add(labelAlbum);
		panelAuteur.add(Box.createVerticalGlue());
		//panelAuteur.add(panelCategory);
		//panelAuteur.add(Box.createHorizontalStrut(2));
		panelAuteur.add(labelAut);
		panelAuteur.add(Box.createVerticalStrut(5));
		panelAuteur.add(labelAuteur);
		//panelAuteur.add(Box.createHorizontalStrut(10));
		panelAuteur.add(Box.createVerticalGlue());
		panelAuteur.add(Box.createVerticalGlue());
		panelCommun = new JPanel(new GridLayout(3,1));
		
		panelAuteurplusImage = new JPanel ();
		panelAuteurplusImage.setLayout(new BoxLayout(panelAuteurplusImage,BoxLayout.X_AXIS));
		
		panelAuteurplusImage.add(Box.createHorizontalStrut(30));
		panelAuteurplusImage.add(panelAuteur);
		panelAuteurplusImage.add(Box.createHorizontalStrut((int) (125-panelAuteur.getPreferredSize().getWidth())));
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
		
		priceSelect = new JLabel ("Price Total : 0 Crédits");
		priceSelect.setFont(new Font(priceSelect.getFont().getFontName(),Font.ROMAN_BASELINE,14));
		dureeSelect = new JLabel ("Durée : ");
		panelSelect.add(Box.createHorizontalStrut(50));
		panelSelect.add(dureeSelect);
		panelSelect.add(Box.createHorizontalStrut(25));
		panelSelect.add(comboWeek);
		panelSelect.add(Box.createHorizontalStrut(25));
		panelSelect.add(comboDay);
		panelSelect.add(Box.createHorizontalStrut(25));
		panelSelect.add(priceSelect);
		panelSelect.add(Box.createHorizontalGlue());
		
		labelDisp = new JLabel ("Disponibilité : ");
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
		//panel.add(labelImage,BorderLayout.SOUTH);
		jf.add(getPanel());
		//super.addUI();
		// TODO Auto-generated constructor stub
	}
	
	class ItemAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("ActionListener : action sur " + comboWeek.getSelectedItem());
			
			priceSelect.setText("Price Total : " + ((int)((comboWeek.getSelectedItem().toString().charAt(0)-"0".charAt(0)) * tarif)) + " Crédits");
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
			System.out.println("événement déclenché sur : " + arg0.getItem());
		}               
	  }
	public void req (int Id){
		String sqlsearch = "SELECT * FROM AUDIO where ID = " + Id;
		 try {
			Class.forName("org.sqlite.JDBC");
			Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
	        System.out.println("Opened database Video successfully");
	        Statement stmt = connexion.createStatement();
	        ResultSet rs = stmt.executeQuery(sqlsearch);
	        titre =rs.getString("titre");
	        //description = rs.getString("description");
	        image = rs.getString("image");
	        auteur = rs.getString("auteur");
	        album = rs.getString("album");
	        //annee = rs.getString("annee");
	        //category = rs.getString("category");
	        exmplaireDispo = rs.getInt("NBEXEMPLAIRE") - rs.getInt("NBEMPRUNT");
	        tarif = rs.getInt("TARIF");
	        stmt.close();
		    connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource()==buttonAjouter) {
			new FicheEmprunt (user, docId,comboWeek.getSelectedItem().toString().charAt(0)-'0', comboDay.getSelectedItem().toString().charAt(0)-'0', "AUDIO", (int)(comboWeek.getSelectedItem().toString().charAt(0)-'0') * tarif);
			jf.dispose();
		}
	}

}

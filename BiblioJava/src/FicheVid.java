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

public class FicheVid extends FicheDoc implements ActionListener{

	private JPanel panelCenterAetI;
	private JLabel labelResume;
	private JLabel labelAnne;
	private String annee;
	private JPanel panelDate;
	private JPanel panelBot;
	private JPanel panelDispo;
	private JLabel labelDisp;
	private JLabel labelDisIOK;
	private JLabel labelDisIKO;
	private int exmplaireDispo;
	private JPanel panelSelect;
	private int tarif;
	private JLabel priceSelect;
	private JLabel dureeSelect;
	private JButton buttonAjouter;
	private int jeton = 0;
	private String langage;
	private String note;
	private JLabel labelLang;
	private JTextArea labelLanguage;
	private JLabel labelNot;
	private JTextArea labelNote;
	private User user;
	private int docId;
	private JComboBox<String> comboWeek;
	private JComboBox<String> comboDay;
	private SqlHelper sqlhelper = new SqlHelper(); 
	public FicheVid(int Id, User user) {
		super(Id);
		this.user = user;
		this.docId = Id;
		this.jeton = user.getCredit();
		req(Id);
		this.jf = new JFrame ();
		this.jf.setTitle("Détail Article");
		this.jf.setMinimumSize(new Dimension(600,800));
		this.jf.setResizable(false);
		this.jf.setVisible(true);
		
		this.panel=new JPanel(new BorderLayout());
		this.panelTitre = new JPanel();
		panelAuteur = new JPanel ();
		panelDescription = new JPanel ();
		panelExemplaire = new JPanel ();
		panelExemplaire.setLayout(new BoxLayout(panelExemplaire,BoxLayout.X_AXIS));
		
		labelTitre = new JLabel("Titre");
		labelTitre.setFont(new Font(labelTitre.getFont().getFontName(),labelTitre.getFont().getStyle(),20));
		labelImage = new JLabel ("erreur");
		
		labelNbExemplaire = new JLabel ("Nb Exemplaire");
		labelNbEmprunt    = new JLabel ("Nb Emprunt" );
		
		labelResume = new JLabel( "Résumé :");
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
		
		labelNot = new JLabel("Note :");
		labelAnne = new JLabel ("Date : " + annee);
		labelAnne.setFont(new Font(labelAnne.getFont().getFontName(),labelAnne.getFont().getStyle(),18));
		
		labelLang = new JLabel ("Langue :");
		labelLanguage = new JTextArea (langage);
		labelLanguage.setMinimumSize(new Dimension(150,10));
		labelLanguage.setMaximumSize(new Dimension(150,100));
		labelLanguage.setColumns(10);
		labelLanguage.setEditable(false);
		labelLanguage.setWrapStyleWord(true);
		labelLanguage.setLineWrap(true);
		labelLanguage.setFont(new Font(labelLanguage.getFont().getFontName(),Font.ITALIC,16));
		labelLanguage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		
		panelDate = new JPanel();
		panelDate.setLayout(new BoxLayout(panelDate, BoxLayout.X_AXIS));
		panelDate.add(Box.createHorizontalStrut(20));
		panelDate.add(labelAnne);
		panelDate.add(Box.createHorizontalGlue());
		
		labelNote = new JTextArea(note);
		labelNote.setMinimumSize(new Dimension(150,10));
		labelNote.setMaximumSize(new Dimension(150,100));
		labelNote.setColumns(10);
		labelNote.setEditable(false);
		labelNote.setWrapStyleWord(true);
		labelNote.setLineWrap(true);
		labelNote.setFont(new Font(labelNote.getFont().getFontName(),labelNote.getFont().getStyle(),16));
		labelNote.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		panelAuteur.setLayout(new BoxLayout(panelAuteur,BoxLayout.Y_AXIS));
		panelAuteur.add(panelDate);
		panelAuteur.add(labelNot);
		panelAuteur.add(labelNote);
		panelAuteur.add(labelLang);
		panelAuteur.add(Box.createVerticalStrut(5));
		panelAuteur.add(labelLanguage);
		panelAuteur.add(Box.createHorizontalStrut(10));
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
		jf.add(getPanel());
	}
	
	class ItemAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			priceSelect.setText("Price Total : " + ((int)((comboWeek.getSelectedItem().toString().charAt(0)-"0".charAt(0)) * tarif)) + " Crédits");
			if (comboWeek.getSelectedItem().toString().charAt(0)!='0' && comboDay.getSelectedItem().toString().charAt(0)!='0')
				buttonAjouter.setText("Emprunter pour " + comboWeek.getSelectedItem() + " et " +comboDay.getSelectedItem());
			else if (comboWeek.getSelectedItem().toString().charAt(0)!='0')
				buttonAjouter.setText("Emprunter pour " + comboWeek.getSelectedItem());
			else if (comboDay.getSelectedItem().toString().charAt(0)!='0')
				buttonAjouter.setText("Emprunter pour " +comboDay.getSelectedItem());
			else buttonAjouter.setText("Emprunter");
			if (jeton <= (int)(comboWeek.getSelectedItem().toString().charAt(0)-"0".charAt(0)) * tarif || ( comboWeek.getSelectedItem().toString().charAt(0)=='0' && comboDay.getSelectedItem().toString().charAt(0)=='0')){
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
			System.out.println("événement déclenché sur : " + arg0.getItem());
		}               
	  }
	public void req (int Id){
		String sqlsearch = "SELECT * FROM VIDEO where ID = " + Id;
		sqlhelper.connect();
		sqlhelper.searchsql(sqlsearch);
        titre =sqlhelper.getString("titre");
        description = sqlhelper.getString("description");
        image = sqlhelper.getString("image");
        langage = sqlhelper.getString("language");
        note = sqlhelper.getString("note");
        annee = sqlhelper.getString("annee");
        exmplaireDispo = sqlhelper.getInt("NBEXEMPLAIRE") - sqlhelper.getInt("NBEMPRUNT");
        tarif = sqlhelper.getInt("TARIF");
        sqlhelper.disconnect();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==buttonAjouter) {
			new FicheEmprunt (user, docId,comboWeek.getSelectedItem().toString().charAt(0)-'0', comboDay.getSelectedItem().toString().charAt(0)-'0', "VIDEO", (int)(comboWeek.getSelectedItem().toString().charAt(0)-'0') * tarif);
			jf.dispose();
		}
	}
}

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddDocument extends JFrame implements ActionListener{
	Document newDoc;
	
	
	private JLabel labelTitre = new JLabel ("Titre");
	private JLabel labelNbExemplaire = new JLabel ("Nb d'exemplaires");
	private JLabel labelTarif = new JLabel ("Tarif");
	private JLabel labelNbEmprunt = new JLabel("Nb d'emprunts");
	protected JFormattedTextField fieldNbExemplaire = new JFormattedTextField( NumberFormat.getNumberInstance() );
	protected JTextField fieldTitre = new JTextField();
	protected JFormattedTextField fieldNbEmprunt = new JFormattedTextField( NumberFormat.getNumberInstance() );
	protected JFormattedTextField fieldTarif = new JFormattedTextField( NumberFormat.getNumberInstance() );
	
	protected JPanel pane;
	protected GridBagConstraints c;
	protected JButton boutonvalider;
	protected boolean docIsOK;
	protected SqlHelper sqlHelper = new SqlHelper();
	
	public AddDocument(String title) {
		docIsOK = false;
		this.setTitle("Add " + title);
		this.setMinimumSize(new Dimension(450,700));
		this.setLayout(new BorderLayout());
		boutonvalider = new JButton("Valider");
		
		
		fieldTitre.setPreferredSize(new Dimension(200, 25));
		fieldNbExemplaire.setPreferredSize(new Dimension(200, 25));
		fieldTarif.setPreferredSize(new Dimension(200, 25));
		fieldNbEmprunt.setPreferredSize(new Dimension(200, 25));
		
		pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		
		c.gridx = 0; c.gridy = 0;
		pane.add(labelTitre,c);
		c.gridx = 1; c.gridy = 0;
		pane.add(fieldTitre, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelNbExemplaire, c);
		c.gridx = 1;
		pane.add(fieldNbExemplaire, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelTarif, c);
		c.gridx = 1;
		pane.add(fieldTarif, c);
		c.gridx = 0; c.gridy++;
		pane.add(labelNbEmprunt, c);
		c.gridx = 1;
		pane.add(fieldNbEmprunt, c);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { // formatter integer only autorisé a faire
		// TODO Auto-generated method stub
		if (arg0.getSource() == boutonvalider) {
			if (fieldTitre.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Titre empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else if (fieldNbEmprunt.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Nbemprunt empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else if (fieldTarif.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "tTrif empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else if (fieldNbExemplaire.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Nb Exemplaire empty", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else docIsOK = true;
		}
	}
	
	
}

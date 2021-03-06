public class Document
{
	protected String code;
	protected String titre;
	protected String auteur;
	protected int annee;
	protected boolean emprunte;
	protected boolean empruntable;
	protected int nbEmprunt;
	protected int nbExemplaire;
	
	public Document(String titre, String auteur, int annee, int nbExemplaire)
	{
		this.setCode("a trouver");
		this.setTitre(titre); 
		this.setAuteur(auteur);
		this.setAnnee(annee);
		this.setEmprunte(false);
		this.setEmpruntable(true);
		this.setNbEmprunt(0);
		this.setNbExemplaire(nbExemplaire);
	}
	
	public Document(Document doc)
	{
		this.setCode("a trouver");
		this.setTitre(doc.titre); 
		this.setAuteur(doc.auteur);
		this.setAnnee(doc.annee);
		this.setEmprunte(false);
		this.setEmpruntable(true);
		this.setNbEmprunt(0);
		this.setNbExemplaire(doc.nbExemplaire);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public boolean isEmprunte() {
		return emprunte;
	}

	public void setEmprunte(boolean emprunte) {
		this.emprunte = emprunte;
	}

	public int getNbEmprunt() {
		return nbEmprunt;
	}

	public void setNbEmprunt(int nbEmprunt) {
		this.nbEmprunt = nbEmprunt;
	}

	public boolean isEmpruntable() {
		return empruntable;
	}

	public void setEmpruntable(boolean empruntable) {
		this.empruntable = empruntable;
	}

	public int getNbExemplaire() {
		return nbExemplaire;
	}

	public void setNbExemplaire(int nbExemplaire) {
		this.nbExemplaire = nbExemplaire;
	}
}
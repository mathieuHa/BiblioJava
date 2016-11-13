public class Livre extends Document
{
	private int nbPage;
	private int dureeEmprunt;
	private int tarif;
	
	public Livre(String titre, String auteur, int annee, int nbExemplaire) {
		super(titre, auteur, annee, nbExemplaire);
		// TODO Auto-generated constructor stub
	}
	
	public Livre(Document doc, int nbPage) {
		super(doc);
		this.nbPage = nbPage;
		this.setDureeEmprunt(7);
		this.tarif = 1;
		// TODO Auto-generated constructor stub
	}


	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(int tarif) {
		this.tarif = tarif;
	}

	public int getDureeEmprunt() {
		return dureeEmprunt;
	}

	public void setDureeEmprunt(int dureeEmprunt) {
		this.dureeEmprunt = dureeEmprunt;
	}
	
	
}
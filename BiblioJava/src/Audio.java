public class Audio extends Document{
	private String classification;
	private int dureeEmprunt;
	private int tarif;

	public Audio(Document doc) {
		super(doc);
		this.setDureeEmprunt(7);
		this.setTarif(1);
		// TODO Auto-generated constructor stub
	}

	public Audio(String titre, String auteur, int annee, int nbExemplaire) {
		super(titre, auteur, annee, nbExemplaire);
		this.setDureeEmprunt(7);
		this.setTarif(1);
		// TODO Auto-generated constructor stub
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public int getDureeEmprunt() {
		return dureeEmprunt;
	}

	public void setDureeEmprunt(int dureeEmprunt) {
		this.dureeEmprunt = dureeEmprunt;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(int tarif) {
		this.tarif = tarif;
	}
}
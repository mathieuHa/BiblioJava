public class Audio {
	private String classification;
	private int dureeEmprunt;
	private double tarif;

	public Audio(String classification, int duree, double tarif){
		this.classification = classification;
		this.setDureeEmprunt(duree);
		this.setTarif(tarif);
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

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}
}
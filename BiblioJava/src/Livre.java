public class Livre
{
	private int nbPage;
	private int dureeEmprunt;
	private double tarif;

	public Livre(int nbPage, int duree, double tarif){
		this.nbPage = nbPage;
		this.setDureeEmprunt(duree);
		this.tarif = tarif;
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

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public int getDureeEmprunt() {
		return dureeEmprunt;
	}

	public void setDureeEmprunt(int dureeEmprunt) {
		this.dureeEmprunt = dureeEmprunt;
	}
	
	
}
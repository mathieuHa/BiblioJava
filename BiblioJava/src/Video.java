public class Video
{
	private String mentionLegale;
	private int dureeFilm;
	private int dureeEmprunt;
	private double tarif;

	public Video(int dureeFilm,	String mentionLegale)
	{
		this.setDureeFilm(dureeFilm);
		this.setMentionLegale(mentionLegale);
		this.setDureeEmprunt(7);
		this.setTarif(0.5);
	}
	
	public Video () {
		
	}

	public String getMentionLegale() {
		return mentionLegale;
	}

	public void setMentionLegale(String mentionLegale) {
		this.mentionLegale = mentionLegale;
	}

	public int getDureeFilm() {
		return dureeFilm;
	}

	public void setDureeFilm(int dureeFilm) {
		this.dureeFilm = dureeFilm;
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
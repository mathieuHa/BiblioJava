public class Video extends Document
{
	
	private String mentionLegale;
	private int dureeFilm;
	private int dureeEmprunt;
	private int tarif;
	
	public Video(Document doc, int dureeFilm, String mentionLegale) {
		super(doc);
		this.mentionLegale = mentionLegale;
		this.dureeFilm = dureeFilm;
		this.dureeEmprunt = 7;
		this.tarif = 1;
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

	public int getTarif() {
		return tarif;
	}

	public void setTarif(int tarif) {
		this.tarif = tarif;
	}
}
public class Video
{
	private String mentionLegale;
	private int dureeFilm;
	private int duree;
	private double tarif;

	public Video(String code, String titre, String auteur, int annee, String classification, int dureeFilm,	String mentionLegale, int duree, double tarif)
	{
		//super(code, titre, auteur, annee);
		this.dureeFilm		= dureeFilm;
		this.mentionLegale	= mentionLegale;
		this.duree			= duree;
		this.tarif			= tarif;
	}
}
public class Audio
{
	private String classification;
	private int duree;
	private double tarif;

	public Audio(String code, String titre, String auteur, _
	int annee, String classification, int duree, double tarif)
	{
		super(code, titre, auteur, annee);
		this.classification	= classification;
		this.duree			= duree;
		this.tarif			= tarif;
	}
}
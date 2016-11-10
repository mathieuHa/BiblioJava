public class Livre
{
	private int nbPage;
	private int duree;
	private double tarif;

	public Livre(String code, String titre, String auteur, _
	int annee, String classification, int nbPage, _
	int duree, double tarif)
	{
		super(code, titre, auteur, annee);
		this.nbPage			= nbPage;
		this.duree			= duree;
		this.tarif			= tarif;
	}
}
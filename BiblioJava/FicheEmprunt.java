public class FicheEmprunt
{
	private int dateEmprunt;
	private int dateLimite;
	private int dateRappel;
	private boolean depasse;
	private int tarif;
	public FicheEmprunt(int dateEmprunt, int dateLimite, int dateRappel, Audio audio)
	{
		this.dateEmprunt 	= dateEmprunt;
		this.dateLimite 	= dateLimite;
		this.dateRappel		= dateRappel;
		this.depasse		= false;
		//this.tarif 			= audio.getTarif();
	}
	public FicheEmprunt(int dateEmprunt, int dateLimite, int dateRappel, Video video)
	{
		this.dateEmprunt 	= dateEmprunt;
		this.dateLimite 	= dateLimite;
		this.dateRappel		= dateRappel;
		this.depasse		= false;
		//this.tarif 			= video.getTarif();
	}
	public FicheEmprunt(int dateEmprunt, int dateLimite, int dateRappel, Livre livre)
	{
		this.dateEmprunt 	= dateEmprunt;
		this.dateLimite 	= dateLimite;
		this.dateRappel		= dateRappel;
		this.depasse		= false;
		//this.tarif 			= livre.getTarif();
	}
}
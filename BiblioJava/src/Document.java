public class Document
{
	private String code;
	private String titre;
	private String auteur;
	private int annee;
	private boolean emprunte;
	private int nbEmprunt;
	
	public Document(String code, String titre, String auteur, int annee)
	{
		this.code 			= code;
		this.titre 			= titre; 
		this.auteur			= auteur;
		this.annee			= annee;
		this.emprunte 		= false;
		this.nbEmprunt		= 0;
	}
}
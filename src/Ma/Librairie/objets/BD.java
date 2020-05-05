package Ma.Librairie.objets;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * Une BD est un objet h�ritant de Ouvrage, il regroupe les caract�ristiques de la BD et de l'ouvrage
 * 
 */
public class BD extends Ouvrage{
	public String Scenariste;
	public String Dessinateur;
	
	public BD (String Titre, String Editeur, String Annee_publication, int Prix_fournisseur, String Scenariste, String Dessinateur) {
		this.Titre = Titre;
		this.Editeur = Editeur;
		this.Annee_publication = Annee_publication;
		this.Prix_vente = (int) (Prix_fournisseur*1.4);
		this.Scenariste = Scenariste;
		this.Dessinateur = Dessinateur;
		this.nb_points = 50;
	}
}

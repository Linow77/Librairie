package Ma.Librairie.objets;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * Un livre est un objet héritant de Ouvrage, il regroupe les caractéristiques du livre et de l'ouvrage
 * 
 */
public class Livre extends Ouvrage{
	public String Auteur;

	public Livre (String Titre, String Editeur, String Annee_publication, int Prix_fournisseur, String Auteur) {
		this.Titre = Titre;
		this.Editeur = Editeur;
		this.Annee_publication = Annee_publication;
		this.Prix_vente = (int) (Prix_fournisseur*1.2);
		this.Auteur = Auteur;
		this.nb_points = 30;

	}

}

package Ma.Librairie.objets;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * Une Vente est un objet comprenant un ouvrage et le client qui à acheté cet ouvrage
 * 
 */
public class Vente {
	public Ouvrage ouvrage_vendu;
	public Client acheteur;
	
	/** CONSTRUCTEUR **/
	public Vente(Ouvrage ouvrage_vendu,Client acheteur)
	{
		this.ouvrage_vendu = ouvrage_vendu;
		this.acheteur = acheteur;
	}
	
}

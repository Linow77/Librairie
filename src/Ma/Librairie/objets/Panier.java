package Ma.Librairie.objets;

import java.util.ArrayList;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * Un Panier est une liste d'ouvrage et un prix total regroupant les prix des ouvrages de la liste
 * 
 */
public class Panier {
	
	public ArrayList<Ouvrage> List_ouvrage = new ArrayList<Ouvrage>();
	public float prix_panier = 0;
}

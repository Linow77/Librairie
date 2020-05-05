package Ma.Librairie.objets;

import java.util.ArrayList;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * Un Stock est la liste de tous les ouvrages compris dans la librairie, ainsi que le nombre d'ouvrage
 * Ici nous avons 8 livres et 8 BD, il est possible d'en rajouter sans soucis
 */
public class Stock {
	public ArrayList<Ouvrage> Stock_ouvrage = new ArrayList<Ouvrage>();
	public int nb_ouvrages;
	//CREATION DU STOCK
	public Stock ()
	{
		// CREATION LIVRES
		Livre Livre1 = new Livre("Livre1","Galianc","1956",25,"Nathan");
		Livre Livre2 = new Livre("Livre2","Galianc","1962",12,"Nathan");
		Livre Livre3 = new Livre("Livre3","Galianc","1979",15,"Nathan");
		Livre Livre4 = new Livre("Livre4","Galianc","1968",100,"Nathan");
		Livre Livre5 = new Livre("Livre5","Galianc","1914",64,"Nathan");
		Livre Livre6 = new Livre("Livre6","Galianc","1979",3,"Nathan");
		Livre Livre7 = new Livre("Livre7","Galianc","1999",42,"Nathan");
		Livre Livre8 = new Livre("Livre8","Galianc","2016",18,"Nathan");
		
		//CREATION BD
		BD BD1 = new BD("BD1","Galianc","1915",25,"Robert","Albert");
		BD BD2 = new BD("BD2","Galianc","1946",47,"Robert","Albert");
		BD BD3 = new BD("BD3","Galianc","1938",5,"Robert","Albert");
		BD BD4 = new BD("BD4","Galianc","1947",7,"Robert","Albert");
		BD BD5 = new BD("BD5","Galianc","1996",16,"Robert","Albert");
		BD BD6 = new BD("BD6","Galianc","1946",49,"Robert","Albert");
		BD BD7 = new BD("BD7","Galianc","1993",70,"Robert","Albert");
		BD BD8 = new BD("BD8","Galianc","1936",10,"Robert","Albert");
		
		//AJOUT LIVRES DANS LA LISTE
		this.Stock_ouvrage.add(Livre1);
		this.Stock_ouvrage.add(Livre2);
		this.Stock_ouvrage.add(Livre3);
		this.Stock_ouvrage.add(Livre4);
		this.Stock_ouvrage.add(Livre5);
		this.Stock_ouvrage.add(Livre6);
		this.Stock_ouvrage.add(Livre7);
		this.Stock_ouvrage.add(Livre8);
		//AJOUT BD DANS LA LISTE OUVRAGES
		this.Stock_ouvrage.add(BD1);
		this.Stock_ouvrage.add(BD2);
		this.Stock_ouvrage.add(BD3);
		this.Stock_ouvrage.add(BD4);
		this.Stock_ouvrage.add(BD5);
		this.Stock_ouvrage.add(BD6);
		this.Stock_ouvrage.add(BD7);
		this.Stock_ouvrage.add(BD8);
		
		this.nb_ouvrages = this.Stock_ouvrage.size();
	}
	
}

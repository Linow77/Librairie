package Ma.Librairie.modele;

import java.util.OptionalInt;
import java.util.stream.IntStream;

import Ma.Librairie.objets.Client;
import Ma.Librairie.objets.Clients;
import Ma.Librairie.objets.Journal_ventes;
import Ma.Librairie.objets.Ouvrage;
import Ma.Librairie.objets.Panier;
import Ma.Librairie.objets.Stock;
import Ma.Librairie.objets.Vente;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * BUY_MODELE est le modèle lié a la vue BUY_PANEL et au controleur BUY_CONTROLER
 * 
 */
public class Buy_model {
	public Clients mes_clients;
	public Panier mon_panier;
	public Stock stock_librairie; //Stock final
	public Stock stock_partiel;
	public Journal_ventes journal_vente;
	
	
	/** CONSTRUCTEUR **/
    public Buy_model(Clients mes_clients,Panier mon_panier,Stock stock_librairie,Stock stock_partiel, Journal_ventes journal_ventes) {
    	this.mes_clients = mes_clients;
    	this.mon_panier = mon_panier;
    	this.stock_librairie = stock_librairie;
    	this.stock_partiel = stock_partiel;
    	this.journal_vente = journal_ventes;
    }
    
    //RECHERCHE OUVRAGE
    public Ouvrage recherche_ouvrage(Stock Stock_partiel,String Titre) {
    	Ouvrage ouvrage_trouve =null;
    	for(int i=0; i<Stock_partiel.nb_ouvrages;i++) {
    		if (Stock_partiel.Stock_ouvrage.get(i).Titre.contentEquals(Titre)){
    			ouvrage_trouve = Stock_partiel.Stock_ouvrage.get(i);
    		}
    	}
    	return ouvrage_trouve;
    }
    
    //AJOUT OUVRAGE DANS PANIER
    public	void ajout_ouvrage_panier(Panier panier_actuel, Ouvrage ouvrage_demande) 
	{	
		panier_actuel.List_ouvrage.add(ouvrage_demande);
	}
    
    //SUPPRESSION OUVRAGE DU STOCK PARTIEL
    public void supprimer_ouvrage_stock_partiel(Stock Stock_partiel, Ouvrage ouvrage_demande)
    {
    	Stock_partiel.Stock_ouvrage.remove(ouvrage_demande);
    	Stock_partiel.nb_ouvrages--;
    }
    
    //CALCUL DU NOUVEAU PRIX DU PANIER
    public void calcul_prix_panier(Panier mon_panier, Ouvrage ouvrage_demande)
    {
    	mon_panier.prix_panier = mon_panier.prix_panier + ouvrage_demande.Prix_vente;
    }
    
    //RECHERCHE CLIENT PAR NOM
    public Client get_client(String nom_client)
    {
    	Clients liste_client = new Clients();
    	Client Client_trouve = null;
    	
    	for(int i=0; i<5; i++)
    	{
    		if (liste_client.Clients_librarie.get(i).Nom.contentEquals(nom_client))		
    		{
    			Client_trouve = liste_client.Clients_librarie.get(i);		
    		}
    		
    	}
    	return Client_trouve;
    }
    
    //AJOUT VENTE DANS JOURNAL
    public void ajout_vente_journal(Journal_ventes journal_ventes, Panier panier_acheteur, Client acheteur)
    {
    	int longueur_panier = panier_acheteur.List_ouvrage.size();
    	
    	
    	for(int i=0; i<longueur_panier;i++)
    	{
    		Vente vente = new Vente(null,acheteur);
    		vente.ouvrage_vendu = panier_acheteur.List_ouvrage.get(i);
    		journal_ventes.Journal.add(vente);
    	}
    }
    
    //MODIFICATION STOCK APRES ACHAT
    public void modif_stock(Stock stock_librairie, Panier panier_acheteur)
    {
    	int longueur_panier = panier_acheteur.List_ouvrage.size();
    	for(int i=0; i< longueur_panier;i++)
    	{
    		stock_librairie.Stock_ouvrage.remove(panier_acheteur.List_ouvrage.get(i));
    	}
    	
    }
    
    //AJOUT DES POINTS AU CLIENT
    public Ouvrage ajout_point_client(Panier panier_acheteur, Client acheteur, Clients mes_clients)
    {
    	Ouvrage ouvrage_gratuit = null;
    	// ajout des points
    	int longueur_panier = panier_acheteur.List_ouvrage.size();
    	int num_client = acheteur.Num_compte;
    	int nb_points =0;

    	for(int i=0; i< longueur_panier;i++)
    	{
    		//la liste commence a 0 d'ou le client -1
    		nb_points = nb_points + panier_acheteur.List_ouvrage.get(i).nb_points;
    	}
    	mes_clients.Clients_librarie.get(num_client-1).nb_points = mes_clients.Clients_librarie.get(num_client-1).nb_points + nb_points;
    	
    	//livre gratuit
    	if (mes_clients.Clients_librarie.get(num_client-1).nb_points >= 1000)
    	{
    		//changement du prix panier
    		//recup tous les prix de chaque ouvrage pour trouver le moins cher
    		//Nous avons 'longueur panier' ouvrages
    		
    		int[] prix_ouvrage = new int[longueur_panier];
    		for(int i=0; i<longueur_panier;i++)
    		{
    			prix_ouvrage[i] = panier_acheteur.List_ouvrage.get(i).Prix_vente;
    		}
    		OptionalInt min = IntStream.of(prix_ouvrage).min();
    		
    		//Remboursement du livre le moins cher
    		panier_acheteur.prix_panier = panier_acheteur.prix_panier - min.getAsInt();
    		
    		//On enleve 1000 points au client
    		mes_clients.Clients_librarie.get(num_client-1).nb_points = mes_clients.Clients_librarie.get(num_client-1).nb_points - 1000;
    		
    		//recherche ouvrage avec ce prix pour le donner a la vue
    		for(int i=0;i<longueur_panier;i++)
    		{
    			if(panier_acheteur.List_ouvrage.get(i).Prix_vente == min.getAsInt())
    			{
    				ouvrage_gratuit = panier_acheteur.List_ouvrage.get(i);
    			}
    		}
    	}
    	return ouvrage_gratuit;
    }
}

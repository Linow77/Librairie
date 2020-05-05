package Ma.Librairie.modele;

import Ma.Librairie.objets.Clients;
import Ma.Librairie.objets.Journal_ventes;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * LOYALTY_MODEL est le modèle lié a la vue LOYALTY_PANEL et au controleur LOYALTY_CONTROLER
 * 
 */

public class Loyalty_model {
	public Clients mes_client;
    public Journal_ventes journal;
    
	/** CONSTRUCTEUR **/
    public Loyalty_model(Clients mes_client, Journal_ventes journal) {
       this.mes_client = mes_client;
       this.journal = journal;
    }
    
    //récupère le numéro d'un client depuis son nom
    public int get_num_client(String nom_client)
    {
    	Clients liste_client = new Clients();
    	int num_Client_trouve = 0;
    	
    	for(int i=0; i<5; i++)
    	{
    		if (liste_client.Clients_librarie.get(i).Nom.contentEquals(nom_client))		
    		{
    			num_Client_trouve = liste_client.Clients_librarie.get(i).Num_compte;		
    		}
    		
    	}
    	return num_Client_trouve;
    }

    
}

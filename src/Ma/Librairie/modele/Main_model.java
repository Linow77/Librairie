package Ma.Librairie.modele;

import Ma.Librairie.objets.Clients;
import Ma.Librairie.objets.Journal_ventes;
import Ma.Librairie.objets.Stock;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * MAIN_MODEL est le modèle lié a la vue MAIN_PANEL et au controleur MAIN_CONTROLER
 * 
 */
public class Main_model {
	public Clients mes_clients;
	public Stock stock_librairie; //Stock final
	public Journal_ventes journal_ventes;
	
	/** CONSTRUCTEUR **/
    public Main_model(Clients mes_clients, Stock stock_librairie, Journal_ventes journal_ventes) {
    	this.mes_clients = mes_clients;
    	this.stock_librairie = stock_librairie;
    	this.journal_ventes = journal_ventes;
    }
}

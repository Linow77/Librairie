package Ma.Librairie.controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import Ma.Librairie.modele.Buy_model;
import Ma.Librairie.objets.Client;
import Ma.Librairie.objets.Ouvrage;
import Ma.Librairie.vue.Buy_Panel;

/**
 * 
 * @author Richard CLOOS
 *
 */

/*
 * BUY_CONTROLER permet les intéractions entre la vue BUY_PANEL et le modèle BUY_MODELE
 * sur la fenêtre d'achat BUY_WINDOW
 * 
 */

public class Buy_Controler implements ActionListener{

	Buy_Panel buy_panel;
	Buy_model buy_model;
	
	/** CONSTRUCTEUR **/
	public Buy_Controler(Buy_Panel buy_panel, Buy_model buy_model) 
	{
        this.buy_panel = buy_panel;
        this.buy_model = buy_model;
    }
	
	public void actionPerformed(ActionEvent e) {

		/** Changement de l'ouvrage souhaité **/ //Inutile
        if (e.getSource() == this.buy_panel.getlisteouvrage_box()) {
        }
        
        /** Clique sur Ajouter **/
        if (e.getSource() == this.buy_panel.getButton_ajouter()) {
        	
        	
        	
        	//Récupérer le titre de l'ouvrage saisie dans la JComboBox **VUE**
        	String titre_ouvrage = this.buy_panel.recup_titre_ouvrage_list();
        	//Vérifie qu'il reste des ouvrages a acheter
        	if (titre_ouvrage.contentEquals("ERROR"))
        	{
        		//Avertir l'utilisateur qu'il ne peut plus ajouter d'ouvrage dans son panier
        		this.buy_panel.affiche_stock_vide();
        	}else {
        	
	        	//Chercher un ouvrage avec ce titre **MODELE**
	        	Ouvrage ouvrage_demande = this.buy_model.recherche_ouvrage(this.buy_model.stock_partiel,titre_ouvrage);
	        	
	        	//Ajouter au panier **MODEL**
	        	this.buy_model.ajout_ouvrage_panier(this.buy_model.mon_panier, ouvrage_demande);
	
	        	//Supprimer du stock partiel **MODEL**
	        	this.buy_model.supprimer_ouvrage_stock_partiel(this.buy_model.stock_partiel, ouvrage_demande);
	        	
	        	//Calculer le prix du panier **MODEL**
	        	this.buy_model.calcul_prix_panier(this.buy_model.mon_panier, ouvrage_demande);
	        	
	        	//Supprimer l'ouvrage choisi de la JComboBox **VUE**
	        	this.buy_panel.suppr_ouvrage_demande_JComboBox(ouvrage_demande);
	        	
	        	//Mettre à jour le prix du panier **VUE**
	        	this.buy_panel.modif_vue_prix_panier(this.buy_model.mon_panier.prix_panier);
	        	
	        	//Mettre à jour le contenu du panier **VUE**
	        	this.buy_panel.afficher_contenu_panier(this.buy_model.mon_panier);
        	}
        }
        
        /** Clique sur Acheter **/
        if (e.getSource() == this.buy_panel.getButton_acheter()) {
        	// On vérifie que le panier n'est pas vide
        	if (this.buy_model.mon_panier.List_ouvrage.size() > 0)
        	{
	        	//demande nom client
	        	String nom_client = this.buy_panel.affiche_demande_nom_client();
	        	
	        	if (nom_client != null) // Au cas ou l'utilisateur annule son choix d'acheter le panier
	        	{
	        		/**ENREGISTREMENT DE L'ACHAT **/
	        		//recupère client en fonction du nom client
	        		Client acheteur = this.buy_model.get_client(nom_client);
	        	
	        		//Enregistrement dans le journal
	            	this.buy_model.ajout_vente_journal(this.buy_model.journal_vente, this.buy_model.mon_panier, acheteur);
	            	
	            	//Enregistrement dans le stock (suppression)
	            	this.buy_model.modif_stock(this.buy_model.stock_librairie, this.buy_model.mon_panier);
	            	
	            	//Enregistrement des points du clients et livre gratuit pour plus de 1000 points
	            	Ouvrage ouvrage_gratuit= null;
	            	ouvrage_gratuit = this.buy_model.ajout_point_client(this.buy_model.mon_panier, acheteur,this.buy_model.mes_clients);
	            	
	            	//affichage du prix final 
	            	this.buy_panel.affiche_prix_final(ouvrage_gratuit,this.buy_model.mon_panier);
	            	
	            	//Fermeture de la fenetre d'achat
	            	this.buy_panel.fermer_fenetre_achat();
	        	}
        	}else { // le panier est vide
        		
        		this.buy_panel.affiche_panier_vide();
        	}
        	
        }
	}
}

package Ma.Librairie.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Ma.Librairie.modele.Loyalty_model;
import Ma.Librairie.vue.Loyalty_Panel;

/**
 * 
 * @author Richard CLOOS
 *
 */

/*
 * LOYALTY_CONTROLER permet les intéractions entre la vue LOYALTY_PANEL et le modèle LOYALTY_MODEL
 * sur la fenêtre de fidélité LOYALTY_WINDOW
 * 
 */
public class Loyalty_Controler implements ActionListener{
	Loyalty_Panel loyalty_panel;
	Loyalty_model loyalty_model;
	
	/** CONSTRUCTEUR **/
	public Loyalty_Controler(Loyalty_Panel Loyalty_Panel, Loyalty_model Loyalty_Model) 
	{
        this.loyalty_panel = Loyalty_Panel;
        this.loyalty_model = Loyalty_Model;
    }
	
	public void actionPerformed(ActionEvent e) {
		/** Afficher les infos du client choisi**/
        if (e.getSource() == this.loyalty_panel.getlisteclient_box()) {
            String nom_client_choisi = this.loyalty_panel.getlisteclient_box().getSelectedItem().toString();
	    	int num_client_choisi = loyalty_model.get_num_client(nom_client_choisi);
	    	//affiche les infos clients
	    	loyalty_panel.updateinfoclient(this.loyalty_model.mes_client.Clients_librarie.get(num_client_choisi-1));
	    	
	    	//affiche l'historique d'achat
	    	this.loyalty_panel.updatehistorique(this.loyalty_model.mes_client.Clients_librarie.get(num_client_choisi-1), this.loyalty_model.journal);
        }

    }
}

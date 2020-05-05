package Ma.Librairie.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Ma.Librairie.modele.Main_model;
import Ma.Librairie.vue.Buy_Window;
import Ma.Librairie.vue.Loyalty_Window;
import Ma.Librairie.vue.Main_Panel;
import Ma.Librairie.vue.Sales_Window;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * MAIN_CONTROLER permet les intéractions entre la vue MAIN_PANEL et le modèle MAIN_MODEL
 * sur la fenêtre du menu principal MAIN_WINDOW
 * 
 */

public class Main_Controler implements ActionListener{
	Main_Panel main_panel;
	Main_model main_model;
	
	/** CONSTRUCTEUR **/
	public Main_Controler(Main_Panel Main_Panel, Main_model Main_Model) 
	{
        this.main_panel = Main_Panel;
        this.main_model = Main_Model;
    }
	
	
	public void actionPerformed(ActionEvent e) {
		/** Menu Achat **/
        if (e.getSource() == this.main_panel.getbuy_button()) {
        	//Vérifier que le stock n'est pas vide
        	if (main_model.stock_librairie.Stock_ouvrage.size() != 0) {
        		new Buy_Window(main_model.mes_clients, main_model.stock_librairie, main_model.journal_ventes);
        	}else {
        		//Afficher que le stock est vide
        		this.main_panel.affiche_stock_vide();
        	}
        }
        
        /** Menu Compte Fidelite **/
        if (e.getSource() == this.main_panel.getloyalty_button()) {
            new Loyalty_Window(main_model.mes_clients,main_model.journal_ventes);
        }
        
        /** Menu  Journal de vente**/
        if (e.getSource() == this.main_panel.getpurchase_button()) {
            new Sales_Window(main_model.journal_ventes);
        }

    }
}

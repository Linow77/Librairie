package Ma.Librairie.vue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Ma.Librairie.controleur.Main_Controler;
import Ma.Librairie.modele.Main_model;
import Ma.Librairie.objets.Clients;
import Ma.Librairie.objets.Journal_ventes;
import Ma.Librairie.objets.Stock;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * MAIN_PANEL représentes les éléments de la JFrame MAIN_WINDOW, c'est à dire la VUE
 * Il permet seulement la création de nouvelles fenêtres (et donc éléments visuels)
 * mais il instancie les éléments fondamentaux (Stock, Clients, et journal) qui eux doivent
 * être transmis aux class qui les modifieront
 * Ainsi, le modèle est dépourvu de fonction mais il donne accès aux éléments fondammentaux
 * Le controler permet la création des fenêtres en fonction du choix de l'utilisateur (boutons)
 */

public class Main_Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2641524781298297164L;
	
	Main_model main_model;
	
	//creation Elements visuels
	private JButton buy_button;
	private JButton loyalty_button;
	private JButton purchase_history_button;
	private JLabel gestion;
	
	//Récupère les objets
	private Clients mes_clients;
	private Stock stock_librairie; //Stock final
	private Journal_ventes journal_ventes;
		
	/** CONSTRUCTEUR **/
	public Main_Panel()
	{
		//Création controleur
		Main_Controler controler;
		
		//Instanciation des objets
		mes_clients = new Clients();
		stock_librairie = new Stock();
		journal_ventes = new Journal_ventes();
		
		//Je récupère dans mon modele, tous les objets qui risquent de changer 
		this.main_model = new Main_model(mes_clients, stock_librairie, journal_ventes);
		//Envoie de la vue et du modele au controleur
		controler = new Main_Controler(this,this.main_model);
		
		/** Texte Gestion de la librairie **/		
		gestion = new JLabel("Gestion de la librairie",JLabel.CENTER);
		
		/** Boutton Menu Proceder à un achat**/
		buy_button = new JButton("Proceder à un achat");
		buy_button.addActionListener(controler);
		
		/** Boutton Menu Consulter les comptes clients**/
		loyalty_button = new JButton("Consulter les comptes clients");
		loyalty_button.addActionListener(controler);
		
		/** Boutton Menu Consulter l'historique d'achat**/
		purchase_history_button = new JButton("Consulter l'historique d'achat");
		purchase_history_button.addActionListener(controler);
		
		
		//Mise en place des composants 
		this.setLayout(null); // positionnement libre
		
		//Positionnement
		gestion.setBounds(90, 5, 220, 30);
		buy_button.setBounds(90, 40, 220, 30);
		loyalty_button.setBounds(90, 75, 220, 30);
		purchase_history_button.setBounds(90, 110, 220, 30);
		
		//Ajout des éléments au Jpanel
		this.add(gestion);
		this.add(buy_button);
		this.add(loyalty_button);
		this.add(purchase_history_button);
	}
	
    /** ACTIONS LISTENER **/
    public JButton getbuy_button() {
        return this.buy_button;
    }
    
    public JButton getloyalty_button() {
    	return this.loyalty_button;
    }
    
    public JButton getpurchase_button() {
    	return this.purchase_history_button;
    }
    
    /** FONCTIONS VUES **/
  //Affiche un message d'erreur si on clique sur le menut achat et que le stock est vide
  	public void affiche_stock_vide()
  	{
  		JOptionPane.showMessageDialog(null, "Le stock de la librairie est vide !", "Stock vide", JOptionPane.ERROR_MESSAGE);
  		
  	}
}

package Ma.Librairie.vue;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Ma.Librairie.controleur.Loyalty_Controler;
import Ma.Librairie.modele.Loyalty_model;
import Ma.Librairie.objets.BD;
import Ma.Librairie.objets.Client;
import Ma.Librairie.objets.Clients;
import Ma.Librairie.objets.Journal_ventes;
import Ma.Librairie.objets.Livre;
import Ma.Librairie.objets.Ouvrage;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * LOYALTY_PANEL représente les éléments de la JFrame LOYALTY_WINDOW, c'est à dire la VUE
 * Il permet de consulter les points fidélités des clients
 * 
 * Le controleur permet l'affichage des points fidélités des clients en fonction du choix utilisateur
 */
public class Loyalty_Panel extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4821526407327224506L;
	
	//Créer le modele
	private Loyalty_model loyalty_model;
	
	//creation éléments visuels
    private JComboBox<Object> liste_client;
    private JLabel info_label;
    private JLabel info_label2;
    private JLabel info_label3;
    private JLabel titre_historique;
    private JTextArea affiche_historique;
    private JScrollPane text_asc;
    
	/** CONSTRUCTEUR **/
	public Loyalty_Panel(Clients mes_clients, Journal_ventes journal)
	{

		//Création controleur
		Loyalty_Controler controler;
		
		//Je récupère tous les objets qui risquent de changer dans mon model
		this.loyalty_model = new Loyalty_model(mes_clients,journal);
		
		//Envoie de la vue et du modele au controleur
		controler = new Loyalty_Controler(this,this.loyalty_model);
		
		/** Barre défilante client **/
		//liste des clients dans la barre defilante
		ArrayList<String> Nom_Client = new ArrayList<String>();
		for(int i = 0; i <5; i++)
		{
			Nom_Client.add(mes_clients.Clients_librarie.get(i).Nom);
		}	
		
		liste_client = new JComboBox<Object>(Nom_Client.toArray());
		liste_client.addActionListener(controler);
		
		
		/** LABELS  INFOS CLIENTS **/
		info_label = new JLabel("",JLabel.CENTER);
		info_label2 = new JLabel("",JLabel.CENTER);
		info_label3 = new JLabel("",JLabel.CENTER);
		//Au début on affiche les infos du premier client de la liste déroulante (car c'est celui affiché de base)
		updateinfoclient(mes_clients.Clients_librarie.get(0));
		
		
		/** JTextArea historique d'achat **/
		titre_historique = new JLabel("Historique d'achat de la journée :",JLabel.CENTER);
		affiche_historique = new JTextArea(4,10);
		affiche_historique.setEditable(false);
		text_asc = new JScrollPane(affiche_historique);
		
		//Au début on affiche l'historique du premier client de la liste déroulante
		updatehistorique(mes_clients.Clients_librarie.get(0),journal);
		
		//Mise en place des composants 
		this.setLayout(null); //Placement manuel
		
		//Positionnement
		liste_client.setBounds(260, 10, 80, 30);
		info_label.setBounds(10, 40, 580, 30);
		info_label2.setBounds(10, 70, 580, 30);
		info_label3.setBounds(10, 100, 580, 30);
		titre_historique.setBounds(10, 120, 580, 30);
		text_asc.setBounds(20, 150, 550, 150);
		
		//Ajout des éléments au Jpanel
		this.add(liste_client);
		this.add(info_label);
		this.add(info_label2);
		this.add(info_label3);
		this.add(titre_historique);
		this.add(text_asc);

	}
	
	/** ACTIONS LISTENER **/
	public void setBookshop(Loyalty_model b) {
        this.loyalty_model = b;
    }
    
    public JComboBox<Object> getlisteclient_box() {
        return this.liste_client;
    }
    
    public JTextArea getTextArea_historique() {
    	return this.affiche_historique;
    }
    
    /** FONCTIONS VUES **/
    //Actualise les infos client du client choisi
    public void updateinfoclient(Client client_choisi) {
    	Client c = client_choisi;
    	info_label.setText("Num Compte: "+c.Num_compte+ " - Nom: "+c.Nom + " - Prénom: "+c.Prenom + " - Date naissance: " +c.Date_naissance);
    	info_label2.setText("Mail: "+c.mail + " - Adresse: "+c.adresse + " - Numéro: "+c.numero);
    	info_label3.setText("Points fidélités: "+c.nb_points);
    }
    
    // Actualise l'affiche de l'historique d'achat du client choisi
    public void updatehistorique(Client client_choisi, Journal_ventes journal)
    {
    	//historique total est l'addition de toutes les vente_client du client choisi
    	String historique_total_string ="";
    				
    	//vente_client représente l'affichage d'une vente du journal avec le client associé
    	String vente_client_string ="";
    			
    	int nb_vente = journal.Journal.size();
    	//Effectuer un saut de ligne dans une string
    	String Newligne=System.getProperty("line.separator");
    					
    	for(int i=0;i<nb_vente;i++)
    	{
    		Ouvrage ouvrage = journal.Journal.get(i).ouvrage_vendu;
    		Client acheteur = journal.Journal.get(i).acheteur;
    		if (acheteur.Num_compte == client_choisi.Num_compte) //On affiche le client 1 de base
    		{
    					
    			//On récupère les attributs des classes filles de Ouvrage
    			if (ouvrage instanceof Livre)
    			{
    				Livre livre = (Livre) ouvrage;
    				//Création de la string d'une vente avec le client associé
    				vente_client_string = "Acheteur: "+acheteur.Nom+" - Compte: "+acheteur.Num_compte+"- Achat: "+livre.Titre+" " + livre.Editeur+" " + livre.Annee_publication+" " + livre.Auteur+" [Prix: "+ ouvrage.Prix_vente+"€]";
    				
    			}else {
    				BD bd = (BD) ouvrage;
    				vente_client_string = "Acheteur: "+acheteur.Nom+" - Compte: "+acheteur.Num_compte+"- Achat: "+bd.Titre+" " + bd.Editeur+" " + bd.Annee_publication+" " + bd.Dessinateur+" " + bd.Scenariste+" [Prix: "+ ouvrage.Prix_vente+"€]";
    			}
    		
    			//Ajout de la vente au string journal
    			historique_total_string = historique_total_string+vente_client_string+Newligne;
    		}
    	}
    	
    	//aucun achat effectué
    	if (historique_total_string.contentEquals(""))
    	{
    		historique_total_string = "Aucun achat";
    	}
    	affiche_historique.setText(historique_total_string);
    	
    }
    
}

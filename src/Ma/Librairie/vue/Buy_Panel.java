package Ma.Librairie.vue;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Ma.Librairie.controleur.Buy_Controler;
import Ma.Librairie.modele.Buy_model;
import Ma.Librairie.objets.BD;
import Ma.Librairie.objets.Clients;
import Ma.Librairie.objets.Journal_ventes;
import Ma.Librairie.objets.Livre;
import Ma.Librairie.objets.Ouvrage;
import Ma.Librairie.objets.Panier;
import Ma.Librairie.objets.Stock;
/**
 * 
 * @author Richard CLOOS
 *
 */

public class Buy_Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4398241528610353275L;

	
	//Créer le modele
	private Buy_model buy_model;
	
	//Création des objets seulement lié a la page ACHAT
	public Panier mon_panier;
	public Stock stock_partiel;
	/** Le stock_partiel est une copie du stock, mais celui changera au fur et à mesure 
	 * que le client modifiera son panier, ainsi il ne verra plus en possibilité d'achat 
	 * un livre qui est déja dans son panier **/
	
	//Creation éléments visuels
	private JLabel choisir;
	private JComboBox<Object> liste_ouvrage_stock;
	private JButton ajouter;
	private JLabel prix_panier;
	private JButton acheter;
	private JLabel titre_panier;
	private JTextArea affiche_panier;
	private JScrollPane text_asc;
	
	private JFrame fenetre;
	
	/** CONSTRUCTEUR **/
	@SuppressWarnings("unchecked")
	public Buy_Panel(JFrame fenetre,Clients mes_clients, Stock stock_librairie, Journal_ventes journal_ventes)
	{
				
		//Création controleur
		Buy_Controler controler;
		
		//Instanciation des objets 
		mon_panier = new Panier();
		stock_partiel = new Stock();
		
		//On récupère le stock_librairie dans le stock_partiel
		stock_partiel.Stock_ouvrage = (ArrayList<Ouvrage>) stock_librairie.Stock_ouvrage.clone();
		stock_partiel.nb_ouvrages = stock_partiel.Stock_ouvrage.size();
		
		//Je récupère dans mon modele, tous les objets qui risquent de changer 
		this.buy_model = new Buy_model(mes_clients,mon_panier,stock_librairie,stock_partiel,journal_ventes);
		this.fenetre = fenetre; // pour pouvoir fermer la fenetre
		//Envoie de la vue et du modele au controleur
		controler = new Buy_Controler(this,this.buy_model);
		
		
		/** LES ELEMENTS QUI SUIVENT SONT INITIALISES EN MEME TEMPS QUE LA FENETRE **/
		/** Texte choisir ouvrage **/
		choisir = new JLabel("Choisir un ouvrage: ");
		
		/** Barre défilante choisir ouvrage **/
		//liste des ouvrages dans la barre defilante
		ArrayList<String> Ouvrage = new ArrayList<String>();
		
		//Remplissage de la liste Ouvrage
		for(int i = 0; i < stock_partiel.nb_ouvrages ; i++)
		{
			Ouvrage ouvrage = stock_partiel.Stock_ouvrage.get(i);
			if(ouvrage != null)
			{
				if (ouvrage instanceof Livre)
				{
					//recup livre depuis ouvrage (donne accès aux attributs de livre)
					Livre livre = (Livre) ouvrage;
					Ouvrage.add(livre.Titre+" " + livre.Editeur+" " + livre.Annee_publication+" " + livre.Auteur+" [Prix: "+ ouvrage.Prix_vente+"€]");
			
				}else {
					//recup bd depuis ouvrage
					BD bd = (BD) ouvrage;
					Ouvrage.add(bd.Titre+" " + bd.Editeur+" " + bd.Annee_publication+" " + bd.Dessinateur+" " + bd.Scenariste+" [Prix: "+ ouvrage.Prix_vente+"€]");
					
				}
			}
		}
		
		liste_ouvrage_stock = new JComboBox<Object>(Ouvrage.toArray());
		liste_ouvrage_stock.addActionListener(controler);
		
		/** Texte Prix panier **/
		prix_panier = new JLabel("Prix du panier: "+mon_panier.prix_panier+"€",JLabel.CENTER);
		
		/** Boutton ajouter **/
		ajouter = new JButton("Ajouter au panier");
		ajouter.addActionListener(controler);
		
		/** Boutton acheter **/
		acheter = new JButton("Acheter le panier");
		acheter.addActionListener(controler);
		
		/** Afficher le panier **/
		titre_panier = new JLabel("Contenu de votre Panier :",JLabel.CENTER);
		affiche_panier = new JTextArea(4,10);
		affiche_panier.setEditable(false);
		text_asc = new JScrollPane(affiche_panier);
		
		
		//Mise en place des composants 
		this.setLayout(null); // positionnement libre

		//Positionnement
		choisir.setBounds(10, 10, 130, 30);
		liste_ouvrage_stock.setBounds(160, 10, 300, 30);
		prix_panier.setBounds(150, 40, 150, 30);
		ajouter.setBounds(10, 40, 140, 30);
		acheter.setBounds(10, 80, 140, 30);
		titre_panier.setBounds(10, 120, 480, 30);
		text_asc.setBounds(100, 150, 300, 150);
		
		//Ajout des éléments au Jpanel
		this.add(liste_ouvrage_stock);
		this.add(choisir);
		this.add(prix_panier);
		this.add(ajouter);
		this.add(acheter);
		this.add(titre_panier);
		this.add(text_asc);
		
		
	}
	 /** ACTIONS LISTENER **/
	public JComboBox<?> getlisteouvrage_box() {
        return this.liste_ouvrage_stock;
    }
	
	public JButton getButton_ajouter() {
		return this.ajouter;
	}
	
	public JLabel getLabel_prix_panier() {
		return this.prix_panier;
	}
	
	public JButton getButton_acheter() {
		return this.acheter;
	}
	
	public JTextArea getTextArea_panier() {
		return this.affiche_panier;
	}
	
	
	/** FONCTIONS VUE **/
	//Récupère le titre du livre choisi dans la list déroulante
	public String recup_titre_ouvrage_list ()
	{
		String titre="";
		if (this.getlisteouvrage_box().getSelectedItem() != null)
		{
			titre = this.getlisteouvrage_box().getSelectedItem().toString();
			titre = titre.substring(0, titre.indexOf(" "));
		}else {
			titre="ERROR";
		}
    	
		return titre;
	}
	
	//Supprime le livre ajouté au panier, de la liste déroulante
	public void suppr_ouvrage_demande_JComboBox(Ouvrage ouvrage_demande)
	{
		String ouvrage_string=null;
		if (ouvrage_demande instanceof Livre)
		{
			Livre livre = (Livre) ouvrage_demande;
			ouvrage_string = livre.Titre+" " + livre.Editeur+" " + livre.Annee_publication+" " + livre.Auteur+" [Prix: "+ ouvrage_demande.Prix_vente+"€]";
			
		}else {
			BD bd = (BD) ouvrage_demande;
			ouvrage_string = bd.Titre+" " + bd.Editeur+" " + bd.Annee_publication+" " + bd.Dessinateur+" " + bd.Scenariste+" [Prix: "+ ouvrage_demande.Prix_vente+"€]";
		}
		this.getlisteouvrage_box().removeItem(ouvrage_string);
	}
	
	//Actualise le prix du panier
	public void modif_vue_prix_panier(float prix_panier)
	{
		String prix_panier_string = "Prix du panier: "+prix_panier+"€";
		this.getLabel_prix_panier().setText(prix_panier_string);
	}
	
	//Récupère le nom du client
	public String affiche_demande_nom_client()
	{
		/** Fenetre demande de nom client **/
		//Liste des noms des clients 
		String[] clients = new String[5];
		for(int i=0; i<5;i++)
		{
			clients[i] = this.buy_model.mes_clients.Clients_librarie.get(i).Nom;
		}
		

	    String nom_client = (String)JOptionPane.showInputDialog(null, 
	      "Veuillez indiquer votre nom client",
	      "Demande nom client",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      clients,
	      clients[0]);
	    
	    return nom_client;
	}
	
	//Affiche le prix final (après réduction)
	public void affiche_prix_final(Ouvrage ouvrage_gratuit, Panier panier_acheteur)
	{
		String message_achat;
		if (ouvrage_gratuit == null) //affichage prix normal
		{
			message_achat ="Le prix de votre panier est: " + panier_acheteur.prix_panier+"€"; 
			
			
		}else { //affichage prix avec réduction
			message_achat = "Grâce à vos 1000 points cumulés, l'ouvrage "+ouvrage_gratuit.Titre+" vous est offert. Le prix de votre panier est: "+ panier_acheteur.prix_panier+"€";
			
		}
		JOptionPane.showMessageDialog(null, message_achat, "Finalisation achat", JOptionPane.INFORMATION_MESSAGE);
		
	}
	// Actualise l'affichage du panier du client
	public void afficher_contenu_panier(Panier panier_acheteur)
	{
		String panier_total ="";
		int longueur_panier = panier_acheteur.List_ouvrage.size();
		String ouvrage_string=null;
		String Newligne=System.getProperty("line.separator");
		for(int i=0; i<longueur_panier;i++)
		{
			Ouvrage ouvrage = panier_acheteur.List_ouvrage.get(i);
			
			if (ouvrage instanceof Livre)
			{
				Livre livre = (Livre) ouvrage;
				ouvrage_string = livre.Titre+" " + livre.Editeur+" " + livre.Annee_publication+" " + livre.Auteur+" [Prix: "+ ouvrage.Prix_vente+"€]";
				
			}else {
				BD bd = (BD) ouvrage;
				ouvrage_string = bd.Titre+" " + bd.Editeur+" " + bd.Annee_publication+" " + bd.Dessinateur+" " + bd.Scenariste+" [Prix: "+ ouvrage.Prix_vente+"€]";
			}
			panier_total = panier_total+ouvrage_string+Newligne;
		}
		
		this.getTextArea_panier().setText(panier_total);
	}
	
	//Affiche un message d'erreur en cas d'achat alors que le panie est vide
	public void affiche_panier_vide()
	{
		JOptionPane.showMessageDialog(null, "Votre panier est vide ! ", "Panier vide", JOptionPane.ERROR_MESSAGE);
		
	}
	
	//Affiche un message d'erreur en cas d'achat alors que le panie est vide
	public void affiche_stock_vide()
	{
		JOptionPane.showMessageDialog(null, "Il n'y a plus d'ouvrage en stock !", "Stock vide", JOptionPane.ERROR_MESSAGE);
		
	}
		
	//Ferme la fenetre d'achat à la fin d'un achat
	public void fermer_fenetre_achat()
	{
		fenetre.dispose();
	}
}

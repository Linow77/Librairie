package Ma.Librairie.vue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import Ma.Librairie.objets.BD;
import Ma.Librairie.objets.Client;
import Ma.Librairie.objets.Journal_ventes;
import Ma.Librairie.objets.Livre;
import Ma.Librairie.objets.Ouvrage;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * SALES_PANEL représente les éléments de la JFrame SALES_WINDOW, c'est à dire la VUE
 * il est dépourvu de controleur et de modèle puisqu'il n'affiche qu'une fenêtre statique
 * 
 */

public class Sales_Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8566969736940355536L;

	
	//Creation élements visuels de la fenetre
	JLabel titre_journal;
	JTextArea affiche_journal;
	JScrollPane text_asc;
	
	/** CONSTRUCTEUR **/
	public Sales_Panel(Journal_ventes journal)
	{
		
		titre_journal = new JLabel("Ventes d'aujourd'hui :",JLabel.CENTER);
		affiche_journal = new JTextArea(4,10);
		affiche_journal.setEditable(false);
		text_asc = new JScrollPane(affiche_journal);
		
		//journal total est l'addition de toutes les vente_client
		String journal_total_string ="";
		
		//vente_client représente l'affichage d'une vente du journal avec le client associé
		String vente_client_string ="";
		
		int nb_vente = journal.Journal.size();
		//Effectuer un saut de ligne dans une string
		String Newligne=System.getProperty("line.separator");
		
		/** On cherche pour toutes les vente, l'ouvrage qui corresponds ainsi que son acheteur **/
		for(int i=0;i<nb_vente;i++)
		{
			Ouvrage ouvrage = journal.Journal.get(i).ouvrage_vendu;
			Client acheteur = journal.Journal.get(i).acheteur;
			
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
			journal_total_string = journal_total_string+vente_client_string+Newligne;
		}
		
		//Insertion du texte final dans le JTextArea
		affiche_journal.setText(journal_total_string);
		
		
		//Mise en place de l'affichafe
		this.setLayout(null); //Affichage manuel des éléments
		
		//Positionnement
		titre_journal.setBounds(10, 10, 580, 30);
		text_asc.setBounds(20, 50, 550, 150);
		
		//Ajout des éléments au Jpanel
		this.add(titre_journal);
		this.add(text_asc);
		
	}
}

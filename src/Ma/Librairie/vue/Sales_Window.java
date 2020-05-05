package Ma.Librairie.vue;

import javax.swing.JFrame;

import Ma.Librairie.objets.Journal_ventes;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * SALES_WINDOW permet la création de la fenetre du menu du journal de vente 
 * 
 */

public class Sales_Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6640268543151576511L;


	
	/** CONSTRUCTEUR **/
	public Sales_Window (Journal_ventes journal) 
	{
		this.setTitle("Menu journal de ventes");
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//creation du panel du menu
		Sales_Panel sales_panel;
		sales_panel = new Sales_Panel(journal);
		
		this.setContentPane(sales_panel);
		//Afficher la fenêtre
		this.setVisible(true);
	}
}

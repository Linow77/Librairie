package Ma.Librairie.vue;

import javax.swing.JFrame;

import Ma.Librairie.objets.Clients;
import Ma.Librairie.objets.Journal_ventes;
import Ma.Librairie.objets.Stock;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * BUY_WINDOW permet la création de la fenetre du menu d'achat
 * 
 */

public class Buy_Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7983636548742699428L;

	public Buy_Window (Clients mes_clients, Stock stock_librairie, Journal_ventes journal_ventes)
	{
		this.setTitle("Menu Achat");
		this.setSize(500, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//creation du panel du menu
		Buy_Panel buy_panel;
		buy_panel = new Buy_Panel(this,mes_clients,stock_librairie, journal_ventes);
		
		this.setContentPane(buy_panel);
		this.setVisible(true);
	}
}

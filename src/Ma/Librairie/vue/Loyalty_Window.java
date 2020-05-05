package Ma.Librairie.vue;

import javax.swing.JFrame;

import Ma.Librairie.objets.Clients;
import Ma.Librairie.objets.Journal_ventes;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * LOYALTY_WINDOW permet la cr�ation de la fenetre du menu fid�lit�
 * 
 */
public class Loyalty_Window extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4450050738188436973L;
	
	
	
	/** CONSTRUCTEUR **/
	public Loyalty_Window (Clients mes_clients, Journal_ventes journal) 
	{
		this.setTitle("Comptes Fid�lit�");
		this.setSize(600, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//creation du panel du menu
		Loyalty_Panel loyalty_panel;
		loyalty_panel = new Loyalty_Panel(mes_clients, journal);
		
		this.setContentPane(loyalty_panel);
		this.setVisible(true);
	}
	
}

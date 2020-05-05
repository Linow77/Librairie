package Ma.Librairie.vue;

import javax.swing.JFrame;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * MAIN_WINDOW permet la création de la fenetre du menu principal
 * 
 */

public class Main_Window extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4450050738188436973L;
	
	/** CONSTRUCTEUR **/
	public Main_Window () {
		this.setTitle("Menu Librairie");
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//creation du panel du menu
		Main_Panel main_panel;
		main_panel = new Main_Panel();
		
		this.setContentPane(main_panel);
		this.setVisible(true);
	}
	
}

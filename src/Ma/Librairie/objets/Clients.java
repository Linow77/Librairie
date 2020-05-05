package Ma.Librairie.objets;

import java.util.ArrayList;

import Ma.Librairie.objets.Client;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * Clients est une liste regroupant tous les clients de la librairie
 * CLIENT ROSES OU ZATIN PERMET DE TESTER L'OUVRAGE GRATUIT AVEC 1000 POINTS FIDELITES
 */
public class Clients {
	public ArrayList<Client> Clients_librarie = new ArrayList<Client>();
	
	
	public Clients() 
	{
				//Décris ainsi : NumCompte, Nom, Prenom, Date_naissance, 			Mail, 				Adresse, 		Numero, Points_fidélités //
		Client Client1 = new Client(1, "Ganay", "Albert", "28-12-1964", "albert.ganay@hotmail.fr", "36 avenue des Apis","0685791463", 0);
		Client Client2 = new Client(2, "Atic", "Alice", "15-08-1998", "alice.atic@hotmail.fr", "12 rue des montages","0606749231", 50);
		Client Client3 = new Client(3, "Roses", "Julien", "24-02-1954", "julien.roses@hotmail.fr", "4 allée des marchands","0678413694", 900);
		Client Client4 = new Client(4, "Demorc", "Elise", "04-05-1974", "elise.demorc@hotmail.fr", "27 avenue des lacs","0684675193", 550);
		Client Client5 = new Client(5, "Zatin", "Guillaume", "26-11-1946", "guillaume.zatin@hotmail.fr", "14 chemins des pelicans","0624671359", 950);
		
		this.Clients_librarie.add(Client1);
		this.Clients_librarie.add(Client2);
		this.Clients_librarie.add(Client3);
		this.Clients_librarie.add(Client4);
		this.Clients_librarie.add(Client5);
		
	}
	
}

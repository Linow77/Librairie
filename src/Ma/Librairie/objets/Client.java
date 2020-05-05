package Ma.Librairie.objets;
/**
 * 
 * @author Richard CLOOS
 *
 */
/*
 * Un client regroupe toutes les infos d'un client de la librairie
 * 
 */
public class Client {
	public int Num_compte;
	public String Nom;
	public String Prenom;
	public String Date_naissance;
	public String mail;
	public String adresse;
	public String numero;
	public int nb_points;
	
	
	public Client(int num, String Nom, String Prenom, String Date, String mail, String adresse, String numero, int nb_points)
	{
		this.Num_compte = num;
		this.Nom = Nom;
		this.Prenom = Prenom;
		this.Date_naissance = Date;
		this.mail = mail;
		this.adresse = adresse;
		this.numero = numero;
		this.nb_points = nb_points;
	}
}

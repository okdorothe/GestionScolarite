package Model;
/**
 * Classe Enseignant 
 * @author Do Ro The
 *@version 1.0
 */
public class Enseignant {
	private int id;
	private String identifiant;
	private String mdp;
	private String nom;
	private String prenom;
	private String tel;
	private int titre;
	public Enseignant(String ident, String mdp, String nom, String prenom, String tel, int titre) {
	//this.id=id;
	this.identifiant=ident;
	this.mdp=mdp;
	this.nom=nom;
	this.prenom=prenom;
	this.tel=tel;
	this.titre=titre;
		// TODO Auto-generated constructor stub
	}
	/**
	 * getter de l'attribut id
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * setter de l'attribut id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * getter de l'attribut identifiant
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return this.identifiant;
	}
	/**
	 * setter de l'attribut identifiant
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	/**
	 * getter de l'attribut mot de passe 
	 * @return the mdp
	 */
	public String getMdp() {
		return this.mdp;
	}
	/**
	 * setter de l'attribut mot de passe 
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	/**
	 * getter de l'attribut nom
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}
	/**
	 * setter de l'attribut nom
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * getter de l'attribut prenom 
	 * @return the prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}
	/**
	 * setter de l'attribut prenom
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * getter de l'attribut tel
	 * @return the tel
	 */
	public String getTel() {
		return this.tel;
	}
	/**
	 * setter de l'attribut tel
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * getter de l'attribut titre 
	 * @return the titre
	 */
	public int getTitre() {
		return this.titre;
	}
	/**
	 * setter de l'attribut titre
	 * @param titre the titre to set
	 */
	public void setTitre(int titre) {
		this.titre = titre;
	}

}

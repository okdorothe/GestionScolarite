package Model;
/**
 * classe Etudiant
 * @author Do Ro The
 *@version 1.0
 */
public class Etudiant {
	private int id;
	private String identifiant;
	private String mdp;
    private String nom;
    private String prenom;
    private String mail;
    private int idGroupe ;
    private int idFiliere;
    private int idTitre;
	public Etudiant(String ident, String pass, String nom,String prenom, String mail, int g, int T,int F) {
		// TODO Auto-generated constructor stub
		this.setIdentifiant(ident);
		this.setMdp(pass);
		this.nom=nom;
		this.mail=mail;
		this.prenom=prenom;
		this.setIdGroupe(g);
		this.setIdFiliere(F);
		this.setIdTitre(T);
	}

	/**
	 * getter de nom
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
	 * getter de l'attribut mail
	 * @return the mail
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * setter de l'email
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * getter de l'attribut id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * setter de l'attribut id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter de l'attribut idGroupe
	 * @return the idGroupe
	 */
	public int getIdGroupe() {
		return this.idGroupe;
	}

	/**
	 * setter de l'attribut idGroupe
	 * @param idGroupe the idGroupe to set
	 */
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}

	/**
	 * getter de l'attribut idFiliere
	 * @return the idFiliere
	 */
	public int getIdFiliere() {
		return idFiliere;
	}

	/**
	 * setter de l'attribut idFilliere
	 * @param idFiliere the idFiliere to set
	 */
	public void setIdFiliere(int idFiliere) {
		this.idFiliere = idFiliere;
	}

	/**
	 * getter de l'attribut idTitre
	 * @return the idTitre
	 */
	public int getIdTitre() {
		return idTitre;
	}

	/**
	 * setter de l'attribut idTitre 
	 * @param idTitre the idTitre to set
	 */
	public void setIdTitre(int idTitre) {
		this.idTitre = idTitre;
	}

	/**
	 * getter de l'attribut identifiant
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * setter de l'attribut identifiant
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * getter de l'attribut mdp
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * setter de l'attribut mdp
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}

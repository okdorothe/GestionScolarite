/**
 * 
 */
package Model;

/**
 * classe cous
 * @author Do Ro The
 *
 */
public class Cours {
	private int id;
	private String nom;
	private int masse_horaire;

	/**
	 * constructeur de la classe cours
	 */
	public Cours(String nom,int masse_horaire) {
		// TODO Auto-generated constructor stub
		this.nom=nom;
		this.masse_horaire=masse_horaire;
	}

	/**
	 * getter de l'id
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * setter de l'id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * getter de l'attribut masse_horaire
	 * @return the masse_horaire
	 */
	public int getMasse_horaire() {
		return this.masse_horaire;
	}

	/**
	 * setter de l'attribut masse_horaire
	 * @param masse_horaire the masse_horaire to set
	 */
	public void setMasse_horaire(int masse_horaire) {
		this.masse_horaire = masse_horaire;
	}

}

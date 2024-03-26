package Model;
/**
 * classe groupe
 * @author Do Ro The
 *@version 1.0
 */
public class Groupe {
	/**
	 * attribut numero
	 */
private String numero;
/**
 * attribut capacite
 */
private int capacite;
/**
 * constructeur de la classe groupe
 * @param num
 * @param cap
 */
	public Groupe(String num, int cap) {
		this.numero=num;
		this.capacite=cap;
		// TODO Auto-generated constructor stub
	}
	/**
	 * getter de l'attribut numero
	 * @return the numero du groupe
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * setter de l'attribut numero
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * setter de l'attribut capacité
	 * @return the capacite
	 */
	public int getCapacite() {
		return capacite;
	}
	/**
	 * setter de l'attribut capacite
	 * @param capacite the capacite to set
	 */
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

}

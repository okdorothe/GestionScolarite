/**
 * 
 */
package Model;

import java.sql.Date;

/**
 * @version 1.0
 * @author Do Ro The
 * classe Seance 
 */
public class Seance {
private int id_ens_seanc;
private int id_sal_seanc;
private int id_grp_seanc;
private int id_crs_seanc;
private int id_type_seanc;
private int hd;
private int hf;
private Date jour;
private int id_fermer;
private String lien;

	/**
	 * constructeur de la classe seance
	 * @param id1
	 * @param id2
	 * @param id3
	 * @param id4
	 * @param j
	 * @param id5
	 * @param id6
	 * @param id7
	 */
	public Seance(int id1,int id2, int id3, int id4,Date j,int id5,int id6,int id7) {
		this.id_ens_seanc=id1;
		this.id_grp_seanc=id2;
		this.id_crs_seanc=id3;
		this.id_sal_seanc=id4;
		this.jour = j;
		this.hd=id5;
		this.hf=id6;
		this.id_type_seanc=id7;
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id_ens_seanc
	 */
	public int getId_ens_seanc() {
		return this.id_ens_seanc;
	}

	/**
	 * @param id_ens_seanc the id_ens_seanc to set
	 */
	public void setId_ens_seanc(int id_ens_seanc) {
		this.id_ens_seanc = id_ens_seanc;
	}

	/**
	 * @return the id_sal_seanc
	 */
	public int getId_sal_seanc() {
		return this.id_sal_seanc;
	}

	/**
	 * @param id_sal_seanc the id_sal_seanc to set
	 */
	public void setId_sal_seanc(int id_sal_seanc) {
		this.id_sal_seanc = id_sal_seanc;
	}

	/**
	 * @return the id_crs_seanc
	 */
	public int getId_crs_seanc() {
		return this.id_crs_seanc;
	}

	/**
	 * @param id_crs_seanc the id_crs_seanc to set
	 */
	public void setId_crs_seanc(int id_crs_seanc) {
		this.id_crs_seanc = id_crs_seanc;
	}

	/**
	 * @return the id_grp_seanc
	 */
	public int getId_grp_seanc() {
		return this.id_grp_seanc;
	}

	/**
	 * @param id_grp_seanc the id_grp_seanc to set
	 */
	public void setId_grp_seanc(int id_grp_seanc) {
		this.id_grp_seanc = id_grp_seanc;
	}

	/**
	 * @return the hd
	 */
	public int getHd() {
		return this.hd;
	}

	/**
	 * @param hd the hd to set
	 */
	public void setHd(int hd) {
		this.hd = hd;
	}

	/**
	 * @return the hf
	 */
	public int getHf() {
		return this.hf;
	}

	/**
	 * @param hf the hf to set
	 */
	public void setHf(int hf) {
		this.hf = hf;
	}

	/**
	 * @return the jour
	 */
	public Date getJour() {
		return this.jour;
	}

	/**
	 * @param jour the jour to set
	 */
	public void setJour(Date jour) {
		this.jour = jour;
	}

	/**
	 * @return the id_fermer
	 */
	public int getId_fermer() {
		return this.id_fermer;
	}

	/**
	 * @param id_fermer the id_fermer to set
	 */
	public void setId_fermer(int id_fermer) {
		this.id_fermer = id_fermer;
	}

	/**
	 * @return the lien
	 */
	public String getLien() {
		return this.lien;
	}

	/**
	 * @param lien the lien to set
	 */
	public void setLien(String lien) {
		this.lien = lien;
	}

	/**
	 * @return the id_type_seanc
	 */
	public int getId_type_seanc() {
		return this.id_type_seanc;
	}

	/**
	 * @param id_type_seanc the id_type_seanc to set
	 */
	public void setId_type_seanc(int id_type_seanc) {
		this.id_type_seanc = id_type_seanc;
	}

}

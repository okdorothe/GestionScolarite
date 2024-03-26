package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * classe groupe pour gerer le groupe des etudiants
 * @author Do Ro The
 *
 */
public class GroupeDAO extends ConnectionDAO {
	private String numero;
/**
 * constructeur de la classe groupe
 */
	public GroupeDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
/**
 * recuperer le l'id dun groupe à partir de son numero
 * @param numero
 * @return l'id du groupe
 */
	public int recp(String numero) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		int rep = 0;
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("Select id_groupe from groupe where numero = ?");
		    ps.setString(1, numero);
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   rep = rs.getInt("id_groupe");
			   
		   }
		 

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de fournisseur existe déjà. Ajout impossible !");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return rep;
			
	}
	/**
	 * permet d'avoir la liste des groupe
	 * @return
	 */
	public ArrayList<String> listGr(){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		ArrayList<String> list = new ArrayList<>();
		list.add("");
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("Select numero from groupe");
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   list.add(rs.getString("numero"));
		   }
		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de fournisseur existe déjà. Ajout impossible !");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		
		return list;
		
	}
	/**
	 * permet d'avoir la liste des etudiants d'un groupe
	 * @param id
	 * @return
	 */
	public ResultSet listEtu(int id){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		try {
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("select identifiant, nom, prenom from etudiants where id_groupe_etu = ?");
			ps.setInt(1, id);
			// Execution de la requete
		   rst=ps.executeQuery();
		} catch (Exception e) {
			
				e.printStackTrace();
		}
		
		return rst;
		
	}
	/**
	 * permet d'avoir le nombre d'etudiant dans un groupe
	 * @param id
	 * @return
	 */
	public int nbrEtu(int id){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		int nb = 0;
		try {
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("select count(*) as nbre from etudiants where id_groupe_etu = ?");
			ps.setInt(1, id);
			// Execution de la requete
			rst=ps.executeQuery();
		   while(rst.next()) {
			   nb=rst.getInt("nbre");
		   }
		} catch (Exception e) {
			
				e.printStackTrace();
		}
		
		return nb;
		
	}
	/**
	 * permet d'avoir la capacite d'un groupe à l'aide de son id 
	 * @param id
	 * @return
	 */
	public int cap(int id){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		int nb = 0;
		try {
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("select capacite from groupe where id_groupe = ?");
			ps.setInt(1, id);
			// Execution de la requete
			rst=ps.executeQuery();
		   while(rst.next()) {
			   nb=rst.getInt("capacite");
		   }
		} catch (Exception e) {
			
				e.printStackTrace();
		}
		
		return nb;
		
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}


	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

}

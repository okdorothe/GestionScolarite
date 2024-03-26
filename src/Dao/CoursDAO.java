/**
 * 
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Cours;
import Model.Etudiant;

/**
 * classe cours pour interagir avec la table cours de la base de données
 * @author Do Ro The
 *@version 1.0
 */
public class CoursDAO extends ConnectionDAO{

	/**
	 * constructeur de le classe cours
	 */
	public CoursDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Ajouter un cours à la base de données
	 * @param crs
	 */
	public void add(Cours crs) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			String generatedColumns[] = {"id_cours"};
			ps = con.prepareStatement("INSERT INTO cours VALUES(cours_seq.nextval, ?, ?)",generatedColumns);
			ps.setString(1, crs.getNom());
			ps.setInt(2, crs.getMasse_horaire());
			// Execution de la requete
			returnValue = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				long insertedId = rs.getLong(1);//read the current id of student add in the table from database
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
	}

}

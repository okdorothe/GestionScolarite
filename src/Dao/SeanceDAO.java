package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Cours;
import Model.Seance;
/**
 * classe seance afin d'interagir avec la table seance dans la base de données
 * @author Do Ro The
 *
 */
public class SeanceDAO extends ConnectionDAO {
/**
 * constructeur de la classe seanceDAO
 */
	public SeanceDAO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * permet d'ajouter une seance à la base de données
	 * @param seanc
	 */
	public void add(Seance seanc) {
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
			ps = con.prepareStatement("INSERT INTO seance(ID_ENS_SEANCE,ID_GRP_SEANCE,ID_CRS_SEANCE,ID_SAL_SEANCE,JOUR,DEBUT_H,FIN_H,ID_TYPE_SEANCE) VALUES(?,?,?,?,?,?,?,?)");
			ps.setInt(1, seanc.getId_ens_seanc());
			ps.setInt(2, seanc.getId_grp_seanc());
			ps.setInt(3, seanc.getId_crs_seanc());
			ps.setInt(4, seanc.getId_sal_seanc());
			ps.setDate(5, seanc.getJour());
			ps.setInt(6, seanc.getHd());
			ps.setInt(7, seanc.getHf());
			ps.setInt(8, seanc.getId_type_seanc());
			
			// Execution de la requete
			returnValue = ps.executeUpdate();
			

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

	/**
	 * permet d'afficher les seances en fonctions du groupe d'etudiants
	 * @param a
	 * @param b
	 * @return
	 */
	public ResultSet affichage(Date a,int b) {
	     Connection con = null;
       PreparedStatement ps = null;
       ResultSet rsAff = null;
     try {
   	               // AccGestionGui acc = new AccGestionGui();
                       //to connect to database
                       con = DriverManager.getConnection(URL,LOGIN,PASS);
                       //statement for reading all student in the table
                       ps=con.prepareStatement("select debut_h || 'H - ' || fin_h || 'H' AS Heures,nom_cours, nom_salle, type_seance, nom_ens, numero from ((((seance inner join groupe on\r\n"
                       		+ "id_groupe = id_grp_seance) inner join enseignant on id_ens_seance = id_ens) inner join salle on id_salle=id_sal_seance)\r\n"
                       		+ "inner join cours on id_cours = id_crs_seance)inner join typeseance on id_type_seance = id_type where jour = ? AND ID_GRP_SEANCE = ?");
                       ps.setDate(1,a);
                       ps.setInt(2, b);
                       // execute the statement
                       rsAff=ps.executeQuery();

                      

       } catch (Exception e) {
			
				e.printStackTrace();
		}
    
		return rsAff; // return the result of executed statement
       
	 }
	
	/**
	 * permet de d'afficher les seances à partir des de l'enseignant
	 * @param a
	 * @param b
	 * @return
	 */
	public ResultSet affich(Date a,int b) {
	     Connection con = null;
      PreparedStatement ps = null;
      ResultSet rsAff = null;
    try {
  	               // AccGestionGui acc = new AccGestionGui();
                      //to connect to database
                      con = DriverManager.getConnection(URL,LOGIN,PASS);
                      //statement for reading all student in the table
                      ps=con.prepareStatement("select debut_h || 'H - ' || fin_h || 'H' AS Heures,nom_cours, nom_salle, type_seance, nom_ens, numero from ((((seance inner join groupe on\r\n"
                      		+ "id_groupe = id_grp_seance) inner join enseignant on id_ens_seance = id_ens) inner join salle on id_salle=id_sal_seance)\r\n"
                      		+ "inner join cours on id_cours = id_crs_seance)inner join typeseance on id_type_seance = id_type where jour = ? AND ID_ENS_SEANCE = ?");
                      ps.setDate(1,a);
                      ps.setInt(2, b);
                      // execute the statement
                      rsAff=ps.executeQuery();

                     

      } catch (Exception e) {
			
				e.printStackTrace();
		}
   
		return rsAff; // return the result of executed statement
      
	 }
}

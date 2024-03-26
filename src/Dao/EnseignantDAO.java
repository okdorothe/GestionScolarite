package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Enseignant;
import Model.Etudiant;
/**
 * classe enseignant contenant les fonctions pour ajouter , suppprimer, modifier un enseigant dans la base de données
 * @author Do Ro The
 *
 */
public class EnseignantDAO extends ConnectionDAO{
/**
 * constructeur de la classe enseignant
 */
	public EnseignantDAO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * permet d'ajouter un enseignant à la base de données 
	 * @param ens
	 */
	public void add(Enseignant ens) {
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
			String generatedColumns[] = {"id_ens"};
			ps = con.prepareStatement("INSERT INTO enseignant VALUES(enseigant_seq.nextval, ?, ?, ?,?,?,?)",generatedColumns);
			ps.setString(1, ens.getIdentifiant());
			ps.setString(2, ens.getMdp());
			ps.setString(3, ens.getNom());
			ps.setString(4, ens.getPrenom());
			ps.setString(5, ens.getTel());
			ps.setInt(6, ens.getTitre());
			
			// Execution de la requete
			returnValue = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				long insertedId = rs.getLong(1);//read the current id of student add in the table from database
				 //this.inserted = insertedId;
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
	/**
	 * permet d'afficher tous les enseignants de la base de données
	 * @return
	 */
	public ResultSet affichage() {
	     Connection con = null;
        PreparedStatement ps = null;
        ResultSet rsAf = null;
      try {
    	               // AccGestionGui acc = new AccGestionGui();
                        //to connect to database
                        con = DriverManager.getConnection(URL,LOGIN,PASS);
                        //statement for reading all student in the table
                        ps=con.prepareStatement("SELECT identifiant,nom_ens, prenom_ens,tel_ens from enseignant ");
                        // execute the statement
                        rsAf=ps.executeQuery();

                       

        } catch (Exception e) {
			
				e.printStackTrace();
		}
     
		return rsAf; // return the result of executed statement
        
	 }
	/**
	 * permet de supprimer un enseignant
	 * @param id
	 */
	 public void supprimer(String id)
	 {
		  Connection con = null;
			PreparedStatement ps = null;
			int returnValue =0;
			try {
				con = DriverManager.getConnection(URL,LOGIN,PASS);
				ps=con.prepareStatement("DELETE FROM enseignant WHERE identifiant = ?");
				ps.setString(1,id);
				returnValue = ps.executeUpdate();
				
			} catch (SQLException e7) {
				// TODO Auto-generated catch block
				e7.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 /**
	  * function to update data of a student table row according to the id
	  * @param e
	  */
	 public void update(Enseignant e) {
		 Connection con = null;
			PreparedStatement ps = null;
			int returnValue =0;
			try {
				con = DriverManager.getConnection(URL,LOGIN,PASS);
				ps=con.prepareStatement("UPDATE enseignant SET nom_ens = ?, prenom_ens = ?, tel_ens = ? WHERE identifiant =  ? ");
				ps.setString(1,e.getNom() );
				ps.setString(2, e.getPrenom());
				ps.setString(3, e.getTel());
				ps.setString(4, e.getIdentifiant());
				returnValue = ps.executeUpdate();
			} catch (SQLException e7) {
				// TODO Auto-generated catch block
				e7.printStackTrace();
			}
	 }
	 

}

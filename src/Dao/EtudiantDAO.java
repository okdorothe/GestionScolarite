/**
 * 
 */
package Dao;
import Gui.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.*;

/**
 * Classe etudiant inserer , modifier , supprimer les etudiants dans la table etudiant de la base de données
 * @author Do Ro The
 *@version 1.0
 */
public class EtudiantDAO extends ConnectionDAO{
	/*
	 *  permet de generer l'id automatiquement
	 */
	private long inserted;
	/**
	 *  constructeur de la classe étudiant
	 */
	public EtudiantDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * ajouter un etudiant dans la base de données
	 * @param etu 
	 */
	public void add(Etudiant etu) {
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
			String generatedColumns[] = {"id_etudiants"};
			ps = con.prepareStatement("INSERT INTO etudiants VALUES(etudiants_seq.nextval, ?, ?, ?,?,?,?,?,?)",generatedColumns);
			ps.setString(1, etu.getIdentifiant());
			ps.setString(2, etu.getMdp());
			ps.setString(3, etu.getNom());
			ps.setString(4, etu.getPrenom());
			ps.setString(5, etu.getMail());
			ps.setInt(6, etu.getIdGroupe());
			ps.setInt(7, etu.getIdTitre());
			ps.setInt(8, etu.getIdFiliere());
			// Execution de la requete
			returnValue = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				long insertedId = rs.getLong(1);//read the current id of student add in the table from database
				 this.inserted = insertedId;
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
	 * lire tous les etudiants de la table etudiant
	 * @param a
	 * @return
	 */
	public ResultSet affichage(String a) {
	     Connection con = null;
        PreparedStatement ps = null;
        ResultSet rsAff = null;
      try {
    	               // AccGestionGui acc = new AccGestionGui();
                        //to connect to database
                        con = DriverManager.getConnection(URL,LOGIN,PASS);
                        //statement for reading all student in the table
                        ps=con.prepareStatement("SELECT identifiant,nom, prenom,filiere,numero,mail from ((etudiants inner join groupe on id_groupe = id_groupe_etu) inner join filiere on id_filiere=id_fil_etu) WHERE groupe.numero = ? ORDER BY id_etudiants");
                        ps.setString(1,a);
                        // execute the statement
                        rsAff=ps.executeQuery();

                       

        } catch (Exception e) {
			
				e.printStackTrace();
		}
     
		return rsAff; // return the result of executed statement
        
	 }
	/**
	 * Supprimer un étudiant à parir de son id
	 * @param id
	 */
	 public void supprimer(String id)
	 {
		  Connection con = null;
			PreparedStatement ps = null;
			int returnValue =0;
			try {
				con = DriverManager.getConnection(URL,LOGIN,PASS);
				ps=con.prepareStatement("DELETE FROM etudiants WHERE identifiant = ?");
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
	 public void update(Etudiant e) {
		 Connection con = null;
			PreparedStatement ps = null;
			int returnValue =0;
			try {
				con = DriverManager.getConnection(URL,LOGIN,PASS);
				ps=con.prepareStatement("UPDATE etudiants SET nom = ?, prenom = ?, mail = ?, id_groupe_etu = ?, id_fil_etu = ? WHERE identifiant =  ? ");
				ps.setString(1,e.getNom());
				ps.setString(2, e.getPrenom());
				ps.setString(3, e.getMail());
				ps.setInt(4, e.getIdGroupe());
				ps.setInt(5, e.getIdFiliere());
				ps.setString(6, e.getIdentifiant());
				returnValue = ps.executeUpdate();
			} catch (SQLException e7) {
				// TODO Auto-generated catch block
				e7.printStackTrace();
			}
	 }
	 
	
	 public long getIdIns()
	 {
		 return this.inserted;
	 }
}

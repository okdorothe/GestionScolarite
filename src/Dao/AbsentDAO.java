package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
/**
 * classe permettent la gestion d'absence depuis la base de données
 * @version 1.0
 * @author Do Ro The
 *
 */
public class AbsentDAO extends ConnectionDAO{

	public AbsentDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * methode permettant d'ajouter une absence à la base de données
	 * @param id1 id du professeur
	 * @param id2 id de la salle
	 * @param id3 heure de debut
	 * @param id4 heure de fin
	 * @param id5 id groupe
	 * @param id6 id 
	 */
	public void add(int id1,int id2,int id3,int id4,int id5,int id6) {
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
			String generatedColumns[] = {"id_abs"};
			ps = con.prepareStatement("INSERT INTO absenter VALUES(absent_seq.nextval,?,?,?,?,?,?)",generatedColumns);
			ps.setInt(1,id1);
			ps.setInt(2,id2);
			ps.setInt(3,id3);
			ps.setInt(4,id4);
			ps.setInt(5,id5);
			ps.setInt(6,id6);
			// Execution de la requete
			returnValue = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				long insertedId = rs.getLong(1);//read the current id of student add in the table from database
			}

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				JOptionPane.showMessageDialog(null, "Vous l'avez déjà ajouter comme absent");
			else
				e.printStackTrace();
		}
		
	}
	
	public ResultSet affichage(int a) {
	     Connection con = null;
       PreparedStatement ps = null;
       ResultSet rsAff = null;
     try {
   	               // AccGestionGui acc = new AccGestionGui();
                       //to connect to database
                       con = DriverManager.getConnection(URL,LOGIN,PASS);
                       //statement for reading all student in the table
                       ps=con.prepareStatement("select id_abs, (fin_h-debut_h)as nombre_heures,jour,nom_cours from ((absenter inner join seance on \r\n"
                       		+ "ID_ENS_SEANCE_ABS=ID_ENS_SEANCE AND ID_CRS_SEANCE_ABS=ID_CRS_SEANCE AND \r\n"
                       		+ "ID_GRP_SEANCE_ABS=ID_GRP_SEANCE AND ID_SAL_SEANCE_ABS=ID_SAL_SEANCE)inner join cours\r\n"
                       		+ "on ID_CRS_SEANCE = id_cours)\r\n"
                       		+ "where id_etu_seance_abs = ?");
                       ps.setInt(1,a);
                       // execute the statement
                       rsAff=ps.executeQuery();

                      

       } catch (Exception e) {
			if(e.getMessage().contains("ORA-00001")) {
				JOptionPane.showMessageDialog(null,"Vous l'avez déjà ajouter comme absent");
			}
			else
				e.printStackTrace();
		}
    
		return rsAff; // return the result of executed statement
       
	 }

}

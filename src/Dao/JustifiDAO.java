/**
 * 
 */
package Dao;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Etudiant;

/**
 * this class allow to interact with table Justifier in the database
 * @author Do Ro The
 *@version 1.0
 */
public class JustifiDAO extends ConnectionDAO{
	private int compte;
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<Integer> listAbs = new ArrayList<>();

	/**
	 * constructeur de la classe justifiDAO
	 */
	public JustifiDAO() {
		super();
		this.compte=0;
		// TODO Auto-generated constructor stub
	}
	/**
	 * allow to add a justification
	 * @param a
	 * @param b
	 * @param c
	 */
	public void add(String a,byte[] b,int c) {
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
			String generatedColumns[] = {"id_file"};
			ps = con.prepareStatement("INSERT INTO justificatifs VALUES(justifier_seq.nextval,?,?,?)",generatedColumns);
			ps.setString(1,a);
			ps.setBytes(2,b);
			ps.setInt(3,c);
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
		}
		
	}
	/**
	 * permet d'afficher les justificatifs
	 * @return
	 */
	public ArrayList<byte[]> afficher() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<byte[]> l = new ArrayList<>();
		byte[] pdfBytes = null ;
		try {
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			  ps=con.prepareStatement("select ID_ABS, file_data, id_etu_seance_abs, file_name from justificatifs\r\n"
			  		+ "inner join absenter on id_abs = id_abs_j where absen=1");
			  rs=ps.executeQuery();
			  if (rs.next()) {
		            // Retrieve the PDF file data as a byte array
		            pdfBytes = rs.getBytes("file_data");
		            l.add(pdfBytes);
		            this.compte++;
		            FonctionsDAO fn = new FonctionsDAO();
		            this.list.add(fn.recpEtuIden(rs.getInt("id_etu_seance_abs")));
		            this.listAbs.add(rs.getInt("id_abs"));
		                   
			  }
		} catch (Exception e) {
				e.printStackTrace();
		}
		return l;
	}
	public int getCompte() {
		return this.compte;
	}
	/**
	 * 
	 * @return list
	 */
	public ArrayList<String> getList() {
		return this.list;
	}
	/**
	 * 
	 * @return la liste des absences
	 */
	public ArrayList<Integer> getListAbs(){
		return this.listAbs;
	}
	/**
	 * permet de metrre l'absence d'un etudiant
	 * @param e
	 */
	 public void update(int e) {
		 Connection con = null;
			PreparedStatement ps = null;
			int returnValue =0;
			try {
				con = DriverManager.getConnection(URL,LOGIN,PASS);
				ps=con.prepareStatement("UPDATE absenter SET absen = 0 WHERE id_abs = ? ");
				ps.setInt(1,e);
				returnValue = ps.executeUpdate();
			} catch (SQLException e7) {
				// TODO Auto-generated catch block
				e7.printStackTrace();
			}
	 }

}

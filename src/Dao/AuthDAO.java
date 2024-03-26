package Dao;

import java.sql.*;
import java.util.ArrayList;
/**
 * cette classe permet la gestion de l'authentification depuis la base de données
 * @author Do Ro The
 *
 */
public class AuthDAO extends ConnectionDAO {
	public AuthDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
/**
 * afficher tous les identidiant et mot de passe de la base de données
 * 
 * @return une liste de tableau contenant l'identifiant et le mot de passe de chaque etudiant
 */
	public ArrayList<Object[]> affEtu() {
		Connection con = null;
		ArrayList<Object[]> listUsers = new ArrayList<>();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSetMetaData resultMeta = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select identifiant, mdp, id_titre,ID_GROUPE_ETU from etudiants");
			ps1= con.prepareStatement("Select identifiant, mdp, id_titre from enseignant");
			rs=ps.executeQuery();
			rs1=ps1.executeQuery();
			resultMeta = rs.getMetaData();
			while(rs.next()) {
				Object[] line = new Object[resultMeta.getColumnCount()];//initialize the table of object line
  				/*
  				 * now we put each column of rs1 in table line column
  				 */
  				for(int i=1; i<=resultMeta.getColumnCount();i++)
  				{
  					line[i-1]=rs.getObject(i);
  				}
  				//add the table line in the list
  				listUsers.add(line);
			}
			while(rs1.next()) {
				resultMeta =null;
				resultMeta = rs1.getMetaData();
				Object[] line = new Object[resultMeta.getColumnCount()];//initialize the table of object line
  				/*
  				 * now we put each column of rs1 in table line column
  				 */
  				for(int i=1; i<=resultMeta.getColumnCount();i++)
  				{
  					line[i-1]=rs1.getObject(i);
  				}
  				//add the table line in the list
  				listUsers.add(line);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUsers;	
	}
	
	
}

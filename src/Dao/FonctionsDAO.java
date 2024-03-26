package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * Contient les différentes fonction utilisable au niveau de l'interface 
 * @author Do Ro The
 *
 */
public class FonctionsDAO extends ConnectionDAO{

	public FonctionsDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * permet de recuperer un l'id d'une filiere avec son nom
	 * @param filiere
	 * @return
	 */
	public int recpFiliere(String filiere) {
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
			ps = con.prepareStatement("Select id_filiere from filiere where filiere = ?");
		    ps.setString(1, filiere);
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next())
		   {
			   rep=rs.getInt("id_filiere");  
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
	 * permet de recuperer un l'id d'un titre avec son nom
	 * @param filiere
	 * @return l'id
	 */
	public int recpTitre(String titre) {
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
			ps = con.prepareStatement("Select id from users where titre = ?");
		    ps.setString(1, titre);
			// Execution de la requete
		   rs=ps.executeQuery();
		   rep=rs.getInt("id");

		} catch (Exception e) {
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
	 * permet d'avoir la liste des filieres
	 * @return liste de lilieres
	 */
	public ArrayList<String> listFil(){
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
			ps = con.prepareStatement("Select filiere from filiere");
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   list.add(rs.getString("filiere"));
		   }
		} catch (Exception e) {
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
	 * permet d'avoir la liste des cours
	 * @return liste des cours
	 */
	public ArrayList<String> listCrs(){
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
			ps = con.prepareStatement("Select nom_cours from cours");
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   list.add(rs.getString("nom_cours"));
		   }
		} catch (Exception e) {
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
	 * permet d'avoir la liste des salles
	 * @return listes des salles 
	 */
	public ArrayList<String> listSalle(){
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
			ps = con.prepareStatement("Select nom_salle from salle");
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   list.add(rs.getString("nom_salle"));
		   }
		} catch (Exception e) {
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
	
	public ArrayList<String> listTseance(){
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
			ps = con.prepareStatement("Select type_seance from typeseance");
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   list.add(rs.getString("type_seance"));
		   }
		} catch (Exception e) {
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
	 * permet d'avoir la liste des professeurs
	 * @return la liste des professeurs
	 */
	public ArrayList<String> listTPro(){
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
			ps = con.prepareStatement("Select nom_ens from enseignant");
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   list.add(rs.getString("nom_ens"));
		   }
		} catch (Exception e) {
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
	 * recuperer l'id d'un prof à partir de son nom
	 * @param pr
	 * @return l'id du prof
	 */
	public int recppr(String pr) {
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
			ps = con.prepareStatement("Select id_ens from enseignant where nom_ens = ?");
		    ps.setString(1, pr);
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   rep=rs.getInt("id_ens");
		   }

		} catch (Exception e) {
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
 * recuperer l'id d'un prof à partir de son nom
 * @param pr
 * @return l'id du prof
 */
	public int recppr1(String pr) {
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
			ps = con.prepareStatement("Select id_ens from enseignant where identifiant = ?");
		    ps.setString(1, pr);
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   rep=rs.getInt("id_ens");
		   }

		} catch (Exception e) {
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
	public int recpTpS(String type) {
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
			ps = con.prepareStatement("Select id_type from typeseance where type_seance = ?");
		    ps.setString(1, type);
			// Execution de la requete
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   rep=rs.getInt("id_type");
			  // JOptionPane.showMessageDialog(null, rep);
		   }
		  

		} catch (Exception e) {
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
	
	public int recpCrs(String crs) {
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
			ps = con.prepareStatement("Select id_cours from cours where nom_cours = ?");
		    ps.setString(1, crs);
			// Execution de la requete
		   rs=ps.executeQuery();
		   
		   while(rs.next()) {
			   rep=rs.getInt("id_cours"); 
		   }

		} catch (Exception e) {
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
	 * recuperer l'id de la salle à partir de son id 
	 * @param salle
	 * @return l'id de la salle
	 */
	public int recpSalle(String salle) {
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
			ps = con.prepareStatement("Select id_salle from salle where nom_salle = ?");
		    ps.setString(1, salle);
			// Execution de la requete
		   rs=ps.executeQuery();
	
		   while(rs.next())
		   {
			   rep=rs.getInt("id_salle");
		   }

		} catch (Exception e) {
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
	 * recuperer l'id dun etudiant à partir de son num
	 * @param etu
	 * @return l'id de l'etudiant
	 */
	public int recpEtu(String etu) {
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
			ps = con.prepareStatement("Select id_etudiants from etudiants where identifiant = ?");
		    ps.setString(1, etu);
			// Execution de la requete
		   rs=ps.executeQuery();
	
		   while(rs.next())
		   {
			   rep=rs.getInt("id_etudiants");
		   }

		} catch (Exception e) {
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
	 * recuperer le nom d'un etudiant à partir de son identifiant
	 * @param etu
	 * @return nom de l'etudiant
	 */
	public String recpEtuIden(int etu) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String rep = null;
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("Select identifiant from etudiants where id_etudiants = ?");
		    ps.setInt(1, etu);
			// Execution de la requete
		   rs=ps.executeQuery();
	
		   while(rs.next())
		   {
			   rep=rs.getString("identifiant");
		   }

		} catch (Exception e) {
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
	
}

package Gui;


import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Dao.*;


public class ModeleStatique {
	private final ArrayList<Object[]> datas = new ArrayList<>();//create a list of table to store any type of variable
	private final ArrayList<Object[]> datasEns = new ArrayList<>();
	private ArrayList<Object[]> dataS = new ArrayList<>();
	private final ArrayList<Object[]> datasEtu = new ArrayList<>();
	private final ArrayList<Object[]> datasAbs = new ArrayList<>();
    private int i=1,j=1;
 // Jtable column names
    private final String[] columnNames = {
    		"Identifiant",
            "Nom",
            "Prénom",
            "Filiere",
            "Groupe",
            "email"};
    
    private final String[] columnEtu2 = {
    		"Identifiant",
            "Nom",
            "Prénom"};
    
    private final String[] columnNamesEns = {
    		"Identifiant",
            "Nom",
            "Prénom",
            "Téléphone"};
    private final String[] columnNamesAbs = {
    		"Id",
            "Nombre d'heures",
            "Jour",
            "Cours"};
 //constructor
    public ModeleStatique() {
        super();
      
    }
 // to get column length
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public int getColumnCountEns() {
        return columnNamesEns.length;
    }
    // to get column value
    public String[] getColumn() {
        return columnNames ;
    }
    public String[] getColumnEns() {
        return columnNamesEns ;
    }
    public String[] getColumns() {
        return columnEtu2 ;
    }
    public String[] getColumnsAbs() {
        return columnNamesAbs ;
    }
 
    public  ArrayList<Object[]> getData (ResultSet rsM) {
    	  EtudiantDAO etud = new EtudiantDAO();//instantiated the class student in order to call the method "afficher"
  		//rsM = etud.affichage();//call of the method "afficher"
  		ResultSetMetaData resultMeta = null;
  		//
  		try {
  			resultMeta = rsM.getMetaData();// put the result in a resultMetaData variable to be able use get column count of rs1
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		 
          try {
  			while(rsM.next())
  			{
  				Object[] line = new Object[resultMeta.getColumnCount()];//initialize the table of object line
  				/*
  				 * now we put each column of rs1 in table line column
  				 */
  				for(i=1; i<=resultMeta.getColumnCount();i++)
  				{
  					line[i-1]=rsM.getObject(i);
  				}
  				//add the table line in the list
  				datas.add(line);
  			}	
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
        return this.datas;
    }
    
    public  ArrayList<Object[]> getDataEns () {
    	ResultSet rsM;
  	  EnseignantDAO ens = new EnseignantDAO();//instantiated the class student in order to call the method "afficher"
		rsM = ens.affichage();//call of the method "afficher"
		ResultSetMetaData resultMeta = null;
		//
		try {
			resultMeta = rsM.getMetaData();// put the result in a resultMetaData variable to be able use get column count of rs1
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
        try {
			while(rsM.next())
			{
				Object[] line1 = new Object[resultMeta.getColumnCount()];//initialize the table of object line
				/*
				 * now we put each column of rs1 in table line column
				 */
				for(i=1; i<=resultMeta.getColumnCount();i++)
				{
					line1[i-1]=rsM.getObject(i);
				}
				//add the table line in the list
				datasEns.add(line1);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return this.datasEns;
  }
    
    public  ArrayList<Object[]> getDataS(Date a,int b) {
    	ResultSet rsM;
  	    SeanceDAO ens = new SeanceDAO();//instantiated the class student in order to call the method "afficher"
		rsM = ens.affichage(a,b);//call of the method "afficher"
		ResultSetMetaData resultMeta = null;
		//
		try {
			resultMeta = rsM.getMetaData();// put the result in a resultMetaData variable to be able use get column count of rs1
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
        try {
			while(rsM.next())
			{
				Object[] line1 = new Object[6];//initialize the table of object line
				/*
				 * now we put each column of rs1 in table line column
				 */
				for(i=1; i<=6;i++)
				{
					line1[i-1]=rsM.getObject(i);
				}
				//add the table line in the list
				dataS.add(line1);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return this.dataS;
  }
    
    public  ArrayList<Object[]> getDataS1(Date a,int b) {
    	ResultSet rsM;
  	    SeanceDAO ens = new SeanceDAO();//instantiated the class student in order to call the method "afficher"
		rsM = ens.affich(a,b);//call of the method "afficher"
		ResultSetMetaData resultMeta = null;
		//
		try {
			resultMeta = rsM.getMetaData();// put the result in a resultMetaData variable to be able use get column count of rs1
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
        try {
			while(rsM.next())
			{
				Object[] line1 = new Object[6];//initialize the table of object line
				/*
				 * now we put each column of rs1 in table line column
				 */
				for(i=1; i<=6;i++)
				{
					line1[i-1]=rsM.getObject(i);
				}
				//add the table line in the list
				this.dataS.add(line1);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return this.dataS;
  }
  
    public  ArrayList<Object[]> getDataEtu2 (int id) {
    	ResultSet rsM;
  	  GroupeDAO gr = new GroupeDAO();//instantiated the class student in order to call the method "afficher"
		rsM = gr.listEtu(id);//call of the method "afficher"
		ResultSetMetaData resultMeta = null;
		//
		try {
			resultMeta = rsM.getMetaData();// put the result in a resultMetaData variable to be able use get column count of rs1
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
        try {
			while(rsM.next())
			{
				Object[] line1 = new Object[resultMeta.getColumnCount()];//initialize the table of object line
				/*
				 * now we put each column of rs1 in table line column
				 */
				for(i=1; i<=resultMeta.getColumnCount();i++)
				{
					line1[i-1]=rsM.getObject(i);
				}
				//add the table line in the list
				datasEtu.add(line1);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return this.datasEtu;
  }

    
    public  ArrayList<Object[]> getDataAbs(int id) {
    	ResultSet rsM;
  	  AbsentDAO ab = new AbsentDAO();//instantiated the class student in order to call the method "afficher"
		rsM = ab.affichage(id);//call of the method "afficher"
		ResultSetMetaData resultMeta = null;
		//
		try {
			resultMeta = rsM.getMetaData();// put the result in a resultMetaData variable to be able use get column count of rs1
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
        try {
			while(rsM.next())
			{
				Object[] line1 = new Object[resultMeta.getColumnCount()];//initialize the table of object line
				/*
				 * now we put each column of rs1 in table line column
				 */
				for(i=1; i<=resultMeta.getColumnCount();i++)
				{
					line1[i-1]=rsM.getObject(i);
				}
				//add the table line in the list
				datasAbs.add(line1);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return this.datasAbs;
  }
}


package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.appengine.api.utils.SystemProperty;

public class DBConnection { 
	 private static Connection con = null;
	    private static String googleUrl = "";
	    private static String localUrl = "jdbc:mysql://127.0.0.1:3306/Paarsheep";


	    public static Connection connection() {
	        
	        if (con == null) {
	            String url = null;
	            try {
	                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
	                   
	                    Class.forName("com.mysql.jdbc.GoogleDriver");
	                    url = googleUrl;
	                } else {
	                    
	                    Class.forName("com.mysql.jdbc.Driver");
	                    url = localUrl;
	                }

	                con = DriverManager.getConnection(url);
	            } catch (Exception e) {
	                con = null;
	                e.printStackTrace();
	            }
	        }

	        // Zurückgegeben der Verbindung
	        return con;
	    }
	    	   
	    	    public void getPartner(){
	    	        if (con != null){
	    	            Statement query;
	    	            try {
	    	                query= con.createStatement();
	    	                final String sql= "SELECT VORNAME, NACHNAME " + "FROM NUTZERPROFIL ";
	    	                final ResultSet result = query.executeQuery(sql);
	    	                while(result.next()){
	    	                    final String first_name = result.getString("VORNAME");
	    	                    final String last_name = result.getString("NACHNAME");
	    	                    final String name = last_name + ", " + first_name;
	    	                    System.out.println(name);
	    	                }
	    	            } catch (final SQLException e) {
	    	                e.printStackTrace();
	    	            }
	    	        }
	    	    }
	    	}
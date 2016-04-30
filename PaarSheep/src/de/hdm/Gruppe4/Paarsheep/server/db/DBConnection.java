package de.hdm.Gruppe4.Paarsheep.server.db;


// Sämtliche java.sql Pakate laden. 
import java.sql.*;
import com.google.appengine.api.utils.SystemProperty;

/**
 * 
 * @author Dang
 * @author Thies
 *
 */
public class DBConnection {


    private static Connection con = null;
    private static String googleUrl = "jdbc:google:mysql://rich-experience-129612:paarsheepinstanz?user=root";
    private static String localUrl = "jdbc:mysql://127.0.0.1:3306/Paarsheep?user=root";


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

    
    /**
	 * Schließt das ResultSet, das Statement und die Connection
	 * @param rs
	 * @param stmt
	 * @param con
	 * @throws Exception
	 */
	public static void closeAll(ResultSet rs, Statement stmt, Connection con) throws Exception {
		
	}
	
	    	   
}
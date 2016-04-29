package de.hdm.Gruppe4.Paarsheep.server.db;
// Sämtliche java.sql Pakate laden. 
import java.sql.*;

import com.google.appengine.api.utils.SystemProperty;

//  @author Thies, Dang
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

}

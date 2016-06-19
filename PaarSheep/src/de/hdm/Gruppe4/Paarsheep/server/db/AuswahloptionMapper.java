package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;


import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Auswahloption</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * 
 * @author Thies
 * @author Dang
 * */

public class AuswahloptionMapper {
	

	  /**
	   * Die Klasse AuswahloptionMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @author Dang
	   * @author Hauler
	   * @author Thies
	   */
private static AuswahloptionMapper auswahloptionMapper = null;

/**
* Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
* neue Instanzen dieser Klasse zu erzeugen.
*/
protected AuswahloptionMapper() {
}

/**
* Es kann nur eine einzige Instanz von AuswahloptionMapper erzeugt werden
* 
* <b>Fazit:</b> AuswahloptionMapper sollte nicht mittels <code>new</code>
* instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
* 
* @return DAS <code>AuswahloptionMapper</code>-Objekt.
* @see suchprofilMapper
*/
public static AuswahloptionMapper auswahloptionMapper() {
if (auswahloptionMapper == null) {
	auswahloptionMapper = new AuswahloptionMapper();
}

return auswahloptionMapper;
}

	/** 
	 * Diese Methode ermöglicht es ein Profil in der Datenbank anzulegen.
	 * 
	 * @param auswahloption
	 * @return auswahloption
	 * @throws Exception
	 */
public Auswahloption insert(Auswahloption auswahloption) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste
       * Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM Auswahloption ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * auswahl erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
    	  auswahloption.setID(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO Auswahloption (AuswahloptionsID, Auswahl_AuswahlID) " + "VALUES ("
            + auswahloption.getID() + "," + auswahloption.getID() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
    
    return auswahloption;
  }
	
	/**
	 * Diese Methode ermöglicht das Löschen eines Abonnements
	 * 
	 * @param auswahloption
	 * @throws Exception
	 */
	  public void delete(Auswahloption auswahloption) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Beschreibung " + "WHERE id=" + auswahloption.getID());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param auswahloption das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	  public Auswahloption update(Auswahloption auswahloption) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE auswahloption " + "SET owner=\"" + auswahloption.getID()
		          + "\" " + "WHERE id=" + auswahloption.getID());

		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Um Analogie zu insert(Beschreibung beschreibung) zu wahren, geben wir auswahl zurück
		    return auswahloption;
		  }

}

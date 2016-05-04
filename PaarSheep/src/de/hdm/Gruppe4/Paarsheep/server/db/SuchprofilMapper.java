package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.*;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;


/**
 * Mapper-Klasse, die <code>SuchprofilMapper</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * 
 * @author Thies
 * @author Hauler
 * @author Dang
 * */

public class SuchprofilMapper {


	  /**
	   * Die Klasse BeschreibungMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see SuchprofilMapper()
	   * @author Dang
	   * @author Hauler
	   * @author Thies
	   */
private static SuchprofilMapper suchprofilMapper = null;

/**
* Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
* neue Instanzen dieser Klasse zu erzeugen.
*/
protected SuchprofilMapper() {
}

/**
* Es kann nur eine einzige Instanz von SuchprofilMapper erzeugt werden
* 
* <b>Fazit:</b> SuchprofilMapper sollte nicht mittels <code>new</code>
* instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
* 
* @return DAS <code>SuchprofilMapper</code>-Objekt.
* @see suchprofilMapper
*/
public static SuchprofilMapper suchprofilMapper() {
if (suchprofilMapper == null) {
	suchprofilMapper = new SuchprofilMapper();
}

return suchprofilMapper;
}

	/** 
	 * Diese Methode ermöglicht es ein Profil in der Datenbank anzulegen.
	 * 
	 * @param suchprofil
	 * @return
	 * @throws Exception
	 */
	public Suchprofil insertSuchprofil(Suchprofil suchprofil) 
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt = null;

		try {
			String sql = "INSERT INTO `Suchprofil`(`SuchprofilID`) VALUES (NULL)";

			preStmt = con.prepareStatement(sql);
			preStmt.executeUpdate();
			preStmt.close();
			
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(null, preStmt, con);
		}

		return suchprofil;
	}
	
	/**
	 * Diese Methode ermöglicht das Löschen eines Abonnements
	 * 
	 * @param suchprofil
	 * @throws Exception
	 */
	  public void delete(Suchprofil suchprofil) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Beschreibung " + "WHERE id=" + suchprofil.getID());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param a das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	  public Suchprofil update(Suchprofil suchprofil) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE accounts " + "SET owner=\"" + suchprofil.getID()
		          + "\" " + "WHERE id=" + suchprofil.getID());

		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Um Analogie zu insert(Beschreibung beschreibung) zu wahren, geben wir auswahl zurück
		    return suchprofil;
		  }
	  
	  /**
	   * Wie ist die Verbindung? 
	   * 
	   * @see findByOwner(Nutzerprofil owner)
	   * @param ownerID Schlüssel des zugehörigen Suchender.
	   * @return Ein ArrayList mit Account-Objekten, die sämtliche Konten des
	   *         betreffenden Kunden repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public ArrayList<Suchprofil> findByOwner(int PofilID) {
	    Connection con = DBConnection.connection();
	    ArrayList<Suchprofil> result = new ArrayList<Suchprofil>();

	    try {
	      Statement stmt = con.createStatement();
// ???????????
	      ResultSet rs = stmt.executeQuery("SELECT FROM accounts "
	          + "WHERE owner=" + ProfilID + " ORDER BY id");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
	      while (rs.next()) {
	        Suchprofil suchprofil = new Suchprofil();
	        suchprofil.setID(rs.getInt("id"));
	        suchprofil.setID(rs.getInt("owner"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.add(suchprofil);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }

}

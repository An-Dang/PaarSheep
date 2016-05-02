package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

public class BeschreibungMapper {
	
	  /**
 	   * Die Klasse BeschreibungMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see BeschreibungMapper()
	   * @author Dang
	   * @author Hauler
	   * @author Thies
	   */
private static BeschreibungMapper beschreibungMapper = null;

/**
 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
 * neue Instanzen dieser Klasse zu erzeugen.
 */
protected BeschreibungMapper() {
}

/**
 * Es kann nur eine einzige Instanz von BeschreibungMapper erzeugt werden
 * 
 * <b>Fazit:</b> AccountMapper sollte nicht mittels <code>new</code>
 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
 * 
 * @return DAS <code>AccountMapper</code>-Objekt.
 * @see accountMapper
 */
public static BeschreibungMapper auswahlMapper() {
  if (beschreibungMapper == null) {
	  beschreibungMapper = new BeschreibungMapper();
  }

  return beschreibungMapper;
}

	/** 
	 * Diese Methode ermöglicht es ein Profil in der Datenbank anzulegen.
	 * 
	 * @param beschreibung
	 * @return
	 * @throws Exception
	 */
	public Beschreibung insertBeschreibung(Beschreibung beschreibung) 
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt = null;

		try {
			String sql = "INSERT INTO `Beschreibung`(`BeschreibungID`) VALUES (NULL)";

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

		return beschreibung;
	}
	
	/**
	 * Diese Methode ermöglicht das Löschen eines Abonnements
	 * 
	 * @param profil
	 * @throws Exception
	 */
	  public void delete(Beschreibung beschreibung) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Beschreibung " + "WHERE id=" + beschreibung.getID());
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
	  public Beschreibung update(Beschreibung beschreibung) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE accounts " + "SET owner=\"" + beschreibung.getID()
		          + "\" " + "WHERE id=" + beschreibung.getID());

		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Um Analogie zu insert(Beschreibung beschreibung) zu wahren, geben wir auswahl zurück
		    return beschreibung;
		  }

}

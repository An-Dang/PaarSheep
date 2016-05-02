package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.*;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Auswahl</code>-Objekte auf eine relationale
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

public class AuswahlMapper {
	
		/**
 	   * Die Klasse InformationMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   */
	private static AuswahlMapper auswahlMapper = null;

/**
 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
 * neue Instanzen dieser Klasse zu erzeugen.
 */
	protected AuswahlMapper() {
	}
	
/**
 * Es kann nur eine einzige Instanz von ProfilMapper erzeugt werden
 * 
 * <b>Fazit:</b> AccountMapper sollte nicht mittels <code>new</code>
 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
 * 
 * @return DAS <code>AuswahlMapper</code>-Objekt.
 * @see accountMapper
 */
	public static AuswahlMapper auswahlMapper() {
		if (auswahlMapper == null) {
			auswahlMapper = new AuswahlMapper();
		}

		return auswahlMapper;
	}
	/** 
	 * Diese Methode ermöglicht es eine Auswahl in der Datenbank anzulegen.
	 * 
	 * @param auswahl
	 * @return
	 * @throws Exception
	 */

	public Auswahl insertAuswahl(Auswahl auswahl) 
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt = null;

		try {
			String sql = "INSERT INTO `Auswahl`(`AuswahlID`) VALUES (NULL)";

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

		return auswahl;
	}
		
	
	 /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param a das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	  public Auswahl update(Auswahl auswahl) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE accounts " + "SET owner=\"" + auswahl.getID()
	          + "\" " + "WHERE id=" + auswahl.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Um Analogie zu insert(Auswahl auswahl) zu wahren, geben wir auswahl zurück
	    return auswahl;
	  }

	  /**
	   * Löschen der Daten eines <code>Auswahl</code>-Objekts aus der Datenbank.
	   * 
	   * @param a das aus der DB zu löschende "Objekt"
	   */
	  public void delete(Auswahl auswahl) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM accounts " + "WHERE id=" + auswahl.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	  }
	  
	  /**
	   * Auslesen aller Auswahl eines durch Fremdschlüssel (Eigenschaft_EigenschaftsID.) gegebenen
	   * Profils.
	   * 
	   * @see findByEigenschaft(Eigenschaft eigenschaft)
	   * @param eigenschaft Schlüssel der zugehörigen Eigenschaft.
	   * @return Ein ArrayList mit Information-Objekten, die sämtliche Informationen des
	   *         betreffenden Eigenschaft repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Auswahl zurückgeliefert.
	   */
	  public ArrayList<Auswahl> findByEigenschaft(Eigenschaft eigenschaft) {
	    Connection con = DBConnection.connection();
	    ArrayList<Auswahl> result = new ArrayList<Auswahl>();

	    try {
	      Statement stmt = con.createStatement();
// SQL Befehl noch umändern!
	      ResultSet rs = stmt.executeQuery("SELECT AuswahlID FROM Auswahl "
	          + "WHERE Auswahl=" + eigenschaft + " ORDER BY id");

	      //Für jeden Eintrag im Suchergebnis wird nun ein Auswhal-Objekt erstellt.
	      //Muss nich angepasst werden

	      while (rs.next()) {
	        Auswahl auswahl = new Auswahl();
	        auswahl.setID(rs.getInt("id"));
	        auswahl.setID(rs.getInt("Eigenschaft"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.add(auswahl);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }
	
}

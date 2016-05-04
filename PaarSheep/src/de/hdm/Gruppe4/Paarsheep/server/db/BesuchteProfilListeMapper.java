package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.*;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

public class BesuchteProfilListeMapper {
	/**
	   * Die Klasse InformationMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see BesuchteProfilListeMapper()
	   * @author Dang
	   * @author Hauler
	   * @author Thies
	   */
	private static BesuchteProfilListeMapper besuchteProfilListeMapper = null;

/**
* Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
* neue Instanzen dieser Klasse zu erzeugen.
*/
	protected BesuchteProfilListeMapper() {
	}
	
/**
* Es kann nur eine einzige Instanz von BesuchteProfilListeMapper erzeugt werden
* 
* <b>Fazit:</b> BesuchteProfilListeMapper sollte nicht mittels <code>new</code>
* instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
* 
* @return DAS <code>AuswahlMapper</code>-Objekt.
* @see accountMapper
*/
	public static BesuchteProfilListeMapper besuchteProfilListeMapper() {
		if (besuchteProfilListeMapper == null) {
			besuchteProfilListeMapper = new BesuchteProfilListeMapper();
		}

		return besuchteProfilListeMapper;
	}
	/** 
	 * Diese Methode ermöglicht es eine Auswahl in der Datenbank anzulegen.
	 * 
	 * @param besuchteProfilListe
	 * @return
	 * @throws Exception
	 */

	public BesuchteProfilListe insert(BesuchteProfilListe besuchteProfilListe) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      /*
	       * Zunächst schauen wir nach, welches der momentan höchste
	       * Primärschlüsselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM BesuchteProfilListe ");

	      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
	      if (rs.next()) {
	        /*
	         * auswahl erhält den bisher maximalen, nun um 1 inkrementierten
	         * Primärschlüssel.
	         */
	    	  besuchteProfilListe.setID(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
	        stmt.executeUpdate("INSERT INTO BesuchteProfilListe (BesuchteProfilListeID, Besuchender_NutzerprofilID) " + "VALUES ("
	            + besuchteProfilListe.getID() + "," + besuchteProfilListe.getID() + ")");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	    
	    return besuchteProfilListe;
	  }
		
	
	 /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param besuchteProfilListe das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	  public BesuchteProfilListe update(BesuchteProfilListe besuchteProfilListe) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE BesuchtenProfilListe " + "SET owner=\"" + besuchteProfilListe.getID()
	          + "\" " + "WHERE id=" + besuchteProfilListe.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Um Analogie zu insert(BesuchteProfilListe besuchteProfilListe) zu wahren, geben wir auswahl zurück
	    return besuchteProfilListe;
	  }

}
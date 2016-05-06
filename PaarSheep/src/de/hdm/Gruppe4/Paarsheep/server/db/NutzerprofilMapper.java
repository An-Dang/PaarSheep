package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;
import java.util.*;


/**
 * Mapper-Klasse, die <code>Nutzerprofil</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * 
 * @author Thies
 * @author Dang
 */

public class NutzerprofilMapper {

	/**
	 * Die Klasse NutzerprofilMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * fuer saemtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see NutzerprofilMapper()
	 */

	private static NutzerprofilMapper nutzerprofilMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected NutzerprofilMapper() {

	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NutzerprofilMapper.nutzerpofilMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>NutzerpofilMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> NutzerprofilMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>NutzerprofilMapper</code>-Objekt.
	 * @see nutzerpofilMapper
	 */
	public static NutzerprofilMapper nutzerprofilMapper() {
		if (nutzerprofilMapper == null) {
			nutzerprofilMapper = new NutzerprofilMapper();
		}
		return nutzerprofilMapper;
	}

	/**
	 * Einfügen eines <code>Nutzerpofil</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param nutzerprofil das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 * @throws Exception        
	 */
	  public Nutzerprofil insert(Nutzerprofil nutzerprofil) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM nutzerprofil ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * c erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        nutzerprofil.setID(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO nutzerprofil (nutzerprofilID, vorname, nachname, geburtsdatum) "
		            + "VALUES (" + nutzerprofil.getID() + ",'" + nutzerprofil.getNachname() + "','"
		            + nutzerprofil.getVorname() + "', '" + nutzerprofil.getGeburtsdatum() + ")");
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    /*
		     * Rückgabe, des evtl. korrigierten nutzerprofil.
		     * 
		     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		     * Objekte übergeben werden, wäre die Anpassung des Nutzerprofil-Objekts auch
		     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
		     * explizite Rückgabe von nutzerprofil ist eher ein Stilmittel, um zu signalisieren,
		     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
		     */
		    return nutzerprofil;
		  }

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param nutzerprofil das Objekt, das in die DB geschrieben werden soll
	 * 
	 * @return das als Parameter übergebene Objekt
	 */
	public Nutzerprofil update(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE nutzerprofil " + "SET vorname=\"" + nutzerprofil.getVorname() + "\", "
					+ "nachname=\"" + nutzerprofil.getNachname() + "\"," + "geburtsdatum=\""
					+ nutzerprofil.getGeburtsdatum() + "\" ," + "WHERE id=" + nutzerprofil.getID());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Um Analogie zu insert(Nutzerprofil nutzerprofil) zu wahren, geben wir
		// nutzerprofil zurück
		return nutzerprofil;
	}

	/**
	 * Löschen der Daten eines <code>Nutzerprofil</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param nutzerprofil
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM nutzerprofil " + "WHERE id=" + nutzerprofil.getID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	 /**
//	   * Auslesen des zugehörigen <code>Profil</code>-Objekts zu einem gegebenen
//	   * Nutzerprofil.
//	   * 
//	   * @param nutzerprofil das Nutzerprofil, dessen Profil wir auslesen möchten
//	   * @return ein Objekt, das den Profil des Nutzerprofils darstellt
//	   */
//	  public Profil getOwner(Nutzerprofil nutzerprofil) {
//	    /*
//	     * Wir bedienen uns hier einfach des CustomerMapper. Diesem geben wir
//	     * einfach den in dem Account-Objekt enthaltenen Fremdschlüssel für den
//	     * Kontoinhaber. Der CustomerMapper lässt uns dann diese ID in ein Objekt
//	     * auf.
//	     */
//	    return ProfilMapper.profilMapper().findByFremdschluessel(nutzerprofil.getOwnerID());
//	  }
}

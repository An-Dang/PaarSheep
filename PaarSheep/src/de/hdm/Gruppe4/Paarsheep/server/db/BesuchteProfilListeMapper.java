package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.*;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Diese Klasse beinhaltet alle Methoden, um die bereits besuchten Profile eines
 * User zu verwalten. Die Klasse wird nur einmal instantiiert, dabei spricht man von
 * einem sogenannten <b> Singleton </b>.
 * 
 * @author thies, andang, Hauler
 *
 */
public class BesuchteProfilListeMapper {
	
	/**
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.	 
	 */
	private static BesuchteProfilListeMapper besuchteProfilListeMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected BesuchteProfilListeMapper() {
	}

	/**
	 * Es kann nur eine einzige Instanz von BesuchteProfilListeMapper erzeugt
	 * werden.
	 * 
	 * <b>Fazit:</b> BesuchteProfilListeMapper sollte nicht mittels
	 * <code>new</code> instantiiert werden, sondern stets durch Aufruf dieser
	 * statischen Methode.
	 * 
	 * @return besuchteProfilListeMapper <code>BesuchteProfilListeMapper</code>-Objekt.
	 * @see besuchteProfilListeMapper
	 */
	public static BesuchteProfilListeMapper besuchteProfilListeMapper() {
		if (besuchteProfilListeMapper == null) {
			besuchteProfilListeMapper = new BesuchteProfilListeMapper();
		}

		return besuchteProfilListeMapper;
	}

	/**
	 * Einfügen eines <code>BesuchteProfilListe</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Primärschlüssel des übergebenen Objekts geprüft und
	 * ggf. berichtigt.
	 * 
	 * @param besuchterID
	 * @param besuchteID
	 * 					gibt die beiden ProfilIDs der betroffenen Profile an
	 * @param besuchteProfilListe
	 *            das zu speichernde Objekt
	 * @param con Datenbankverbindung herstellen
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public int insert(int besuchterID, int besuchteID) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt = con.createStatement();

			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO BesuchteProfilListe ( BesucherID , BesuchteID) " + "VALUES (" + besuchterID
					+ "," + besuchteID + ")");

		} catch (SQLException e) {

		}

		/*
		 * Rückgabe, des evtl. korrigierten besuchteProfilListe.
		 */
		return besuchteID;
	}

	/**
	 * Profil von BesuchteProfilListe entfernen
	 * 
	 * @param besuchteProfilListe das aus der DB zu löschende "Objekt"
	 * @param con Datenbankverbindung herstellen
	 */
	public void delete(BesuchteProfilListe besuchteProfilListe) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate(
					"DELETE FROM BesuchteProfilListe " + "WHERE BesuchteProfilListeID=" + besuchteProfilListe.getID());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Löschen der BesuchteProfilListe (<code>BesuchteProfilListe</code>-Objekt)
	 * eines Nutzerprofils. Diese Methode sollte aufgerufen werden, bevor ein
	 * <code>Nutzerprofil</code> -Objekt gelöscht wird.
	 * 
	 * @param nutzerprofil das <code>Nutzerprofil</code>-Objekt, zu dem die
	 *            BesuchteProfilListe gehört
	 * @param con Datenbankverbindung herstellen
	 */
	public void deleteBesuchteProfilListeOf(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM BesuchteProfilListe " + "WHERE BesuchterID=" + nutzerprofil.getID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auslesen aller BesuchteProfilListe eines durch Fremdschlüssel
	 * (BesucherID) gegebenen Nutzerprofils.
	 * 
	 * @param nutzerprofil aktuelles Nutzerprofil
	 * @param con Datenbankverbindung herstellen
	 * @param BesuchteProfilListe
	 *            Schlüssel des zugehörigen Nutzerprofils.
	 * @return result Nutzerprofil-Objekt als Array-List
	 */
	public ArrayList<BesuchteProfilListe> findByBesucherID(int nutzerprofil) {
		Connection con = DBConnection.connection();
		ArrayList<BesuchteProfilListe> result = new ArrayList<BesuchteProfilListe>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM BesuchteProfilListe WHERE BesucherID =" + nutzerprofil);

			/*
			 * Für jeden Eintrag im Suchergebnis wird nun ein
			 * BesuchteProfilListe-Objekt erstellt.
			 */

			while (rs.next()) {
				BesuchteProfilListe bpl = new BesuchteProfilListe();

				bpl.setBesuchteID(rs.getInt("BesuchteID"));
				bpl.setBesucherID(rs.getInt("BesucherID"));

				// Hinzufügen des neuen Objekts zur ArrayList
				result.add(bpl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ArrayList zurückgeben
		return result;
	}

}

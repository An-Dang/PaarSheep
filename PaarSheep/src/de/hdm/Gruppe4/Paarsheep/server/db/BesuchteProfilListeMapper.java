package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.*;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * @author andang
 *
 */
public class BesuchteProfilListeMapper {
	/**
	 * Die Klasse BesuchteProfilLiMapper wird nur einmal instantiiert. Man
	 * spricht hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @author Dang
	 * @author Hauler
	 * @author Thies
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
	 * werden
	 * 
	 * <b>Fazit:</b> BesuchteProfilListeMapper sollte nicht mittels
	 * <code>new</code> instantiiert werden, sondern stets durch Aufruf dieser
	 * statischen Methode.
	 * 
	 * @return DAS <code>BesuchteProfilListeMapper</code>-Objekt.
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
	 * @param besuchteProfilListe
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public BesuchteProfilListe insert(BesuchteProfilListe besuchteProfilListe) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(BesuchteProfilListeID) AS maxid " + "FROM BesuchteProfilListe ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * merkzettel erhält den bisher maximalen, nun um 1
				 * inkrementierten Primärschlüssel.
				 */
				besuchteProfilListe.setID(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO BesuchteProfilListe (BesuchteProfilListeID, BesuchteID , BesucherID "
						+ "VALUES (" + besuchteProfilListe.getID() + "," + besuchteProfilListe.getBesuchteID() + ","
						+ besuchteProfilListe.getBesucherID() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/**
		 * Rückgabe, des evtl. korrigierten besuchteProfilListe.
		 */
		return besuchteProfilListe;
	}

	/**
	 * Profil von BesuchteProfilListe entfernen
	 * 
	 * @param besuchteProfilListe
	 *            das aus der DB zu löschende "Objekt"
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
	 * @param nutzerprofil 
	 * 
	 *            das <code>Nutzerprofil</code>-Objekt, zu dem die
	 *            BesuchteProfilListe gehört
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
	 * @param nutzerprofil 
	 * 
	 * @param BesuchteProfilListe
	 *            Schlüssel des zugehörigen Nutzerprofils.
	 * @return ArrayList Nutzerprofil-Objekt
	 */
	public ArrayList<Nutzerprofil> findByBesucherID(int nutzerprofil) {
		Connection con = DBConnection.connection();
		ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzerprofil.Nachname, Nutzerprofil.Vorname "
					+ "FROM Nutzerprofil, BesuchteProfilListe" + "WHERE BesucherID=" + "nutzerprofil.getID()"
					+ "AND nutzerprofil.nutzerprofilid = besuchteProfilListe.besuchteID");

			/**
			 * SELECT * FROM besuchteProfilListe INNER JOIN nutzerprofil " +
			 * "ON besuchteProfilListe.besucherID = nutzerprofil.nutzerprofilID "
			 * + "WHERE besuchteProfilListe.besuchteID = "
			 * +nutzerprofil.getID());
			 */

			/**
			 * Für jeden Eintrag im Suchergebnis wird nun ein
			 * BesuchteProfilListe-Objekt erstellt.
			 */

			while (rs.next()) {
				Nutzerprofil np = new Nutzerprofil();

				np.setVorname(rs.getString("Vorname"));
				np.setNachname(rs.getString("Nachname"));

				// Hinzufügen des neuen Objekts zur ArrayList
				result.add(np);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ArrayList zurückgeben
		return result;
	}

}

package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Mapper-Klasse, die <code>SperrlisteMapper</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * 
 * @author Thies
 * @author Hauler
 * @author Dang
 */

public class SperrlisteMapper {

	/**
	 * Die Klasse SperrlisteMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */
	private static SperrlisteMapper sperrlisteMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected SperrlisteMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>SperrlisteMapper.sperrlisteMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>sperrlisteMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> SperrlisteMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return sperrlisteMapper <code>SperrlisteMapper</code>-Objekt.
	 * @see sperrlisteMapper
	 */
	public static SperrlisteMapper sperrlisteMapper() {
		if (sperrlisteMapper == null) {
			sperrlisteMapper = new SperrlisteMapper();
		}
		return sperrlisteMapper;
	}

	/**
	 * Einfügen eines <code>SperrlisteMapper</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Primärschlüssel des übergebenen Objekts geprüft und
	 * ggf. berichtigt.
	 * 
	 * @param fremdprofilID Profil ID des gesperrten Users
	 * @param nutzerprofilID Profil ID des sperrenden Users
	 * @param con Datenbankverbindung
	 * @param stmt Statment 
	 *         
	 * @return fremdprofilID Die ID des Gesperrten wird zurueckgegeben
	 */
	public int insert(int nutzerprofilID, int fremdprofilID) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO Kontaktsperrliste (SperrenderID, GesperrterID) " + "VALUES ("
					+ nutzerprofilID + "," + fremdprofilID + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fremdprofilID;
	}

	/**
	 * Profil von der Sperrliste entfernen
	 * @param SperrenderID aktueller User
	 * @param GesperrterID ID des bisher gesperrten Profils
	 * @param con Datenbankverbindung
	 * @param stmt Statement
	 */
	public void delete(int SperrenderID, int GesperrterID) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Kontaktsperrliste WHERE GesperrterID= " + GesperrterID
					+ " AND SperrenderID= " + SperrenderID);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Löschen der Sperrliste (<code>Sperrliste</code>-Objekte) eines
	 * Nutzerprofils. Diese Methode sollte aufgerufen werden, bevor ein
	 * <code>Nutzerprofil</code> -Objekt gelöscht wird.
	 * 
	 * @param nutzerprofil das <code>Nutzerprofil</code>-Objekt, zu dem die Sperrliste
	 *            gehören
	 * @param con Datenbankverbindung
	 * @param stmt Statment
	 */
	public void deleteSperrlisteOf(int nutzerprofil) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM Kontaktsperrliste " + "WHERE SperrenderID =" + nutzerprofil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auslesen der Kontaktsperrliste anhand von Fremdschluesseln (Sperrender ID)
	 * des uebergebenen Nutzerprofils.
	 * @param nutzerprofilID Profil ID des aktuellen Users
	 * @param con Datenbankverbindung
	 * @param result ArrayList, in welche die Profile der Sperrliste geschrieben werden
	 * @param stmt Kontaktsperrliste wird ausgelesen
	 * 
	 * @return result ArrayList Nutzerprofil-Objekt
	 */
	public ArrayList<Nutzerprofil> findBySperrender(int nutzerprofilID) {
		Connection con = DBConnection.connection();
		ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();
		try {
			Statement stmt = con.createStatement();
			// Im Stmt wird die Kontaktsperrliste von dem eingeloggtem Nutzer
			// ausgelesen
			ResultSet rs = stmt
					.executeQuery("SELECT Nutzerprofil.NutzerprofilID, Nutzerprofil.vorname, Nutzerprofil.nachname"
							+ " FROM Nutzerprofil, Profil, Kontaktsperrliste WHERE Kontaktsperrliste.SperrenderID ="
							+ nutzerprofilID + " AND Nutzerprofil.NutzerprofilID = Kontaktsperrliste.GesperrterID "
							+ " AND Profil.ProfilID = Kontaktsperrliste.GesperrterID");
			while (rs.next()) {
				Nutzerprofil np = new Nutzerprofil();
				np.setID(rs.getInt(1));
				np.setVorname(rs.getString("Vorname"));
				np.setNachname(rs.getString("Nachname"));
				result.add(np);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnis ArrayList zurückgeben
		return result;
	}

	/**
	 * Der Status eines Profils zu einem anderen wird geprueft. Dabei geht es darum,
	 * heraus zu finden, ob ob ein Profil von einem anderen gesperrt wurde. In diesem
	 * Fall darf das gesperrte Profil beim sperrenden nirgends auftauchen und das gesperrte
	 * Profil darf das sperrende Profil nicht mehr sehen/finden.
	 * @param nutzerprofil User ID des aktuellen Nutzer (fuer den geprueft wird)
	 * @param con Datenbankverbindung
	 * @param stmt Statement
	 * @param fremdprofilID ProfilID des fremden Profils, das geprueft werden soll
	 * @return sperrStatus liefert Status der Sperre zurueck
	 */
	public int pruefeSperrstatusFremdprofil(int nutzerprofil, int fremdprofilID) {
		Connection con = DBConnection.connection();
		int sperrStatus = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * From Kontaktsperrliste Where GesperrterID = " + fremdprofilID);
			if (rs.next()){
				sperrStatus = 1;
			}else{
				sperrStatus = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sperrStatus;
	}
}

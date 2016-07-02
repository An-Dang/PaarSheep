package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

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
	 * @return nutzerprofilMapper <code>NutzerprofilMapper</code>-Objekt.
	 */
	public static NutzerprofilMapper nutzerprofilMapper() {
		if (nutzerprofilMapper == null) {
			nutzerprofilMapper = new NutzerprofilMapper();
		}
		return nutzerprofilMapper;
	}

	/**
	 * Diese Methode bezieht ihre Informationen aus der
	 * PartnerboerseAdministrationImpl und erstellt mit diesen einen neuen
	 * Nutzer in der Datenbank.
	 * 
	 * @param nutzerprofil
	 * @return nutzerprofil
	 */
	public Nutzerprofil insert(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Der höchste Wert des Primärschlüssel der Tabelle Profil wird
			 * ermittelt
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(ProfilID) AS maxid " + "FROM Profil ");

			if (rs.next()) {

				/*
				 * Das Attribut von Nutzerprofil Nutzprofil_ProfilID wird anhand
				 * des maximalen Wertes von ProfilID vergeben und + 1 gesetzt.
				 * ACHTUNG!!!: Dieses Attribut setzt bei Profil den
				 * Primärschlüssel ALS AUCH bei Nutzerprofil den Fremdschlüssel.
				 */
				nutzerprofil.setProfilID(rs.getInt("maxid") + 1);

				// Dieses Statement übergibt die Werte an die Tabelle Profil
				stmt.executeUpdate(
						"INSERT INTO Profil (ProfilID, Geschlecht, Haarfarbe, Koerpergroesse, Raucher, Religion) "
								+ "VALUES(" + nutzerprofil.getProfilID() + ",'" + nutzerprofil.getGeschlecht() + "','"
								+ nutzerprofil.getHaarfarbe() + "','" + nutzerprofil.getKoerpergroesse() + "','"
								+ nutzerprofil.getRaucher() + "','" + nutzerprofil.getReligion() + "')");

				// Dieses Statement übergibt die Werte an die Tabelle
				// Nutzerprofil
				stmt.executeUpdate("INSERT INTO Nutzerprofil " + "(GoogleMail, Geburtsdatum, Vorname, Nachname, "
						+ "NutzerprofilID) " + "VALUES ('" + nutzerprofil.getEmailAddress() + "','"
						+ nutzerprofil.getGeburtsdatum() + "','" + nutzerprofil.getVorname() + "','"
						+ nutzerprofil.getNachname() + "'," + nutzerprofil.getProfilID() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Rückgabe des nutzerprofil.
		return nutzerprofil;
	}

	/**
	 * Update des Nutzerprofils nach Bearbeitung durch User.
	 * 
	 * @param con
	 *            Datenbankverbindung
	 * @param stmt
	 *            Statement
	 * @param np
	 *            Nutzerprofil
	 */
	public void bearbeiteNutzerprofil(Nutzerprofil np) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Dieses Statement übergibt die Werte an die Tabelle Profil
			stmt.executeUpdate("UPDATE Nutzerprofil SET Vorname=\"" + np.getVorname() + "\", Nachname =\""
					+ np.getNachname() + "\", " + "Geburtsdatum = \"" + np.getGeburtsdatum() + "\""
					+ "WHERE NutzerprofilID = " + np.getProfilID());

			stmt = con.createStatement();

			stmt.executeUpdate("UPDATE Profil" + " SET Geschlecht = \"" + np.getGeschlecht() + "\", "
					+ " Haarfarbe = \"" + np.getHaarfarbe() + "\"," + "Koerpergroesse =\"" + np.getKoerpergroesse()
					+ "\", " + "Raucher =\"" + np.getRaucher() + "\"," + "Religion =\"" + np.getReligion()
					+ "\" WHERE ProfilID = " + np.getProfilID());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Löschen der Daten eines <code>Nutzerprofil</code>-Objekts aus der
	 * Datenbank. Wenn Nutzerprofil gelöscht wird, wird alles gelöscht!
	 * 
	 * @param nutzerprofil
	 *            das aus der DB zu löschende "Objekt"
	 * @param con
	 *            Datenbankverbindung
	 * @param stmt
	 *            Statement
	 */
	public void deleteNutzerprofil(int nutzerprofilID) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Information WHERE Information.ProfilID =" + nutzerprofilID);
			stmt.executeUpdate("DELETE FROM Suchprofil WHERE Suchprofil.NutzerprofilID =" + nutzerprofilID);
			stmt.executeUpdate("DELETE FROM BesuchteProfilListe WHERE BesucherID =" + nutzerprofilID);
			stmt.executeUpdate("DELETE FROM BesuchteProfilListe WHERE BesuchteID =" + nutzerprofilID);
			stmt.executeUpdate("DELETE FROM Kontaktsperrliste WHERE SperrenderID =" + nutzerprofilID);
			stmt.executeUpdate("DELETE FROM Kontaktsperrliste WHERE GesperrterID =" + nutzerprofilID);
			stmt.executeUpdate("DELETE FROM Merkzettel WHERE MerkenderID =" + nutzerprofilID);
			stmt.executeUpdate("DELETE FROM Merkzettel WHERE GemerkteID =" + nutzerprofilID);
			stmt.executeUpdate("DELETE FROM Nutzerprofil WHERE Nutzerprofil.NutzerprofilID =" + nutzerprofilID);
			stmt.executeUpdate("DELETE FROM Profil WHERE Profil.ProfilID =" + nutzerprofilID);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auslesen aller Nutzerprofile.
	 * 
	 * @param nutzerproffilID
	 *            profil ID des aktuellen Profils
	 * @param con
	 *            Datenbankverbindung
	 * @param result
	 *            ArrayList, in die die Profile geschrieben werden
	 * @param stmt
	 *            Statement
	 * 
	 * @return result Ein ArrayList mit Nutzerprofil-Objekten, die sämtliche
	 *         Nutzerprofil repräsentieren. Bei evtl. Exceptions wird ein
	 *         partiell gefüllter oder ggf. auch leerer ArrayList
	 *         zurückgeliefert.
	 */
	public ArrayList<Nutzerprofil> findAllNutzerprofil(int nutzerproffilID) {
		Connection con = DBConnection.connection();

		// ArrayList vorbereiten
		ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzerprofil, Profil Where NutzerprofilID Not Like "
					+ nutzerproffilID + " AND NutzerprofilID = ProfilID ");

			// Für jeden Eintrag im Suchergebnis wird nun ein
			// Nutzerprofil-Objekt erstellt.
			while (rs.next()) {
				Nutzerprofil nutzerprofil = new Nutzerprofil();
				nutzerprofil.setID(rs.getInt("NutzerprofilID"));
				nutzerprofil.setVorname(rs.getString("Vorname"));
				nutzerprofil.setNachname(rs.getString("Nachname"));
				nutzerprofil.setGeburtsdatum(rs.getDate("Geburtsdatum"));
				nutzerprofil.setGeschlecht(rs.getString("Geschlecht"));
				nutzerprofil.setKoerpergroesse(rs.getInt("Koerpergroesse"));
				nutzerprofil.setHaarfarbe(rs.getString("Haarfarbe"));
				nutzerprofil.setRaucher(rs.getString("Raucher"));
				nutzerprofil.setReligion(rs.getString("Religion"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.add(nutzerprofil);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Auslesen des Nutzerporfils eines durch Fremdschlüssel (NutzerprofilID.)
	 * gegebenen Profils.
	 * 
	 * @param fremdprofilID
	 *            Profil Id des fremden Profils
	 * @param con
	 *            Datenbankverbindung
	 * @param stmt
	 *            Statement
	 * @param n
	 *            Nutzerprofil, in welche die Ergebnisse geschrieben werden
	 * @return null
	 */
	public Nutzerprofil findFremdprofil(int fremdprofilID) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzerprofil, Profil " + "WHERE ProfilID= " + fremdprofilID
					+ " AND NutzerprofilID = " + fremdprofilID);

			/*
			 * Es kann max. ein Ergebnis-Tupel zurückgegeben werden. Prüfen, ob
			 * ein Ergebnis-Tupel vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Nutzerprofil-Objekt umwandeln.
				Nutzerprofil n = new Nutzerprofil();
				n.setID(rs.getInt("NutzerprofilID"));
				n.setVorname(rs.getString("Vorname"));
				n.setNachname(rs.getString("Nachname"));
				n.setGeburtsdatum(rs.getDate("Geburtsdatum"));
				n.setGeschlecht(rs.getString("Geschlecht"));
				n.setKoerpergroesse(rs.getInt("Koerpergroesse"));
				n.setHaarfarbe(rs.getString("Haarfarbe"));
				n.setRaucher(rs.getString("Raucher"));
				n.setReligion(rs.getString("Religion"));
				n.setEmailAddress(rs.getString("GoogleMail"));
				return n;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	/**
	 * Auslesen aller Nutzerprofile.
	 * 
	 * @param nutzerprofil
	 *            Nutzerprofil wird angelegt fuer Suchprofile
	 * @param con
	 *            Datenbankverbindung
	 * @param stmt
	 *            Statement
	 * @param result
	 *            ArrayList, in welche die Vorschlaege geschrieben werden
	 * 
	 * @return result Ein ArrayList mit Nutzerprofil-Objekten, die sämtliche
	 *         Nutzerprofil repräsentieren. Bei evtl. Exceptions wird ein
	 *         partiell gefüllter oder ggf. auch leerer ArrayList
	 *         zurückgeliefert.
	 */
	public ArrayList<Nutzerprofil> findPartnervorschlägen(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		// ArrayList vorbereiten
		ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT NutzerprofilID, Vorname, Nachname FROM Nutzerprofil Where NutzerprofilID Not Like '%"
							+ nutzerprofil.getID() + "");

			// Für jeden Eintrag im Suchergebnis wird nun ein
			// Nutzerprofil-Objekt erstellt.
			while (rs.next()) {

				nutzerprofil.setID(rs.getInt("NutzerprofilID"));
				nutzerprofil.setVorname(rs.getString("Vorname"));
				nutzerprofil.setNachname(rs.getString("Nachname"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.add(nutzerprofil);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * In dieser Methode wird überprüft ob der Nutzer bereits in der Datenbank
	 * vorhanden ist.
	 *
	 * Die Überprüfung wird anhand der Emailadresse vorgenommen, welche in dem
	 * Nutzerprofilobjekt loginInfo enthalten ist.
	 * 
	 * @param loginInfo
	 * @param nutzerprofil
	 *            Nutzerdaten des Users werden hineingeladen
	 * @param con
	 *            Datenbankverbindung
	 * @param email
	 *            Email Adresse es Users, der sich einloggen will
	 * @return nutzerprofil Profildaten werden darin zurueckgegeben.
	 */
	public Nutzerprofil checkStatus(Nutzerprofil loginInfo) {
		Nutzerprofil nutzerprofil = loginInfo;

		Connection con = DBConnection.connection();
		String email = loginInfo.getEmailAddress();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzerprofil WHERE" + " GoogleMail = '" + email + "'");

			/*
			 * Wenn der Nutzer in der Datenbank vorhanden ist, werden die
			 * Informationen aus dem Eintrag in der Datenbank in dem Objekt
			 * nutzerprofil gespeichert Außerdem wird der Status des Objekts
			 * nutzerprofil auf true gesetzt um die Überprüfung des Objekts als
			 * in der Datenbank vorhanden zurückzugeben
			 */
			if (rs.next()) {

				nutzerprofil.setStatus(true);
				nutzerprofil.setProfilID(rs.getInt("NutzerprofilID"));
				nutzerprofil.setEmailAddress(rs.getString("GoogleMail"));

				/*
				 * Hier werden alle Informationen aus der Tabelle profil
				 * gezogen, in welchen die ProfilID identisch ist mit dem
				 * Fremdschlüssel des Nutzerprofils welcher soeben in dem
				 * ResultSet rs an der Stelle 5 gespeichert wurde
				 */

				ResultSet rs2 = stmt
						.executeQuery("SELECT * FROM Profil WHERE " + "ProfilID = " + rs.getInt("NutzerprofilID"));
				if (rs2.next()) {
					nutzerprofil.setID(rs2.getInt("ProfilID"));
				}

				/*
				 * Wenn die Email nicht in der DAtenbak vorhanden ist, wird der
				 * Status im Objekt auf false gesetzt, um bei der Überprüfung
				 * den Status als nicht in der Datenbank vorhanden
				 * zurückzugeben.
				 */
			} else {

				nutzerprofil.setStatus(false);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return nutzerprofil;
	}

	/**
	 * Nutzerprofil mit vorgegebener Profil-ID suchen.
	 * 
	 * @param profilID
	 * @param con
	 *            Datenbankverbindung
	 * @param stmt
	 *            Statement
	 * @param n
	 *            Nutzerprofil, welches per ID gefunden werden soll
	 * @return null Nutzerprofil wird zurueckgegeben
	 */
	public Nutzerprofil findByNutzerprofilId(int profilID) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzerprofil, Profil " + "WHERE ProfilID= " + profilID
					+ " AND NutzerprofilID= " + profilID);

			/*
			 * Es kann max. ein Ergebnis-Tupel zurückgegeben werden. Prüfen, ob
			 * ein Ergebnis-Tupel vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Nutzerprofil-Objekt umwandeln.
				Nutzerprofil n = new Nutzerprofil();
				n.setProfilID(rs.getInt("NutzerprofilID"));
				n.setVorname(rs.getString("Vorname"));
				n.setNachname(rs.getString("Nachname"));
				n.setGeschlecht(rs.getString("Geschlecht"));
				n.setGeburtsdatum(rs.getDate("Geburtsdatum"));
				n.setKoerpergroesse(rs.getInt("Koerpergroesse"));
				n.setHaarfarbe(rs.getString("Haarfarbe"));
				n.setRaucher(rs.getString("Raucher"));
				n.setReligion(rs.getString("Religion"));
				n.setEmailAddress(rs.getString("GoogleMail"));
				return n;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Geordnete Partnervorschlaege fuer ein Nutzerprofil anhand eines
	 * Suchprofils auslesen.
	 * 
	 * @param profilId
	 *            Die Profil-ID des Nutzerprofils, fuer das die
	 *            Partnervorschlaege ausgelesen werden sollen.
	 * @param suchprofilId
	 *            Die Profil-ID des Suchprofils, fuer das die Partnervorschlaege
	 *            ausgelesen werden sollen.
	 * @param con
	 *            Datenbankverbindung
	 * @param result
	 *            ArrayList, in welche die Resultate der Abfrage gespeichert
	 *            werden
	 * @param nutzerprofil
	 *            Darin werden die einzelnen Daten der Profile gespeichert
	 * @param stmt
	 *            Statement
	 * @return result Liste von vorgeschlagenenen Nutzerprofil-Objekten.
	 */

	public List<Nutzerprofil> findAllPartnervorschlaegeSuchprofil(int profilId, int suchprofilId) {
		Connection con = DBConnection.connection();
		List<Nutzerprofil> result = new ArrayList<Nutzerprofil>();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT Nutzerprofil.NutzerprofilID, Nutzerprofil.Vorname, Nutzerprofil.Nachname, "
							+ " Nutzerprofil.Geburtsdatum, Profil.Geschlecht, Profil.Koerpergroesse, Profil.Haarfarbe,"
							+ " Profil.Raucher, Profil.Religion" + " FROM Nutzerprofil LEFT JOIN Profil "
							+ "ON Nutzerprofil.NutzerprofilID = Profil.Profilid"
							+ "WHERE Nutzerprofil.NutzerprofilID !=" + profilId);

			while (rs.next()) {
				Nutzerprofil nutzerprofil = new Nutzerprofil();
				nutzerprofil.setProfilID(rs.getInt("NutzerprofilID"));
				nutzerprofil.setVorname(rs.getString("Vorname"));
				nutzerprofil.setNachname(rs.getString("Nachname"));
				nutzerprofil.setGeburtsdatum(rs.getDate("Geburtsdatum"));
				nutzerprofil.setGeschlecht(rs.getString("Geschlecht"));
				nutzerprofil.setHaarfarbe(rs.getString("Haarfarbe"));
				nutzerprofil.setKoerpergroesse(rs.getInt("Koerpergroesse"));
				nutzerprofil.setRaucher(rs.getString("Raucher"));
				nutzerprofil.setReligion(rs.getString("Religion"));

				result.add(nutzerprofil);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Die bisher nicht betrachteten Profile durch den aktuellen User werden
	 * gesucht und zurueckgegeben.
	 * 
	 * @param con
	 *            Datenbankverbindung
	 * @param result
	 *            ArrayList, in die die gefundenen Profile geschrieben werden
	 * @param stmt
	 *            Statement
	 * @param profilID
	 *            ID des aktuellen Profils
	 * @return result liefert Liste von Profilen zurueck, die nicht betrachtet
	 *         wurden
	 */
	public ArrayList<Nutzerprofil> findUnangeseheneNutzerprofileByID(int profilID) {
		Connection con = DBConnection.connection();

		ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Profil " + "INNER JOIN Nutzerprofil "
					+ "ON Profil.profilID = Nutzerprofil.NutzerprofilID "
					+ "WHERE Nutzerprofil.NutzerprofilID Not Like '%" + profilID + "'"
					+ "And Nutzerprofil.NutzerprofilID " + "NOT IN (SELECT BesuchteProfilListe.BesuchteID "
					+ "FROM BesuchteProfilListe WHERE BesuchteProfilListe.BesucherID = " + profilID + ")");

			while (rs.next()) {
				Nutzerprofil nutzerprofil = new Nutzerprofil();
				nutzerprofil.setProfilID(rs.getInt("NutzerprofilID"));
				nutzerprofil.setVorname(rs.getString("Vorname"));
				nutzerprofil.setNachname(rs.getString("Nachname"));
				nutzerprofil.setGeburtsdatum(rs.getDate("Geburtsdatum"));
				nutzerprofil.setGeschlecht(rs.getString("Geschlecht"));
				nutzerprofil.setHaarfarbe(rs.getString("Haarfarbe"));
				nutzerprofil.setKoerpergroesse(rs.getInt("Koerpergroesse"));
				nutzerprofil.setRaucher(rs.getString("Raucher"));
				nutzerprofil.setReligion(rs.getString("Religion"));

				result.add(nutzerprofil);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}
}
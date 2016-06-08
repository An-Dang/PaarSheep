package de.hdm.Gruppe4.Paarsheep.server.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;


import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Nutzerprofil</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte
 * können in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
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
	 * Einfügen eines <code>Nutzerpofil</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und
	 * ggf. berichtigt.
	 * 
	 * @param nutzerprofil
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 * @throws Exception
	 */

	// -----------------------------------------------------------------------------
	// Diese Methode bezieht ihre Informationen aus der
	// PartnerboerseAdministrationImpl und erstellt mit diesen einen neuen
	// Nutzer in der Datenbank

	public Nutzerprofil insert(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Der höchste Wert des Primärschlüssel der Tabelle Profil wird
			// ermittelt
			ResultSet rs = stmt.executeQuery("SELECT MAX(ProfilID) AS maxid " + "FROM Profil ");

			if (rs.next()) {

				// Das Attribut von Nutzerprofil Nutzprofil_ProfilID wird anhand
				// des
				// maximalen Wertes von ProfilID vergeben und + 1 gesetzt.
				// ACHTUNG!!!: Dieses Attribut setzt bei Profil den
				// Primärschlüssel
				// ALS AUCH bei Nutzerprofil den Fremdschlüssel.

				nutzerprofil.setProfilID(rs.getInt("maxid") + 1);

				// Dieses Statement übergibt die Werte an die Tabelle Profil
				stmt.executeUpdate(
						"INSERT INTO profil (ProfilID, Geschlecht, Haarfarbe, " + "Koerpergroesse, Raucher, Religion) "
								+ "VALUES(" + nutzerprofil.getProfilID() + ",'" + nutzerprofil.getGeschlecht() + "','"
								+ nutzerprofil.getHaarfarbe() + "','" + nutzerprofil.getKoerpergroesse() + "','"
								+ nutzerprofil.getRaucher() + "','" + nutzerprofil.getReligion() + "')");

				// Der höchste Wert des Primärschlüssel von Nutzerprofil wird
				// ermittelt
				ResultSet rs2 = stmt.executeQuery("SELECT MAX(NutzerprofilID) " + "AS maxid " + "FROM nutzerprofil ");

				if (rs2.next()) {

					// Das Attribut von Nutzerprofil Nutzprofil_ProfilID wird
					// anhand des maximalen Wertes von ProfilID vergeben und
					// + 1 gesetzt.
					nutzerprofil.setID(rs2.getInt("maxid") + 1);

					stmt = con.createStatement();

					// Dieses Statement übergibt die Werte an die Tabelle
					// Nutzerprofil
					stmt.executeUpdate("INSERT INTO nutzerprofil "
							+ "(GoogleMail, NutzerprofilID, Geburtsdatum, Vorname, Nachname, "
							+ "Nutzerprofil_ProfilID) " + "VALUES ('" + nutzerprofil.getEmailAddress() + "',"
							+ nutzerprofil.getID() + ",'" + nutzerprofil.getGeburtsdatum() + "','"
							+ nutzerprofil.getVorname() + "','" + nutzerprofil.getNachname() + "',"
							+ nutzerprofil.getProfilID() + ")");

				}
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * Rückgabe des nutzerprofil.
		 */
		return nutzerprofil;
	}
//-----------------------------------------------------------------------------
	
	public Nutzerprofil bearbeiteNutzerprofil(Nutzerprofil profil ) {
		Nutzerprofil nutzerprofil = profil;
		
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

				String religion = nutzerprofil.getReligion();
				int koerpergroesse = nutzerprofil.getKoerpergroesse();
				String haarfarbe = nutzerprofil.getHaarfarbe();
				String raucher = nutzerprofil.getRaucher();
				String geschlecht = nutzerprofil.getGeschlecht();

				// Dieses Statement übergibt die Werte an die Tabelle Profil
				stmt.executeUpdate(
						"UPDATE profil SET Geschlecht = '" + geschlecht+ "', "
								+ "Haarfarbe ='" + haarfarbe + "' ,"
								+ "Koerpergroesse=" + koerpergroesse + ", "
								+ "Raucher='" + raucher + "', Religion='" 
								+ religion + "' WHERE ProfilID = " + nutzerprofil.getID());
		
						
				String vorname = nutzerprofil.getVorname();
				String nachname = nutzerprofil.getNachname();
			
				
				Statement stmt2 = con.createStatement();
				
				
				stmt2.executeUpdate("UPDATE nutzerprofil SET Vorname='" 
				+ vorname + "', Nachname='" + nachname + "', " 
				+ "Geburtsdatum='" + nutzerprofil.getGeburtsdatum() + "' "
				+ "WHERE Nutzerprofil_ProfilID = " + nutzerprofil.getProfilID()); 
						
		
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		

		return nutzerprofil;
	}

	
	
// ----------------------------------------------------------------------------
//In dieser Methode wird überprüft ob der Nutzer bereits in der Datenbank 
//vorhanden ist.
	
//Die überpfrüung wird anhand der Emailadresse vorgenommen, welche in dem 
//Nutzerprofilobjekt loginInfo enthalten ist, vorgenommen	
	public Nutzerprofil checkStatus(Nutzerprofil loginInfo) {
		Nutzerprofil nutzerprofil = loginInfo;

		Connection con = DBConnection.connection();
		String email = loginInfo.getEmailAddress();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM nutzerprofil WHERE"
			+ " " + "GoogleMail = '" + email + "';");
			
			//Wenn der Nutzer in der Datenbank vorhanden ist, werden die 
			//Informationen aus dem Eintrag in der Datenbank in dem Objekt 
			//nutzerprofil gespeichert
			
			//Außerdem wird der Status des Objekts nutzerprofil auf true 
			//gesetzt um die Überprüfung des Objekts als in der Datenbank 
			//vorhanden zurückzugeben
			if (rs.next()) {

				nutzerprofil.setStatus(true);
				nutzerprofil.setProfilID(rs.getInt(1));
				nutzerprofil.setGeburtsdatum(rs.getDate(2));
				nutzerprofil.setVorname(rs.getString(3));
				nutzerprofil.setNachname(rs.getString(4));
				nutzerprofil.setEmailAddress(rs.getString(5));
				
			//Hier werden alle Informationen aus der Tabelle profil gezogen, in
			//welchen die ProfilID identisch ist mit dem Fremdschlüssel des 
			//Nutzerprofils welcher soeben in dem ResultSet rs an der Stelle 5 
			//gespeichert wurde	
				
				ResultSet rs2 = stmt.executeQuery("SELECT * FROM profil WHERE " 
				+ "ProfilID = '" + rs.getInt(6) + "';");
				if (rs2.next()) {
					nutzerprofil.setID(rs2.getInt(1));
					nutzerprofil.setReligion(rs2.getString(2));
					nutzerprofil.setKoerpergroesse(rs2.getInt(3));
					nutzerprofil.setHaarfarbe(rs2.getString(4));
					nutzerprofil.setRaucher(rs2.getString(5));
					nutzerprofil.setGeschlecht(rs2.getString(6));
					
					
				}
				
				//Wenn die Email nicht in der DAtenbak vorhanden ist, wird der 
				//Status im Objekt auf false gesetzt, um bei der Überprüfung 
				// den Status als nicht in der Datenbank vorhanden
				//zurückzugeben
			} else {

				
				nutzerprofil.setStatus(false);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return nutzerprofil;
	}

	// ----------------------------------------------------------------------------

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param nutzerprofil
	 *            das Objekt, das in die DB geschrieben werden soll
	 * 
	 * @return das als Parameter übergebene Objekt
	 */
	public Nutzerprofil update(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE Nutzerprofil INNER JOIN Profil"
					+ "ON Nutzerprofil.Nutzerprofil_ProfilID = ProfilID"
					+ "SET vorname=\", nachname=\", geburtsdatum=\""
					+ "Religion=\", Koerpergroesse=\", Haarfarbe=\", Raucher=\", Geschlecht=\"" + "WHERE profilID="
					// Richtig?! damit wir nutzerprofil.getNutzerprofil_ProfilID
					+ nutzerprofil.getID());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Um Analogie zu insert(Nutzerprofil nutzerprofil) zu wahren, geben wir
		// nutzerprofil zurück
		return nutzerprofil;
	}

	/**
	 * Löschen der Daten eines <code>Nutzerprofil</code>-Objekts aus der
	 * Datenbank. Wenn Nutzerprofil gelöscht wird, wird alles gelöscht!
	 * 
	 * @param nutzerprofil
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Nutzerprofil " + "WHERE NutzerprofilID=" + nutzerprofil.getID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// -----------------------------------------------------------------------------
	/**
	 * Auslesen aller Nutzerprofile.
	 * 
	 * @return Ein ArrayList mit Nutzerprofil-Objekten, die sämtliche
	 *         Nutzerprofil repräsentieren. Bei evtl. Exceptions wird ein
	 *         partiell gefüllter oder ggf. auch leerer ArrayList
	 *         zurückgeliefert.
	 */
	public ArrayList<Nutzerprofil> findAllNutzerprofil() {
		Connection con = DBConnection.connection();

		// ArrayList vorbereiten
		ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT NutzerprofilID, Vorname, Nachname FROM Nutzerprofil ");

			// Für jeden Eintrag im Suchergebnis wird nun ein
			// Nutzerprofil-Objekt erstellt.
			while (rs.next()) {
				Nutzerprofil nutzerprofil = new Nutzerprofil();
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
	// -----------------------------------------------------------------------------

	/**
	 * Auslesen des Nutzerporfils eines durch Fremdschlüssel
	 * (Nutzerprofil_ProfilID.) gegebenen Profils.
	 * 
	 * @see findByProfil(Profil Nutzerprofil_ProfilID)
	 * @param Nutzerprofil_ProfilID
	 *            Schlüssel des zugehörigen Kunden.
	 */
	public Nutzerprofil findByProfil(Nutzerprofil Nutzerprofil_ProfilID) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT nutzerprofilid, nutzerprofil_profilid FROM Nutzerprofil "
					+ "WHERE nutzerprofil_profilid=" + Nutzerprofil_ProfilID);

			// Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt
			// erstellt.
			while (rs.next()) {
				Nutzerprofil nutzerprofil = new Nutzerprofil();
				nutzerprofil.setID(rs.getInt("Nutzerprofilid"));
				nutzerprofil.setProfilID(rs.getInt("Nutzerprofil_profilID"));

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	// ----------------------------------------------------------------------------

	// Methode um mit Hilfe einer vorher eingetragenen id Nutzerdaten angezeigt
	// zu bekommen.
	public Nutzerprofil readNutzerProfil(int id) {

		// Wir erstellen hier ein Nutzerprofil, welches mit den Informationen
		// welche wir aus der Datenbank bekommen, gefüllt wird.
		Nutzerprofil nutzerprofil = new Nutzerprofil();
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken

			// Hier holen wir uns aus der Profil-Tabelle die allgemeinen
			// Informationen über den Nutzer,
			// der durch die eingegebene id identifiziert wurde und speichern
			// diese in rs.

			ResultSet rs = stmt.executeQuery(
					"SELECT ProfilID, Religion, Koerpergroesse, Haarfarbe, Raucher, Geschlecht FROM profil WHERE ProfilID = "
							+ id);

			if (rs.next()) {

				// Hier holen wir uns aus der Nutzerprofil-Tabelle Informationen
				// über den Nutzer und speichern sie in rs2.

				ResultSet rs2 = stmt2.executeQuery(
						"SELECT Nutzerprofil_ProfilID, NutzerprofilID, Geburtsdatum, Vorname, Nachname FROM nutzerprofil WHERE Nutzerprofil_ProfilID = "
								+ id);

				if (rs2.next()) {
					// Hier holen wir die allgemeinen Profilinformationen aus rs
					// und fügen diese in das Nutzerprofil ein.
					nutzerprofil.setProfilID(rs.getInt("ProfilID"));
					nutzerprofil.setReligion(rs.getString("Religion"));
					nutzerprofil.setKoerpergroesse(rs.getInt("Koerpergroesse"));
					nutzerprofil.setHaarfarbe(rs.getString("Haarfarbe"));
					nutzerprofil.setRaucher(rs.getString("Raucher"));
					nutzerprofil.setGeschlecht(rs.getString("Geschlecht"));

					// Hier holen wir die restlichen Profilinformationen aus rs2
					// und fügen diese ebenfalls in das Nutzerprofil ein.
					nutzerprofil.setProfilID(rs2.getInt("Nutzerprofil_ProfilID"));
					// TODO Geburtsdatum muss noch gesettet werden!!!!
					nutzerprofil.setVorname(rs2.getString("Vorname"));
					nutzerprofil.setNachname(rs2.getString("Nachname"));

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return nutzerprofil;
	}

	// ----------------------------------------------------------------------------

	// Methode um mit Hilfe einer vorher eingetragenen id Nutzerdaten angezeigt
	// zu bekommen.
	public Nutzerprofil readEigenesProfil(int id) {

		// Wir erstellen hier ein Nutzerprofil, welches mit den Informationen
		// welche wir aus der Datenbank bekommen, gefüllt wird.
		Nutzerprofil nutzerprofil = new Nutzerprofil();
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken

			// Hier holen wir uns aus der Profil-Tabelle die allgemeinen
			// Informationen über den Nutzer,
			// der durch die eingegebene id identifiziert wurde und speichern
			// diese in rs.

			ResultSet rs = stmt.executeQuery(
					"SELECT ProfilID, Religion, Koerpergroesse, Haarfarbe, Raucher, Geschlecht FROM profil WHERE ProfilID = "
							+ id);

			if (rs.next()) {

				// Hier holen wir uns aus der Nutzerprofil-Tabelle Informationen
				// über den Nutzer und speichern sie in rs2.

				ResultSet rs2 = stmt2.executeQuery(
						"SELECT Nutzerprofil_ProfilID, NutzerprofilID, Geburtsdatum, Vorname, Nachname FROM nutzerprofil WHERE Nutzerprofil_ProfilID = "
								+ id);

				if (rs2.next()) {
					// Hier holen wir die allgemeinen Profilinformationen aus rs
					// und fügen diese in das Nutzerprofil ein.
					nutzerprofil.setProfilID(rs.getInt("ProfilID"));
					nutzerprofil.setReligion(rs.getString("Religion"));
					nutzerprofil.setKoerpergroesse(rs.getInt("Koerpergroesse"));
					nutzerprofil.setHaarfarbe(rs.getString("Haarfarbe"));
					nutzerprofil.setRaucher(rs.getString("Raucher"));
					nutzerprofil.setGeschlecht(rs.getString("Geschlecht"));

					// Hier holen wir die restlichen Profilinformationen aus rs2
					// und fügen diese ebenfalls in das Nutzerprofil ein.
					nutzerprofil.setProfilID(rs2.getInt("Nutzerprofil_ProfilID"));
					// TODO Geburtsdatum muss noch gesettet werden!!!!
					nutzerprofil.setVorname(rs2.getString("Vorname"));
					nutzerprofil.setNachname(rs2.getString("Nachname"));

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return nutzerprofil;
	}
}
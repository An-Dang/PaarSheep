package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

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
 */
public class SuchprofilMapper {

	/**
	 * Die Klasse BeschreibungMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */
	private static SuchprofilMapper suchprofilMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected SuchprofilMapper() {
	}

	/**
	 * Es kann nur eine einzige Instanz von SuchprofilMapper erzeugt werden
	 * 
	 * <b>Fazit:</b> SuchprofilMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return suchprofilMapper <code>SuchprofilMapper</code>-Objekt.
	 */
	public static SuchprofilMapper suchprofilMapper() {
		if (suchprofilMapper == null) {
			suchprofilMapper = new SuchprofilMapper();
		}
		return suchprofilMapper;
	}

	/**
	 * Suchprofil-Objekt in die Datenbank einfügen.
	 * 
	 * @param suchprofil Das eingegebene Suchprofil wird gespeichert
	 * @param con Datenbankverbindung
	 * @param stmt Statement
	 * @param profilid Profil ID des aktuell eingeloggten Users
	 * @return suchprofil gespeichertes Suchprofil wird zurueckgegeben
	 */
	public Suchprofil insertSuchprofil(Suchprofil suchprofil, int profilid) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// Größte profil_id aus der Tabelle t_profil ermitteln.
			ResultSet rs = stmt.executeQuery("SELECT MAX(Profilid) AS maxProfilID " + "FROM Profil");
			// Wenn wir etwas zurueckerhalten...
			if (rs.next()) {
				// Suchprofil-Objekt mit bisher maximalem, nun um 1 inkrementierten Primärschlüssel versehen.
				suchprofil.setProfilID(rs.getInt("maxProfilID") + 1);
				// Tabelle Profil befüllen:
				stmt = con.createStatement();
				stmt.executeUpdate(
						"INSERT INTO Profil (ProfilID, Religion, Koerpergroesse, Haarfarbe, Raucher, Geschlecht) "
								+ "VALUES(" + suchprofil.getProfilID() + ",'" + suchprofil.getReligion() + "','"
								+ suchprofil.getKoerpergroesse() + "','" + suchprofil.getHaarfarbe() + "','"
								+ suchprofil.getRaucher() + "','" + suchprofil.getGeschlecht() + "')");

				// Tablle Suchprofil befüllen:
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO Suchprofil (Suchprofil, NutzerprofilID, Suchprofilname) " + "VALUES("
						+ suchprofil.getProfilID() + "," + profilid + ",'" + suchprofil.getSuchprofilName() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		/*
		 * Suchprofil-Objekt zurückgeben.
		 */
		return suchprofil;
	}

	/**
	 * Diese Methode ermöglicht das Löschen eines Suchprofils
	 * 
	 * @param nutzerprofilid Profil ID des aktuellen Users
	 * @param con Datenbankverbindung
	 * @param suchprofilid ID des Suchprofils
	 * @param result Hierin wird das Ergebnis uebertragen
	 * @param suchprofilName uebergibt den vergebenen Namen des Suchprofils
	 * 
	 */
	public void deleteSuchprofil(int nutzerprofilid, String suchprofilName) {
		Connection con = DBConnection.connection();
		int suchprofilid = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt
					.executeQuery("SELECT Suchprofil FROM Suchprofil " + "WHERE Suchprofil.NutzerprofilID="
							+ nutzerprofilid + " AND Suchprofil.Suchprofilname LIKE '" + suchprofilName + "'");
			if (result.next()) {
				suchprofilid = result.getInt("Suchprofil");
				stmt = con.createStatement();
				stmt.executeUpdate("DELETE FROM Information " + "WHERE Information.ProfilID=" + suchprofilid);
				stmt.executeUpdate("DELETE FROM Suchprofil WHERE Suchprofil.Suchprofil=" + suchprofilid);
				/* Daten aus der Tabelle profil mit der entsprechenden
					suchprofil_id löschen.*/
				stmt.executeUpdate("DELETE FROM Profil WHERE Profil.ProfilID =" + suchprofilid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Suchprofil-Objekt wiederholt in die Datenbank schreiben nach Aenderungen.
	 * @param con Datenbankverbindung
	 * @param stmt Statement
	 * @param suchprofil uebergebenes Suchprofil bzw. Objekte davon
	 */
	public void updateSuchprofil(Suchprofil suchprofil) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE Suchprofil " + "SET Suchprofilname=\"" + suchprofil.getSuchprofilName() + "\" "
					+ "WHERE Suchprofil =" + suchprofil.getProfilID());
			stmt = con.createStatement();
			stmt.executeUpdate(
					"UPDATE Profil " + "SET Religion=\"" + suchprofil.getReligion() + "\"," + "Koerpergroesse=\""
							+ suchprofil.getKoerpergroesse() + "\", " + "Haarfarbe=\"" + suchprofil.getHaarfarbe()
							+ "\", " + "Raucher=\"" + suchprofil.getRaucher() + "\"," + "Geschlecht=\""
							+ suchprofil.getGeschlecht() + "\" " + "WHERE ProfilID=" + suchprofil.getProfilID());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Suchen eines Suchprofils von einem Nutzer
	 * 
	 * @param nutzerprofil Profil des aktuellen Nutzers
	 * @param con Datenbankverbindung
	 * @param result ArrayList, in welcher das Suchprofil gespeichert wird
	 * @return result ArrayList, in welcher das gesuchte Suchprofil zurueckgegeben wird
	 */
	public ArrayList<Suchprofil> findSuchprofilByNutzerID(int nutzerprofil) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		// ArrayList in welchem die Suchprofile gespeichert werden
		ArrayList<Suchprofil> result = new ArrayList<Suchprofil>();
		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM Suchprofil INNER JOIN " + "Profil ON Suchprofil.Suchprofil = Profil.ProfilID "
							+ "WHERE Suchprofil.NutzerprofilID=" + nutzerprofil);
			while (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Suchprofil suchprofil = new Suchprofil();
				/*
				 * Für jeden Eintrag im Suchergebnis wird nun ein
				 * Suchprofil-Objekt erstellt.
				 */
				suchprofil.setProfilID(rs.getInt("Suchprofil"));
				suchprofil.setSuchprofilName(rs.getString("SuchprofilName"));
				suchprofil.setReligion(rs.getString("Religion"));
				suchprofil.setKoerpergroesse(rs.getInt("Koerpergroesse"));
				suchprofil.setHaarfarbe(rs.getString("Haarfarbe"));
				suchprofil.setRaucher(rs.getString("Raucher"));
				suchprofil.setGeschlecht(rs.getString("Geschlecht"));
				result.add(suchprofil);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ArrayList zurückgeben
		return result;
	}

	/**
	 * Suchprofil anhand der SuchprofilID ausgeben.
	 * 
	 * @param suchprofilid uebergebene ID des Suchprofils
	 * @param stmt Statement
	 * @param suchprofil Hierin werden Eigenschaften des Suchprofils geschrieben
	 * @return null
	 */
	public Suchprofil findSuchprofilBySuchprofilID(int suchprofilid) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Suchprofil, Profil " + "WHERE ProfilID= " + suchprofilid
					+ " AND Suchprofil = " + suchprofilid);

			while (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Suchprofil suchprofil = new Suchprofil();
				/**
				 * Für jeden Eintrag im Suchergebnis wird nun ein
				 * Suchprofil-Objekt erstellt.
				 */
				suchprofil.setProfilID(rs.getInt("Suchprofil"));
				suchprofil.setSuchprofilName(rs.getString("SuchprofilName"));
				suchprofil.setReligion(rs.getString("Religion"));
				suchprofil.setKoerpergroesse(rs.getInt("Koerpergroesse"));
				suchprofil.setHaarfarbe(rs.getString("Haarfarbe"));
				suchprofil.setRaucher(rs.getString("Raucher"));
				suchprofil.setGeschlecht(rs.getString("Geschlecht"));
				return suchprofil;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}  
	
	/**
	 * Ein Suchprofil soll anhand seines Namens gesucht werden.
	 * @param nutzerprofil Profil des aktuellen Users
	 * @param suchprofilname Name des gesuchten Suchprofils
	 * @param con Datenbankverbindun
	 * @param stmt Statement
	 * @return null
	 */
	public Suchprofil findSuchprofiByName(int nutzerprofil, String suchprofilname) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Suchprofil INNER JOIN Profil "
					+ "ON Suchprofil.Suchprofil = Profil.ProfilID " + "WHERE Suchprofil.NutzerprofilID =" + nutzerprofil
					+ " AND Suchprofil.Suchprofilname LIKE '" + suchprofilname + "'");
			if (result.next()) {
				Suchprofil suchprofil = new Suchprofil();
				suchprofil.setProfilID(result.getInt("Suchprofil"));
				suchprofil.setSuchprofilName(result.getString("Suchprofilname"));
				suchprofil.setReligion(result.getString("Religion"));
				suchprofil.setKoerpergroesse(result.getInt("Koerpergroesse"));
				suchprofil.setHaarfarbe(result.getString("Haarfarbe"));
				suchprofil.setRaucher(result.getString("Raucher"));
				suchprofil.setGeschlecht(result.getString("Geschlecht"));
				return suchprofil;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
}

package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.*;

import com.google.gwt.user.client.Window;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Mapper-Klasse, die <code>SuchprofilMapper</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte
 * können in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
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
	 * @see SuchprofilMapper()
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
	 * @return DAS <code>SuchprofilMapper</code>-Objekt.
	 * @see suchprofilMapper
	 */
	public static SuchprofilMapper suchprofilMapper() {
		if (suchprofilMapper == null) {
			suchprofilMapper = new SuchprofilMapper();
		}

		return suchprofilMapper;
	}

	/**
	 * Diese Methode ermöglicht es ein Profil in der Datenbank anzulegen.
	 * 
	 */
	public Suchprofil insert(Suchprofil suchprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(ProfilID) AS maxid " + "FROM Profil ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * auswahl erhält den bisher maximalen, nun um 1
				 * inkrementierten Primärschlüssel.
				 */
				suchprofil.setID(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation

				stmt.executeUpdate("INSERT INTO Profil " + "(ProfilID, geschlecht, haarfarbe, raucher, religion) "
						+ "VALUES (" + suchprofil.getID() + ",'" + suchprofil.getGeschlecht() + "','"
						+ suchprofil.getHaarfarbe() + "','" + suchprofil.getRaucher() + "','" + suchprofil.getReligion()
						+ "')");

				// Einige Attribute werden von der Klasse Profil geerbt und
				// m�ssen daher
				// dort rein geschrieben werden.
				ResultSet rs2 = stmt.executeQuery("SELECT MAX(SuchprofilID)" + "AS maxid " + "FROM Suchprofil ");

				if (rs2.next()) {

					suchprofil.setProfilID(rs2.getInt("maxid") + 1);

					stmt = con.createStatement();

					stmt.executeUpdate(
							"INSERT INTO Suchprofil (SuchprofilID, Suchprofil_ProfilID, suchprofilname, alter_von, alter_bis, koerpergroesse_von, koerpergroesse_bis, Suchprofil_NutzerprofilID)"
									+ "VALUES (" + suchprofil.getProfilID() + "," + suchprofil.getID() + ",'"
									+ suchprofil.getSuchprofilname() + "'," + suchprofil.getAltervon() + ","
									+ suchprofil.getAlterbis() + ", " + suchprofil.getKoerpergroessevon() + ","
									+ suchprofil.getKoerpergroessebis() + ","
									+ suchprofil.getSuchprofil_nutzerprofilID() + ")");

				}
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return suchprofil;
	}

	/**
	 * Diese Methode ermöglicht das Löschen eines Suchprofils
	 * 
	 * @param suchprofil
	 * @throws Exception
	 */
	public void delete(Suchprofil suchprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Suchprofils " + "WHERE SuchprofilID=" + suchprofil.getID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Löschen des Suchprofil (<code>Suchprofil</code>-Objekt) eines
	 * Nutzerprofils. Diese Methode sollte aufgerufen werden, bevor ein
	 * <code>Nutzerprofil</code> -Objekt gelöscht wird.
	 * 
	 * @param Nutzerprofil
	 *            das <code>Nutzerprofil</code>-Objekt, zu dem der Merkzettel
	 *            gehört
	 */
	public void deleteSuchprofilOf(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Funktioniert das so ???
			stmt.executeUpdate("DELETE FROM Suchprofil " + "WHERE Suchprofil_ProfilID=" + nutzerprofil.getID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param suchprofil
	 * @return das als Parameter übergebene Objekt
	 */
	public Suchprofil update(Suchprofil suchprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE Suchprofil " + "SET Alter_von=\"" + suchprofil.getAltervon() + "\" "
					+ "SET Alter_bis=\"" + suchprofil.getAlterbis() + "\" " + "SET Koerpergroesse_von=\""
					+ suchprofil.getKoerpergroessevon() + "\" " + "SET Koerpergroesse_bis=\""
					+ suchprofil.getKoerpergroessebis() + "\" " + "SET Geschlecht=\"" + suchprofil.getGeschlecht()
					+ "\" " + "SET Haarfarbe=\"" + suchprofil.getHaarfarbe() + "\" " + "SET Religion=\""
					+ suchprofil.getReligion() + "\" " + "SET Raucher=\"" + suchprofil.getRaucher() + "\" "
					+ "WHERE Suchprofil.Suchprofil_ProfilID=" + suchprofil.getProfilID());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Um Analogie zu insert(Beschreibung beschreibung) zu wahren, geben wir
		// auswahl zurück
		return suchprofil;
	}

	/**
	 * Suchen eines Suchporfils von einem Nutzer
	 * 
	 * @see findBySuchprofilID(int ProfilID)
	 * @param suchprofil
	 *            Schlüssel des zugehörigen Suchender.
	 * @return
	 */
	public Suchprofil findBySuchprofilID(int ProfilID) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(

					"SELECT Suchprofil.SuchprofilID, Profil.ProfilID"
							+ "FROM Suchprofil INNER JOIN Profil ON Suchprofil.SuchprofilID = Profil.ProfilID");

			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Suchprofil suchprofil = new Suchprofil();

				/**
				 * Für jeden Eintrag im Suchergebnis wird nun ein
				 * Suchprofil-Objekt erstellt.
				 */
				suchprofil.setProfilID(rs.getInt("SuchprofilID"));
				suchprofil.setGeschlecht(rs.getString("Geschlecht"));
				suchprofil.setHaarfarbe(rs.getString("Haarfarbe"));
				suchprofil.setAlterbis(rs.getInt("Alter_von"));
				suchprofil.setAltervon(rs.getInt("Alter_bis"));
				suchprofil.setKoerpergroessevon(rs.getInt("Koerpergroesse_von"));
				suchprofil.setKoerpergroessebis(rs.getInt("Koerpergroesse_bis"));
				suchprofil.setRaucher(rs.getString("Raucher"));
				suchprofil.setReligion(rs.getString("Religion"));

				return suchprofil;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public ArrayList<Suchprofil> readSuchprofile(Nutzerprofil profil) {
		final Nutzerprofil nutzerprofil = profil;

		Connection con = DBConnection.connection();

		ArrayList<Suchprofil> suchprofile = new ArrayList<Suchprofil>();

	
		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM paarsheep.suchprofil WHERE Suchprofil_NutzerprofilID =" + nutzerprofil.getID());
			
			
			
			while (rs.next()) {

				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM paarsheep.profil WHERE ProfilID =" + rs.getInt(7));

				if (rs2.next()) {

					Suchprofil suchprofil = new Suchprofil();

					suchprofil.setID(rs.getInt(1));
					suchprofil.setSuchprofilname(rs.getString(2));
					suchprofil.setAltervon(rs.getInt(3));
					suchprofil.setAlterbis(rs.getInt(4));
					suchprofil.setKoerpergroessevon(rs.getInt(5));
					suchprofil.setKoerpergroessebis(rs.getInt(6));
					suchprofil.setProfilID(rs.getInt(7));
					suchprofil.setSuchprofil_nutzerprofilID(rs.getInt(8));

					suchprofil.setHaarfarbe(rs2.getString(4));
					suchprofil.setRaucher(rs2.getString(5));
					suchprofil.setGeschlecht(rs2.getString(6));
					suchprofil.setReligion(rs2.getString(2));
					
					

					suchprofile.add(suchprofil);
				}

			}
			return suchprofile;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}

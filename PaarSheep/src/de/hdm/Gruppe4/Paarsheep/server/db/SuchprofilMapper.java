package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
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
	 * Suchprofil-Objekt in die Datenbank einfügen.
	 */
	public Suchprofil insertSuchprofil(Suchprofil suchprofil, int profilid) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Größte profil_id aus der Tabelle t_profil ermitteln.
			ResultSet rs = stmt.executeQuery("SELECT MAX(profilid) AS maxprofilid " + "FROM profil");

			// Wenn wir etwas zurueckerhalten...
			if (rs.next()) {

				// Suchprofil-Objekt mit bisher maximalem, nun um 1
				// inkrementierten Primärschlüssel versehen.
				suchprofil.setProfilID(rs.getInt("maxprofilid") + 1);
				// Tabelle Profil befüllen:
				stmt = con.createStatement();
				stmt.executeUpdate(
						"INSERT INTO profil (profilid, religion, koerpergroesse, haarfarbe, raucher, geschlecht) "
								+ "VALUES(" + suchprofil.getProfilID() + ",'" + suchprofil.getReligion() + "','"
								+ suchprofil.getKoerpergroesse() + "','" + suchprofil.getHaarfarbe() + "','"
								+ suchprofil.getRaucher() + "','" + suchprofil.getGeschlecht() + "')");
			
				// Tablle Suchprofil befüllen:
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO suchprofil (Suchprofil, NutzerprofilID, suchprofilname) " + "VALUES("
						+ suchprofil.getProfilID() + "," + profilid + ",'"
						+ suchprofil.getSuchprofilName() + "')");
			
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
	 * @param suchprofil
	 * @throws Exception
	 */
	public void delete(Suchprofil suchprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Suchprofil " + "WHERE SuchprofilID=" + suchprofil.getID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Suchprofil-Objekt wiederholt in die Datenbank schreiben.
	 */
	public void updateSuchprofil(Suchprofil suchprofil) {
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE suchprofil " + "SET suchprofilname=\"" + suchprofil.getSuchprofilName() + "\", "
					+ "WHERE suchprofilid=" + suchprofil.getProfilID());

			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE profil " + "SET religion=\"" + suchprofil.getReligion() + "\","
					+ "koerpergroesse=\"" + suchprofil.getKoerpergroesse() + "\", " 
					+ "haarfarbe=\"" + suchprofil.getHaarfarbe()+ "\", " 
					+ "raucher=\"" + suchprofil.getRaucher()+"\","
					+ "geschlecht=\"" + suchprofil.getGeschlecht() + "\" "
					+ "WHERE profilID=" + suchprofil.getProfilID());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

	}


	/**
	 * Suchen eines Suchporfils von einem Nutzer
	 * 
	 * @see findBySuchprofilID(int ProfilID)
	 * @param suchprofil
	 *            Schlüssel des zugehörigen Suchender.
	 * @return
	 */
	public Suchprofil findBySuchprofilID(int profilid) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(

			"SELECT * FROM suchporfil INNER JOIN "
			+ "profil ON suchprofil.suchprofilid = profil.profilid "
			+ "WHERE suchprofil.nutzerprofilid=" + profilid);
			
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
				suchprofil.setKoerpergroesse(rs.getInt("Koerpergroesse"));
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
	
	
	
	
	//folgende Befehle werden nicht verendet
	

	

	public ArrayList<Suchprofil> readSuchprofile(Nutzerprofil profil) {
		final Nutzerprofil nutzerprofil = profil;

		Connection con = DBConnection.connection();

		ArrayList<Suchprofil> suchprofile = new ArrayList<Suchprofil>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM paarsheep.suchprofil WHERE NutzerprofilID =" + nutzerprofil.getID());

			while (rs.next()) {

				ResultSet rs2 = stmt2.executeQuery(
						"SELECT * FROM paarsheep.nutzerprofil WHERE NutzerprofilID =" + rs.getInt("NutzerprofilID"));

				if (rs2.next()) {

					Suchprofil suchprofil = new Suchprofil();

					/**
					 * suchprofil.setSuchprofilID(rs.getInt(1));
					 * suchprofil.setSuchprofilname(rs.getString(2));
					 * suchprofil.setAltervon(rs.getInt(3));
					 * suchprofil.setAlterbis(rs.getInt(4));
					 * suchprofil.setKoerpergroessevon(rs.getInt(5));
					 * suchprofil.setKoerpergroessebis(rs.getInt(6));
					 * suchprofil.setSuchprofilID(rs.getInt(7));
					 * suchprofil.setNutzerprofilID(rs.getInt(8));
					 * 
					 * suchprofil.setHaarfarbe(rs2.getString(4));
					 * suchprofil.setRaucher(rs2.getString(5));
					 * suchprofil.setGeschlecht(rs2.getString(6));
					 * suchprofil.setReligion(rs2.getString(2));
					 **/
					suchprofil.setProfilID(rs.getInt("Suchprofil"));
					suchprofil.setSuchprofilName(rs.getString("Suchprofilname"));

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

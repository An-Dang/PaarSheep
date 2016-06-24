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
	 * @param suchprofil 
	 * @param profilid 
	 * @return suchprofil
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
	 * @param nutzerprofilid 
	 * @param suchprofilName 
	 * 
	 */
	public void deleteSuchprofil(int nutzerprofilid, String suchprofilName) {
		Connection con = DBConnection.connection();
		int suchprofilid = 0;
		try {
			Statement stmt = con.createStatement();

			ResultSet result = stmt.executeQuery("SELECT suchprofil FROM suchprofil " + "WHERE suchprofil.nutzerprofilid="
							+ nutzerprofilid + " AND suchprofil.suchprofilname LIKE '" + suchprofilName + "'");
			
			if (result.next()) {
				suchprofilid = result.getInt("suchprofil");
				
				stmt = con.createStatement();
				stmt.executeUpdate(
						"DELETE FROM suchprofil " + "WHERE suchprofil.suchprofil=" + suchprofilid);

				// Daten aus der Tabelle profil mit der entsprechenden
				// suchprofil_id löschen.
				stmt = con.createStatement();
				stmt.executeUpdate("DELETE FROM profil WHERE profil.profilid=" + suchprofilid);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Suchprofil-Objekt wiederholt in die Datenbank schreiben.
	 * @param suchprofil 
	 */
	public void updateSuchprofil(Suchprofil suchprofil) {
		Connection con = DBConnection.connection();
			try {			
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE suchprofil " + "SET suchprofilname=\"" + suchprofil.getSuchprofilName() + "\" "
					+ "WHERE suchprofil=" + suchprofil.getProfilID());

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
	 * @param nutzerprofil 
	 * 
	 * @return result
	 */
	public ArrayList<Suchprofil> findSuchprofilByNutzerID(int nutzerprofil) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		ArrayList<Suchprofil> result = new ArrayList<Suchprofil>();
		// ArrayList in welchem die Suchprofile gespeichert werden

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(

			"SELECT * FROM suchprofil INNER JOIN "
			+ "profil ON suchprofil.suchprofil = profil.profilid "
			+ "WHERE suchprofil.nutzerprofilid=" + nutzerprofil);
			
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
	 * @param suchprofilid 
	 * @return null
	 */
	public Suchprofil findSuchprofilBySuchprofilID(int suchprofilid) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM suchprofil, profil "
							+ "WHERE profilID= " + suchprofilid
							+ " AND suchprofil= " + suchprofilid);

			while (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Suchprofil suchprofil = new Suchprofil();

				/**
				 * Für jeden Eintrag im Suchergebnis wird nun ein
				 * Suchprofil-Objekt erstellt.
				 */
				suchprofil.setProfilID(rs.getInt("SuchprofilID"));
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
	 * @param nutzerprofil
	 * @param suchprofilname
	 * @return null
	 */
	public Suchprofil findSuchprofiByName(int nutzerprofil, String suchprofilname) {
		Connection con = DBConnection.connection();
		
		try{

		Statement stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * FROM suchprofil INNER JOIN profil "
				+ "ON suchprofil.suchprofil = profil.profilid " + "WHERE suchprofil.nutzerprofilid="
				+ nutzerprofil + " AND suchprofil.suchprofilname LIKE '" + suchprofilname + "'");
		
		
		if(result.next()){
			
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

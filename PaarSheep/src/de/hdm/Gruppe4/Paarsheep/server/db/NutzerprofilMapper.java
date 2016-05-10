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
	 * Einfügen eines <code>Nutzerpofil</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param nutzerprofil
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 * @throws Exception
	 */
	
//-----------------------------------------------------------------------------	
	//Diese Methode bezieht ihre Informationen aus der 
	//PartnerboerseAdministrationImpl und erstellt mit diesen einen neuen 
	//Nutzer in der Datenbank
	
	public Nutzerprofil insert(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Der höchste Wert des Primärschlüssel der Tabelle Profil wird
			// ermittelt
			ResultSet rs = stmt.executeQuery("SELECT MAX(ProfilID) AS maxid " 
			+ "FROM Profil ");

			if (rs.next()) {
				
			//Das Attribut von Nutzerprofil Nutzprofil_ProfilID wird anhand des 
			//maximalen Wertes von ProfilID vergeben und + 1 gesetzt.
			//ACHTUNG!!!: Dieses Attribut setzt bei Profil den Primärschlüssel 
			//ALS AUCH bei Nutzerprofil den Fremdschlüssel.
				
				nutzerprofil.setNutzerprofil_ProfilID(rs.getInt("maxid") + 1);

			//Dieses Statement übergibt die Werte an die Tabelle Profil
				stmt.executeUpdate(
						"INSERT INTO profil (ProfilID, Geschlecht, Haarfarbe, "
						+ "Koerpergroesse, Raucher, Religion) "
								+ "VALUES(" 
								+ nutzerprofil.getNutzerprofil_ProfilID() 
								+ ",'" + nutzerprofil.getGeschlecht() + "','" 
								+ nutzerprofil.getHaarfarbe() + "','"
								+ nutzerprofil.getKoerpergroesse() + "','" 
								+ nutzerprofil.getRaucher() + "','"
								+ nutzerprofil.getReligion() + "')");

			// Der höchste Wert des Primärschlüssel von Nutzerprofil wird
			// ermittelt
				ResultSet rs2 = stmt.executeQuery("SELECT MAX(NutzerprofilID) "
						+ "AS maxid " + "FROM nutzerprofil ");

				
				if (rs2.next()) {

			//Das Attribut von Nutzerprofil Nutzprofil_ProfilID wird 
			//anhand des maximalen Wertes von ProfilID vergeben und 
			//+ 1 gesetzt.
					nutzerprofil.setID(rs2.getInt("maxid") + 1);

					stmt = con.createStatement();

			//Dieses Statement übergibt die Werte an die Tabelle Nutzerprofil
					stmt.executeUpdate("INSERT INTO nutzerprofil "
							+ "(NutzerprofilID, Vorname, Nachname, "
							+ "Nutzerprofil_ProfilID) " + "VALUES ("
							+ nutzerprofil.getID() + ",'" 
							+ nutzerprofil.getVorname() + "','"
							+ nutzerprofil.getNachname() + "'," 
							+ nutzerprofil.getNutzerprofil_ProfilID()+ ")" );

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
	
//-----------------------------------------------------------------------------	
	/**
	   * Auslesen aller Nutzerprofile.
	   * 
	   * @return Ein ArrayList mit Nutzerprofil-Objekten, die sämtliche Nutzerprofil
	   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	   *         oder ggf. auch leerer ArrayList zurückgeliefert.
	   */
	  public ArrayList<Nutzerprofil> findAllNutzerprofil() {
	    Connection con = DBConnection.connection();

	    // ArrayList vorbereiten
	    ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT Nutzerprofil_ProfilID FROM Nutzerprofil "
	          + " ORDER BY Nutzerprofil_ProfilID");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Nutzerprofil-Objekt erstellt.
	      while (rs.next()) {
	    	  Nutzerprofil nutzerprofil = new Nutzerprofil();
	    	  nutzerprofil.setNutzerprofil_ProfilID(rs.getInt("Nutzerprofil_ProfilID"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.add(nutzerprofil);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }
//-----------------------------------------------------------------------------	


//	/**
//	 * Auslesen aller Nutzerprofile eines durch Fremdschlüssel
//	 * (Nutzerprofil_ProfilID.) gegebenen Profils.
//	 * 
//	 * @see findByFremdschluesselNutzerprofil_ProfilID(Profil
//	 *      Nutzerprofil_ProfilID)
//	 * @param nutzerprofil_ProfilID
//	 *            Schlüssel des zugehörigen profils.
//	 * @return nutzerprofil
//	 */
//	public Nutzerprofil findByFremdschluesselNutzerprofil_ProfilID(Profil Nutzerprofil_ProfilID) {
//		Connection con = DBConnection.connection();
//
//		try {
//			Statement stmt = con.createStatement();
//
//			ResultSet rs = stmt.executeQuery("SELECT nutzerprofilid, nutzerprofil_profilid FROM Nutzerprofil "
//					+ "WHERE nutzerprofil_profilid=" + Nutzerprofil_ProfilID);
//
//			// Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt
//			// erstellt.
//			while (rs.next()) {
//				Nutzerprofil nutzerprofil = new Nutzerprofil();
//				nutzerprofil.setID(rs.getInt("NutzerprofilID"));
//				nutzerprofil.setNutzerprofil_ProfilID(rs.getInt("Nutzerprofil_ProfilID"));
//				return nutzerprofil;
//
//			}
//		} catch (SQLException e2) {
//			e2.printStackTrace();
//			return null;
//		}
//
//		return null;
//	}

	/**
	 * Auslesen des Nutzerporfils eines durch Fremdschlüssel
	 * (Nutzerprofil_ProfilID.) gegebenen Profils.
	 * 
	 * @see findByProfil(Profil Nutzerprofil_ProfilID)
	 * @param Nutzerprofil_ProfilID
	 * Schlüssel des zugehörigen Kunden.
	 */
	public Nutzerprofil findByProfil(int Nutzerprofil_ProfilID) {
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
				nutzerprofil.setNutzerprofil_ProfilID(rs.getInt("Nutzerprofil_profilID"));

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}
}

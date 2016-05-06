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
	 * Einfügen eines <code>Nutzerpofil</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param nutzerprofil das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 * @throws Exception        
	 */
	  public Nutzerprofil insert(Nutzerprofil nutzerprofil) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM nutzerprofil ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * c erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        nutzerprofil.setID(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO nutzerprofil (NutzerprofilID, Vorname, Nachname, Geburtsdatum) "
		            + "VALUES (" + nutzerprofil.getID() + ",'" + nutzerprofil.getNachname() + "','"
		            + nutzerprofil.getVorname() + "', '" + nutzerprofil.getGeburtsdatum() + ")");
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
	 
		    /*
		     * Rückgabe, des evtl. korrigierten nutzerprofil.
		     * 
		     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		     * Objekte übergeben werden, wäre die Anpassung des Nutzerprofil-Objekts auch
		     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
		     * explizite Rückgabe von nutzerprofil ist eher ein Stilmittel, um zu signalisieren,
		     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
		     */
		    return nutzerprofil;
		  }

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param nutzerprofil das Objekt, das in die DB geschrieben werden soll
	 * 
	 * @return das als Parameter übergebene Objekt
	 */
	public Nutzerprofil update(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE Nutzerprofil INNER JOIN Profil" + "ON Nutzerprofil.Nutzerprofil_ProfilID = ProfilID"
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
	 * Datenbank.
	 * Wenn Nutzerprofil gelöscht wird, wird alles gelöscht!
	 * 
	 * @param nutzerprofil das aus der DB zu löschende "Objekt"
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

	  
	  /**
	   * Auslesen aller Nutzerprofile eines durch Fremdschlüssel (Nutzerprofil_ProfilID.) gegebenen
	   * Profils.
	   * 
	   * @see findByFremdschluesselNutzerprofil_ProfilID(Profil Nutzerprofil_ProfilID)
	   * @param nutzerprofil_ProfilID Schlüssel des zugehörigen profils.
	   * @return nutzerprofil
	   */
	  public Nutzerprofil findByFremdschluesselNutzerprofil_ProfilID(Profil Nutzerprofil_ProfilID) {
	  Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT nutzerprofilid, nutzerprofil_profilid FROM Nutzerprofil "
	          + "WHERE nutzerprofil_profilid=" + Nutzerprofil_ProfilID);

	      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
	      while (rs.next()) {
	        Nutzerprofil nutzerprofil = new Nutzerprofil();
	        nutzerprofil.setID(rs.getInt("id"));
	        nutzerprofil.setNutzerprofil_ProfilID(rs.getInt("nutzerprofil_ProfilID"));
	        return nutzerprofil;
	     
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }

	    return null;
	  }

	  /**
	   * Auslesen des Nutzerporfils eines durch Fremdschlüssel (Nutzerprofil_ProfilID.) gegebenen
	   * Profils.
	   * 
	   * @see findByOwner(Customer owner)
	   * @param ownerID Schlüssel des zugehörigen Kunden.
	  */
	public Nutzerprofil findByProfil(Profil Nutzerprofil_ProfilID) {
		 Connection con = DBConnection.connection();
		 
		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT nutzerprofilid, nutzerprofil_profilid FROM Nutzerprofil "
		          + "WHERE nutzerprofil_profilid=" + Nutzerprofil_ProfilID);

		      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
		      while (rs.next()) {
		        Nutzerprofil nutzerprofil = new Nutzerprofil();
		        nutzerprofil.setID(rs.getInt("nutzerprofilid"));
		        nutzerprofil.setNutzerprofil_ProfilID(rs.getInt("nutzerprofil_profilID"));

		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }
}

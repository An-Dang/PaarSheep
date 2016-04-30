package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;


import de.hdm.Gruppe4.Paarsheep.shared.bo.Information;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;


/**
 * @author Dang
 * @author Hauler
 * @author Thies
 */
public class NutzerprofilMapper {
  
	/** 
	 * Diese Methode ermöglicht es ein Profil in der Datenbank anzulegen.
	 * 
	 * @param profil
	 * @return
	 * @throws Exception
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
	 * @param a
	 *            das zu speichernde Objekt
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
		        stmt.executeUpdate("INSERT INTO nutzerprofil (nutzerprofilID, vorname, nachname, geburtsdatum) "
		            + "VALUES (" + nutzerprofil.getID() + ",'" + nutzerprofil.getNachname() + "','"
		            + nutzerprofil.getVorname() + "','" nutzerprofil.getGeburtsdatum() + "')");
		      }

			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(null, preStmt, con);
		}

		return nutzerprofil;
	}
		
		  /**
		   * Wiederholtes Schreiben eines Objekts in die Datenbank.
		   * 
		   * @param nutzerprofil das Objekt, das in die DB geschrieben werden soll
		   * @return das als Parameter übergebene Objekt
		   */
		  public Nutzerprofil update(Nutzerprofil nutzerprofil) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE nutzerprofil " + "SET vorname=\""
		          + nutzerprofil.getVorname() + "\", " + "nachname=\"" + nutzerprofil.getNachname() + "\" "
		          + "WHERE id=" + nutzerprofil.getID());

		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    // Um Analogie zu insert(Customer c) zu wahren, geben wir c zurück
		    return nutzerprofil;
		  }
		  

	  /**
	   * Löschen der Daten eines <code>Nutzerprofil</code>-Objekts aus der Datenbank.
	   * 
	   * @param nutzerprofil das aus der DB zu löschende "Objekt"
	   */
	  public void delete(Nutzerprofil nutzerprofil) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM nutzerprofil " + "WHERE id=" + nutzerprofil.getID());
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
}
	  

package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.*;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;



public class AuswahlMapper {
	
		/**
 	   * Die Klasse InformationMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see AuswahlMapper()
	   * @author Dang
	   * @author Hauler
	   * @author Thies
	   */
	private static AuswahlMapper auswahlMapper = null;

/**
 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
 * neue Instanzen dieser Klasse zu erzeugen.
 */
	protected AuswahlMapper() {
	}
	
/**
 * Es kann nur eine einzige Instanz von ProfilMapper erzeugt werden
 * 
 * <b>Fazit:</b> AccountMapper sollte nicht mittels <code>new</code>
 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
 * 
 * @return DAS <code>AuswahlMapper</code>-Objekt.
 * @see accountMapper
 */
	public static AuswahlMapper auswahlMapper() {
		if (auswahlMapper == null) {
			auswahlMapper = new AuswahlMapper();
		}

		return auswahlMapper;
	}
	/** 
	 * Diese Methode ermöglicht es eine Auswahl in der Datenbank anzulegen.
	 * 
	 * @param auswahl
	 * @return
	 * @throws Exception
	 */
	public AuswahlMapper insertAuswahl(AuswahlMapper auswahl) 
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt = null;

		 try {
		      Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM customers ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * c erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        auswahl.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO customers (id, firstName, lastName) "
		            + "VALUES (" + auswahl.getID() + ",'" + auswahl.getFirstName() + "','"
		            + auswahl.getLastName() + "')");
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		}
	
	 /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param a das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	  public Auswahl update(Auswahl auswahl) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE accounts " + "SET owner=\"" + auswahl.getID()
	          + "\" " + "WHERE id=" + auswahl.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Um Analogie zu insert(Auswahl auswahl) zu wahren, geben wir auswahl zurück
	    return auswahl;
	  }

	  /**
	   * Löschen der Daten eines <code>Auswahl</code>-Objekts aus der Datenbank.
	   * 
	   * @param a das aus der DB zu löschende "Objekt"
	   */
	  public void delete(Auswahl auswahl) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM accounts " + "WHERE id=" + auswahl.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	  }
	  
	  /**
	   * Auslesen aller Konten eines durch Fremdschlüssel (ProfilID.) gegebenen
	   * Profils.
	   * 
	   * @see findByProfil(Profil Profil)
	   * @param profil Schlüssel des zugehörigen Profils.
	   * @return Ein Vektor mit Information-Objekten, die sämtliche Informationen des
	   *         betreffenden Profils repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public ArrayList<Auswahl> findByEigenschaft(Eigenschaft eigenschaft) {
	    Connection con = DBConnection.connection();
	    ArrayList<Auswahl> result = new ArrayList<Auswahl>();

	    try {
	      Statement stmt = con.createStatement();
// SQL Befehl noch umändern!
	      ResultSet rs = stmt.executeQuery("SELECT id, profil FROM information "
	          + "WHERE profil=" + profil + " ORDER BY id");

	      //Für jeden Eintrag im Suchergebnis wird nun ein Auswhal-Objekt erstellt.
	      //Muss nich angepasst werden

	      while (rs.next()) {
	        Auswahl auswahl = new Auswahl();
	        auswahl.setID(rs.getInt("id"));
	        auswahl.setEigenschaftsID(rs.getInt("Eigenschaft"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(auswahl);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }
	
}

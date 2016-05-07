package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Merkzettel;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Sperrliste;

/**
 * Mapper-Klasse, die <code>SperrlisteMapper</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * 
 * @author Thies
 * @author Hauler
 * @author Dang
 * */

public class SperrlisteMapper {
	
	/**
	   * Die Klasse SperrlisteMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see SperrlisteMapper()
	   */
	  private static SperrlisteMapper sperrlisteMapper = null;

	  /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	  protected SperrlisteMapper() {
	  }

	  /**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>SperrlisteMapper.sperrlisteMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	   * Instanz von <code>sperrlisteMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> SperrlisteMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>SperrlisteMapper</code>-Objekt.
	   * @see sperrlisteMapper
	   */
	  public static SperrlisteMapper sperrlisteMapper() {
	    if (sperrlisteMapper == null) {
	    	sperrlisteMapper = new SperrlisteMapper();
	    }

	    return sperrlisteMapper;
	  }
	  
	  
	  /**
	   * Einfügen eines <code>SperrlisteMapper</code>-Objekts in die Datenbank. Dabei wird
	   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	   * berichtigt.
	   * 
	   * @param information das zu speichernde Objekt
	   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	  public Sperrliste insert(Sperrliste sperrliste) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      /*
	       * Zunächst schauen wir nach, welches der momentan höchste
	       * Primärschlüsselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM sperrliste ");

	      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
	      if (rs.next()) {
	        /*
	         * merkzettel erhält den bisher maximalen, nun um 1 inkrementierten
	         * Primärschlüssel.
	         */
	    	  sperrliste.setID(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
	        stmt.executeUpdate("INSERT INTO sperrliste (SperrlisteID, Sperrender_NutzerprofilID) " + "VALUES ("
	            + sperrliste.getID() + "," + sperrliste.getSperrender_NutzerprofilID() + ")");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    /*
	     * Rückgabe, der evtl. korrigierten Sperrliste.
	     * 
	     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
	     * Objekte übergeben werden, wäre die Anpassung des Account-Objekts auch
	     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
	     * explizite Rückgabe von a ist eher ein Stilmittel, um zu signalisieren,
	     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
	     */
	    return sperrliste;
	  }
	  
	  
	  
	 
	  /**
	   * Löschen der Daten eines <code>Sperrliste</code>-Objekts aus der Datenbank.
	   * 
	   * @param sperrliste das aus der DB zu löschende "Objekt"
	   */
	  public void delete(Sperrliste sperrliste) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM sperrliste " + "WHERE SperrlisteID=" + sperrliste.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	  }

	  /**
	   * Löschen der Sperrliste (<code>Sperrliste</code>-Objekte) eines Nutzerprofils.
	   * Diese Methode sollte aufgerufen werden, bevor ein <code>Nutzerprofil</code>
	   * -Objekt gelöscht wird.
	   * 
	   * @param nutzerprofil das <code>Nutzerprofil</code>-Objekt, zu dem die Sperrliste gehören
	   */
	public void deleteSperrlisteOf(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Sperrliste " + "WHERE Sperrender_NutzerprofilID=" + nutzerprofil.getID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	  
	  /**
	   * Auslesen aller Sperrlisten eines durch Fremdschlüssel (Sperrender_NutzerprofilID) gegebenen
	   * Nutzerprofils.
	   * 
	   * @see findBySperrender(Nutzerprofil sperrender)
	   * @param Sperrender_NutzerprofilID Schlüssel des zugehörigen Nutzerprofils.
	   * @return Eine Arraylist mit Sperrliste-Objekten, die sämtliche Sperrlisten des
	   *         betreffenden Nutzerprofils repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public ArrayList<Sperrliste> findBySperrender(int sperrender_NutzerprofilID) {
	    Connection con = DBConnection.connection();
	    ArrayList<Sperrliste> result = new ArrayList<Sperrliste>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT sperrlisteID, sperrender_NutzeprofilID FROM sperrliste "
	          + "WHERE sperrender_NutzeprofilID=" + sperrender_NutzerprofilID + " ORDER BY merkzettelID");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Informations-Objekt erstellt.
	      while (rs.next()) {
	    	Sperrliste sperrliste = new  Sperrliste();
	    	sperrliste.setSperrlisteID(rs.getInt("merkzettelID"));
	    	sperrliste.setSperrender_NutzerprofilID(rs.getInt("sperrender_NutzerprofilID"));

	        // Hinzufügen des neuen Objekts zum Array
	        result.add(sperrliste);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }
	  
	  
	  /**
	   * Auslesen aller Sperrlisten eines Nutzerprofils (durch <code>Nutzerprofil</code>-Objekt
	   * gegeben).
	   * 
	   * @see findBySperrender(int sperrender_NutzerprofilID)
	   * @param sperrender Nutzerprofilobjekt, dessen Sperrlisten wir auslesen möchten.
	   * @return alle Sperrlisten des Nutzerprofils
	   */
	  public ArrayList<Sperrliste> findBySperrender(Nutzerprofil sperrender) {

	    /*
	     * Wir lesen einfach die Kundennummer (Primärschlüssel) des Customer-Objekts
	     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
	     */
	    return findBySperrender(sperrender.getSperrender_NutzerprofilID());
	  }
}


package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Merkzettel;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

/**
 * Mapper-Klasse, die <code>Merkzettel</code>-Objekte auf eine relationale
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

public class MerkzettelMapper {
	
	/**
	   * Die Klasse MerkzettelMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see MerkzettelMapper()
	   */
	  private static MerkzettelMapper merkzettelMapper = null;

	  /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	  protected MerkzettelMapper() {
	  }

	  /**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>MerkzettelMapper.merkzettelMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	   * Instanz von <code>MerkzettelMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> MerkzettelMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>MerkzettelMapper</code>-Objekt.
	   * @see merkzettelMapper
	   */
	  public static MerkzettelMapper merkzettelMapper() {
	    if (merkzettelMapper == null) {
	    	merkzettelMapper = new MerkzettelMapper();
	    }

	    return merkzettelMapper;
	  }
	  
	  
	  /**
	   * Einfügen eines <code>Merkzettel</code>-Objekts in die Datenbank. Dabei wird
	   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	   * berichtigt.
	   * 
	   * @param information das zu speichernde Objekt
	   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	  public Merkzettel insert(Merkzettel merkzettel) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      /*
	       * Zunächst schauen wir nach, welches der momentan höchste
	       * Primärschlüsselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM merkzettel ");

	      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
	      if (rs.next()) {
	        /*
	         * merkzettel erhält den bisher maximalen, nun um 1 inkrementierten
	         * Primärschlüssel.
	         */
	    	  merkzettel.setID(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
	        stmt.executeUpdate("INSERT INTO merkzettel (MerkzettelID, Merkender_NutzerprofilID) " + "VALUES ("
	            + merkzettel.getID() + "," + merkzettel.getMerkender_NutzerprofilID() + ")");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    /*
	     * Rückgabe, des evtl. korrigierten Accounts.
	     * 
	     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
	     * Objekte übergeben werden, wäre die Anpassung des Account-Objekts auch
	     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
	     * explizite Rückgabe von a ist eher ein Stilmittel, um zu signalisieren,
	     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
	     */
	    return merkzettel;
	  }
	  
	  
	  
	 
	  /**
	   * Löschen der Daten eines <code>Merkzettel</code>-Objekts aus der Datenbank.
	   * 
	   * @param merkzettel das aus der DB zu löschende "Objekt"
	   */
	  public void delete(Merkzettel merkzettel) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM merkzettel " + "WHERE MerkzettelID=" + merkzettel.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	  }

	  /**
	   * Löschen sämtlicher Merkzettel (<code>Merkzettel</code>-Objekte) eines Nutzerprofils.
	   * Diese Methode sollte aufgerufen werden, bevor ein <code>Nutzerprofil</code>
	   * -Objekt gelöscht wird.
	   * 
	   * @param nutzerprofil das <code>Nutzerprofil</code>-Objekt, zu dem die Merkzettel gehören
	   */
	  public void deleteMerkzettelOf(Nutzerprofil nutzerprofil) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM nutzerprofil " + "WHERE merkender_NutzerprofilID=" + nutzerprofil.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	  }

	  
	  
	  /**
	   * Auslesen aller Merkzettel eines durch Fremdschlüssel (Merkender_NutzerprofilID) gegebenen
	   * Nutzerprofils.
	   * 
	   * @see findByMerkender(Nutzerprofil merkender)
	   * @param Merkender_NutzerprofilID Schlüssel des zugehörigen Nutzerprofils.
	   * @return Eine Arraylist mit Merkzettel-Objekten, die sämtliche Merkzettel des
	   *         betreffenden Nutzerprofils repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public ArrayList<Merkzettel> findByMerkender(int merkender_NutzerprofilID) {
	    Connection con = DBConnection.connection();
	    ArrayList<Merkzettel> result = new ArrayList<Merkzettel>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT merkzettelID, merkender_NutzeprofilID FROM merkzettel "
	          + "WHERE merkender_NutzeprofilID=" + merkender_NutzerprofilID + " ORDER BY merkzettelID");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Informations-Objekt erstellt.
	      while (rs.next()) {
	    	Merkzettel merkzettel = new Merkzettel();
	    	merkzettel.setmerkzettelID(rs.getInt("merkzettelID"));
	    	merkzettel.setMerkender_NutzerprofilID(rs.getInt("merkender_NutzerprofilID"));

	        // Hinzufügen des neuen Objekts zum Array
	        result.add(merkzettel);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }
	  
	  
	  /**
	   * Auslesen aller Merkzettel eines Nutzerprofils (durch <code>Nutzerprofil</code>-Objekt
	   * gegeben).
	   * 
	   * @see findByMerkender(int merkender_NutzerprofilID)
	   * @param merkender Nutzerprofilobjekt, dessen Merkzettel wir auslesen möchten.
	   * @return alle Merkzettel des Nutzerprofils
	   */
	  public ArrayList<Merkzettel> findByMerkender(Nutzerprofil merkender) {

	    /*
	     * Wir lesen einfach die Kundennummer (Primärschlüssel) des Customer-Objekts
	     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
	     */
	    return findByMerkender(merkender.getMerkender_NutzerprofilID());
	  }
}



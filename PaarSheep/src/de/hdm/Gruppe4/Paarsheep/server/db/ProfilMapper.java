package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.Vector;

//import de.hdm.Gruppe4.Paarsheep.shared.bo*;

/**
 * Mapper-Klasse, die <code>Account</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Thies, Dang
 */
public class ProfilMapper {

  /**
   * Die Klasse ProfilMapper wird nur einmal instantiiert.
   */
  private static ProfilMapper profilMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected ProfilMapper() {
  }

  /**
   * Es kann nur eine einzige Instanz von ProfilMapper erzeugt werden
   * 
   * <b>Fazit:</b> AccountMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>AccountMapper</code>-Objekt.
   * @see accountMapper
   */
  public static ProfilMapper accountMapper() {
    if (profilMapper == null) {
      profilMapper = new ProfilMapper();
    }

    return profilMapper;
  }

  /**
   * Test Profilkey ausgeben
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Konto-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public ProfilMapper findByKey(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT ProfilID FROM Profil");
      
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

}

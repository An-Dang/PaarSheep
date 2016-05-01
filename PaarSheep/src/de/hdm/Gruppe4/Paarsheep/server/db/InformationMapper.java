package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Information;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Profil;

/**
 * Mapper-Klasse, die <code>Information</code>-Objekte auf eine relationale
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

public class InformationMapper {
	/**
	   * Die Klasse InformationMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see InformationMapper()
	   */
	  private static InformationMapper informationMapper = null;

	  /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	  protected InformationMapper() {
	  }

	  /**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>AccountMapper.accountMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	   * Instanz von <code>AccountMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> AccountMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>InformationMapper</code>-Objekt.
	   * @see accountMapper
	   */
	  public static InformationMapper informationMapper() {
	    if (informationMapper == null) {
	    	informationMapper = new InformationMapper();
	    }

	    return informationMapper;
	  }
	  
	  
	  /**
		 * Einfügen eines <code>Information</code>-Objekts in die Datenbank. Dabei wird
		 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
		 * berichtigt.
		 * 
		 * @param Information
		 *            das zu speichernde Objekt
		 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
		 *         <code>id</code>.
		 * @throws Exception        
		 */
		  public Information insert(Information information) {
			    Connection con = DBConnection.connection();

			    try {
			      Statement stmt = con.createStatement();

			      /*
			       * Zunächst schauen wir nach, welches der momentan höchste
			       * Primärschlüsselwert ist.
			       */
			      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
			          + "FROM Information ");

			      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			      if (rs.next()) {
			        /*
			         * c erhält den bisher maximalen, nun um 1 inkrementierten
			         * Primärschlüssel.
			         */
			    	  Information.setID(rs.getInt("maxid") + 1);

			        stmt = con.createStatement();

			        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
			        stmt.executeUpdate("INSERT INTO Information (informationID) "
			            + "VALUES (" + information.getID()+ "')");
			      }

				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
			}finally {
				DBConnection.closeAll(null, preStmt, con);
			}

			return information;
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
	  public ArrayList<Information> findByProfil(Profil profil) {
	    Connection con = DBConnection.connection();
	    ArrayList<Information> result = new ArrayList<Information>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, profil FROM information "
	          + "WHERE profil=" + profil + " ORDER BY id");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
	      while (rs.next()) {
	        Information information = new Information();
	        information.setID(rs.getInt("id"));
	        information.setProfilID(rs.getInt("profil"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(information);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }
}

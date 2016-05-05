package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;



public class ProfilMapper {

  /**
   	   * Die Klasse InformationMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see ProfilMapper()
	   * @author Dang
	   * @author Hauler
	   * @author Thies
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
  public static ProfilMapper profilMapper() {
    if (profilMapper == null) {
      profilMapper = new ProfilMapper();
    }

    return profilMapper;
  }
  
	/** 
	 * Diese Methode ermöglicht es ein Profil in der Datenbank anzulegen.
	 * 
	 * @param profil
	 * @return
	 * @throws Exception
	 */
	public Profil insertProfil(Profil profil) 
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt = null;

		try {
			String sql = "INSERT INTO `Profil`(`ProfilID`) VALUES (NULL)";

			preStmt = con.prepareStatement(sql);
			preStmt.executeUpdate();
			preStmt.close();
			
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(null, preStmt, con);
		}

		return profil;
	}
	
	/**
	 * Diese Methode ermöglicht das Löschen eines Abonnements
	 * 
	 * @param profil
	 * @throws Exception
	 */

	  public void delete(Profil profil) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM nutzerprofil " + "WHERE id=" + profil.getID());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
	  }
	  
	  /**
	   * Auslesen der zugehörigen <code>Information</code>-Objekte zu einem gegebenen
	   * Profil.
	   * 
	   * @param Profil, dessen Informationen wir auslesen möchten
	   * @return ein ArrayList mit sämtlichen Information-Objekten des Profils
	   */
	  public ArrayList<Information> getInformationOf(Profil profil) {
		  /*
		     * Wir bedienen uns hier einfach des InformationMapper. Diesem geben wir einfach
		     * den in dem Profil-Objekt enthaltenen Primärschlüssel.Der ProfilMapper
		     * löst uns dann diese ID in eine Reihe von Information-Objekten auf.
		     */
	    return InformationMapper.informationMapper().findByOwner(profil);
	  }

	  
	  /**
	   * Auslesen des zugehörigen <code>Nutzerprofil</code>-Objekts zu einem gegebenen
	   * Profil.
	   * 
	   * @param profil das Profil, dessen Nutzerprofil wir auslesen möchten
	   * @return ein Objekt, das den Eigentümer des Kontos darstellt
	   */
	  public Nutzerprofil getNutzerprofil(Profil profil) {
		  /*
		     * Wir bedienen uns hier einfach den NutzerprofilMapper. Diesem geben wir einfach
		     * den in dem Profil-Objekt enthaltenen Primärschlüssel.Der ProfilMapper
		     * löst uns dann diese ID in ein Nutzerprofil-Objekt auf.
		     */
	    return NutzerprofilMapper.nutzerprofilMapper().findByProfil(profil);
	  }

	  
	  /**
	   * Suchen eines Profil mit vorgegebener ProfilID. Da diese eindeutig ist,
	   * wird genau ein Objekt zur�ckgegeben.
	   * 
	   * @param profilid Primärschlüsselattribut (->DB)
	   * @return Profil-Objekt, das dem übergebenen Schlüssel entspricht, null bei
	   *         nicht vorhandenem DB-Tupel.
	   */
	  
	public Profil findByFremdschluesselNutzerprofil_ProfilID(int id) {
		 // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt
	          .executeQuery("SELECT profilid, religion, koerpergroeße, haarfarbe, geschlecht FROM profil "
	              + "WHERE profilid=" + id);

	      /*
	       * Da profilid Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	       * werden. Prüfe, ob ein Ergebnis vorliegt.
	       */
	      if (rs.next()) {
	        // Ergebnis-Tupel in Objekt umwandeln
	        Profil profil = new Profil();
	        profil.setID(rs.getInt("profilID"));
	        profil.setReligion(rs.getString("religion"));
	        profil.setKoerpergroesse(rs.getInt("koerpergroeße"));
	        profil.setHaarfarbe(rs.getString("haarfarbe"));
	        profil.setGeschlecht(rs.getString("geschlecht"));
	        

	        return profil;
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }

	    return null;
	  }

	}
	  
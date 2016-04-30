package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Information;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Profil;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Profil;


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
  public static ProfilMapper auswahlMapper() {
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
	public ProfilMapper insertAbonnement(ProfilMapper profil) 
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
	public void delete(ProfilMapper profil) 
			throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM Profil " + "WHERE ProfilID=" + Profil.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(null, stmt, con);
		}
	}
	
	  /**
	   * Auslesen der zugehörigen <code>Information</code>-Objekte zu einem gegebenen
	   * Profil.
	   * 
	   * @param Profil, dessen Informationen wir auslesen möchten
	   * @return ein Vektor mit sömtlichen Information-Objekten des Profils
	   */
	  public ArrayList<Information> getInformationOf(Profil profil) {

	    return InformationMapper.informationMapper().findByProfil(profil);
	  }

}

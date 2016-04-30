package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Information;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Profil;
import de.hdm.thies.bankProjekt.server.db.DBConnection;
import de.hdm.thies.bankProjekt.shared.bo.Account;

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
	   * Auslesen aller Konten eines durch Fremdschlüssel (ProfilID.) gegebenen
	   * Profils.
	   * 
	   * @see findByProfil(Profil Profil)
	   * @param profil Schlüssel des zugehörigen Profils.
	   * @return Ein Vektor mit Information-Objekten, die sämtliche Informationen des
	   *         betreffenden Profils repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
	   */
	  public Vector<Information> findByProfil(Profil profil) {
	    Connection con = DBConnection.connection();
	    Vector<Information> result = new Vector<Information>();

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

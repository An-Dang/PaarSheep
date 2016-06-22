package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.*;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;


/**
 * Mapper-Klasse, die <code>Auswahl</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Dang
 * @author Thies
 * 
 * */

public class OptionMapper {
	
		/**
 	   * Die Klasse InformationMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   */
	private static OptionMapper optionMapper = null;

/**
 * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
 * neue Instanzen dieser Klasse zu erzeugen.
 */
	protected OptionMapper() {
	}
	
/**
 * Es kann nur eine einzige Instanz von ProfilMapper erzeugt werden
 * 
 * <b>Fazit:</b> AccountMapper sollte nicht mittels <code>new</code>
 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
 * 
 * @return DAS <code>OptionMapper</code>-Objekt.
 * @see accountMapper
 */
	public static OptionMapper optionMapper() {
		if (optionMapper == null) {
			optionMapper = new OptionMapper();
		}

		return optionMapper;
	}
	
/** 
* Diese Methode ermöglicht es eine Auswahl in der Datenbank anzulegen.
 * @param option 
* 
* @param pption
* @return Option
* @throws Exception
*/
	  public Option insert(Option option) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      /*
	       * Zunächst schauen wir nach, welches der momentan höchste
	       * Primärschlüsselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(EigenschaftsOptionID) AS maxid "
	          + "FROM EigenschaftsOption ");

	      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
	      if (rs.next()) {
	        /*
	         * auswahl erhält den bisher maximalen, nun um 1 inkrementierten
	         * Primärschlüssel.
	         */
	    	  option.setID(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
	        stmt.executeUpdate("INSERT INTO EigenschaftsOption (EigenschaftsOption, Optionsbezeichnung " + "VALUES ("
	            + option.getID() + "," + option.getID() + ")");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	    
	    return option;
	  }
		
	
	 /**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * @param option 
	   * 
	   * @return das als Parameter übergebene Objekt
	   */
	  public Option update(Option option) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE EigenschaftsOption " + "SET EigenschaftsOption=\"" + option.getID()
	          + "\" " + "WHERE EigenschaftsOptionID=" + option.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Um Analogie zu insert(Auswahl auswahl) zu wahren, geben wir auswahl zurück
	    return option;
	  }

	  /**
	   * Löschen der Daten eines <code>Auswahl</code>-Objekts aus der Datenbank.
	 * @param option 
	   * 
	   * @param a das aus der DB zu löschende "Objekt"
	   */
	  public void delete(Option option) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM EigenschaftsOption " + "WHERE id=" + option.getID());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	  }
	  
	  /**
	   * Auslesen aller Auswahl eines durch Fremdschlüssel (Eigenschaft_EigenschaftsID.) gegebenen
	   * Profils.
	   * 
	   * @see 
	   * @param eigenschaft Schlüssel der zugehörigen Eigenschaft.
	   * @return Ein ArrayList mit Information-Objekten, die sämtliche Informationen des
	   *         betreffenden Eigenschaft repräsentieren. Bei evtl. Exceptions wird ein
	   *         partiell gefüllter oder ggf. auch leerer Auswahl zurückgeliefert.
	   */
	  public ArrayList<Option> readOption() {
	    Connection con = DBConnection.connection();
	    ArrayList<Option> result = new ArrayList<Option>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery ("SELECT EigenschaftID, Erlaeuterung "
	  					+"FROM Eigenschaft "
						+"Where Eigenschaft.Eigenschaftstyp = 'o'");
//	    		  ("SELECT EigenschaftID, Erlaeuterung " 
//					+ "FROM Eigenschaft Inner Join EigenschaftsOption "
//					+ "ON Eigenschaft.EigenschaftID = EigenschaftsOption.EigenschaftsOptionID"
//					+ "Where Eigenchaftstyp = 'o'");

	      //Für jeden Eintrag im Suchergebnis wird nun ein Auswhal-Objekt erstellt.
	      //Muss nich angepasst werden

	      while (rs.next()) {
				
	    	  	Option option = new Option();
	    	  	option.setID(rs.getInt("EigenschaftID"));
				option.setErlaeuterung(rs.getString("Erlaeuterung"));
				result.add(option);

	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }
	  
	  /**
	 * @return Option
	 */
	public ArrayList<Option> readOptionAuswahl() {
		    Connection con = DBConnection.connection();
		    ArrayList<Option> result = new ArrayList<Option>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT EigenschaftsOption.Optionsbezeichnung " 
		    		  + "FROM Eigenschaft Inner Join  EigenschaftsOption "
		    		  + "ON Eigenschaft.EigenschaftID = EigenschaftsOption.EigenschaftsOptionID");

		      //Für jeden Eintrag im Suchergebnis wird nun ein Auswhal-Objekt erstellt.
		      //Muss nich angepasst werden

		      while (rs.next()) {
					
		    	  Option Option = new Option();
		    	  Option.setOptionsBezeichnung(rs.getString("Optionsbezeichnung"));
		    	  result.add(Option);

		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
		  }

	
}

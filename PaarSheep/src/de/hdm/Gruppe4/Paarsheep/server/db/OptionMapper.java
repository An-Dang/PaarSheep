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
	 * Es kann nur eine einzige Instanz von ProfilMapper erzeugt werden.
	 * 
	 * <b>Fazit:</b> AccountMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 * 
	 * @return optionMapper <code>OptionMapper</code>-Objekt.
	 */
	public static OptionMapper optionMapper() {
		if (optionMapper == null) {
			optionMapper = new OptionMapper();
		}
		return optionMapper;
	}
	
		/** 
		 * Diese Methode ermöglicht es eine Auswahl in der Datenbank anzulegen.
		 * @param option moegliche Auswahlen
		 * @param con Datenbankverbindung
		 * @param stmt Statement
		 * @return option liefert moegliche Auswahlen 
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
	         * Auswahl erhält den bisher maximalen, nun um 1 inkrementierten
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
	   * @param option Auswahl speichern
	   * @param con Datenbankverbindung
	   * @param stmt Statment
	   * @return option das als Parameter übergebene Objekt
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
	    // Um Analogie zu insert(Auswahl auswahl) zu wahren, geben wir Auswahl zurück
	    return option;
	  }

	  /**
	   * Löschen der Daten eines <code>Auswahl</code>-Objekts aus der Datenbank.
	   * @param option Auswahl speichern; aus der DB zu loeschende Objekt
	   * @param con Datenbankverbindung
	   * @param stmt Statement
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
	   * Auslesen aller Auswahlen eines durch Fremdschlüssel (Eigenschaft_EigenschaftsID.) gegebenen
	   * Profils.
	   * @param con Datenbankverbindung
	   * @param result ArrayList, in welche alle gefundenen Eigenschaften geschrieben werden
	   * @param option Schlüssel der zugehörigen Eigenschaft.
	   * @param stmt Statement
	   * @return result Ein ArrayList mit Information-Objekten, die sämtliche Informationen des
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

	      /* Für jeden Eintrag im Suchergebnis wird nun ein Auswhal-Objekt erstellt.
	     		muss nich angepasst werden */

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
	   * Fuer jeden Eintrag in der Suche wird ein Auswahl-Objekt erstellt.
	   * @param con Datenbankverbindung
	   * @param result ArrayList, in welche die ausgelesenen Werte gespeichert werden
	   * @param Option Auswahl-Objekte
	   * @return result ArrayList mit Ergebnissen zurueckgeben
	   */
	public ArrayList<Option> readOptionAuswahl(int eigenschaftsID) {
		    Connection con = DBConnection.connection();
		    ArrayList<Option> result = new ArrayList<Option>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT EigenschaftsOption.Optionsbezeichnung " 
		    		  + "FROM Eigenschaft Inner Join  EigenschaftsOption "
		    		  + "ON Eigenschaft.EigenschaftID = EigenschaftsOption.EigenschaftsOptionID "
		    		  + "Where EigenschaftsOption.EigenschaftsOptionID = " + eigenschaftsID);

		      //Für jeden Eintrag im Suchergebnis wird nun ein Auswahl-Objekt erstellt.
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

	/**
	 * Eigenschaften eines Profils suchen.
	 * @param con Datenbankverbindung
	 * @param result ArrayList, in welche die Optionen geschrieben werden
	 * @param stmt Statement
	 * @param profilID ID des aktuellen Profils
	 * @return result ArrayList mit Ergebnissen zurueckgeben
	 */
	 public ArrayList<Option> findOptionByProfil(int profilID) {
		    Connection con = DBConnection.connection();
		    ArrayList<Option> result = new ArrayList<Option>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery ("SELECT Eigenschaft.EigenschaftID, Erlaeuterung "
		  					+ "FROM Eigenschaft , Information "
							+ "Where Eigenschaft.Eigenschaftstyp = 'o' "
							+ "AND Information.ProfilID = " + profilID
							+ " AND Information.EigenschaftID = Eigenschaft.EigenschaftID");

		      //Für jeden Eintrag im Suchergebnis wird nun ein Auswahl-Objekt erstellt.
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
}

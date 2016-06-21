package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Eigenschaft</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * 
 * @author Thies
 * @author Hauler
 * @author Dang
 */

public class EigenschaftMapper {

	/**
	 * Die Klasse EigenschaftMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */
	private static EigenschaftMapper eigenschaftMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected EigenschaftMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>EigenschaftMapper.eigenschaftMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>EigenschaftMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> EigenschaftMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>EigenschaftMapper</code>-Objekt.
	 * @see eigenschaftMapper
	 */
	public static EigenschaftMapper eigenschaftMapper() {
		if (eigenschaftMapper == null) {
			eigenschaftMapper = new EigenschaftMapper();
		}

		return eigenschaftMapper;
	}

	/**
	 * Einfügen eines <code>Eigenschaft</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param eigenschaft
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>ID</code>.
	 */
	
	public ArrayList<Beschreibung> readEigenschaft() {

		ArrayList<Beschreibung> result = new ArrayList<Beschreibung>();

		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT EigenschaftID, Erlaeuterung " 
					+ "FROM Eigenschaft Inner Join Beschreibung "
					+ "ON Eigenschaft.EigenschaftID = Beschreibung.BeschreibungsID");

			while (rs.next()) {
				
				Beschreibung beschreibung = new Beschreibung();
				beschreibung.setID(rs.getInt("EigenschaftID"));
				beschreibung.setErlaeuterung(rs.getString("Erlaeuterung"));
				result.add(beschreibung);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		return result;
	}


	/**
	 * @param eigenschaft
	 * @return eigenschaft
	 */
	public Eigenschaft insert(Eigenschaft eigenschaft) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(EigenschaftID) AS maxid " + "FROM Eigenschaft ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * a erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				eigenschaft.setID(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				// Noch nicht vollständig
				stmt.executeUpdate(
						"INSERT INTO Eigenschaft (EigenschaftID,  " + "VALUES (" + eigenschaft.getID() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Rückgabe der Eigenschaft.

		return eigenschaft;
	}

	/**
	 * Löschen der Daten eines <code>Eigenschaft</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param eigenschaft
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Eigenschaft eigenschaft) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Eigenschaft " + "WHERE EigenschaftID=" + eigenschaft.getID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 /**
	 * @param profilID
	 * @return result
	 */
	public ArrayList<Beschreibung> findEigenschaftByProfil(int profilID){
		Connection con = DBConnection.connection();
	    ArrayList<Beschreibung> result = new ArrayList<Beschreibung>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("Select Erlaeuterung "
	    		  + " From Information, Eigenschaft , Beschreibung "
	    		  + " Where Information.ProfilID = " + profilID 
                  + " AND Information.EigenschaftID = Eigenschaft.EigenschaftID "
	    		  + " AND Eigenschaft.EigenschaftID = Beschreibung.BeschreibungsID");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Informations-Objekt erstellt.
	      while (rs.next()) {
	    	  Beschreibung beschreibung = new Beschreibung();
	    	  beschreibung.setErlaeuterung(rs.getString("Erlaeuterung"));
	        
	        // Hinzufügen des neuen Objekts zum Array
	        result.add(beschreibung);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	 }

}

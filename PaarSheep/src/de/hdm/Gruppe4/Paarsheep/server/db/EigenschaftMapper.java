package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.ArrayList;

import com.google.gwt.user.client.Window;

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
	 * @see EigenschaftMapper()
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
	

	public ArrayList<Eigenschaft> readEigenschaft() {

		ArrayList<Eigenschaft> result = new ArrayList<Eigenschaft>();

		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT Erlaeuterung FROM Eigenschaft");

			while (rs.next()) {
				
				Eigenschaft eigenschaft = new Beschreibung();
				eigenschaft.setErlaeuterung(rs.getString("Erlaeuterung"));
				result.add(eigenschaft);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		return result;
	}


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

}

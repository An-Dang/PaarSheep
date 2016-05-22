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
 */

public class MerkzettelMapper {

	/**
	 * Die Klasse MerkzettelMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see MerkzettelMapper()
	 */
	private static MerkzettelMapper merkzettelMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected MerkzettelMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>MerkzettelMapper.merkzettelMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>MerkzettelMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> MerkzettelMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
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
	 * Einfügen eines <code>Merkzettel</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param merkzettel
	 *            das zu speichernde Objekt
	 */
	public Merkzettel insert(Merkzettel merkzettel) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(MerkzettelID) AS maxid " + "FROM Merkzettel ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * merkzettel erhält den bisher maximalen, nun um 1
				 * inkrementierten Primärschlüssel.
				 */
				merkzettel.setID(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO Merkzettel (MerkzettelID, MerkenderID , GemerkterID " + "VALUES ("
						+ merkzettel.getID() + "," + merkzettel.getMerkenderID() + "," + merkzettel.getGermerkterID()
						+ ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/**
		 * Rückgabe, des evtl. korrigierten Merkzettels.
		 */
		return merkzettel;
	}

	/**
	 * Profil von Merkliste entfernen
	 * 
	 * @param merkzettel
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void delete(Merkzettel merkzettel) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Merkzettel " + "WHERE MerkzettelID=" + merkzettel.getID());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Löschen des Merkzettels (<code>Merkzettel</code>-Objekt) eines
	 * Nutzerprofils. Diese Methode sollte aufgerufen werden, bevor ein
	 * <code>Nutzerprofil</code> -Objekt gelöscht wird.
	 * 
	 * @param Nutzerprofil
	 *            das <code>Nutzerprofil</code>-Objekt, zu dem der Merkzettel
	 *            gehört
	 */
	public void deleteMerkzettelOf(Nutzerprofil nutzerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Merkzettel " + "WHERE MerkenderID=" + nutzerprofil.getID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auslesen aller Merkzettel eines durch Fremdschlüssel
	 * (MerkenderID) gegebenen Nutzerprofils.
	 * 
	 * @see findByMerkenderID(int nutzerprofil) 
	 * @param Merkzettel
	 *            Schlüssel des zugehörigen Nutzerprofils.
	 * @return ArrayList Nutzerprofil-Objekt
	 */
	public ArrayList<Nutzerprofil> findByMerkenderID(int nutzerprofil) {
		Connection con = DBConnection.connection();
		ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Merkzettel INNER JOIN Nutzerprofil"
					+ "ON Merkzettel.GemerkteID = Nutzerprofil.NutzerprofilID"
					+ "WHERE Merkzettel.MerkendeID=" + nutzerprofil);

			/**
			 * Für jeden Eintrag im Suchergebnis wird nun ein Merkzettel-Objekt
			 * erstellt.
			 */

			while (rs.next()) {
				Nutzerprofil np = new Nutzerprofil();
				
				np.setVorname(rs.getString("Vorname"));
				np.setNachname(rs.getString("Nachname"));
				
				

				// Hinzufügen des neuen Objekts zur ArrayList
				result.add(np);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ArrayList zurückgeben
		return result;
	}

}

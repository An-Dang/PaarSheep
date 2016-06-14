package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.ArrayList;
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
	 * Einfügen eines Merkzettels
	 */
	public void insert(Nutzerprofil nutzerprofilID, int GemerkterID) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO Merkzettel ( MerkenderID , GemerkteID)  VALUES (" + nutzerprofilID.getID()
					+ "," + GemerkterID + ")");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Profil von Merkliste entfernen
	 */
	public void delete(Nutzerprofil MerkenderID, int GemerkterID) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM Merkzettel " + "WHERE MerkenderID =" + MerkenderID.getID()
					+ " AND Merkzettel.GemerkteID =" + GemerkterID);
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Löschen des Merkzettels
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
	 * Auslesen Merkzettel
	 */
	public ArrayList<Nutzerprofil> findByMerkenderID(Nutzerprofil nutzerprofil) {
		// Nutzerprofil wird übergeben
		final Nutzerprofil profil = nutzerprofil;
		Connection con = DBConnection.connection();
		ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();
		// ArrayList wo später Nutzerprofile gespeichert werden

		try {
			Statement stmt = con.createStatement();
			// Im Stmt wird der Merkzettel von dem Eingeloggtem Nutzer
			// ausgelesen
			ResultSet rs = stmt.executeQuery("SELECT * FROM Merkzettel WHERE MerkenderID=" + profil.getID());

			while (rs.next()) {

				Statement stmt2 = con.createStatement();
				// Im stmt2 werden die Informationen des Germekrtennutzerprofils
				// ausgelsen
				ResultSet rs2 = stmt2.executeQuery(
						"SELECT * " + "FROM Nutzerprofil WHERE Nutzerprofil_ProfilID =" + rs.getInt(2)); 

				// Im rs2 wird wird jede Zeile ausgelsen und in np
				// abgespeichert.
				while (rs2.next()) {

					Nutzerprofil np = new Nutzerprofil();

					np.setID(rs2.getInt(6));
					//np.setProfilID(rs2.getInt(1));
					np.setVorname(rs2.getString(3));
					np.setNachname(rs2.getString(4));
					np.setGeburtsdatum(rs2.getDate(2));
					np.setEmailAddress(rs2.getString(5));

					result.add(np);

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ArrayList zurückgeben
		return result;
	}

}

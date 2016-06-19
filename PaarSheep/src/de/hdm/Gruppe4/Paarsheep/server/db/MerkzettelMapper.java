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
	 * @param nutzerprofilID 
	 * @param GemerkterID 
	 * @return int
	 */
	public int insert(int nutzerprofilID, int GemerkterID) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO Merkzettel ( MerkenderID , GemerkteID)  VALUES (" + nutzerprofilID + ","
					+ GemerkterID + ")");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return GemerkterID;
	}

	/**
	 * Profil von Merkliste entfernen
	 * @param nutzerprofilID 
	 * @param GemerkterID 
	 */
	public void delete(int nutzerprofilID, int GemerkterID) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM Merkzettel " + "WHERE MerkenderID =" + nutzerprofilID
					+ " AND Merkzettel.GemerkteID =" + GemerkterID);
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Löschen des Merkzettels
	 * @param nutzerprofilID 
	 */
	public void deleteMerkzettelOf(int nutzerprofilID) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM Merkzettel " + "WHERE MerkenderID=" + nutzerprofilID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auslesen Merkzettel
	 * @param nutzerprofilID 
	 * @return result
	 */
	public ArrayList<Nutzerprofil> findByMerkenderID(int nutzerprofilID) {

		Connection con = DBConnection.connection();
		ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();
		// ArrayList wo später Nutzerprofile gespeichert werden

		try {
			Statement stmt = con.createStatement();
			// Im Stmt wird der Merkzettel von dem Eingeloggtem Nutzer
			// ausgelesen
			ResultSet rs = stmt
					.executeQuery(" SELECT Nutzerprofil.NutzerprofilID, Nutzerprofil.vorname, Nutzerprofil.nachname"
							+ " FROM Nutzerprofil, Profil, Merkzettel WHERE Merkzettel.MerkenderID =" + nutzerprofilID
							+ " AND Nutzerprofil.NutzerprofilID = Merkzettel.GemerkteID "
							+ " AND Profil.ProfilID = Merkzettel.GemerkteID");

			while (rs.next()) {

				Nutzerprofil np = new Nutzerprofil();

				np.setID(rs.getInt(1));
				np.setVorname(rs.getString("vorname"));
				np.setNachname(rs.getString("nachname"));

				result.add(np);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ArrayList zurückgeben
		return result;
	}

	/**
	 * 
	 * @param nutzerprofilID
	 * @param fremdprofilID
	 * @return int vermerkStatus
	 */
	public int pruefeVermerkstatus(int nutzerprofilID, int fremdprofilID) {
		Connection con = DBConnection.connection();

		int vermerkStatus = 0;

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("Select GemerkteID From Merkzettel Where GemerkteID = " + fremdprofilID 
					+ " AND MerkenderID = " + nutzerprofilID);

			if (rs.next()) {
				vermerkStatus = 1;
			} else {
				vermerkStatus = 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vermerkStatus;

	}

}

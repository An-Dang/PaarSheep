package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Information</code>-Objekte auf eine relationale
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

public class InformationMapper {
	/**
	 * Die Klasse InformationMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */
	private static InformationMapper informationMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected InformationMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>InformationMapper.informationMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>InformationMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> InformationMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>InformationMapper</code>-Objekt.
	 * @see informationMapper
	 */
	public static InformationMapper informationMapper() {
		if (informationMapper == null) {
			informationMapper = new InformationMapper();
		}

		return informationMapper;
	}

	/**
	 * Einfügen eines <code>Information</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param information
	 *            das zu speichernde Objekt
	 * @param ProfilID
	 * @param EigenschaftID
	 * @param Information
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Information insertInformation(Information information, int ProfilID, int EigenschaftID, String Information) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(InformationID) AS maxid " + "FROM information ");
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * a erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				information.setID(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO information (InformationID, ProfilID, EigenschaftID, Information) "
						+ "VALUES (" + information.getID() + "," + ProfilID + "," + EigenschaftID + ",'" + Information
						+ "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Rückgabe der Information
		return information;
	}

	/**
	 * @param info
	 * @param profilID
	 * @param eigenschaftID
	 */
	public void bearbeiteNutzerprofilInfo(String info, int profilID, int eigenschaftID) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// Dieses Statement übergibt die Werte an die Tabelle Profil
			stmt.executeUpdate(
					"UPDATE Information SET  Information.Information = '" + info + "' WHERE Information.ProfilID = "
							+ profilID + " AND Information.EigenschaftID =  " + eigenschaftID);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Löschen der Daten eines <code>Information</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param profilID
	 * 
	 * @param information
	 *            das aus der DB zu löschende "Objekt"
	 */
	public void deleteAllNutzerInfo(int profilID) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("Delete from Information Where Information.ProfilID =" + profilID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auslesen aller Informationen eines durch Fremdschlüssel (ProfilID)
	 * gegebenen Profils.
	 * 
	 * @param profilID
	 * 
	 * @param ProfilID
	 *            Schlüssel des zugehörigen Profils.
	 * @return Ein ArrayList mit Information-Objekten, die sämtliche Information
	 *         des betreffenden Profils repräsentieren. Bei evtl. Exceptions
	 *         wird ein partiell gefüllter oder ggf. auch leerer ArrayList
	 *         zurückgeliefert.
	 */
	public ArrayList<Information> findInfoByProfil(int profilID) {
		Connection con = DBConnection.connection();
		ArrayList<Information> result = new ArrayList<Information>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select Information.Information, Information.EigenschaftID "
					+ "From Information inner join Eigenschaft "
					+ "on Information.EigenschaftID = Eigenschaft.EigenschaftID " + "And Information.ProfilID ="
					+ profilID + " AND Eigenschaft.Eigenschaftstyp not like 'o'");
			// Für jeden Eintrag im Suchergebnis wird nun ein
			// Informations-Objekt erstellt.
			while (rs.next()) {
				Information information = new Information();
				information.setInformation(rs.getString("Information"));
				information.setID(rs.getInt("Information.EigenschaftID"));
				// Hinzufügen des neuen Objekts zum Array
				result.add(information);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * @param profilID
	 * @return result
	 */
	public ArrayList<Information> findAuswahlInfoByProfil(int profilID) {
		Connection con = DBConnection.connection();
		ArrayList<Information> result = new ArrayList<Information>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select Information " + "From Information inner join Eigenschaft "
					+ "on Information.EigenschaftID = Eigenschaft.EigenschaftID " + "And Information.ProfilID ="
					+ profilID + " And Eigenschaft.Eigenschaftstyp = 'o'");

			// Für jeden Eintrag im Suchergebnis wird nun ein
			// Informations-Objekt erstellt.
			while (rs.next()) {
				Information information = new Information();
				information.setInformation(rs.getString("Information"));

				// Hinzufügen des neuen Objekts zum Array
				result.add(information);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * @param profilID
	 * @return ArrayList<Information>
	 */
	public ArrayList<Information> findAllInfoByProfil(int profilID) {
		Connection con = DBConnection.connection();
		ArrayList<Information> result = new ArrayList<Information>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"Select Information, Information.EigenschaftID " + "From Information inner join Eigenschaft "
							+ "on Information.EigenschaftID = Eigenschaft.EigenschaftID " + "And Information.ProfilID ="
							+ profilID);

			// Für jeden Eintrag im Suchergebnis wird nun ein
			// Informations-Objekt erstellt.
			while (rs.next()) {
				Information information = new Information();
				information.setInformation(rs.getString("Information"));
				information.setID(rs.getInt("Information.EigenschaftID"));

				// Hinzufügen des neuen Objekts zum Array
				result.add(information);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * @param profilID
	 * @param eigID
	 */
	public void deleteNutzerInfo(int profilID, int eigID) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("Delete from Information Where Information.ProfilID =" + profilID
					+ "AND Information.EigenschaftID = " + eigID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
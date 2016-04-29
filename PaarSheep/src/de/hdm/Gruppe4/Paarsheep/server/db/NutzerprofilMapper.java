package de.hdm.Gruppe4.Paarsheep.server.db;

import java.sql.*;


public class NutzerprofilMapper {
	
	/**
	 * Die Klasse NutzerMapper wird nur einmal instantiiert. Man spricht hierbei
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see NutzerMapper()
	 */
	private static NutzerprofilMapper nutzerprofilMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected NutzerprofilMapper() {

	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NutzerprofilMapper.nutzerpofilMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>NutzerpofilMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> NutzerprofilMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>NutzerprofilMapper</code>-Objekt.
	 * @see nutzerpofilMapper
	 */
	public static NutzerprofilMapper nutzerprofilMapper() {
		if (nutzerprofilMapper == null) {
			nutzerprofilMapper = new NutzerprofilMapper();
		}
		return nutzerprofilMapper;
	}

	
	/**
	 * Einfügen eines <code>Nutzerpofil</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param a
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 * @throws Exception        
	 */
	public Nutzerprofil insert(Nutzerprofil nutzerprofil)
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt=null;
		try {
			 Statement stmt = con.createStatement();
	
			
			/*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM nutzerprofil ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * c erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        nutzerprofil.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO nutzerprofil (nutzerprofilid, vorname, nachname, geburtsdatum) "
		            + "VALUES (" + nutzerprofil.getnutzerprofilId() + ",'" + nutzerprofil.getNachname() + "','"
		            + nutzerprofil.getVorname() + "','" nutzerprofil.getGeburtsdatum + "')");
		      }
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());

		}
		return nutzerpofil;
		
		
		

		  /**
		   * Wiederholtes Schreiben eines Objekts in die Datenbank.
		   * 
		   * @param nutzerprofil das Objekt, das in die DB geschrieben werden soll
		   * @return das als Parameter übergebene Objekt
		   */
		  public Nutzerprofil update(Nutzerprofil Nutzerprofil) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE nutzerprofil " + "SET vorname=\""
		          + nutzerprofil.getVorname() + "\", " + "nachname=\"" + nutzerprofil.getNachname() + "\" "
		          + "WHERE id=" + nutzerprofil.getNutzerprofilID());

		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    // Um Analogie zu insert(Customer c) zu wahren, geben wir c zurück
		    return nutzerprofil;
		  }
	
}
}

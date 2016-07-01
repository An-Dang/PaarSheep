package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * Die Klasse BesuchteProfilListe erweitert die Klasse Profil.
 * @author andang
 *
 */
public class BesuchteProfilListe extends Profil {

	private static final long serialVersionUID = 1L;

	private int besuchteID;
	private int besucherID;

	/**
	 * Die ID des Profils, welches besucht wurde, ausgeben
	 * @return besuchteID ID des besuchten Profils
	 */
	public int getBesuchteID() {
		return besuchteID;
	}

	/**
	 * ID des besuchten Profils setzen
	 * @param besuchteID ID des besuchten Profils
	 */
	public void setBesuchteID(int besuchteID) {
		this.besuchteID = besuchteID;
	}
	
	/**
	 * ID des besuchenden Profils (auf ein anderes Profil) ausgeben
	 * @return besucherID ID des besuchenden Profils ausgeben
	 */
	public int getBesucherID() {
		return besucherID;
	}

	/**
	 * Die ID des besuchenden Profils setzen
	 * @param besucherID ID des besuchenden Profils setzen
	 */
	public void setBesucherID(int besucherID) {
		this.besucherID = besucherID;
	}
}

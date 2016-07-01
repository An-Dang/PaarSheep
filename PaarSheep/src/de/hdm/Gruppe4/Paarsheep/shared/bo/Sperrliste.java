package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * Die Klasse Sperrliste erweitert die Klasse BusinessObject.
 * @author andang
 *
 */
public class Sperrliste extends BusinessObject {

	private static final long serialVersionUID = 1L;


	private int sperrenderID;
	private int gesperrterID;


	/**
	 * Die ID des sperrenden Profils zurueckgeben
	 * @return sperrenderID ID des sperrenden Profils
	 */
	public int getSperrenderID() {
		return this.sperrenderID;
	}


	/**
	 * ID des sperrenden Profils setzen
	 * @param ProfilID ID des sperrenden Profils (aktueller User)
	 */
	public void setSperrenderID(int ProfilID) {
		this.sperrenderID = ProfilID;
	}

	/**
	 * Id des gesperrten Profils zurueckgeben
	 * @return gesperrterID ID des gesperrten Profils
	 */
	public int getGesperrterID() {
		return gesperrterID;
	}

	/**
	 * ID des gesperrten Profils setzen
	 * @param ProfilID ID des gesperrten Profils 
	 */
	public void setGesperrterID(int ProfilID) {
		this.gesperrterID = ProfilID;
	}

}

package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Sperrliste extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Fremdschluesselbeziehung zum Inhaber Nutzerprofils der Sperrrliste.
	 * 
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	private int sperrenderID;
	private int gesperrterID;

	/**
	 * Auslesen des Fremdschluessels zum Inhaber der Sperrliste.
	 * 
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public int getSperrenderID() {
		return this.sperrenderID;
	}

	/**
	 * Setzen des Fremdschluessels zum Inhaber der Sperrliste.
	 * 
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public void setSperrenderID(int ProfilID) {
		this.sperrenderID = ProfilID;
	}

	/**
	 * Auslesen des Fremdschluessels zum Inhaber der Sperrliste.
	 * 
	 * @author An Dang
	 */
	public int getGesperrterID() {
		return gesperrterID;
	}

	/**
	 * Setzen des Fremdschluessels zum Inhaber der Sperrliste.
	 * 
	 * @author An Dang
	 */

	public void setGesperrterID(int ProfilID) {
		this.gesperrterID = ProfilID;
	}

}

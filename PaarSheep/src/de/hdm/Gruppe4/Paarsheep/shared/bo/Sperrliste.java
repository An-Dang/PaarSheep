package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * @author andang
 *
 */
public class Sperrliste extends BusinessObject {

	private static final long serialVersionUID = 1L;


	private int sperrenderID;
	private int gesperrterID;


	/**
	 * @return sperrenderID
	 */
	public int getSperrenderID() {
		return this.sperrenderID;
	}


	/**
	 * @param ProfilID
	 */
	public void setSperrenderID(int ProfilID) {
		this.sperrenderID = ProfilID;
	}

	/**
	 * @return gesperrterID
	 */
	public int getGesperrterID() {
		return gesperrterID;
	}

	/**
	 * @param ProfilID
	 */
	public void setGesperrterID(int ProfilID) {
		this.gesperrterID = ProfilID;
	}

}

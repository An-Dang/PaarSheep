package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * Die Klasse Merkzettel erweitert die BusinessObject Klassen.
 * @author andang
 *
 */
public class Merkzettel extends BusinessObject {

	private static final long serialVersionUID = 1L;
	private int merkenderID;
	private int gemerkterID;
	
	  /**
	   * Die ID des merkenden Profils (aktueller User)  wird zurueckgegeben
	 * @return merkenderID ProfilID des merkenden Users
	 */
	public int getMerkenderID() {
	    return this.merkenderID;
	  }

	  /**
	   * Die ID des merkenden Profils wird gesetzt
	 * @param profilID ProfilID des merkenden Users
	 */
	public void setMerkenderID(int profilID) {
	    this.merkenderID = profilID;
	  }

	  
	  /**
	   * ID des gemerkten (fremden) Profils zurueckgeben
	 * @return gemerkterID gemerktes, fremdes Profil
	 */
	public int getGemerkterID() {
		return gemerkterID;
	}

	/**
	 * ID des gemerkten (fremden) Profils wird gesetzt
	 * @param profilID ID des gemerkten Profils
	 */
	public void setGemerkterID(int profilID) {
		this.gemerkterID = profilID;
	}
}

package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * @author andang
 *
 */
public class Merkzettel extends BusinessObject {

	private static final long serialVersionUID = 1L;


	private int merkenderID;
	

	private int gemerkterID;
	
	
	  /**
	 * @return merkenderID
	 */
	public int getMerkenderID() {
	    return this.merkenderID;
	  }

	  /**
	 * @param profilID
	 */
	public void setMerkenderID(int profilID) {
	    this.merkenderID = profilID;
	  }

	  
	  /**
	 * @return gemerkterID
	 */
	public int getGemerkterID() {
		return gemerkterID;
	}

	/**
	 * @param profilID
	 */
	public void setGemerkterID(int profilID) {
		this.gemerkterID = profilID;
	}
	
	
}

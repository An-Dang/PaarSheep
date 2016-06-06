package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Merkzettel extends BusinessObject {

	private static final long serialVersionUID = 1L;

	 /**
	   * Fremdschluesselbeziehung zum Merkenden Nutzerprofil.
	   * @author Dominik Sasse
	   * @author An Dang
	   * @author Tino Hauler 
	   */
	private int merkenderID;
	
	 /**
	   * Fremdschluesselbeziehung zum Gemerkten Nutzerprofil.
	   * @author An Dang
	   * @author Tino Hauler 
	   */
	private int gemerkterID;
	
	
	
// Merkender
	  public int getMerkenderID() {
	    return this.merkenderID;
	  }
	  

	  public void setMerkenderID(int profilID) {
	    this.merkenderID = profilID;
	  }
	  
		
//Gemerkte		
	  public int getGemerkterID() {
		return gemerkterID;
	}

	public void setGemerkterID(int profilID) {
		this.gemerkterID = profilID;
	}
	
	
}

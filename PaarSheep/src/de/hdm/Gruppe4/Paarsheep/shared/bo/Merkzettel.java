package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Merkzettel extends BusinessObject {

	private static final long serialVersionUID = 1L;

	 /**
	   * Fremdschluesselbeziehung zum Merkenden Nutzerprofil.
	   * @author Dominik Sasse
	   */
	private int merkenderID;
	
	 /**
	   * Fremdschluesselbeziehung zum Gemerkten Nutzerprofil.
	   * @author An Dang
	   * @author Tino Hauler 
	   */
	private int germerkterID;
	
	
	
// Merkender
	  public int getMerkenderID() {
	    return this.merkenderID;
	  }
	  

	  public void setMerkenderID(int ProfilID) {
	    this.merkenderID = ProfilID;
	  }
	  
		
//Gemerkte		
	  public int getGermerkterID() {
		return germerkterID;
	}

	public void setGermerkterID(int germerkterID) {
		this.germerkterID = germerkterID;
	}
	
	
}

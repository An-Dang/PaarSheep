package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Merkzettel extends BusinessObject {

	private static final long serialVersionUID = 1L;


	private int merkenderID;
	

	private int gemerkterID;
	
	
	  public int getMerkenderID() {
	    return this.merkenderID;
	  }
	  

	  public void setMerkenderID(int profilID) {
	    this.merkenderID = profilID;
	  }
	  
		
	  
	  public int getGemerkterID() {
		return gemerkterID;
	}

	public void setGemerkterID(int profilID) {
		this.gemerkterID = profilID;
	}
	
	
}

package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Merkzettel extends BusinessObject {

	private static final long serialVersionUID = 1L;

	 /**
	   * Fremdschluesselbeziehung zum Inhaber des Merkzettels.
	   * @author Dominik Sasse
	   */
	private int merkender_NutzerprofilID;
	
	
	  /**
	   * Auslesen des Fremdschluessels zum Inhaber des Merkzettels.
	   * @author Dominik Sasse
	   */
	  public int getMerkender_NutzerprofilID() {
	    return this.merkender_NutzerprofilID;
	  }
	  
	  /**
	   * Setzen des Fremdschluessels zum Inhaber des Merkzettels.
	   * @author Dominik Sasse
	   */
	  public void setMerkender_NutzerprofilID(int ProfilID) {
	    this.merkender_NutzerprofilID = ProfilID;
	  }
	
	
	
}

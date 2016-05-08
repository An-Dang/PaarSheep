package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Sperrliste extends BusinessObject {
	
	private static final long serialVersionUID = 1L;


	
	 /**
	   * Fremdschluesselbeziehung zum Inhaber Nutzerprofils der Sperrrliste.
	   * @author Dominik Sasse
	   */
	private int sperrender_NutzerprofilID;
	
	
	  /**
	   * Auslesen des Fremdschluessels zum Inhaber der Sperrliste.
	   * @author Dominik Sasse
	   */
	  public int getSperrender_NutzerprofilID() {
	    return this.sperrender_NutzerprofilID;
	  }
	  
	  /**
	   * Setzen des Fremdschluessels zum Inhaber der Sperrliste.
	   * @author Dominik Sasse
	   */
	  public void setSperrender_NutzerprofilID(int ProfilID) {
	    this.sperrender_NutzerprofilID = ProfilID;
	  }
}

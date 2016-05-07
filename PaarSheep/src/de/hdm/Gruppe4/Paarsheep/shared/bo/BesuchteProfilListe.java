package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class BesuchteProfilListe extends BusinessObject {

	private static final long serialVersionUID = 1L;

	
	 /**
	   * Fremdschluesselbeziehung zum Inhaber der BesuchteProfilListe.
	   * @author Dominik Sasse
	   */
	private int besuchender_NutzerprofilID;
	
	
	  /**
	   * Auslesen des Fremdschluessels zum Inhaber der BesuchteProfilListe.
	   * @author Dominik Sasse
	   */
	  public int getBesuchender_NutzerprofilID() {
	    return this.besuchender_NutzerprofilID;
	  }
	  
	  /**
	   * Setzen des Fremdschluessels zum Inhaber der BesuchteProfilListe.
	   * @author Dominik Sasse
	   */
	  public void setBesuchender_NutzerprofilID(int ProfilID) {
	    this.besuchender_NutzerprofilID = ProfilID;
	  }
}

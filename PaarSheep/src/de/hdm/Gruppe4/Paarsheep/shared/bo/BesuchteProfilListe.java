package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class BesuchteProfilListe extends BusinessObject {

	private static final long serialVersionUID = 1L;

	 /**
	   * Fremdschluesselbeziehung zum Besuchte Nutzerprofile.
	   * @author Dominik Sasse
	   * @author An Dang
	   * @author Tino Hauler 
	   */
	private int besuchteID;
	
	 /**
	   * Fremdschluesselbeziehung zum Besuchter Nutzerprofile.
	   * @author Dominik Sasse
	   * @author An Dang
	   * @author Tino Hauler 
	   */
	private int besucherID;
	

	/**
	 * 
	 * @return the besuchteID
	 */
	public int getBesuchteID() {
		return besuchteID;
	}

	/**
	 * @param besuchteID the besuchteID to set
	 */
	public void setBesuchteID(int besuchteID) {
		this.besuchteID = besuchteID;
	}

	/**
	 * @return the besucherID
	 */
	public int getBesucherID() {
		return besucherID;
	}

	/**
	 * @param besucherID the besucherID to set
	 */
	public void setBesucherID(int besucherID) {
		this.besucherID = besucherID;
	}
}

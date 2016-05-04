package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Information extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Inhalt der Freitext/- und Auswahlfelder
	 * @author Manuel Weiler
	 */
	private String information = "";
	
	/**
	 * Auslesen der Informationen
	 * @return information
	 * @author Manuel Weiler
	 */
	public String getInformation(){
		return this.information;
	}
}

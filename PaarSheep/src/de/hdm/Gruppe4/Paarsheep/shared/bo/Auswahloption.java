package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Auswahloption extends Auswahl{

	private static final long serialVersionUID = 1L;

	/**
	 * Bezeichnung der einzelnen Auswahloptionen
	 * @author Manuel Weiler
	 */
	private String optionsBezeichnung = "";

	/**
	 * Bezeichnung der einzelnen Auswahloptionen auslesen
	 * @return optionsBezeichnung
	 * @author Manuel Weiler
	 */
	public String getOptionsBezeichnung() {
		return this.optionsBezeichnung;
	}
	
	/**
	 *Methode um das Attribut optionsbezeichnung zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setOptionsBezeichnung(String optionsBezeichnung){
		this.optionsBezeichnung = optionsBezeichnung;
	}
}

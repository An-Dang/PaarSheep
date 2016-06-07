package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Beschreibung extends Eigenschaft {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Inhalt der Beschreibung der einzelnen Freitextfelder
	 * @author Manuel Weiler 
	 */
	private String erlaeuterung = "";
	
	/**
	 * Setzen der Beschreibung f�r die Freitextfelder
	 * @param beschreibung
	 * @author Manuel Weiler
	 */
	public void setErlaeuterung(String erlaeuterung){
		this.erlaeuterung = erlaeuterung;
	}
	
	/**
	 *Methode um das Attribut beschreibung abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public String getErlaeuterung(){
		return this.erlaeuterung;
	}
}

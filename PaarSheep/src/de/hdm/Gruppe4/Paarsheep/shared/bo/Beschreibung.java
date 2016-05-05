package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Beschreibung extends Eigenschaft {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Inhalt der Beschreibung der einzelnen Freitextfelder
	 * @author Manuel Weiler 
	 */
	private String beschreibung = "";
	
	/**
	 * Setzen der Beschreibung f�r die Freitextfelder
	 * @param beschreibung
	 * @author Manuel Weiler
	 */
	public void setBeschreibung(String beschreibung){
		this.beschreibung = beschreibung;
	}
	
	/**
	 *Methode um das Attribut beschreibung abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public String getBeschreibung(){
		return this.beschreibung;
	}
}

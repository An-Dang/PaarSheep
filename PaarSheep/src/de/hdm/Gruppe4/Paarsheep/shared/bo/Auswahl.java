package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Auswahl extends Eigenschaft {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Bezeichnung der Auswahlmöglichkeit
	 * @author Manuel Weiler
	 */
	private String bezeichnung = "";
	
	/**
	 * Auslesesn der einzelnen Bezeichnungen
	 * @return bezeichnung
	 * @author Manuel Weiler
	 */
	public String getBezeichnung(){
		return this.bezeichnung;
	}
	
	/**
	 * Anlegen des Objects Auswahloption
	 * @author Manuel Weiler
	 */
	private Object auswahlOption;
	
	/**
	 * Auswahloptionen auslesen lassen
	 * @return auswahlOption
	 * @author Manuel Weiler
	 */
	public Object getAuswahloption(){
		return this.auswahlOption;
	}
	
	/**
	 * NOCH LEER!!!!!!
	 * @param Auswahloption
	 * @author Manuel Weiler
	 */
	public void waehleAuswahlOption(Object Auswahloption){
		
		
	}

}

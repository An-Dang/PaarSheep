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
	 *Methode um das Attribut bezeichnung zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setBezeichnung(String bezeichnung){
		this.bezeichnung = bezeichnung;
	}
	
	
	
	
	
	/**
	 * Die folgenden Zeilen wurden auskommentiert da diese nicht für das shared.bo-package relevant sind.
	 * @author Dominik Sasse 
	 */
	
	
	
	/**
	 * Auswahloptionen auslesen lassen
	 * @return auswahlOption
	 * @author Manuel Weiler
	 */
	//public Object getAuswahloption(){
	//	return this.auswahlOption;
	//}
	
	/**
	 * NOCH LEER!!!!!!
	 * @param Auswahloption
	 * @author Manuel Weiler
	 */
	//public void waehleAuswahlOption(Object Auswahloption){
		
		
	//}

}

package de.hdm.Gruppe4.Paarsheep.shared.bo;

public abstract class Eigenschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Name der Erleuterung der einzelnen Eigenschaftsfelder
	 * @author Manuel Weiler
	 */
	private String erleuterung = "";

	/**
	 * Erleuterung für das jeweilige Eigenschaftsfeld auslesen 
	 * @return erleuterung
	 * @author Manuel Weiler
	 */
	public String getErleuterung() {
		return this.erleuterung;

	}
	
	/**
	 * Erstellung der Eigenschaft
	 * 
	 * @author Dominik Sasse
	 * 
	 */
	public void setErlaeuterung(String erlaeuterung){
		this.erleuterung = erlaeuterung;
	}
}

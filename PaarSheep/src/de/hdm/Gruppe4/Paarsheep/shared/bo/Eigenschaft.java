package de.hdm.Gruppe4.Paarsheep.shared.bo;

public abstract class Eigenschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Name der Erleuterung der einzelnen Eigenschaftsfelder
	 * @author Manuel Weiler
	 */
	private String erleuterung = "";

	/**
	 * Erleuterung f�r das jeweilige Eigenschaftsfeld auslesen 
	 * @return erleuterung
	 * @author Manuel Weiler
	 */
	public String getErleuterung() {
		return this.erleuterung;
	}
}

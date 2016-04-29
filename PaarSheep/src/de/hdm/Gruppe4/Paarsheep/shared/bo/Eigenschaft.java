package de.hdm.Gruppe4.Paarsheep.shared.bo;

public abstract class Eigenschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Name der Erleuterung der einzelnen Eigenschaftsfelder
	 * @author Manuel Weiler
	 */
	private String erleuterung = "";

	/**
	 * Anlegen des Objects Eigenschaft
	 * @author Manuel Weiler
	 */
	private Object eigenschaft;

	/**
	 * Anzeigen des Eigenschaftsfeldes
	 * @return eigenschaft
	 * @author Manuel Weiler
	 */
	public Object eigenschaftAnzeigen() {
		return eigenschaft;
	}

	/**
	 * Anzeigen der Erleuterung für das jeweilige Eigenschaftsfeld
	 * @return erleuterung
	 * @author Manuel Weiler
	 */
	public String erleuterungAnzeigen() {
		return this.erleuterung;
	}
}

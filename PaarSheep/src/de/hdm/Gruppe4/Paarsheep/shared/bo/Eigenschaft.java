package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * Die Klasse Eigenschaft erweiter die BusinessObject Klassen.
 * @author andang
 *
 */
public abstract class Eigenschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private String erlaeuterung = "";

	/**
	 * Die jeweilige Erlaeuterung wird zurueckgegeben
	 * @return erlaeuterung
	 */
	public String getErlaeuterung() {
		return erlaeuterung;
	}


	/**
	 * Eine Erlaeuterung wird gesetzt
	 * @param erlaeuterung
	 */
	public void setErlaeuterung(String erlaeuterung) {
		this.erlaeuterung = erlaeuterung;
	}


}


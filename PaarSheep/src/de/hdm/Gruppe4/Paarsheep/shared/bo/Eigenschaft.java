package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * @author andang
 *
 */
public abstract class Eigenschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;

	
	private String erlaeuterung = "";



	/**
	 * @return erlaeuterung
	 */
	public String getErlaeuterung() {
		return erlaeuterung;
	}


	/**
	 * @param erlaeuterung
	 */
	public void setErlaeuterung(String erlaeuterung) {
		this.erlaeuterung = erlaeuterung;
	}


}


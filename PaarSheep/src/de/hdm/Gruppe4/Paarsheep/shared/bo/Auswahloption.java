package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * @author andang
 *
 */
public class Auswahloption extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private int optionsID;
	private String optionsBezeichnung;
	
	
	/**
	 * @return optionsID
	 */
	public int getOptionsID() {
		return optionsID;
	}

	/**
	 * @param optionsID
	 */
	public void setOptionsid(int optionsID) {
		this.optionsID = optionsID;
	}

	/**
	 * @return optionsBezeichnung
	 */
	public String getOptionsBezeichnung() {
		return optionsBezeichnung;
	}

	/**
	 * @param optionsBezeichnung
	 */
	public void setOptionsBezeichnung(String optionsBezeichnung) {
		this.optionsBezeichnung = optionsBezeichnung;
	}

}

package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Auswahloption extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private int optionsid;
	private String optionsBezeichnung;
	
	
	public int getOptionsid() {
		return optionsid;
	}

	public void setOptionsid(int optionsid) {
		this.optionsid = optionsid;
	}

	
	
	
	public String getOptionsBezeichnung() {
		return optionsBezeichnung;
	}

	public void setOptionsBezeichnung(String optionsBezeichnung) {
		this.optionsBezeichnung = optionsBezeichnung;
	}

}

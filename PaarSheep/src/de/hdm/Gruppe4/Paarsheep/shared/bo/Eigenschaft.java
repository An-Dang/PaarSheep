package de.hdm.Gruppe4.Paarsheep.shared.bo;

public abstract class Eigenschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;

	
	private String erlaeuterung = "";



	public String getErlaeuterung() {
		return erlaeuterung;
	}


	public void setErlaeuterung(String erlaeuterung) {
		this.erlaeuterung = erlaeuterung;
	}


}


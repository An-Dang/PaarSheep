package de.hdm.Gruppe4.Paarsheep.shared.bo;

public abstract class Eigenschaft extends BusinessObject {

	private static final long serialVersionUID = 1L;

	
	private String erleuterung = "";



	public String getErleuterung() {
		return erleuterung;
	}


	public void setErleuterung(String erleuterung) {
		this.erleuterung = erleuterung;
	}


}


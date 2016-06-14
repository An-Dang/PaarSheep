package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class Sperrliste extends BusinessObject {

	private static final long serialVersionUID = 1L;


	private int sperrenderID;
	private int gesperrterID;


	public int getSperrenderID() {
		return this.sperrenderID;
	}


	public void setSperrenderID(int ProfilID) {
		this.sperrenderID = ProfilID;
	}

	
	
	
	public int getGesperrterID() {
		return gesperrterID;
	}

	public void setGesperrterID(int ProfilID) {
		this.gesperrterID = ProfilID;
	}

}

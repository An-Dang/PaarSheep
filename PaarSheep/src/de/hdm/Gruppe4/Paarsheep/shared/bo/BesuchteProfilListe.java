package de.hdm.Gruppe4.Paarsheep.shared.bo;

public class BesuchteProfilListe extends BusinessObject {

	private static final long serialVersionUID = 1L;


	private int besuchteID;
	private int besucherID;
	


	public int getBesuchteID() {
		return besuchteID;
	}

	public void setBesuchteID(int besuchteID) {
		this.besuchteID = besuchteID;
	}

	
	
	
	public int getBesucherID() {
		return besucherID;
	}

	public void setBesucherID(int besucherID) {
		this.besucherID = besucherID;
	}
}

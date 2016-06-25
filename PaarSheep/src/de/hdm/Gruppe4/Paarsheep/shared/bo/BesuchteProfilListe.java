package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * @author andang
 *
 */
public class BesuchteProfilListe extends Profil {

	private static final long serialVersionUID = 1L;


	private int besuchteID;
	private int besucherID;
	


	/**
	 * @return besuchteID
	 */
	public int getBesuchteID() {
		return besuchteID;
	}

	/**
	 * @param besuchteID
	 */
	public void setBesuchteID(int besuchteID) {
		this.besuchteID = besuchteID;
	}
	
	/**
	 * @return besucherID
	 */
	public int getBesucherID() {
		return besucherID;
	}

	/**
	 * @param besucherID
	 */
	public void setBesucherID(int besucherID) {
		this.besucherID = besucherID;
	}
}

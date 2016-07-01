package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * Die Klasse Information erweitert die Klassen des BusinessObjects.
 * @author andang
 *
 */
public class Information extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private int profilId;
	private int eigenschaftId;
	private String information;


	/**
	 * Eine Information zu einer Eigenschaft wird zurueckgegeben.
	 * @return information Info zu Eigenschaft
	 */
	public String getInformation() {
		return information;
	}


	/**
	 * Information setzen von Eigenschaften
	 * @param information Info von Eigenschaft setzen
	 */
	public void setInformation(String information) {
		this.information = information;
	}

	/**
	 * ProfilID auslesen
	 * @return profilId ID eines Profils auslesen
	 */
	public int getProfilId() {
		return profilId;
	}

	/**
	 * Profil ID setzen
	 * @param profilId Profil ID wird gesetzt
	 */
	public void setProfilId(int profilId) {
		this.profilId = profilId;
	}

	/**
	 * ID einer Eigenschaft zurueckgeben.
	 * @return eigenschaftId ID einer Eigenschaft zurueckgeben
	 */
	public int getEigenschaftId() {
		return eigenschaftId;
	}

	/**
	 * ID einer Eigenschaft setzen
	 * @param eigenschaftId ID von Eigenschaft setzen
	 */
	public void setEigenschaftId(int eigenschaftId) {
		this.eigenschaftId = eigenschaftId;
	}
	
}

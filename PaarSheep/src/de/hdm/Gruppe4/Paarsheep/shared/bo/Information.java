package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * @author andang
 *
 */
public class Information extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private int profilId;
	private int eigenschaftId;
	private String information;


	/**
	 * @return information
	 */
	public String getInformation() {
		return information;
	}


	/**
	 * @param information
	 */
	public void setInformation(String information) {
		this.information = information;
	}


	/**
	 * @return the profilId
	 */
	public int getProfilId() {
		return profilId;
	}


	/**
	 * @param profilId the profilId to set
	 */
	public void setProfilId(int profilId) {
		this.profilId = profilId;
	}


	/**
	 * @return the eigenschaftId
	 */
	public int getEigenschaftId() {
		return eigenschaftId;
	}


	/**
	 * @param eigenschaftId the eigenschaftId to set
	 */
	public void setEigenschaftId(int eigenschaftId) {
		this.eigenschaftId = eigenschaftId;
	}
	
}

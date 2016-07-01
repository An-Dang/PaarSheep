package de.hdm.Gruppe4.Paarsheep.shared.bo;


/**
 * Die Klasse Suchprofil erweitert die Klasse Profil.
 * @author andang
 *
 */
public class Suchprofil extends Profil{
	
	private static final long serialVersionUID = 1L;


	private String suchprofilName;


	/**
	 * Der Name eines Suchprofils soll zurueckgegeben werden.
	 * @return suchprofilName Name eines Suchprofils
	 */
	public String getSuchprofilName() {
		return suchprofilName;
	}
	
	/**
	 * Name eines Suchprofils soll gesetzt werden
	 * @param suchprofilName Name eines Suchprofils
	 */
	public void setSuchprofilName(String suchprofilName) {
		this.suchprofilName = suchprofilName;
	}
	
}
package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * Eine Abstrakte Klasse Profil wird erstellt welche von der Klasse BusinessObject.java erbt.
 * Die Profil-Klasse enth�lt Grundlegende Informationen, welche f�r die Partnersuche und der damit verbundenen 
 * Aehnlichkeitsmassberechnung notwendig sind.
 * 
 * @author Dominik Sasse
 *
 */
public abstract class Profil extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	
	private int profilID = 0;
	
	private String religion = null;
	private int koerpergroesse = 0;
	private String haarfarbe = null;
	private String raucher = null;
	private String geschlecht = null;

	/**
	 * Die Religion eines Users wird gespeichert
	 * @param religion Religionszugehoerigkeit eines Users
	 */
	public void setReligion(String religion){
		this.religion = religion;
	}
	
	/**
	 * Religion von User wird zurueckgegeben
	 * @return religion Religion eines Users
	 */
	public String getReligion(){
		return this.religion;
	}
	
	/**
	 * Die Koerpergroesse eines Users wird gespeichert
	 * @param koerpergroesse Koerpergroesse eines Users
	 */
	public void setKoerpergroesse(int koerpergroesse){
		this.koerpergroesse = koerpergroesse;
	}

	/**
	 * Die Koerpergroesse eines Users wird zurueckgegeben.
	 * @return koerpergroesse Koerpergroesse eines Users
	 */
	public int getKoerpergroesse(){
		return this.koerpergroesse;
	}

	/**
	 * Haarfarbe von User speichern
	 * @param haarfarbe Haarfarbe des Users
	 */
	public void setHaarfarbe(String haarfarbe){
		this.haarfarbe = haarfarbe;
	}

	/**
	 * Haarfarbe des Users auslesen
	 * @return haarfarbe Haarfarbe des Users
	 */
	public String getHaarfarbe(){
		return this.haarfarbe;
	}

	/**
	 * Entscheidung, ob User Raucher ist oder nicht, speichern
	 * @param raucher ob User ruacht oder nicht
	 */
	public void setRaucher(String raucher){
		this.raucher = raucher;
	}
	
	/**
	 * Entscheidung, ob User Raucher ist oder nicht, abfragen
	 * @return raucher ob User raucht oder nicht
	 */
	public String getRaucher(){
		return this.raucher;
	}
	
	/**
	 * Geschlecht des Users speichern
	 * @param geschlecht Geschlecht des Users
	 */
	public void setGeschlecht(String geschlecht){
		this.geschlecht = geschlecht;
	}

	/**
	 * Geschlecht des Users auslesen
	 * @return geschlecht Geschlecht des Users
	 */
	public String getGeschlecht(){
		return this.geschlecht;
	}

	/**
	 * Profil ID eines Users setzen
	 * @param id Profil ID eines Users
	 */
	public void setProfilID (int id) {
		this.profilID = id;
	}
	
	/**
	 * Profil ID eines Users ausgeben
	 * @return profilID Profil ID eines Users
	 */
	public int getProfilID () {
		return this.profilID;
	}
}
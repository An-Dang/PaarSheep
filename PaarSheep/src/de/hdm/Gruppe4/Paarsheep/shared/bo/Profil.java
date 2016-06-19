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
	 * @param religion
	 */
	public void setReligion(String religion){
		this.religion = religion;
	}
	
	/**
	 * @return religion
	 */
	public String getReligion(){
		return this.religion;
	}
	
	/**
	 * @param koerpergroesse
	 */
	public void setKoerpergroesse(int koerpergroesse){
		this.koerpergroesse = koerpergroesse;
	}
	

	/**
	 * @return koerpergroesse
	 */
	public int getKoerpergroesse(){
		return this.koerpergroesse;
	}

	/**
	 * @param haarfarbe
	 */
	public void setHaarfarbe(String haarfarbe){
		this.haarfarbe = haarfarbe;
	}
	

	/**
	 * @return haarfarbe
	 */
	public String getHaarfarbe(){
		return this.haarfarbe;
	}

	/**
	 * @param raucher
	 */
	public void setRaucher(String raucher){
		this.raucher = raucher;
	}
	
	/**
	 * @return raucher
	 */
	public String getRaucher(){
		return this.raucher;
	}

	
	
	
	/**
	 * @param geschlecht
	 */
	public void setGeschlecht(String geschlecht){
		this.geschlecht = geschlecht;
	}

	/**
	 * @return geschlecht
	 */
	public String getGeschlecht(){
		return this.geschlecht;
	}

	
	
	/**
	 * @param id
	 */
	public void setProfilID (int id) {
		this.profilID = id;
	}
	
	/**
	 * @return profilID
	 */
	public int getProfilID () {
		return this.profilID;
	}
}











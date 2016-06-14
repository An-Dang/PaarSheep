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
	
	

	public void setReligion(String religion){
		this.religion = religion;
	}
	
	public String getReligion(){
		return this.religion;
	}
	
	

	
	public void setKoerpergroesse(int koerpergroesse){
		this.koerpergroesse = koerpergroesse;
	}
	

	public int getKoerpergroesse(){
		return this.koerpergroesse;
	}
	

	

	public void setHaarfarbe(String haarfarbe){
		this.haarfarbe = haarfarbe;
	}
	

	public String getHaarfarbe(){
		return this.haarfarbe;
	}
	
	
	

	public void setRaucher(String raucher){
		this.raucher = raucher;
	}
	
	public String getRaucher(){
		return this.raucher;
	}

	
	
	
	public void setGeschlecht(String geschlecht){
		this.geschlecht = geschlecht;
	}

	public String getGeschlecht(){
		return this.geschlecht;
	}

	
	
	public void setProfilID (int id) {
		this.profilID = id;
	}
	
	public int getProfilID () {
		return this.profilID;
	}
}











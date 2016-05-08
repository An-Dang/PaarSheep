package de.hdm.Gruppe4.Paarsheep.shared.bo;

import java.util.ArrayList;

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
	

	/**
	 * Attribut raucher vom Typ boolean wird angelegt. Entweder der Nutzer raucht (true), oder nicht (false)
	 * 
	 * @author Dominik Sasse
	 */
	private String raucher = null;
	
	/**
	 *Attribut haarfarbe vom Typ String wird angelegt.
	 *
	 * @author Dominik Sasse
	 */
	private String haarfarbe = null;
	
	/**
	 *Attribut religion vom Typ String wird angelegt.
	 *
	 * @author Dominik Sasse
	 */
	private String religion = null;
	
	/**
	 *Attribut koerpergroesse vom Typ Integer wird angelegt.
	 *
	 * @author Dominik Sasse
	 */
	private int koerpergroesse = 0;
	
	/**
	 *Attribut geschlecht vom Typ String wird angelegt.
	 *
	 * @author Dominik Sasse
	 */
	private String geschlecht = null;
	
	/**
	 *Methode um das Attribut raucher zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	
	public void setRaucher(String raucher){
		this.raucher = raucher;
	}
	
	/**
	 *Methode um das Attribut raucher abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public String getRaucher(){
		return this.raucher;
	}
	
	/**
	 *Methode um das Attribut haarfarbe zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setHaarfarbe(String haarfarbe){
		this.haarfarbe = haarfarbe;
	}
	
	/**
	 *Methode um das Attribut haarfarbe abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public String getHaarfarbe(){
		return this.haarfarbe;
	}
	
	/**
	 *Methode um das Attribut religion zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setReligion(String religion){
		this.religion = religion;
	}
	
	/**
	 *Methode um das Attribut religion abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public String getReligion(){
		return this.religion;
	}
	
	/**
	 *Methode um das Attribut koerpergroesse zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setKoerpergroesse(int koerpergroesse){
		this.koerpergroesse = koerpergroesse;
	}
	
	/**
	 *Methode um das Attribut koerpergroesse abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public int getKoerpergroesse(){
		return this.koerpergroesse;
	}
	
	/**
	 *Methode um das Attribut geschlecht zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setGeschlecht(String geschlecht){
		this.geschlecht = geschlecht;
	}
	
	/**
	 *Methode um das Attribut geschlecht abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public String getGeschlecht(){
		return this.geschlecht;
	}
}











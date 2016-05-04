package de.hdm.Gruppe4.Paarsheep.shared.bo;


public class Suchprofil extends Profil {
	
	private static final long serialVersionUID = 1L;

	/**
	 *Attribut altervon vom Typ Integer wird angelegt und den Wert 0 zugewiesen.
	 *
	 * @author Dominik Sasse
	 */
	private int altervon = 0;
	
	/**
	 *Attribut alterbis vom Typ Integer wird angelegt und den Wert 0 zugewiesen.
	 *
	 * @author Dominik Sasse
	 */
	private int alterbis = 0;
	
	/**
	 *Methode um das Attribut altervon zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setAltervon(int altervon){
		this.altervon = altervon;
	}
	
	/**
	 *Methode um das Attribut altervon abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public int getAltervon(){
		return this.altervon;
	}
	
	/**
	 *Methode um das Attribut alterbis zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setAlterbis(int alterbis){
		this.alterbis = alterbis;
	}
	
	/**
	 *Methode um das Attribut alterbis abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public int getAlterbis(){
		return this.alterbis;
	}

	
}

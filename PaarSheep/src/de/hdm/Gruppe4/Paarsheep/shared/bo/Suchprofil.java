package de.hdm.Gruppe4.Paarsheep.shared.bo;


public class Suchprofil extends Profil {
	
	private static final long serialVersionUID = 1L;

	private int suchprofilNutzerprofilID = 0;
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
	 *Attribut koerpergroessevon vom Typ Integer wird angelegt und den Wert 0 zugewiesen.
	 *
	 * @author Dominik Sasse
	 */
	private int koerpergroessevon = 0;
	
	/**
	 *Attribut koerpergroessebis vom Typ Integer wird angelegt und den Wert 0 zugewiesen.
	 *
	 * @author Dominik Sasse
	 */
	private int koerpergroessebis = 0;
		
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
	
	/**
	 *Methode um das Attribut koerpergroessevon zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setKoerpergroessevon(int koerpergroessevon){
		this.koerpergroessevon = koerpergroessevon;
	}
	
	/**
	 *Methode um das Attribut koerpergroessevon abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public int getKoerpergroessevon(){
		return this.koerpergroessevon;
	}
	
	/**
	 *Methode um das Attribut alterbis zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setKoerpergroessebis(int koerpergroessebis){
		this.koerpergroessebis = koerpergroessebis;
	}
	
	/**
	 *Methode um das Attribut koerpergroessebis abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public int getKoerpergroessebis(){
		return this.koerpergroessebis;
	}

	
	public int getSuchprofilNutzerprofilID() {
		return this.suchprofilNutzerprofilID;
	}

	public void setSuchprofilNutzerprofilID(int suchprofilNutzerprofilID) {
		this.suchprofilNutzerprofilID = suchprofilNutzerprofilID;
	}

	
}

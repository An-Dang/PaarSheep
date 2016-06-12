package de.hdm.Gruppe4.Paarsheep.shared.bo;


public class Suchprofil extends Profil {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Erstellung der Attribute von Suchprofil
	 * @author Dominik Sasse
	 */
	
	private String suchprofilname;
	
	private int altervon = 0;
	
	private int alterbis = 0;

	private int koerpergroessevon = 0;

	private int koerpergroessebis = 0;
	
	private int suchprofil_nutzerprofilID = 0;
	/**
	 *Erstellung s�mtlicher getter- und setter-Methoden.
	 *
	 * @author Dominik Sasse
	 */
	public void setSuchprofil_nutzerprofilID(int ID){
		this.suchprofil_nutzerprofilID = ID;
	}
	
	public int getSuchprofil_nutzerprofilID() {
		return this.suchprofil_nutzerprofilID;
	}
	
	public void setAltervon(int altervon){
		this.altervon = altervon;
	}

	public int getAltervon(){
		return this.altervon;
	}

	public void setAlterbis(int alterbis){
		this.alterbis = alterbis;
	}

	public int getAlterbis(){
		return this.alterbis;
	}

	public void setKoerpergroessevon(int koerpergroessevon){
		this.koerpergroessevon = koerpergroessevon;
	}

	public int getKoerpergroessevon(){
		return this.koerpergroessevon;
	}

	public void setKoerpergroessebis(int koerpergroessebis){
		this.koerpergroessebis = koerpergroessebis;
	}

	public int getKoerpergroessebis(){
		return this.koerpergroessebis;
	}
	
	public void setSuchprofilname(String suchprofilname){
		this.suchprofilname = suchprofilname;
	}

	public String getSuchprofilname(){
		return this.suchprofilname;
	}

	
}

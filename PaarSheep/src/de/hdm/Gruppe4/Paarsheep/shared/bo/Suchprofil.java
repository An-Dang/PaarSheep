package de.hdm.Gruppe4.Paarsheep.shared.bo;


public class Suchprofil extends BusinessObject{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Erstellung der Attribute von Suchprofil
	 * @author Dominik Sasse
	 */
	private int suchprofilID; 
	
	private String suchprofilname;
	
	private int altervon = 0;
	
	private int alterbis = 0;

	private int koerpergroessevon = 0;

	private int koerpergroessebis = 0;
	
	private int nutzerprofilID = 0;
	
	private String religion;
	
	private String geschlecht;
	
	private String haarfarbe;
	
	private String raucher;
	
	/**
	 *Erstellung s�mtlicher getter- und setter-Methoden.
	 *
	 * @author Dominik Sasse
	 */
	
	
	public void setNutzerprofilID(int ID){
		this.nutzerprofilID = ID;
	}
	
	public int getSuchprofilID() {
		return suchprofilID;
	}

	public void setSuchprofilID(int suchprofilID) {
		this.suchprofilID = suchprofilID;
	}

	public int getNutzerprofilID() {
		return this.nutzerprofilID;
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
	
	public void setReligion(String religion){
		this.religion = religion;
	}
	
	public String getReligion(){
		return this.religion;
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

	public void setHaarfarbe(String haarfarbe){
		this.haarfarbe = haarfarbe;
	}
	
	public String getHaarfarbe(){
		return this.haarfarbe;
	}
}

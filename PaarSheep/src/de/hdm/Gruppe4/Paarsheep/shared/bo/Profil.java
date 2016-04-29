package de.hdm.Gruppe4.Paarsheep.shared.bo;

public abstract class Profil extends BusinessObject{
	
	private boolean raucher;
	private String haarfarbe;
	private String religion;
	private int korpergroesse = 0;
	
	public void setRaucher(boolean raucher){
		this.raucher = raucher;
	}
	public boolean getRaucher(){
		return true;
	}
	
	public void setHaarfarbe(String haarfarbe){
		this.haarfarbe = haarfarbe;
	}
	public String getHaarfarbe(){
		return this.haarfarbe;
	}
	
	public void setReligion(String religion){
		this.religion = religion;
	}
	public String getReligion(){
		return this.religion;
	}
	
	public void setKoerpergroesse(int koerpergroesse){
		this.korpergroesse = koerpergroesse;
	}
	public int getKoerpergroesse(){
		return this.korpergroesse;
	}
}







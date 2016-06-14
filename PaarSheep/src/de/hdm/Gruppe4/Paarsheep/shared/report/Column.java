package de.hdm.Gruppe4.Paarsheep.shared.report;

import java.io.Serializable;

public class Column implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String wert = "";
	
	public Column(){
		
	}
	
	public Column(String w){
		this.wert = w;
	}
	
	public String getWert(){
		return wert;
	}
	
	public void setWert(String wert){
		this.wert = wert;
	}
	
	public String toString(){
		return this.wert;
	}
}

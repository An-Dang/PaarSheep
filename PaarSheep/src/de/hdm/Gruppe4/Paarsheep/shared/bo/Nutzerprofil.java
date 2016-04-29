package de.hdm.Gruppe4.Paarsheep.shared.bo;

import java.sql.Date;

public class Nutzerprofil extends Profil {

	private static final long serialVersionUID = 1L;
	
	/**
	 *Attribut vorname vom Typ String wird angelegt.
	 *
	 * @author Dominik Sasse
	 */
	private String vorname;
	
	/**
	 *Attribut nachname vom Typ String wird angelegt.
	 *
	 * @author Dominik Sasse
	 */
	private String nachname;
	
	/**
	 *Attribut Geburtsdatum vom Typ Date wird angelegt.
	 *
	 * @author Dominik Sasse
	 */
	private Date geburtsdatum;
	
	/**
	 *Methode um das Attribut vorname abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	/**
	 *Methode um das Attribut vorname abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public String getVorname(){
		return this.vorname;
	}
	
	/**
	 *Methode um das Attribut nachname zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setNachname(String nachname){
		this.nachname = nachname;
	}
	
	/**
	 *Methode um das Attribut nachname abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public String getNachname(){
		return this.nachname;
	}
	
	/**
	 *Methode um das Attribut geburtsdatum zu setzen.
	 *
	 * @author Dominik Sasse
	 */
	public void setGeburtsdatum(Date geburtsdatum){
		this.geburtsdatum = geburtsdatum;
	}
	
	/**
	 *Methode um das Attribut geburtsdatum abzurufen.
	 *
	 * @author Dominik Sasse
	 */
	public Date getGeburtsdatum(){
		return this.geburtsdatum;
	}
	

}




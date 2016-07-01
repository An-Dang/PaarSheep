package de.hdm.Gruppe4.Paarsheep.shared.bo;

import java.util.Date;

/**
 * Die Klasse Nutzerprofil erweitert die Klasse Profil.
 * @author andang
 *
 */
public class Nutzerprofil extends Profil {

	private static final long serialVersionUID = 1L;
	
    private boolean loggedIn = false;
	private String loginUrl;
    private String logoutUrl;
	private String emailAddress;
	private String nickname;
	private boolean status = false;
	
	private int aehnlichkeit;
	private int aehnlichkeitSP;
	
	private Date geburtsdatum;
	private String vorname;
	private String nachname;


	/**
	 * Das Geburtsdatum eines Users wird gesetzt.
	 * @param geburtsdatum GB Datum eines Users
	 */
	public void setGeburtsdatum(Date geburtsdatum){
		this.geburtsdatum = geburtsdatum;
	}
	

	/**
	 * Geburtsdatum eines Users wird geholt
	 * @return geburtsdatum GB-Datum ausgeben
	 */
	public Date getGeburtsdatum(){
		return this.geburtsdatum;
	}
	
	
	/**
	 * Der Vorname eines Users wird gesetzt
	 * @param vorname Vorname d. Users wird gespeichert
	 */
	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	/**
	 * Der Vorname wird ausgelesen.
	 * @return vorname Vorname des Users
	 */
	public String getVorname(){
		return this.vorname;
	}
	
	/**
	 * Nachname des Users speichern
	 * @param nachname Nachname des Users
	 */
	public void setNachname(String nachname){
		this.nachname = nachname;
	}
	

	/**
	 * Nachname des Users holen.
	 * @return nachname Nachname des Users auslesen
	 */
	public String getNachname(){
		return this.nachname;
	}
	
	
	/**
	 * Pruefung ob User eingeloggt ist
	 * @return loggedIn Status von Login holen
	 */
	public boolean isLoggedIn() {
	    return loggedIn;
	}

	  /**
	   * Status des Logins setzen
	 * @param loggedIn Status wird gesetzt
	 */
	public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	}

	  /**
	   * Login URL auslegen
	 * @return loginUrl Login URL fuer Anmeldung am System
	 */
	public String getLoginUrl() {
	    return loginUrl;
	}

	  /**
	   * Login URL festlegen fuer Anmeldung am System
	 * @param loginUrl Login URL fuer Anmeldung
	 */
	public void setLoginUrl(String loginUrl) {
	    this.loginUrl = loginUrl;
	}

	  /**
	   * URL fuer Logout auslesen
	 * @return logoutUrl URL fuer Logout
	 */
	public String getLogoutUrl() {
	    return logoutUrl;
	}

	  /**
	   * Logout URL festlegen 
	 * @param logoutUrl URL fuer Logout
	 */
	public void setLogoutUrl(String logoutUrl) {
	    this.logoutUrl = logoutUrl;
	}

	  /**
	   * Email Adresse auslesen
	 * @return emailAddress Email eines Users
	 */
	public String getEmailAddress() {
	    return emailAddress;
	}

	  /**
	   * Email Adresse speichern
	 * @param emailAddress Email eines Users
	 */
	public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	}

	  /**
	   * Der Nickname eines Users wird ausgelesen
	 * @return nickname Nickname eines Users
	 */
	public String getNickname() {
	    return nickname;
	}

	  /**
	   * Nickname speichern
	 * @param nickname Nickname eines Users
	 */
	public void setNickname(String nickname) {
	    this.nickname = nickname;
	}
	  
	  /**
	   * Status wird gesetzt
	 * @param status
	 */
	public void setStatus(boolean status) {
		  this.status = status;
	}
	  
	  /**
	   * Status auslesen
	 * @return status
	 */
	public boolean getStatus() {
		  return status;
	  }
	  
	  /**
	   * Aehnlichkeit wird zurueckgegeben
	 * @return aehnlichkeit zwischen 2 Profilen
	 */
	public int getAehnlichkeit(){
		  return aehnlichkeit;
	  }
	  
	  /**
	   * Aehnlichkeit zwischen 2 Profilen setzen
	 * @param aehnlichkeit zwischen 2 Profilen
	 */
	public void setAehnlichkeit(int aehnlichkeit){
		  this.aehnlichkeit = aehnlichkeit;
	  }
	  
	  /**
	   * Aehnlichkeit zwischen Suchprofil und Profil ausgeben
	 * @return aehnlichkeitSP Aehnlichkeit zwischen Profilen und Suchprofil
	 */
	public int getAehnlichkeitSP(){
		  return aehnlichkeitSP;
	  }
	  
	  /**
	   * Aehnlichkeit zwischen Suchprofil und Profilen
	 * @param aehnlichkeitSP Aehnlichkeit zwischen Profilen und Suchprofilen
	 */
	public void setAehnlichkeitSP(int aehnlichkeitSP){
		  this.aehnlichkeitSP = aehnlichkeitSP;
		  }

	/**
	 * 
	 */
	public boolean equals(Object o) {

	    if (o != null && o instanceof Nutzerprofil) {
	      Nutzerprofil n = (Nutzerprofil) o;
	      try {
	        return super.equals(n);
	      }
	      catch (IllegalArgumentException e) {
	        return false;
	      }
	    }
	    return false;
	  }


	
	}	






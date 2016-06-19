package de.hdm.Gruppe4.Paarsheep.shared.bo;

import java.util.Date;

/**
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
	 * @param geburtsdatum
	 */
	public void setGeburtsdatum(Date geburtsdatum){
		this.geburtsdatum = geburtsdatum;
	}
	

	/**
	 * @return geburtsdatum
	 */
	public Date getGeburtsdatum(){
		return this.geburtsdatum;
	}
	
	
	/**
	 * @param vorname
	 */
	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	/**
	 * @return vorname
	 */
	public String getVorname(){
		return this.vorname;
	}
	
	

	/**
	 * @param nachname
	 */
	public void setNachname(String nachname){
		this.nachname = nachname;
	}
	

	/**
	 * @return nachname
	 */
	public String getNachname(){
		return this.nachname;
	}
	
	
	
	/**
	 * @return loggedIn
	 */
	public boolean isLoggedIn() {
	    return loggedIn;
	}

	  /**
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	}

	  /**
	 * @return loginUrl
	 */
	public String getLoginUrl() {
	    return loginUrl;
	}

	  /**
	 * @param loginUrl
	 */
	public void setLoginUrl(String loginUrl) {
	    this.loginUrl = loginUrl;
	}

	  /**
	 * @return logoutUrl
	 */
	public String getLogoutUrl() {
	    return logoutUrl;
	}

	  /**
	 * @param logoutUrl
	 */
	public void setLogoutUrl(String logoutUrl) {
	    this.logoutUrl = logoutUrl;
	}

	  /**
	 * @return emailAddress
	 */
	public String getEmailAddress() {
	    return emailAddress;
	}

	  /**
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	}

	  /**
	 * @return nickname
	 */
	public String getNickname() {
	    return nickname;
	}

	  /**
	 * @param nickname
	 */
	public void setNickname(String nickname) {
	    this.nickname = nickname;
	}
	  
	  /**
	 * @param status
	 */
	public void setStatus(boolean status) {
		  this.status = status;
	}
	  
	  /**
	 * @return status
	 */
	public boolean getStatus() {
		  return status;
	  }
	  
	  /**
	 * @return aehnlichkeit
	 */
	public int getAehnlichkeit(){
		  return aehnlichkeit;
	  }
	  
	  /**
	 * @param aehnlichkeit
	 */
	public void setAehnlichkeit(int aehnlichkeit){
		  this.aehnlichkeit = aehnlichkeit;
	  }
	  
	  /**
	 * @return aehnlichkeitSP
	 */
	public int getAehnlichkeitSP(){
		  return aehnlichkeitSP;
	  }
	  
	  /**
	 * @param aehnlichkeitSP
	 */
	public void setAehnlichkeitSP(int aehnlichkeitSP){
		  this.aehnlichkeitSP = aehnlichkeitSP;
		  }

	
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






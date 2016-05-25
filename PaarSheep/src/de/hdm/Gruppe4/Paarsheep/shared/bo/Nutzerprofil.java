package de.hdm.Gruppe4.Paarsheep.shared.bo;

import java.util.Date;

public class Nutzerprofil extends Profil {
	
	  private boolean loggedIn = false;
	  private String loginUrl;
	  private String logoutUrl;
	  private String emailAddress;
	  private String nickname;
	  
	  private boolean status = false;

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
	
	// Attribute von der Abstract Klasse Profil werden hier benutzt
	
	/**
	 * Attribut raucher vom Typ boolean wird angelegt. Entweder der Nutzer raucht (true), oder nicht (false)
	 * 
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	private String raucher;
	
	/**
	 *Attribut haarfarbe vom Typ String wird angelegt.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	private String haarfarbe = "";
	
	/**
	 *Attribut religion vom Typ String wird angelegt.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	private String religion = null;
	
	/**
	 *Attribut koerpergroesse vom Typ Integer wird angelegt.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	private int koerpergroesse = 0;
	
	/**
	 *Attribut geschlecht vom Typ String wird angelegt.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	private String geschlecht = null;
	
	/**
	 *Methode um das Attribut raucher zu setzen.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	
	public void setRaucher(String raucher){
		this.raucher = raucher;
	}
	
	/**
	 *Methode um das Attribut raucher abzurufen.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public String getRaucher(){
		return raucher;
	}
	
	/**
	 *Methode um das Attribut haarfarbe zu setzen.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public void setHaarfarbe(String haarfarbe){
		this.haarfarbe = haarfarbe;
	}
	
	/**
	 *Methode um das Attribut haarfarbe abzurufen.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public String getHaarfarbe(){
		return this.haarfarbe;
	}
	
	/**
	 *Methode um das Attribut religion zu setzen.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public void setReligion(String religion){
		this.religion = religion;
	}
	
	/**
	 *Methode um das Attribut religion abzurufen.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public String getReligion(){
		return this.religion;
	}
	
	/**
	 *Methode um das Attribut koerpergroesse zu setzen.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public void setKoerpergroesse(int koerpergroesse){
		this.koerpergroesse = koerpergroesse;
	}
	
	/**
	 *Methode um das Attribut koerpergroesse abzurufen.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public int getKoerpergroesse(){
		return this.koerpergroesse;
	}
	
	/**
	 *Methode um das Attribut geschlecht zu setzen.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public void setGeschlecht(String geschlecht){
		this.geschlecht = geschlecht;
	}
	
	/**
	 *Methode um das Attribut geschlecht abzurufen.
	 *
	 * @author An Dang
	 * @author Dominik Sasse
	 */
	public String getGeschlecht(){
		return this.geschlecht;
	}
	
	public boolean isLoggedIn() {
	    return loggedIn;
	  }

	  public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	  }

	  public String getLoginUrl() {
	    return loginUrl;
	  }

	  public void setLoginUrl(String loginUrl) {
	    this.loginUrl = loginUrl;
	  }

	  public String getLogoutUrl() {
	    return logoutUrl;
	  }

	  public void setLogoutUrl(String logoutUrl) {
	    this.logoutUrl = logoutUrl;
	  }

	  public String getEmailAddress() {
	    return emailAddress;
	  }

	  public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	  }

	  public String getNickname() {
	    return nickname;
	  }

	  public void setNickname(String nickname) {
	    this.nickname = nickname;
	  }
	  
	  public void setStatus(boolean status) {
		  this.status = status;
	  }
	  
	  public boolean getStatus() {
		  return status;
	  }
	


	  @Override
	public boolean equals(Object o) {
	    /*
	     * Abfragen, ob ein Objekt ungl. NULL ist und ob ein Objekt gecastet werden
	     * kann, sind immer wichtig!
	     */
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




package de.hdm.Gruppe4.Paarsheep.shared.bo;

import java.util.Date;

public class Nutzerprofil extends BusinessObject {

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
	private String religion = null;	
	private int koerpergroesse = 0;
	private String haarfarbe = null;
	private String raucher = null;
	private String geschlecht = null;

	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	

	public String getVorname(){
		return this.vorname;
	}
	

	public void setNachname(String nachname){
		this.nachname = nachname;
	}
	

	public String getNachname(){
		return this.nachname;
	}
	

	public void setGeburtsdatum(Date geburtsdatum){
		this.geburtsdatum = geburtsdatum;
	}
	

	public Date getGeburtsdatum(){
		return this.geburtsdatum;
	}
	
	
	public void setRaucher(String raucher){
		this.raucher = raucher;
	}
	

	public String getRaucher(){
		return raucher;
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
		this.koerpergroesse = koerpergroesse;
	}
	
	
	public int getKoerpergroesse(){
		return this.koerpergroesse;
	}
	

	public void setGeschlecht(String geschlecht){
		this.geschlecht = geschlecht;
	}
	
	
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
	  
	  public int getAehnlichkeit(){
		  return aehnlichkeit;
	  }
	  
	  public void setAehnlichkeit(int aehnlichkeit){
		  this.aehnlichkeit = aehnlichkeit;
	  }
	  
	  public int getAehnlichkeitSP(){
		  return aehnlichkeitSP;
	  }
	  
	  public void setAehnlichkeitSP(int aehnlichkeitSP){
		  this.aehnlichkeitSP = aehnlichkeitSP;
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






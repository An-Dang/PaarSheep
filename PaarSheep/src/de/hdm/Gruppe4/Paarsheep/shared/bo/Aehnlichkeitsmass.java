package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * Die Klasse Aehnlichkeitsmass erweitert die Klasse BusinessObject.
 * 
 * @author andang
 *
 */
public class Aehnlichkeitsmass extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 *Attribut aehnlichkeitsmass vom Typ int wird angelegt.
	 *
	 */
	private int aehnlichkeitsmass = 0;
	
	private Nutzerprofil fremdprofil = null;
	
	private Suchprofil suchprofil = null;
	
	/**
	 *Methode um das Attribut Aehnlichkeitsmass zu setzen.
	 *
	 * @param aehnlichkeitsmass berechnet die Aehnlichkeit zwischen 2 Profilen
	 */
	public void setAehnlichkeitsmass(int aehnlichkeitsmass){
		this.aehnlichkeitsmass = aehnlichkeitsmass;
	}
	
	/**
	 *Methode um das Attribut aehnlichkeitsmass auszugeben.
	 *
	 * @return aehnlichkeitsmass liefert den Grad der Aehnlichkeit zweier Profile
	 * 				zurueck
	 */
	public int getAehnlichkeitsmass(){
		return this.aehnlichkeitsmass;
	}

	/**
	 * Das Nutzerprofil eines anderen Users zurueckgeben.
	 * @return fremdprofil Profil eines anderen Users
	 */
	public Nutzerprofil getFremdprofil() {
		return fremdprofil;
	}

	/**
	 * Nutzerprofil eines anderen Users wird gesetzt
	 * @param fremdprofil Profil eines anderen Users setzen
	 */
	public void setFremdprofil(Nutzerprofil fremdprofil) {
		this.fremdprofil = fremdprofil;
	}

	/**
	 * Suchprofil wird zurueckgegeben
	 * @return suchprofil Das gewuenschte Suchprofil wird zurueckgegeben.
	 */
	public Suchprofil getSuchprofil() {
		return suchprofil;
	}

	/**
	 * Suchprofil abspeichern
	 * @param suchprofil Das gewuenschte Suchprofil wird gesetzt
	 */
	public void setSuchprofil(Suchprofil suchprofil) {
		this.suchprofil = suchprofil;
	}
}

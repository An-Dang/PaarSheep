package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * @author andang
 *
 */
public class Aehnlichkeitsmass extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 *Attribut aehnlichkeitsmass vom Typ double wird angelegt.
	 *
	 */
	private int aehnlichkeitsmass = 0;
	
	private Nutzerprofil fremdprofil = null;
	
	private Suchprofil suchprofil = null;
	
	/**
	 *Methode um das Attribut aehnlichkeitsmass zu setzen.
	 *
	 * @param aehnlichkeitsmass 
	 */
	public void setAehnlichkeitsmass(int aehnlichkeitsmass){
		this.aehnlichkeitsmass = aehnlichkeitsmass;
	}
	
	/**
	 *Methode um das Attribut aehnlichkeitsmass auszugeben.
	 *
	 * @return aehnlichkeitsmass
	 */
	public int getAehnlichkeitsmass(){
		return this.aehnlichkeitsmass;
	}

	public Nutzerprofil getFremdprofil() {
		return fremdprofil;
	}

	public void setFremdprofil(Nutzerprofil fremdprofil) {
		this.fremdprofil = fremdprofil;
	}

	public Suchprofil getSuchprofil() {
		return suchprofil;
	}

	public void setSuchprofil(Suchprofil suchprofil) {
		this.suchprofil = suchprofil;
	}

	
}

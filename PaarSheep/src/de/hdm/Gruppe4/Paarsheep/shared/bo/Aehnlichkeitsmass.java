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
	private double aehnlichkeitsmass = 0.0;
	
	/**
	 *Methode um das Attribut aehnlichkeitsmass zu setzen.
	 *
	 * @param aehnlichkeitsmass 
	 */
	public void setAehnlichkeitsmass(double aehnlichkeitsmass){
		this.aehnlichkeitsmass = aehnlichkeitsmass;
	}
	
	/**
	 *Methode um das Attribut aehnlichkeitsmass auszugeben.
	 *
	 * @return aehnlichkeitsmass
	 */
	public double getAehnlichkeitsmass(){
		return this.aehnlichkeitsmass;
	}

	
}

package de.hdm.Gruppe4.Paarsheep.shared.bo;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Die Klasse Benutzer erweitert die Klasse Profil.
 * @author andang
 *
 */
public class Benutzer extends Profil implements IsSerializable, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static int profilId = 1; 

	/**
	 * Die ID eines Profils wird zurueckgegeben.
	 * @return profilId ID eines Profils
	 */
	public static int getProfilId() {
		return profilId;
	}

	/**
	 * Die ID eines Profils wird gesetzt
	 * @param profilId ProfilID setzen
	 */
	public void setProfilId(int profilId) {
		Benutzer.profilId = profilId;
	}
	

}
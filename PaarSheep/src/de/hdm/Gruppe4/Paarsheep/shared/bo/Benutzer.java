package de.hdm.Gruppe4.Paarsheep.shared.bo;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author andang
 *
 */
public class Benutzer extends Profil implements IsSerializable, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static int profilId = 1; 

	/**
	 * @return profilId
	 */
	public static int getProfilId() {
		return profilId;
	}

	/**
	 * @param profilId
	 */
	public void setProfilId(int profilId) {
		Benutzer.profilId = profilId;
	}
	

}
package de.hdm.Gruppe4.Paarsheep.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Aehnlichkeitsmass;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Profil;
import de.hdm.Gruppe4.Paarsheep.shared.report.ReportByAllProfile;
import de.hdm.Gruppe4.Paarsheep.shared.report.ReportByProfil;

/**
 * Synchrone Schnittstelle f�r eine RPC-f�hige Klasse zur Erstellung von
 * Reports
 * @author Manuel Weiler
 *
 */
public interface ReportGenerator extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von
	 * GWT RPC zus�tzlich zum No Argument Constructor der implementierenden
	 * Klasse BankAdministrationImpltungImpl} notwendig. Bitte diese Methode
	 * direkt nach der Instantiierung aufrufen.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;
	
	/**
	 * Setzen des zugeordneten Profil
	 * 
	 * @param profil
	 * @throws IllegalArgumentException
	 */
	public void setNutzerprofil(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	
	/**
	 * Erstellen eines Reports f�r ein einzelnes Profil
	 * @param p
	 * @return fertiger Report
	 * @throws IllegalArgumentException
	 */
	public abstract ReportByProfil createReportByProfil(Nutzerprofil p, Aehnlichkeitsmass a) 
			throws IllegalArgumentException;
	
	/**
	 * Erstellen eines Reports f�r alle Profile
	 * @return fertiger Report
	 * @throws IllegalArgumentException
	 */
	public abstract ReportByAllProfile createReportByAllProfile() 
			throws IllegalArgumentException;
	
	
}

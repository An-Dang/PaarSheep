package de.hdm.Gruppe4.Paarsheep.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Aehnlichkeitsmass;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Profil;
import de.hdm.Gruppe4.Paarsheep.shared.report.ReportByAllProfile;
import de.hdm.Gruppe4.Paarsheep.shared.report.ReportByProfil;

/**
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Erstellung von
 * Reports
 * @author Manuel Weiler
 *
 */
public interface ReportGenerator extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von
	 * GWT RPC zusätzlich zum No Argument Constructor der implementierenden
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
	public void setProfil(Profil profil) throws IllegalArgumentException;
	
	/**
	 * Erstellen eines Reports für ein einzelnes Profil
	 * @param p
	 * @return fertiger Report
	 * @throws IllegalArgumentException
	 */
	public abstract ReportByProfil createReportByProfil(Profil p, Aehnlichkeitsmass a) 
			throws IllegalArgumentException;
	
	/**
	 * Erstellen eines Reports für alle Profile
	 * @return fertiger Report
	 * @throws IllegalArgumentException
	 */
	public abstract ReportByAllProfile createReportByAllProfile() 
			throws IllegalArgumentException;
	
	
}

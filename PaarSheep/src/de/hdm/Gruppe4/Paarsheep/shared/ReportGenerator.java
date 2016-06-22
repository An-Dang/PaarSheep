package de.hdm.Gruppe4.Paarsheep.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.report.InfoObjekteByNutzerReport;
import de.hdm.Gruppe4.Paarsheep.shared.report.PartnervorschleageBySuchprofilReport;
import de.hdm.Gruppe4.Paarsheep.shared.report.PartnervorschleageByUngesehenenNutzerprofilenReport;
import de.hdm.Gruppe4.Paarsheep.shared.report.ProfilInfoByNutzerprofilReport;


/**
 * Synchrone Schnittstelle f�r eine RPC-f�hige Klasse zur Erstellung von
 * Reports
 * @author Manuel Weiler
 *
 */
@RemoteServiceRelativePath("reportgenerator")
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
	
	
	
	ProfilInfoByNutzerprofilReport createProfilInfoByNutzerprofilReport(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	
	InfoObjekteByNutzerReport createInfoObjekteByNutzerReport(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	
	
	PartnervorschleageByUngesehenenNutzerprofilenReport createPartnervorschleageByUngesehenenNutzerprofilenReport(Nutzerprofil nutzerprofil)
			throws IllegalArgumentException;
	
	PartnervorschleageBySuchprofilReport createPartnervorschleageBySuchprofilReport(Nutzerprofil nutzerprofil, String suchprofilname)
			throws IllegalArgumentException;



	/**
	 * @param loginInfo
	 * @return Nutzerprofil
	 * @throws IllegalArgumentException
	 */
	public Nutzerprofil checkStatus(Nutzerprofil loginInfo) throws IllegalArgumentException;
	
}

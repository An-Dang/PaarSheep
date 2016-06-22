package de.hdm.Gruppe4.Paarsheep.shared;

import java.util.List;

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
	 * Methode, die einen fertigen Report vom Typ InfoObjekteByNutzerReport zurueckliefert. 
	 * Der Report stellt alle Infos eines Nutzerprofils dar.
	 * 
	 * @param nutzerprofil Nutzerprofil-Objekt
	 * @return InfoObjekteByNutzerReport Fertiges Report-Objekt vom Typ InfoObjekteByNutzerReport
	 * @throws IllegalArgumentException
	 */
	InfoObjekteByNutzerReport createInfoObjekteByNutzerReport(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	
	
	
	/**
	 * Methode, die einen fertigen Report vom Typ ProfilInfoByNutzerprofilReport zurueckliefert.
	 * Der Report stellt alle Profilinformationen eines Nutzerprofils dar.
	 * 
	 * @param nutzerprofil Nutzerprofil-Objekt
	 * @return ProfilInfoByNutzerprofilReport Fertiges Report-Objekt vom Typ ProfilInfoByNutzerprofilReport
	 * @throws IllegalArgumentException
	 */
	ProfilInfoByNutzerprofilReport createProfilInfoByNutzerprofilReport(Nutzerprofil nutzerprofil)
			throws IllegalArgumentException;
	
	/**
	 * Methode, die einen fertigen Report vom Typ PartnervorschleageBySuchprofilReport zurueckliefert.
	 * Der Report stellt alle Partnervorschlaege, die anhand eines Suchprofils ermittelt wurden, 
	 * für ein Nutzerprofil dar.
	 * 
	 * @param nutzerprofil Nutzerprofil-Objekt
	 * @param suchprofilname Name des Suchprofil-Objektes
	 * @return PartnervorschleageBySuchprofilReport Fertiges Report-Objekt vom Typ PartnervorschleageBySuchprofilReport
	 * @throws IllegalArgumentException
	 */
	PartnervorschleageBySuchprofilReport createPartnervorschleageBySuchprofilReport(Nutzerprofil nutzerprofil,
			String suchprofilname) throws IllegalArgumentException;


	/**
	 * Methode, die einen fertigen Report vom Typ PartnervorschleageByUngesehenenNutzerprofilenReport zurueckliefert.
	 * Der Report stellt alle unangesehenen Partnervorschlaege eines Nutzerprofils dar.
	 * 
	 * @param nutzerprofil Nutzerprofil-Objekt
	 * @return PartnervorschleageByUngesehenenNutzerprofilenReport Fertiges Report-Objekt vom Typ PartnervorschleageByUngesehenenNutzerprofilenReport
	 * @throws IllegalArgumentException
	 */
	PartnervorschleageByUngesehenenNutzerprofilenReport createPartnervorschleageByUngesehenenNutzerprofilenReport(Nutzerprofil nutzerprofil)
			throws IllegalArgumentException;
	

//	/**
//	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von
//	 * GWT RPC zus�tzlich zum No Argument Constructor der implementierenden
//	 * Klasse BankAdministrationImpltungImpl} notwendig. Bitte diese Methode
//	 * direkt nach der Instantiierung aufrufen.
//	 * 
//	 * @throws IllegalArgumentException
//	 */
//	public void init() throws IllegalArgumentException;
//	
//	ProfilInfoByNutzerprofilReport createProfilInfoByNutzerprofilReport(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
//	
//	InfoObjekteByNutzerReport createInfoObjekteByNutzerReport(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
//	
//	
//	PartnervorschleageByUngesehenenNutzerprofilenReport createPartnervorschleageByUngesehenenNutzerprofilenReport(Nutzerprofil nutzerprofil)
//			throws IllegalArgumentException;
//	
//	PartnervorschleageBySuchprofilReport createPartnervorschleageBySuchprofilReport(Nutzerprofil nutzerprofil, String suchprofilname)
//			throws IllegalArgumentException;
//
//	
//	
//	/*
//	 * *************************************************************************
//	 * ABSCHNITT, Beginn: PartnervorschlaegeSp
//	 * ***************************************************************************
//	 */
//
//	/**
//	 * 
//	 * Aehnlichkeit zwischen einem Suchprofil eines Nutzers und den Profilinfos 
//	 * und InfoOjekte anderer Nutzerprofile berechnen.
//	 *  
//	 * @param profilid
//	 * @throws IllegalArgumentException
//	 */
//	public void AehnlichkeitForSuchprofilBerechnen(int profilId)
//			throws IllegalArgumentException;
//
//
//	/**
//	 * Alle Partnervorschlaege anhand von Suchprofilen fuer einen Nutzer auslesen.
//	 * Es werden nur diejenigen Nutzerprofile ausgelesen, von denen der Nutzer 
//	 * nicht gesperrt wurde. 
//	 * 
//	 * @param profilId
//	 * @param suchprofilName
//	 * @return Liste von Nutzerprofil-Objekten
//	 * @throws IllegalArgumentException
//	 */
//	public List<Nutzerprofil> getPartnervorschlaegeSuchprofil(int profilId,
//			String suchprofilName) throws IllegalArgumentException;
//
//	
//	/*
//	 * **************************************************************************
//	 * ABSCHNITT, Ende: PartnervorschlaegeSp
//	 * **************************************************************************
//	 */
//
//
//	/**
//	 * @param loginInfo
//	 * @return Nutzerprofil
//	 * @throws IllegalArgumentException
//	 */
//	public Nutzerprofil checkStatus(Nutzerprofil loginInfo) throws IllegalArgumentException;
//	
}

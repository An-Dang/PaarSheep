package de.hdm.Gruppe4.Paarsheep.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.report.InfoObjekteByNutzerReport;
import de.hdm.Gruppe4.Paarsheep.shared.report.PartnervorschleageBySuchprofilReport;
import de.hdm.Gruppe4.Paarsheep.shared.report.PartnervorschleageByUngesehenenNutzerprofilenReport;
import de.hdm.Gruppe4.Paarsheep.shared.report.ProfilInfoByNutzerprofilReport;

/**
 * Gegenst�ck zum Interface
 * @author Manuel Weiler
 *
 */
public interface ReportGeneratorAsync{
	
	/**
	 * Methode, die einen fertigen Report vom Typ InfoObjekteByNutzerReport zurueckliefert. 
	 * Der Report stellt alle InfoObjekte eines Nutzerprofils dar.
	 * 
	 * @param nutzerprofil Nutzerprofil-Objekt
	 * @param callback
	 */
	void createInfoObjekteByNutzerReport(Nutzerprofil nutzerprofil, AsyncCallback<InfoObjekteByNutzerReport> callback);

	/**
	 * Methode, die einen fertigen Report vom Typ ProfilInfoByNutzerprofilReport zurueckliefert.
	 * Der Report stellt alle Profil eines Nutzerprofils dar.
	 * 
	 * @param nutzerprofil Nutzerprofil-Objekt
	 * @param callback
	 */
	void createProfilInfoByNutzerprofilReport(Nutzerprofil nutzerprofil, AsyncCallback<ProfilInfoByNutzerprofilReport> callback);

	/**
	 * Methode, die einen fertigen Report vom Typ AllPartnervorschlaegeNpReport zurueckliefert.
	 * Der Report stellt alle unangesehenen Partnervorschlaege eines Nutzerprofils dar.
	 * 
	 * @param nutzerprofil Nutzerprofil-Objekt
	 * @param callback
	 */
	void createPartnervorschleageByUngesehenenNutzerprofilenReport(Nutzerprofil nutzerprofil,
			AsyncCallback<PartnervorschleageByUngesehenenNutzerprofilenReport> callback);

	/**
	 * Methode, die einen fertigen Report vom Typ PartnervorschleageBySuchprofilReport zurueckliefert.
	 * Der Report stellt alle Partnervorschlaege, die anhand eines Suchprofils ermittelt wurden, 
	 * für ein Nutzerprofil dar.
	 * 
	 * @param nutzerprofil Nutzerprofil-Objekt
	 * @param suchprofilname Name des Suchprofil-Objektes
	 * @param callback
	 */
	void createPartnervorschleageBySuchprofilReport(Nutzerprofil nutzerprofil, String suchprofilname,
			AsyncCallback<PartnervorschleageBySuchprofilReport> callback);

	
	

//	void init(AsyncCallback<Void> callback);
//	
//	
//	void createProfilInfoByNutzerprofilReport(Nutzerprofil nutzerprofil, AsyncCallback<ProfilInfoByNutzerprofilReport> callback);
//	
//	void createInfoObjekteByNutzerReport(Nutzerprofil nutzerprofil, AsyncCallback<InfoObjekteByNutzerReport> callback);
//	
//	void createPartnervorschleageByUngesehenenNutzerprofilenReport(Nutzerprofil nutzerprofil,
//			AsyncCallback<PartnervorschleageByUngesehenenNutzerprofilenReport> callback);
//	
//	void createPartnervorschleageBySuchprofilReport(Nutzerprofil nutzerprofil, String suchprofilname,
//			AsyncCallback<PartnervorschleageBySuchprofilReport> callback);
//	
//	/**
//	 * @param loginInfo
//	 * @param callback
//	 */
//	void checkStatus(Nutzerprofil loginInfo, AsyncCallback<Nutzerprofil> callback);

}

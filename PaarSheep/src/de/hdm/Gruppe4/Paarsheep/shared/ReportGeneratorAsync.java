package de.hdm.Gruppe4.Paarsheep.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;
import de.hdm.Gruppe4.Paarsheep.shared.report.*;

/**
 * Gegenst�ck zum Interface
 * @author Manuel Weiler
 *
 */
public interface ReportGeneratorAsync{
	
	/**
	 * Initialisierung des Objekts.
	 * 
	 * @param callback
	 */
	void init(AsyncCallback<Void> callback);
	
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
	void createProfilInfoByNutzerprofilReport(Nutzerprofil nutzerprofil, int aehnlichkeitsmass, AsyncCallback<ProfilInfoByNutzerprofilReport> callback);

	/**
	 * Methode, die einen fertigen Report vom Typ AllPartnervorschlaegeNpReport zurueckliefert.
	 * Der Report stellt alle unangesehenen Partnervorschlaege eines Nutzerprofils dar.
	 * 
	 * @param nutzerprofil Nutzerprofil-Objekt
	 * @param asyncCallback
	 */

	/**
	 * Methode, die einen fertigen Report vom Typ PartnervorschlaegeSpReport zurueckliefert.
	 * Der Report stellt alle Partnervorschlaege, die anhand eines Suchprofils ermittelt wurden, 
	 * für ein Nutzerprofil dar.
	 * 
	 * @param nutzerprofil Nutzerprofil-Objekt
	 * @param suchprofilname Name des Suchprofil-Objektes
	 * @param callback
	 */
	
	
	void createPartnervorschleageBySpReport(Nutzerprofil nutzerprofil, Suchprofil suchprofil,
			AsyncCallback<PartnervorschlaegeSpReport> asyncCallback);

	
	
	/**
	 * @param nutzerprofil
	 * @param asynccallback
	 */
	void createAllPartnervorschlaegeNpReport(Nutzerprofil nutzerprofil,
			AsyncCallback<AllPartnervorschlaegeNpReport> asynccallback);

	
	

//	void init(AsyncCallback<Void> callback);
//	
//	
//	void createProfilInfoByNutzerprofilReport(Nutzerprofil nutzerprofil, AsyncCallback<ProfilInfoByNutzerprofilReport> callback);
//	
//	void createInfoObjekteByNutzerReport(Nutzerprofil nutzerprofil, AsyncCallback<InfoObjekteByNutzerReport> callback);
//	
//	void createPartnervorschleageByUngesehenenNutzerprofilenReport(Nutzerprofil nutzerprofil,
//			AsyncCallback<AllPartnervorschlaegeNpReport> callback);
//	
//	void createPartnervorschleageBySuchprofilReport(Nutzerprofil nutzerprofil, String suchprofilname,
//			AsyncCallback<PartnervorschlaegeSpReport> callback);
//	
//	/**
//	 * @param loginInfo
//	 * @param callback
//	 */
//	void checkStatus(Nutzerprofil loginInfo, AsyncCallback<Nutzerprofil> callback);

}

package de.hdm.Gruppe4.Paarsheep.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * PartnerboerseAdministrationAsync ist das Gegenst�ck zu
 * PartnerboerseAdministration. Es l�sst sich automatisch durch das
 * Google-Plugin erstellen und erg�nzen. Dies macht eine weiter Dokumentation
 * �berfl�ssig.
 * 
 * @author An Dang
 * @author Dominik Sasse
 */

public interface PartnerboerseAdministrationAsync {

	/**
	 * @param optionsBezeichnung
	 * @param callback
	 */
	void createAuswahloption(String optionsBezeichnung, AsyncCallback<Auswahloption> callback);

	/**
	 * Abstrakte Klasse
	 * @param geburtsdatum 
	 * @param emailAddress 
	 * @param vorname 
	 * @param nachname 
	 * @param geschlecht 
	 * @param religion 
	 * @param koerpergroesse 
	 * @param haarfarbe 
	 * @param raucher 
	 * @param callback 
	 */
	// void createEigenschaft(String erlaeuterung, AsyncCallback<Eigenschaft>
	// callback);

	void createNutzerprofil(Date geburtsdatum, String emailAddress, String vorname, String nachname, String geschlecht,
			String religion, int koerpergroesse, String haarfarbe, String raucher,
			AsyncCallback<Nutzerprofil> callback);

	/**
	 * @param profilId
	 * @param vorname
	 * @param nachname
	 * @param geschlecht
	 * @param geburtsdatum
	 * @param koerpergroesse
	 * @param haarfarbe
	 * @param raucher
	 * @param religion
	 * @param callback
	 */
	void bearbeiteNutzerprofil(int profilId, String vorname, String nachname, String geschlecht, Date geburtsdatum,
			int koerpergroesse, String haarfarbe, String raucher, String religion, AsyncCallback<Void> callback);

	/**
	 * @param loginInfo
	 * @param callback
	 */
	void checkStatus(Nutzerprofil loginInfo, AsyncCallback<Nutzerprofil> callback);


	/**
	 * Abschnitt Suchprofil
	 * @author Tino Hauler
	 * @param porifilid 
	 * @param suchprofilname 
	 * @param geschlecht 
	 * @param raucher 
	 * @param haarfarbe 
	 * @param religion 
	 * @param koerpergroesse 
	 * @param callback 
	 */
	void insertSuchprofil(int porifilid, String suchprofilname, String geschlecht,
			String raucher, String haarfarbe, String religion, int koerpergroesse, AsyncCallback<Suchprofil> callback);
	
	/**
	 * Suchprofil updaten.
	 * @param profilid 
	 * @param suchprofilid 
	 * @param suchprofilname 
	 * @param religion 
	 * @param koerpergroesse 
	 * @param haarfarbe 
	 * @param raucher 
	 * @param geschlecht 
	 * @param callback 
	 */
	void updateSuchprofil(int profilid, int suchprofilid, String suchprofilname, String religion, int koerpergroesse,
			String haarfarbe, String raucher, String geschlecht, AsyncCallback<Void> callback);
	

	/**
	 * Suchprofil loeschen.
	 * @param nutzerprofilid 
	 * @param suchprofilName 
	 * @param callback 
	 */
	void deleteSuchprofil(int nutzerprofilid, String suchprofilName, AsyncCallback<Void> callback);

	

	/**
	 * @param callback
	 */
	void init(AsyncCallback<Void> callback);
	
	
	/**
	 * Alle Suchprofile eines Nutzers auslesen.
	 * @param nutzerprofilid 
	 * @param callback 
	 */
	void findSuchprofilByNutzerID(int nutzerprofilid, AsyncCallback<ArrayList<Suchprofil>> callback);
	
	
	/**
	 * Suchprofil anhand der SuchprofilID ausgeben.
	 * @param suchprofilid 
	 * @param callback 
	 */
	void findSuchprofilBySuchprofilID(int suchprofilid, AsyncCallback<Suchprofil> callback);
	
	/**
	 * Suchprofil anhand des Suchprofilnamens ausgeben.
	 * @param nutzerprofilid 
	 * @param suchprofilname 
	 * @param callback 
	 * @throws Exception 
	 */
	void findSuchprofiByName(int nutzerprofilid, String suchprofilname, AsyncCallback<Suchprofil> callback) throws Exception;
	

	/**
	 * ABSCHNITT Beginn Merkzettel
	 * 
	 * @author An Dang
	 * @param nutzerprofilID 
	 * @param GemerkterID 
	 * @param callback 
	 */
	void merkeNutzerprofil(int nutzerprofilID, int GemerkterID, AsyncCallback<Integer> callback);

	/**
	 * @param nutzerprofil
	 * @param callback
	 */
	void deleteMerkzettelOf(int nutzerprofil, AsyncCallback<Void> callback);

	/**
	 * @param MerkenderID
	 * @param GemerkteID
	 * @param callback
	 */
	void deleteNutzerprofilvonMerkliste(int MerkenderID, int GemerkteID, AsyncCallback<Void> callback);

	/**
	 * @param nutzerprofil
	 * @param callback
	 */
	void findByMerkenderID(int nutzerprofil, AsyncCallback<ArrayList<Nutzerprofil>> callback);

	/**
	 * ABSCHNITT Ende Merkzettel
	 */

	/**
	 * ABSCHNITT Beginn Kontaktsperrliste
	 * 
	 * @author An Dang
	 * @param nutzerprofilID 
	 * @param FremdprofilID 
	 * @param callback 
	 */
	void sperreNutzerprofil(int nutzerprofilID, int FremdprofilID, AsyncCallback<Integer> callback);

	/**
	 * @param nutzerprofil
	 * @param callback
	 */
	void deleteSperrlisteOf(int nutzerprofil, AsyncCallback<Void> callback);

	/**
	 * @param SperrenderID
	 * @param GesperrterID
	 * @param callback
	 */
	void entsperreNutzerprofil(int SperrenderID, int GesperrterID, AsyncCallback<Void> callback);

	/**
	 * @param nutzerprofil
	 * @param callback
	 */
	void findBySperrenderID(int nutzerprofil, AsyncCallback<ArrayList<Nutzerprofil>> callback);

	/**
	 * ABSCHNITT Ende Kontaktsperrliste
	 * @param information 
	 * @param ProfilID 
	 * @param EigenschaftID 
	 * @param Information 
	 * @param callback 
	 */
	
	void insertInformation(Information information, int ProfilID, int EigenschaftID, String Information, AsyncCallback<Information> callback);

	/**
	 * @param beschreibung
	 * @param callback
	 */
	void createBeschreibung(String beschreibung, AsyncCallback<Beschreibung> callback);


	/**
	 * @param callback
	 */
	void getAllNutzerprofile(AsyncCallback<ArrayList<Nutzerprofil>> callback);

	/**
	 * @param BesuchteProfilListeID
	 * @param BesuchteID
	 * @param BesucherID
	 * @param callback
	 */
	void besucheNutzerprofil(int BesuchteProfilListeID, int BesuchteID, int BesucherID,
			AsyncCallback<BesuchteProfilListe> callback);

	/**
	 * @param nutzerprofil
	 * @param callback
	 */
	void findByBesucherID(int nutzerprofil, AsyncCallback<ArrayList<Nutzerprofil>> callback);

	/**
	 * @param besuchteProfilListe
	 * @param callback
	 */
	void deleteNutzerprofilvonBesuchteProfilListe(BesuchteProfilListe besuchteProfilListe,
			AsyncCallback<Void> callback);

	/**
	 * @param nutzerprofil
	 * @param callback
	 */
	void deleteBesuchteProfilListeOf(Nutzerprofil nutzerprofil, AsyncCallback<Void> callback);
	
	/**
	 * @param fremdprofilID
	 * @param callback
	 */
	void getFremdesProfilByID(int fremdprofilID, AsyncCallback<Nutzerprofil> callback);

	// -------------------------------------------------------------------------
	// Für die Eigenschaften
	/**
	 * @param callback
	 */
	void readEigenschaft(AsyncCallback<ArrayList<Eigenschaft>> callback);
	// -------------------------------------------------------------------------


	/**
	 * @param profilID
	 * @param AsyncCallback
	 */
	void getNutzerprofilById(int profilID, AsyncCallback<Nutzerprofil> AsyncCallback);


	/**
	 * @param asyncCallback
	 */
	void getNutzerprofileOhneGesetzteSperrung(AsyncCallback<List<Nutzerprofil>> asyncCallback);

	/**
	 * @param suchprofilId
	 * @param fremdprofilId
	 * @param asyncCallback
	 */
	void berechneAehnlichkeitSpFor(int suchprofilId, int fremdprofilId, AsyncCallback<Integer> asyncCallback);

	/**
	 * @param suchprofilId
	 * @param suchprofilName
	 * @param fremdprofilId
	 * @param aehnlichkeit
	 * @param asyncCallback
	 */
	void aehnlichkeitSetzenSp(int suchprofilId, String suchprofilName, int fremdprofilId, int aehnlichkeit,
			AsyncCallback<Void> asyncCallback);

	/**
	 * @param fremdprofilId
	 * @param asyncCallback
	 */
	void besuchSetzen(int fremdprofilId, AsyncCallback<Void> asyncCallback);

	/**
	 * @param asyncCallback
	 */
	void getGeordnetePartnervorschlaegeNp(AsyncCallback<List<Nutzerprofil>> asyncCallback);

	/**
	 * @param nutzerprofilID
	 * @param fremdprofilID
	 * @param asyncCallback
	 */
	void  pruefeSperrstatusFremdprofil(int nutzerprofilID,int fremdprofilID, AsyncCallback<Integer> asyncCallback);

	/**
	 * @param nutzerprofilID
	 * @param fremdprofilID
	 * @param asyncCallback
	 */
	void pruefeVermerkstatus(int nutzerprofilID,int fremdprofilID, AsyncCallback<Integer> asyncCallback);

	/**
	 * @param selectedItemText
	 * @param asyncCallback
	 */
	void getGeordnetePartnervorschlaegeSp(String selectedItemText, AsyncCallback<List<Nutzerprofil>> asyncCallback);

}

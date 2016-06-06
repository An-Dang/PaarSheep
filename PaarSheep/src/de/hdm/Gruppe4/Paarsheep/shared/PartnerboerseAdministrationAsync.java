package de.hdm.Gruppe4.Paarsheep.shared;


import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;


/**
 * PartnerboerseAdministrationAsync ist das Gegenst�ck zu PartnerboerseAdministration. 
 * Es l�sst sich automatisch durch das Google-Plugin erstellen und erg�nzen.
 * Dies macht eine weiter Dokumentation �berfl�ssig.
 * @author An Dang
 * @author Dominik Sasse
 */


public interface PartnerboerseAdministrationAsync {

	void createAuswahloption(String optionsBezeichnung, AsyncCallback<Auswahloption> callback);

	/**
	 * Abstrakte Klasse
	 */
	//void createEigenschaft(String erlaeuterung, AsyncCallback<Eigenschaft> callback);

	void createNutzerprofil(Date geburtsdatum, String emailAddress, String vorname,String nachname,
			  String geschlecht,String religion,int koerpergroesse,
			  String haarfarbe,String raucher, 
							AsyncCallback<Nutzerprofil> callback);
	
	void bearbeiteNutzerprofil(Nutzerprofil nutzerprofil, AsyncCallback<Nutzerprofil> callback);
	
	
	void checkStatus (Nutzerprofil loginInfo, AsyncCallback<Nutzerprofil> callback); 

	/**
	 * Abstrakte Klasse
	 */
	//void createProfil(Boolean raucher, String haarfarbe, String religion, Integer koerpergroesse, String geschlecht,
	//		AsyncCallback<Profil> callback);

	void createSuchprofil(String geschlecht, 
			int altervon, int alterbis, String religion, 
			String haarfarbe, String raucher, int koerpergroessevon, 
			int koerpergroessebis, AsyncCallback<Suchprofil> callback);

	void init(AsyncCallback<Void> callback);
	
	/**
	 * ABSCHNITT Beginn Merkzettel 
	 * @author An Dang
	 */
	void merkeNutzerprofil(Merkzettel merkzettel,Nutzerprofil nutzerprofilID, int GemerkterID, AsyncCallback<Merkzettel> callback);
	
	void deleteMerkzettelOf(Nutzerprofil nutzerprofil, AsyncCallback<Void> callback);
	
	void deleteNutzerprofilvonMerkliste(Nutzerprofil MerkenderID, int GemerkteID, AsyncCallback<Void> callback);
	
	void findByMerkenderID(Nutzerprofil nutzerprofil, AsyncCallback<ArrayList<Nutzerprofil>> callback);
	/**
	 * ABSCHNITT Ende Merkzettel 
	 */
	
	/**
	 * ABSCHNITT Beginn Kontaktsperrliste 
	 * @author An Dang
	 */
	void sperreNutzerprofil(int SperrlisteID, int SperrenderID, int GesperrterID, AsyncCallback<Sperrliste> callback);
	
	void deleteSperrlisteOf(Nutzerprofil nutzerprofil, AsyncCallback<Void> callback);
	
	void entsperreNutzerprofil(int SperrenderID, int GesperrterID, AsyncCallback<Void> callback);
	
	void findBySperrenderID(Nutzerprofil nutzerprofil,  AsyncCallback<ArrayList<Nutzerprofil>> callback);
	/**
	 * ABSCHNITT Ende Kontaktsperrliste 
	 */

	void createBeschreibung(String beschreibung, AsyncCallback<Beschreibung> callback);

	void createAuswahl(Auswahloption a, AsyncCallback<Auswahl> callback);

	void saveNutzerprofil(Nutzerprofil nutzerprofil, AsyncCallback<Void> callback);

	void saveSuchprofil(Suchprofil suchprofil, AsyncCallback<Void> callback);

	void setNutzerprofil(Nutzerprofil p, AsyncCallback<Void> callback);

	void getNutzerprofil(int id, AsyncCallback<Nutzerprofil> callback);
	
// Alle Nutzerprofile auslesen
	void getAllNutzerprofile(AsyncCallback<ArrayList<Nutzerprofil>> callback);

	
	void getNutzerprofil(Nutzerprofil Nutzerprofil_ProfilID, AsyncCallback<Nutzerprofil> callback);

	void besucheNutzerprofil(int BesucheNutzerprofil, int BesuchteID, int BesucherID, AsyncCallback<BesuchteProfilListe> callback);
	
	void findByBesucherID(int nutzerprofil, AsyncCallback<ArrayList<Nutzerprofil>> callback);
	
	void deleteNutzerprofilvonBesuchteProfilListe (BesuchteProfilListe besuchteProfilListe, AsyncCallback<Void> callback);
	
	void deleteBesuchteProfilListeOf(Nutzerprofil nutzerprofil, AsyncCallback<Void> callback);
}

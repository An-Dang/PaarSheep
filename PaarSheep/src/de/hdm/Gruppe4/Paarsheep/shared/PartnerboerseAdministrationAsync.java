package de.hdm.Gruppe4.Paarsheep.shared;

import java.sql.*;
import java.util.ArrayList;

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

	void createNutzerprofil(String emailAddress, String vorname,String nachname,
			  String geschlecht,String religion,int koerpergroesse,
			  String haarfarbe,String raucher, 
							AsyncCallback<Nutzerprofil> callback);
	
	void checkStatus (String emailAdress, AsyncCallback<Nutzerprofil> callback); 

	/**
	 * Abstrakte Klasse
	 */
	//void createProfil(Boolean raucher, String haarfarbe, String religion, Integer koerpergroesse, String geschlecht,
	//		AsyncCallback<Profil> callback);

	void createSuchprofil(int koerpergroessevon, int koerpergroessebis, 
			int altervon, int alterbis, 
			String raucher, String religion, String haarfarbe, 
			String geschlecht, AsyncCallback<Suchprofil> callback);

	void init(AsyncCallback<Void> callback);

	void merkeNutzerprofil(int MerkzettelID, int MerkenderID, int GemerkterID, AsyncCallback<Merkzettel> callback);
	// Richtig ?
	void deleteMerkzettelOf(Nutzerprofil nutzerprofil, AsyncCallback<Void> callback);
	
	void findByMerkenderID(int nutzerprofil, AsyncCallback<ArrayList<Nutzerprofil>> callback);
	
	void sperreNutzerprofil(int SperrlisteID, int SperrenderID, int GesperrterID, AsyncCallback<Sperrliste> callback);
	
	void deleteSperrlisteOf(Nutzerprofil nutzerprofil, AsyncCallback<Void> callback);
	
	void findBySperrenderID(int nutzerprofilID,  AsyncCallback<ArrayList<Nutzerprofil>> callback);

	void createBeschreibung(String beschreibung, AsyncCallback<Beschreibung> callback);

	void createAuswahl(Auswahloption a, AsyncCallback<Auswahl> callback);

	void saveNutzerprofil(Nutzerprofil nutzerprofil, AsyncCallback<Void> callback);

	void saveSuchprofil(Suchprofil suchprofil, AsyncCallback<Void> callback);

	void setNutzerprofil(Nutzerprofil p, AsyncCallback<Void> callback);

	void getNutzerprofil(int id, AsyncCallback<Nutzerprofil> callback);

	void getAllNutzerprofile(AsyncCallback<ArrayList<Nutzerprofil>> callback);

	void getNutzerprofil(Nutzerprofil Nutzerprofil_ProfilID, AsyncCallback<Nutzerprofil> callback);

	void entsperreNutzerprofil(Sperrliste sperrliste, AsyncCallback<Void> callback);

	void deleteNutzerprofilvonMerkliste(Merkzettel merkzettel, AsyncCallback<Void> callback);

	void besucheNutzerprofil(int ProfilID, AsyncCallback<BesuchteProfilListe> callback);


}

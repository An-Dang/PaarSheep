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

	void createAuswahloption(String optionsBezeichnung, AsyncCallback<Auswahloption> callback);

	/**
	 * Abstrakte Klasse
	 */
	// void createEigenschaft(String erlaeuterung, AsyncCallback<Eigenschaft>
	// callback);

	void createNutzerprofil(Date geburtsdatum, String emailAddress, String vorname, String nachname, String geschlecht,
			String religion, int koerpergroesse, String haarfarbe, String raucher,
			AsyncCallback<Nutzerprofil> callback);

	void bearbeiteNutzerprofil(int profilId, String vorname, String nachname, String geschlecht, Date geburtsdatum,
			int koerpergroesse, String haarfarbe, String raucher, String religion, AsyncCallback<Void> callback);

	void checkStatus(Nutzerprofil loginInfo, AsyncCallback<Nutzerprofil> callback);

	/**
	 * Abstrakte Klasse
	 */
	// void createProfil(Boolean raucher, String haarfarbe, String religion,
	// Integer koerpergroesse, String geschlecht,
	// AsyncCallback<Profil> callback);

	/**
	 * Abschnitt Suchprofil
	 * @author Tino Hauler
	 */
	void insertSuchprofil(int porifilid, String suchprofilname, String geschlecht,
			String raucher, String haarfarbe, String religion, int koerpergroesse, AsyncCallback<Suchprofil> callback);
	
	void updateSuchprofil(int profilid, int suchprofilid, String suchprofilname, String geschlecht, String raucher, String haarfarbe,
			String religion, int koerpergroesse, AsyncCallback<Void> callback);

	void init(AsyncCallback<Void> callback);
	
	
	/**
	 * Alle Suchprofile eines Nutzers auslesen.
	 */
	void findSuchprofilByNutzerID(int nutzerprofilid, AsyncCallback<ArrayList<Suchprofil>> callback);
	
	
	/**
	 * Suchprofil anhand der SuchprofilID ausgeben.
	 */
	void findSuchprofilBySuchprofilID(int suchprofilid, AsyncCallback<Suchprofil> callback);
	
	/**
	 * Suchprofil anhand des Suchprofilnamens ausgeben.
	 */
	void findSuchprofiByName(int nutzerprofilid, String suchprofilname, AsyncCallback<Suchprofil> callback) throws Exception;
	

	/**
	 * ABSCHNITT Beginn Merkzettel
	 * 
	 * @author An Dang
	 */
	void merkeNutzerprofil(int nutzerprofilID, int GemerkterID, AsyncCallback<Integer> callback);

	void deleteMerkzettelOf(int nutzerprofil, AsyncCallback<Void> callback);

	void deleteNutzerprofilvonMerkliste(int MerkenderID, int GemerkteID, AsyncCallback<Void> callback);

	void findByMerkenderID(int nutzerprofil, AsyncCallback<ArrayList<Nutzerprofil>> callback);

	/**
	 * ABSCHNITT Ende Merkzettel
	 */

	/**
	 * ABSCHNITT Beginn Kontaktsperrliste
	 * 
	 * @author An Dang
	 */
	void sperreNutzerprofil(int nutzerprofilID, int FremdprofilID, AsyncCallback<Integer> callback);

	void deleteSperrlisteOf(int nutzerprofil, AsyncCallback<Void> callback);

	void entsperreNutzerprofil(int SperrenderID, int GesperrterID, AsyncCallback<Void> callback);

	void findBySperrenderID(int nutzerprofil, AsyncCallback<ArrayList<Nutzerprofil>> callback);

	/**
	 * ABSCHNITT Ende Kontaktsperrliste
	 */

	void createBeschreibung(String beschreibung, AsyncCallback<Beschreibung> callback);

	// void createAuswahl(Auswahloption a, AsyncCallback<Auswahl> callback);

	void getAllNutzerprofile(AsyncCallback<ArrayList<Nutzerprofil>> callback);

	void besucheNutzerprofil(int BesuchteProfilListeID, int BesuchteID, int BesucherID,
			AsyncCallback<BesuchteProfilListe> callback);

	void findByBesucherID(int nutzerprofil, AsyncCallback<ArrayList<Nutzerprofil>> callback);

	void deleteNutzerprofilvonBesuchteProfilListe(BesuchteProfilListe besuchteProfilListe,
			AsyncCallback<Void> callback);

	void deleteBesuchteProfilListeOf(Nutzerprofil nutzerprofil, AsyncCallback<Void> callback);
	
	void getFremdesProfilByID(int fremdprofilID, AsyncCallback<Nutzerprofil> callback);

	// -------------------------------------------------------------------------
	// Für die Eigenschaften
	void readBeschreibungen(AsyncCallback<ArrayList<Beschreibung>> callback);
	// -------------------------------------------------------------------------

	void findeSuchprofile(Nutzerprofil nutzerprofil, AsyncCallback<ArrayList<Suchprofil>> callback);

	void getNutzerprofilById(int profilID, AsyncCallback<Nutzerprofil> AsyncCallback);

	void getAllSuchprofileFor(AsyncCallback<List<Suchprofil>> asyncCallback);

	void getNutzerprofileOhneGesetzteSperrung(AsyncCallback<List<Nutzerprofil>> asyncCallback);

	void berechneAehnlichkeitSpFor(int suchprofilId, int fremdprofilId, AsyncCallback<Integer> asyncCallback);

	void aehnlichkeitSetzenSp(int suchprofilId, String suchprofilName, int fremdprofilId, int aehnlichkeit,
			AsyncCallback<Void> asyncCallback);

	void besuchSetzen(int fremdprofilId, AsyncCallback<Void> asyncCallback);

	void getGeordnetePartnervorschlaegeNp(AsyncCallback<List<Nutzerprofil>> asyncCallback);

	void  pruefeSperrstatusFremdprofil(int nutzerprofilID,int fremdprofilID, AsyncCallback<Integer> asyncCallback);

	void pruefeVermerkstatus(int fremdprofilID, AsyncCallback<Integer> asyncCallback);

	void vermerkstatusAendern(int fremdprofilId, AsyncCallback<Integer> asyncCallback);

	void sperrstatusAendern(int fremdprofilId, AsyncCallback<Integer> asyncCallback);

	void getGeordnetePartnervorschlaegeSp(String selectedItemText, AsyncCallback<List<Nutzerprofil>> asyncCallback);

}

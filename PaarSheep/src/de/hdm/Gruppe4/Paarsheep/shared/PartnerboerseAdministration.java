package de.hdm.Gruppe4.Paarsheep.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * @author andang
 *
 */
@RemoteServiceRelativePath("partnerboerseadministration")
public interface PartnerboerseAdministration extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von
	 * GWT RPC zus�tzlich zum No Argument Constructor der implementierenden
	 * Klasse {@link BankVerwaltungImpl} notwendig. Bitte diese Methode direkt
	 * nach der Instantiierung aufrufen.
	 * 
	 * @throws IllegalArgumentException
	 * @author Thies
	 * @author Dominik Sasse
	 * 
	 */

	public void init() throws IllegalArgumentException;

	/**
	 * @param nutzerprofilID 
	 * @return ArrayList<Nutzerprofil>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Nutzerprofil> getAllNutzerprofile(int nutzerprofilID) throws IllegalArgumentException;

	/**
	 * 
	 * 
	 * @return ArrayList<Eigenschaft>
	 * @throws IllegalArgumentException 
	 */
	
	
				// Information / Eigenschaften
	
	public ArrayList<Beschreibung> readEigenschaft() throws IllegalArgumentException;
	
	/**
	 * @return ArrayList<Option>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> readOption() throws IllegalArgumentException;
	
	/**
	 * @return readOptionAuswahl
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> readOptionAuswahl(int eigenschaftsID) throws IllegalArgumentException;
	
	/**
	 * @param profilID
	 * @return sd
	 * @throws IllegalArgumentException
	 */
	public Map<List<Option>, List<Option>> getAllProfilAuswahlEig(int eigenschaftsID) 
			throws IllegalArgumentException;
	
	/**
	 * @param nutzerprofilID
	 * @return <List<Beschreibung>, List<Information>
	 * @throws IllegalArgumentException
	 */
	public Map<List<Beschreibung>, List<Information>> showProfilAllEigBeschreibung(int nutzerprofilID) throws IllegalArgumentException;
	
	/**
	 * @param nutzerprofilID
	 * @return Map<List<Beschreibung>, List<Information>
	 * @throws IllegalArgumentException
	 */
	public Map<List<Beschreibung>, List<Information>> showProfilEigBeschreibung(int nutzerprofilID) throws IllegalArgumentException;
	
	/**
	 * @param profilID
	 * @throws IllegalArgumentException
	 */
	public void deleteAllNutzerInfo (int profilID) throws IllegalArgumentException;
	
	/**
	 * @param nutzerprofilID
	 * @return Map<List<Option>, List<Information>
	 * @throws IllegalArgumentException
	 */
	public Map<List<Option>, List<Information>> showProfilEigAuswahl(int nutzerprofilID) throws IllegalArgumentException;
	
	public ArrayList<Option> findOptionByProfil(int profilID) throws IllegalArgumentException;
	
	/**
	 * @param info
	 * @param profilID
	 * @param eigenschaftID
	 * @throws IllegalArgumentException
	 */
	public void bearbeiteNutzerprofilInfo(String info,
			int profilID, int eigenschaftID) throws IllegalArgumentException;
	/**
	 * @param geburtsdatum
	 * @param emailAddress
	 * @param vorname
	 * @param nachname
	 * @param geschlecht
	 * @param religion
	 * @param koerpergroesse
	 * @param haarfarbe
	 * @param raucher
	 * @return Nutzerprofil
	 * @throws IllegalArgumentException
	 */
	public Nutzerprofil createNutzerprofil(Date geburtsdatum, String emailAddress, String vorname, String nachname, String geschlecht,
			String religion, int koerpergroesse, String haarfarbe, String raucher) throws IllegalArgumentException;


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
	 * @throws IllegalArgumentException
	 */
	public void bearbeiteNutzerprofil(int profilId, String vorname, String nachname, String geschlecht,
			Date geburtsdatum, int koerpergroesse, String haarfarbe, String raucher, String religion)
			throws IllegalArgumentException;

	/**
	 * @param loginInfo
	 * @return Nutzerprofil
	 * @throws IllegalArgumentException
	 */
	public Nutzerprofil checkStatus(Nutzerprofil loginInfo) throws IllegalArgumentException;

	/**
	 * Abstrakte Klasse.
	 * 
	 * @author Dominik Sasse
	 */
	// public Eigenschaft createEigenschaft(String erlaeuterung)
	// throws IllegalArgumentException;

	/**
	 * Auswahl anlegen
	 * 
	 * @author Dominik Sasse
	 */
	// public Auswahl createAuswahl(Auswahloption a) throws
	// IllegalArgumentException;

//	/**
//	 * Eine Auswahloption anlegen.
//	 * @param optionsBezeichnung 
//	 * @return Auswahloption
//	 * @throws IllegalArgumentException 
//	 */
//	public Auswahloption createAuswahloption(String optionsBezeichnung) throws IllegalArgumentException;

	/**
	 * Eine Beschreibung erstellen
	 * @param beschreibung 
	 * @return Beschreibung
	 * @throws IllegalArgumentException 
	 * 
	 */
	public Beschreibung createBeschreibung(String beschreibung) throws IllegalArgumentException;

	/**
	 * Erstellen eines Suchprofils
	 * 
	 * @author Tino Hauler
	 * @param profilid 
	 * @param suchprofilname 
	 * @param geschlecht 
	 * @param raucher 
	 * @param haarfarbe 
	 * @param religion 
	 * @param koerpergroesse 
	 * @return suchprofil
	 * @throws IllegalArgumentException 
	 */
	public Suchprofil insertSuchprofil(int profilid, String suchprofilname, String geschlecht, String raucher,
			String haarfarbe, String religion, int koerpergroesse) throws IllegalArgumentException;

	/**
	 * Bearbeiten des Suchprofils
	 * 
	 * @author Tino Hauler
	 * @param profilid 
	 * @param suchprofilid 
	 * @param suchprofilname 
	 * @param religion 
	 * @param koerpergroesse 
	 * @param haarfarbe 
	 * @param raucher 
	 * @param geschlecht 
	 * @throws IllegalArgumentException 
	 */
	void updateSuchprofil(int profilid, int suchprofilid, String suchprofilname, String religion, int koerpergroesse,
			String haarfarbe, String raucher, String geschlecht) throws IllegalArgumentException;

	/**
	 * Suchprofil loeschen.
	 * @param nutzerprofilId 
	 * @param suchprofilName 
	 * @throws IllegalArgumentException 
	 */
	public void deleteSuchprofil(int nutzerprofilId, String suchprofilName) throws IllegalArgumentException;

	/**
	 * Alle Suchprofile eines Nutzers ausgeben.
	 * @param nutzerprofilid 
	 * @return ArrayList<Suchprofil>
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<Suchprofil> findSuchprofilByNutzerID(int nutzerprofilid) throws IllegalArgumentException;

	/**
	 * Alle Suchprofile eines Nutzers ausgeben.
	 * @param suchprofilId 
	 * @return suchprofil
	 * @throws IllegalArgumentException 
	 */
	public Suchprofil findSuchprofilBySuchprofilID(int suchprofilId) throws IllegalArgumentException;

	/**
	 * Alle Suchprofile des Suchprofilnamens ausgeben.
	 * @param nutzerprofilid 
	 * @param suchprofilname 
	 * @return suchprofil
	 * @throws Exception 
	 */

	public Suchprofil findSuchprofiByName(int nutzerprofilid, String suchprofilname) throws Exception;

	/**
	 * ABSCHNITT Beginn Merkzettel
	 * 
	 * @author An Dang
	 * @param nutzerprofilID 
	 * @param GemerkterID 
	 * @return int
	 * @throws IllegalArgumentException 
	 */
	public int merkeNutzerprofil(int nutzerprofilID, int GemerkterID) throws IllegalArgumentException;

	/**
	 * @param MerkenderID
	 * @param GemerkteID
	 * @throws IllegalArgumentException
	 */
	public void deleteNutzerprofilvonMerkliste(int MerkenderID, int GemerkteID) throws IllegalArgumentException;

	/**
	 * @param nutzerprofil
	 * @throws IllegalArgumentException
	 */
	public void deleteMerkzettelOf(int nutzerprofil) throws IllegalArgumentException;

	/**
	 * @param nutzerprofil
	 * @return ArrayList<Nutzerprofil>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Nutzerprofil> findByMerkenderID(int nutzerprofil) throws IllegalArgumentException;

	/**
	 * ABSCHNITT Ende Merkzettel
	 */

	/**
	 * ABSCHNITT Beginn Kontaktsperrliste
	 * 
	 * @author An Dang
	 * @param nutzerprofilID 
	 * @param FremdprofilID 
	 * @return int
	 * @throws IllegalArgumentException 
	 */
	public int sperreNutzerprofil(int nutzerprofilID, int FremdprofilID) throws IllegalArgumentException;

	/**
	 * @param SperrenderID
	 * @param GesperrterID
	 * @throws IllegalArgumentException
	 */
	public void entsperreNutzerprofil(int SperrenderID, int GesperrterID) throws IllegalArgumentException;

	/**
	 * @param nutzerprofil
	 * @throws IllegalArgumentException
	 */
	public void deleteSperrlisteOf(int nutzerprofil) throws IllegalArgumentException;

	/**
	 * @param nutzerprofil
	 * @return ArrayList<Nutzerprofil>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Nutzerprofil> findBySperrenderID(int nutzerprofil) throws IllegalArgumentException;

	/**
	 * ABSCHNITT Ende Merkzettel
	 */

	/**
	 * Profil zu BesuchteprofilListe hinzufuegen
	 * 
	 * @author Tino Hauler
	 * @param BesuchteProfilListeID 
	 * @param BesuchteID 
	 * @param BesucherID 
	 * @return BesuchteProfilListe
	 * @throws IllegalArgumentException 
	 * 
	 */
	public int besucheNutzerprofil(int BesuchteID, int BesucherID)
			throws IllegalArgumentException;

	/**
	 * Profil von BesuchteProfilListe entfernen
	 * 
	 * @author Tino Hauler
	 * @param besuchteProfilListe 
	 * @throws IllegalArgumentException 
	 * 
	 */
	public void deleteNutzerprofilvonBesuchteProfilListe(BesuchteProfilListe besuchteProfilListe)
			throws IllegalArgumentException;

	/**
	 * Entfernen der BesuchteProfilListe, wenn der Nutzer gelöscht wird.
	 * 
	 * @author Tino Hauler
	 * @param nutzerprofil 
	 * @throws IllegalArgumentException 
	 * 
	 */

	public void deleteBesuchteProfilListeOf(Nutzerprofil nutzerprofil) throws IllegalArgumentException;

	/**
	 * Auslesen aller besuchten Profile eines durch Fremdschlüssel (BesucherID)
	 * gegebenen Nutzerprofils
	 * 
	 * @author Tino Hauler
	 * @param information 
	 * @param ProfilID 
	 * @param EigenschaftID 
	 * @param Information 
	 * @return information
	 */
	
	public Information insertInformation(Information information, int ProfilID, int EigenschaftID, String Information);

	/**
	 * @param nutzerprofil
	 * @return ArrayList<Nutzerprofil>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Nutzerprofil> findByBesucherID(int nutzerprofil) throws IllegalArgumentException;

	/**
	 * @param profilID
	 * @return nutzerprofil
	 * @throws IllegalArgumentException
	 */
	public Nutzerprofil getNutzerprofilById(int profilID) throws IllegalArgumentException;

	/**
	 * @return List<Nutzerprofil>
	 */
	public List<Nutzerprofil> getNutzerprofileOhneGesetzteSperrung();

	/**
	 * @param suchprofilId
	 * @param fremdprofilId
	 * @return int
	 */
	public int berechneAehnlichkeitSpFor(int suchprofilId, int fremdprofilId);

	/**
	 * @param suchprofilId
	 * @param suchprofilName
	 * @param fremdprofilId
	 * @param aehnlichkeit
	 */
	void aehnlichkeitSetzenSp(int suchprofilId, String suchprofilName, int fremdprofilId, int aehnlichkeit);

	/**
	 * @param fremdprofilId
	 */
	void besuchSetzen(int fremdprofilId);

	/**
	 * @param nutzerprofilID 
	 * @return double
	 */
	double getPartnervorschlaegeNp(Nutzerprofil nutzerprofil, Suchprofil suchprofil);

	/**
	 * @param nutzerprofilID
	 * @param fremdprofilID
	 * @return int
	 */
	public int pruefeSperrstatusFremdprofil(int nutzerprofilID, int fremdprofilID);

	/**
	 * @param nutzerprofilID
	 * @param fremdprofilID
	 * @return int
	 */
	public int pruefeVermerkstatus(int nutzerprofilID,int fremdprofilID);



	/**
	 * @param suchprofilName
	 * @return List<Nutzerprofil>
	 * @throws IllegalArgumentException
	 */
	public List<Nutzerprofil> getGeordnetePartnervorschlaegeSp(String suchprofilName) throws IllegalArgumentException;

	/**
	 * @param fremdprofilID
	 * @return nutzerprofil
	 * @throws IllegalArgumentException
	 */
	public Nutzerprofil getFremdesProfilByID(int fremdprofilID) throws IllegalArgumentException;

}

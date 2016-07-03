package de.hdm.Gruppe4.Paarsheep.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Synchrones Interface welches das Interface RemoteService erweitert. In ihr
 * finden sich sämtliche Methodensignaturen der Methoden, welche von der Klasse
 * PartnerboerseAdministraionImpl zu implementieren sind.
 * 
 * @author An Dang
 * @author Tino Hauler
 */
@RemoteServiceRelativePath("partnerboerseadministration")
public interface PartnerboerseAdministration extends RemoteService {

	/**
	 * Initialisieren
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Nutzerprofil
	 * *************************************************************************
	 * **
	 */

	/**
	 * Alle Nutzerprofile in eine ArrayList schreiben
	 * 
	 * @param nutzerprofilID
	 * @return ArrayList<Nutzerprofil>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Nutzerprofil> getAllNutzerprofile(int nutzerprofilID) throws IllegalArgumentException;

	/**
	 * InfoObjekt eines Nutzerprofils löschen
	 * 
	 * @param profilID
	 * @param eigID
	 * @throws IllegalArgumentException
	 */

	void deleteNutzerInfo(int profilID, int eigID) throws IllegalArgumentException;

	/**
	 * Eigenschaftsmöglichkeiten auslesen
	 * 
	 * @return ArrayList<Beschreibung>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Beschreibung> readEigenschaft() throws IllegalArgumentException;

	/**
	 * Option auslesen
	 * 
	 * @return ArrayList<Option>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> readOption() throws IllegalArgumentException;

	/**
	 * Auswahloptionen auslesen
	 * 
	 * @param eigenschaftsID
	 * @return ArrayList<readOptionAuswahl>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> readOptionAuswahl(int eigenschaftsID) throws IllegalArgumentException;

	/**
	 * @param eigenschaftsID
	 * @return Map<List<Option>, List<Option>
	 * @throws IllegalArgumentException
	 */
	public Map<List<Option>, List<Option>> getAllProfilAuswahlEig(int eigenschaftsID) throws IllegalArgumentException;

	/**
	 * @param nutzerprofilID
	 * @return Map <List<Beschreibung>, List<Information>
	 * @throws IllegalArgumentException
	 */
	public Map<List<Beschreibung>, List<Information>> showProfilAllEigBeschreibung(int nutzerprofilID)
			throws IllegalArgumentException;

	/**
	 * @param nutzerprofilID
	 * @return Map<List<Beschreibung>, List<Information>
	 * @throws IllegalArgumentException
	 */
	public Map<List<Beschreibung>, List<Information>> showProfilEigBeschreibung(int nutzerprofilID)
			throws IllegalArgumentException;

	/**
	 * Alle InfoObjekte eines Nutzers löschen
	 * 
	 * @param profilID
	 * @throws IllegalArgumentException
	 */
	public void deleteAllNutzerInfo(int profilID) throws IllegalArgumentException;

	/**
	 * @param nutzerprofilID
	 * @return Map<List<Option>, List<Information>
	 * @throws IllegalArgumentException
	 */
	public Map<List<Option>, List<Information>> showProfilEigAuswahl(int nutzerprofilID)
			throws IllegalArgumentException;

	/**
	 * Option eines Profils ausgeben
	 * 
	 * @param profilID
	 * @return ArrayList<Option>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> findOptionByProfil(int profilID) throws IllegalArgumentException;

	/**
	 * InfoObjekt eines Nutzers bearbeiten
	 * 
	 * @param info
	 * @param profilID
	 * @param eigenschaftID
	 * @throws IllegalArgumentException
	 */
	public void bearbeiteNutzerprofilInfo(String info, int profilID, int eigenschaftID) throws IllegalArgumentException;

	/**
	 * Ein neues Nutzerprofil erstellen
	 * 
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
	public Nutzerprofil createNutzerprofil(Date geburtsdatum, String emailAddress, String vorname, String nachname,
			String geschlecht, String religion, int koerpergroesse, String haarfarbe, String raucher)
			throws IllegalArgumentException;

	/**
	 * Nutzerprofil bearbeiten
	 * 
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

	// /**
	// * Eine Auswahloption anlegen.
	// * @param optionsBezeichnung
	// * @return Auswahloption
	// * @throws IllegalArgumentException
	// */
	// public Auswahloption createAuswahloption(String optionsBezeichnung)
	// throws IllegalArgumentException;

	/**
	 * Eine Beschreibung erstellen
	 * 
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
	 * 
	 * @param nutzerprofilId
	 * @param suchprofilName
	 * @throws IllegalArgumentException
	 */
	public void deleteSuchprofil(int nutzerprofilId, String suchprofilName) throws IllegalArgumentException;

	/**
	 * Alle Suchprofile eines Nutzers ausgeben.
	 * 
	 * @param nutzerprofilid
	 * @return ArrayList<Suchprofil>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Suchprofil> findSuchprofilByNutzerID(int nutzerprofilid) throws IllegalArgumentException;

	/**
	 * Alle Suchprofile eines Nutzers ausgeben.
	 * 
	 * @param suchprofilId
	 * @return suchprofil
	 * @throws IllegalArgumentException
	 */
	public Suchprofil findSuchprofilBySuchprofilID(int suchprofilId) throws IllegalArgumentException;

	/**
	 * Alle Suchprofile des Suchprofilnamens ausgeben.
	 * 
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
	 * fremdes Nutzerprofil von Merklist löschen
	 * 
	 * @param MerkenderID
	 * @param GemerkteID
	 * @throws IllegalArgumentException
	 */
	public void deleteNutzerprofilvonMerkliste(int MerkenderID, int GemerkteID) throws IllegalArgumentException;

	/**
	 * Den Merkzettel eines Nutzerprofils löschen
	 * 
	 * @param nutzerprofil
	 * @throws IllegalArgumentException
	 */
	public void deleteMerkzettelOf(int nutzerprofil) throws IllegalArgumentException;

	/**
	 * Merkzettel andhand des MerkenderID ausgeben
	 * 
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
	 * Ein Nutzerprofil entsperren
	 * 
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
	 * Sperrliste anhand der SperrenderID ausgeben
	 * 
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
	public int besucheNutzerprofil(int BesuchteID, int BesucherID) throws IllegalArgumentException;

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
	 * BesuchteProfilListe anhand der BesucherID ausgeben
	 * 
	 * @param nutzerprofil
	 * @return ArrayList<Nutzerprofil>
	 * @throws IllegalArgumentException
	 */
	public ArrayList<BesuchteProfilListe> findByBesucherID(int nutzerprofil) throws IllegalArgumentException;

	/**
	 * Ein Nutzerprofil andhand der ID auslesen
	 * 
	 * @param profilID
	 * @return nutzerprofil
	 * @throws IllegalArgumentException
	 */
	public Nutzerprofil getNutzerprofilById(int profilID) throws IllegalArgumentException;

	/**
	 * @param fremdprofilId
	 */
	void besuchSetzen(int fremdprofilId);

	/**
	 * Ähnlichkeitsmaß für unangsehenen Partnervorschläge
	 * 
	 * @param nutzerprofilI
	 * @return double
	 */
	public ArrayList<Aehnlichkeitsmass> getPartnervorschlaegeNp(Nutzerprofil np);

	/**
	 * Ähnlichkeitsmaß für Partnervorschläge anhand Suchprofil
	 * 
	 * @param nutzerprofil
	 *            * @param suchprofil
	 * @return double
	 */
	public ArrayList<Aehnlichkeitsmass> getPartnervorschlaegeSp(Nutzerprofil np, Suchprofil sp);

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
	public int pruefeVermerkstatus(int nutzerprofilID, int fremdprofilID);

	/**
	 * @param fremdprofilID
	 * @return nutzerprofil
	 * @throws IllegalArgumentException
	 */
	public Nutzerprofil getFremdesProfilByID(int fremdprofilID) throws IllegalArgumentException;

	/**
	 * Alle unangesehenen Nutzerprofile
	 * 
	 * @param profilId
	 * @return ArrayList<Nutzerprofil>
	 */

	public ArrayList<Nutzerprofil> getUnangeseheneNutzerprofile(int profilId);

	/**
	 * Löschen eines Nutzerprofils
	 * 
	 * @param nutzerprofilID
	 */

	public void deleteNutzerprofil(int nutzerprofilID);

}

package de.hdm.Gruppe4.Paarsheep.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.Gruppe4.Paarsheep.server.db.*;
import de.hdm.Gruppe4.Paarsheep.shared.*;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * @author andang
 *
 */
@SuppressWarnings("serial")
public class PartnerboerseAdministrationImpl extends RemoteServiceServlet implements PartnerboerseAdministration {

	private OptionMapper optionMapper = null;
	private BeschreibungMapper beschreibungMapper = null;
	private BesuchteProfilListeMapper besuchteProfilListeMapper = null;
	private EigenschaftMapper eigenschaftMapper = null;
	private InformationMapper informationMapper = null;
	private MerkzettelMapper merkzettelMapper = null;
	private NutzerprofilMapper nutzerprofilMapper = null;
	private SperrlisteMapper sperrlisteMapper = null;
	private SuchprofilMapper suchprofilMapper = null;

	private Suchprofil suchprofil = null;

	/**
	 * No-Argument Konstruktor
	 * 
	 * @throws IllegalArgumentException
	 */
	public PartnerboerseAdministrationImpl() throws IllegalArgumentException {

	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Initialisierung
	 * ***************************************
	 * ************************************
	 */

	public void init() throws IllegalArgumentException {
		this.optionMapper = OptionMapper.optionMapper();
		this.beschreibungMapper = BeschreibungMapper.beschreibungMapper();
		this.besuchteProfilListeMapper = BesuchteProfilListeMapper.besuchteProfilListeMapper();
		this.eigenschaftMapper = EigenschaftMapper.eigenschaftMapper();
		this.informationMapper = InformationMapper.informationMapper();
		this.merkzettelMapper = MerkzettelMapper.merkzettelMapper();
		this.nutzerprofilMapper = NutzerprofilMapper.nutzerprofilMapper();
		this.sperrlisteMapper = SperrlisteMapper.sperrlisteMapper();
		this.suchprofilMapper = SuchprofilMapper.suchprofilMapper();

	}

	// ABSCHNITT, Ende: Initialisierung

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Nutzerprofil
	 * *************************************************************************
	 * **
	 */

	/**
	 * Nutzerprofil anlegen.
	 */

	@Override
	public Nutzerprofil createNutzerprofil(Date geburtsdatum, String emailAddress, String vorname, String nachname,
			String geschlecht, String religion, int koerpergroesse, String haarfarbe, String raucher)
			throws IllegalArgumentException {

		Nutzerprofil nutzerprofil = new Nutzerprofil();
		nutzerprofil.setEmailAddress(emailAddress);
		nutzerprofil.setVorname(vorname);
		nutzerprofil.setNachname(nachname);
		nutzerprofil.setGeburtsdatum(geburtsdatum);

		nutzerprofil.setRaucher(raucher);
		nutzerprofil.setHaarfarbe(haarfarbe);
		nutzerprofil.setReligion(religion);
		nutzerprofil.setKoerpergroesse(koerpergroesse);
		nutzerprofil.setGeschlecht(geschlecht);

		return this.nutzerprofilMapper.insert(nutzerprofil);
	}

	/**
	 * Nutzerprofil anlegen.
	 */

	public void bearbeiteNutzerprofil(int profilId, String vorname, String nachname, String geschlecht,
			Date geburtsdatum, int koerpergroesse, String haarfarbe, String raucher, String religion) {

		Nutzerprofil nutzerprofil = new Nutzerprofil();
		nutzerprofil.setProfilID(profilId);
		nutzerprofil.setVorname(vorname);
		nutzerprofil.setNachname(nachname);
		nutzerprofil.setGeschlecht(geschlecht);
		nutzerprofil.setGeburtsdatum(geburtsdatum);
		nutzerprofil.setKoerpergroesse(koerpergroesse);
		nutzerprofil.setHaarfarbe(haarfarbe);
		nutzerprofil.setRaucher(raucher);
		nutzerprofil.setReligion(religion);

		this.nutzerprofilMapper.bearbeiteNutzerprofil(nutzerprofil);
	}

	public Nutzerprofil checkStatus(Nutzerprofil loginInfo) {
		return this.nutzerprofilMapper.checkStatus(loginInfo);
	}

	/**
	 * Auslesen aller Nutzer
	 * 
	 * @param nutzerprofilID
	 * @return nutzerprofilID
	 * @throws IllegalArgumentException
	 * 
	 */
	public ArrayList<Nutzerprofil> getAllNutzerprofile(int nutzerprofilID) throws IllegalArgumentException {

		return this.nutzerprofilMapper.findAllNutzerprofil(nutzerprofilID);
	}


	public Nutzerprofil getFremdesProfilByID(int fremdprofilID) {

		return this.nutzerprofilMapper.findFremdprofil(fremdprofilID);
	}

	/**
	 * Nutzerprofil anhand dessen Profil-ID auslesen.
	 */
	public Nutzerprofil getNutzerprofilById(int profilID) throws IllegalArgumentException {
		return this.nutzerprofilMapper.findByNutzerprofilId(profilID);
	}
	
	/**
	 * @param profilId
	 * @return df
	 * @throws IllegalArgumentException
	 */
	public List<Nutzerprofil> getUnangeseheneNutzerprofile(int profilId) throws IllegalArgumentException {
		return this.nutzerprofilMapper.findUnangeseheneNutzerprofileByID(profilId);
	}

	
	

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Nutzerprofil
	 * *************************************************************************
	 * **
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Suchprofil
	 * *************************************************************************
	 * **
	 */

	/**
	 * Suchprofil anlegen.
	 */

	public Suchprofil insertSuchprofil(int profilid, String suchprofilname, String geschlecht, String raucher,
			String haarfarbe, String religion, int koerpergroesse) throws IllegalArgumentException {

		Suchprofil suchprofil = new Suchprofil();
		suchprofil.setSuchprofilName(suchprofilname);
		suchprofil.setKoerpergroesse(koerpergroesse);

		suchprofil.setGeschlecht(geschlecht);
		suchprofil.setRaucher(raucher);
		suchprofil.setReligion(religion);
		suchprofil.setHaarfarbe(haarfarbe);
		return this.suchprofilMapper.insertSuchprofil(suchprofil, profilid);
	}

	/**
	 * Suchprofil aktualisieren.
	 */
	public void updateSuchprofil(int profilid, int suchprofilid, String suchprofilname, String religion,
			int koerpergroesse, String haarfarbe, String raucher, String geschlecht) throws IllegalArgumentException {

		Suchprofil suchprofil = new Suchprofil();
		suchprofil.setProfilID(suchprofilid);
		suchprofil.setSuchprofilName(suchprofilname);
		suchprofil.setReligion(religion);
		suchprofil.setHaarfarbe(haarfarbe);
		suchprofil.setRaucher(raucher);
		suchprofil.setKoerpergroesse(koerpergroesse);
		suchprofil.setGeschlecht(geschlecht);

		this.suchprofilMapper.updateSuchprofil(suchprofil);

	}

	/**
	 * Suchprofil loeschen.
	 */
	public void deleteSuchprofil(int nutzerprofilid, String suchprofilName) throws IllegalArgumentException {
		this.suchprofilMapper.deleteSuchprofil(nutzerprofilid, suchprofilName);
	}

	/**
	 * Finde Suchprofil anhand der NutzerprofilID
	 */
	public ArrayList<Suchprofil> findSuchprofilByNutzerID(int nutzerprofil) {
		return this.suchprofilMapper.findSuchprofilByNutzerID(nutzerprofil);
	}

	/**
	 * Finde Suchprofil anhand der SuchprofilID
	 */

	public Suchprofil findSuchprofilBySuchprofilID(int suchprofilId) throws IllegalArgumentException {
		return this.suchprofilMapper.findSuchprofilBySuchprofilID(suchprofilId);
	}

	/**
	 * Finde Suchprofil anhand des Suchprofilnames
	 */
	public Suchprofil findSuchprofiByName(int nutzerprofilid, String suchprofilname) throws Exception {
		return SuchprofilMapper.suchprofilMapper().findSuchprofiByName(nutzerprofilid, suchprofilname);
	}

	// /**
	// * Finde Suchprofil
	// */
	// public ArrayList<Suchprofil> findeSuchprofile(Nutzerprofil nutzerprofil)
	// {
	//
	// return this.suchprofilMapper.readSuchprofile(nutzerprofil);
	// }

	/**
	 * Suche durchf�hren anhand von Suchprofil
	 */

	/**
	 * public Nutzerprofil suchemitSuchprofil(int ProfilID) {
	 * 
	 * getAllNutzerprofile();
	 * 
	 * for (int i = 0; i < getAllNutzerprofile().size(); i++) {
	 * 
	 * if (this.suchprofil.getProfilID() ==
	 * getAllNutzerprofile().get(i).getProfilID()) if
	 * (this.suchprofil.getKoerpergroessevon() >=
	 * getAllNutzerprofile().get(i).getKoerpergroesse()) if
	 * (this.suchprofil.getKoerpergroessebis() <=
	 * getAllNutzerprofile().get(i).getKoerpergroesse()) // heutiges Datum minus
	 * Geburtsdatum = Alter // if-Bedingung wie oben
	 * 
	 * if (this.suchprofil.getHaarfarbe().equals(getAllNutzerprofile().get(i).
	 * getHaarfarbe())) if
	 * (this.suchprofil.getRaucher().equals(getAllNutzerprofile().get(i).
	 * getRaucher())) if
	 * (this.suchprofil.getReligion().equals(getAllNutzerprofile().get(i).
	 * getReligion()))
	 * 
	 * // return Statement �berarbeiten! // Eventuell Array erstellen mit allen
	 * // �brigen Nutzerprofilen. return this.nutzerprofil;
	 * 
	 * } return null;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Override public List<Suchprofil> getAllSuchprofileFor() { // TODO
	 *           Auto-generated method stub return null; }
	 * 
	 * 
	 *           /*
	 *           ***************************************************************
	 *           ********** ** ABSCHNITT, Ende: Suchprofil
	 *           ***************************************************************
	 *           ********** **
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Sperrliste
	 * *************************************************************************
	 * **
	 */

	/**
	 * Nutzerprofil sperren
	 * 
	 * @return int sperrStatus
	 */

	public int sperreNutzerprofil(int nutzerprofilID, int FremdprofilID) throws IllegalArgumentException {

		int sperrStatus = this.sperrlisteMapper.insert(nutzerprofilID, FremdprofilID);

		if (sperrStatus == 1) {
			this.sperrlisteMapper.delete(nutzerprofilID, FremdprofilID);
		} else {
			this.sperrlisteMapper.insert(nutzerprofilID, FremdprofilID);
			this.merkzettelMapper.delete(nutzerprofilID, FremdprofilID);
		}

		return sperrStatus;
	}

	/**
	 * Nutzerprofil entsperren
	 */

	public void entsperreNutzerprofil(int SperrenderID, int GesperrterID) throws IllegalArgumentException {

		this.sperrlisteMapper.delete(SperrenderID, GesperrterID);

	}

	/**
	 * Sperrliste eines Nutzers löschen
	 */

	public void deleteSperrlisteOf(int nutzerprofil) throws IllegalArgumentException {

		this.sperrlisteMapper.deleteSperrlisteOf(nutzerprofil);

	}

	public ArrayList<Nutzerprofil> findBySperrenderID(int nutzerprofil) throws IllegalArgumentException {

		return this.sperrlisteMapper.findBySperrender(nutzerprofil);
	}

	public int pruefeSperrstatusFremdprofil(int nutzerprofilID, int fremdprofilID) {

		return this.sperrlisteMapper.pruefeSperrstatusFremdprofil(fremdprofilID, nutzerprofilID);

	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Sperrliste
	 * *************************************************************************
	 * **
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Merkzettel
	 * *************************************************************************
	 * **
	 */

	/**
	 * Nutzerprofil merken
	 */
	public int merkeNutzerprofil(int nutzerprofilID, int GemerkterID) throws IllegalArgumentException {

		// return this.merkzettelMapper.insert(nutzerprofilID, GemerkterID);

		int vermerkStatus = this.merkzettelMapper.pruefeVermerkstatus(nutzerprofilID, GemerkterID);

		if (vermerkStatus == 1) {
			this.merkzettelMapper.delete(nutzerprofilID, GemerkterID);
		} else {
			this.merkzettelMapper.insert(nutzerprofilID, GemerkterID);
		}

		return vermerkStatus;

	}

	/**
	 * Nutzerprofil von Merkzettel entfernen
	 */
	public void deleteNutzerprofilvonMerkliste(int MerkenderID, int GemerkteID) throws IllegalArgumentException {

		this.merkzettelMapper.delete(MerkenderID, GemerkteID);
	}

	/**
	 * Merkzettel eines Nutzers löschen
	 */

	public void deleteMerkzettelOf(int nutzerprofil) throws IllegalArgumentException {

		this.merkzettelMapper.deleteMerkzettelOf(nutzerprofil);
	}

	/**
	 * Merkzettel anhand der MerkenderID finden
	 */
	public ArrayList<Nutzerprofil> findByMerkenderID(int nutzerprofil) throws IllegalArgumentException {

		return this.merkzettelMapper.findByMerkenderID(nutzerprofil);
	}

	public int pruefeVermerkstatus(int nutzerprofilID, int fremdprofilID) {
		return this.merkzettelMapper.pruefeVermerkstatus(nutzerprofilID, fremdprofilID);

	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Merkzettel
	 * *************************************************************************
	 * **
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: BesuchteProfilListe
	 * *************************************************************************
	 * **
	 */

	/**
	 * @param besuchterID
	 * @param besuchteID
	 * @return besuchteProfilListeMapper
	 * @throws IllegalArgumentException
	 */
	public int besucheNutzerprofil(int besuchterID, int besuchteID) throws IllegalArgumentException {

		return besuchteProfilListeMapper.insert(besuchterID, besuchteID);
	}

	/**
	 * Nutzerpofil von BesuchteProfillListe entfernen
	 */
	@Override
	public void deleteNutzerprofilvonBesuchteProfilListe(BesuchteProfilListe besuchteProfilListe)
			throws IllegalArgumentException {
		besuchteProfilListe.getID();

		this.besuchteProfilListeMapper.delete(besuchteProfilListe);

	}

	/**
	 * Auslesen aller BesuchteProfilListe eines durch Fremdschlüssel
	 * (BesucherID) gegeben Nutzerprofils
	 */
	@Override
	public ArrayList<Nutzerprofil> findByBesucherID(int nutzerprofilID) throws IllegalArgumentException {

		return this.besuchteProfilListeMapper.findByBesucherID(nutzerprofilID);
	}

	/**
	 * BesuchteProfillListe eines Nutzers entfernen
	 */
	@Override
	public void deleteBesuchteProfilListeOf(Nutzerprofil nutzerprofil) throws IllegalArgumentException {
		nutzerprofil.getID();

		this.besuchteProfilListeMapper.deleteBesuchteProfilListeOf(nutzerprofil);

	}

	@Override
	public void besuchSetzen(int fremdprofilId) {
		// TODO Auto-generated method stub

	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: BesuchteProfilListe
	 * *************************************************************************
	 * **
	 */

	public Information insertInformation(Information information, int ProfilID, int EigenschaftID, String Information)
			throws IllegalArgumentException {
		Information info = new Information();
		info.setID(information.getID());

		return this.informationMapper.insertInformation(info, ProfilID, EigenschaftID, Information);
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Information
	 * *************************************************************************
	 * **
	 */

	/**
	 * @param info
	 * @param profilID
	 * @param eigenschaftID
	 * @throws IllegalArgumentException
	 */
	public void bearbeiteNutzerprofilInfo(String info,
			int profilID, int eigenschaftID) throws IllegalArgumentException {
//		Map<List<Information>, List<Beschreibung>> result = new HashMap<List<Information>, List<Beschreibung>>();
		
		this.informationMapper.bearbeiteNutzerprofilInfo(info, profilID, eigenschaftID);
		
				

	}

	/**
	 * Eigenschafte und deren Beschreibung
	 * 
	 * @param nutzerprofilID
	 * @return result1
	 * @throws IllegalArgumentException
	 * @throws IllegalArgumentExceptio
	 */
	public Map<List<Beschreibung>, List<Information>> showProfilAllEigBeschreibung(int nutzerprofilID)
			throws IllegalArgumentException {
		Map<List<Beschreibung>, List<Information>> result = new HashMap<List<Beschreibung>, List<Information>>();

		List<Beschreibung> listEig = eigenschaftMapper.findEigenschaftAllByProfil(nutzerprofilID);
		List<Information> listInfo = informationMapper.findAllInfoByProfil(nutzerprofilID);

		result.put(listEig, listInfo);
		return result;
	}
	
	/**
	 * @param nutzerprofilID
	 * @return result
	 * @throws IllegalArgumentException
	 */
	public Map<List<Beschreibung>, List<Information>> showProfilEigBeschreibung(int nutzerprofilID)
			throws IllegalArgumentException {
		Map<List<Beschreibung>, List<Information>> result = new HashMap<List<Beschreibung>, List<Information>>();

		List<Beschreibung> listEig = eigenschaftMapper.findEigenschaftByProfil(nutzerprofilID);
		List<Information> listInfo = informationMapper.findInfoByProfil(nutzerprofilID);

		result.put(listEig, listInfo);
		return result;
	}

	/**
	 * Eigenschafte und deren Auswahl
	 * 
	 * @param nutzerprofilID
	 * @return result1
	 * @throws IllegalArgumentException
	 * @throws IllegalArgumentExceptio
	 */
	public Map<List<Option>, List<Information>> showProfilEigAuswahl(int nutzerprofilID)
			throws IllegalArgumentException {
		Map<List<Option>, List<Information>> result1 = new HashMap<List<Option>, List<Information>>();

		List<Option> listEig = eigenschaftMapper.findEigenschaftauswahlByProfil(nutzerprofilID);
		List<Information> listInfo = informationMapper.findAuswahlInfoByProfil(nutzerprofilID);

		result1.put(listEig, listInfo);
		return result1;

	}

	/**
	 * @param profilID
	 * @return result1
	 * @throws IllegalArgumentException
	 * @throws IllegalArgumentExceptio
	 */
	public Map<List<Option>, List<Option>> getAllProfilAuswahlEig(int eigenschaftsID) throws IllegalArgumentException {
		Map<List<Option>, List<Option>> result1 = new HashMap<List<Option>, List<Option>>();

		List<Option> listRO = optionMapper.readOption();
		List<Option> listROA = optionMapper.readOptionAuswahl(eigenschaftsID);

		result1.put(listRO, listROA);
		return result1;

	}
	
	/**
	 * @param profilID
	 * @throws IllegalArgumentException
	 */
	public void deleteAllNutzerInfo (int profilID) throws IllegalArgumentException {
		
		this.informationMapper.deleteAllNutzerInfo(profilID);
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Information
	 * *************************************************************************
	 * **
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Eigenschaft
	 * *************************************************************************
	 * **
	 */

	/**
	 * Erstellung einer Beschreibung
	 * 
	 * Eine Beschreibung wird angelegt und in der Datenbank gespeichert.
	 * 
	 */
	@Override
	public Beschreibung createBeschreibung(String beschreibung) throws IllegalArgumentException {

		Beschreibung b = new Beschreibung();
		// b.setBeschreibung(beschreibung);

		return this.beschreibungMapper.insert(b);

	}

	public ArrayList<Beschreibung> readEigenschaft() throws IllegalArgumentException {

		return eigenschaftMapper.readEigenschaft();
	}

	/**
	 * @return optionMapper
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> readOption() throws IllegalArgumentException {
		return optionMapper.readOption();
	}

	/**
	 * @return auswahloptionMapper
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> readOptionAuswahl(int eigenschaftsID) throws IllegalArgumentException {
		return optionMapper.readOptionAuswahl(eigenschaftsID);
	}

	/**
	 * @param profilID
	 * @return this.optionMapper.findOptionByProfil(profilID)
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> findOptionByProfil(int profilID) throws IllegalArgumentException{
		return this.optionMapper.findOptionByProfil(profilID);
	}
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Eigenschaft
	 * *************************************************************************
	 * **
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Aehnlichkeitsmaß
	 * *************************************************************************
	 * **
	 */

	/**
	 * Berechnung Aehnlichkeitsmass
	 * 
	 * @author Dominik Sasse
	 */

	// public float berechneAehnlichkeitsmass() {

	/**
	 * <<<<<<< HEAD Ben�tigt: - eigene ProfilID - ArrayList mit allen Nutzern -
	 * equals-Methode f�r String-Vergleich - Ausgabe des �hnlichkeitsmass f�r
	 * die einzelnen Profil- Vergleiche ======= Ben�tigt: - eigene ProfilID -
	 * ArrayList mit allen Nutzern - equals-Methode f�r String-Vergleich -
	 * Ausgabe des �hnlichkeitsmass f�r die einzelnen Profil- Vergleiche >>>>>>>
	 * refs/heads/master
	 */

	// nutzerprofil.getNutzerprofilID();

	// ArrayList mit allen Nutzerprofilen

	// getAllNutzerprofile();

	/**
	 * Variable zum Zaehlen der Uebereinstimmungen mit einem anderen Profil
	 * 
	 * @author Dominik Sasse
	 * 
	 */
	float zwischenErgebnis = 0.0f;

	/**
	 * Hier wird eine ArrayList angelegt in welcher die ausgerechneten
	 * Aehnlichkeitsmasse gespeichert werden sollen.
	 * 
	 * @author Dominik Sasse
	 * 
	 */

	ArrayList<Float> aehnlichkeitsmass = new ArrayList<Float>();

	/**
	 * for (int i = 0; i < getAllNutzerprofile().size(); i++) {
	 * 
	 * if (this.nutzerprofil.getProfilID() ==
	 * getAllNutzerprofile().get(i).getProfilID()) { i++; }
	 * 
	 * else {
	 * 
	 * // For-Schleife mit einem Array in welchem alle Eigenschaften // sind? //
	 * Dann könnte auch man das Ergebnis mit durch die Länge des // Arrays
	 * teilen.
	 * 
	 * if (this.nutzerprofil.getHaarfarbe().equals(getAllNutzerprofile().get(i).
	 * getHaarfarbe())) { zwischenErgebnis = +1; } else { zwischenErgebnis = +0;
	 * 
	 * } if (this.nutzerprofil.getRaucher().equals(getAllNutzerprofile().get(i).
	 * getRaucher())) { zwischenErgebnis = +1; } else { zwischenErgebnis = +0;
	 * 
	 * } if
	 * (this.nutzerprofil.getReligion().equals(getAllNutzerprofile().get(i).
	 * getReligion())) { zwischenErgebnis = +1; } else { zwischenErgebnis = +0;
	 * }
	 * 
	 * float ergebnis = 3 / zwischenErgebnis * 100;
	 * 
	 * // Hier wird noch ein Zwischenschritt benötigt in welchem das //
	 * Ähnlichkeitsmaß dem entsprechenden // Profil zuordnet.
	 * 
	 * aehnlichkeitsmass.add(ergebnis); }
	 * 
	 * } return 0;
	 * 
	 * }
	 **/

	@Override
	public int berechneAehnlichkeitSpFor(int suchprofilId, int fremdprofilId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void aehnlichkeitSetzenSp(int suchprofilId, String suchprofilName, int fremdprofilId, int aehnlichkeit) {
		// TODO Auto-generated method stub

	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Aehnlichkeitsmaß
	 * *************************************************************************
	 * **
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Partnervorschlaege
	 * *************************************************************************
	 * **
	 */

	@Override
	public List<Nutzerprofil> getGeordnetePartnervorschlaegeSp(String suchprofilName) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param nutzerprofil
	 * @return double
	 * @throws IllegalArgumentException
	 * 
	 */

	public int getPartnervorschlaegeNp(int profilID) throws IllegalArgumentException {
		
		Nutzerprofil referenzprofil = nutzerprofilMapper.findByNutzerprofilId(profilID);
		List<Nutzerprofil> vergleichsprofile = nutzerprofilMapper.findUnangeseheneNutzerprofileByID(profilID);
		
		// Anzahl Profilattribute & Infos von P1 (default = 5, weil auf 5
		// Profilattribute abgeprüft wird)
		int verglfremdesprofil = 6;

		// Anazhl der gemeinsamen Profilattribute & Infos
		int aehnlichkeit = 0;
		// Prüfung der zu vergleichenden Profilattribute
		// if
		for (Nutzerprofil nutzerprofil : vergleichsprofile){
			
		if (referenzprofil.getGeburtsdatum().equals(nutzerprofil.getGeburtsdatum()))
			aehnlichkeit++;
		if (referenzprofil.getKoerpergroesse() == nutzerprofil.getKoerpergroesse())
			aehnlichkeit++;
		if (referenzprofil.getHaarfarbe().equals(nutzerprofil.getHaarfarbe()))
			aehnlichkeit++;
		if (referenzprofil.getReligion().equals(nutzerprofil.getReligion()))
			aehnlichkeit++;
		if (referenzprofil.getGeschlecht().equals(nutzerprofil.getGeschlecht()))
			aehnlichkeit++;
		if (referenzprofil.getRaucher().equals(nutzerprofil.getRaucher()))
			aehnlichkeit++;
		}
	
		// Ergebnis im richtigen Format zurückliefern
		return (aehnlichkeit / verglfremdesprofil * 100);
		
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Partnervorschlaege
	 * *************************************************************************
	 * **
	 */



}
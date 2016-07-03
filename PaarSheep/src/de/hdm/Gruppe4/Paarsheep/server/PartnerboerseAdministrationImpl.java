package de.hdm.Gruppe4.Paarsheep.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.Gruppe4.Paarsheep.server.db.*;
import de.hdm.Gruppe4.Paarsheep.shared.*;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Implementierungsklasse des Interface PartnerboerseAdministration.
 * 
 * @author An Dang
 * @author Tino Hauler
 * @author Dominik Sasse
 * @author Manuel Weiler
 * @author Marcel pleyer
 * @author Florian Maurer
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
	 * *************************************************************************
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
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Initialisierung
	 * *************************************************************************
	 */


	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Nutzerprofil
	 * *************************************************************************
	 * **
	 */

	/**
	 * Nutzerprofil anlegen.
	 */

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
	 * Nutzerprofil bearbeiten.
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
	
	/**
	 * Login Status Check
	 */

	public Nutzerprofil checkStatus(Nutzerprofil loginInfo) {
		return this.nutzerprofilMapper.checkStatus(loginInfo);
	}
	
	/**
	 * Nutzerlöschen
	 */
	
	public void deleteNutzerprofil(int nutzerprofilID){
		this.nutzerprofilMapper.deleteNutzerprofil(nutzerprofilID);
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
	
	/**
	 * Auslesen Fremdes Profil
	 */

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
	 * Auslesen alle Unangesehne Profile
	 * @param profilId
	 * @return df
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Nutzerprofil> getUnangeseheneNutzerprofile(int profilId) throws IllegalArgumentException {
		ArrayList<Nutzerprofil> result = new ArrayList<Nutzerprofil>();
		result = nutzerprofilMapper.findUnangeseheneNutzerprofileByID(profilId);
		return result;
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

	/*
  	 ***************************************************************
  	 ************ ABSCHNITT, Ende: Suchprofil
	 ***************************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Sperrliste
	 * *************************************************************************
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
	
	/**
	 * Sperrender auslesen
	 */
	
	public ArrayList<Nutzerprofil> findBySperrenderID(int nutzerprofil) throws IllegalArgumentException {
		return this.sperrlisteMapper.findBySperrender(nutzerprofil);
	}
	
	/**
	 * Sperrstatus prüfen
	 */
	
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

	/**
	 * Vermerkstatus Prüfen
	 */
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
	 * Vermerk falls ein Nutzer besuchte wurde
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
	public void deleteNutzerprofilvonBesuchteProfilListe(BesuchteProfilListe besuchteProfilListe)
			throws IllegalArgumentException {
		besuchteProfilListe.getID();
		this.besuchteProfilListeMapper.delete(besuchteProfilListe);
	}

	/**
	 * Auslesen aller BesuchteProfilListe eines durch Fremdschlüssel
	 * (BesucherID) gegeben Nutzerprofils
	 */
	public ArrayList<BesuchteProfilListe> findByBesucherID(int nutzerprofilID) throws IllegalArgumentException {
		return this.besuchteProfilListeMapper.findByBesucherID(nutzerprofilID);
	}

	/**
	 * BesuchteProfillListe eines Nutzers entfernen
	 */
	public void deleteBesuchteProfilListeOf(Nutzerprofil nutzerprofil) throws IllegalArgumentException {
		nutzerprofil.getID();
		this.besuchteProfilListeMapper.deleteBesuchteProfilListeOf(nutzerprofil);
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: BesuchteProfilListe
	 * *************************************************************************
	 * **
	 */
	
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Information
	 * *************************************************************************
	 * **
	 */
	
	/**
	 * Nutzerinfo Löschen
	 * 
	 * @param profilID
	 * @param eigID
	 * @throws IllegalArgumentException
	 */
	public void deleteNutzerInfo(int profilID, int eigID)throws IllegalArgumentException {
		this.informationMapper.deleteNutzerInfo(profilID, eigID);
	}
	
	/**
	 * Nutzerinformation hinzufügen
	 */
	public Information insertInformation(Information information, int ProfilID, int EigenschaftID, String Information)
			throws IllegalArgumentException {
		Information info = new Information();
		info.setID(information.getID());

		return this.informationMapper.insertInformation(info, ProfilID, EigenschaftID, Information);
	}

	/**
	 * Alle Infos zu einem Nutzerprofil auslesen
	 * 
	 * @param profilID
	 * @return this.informationMapper.findAllInfoByProfil(profilID)
	 * @throws IllegalArgumentException
	 */

	public ArrayList<Information> findAllInfoByProfil(int profilID) throws IllegalArgumentException {
		return this.informationMapper.findAllInfoByProfil(profilID);
	}

	/**
	 * Nutzerprofilinfos bearbeiten
	 * 
	 * @param info
	 * @param profilID
	 * @param eigenschaftID
	 * @throws IllegalArgumentException
	 */
	public void bearbeiteNutzerprofilInfo(String info, int profilID, int eigenschaftID)
			throws IllegalArgumentException {
		this.informationMapper.bearbeiteNutzerprofilInfo(info, profilID, eigenschaftID);
	}

	/**
	 * Alle Eigenschafte und deren Beschreibung 
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
	 *	Nutzer Eigenschafte und deren Beschreibung 
	 * 
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
	 * Optionsbezeichnung und dessen Erlaeuterung
	 * 
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
	 * Alle Nutzer infos löschen
	 * 
	 * @param profilID
	 * @throws IllegalArgumentException
	 */
	public void deleteAllNutzerInfo(int profilID) throws IllegalArgumentException {
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
	
	public Beschreibung createBeschreibung(String beschreibung) throws IllegalArgumentException {
		Beschreibung b = new Beschreibung();
		return this.beschreibungMapper.insert(b);
	}
	
	/**
	 * Beschreibungseigenschaften auslesen
	 */
	public ArrayList<Beschreibung> readEigenschaft() throws IllegalArgumentException {
		return eigenschaftMapper.readEigenschaft();
	}

	/**
	 * Optionseigenschaft auslesen
	 * 
	 * @return optionMapper
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> readOption() throws IllegalArgumentException {
		return optionMapper.readOption();
	}

	/**
	 * Auswahloption auslesen
	 * @return auswahloptionMapper
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> readOptionAuswahl(int eigenschaftsID) throws IllegalArgumentException {
		return optionMapper.readOptionAuswahl(eigenschaftsID);
	}

	/**
	 * Option eines Nutzers Auslesen
	 * 
	 * @param profilID
	 * @return this.optionMapper.findOptionByProfil(profilID)
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Option> findOptionByProfil(int profilID) throws IllegalArgumentException {
		return this.optionMapper.findOptionByProfil(profilID);
	}
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Eigenschaft
	 * *************************************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Aehnlichkeitsmaß
	 * *************************************************************************
	 */


	/**
	 * Aehnlichkeitsmass Berechnung für Ungesehne Nutzerprofile
	 * 
	 * @param eigenesProfil
	 * @param referenzProfil
	 * @return aehnlichkeit * (100 /verglprofil)
	 */
	public int getAehnlichkeitvonProfilen(Nutzerprofil eigenesProfil, Nutzerprofil referenzProfil) {
		// Variablen zur Berechnung
		int aehnlichkeit = 0;
		int verglprofil = 6;
		//Vergleich der Werte
		if (eigenesProfil.getGeburtsdatum().equals(referenzProfil.getGeburtsdatum()))
			aehnlichkeit++;
		if (eigenesProfil.getKoerpergroesse() == referenzProfil.getKoerpergroesse())
			aehnlichkeit++;
		if (eigenesProfil.getHaarfarbe().equals(referenzProfil.getHaarfarbe()))
			aehnlichkeit++;
		if (eigenesProfil.getReligion().equals(referenzProfil.getReligion()))
			aehnlichkeit++;
		if (eigenesProfil.getGeschlecht().equals(referenzProfil.getGeschlecht()))
			aehnlichkeit++;
		if (eigenesProfil.getRaucher().equals(referenzProfil.getRaucher()))
			aehnlichkeit++;

		// Alle Infos
		ArrayList<Information> infosP1 = findAllInfoByProfil(referenzProfil.getProfilID());
		// Anzahl Infos addieren
		verglprofil += infosP1.size();
		// Alle Infos des infoRefrenz durchlafen
		for (Information infoRefrenz : infosP1) {
			// Infos des infoNutzer
			ArrayList<Information> infosP2 = findAllInfoByProfil(eigenesProfil.getProfilID());
			for (Information infoNutzer : infosP2) {
				// Gleicher FreeText oder gleiche Selection wurde gefunden
				if (infoNutzer.getID() == infoRefrenz.getID()) {
					if (infoNutzer.getInformation().equals(infoRefrenz.getInformation())) {
						aehnlichkeit++;
					}
				}
			}
		}
		return aehnlichkeit * (100 / verglprofil);
	}

	/**
	 * Aehnlichkeitsmass Berechnung mit dem Suchprofil
	 * 
	 * @param suchprofil
	 * @param referenzProfil
	 * @return aehnlichkeit * (100 /verglprofil)
	 */

	public int getAehnlichkeitvonSuchprofilen(Suchprofil suchprofil, Nutzerprofil referenzProfil) {
			// Variablen zur Berechnung
			int aehnlichkeit = 0;
			int verglprofil = 6;
			//Vergleich der Werte
			if (suchprofil.getKoerpergroesse() == referenzProfil.getKoerpergroesse())
				aehnlichkeit++;
			if (suchprofil.getHaarfarbe().equals(referenzProfil.getHaarfarbe()))
				aehnlichkeit++;
			if (suchprofil.getReligion().equals(referenzProfil.getReligion()))
				aehnlichkeit++;
			if (suchprofil.getGeschlecht().equals(referenzProfil.getGeschlecht()))
				aehnlichkeit++;
			if (suchprofil.getRaucher().equals(referenzProfil.getRaucher()))
				aehnlichkeit++;

			// Alle Infos
			ArrayList<Information> infosP1 = findAllInfoByProfil(referenzProfil.getProfilID());
			// Anzahl Infos addieren
			verglprofil += infosP1.size();
			// Alle Infos des infoRefrenz durchlafen
			for (Information infoRefrenz : infosP1) {
				// Infos des infoNutzer
				ArrayList<Information> infosP2 = findAllInfoByProfil(suchprofil.getProfilID());
				for (Information infoNutzer : infosP2) {
					// Gleicher FreeText oder gleiche Selection wurde gefunden
					if (infoNutzer.getID() == infoRefrenz.getID()) {
						if (infoNutzer.getInformation().equals(infoRefrenz.getInformation())) {
							aehnlichkeit++;
						}
					}
				}
			}
			return aehnlichkeit * (100 / verglprofil);
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
	
	/**
	 * Ungesehne Partnervorschläge nach aehnlichkeitsmass sortiert 
	 * 
	 * @param nutzerprofil
	 * @return int
	 * @throws IllegalArgumentException
	 * 
	 */
	public ArrayList<Aehnlichkeitsmass> getPartnervorschlaegeNp(Nutzerprofil np) throws IllegalArgumentException {

		//Hier werden alle Profile gespeichert
		ArrayList<Nutzerprofil> vergleichsprofile = this.nutzerprofilMapper.findUnangeseheneNutzerprofileByID(np.getID());
		//Das ist die ErgebnisArrayList für die Partnervorschläge
		ArrayList<Aehnlichkeitsmass> result = new ArrayList<Aehnlichkeitsmass>();
		
		Nutzerprofil eigenesProfil = new Nutzerprofil();
		eigenesProfil = this.getNutzerprofilById(np.getProfilID());
		//Hier wird das Ergebnis vorbereitet
		for (Nutzerprofil referenzProfil : vergleichsprofile) {
			//Neuen Partnervorschlag anlegen & befüllen
			Aehnlichkeitsmass aehnlichkeitsmass = new Aehnlichkeitsmass();
			//Hier wird das Ähnlichkeitsmaß berechnet und eingefügt
			aehnlichkeitsmass.setAehnlichkeitsmass(getAehnlichkeitvonProfilen(eigenesProfil, referenzProfil));
			// Fremdeprofil wird befüöt
			aehnlichkeitsmass.setFremdprofil(referenzProfil);
			// Ergebnis kommt in Aehnlichkeitsmass
			result.add(aehnlichkeitsmass);
		}
		//sortieren
		bubbleSort(result);
		return result;
	}
	
	/**
	 * Partnervorschläge mit dem Suchprofil nach aehnlichkeitsmass sortiert 
	 * 
	 * @param nutzerprofil
	 * @return double
	 * @throws IllegalArgumentException
	 * 
	 */
	public ArrayList<Aehnlichkeitsmass> getPartnervorschlaegeSp(Nutzerprofil np, Suchprofil sp) {
		
		//Hier werden alle Profile gespeichert
		ArrayList<Nutzerprofil> vergleichsprofile = this.nutzerprofilMapper.findAllNutzerprofil(np.getProfilID());
		//Das ist die ArrayList für die Partnervorschläge
		ArrayList<Aehnlichkeitsmass> result = new ArrayList<Aehnlichkeitsmass>();
		//Hier wird das Ergebnis vorbereitet
		for (Nutzerprofil referenzProfil : vergleichsprofile) {
				//Neuen Partnervorschlag anlegen & befüllen
				Aehnlichkeitsmass tmp = new Aehnlichkeitsmass();
				tmp.setSuchprofil(sp);
				//Hier wird das Ähnlichkeitsmaß berechnet und eingefügt
				tmp.setAehnlichkeitsmass(getAehnlichkeitvonSuchprofilen(sp, referenzProfil));
				//Partnervorschlag zur ArrayLis hinzufügen
				result.add(tmp);
		}	
		//sortieren
		bubbleSort(result);
		return result;
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Partnervorschlaege
	 * *************************************************************************
	 * **
	 */
	
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Anfang: Sortierung
	 * *************************************************************************
	 * **
	 */
	/**
	 * @param aehnlichkeitsmass
	 * @throws IllegalArgumentException
	 */
	public void bubbleSort(ArrayList<Aehnlichkeitsmass> aehnlichkeitsmass) throws IllegalArgumentException
	{
		//Implementierung eines Bubblesort-Algorithmus
        boolean swapped = true;
        int length = aehnlichkeitsmass.size();

        //Äußere Schleife: Wurde ein Swap entdeckt?
        while (swapped) {
            swapped = false;		
            for(int i=0; i < length - 1; i++)
            {
            	if (aehnlichkeitsmass.get(i).getAehnlichkeitsmass() < aehnlichkeitsmass.get(i+1).getAehnlichkeitsmass())
                {
            		Aehnlichkeitsmass temp = aehnlichkeitsmass.get(i);
                    aehnlichkeitsmass.set(i,aehnlichkeitsmass.get(i+1));
                    aehnlichkeitsmass.set(i+1,temp);
                    swapped = true;		
                }
            }
        }	
	}
}
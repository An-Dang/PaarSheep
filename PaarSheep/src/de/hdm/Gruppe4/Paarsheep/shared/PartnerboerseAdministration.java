package de.hdm.Gruppe4.Paarsheep.shared;


import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

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

	public void setNutzerprofil(Nutzerprofil p) throws IllegalArgumentException;

	public Nutzerprofil getNutzerprofil(int id) throws IllegalArgumentException;

	public Nutzerprofil getNutzerprofil(Nutzerprofil Nutzerprofil_ProfilID) throws IllegalArgumentException;

	public ArrayList<Nutzerprofil> getAllNutzerprofile() throws IllegalArgumentException;

	/**
	 * Ein Nutzerprofil anlegen.
	 * 
	 * @author Dominik Sasse
	 */
	public Nutzerprofil createNutzerprofil(Date geburtsdatum, String emailAddress, String vorname, String nachname, String geschlecht,
			String religion, int koerpergroesse, String haarfarbe, String raucher) throws IllegalArgumentException;

	public Nutzerprofil bearbeiteNutzerprofil(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	
	
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
	public Auswahl createAuswahl(Auswahloption a) throws IllegalArgumentException;

	/**
	 * Eine Auswahloption anlegen.
	 * 
	 * @author Dominik
	 */
	public Auswahloption createAuswahloption(String optionsBezeichnung) throws IllegalArgumentException;

	/**
	 * Eine Beschreibung erstellen
	 * 
	 * @author Dominik Sasse
	 * 
	 */
	public Beschreibung createBeschreibung(String beschreibung) throws IllegalArgumentException;

	/**
	 * Ein Suchprofil anlegen.
	 * 
	 * @author Dominik Sasse
	 */
	public Suchprofil createSuchprofil(int koerpergroessevon, int koerpergroessbis,int altervon, int alterbis, 
			String raucher, String religion, String haarfarbe, String geschlecht) throws IllegalArgumentException;

	/**
	 * ABSCHNITT Beginn Merkzettel 
	 * @author An Dang
	 */
	public Merkzettel merkeNutzerprofil(int MerkzettelID, int MerkenderID, int GemerkterID)
			throws IllegalArgumentException;

	public void deleteNutzerprofilvonMerkliste(int nutzerprofilID) throws IllegalArgumentException;

	public void deleteMerkzettelOf(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	
	public ArrayList<Nutzerprofil> findByMerkenderID(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	/**
	 * ABSCHNITT Ende Merkzettel 
	 */

	/**
	 * ABSCHNITT Beginn Kontaktsperrliste 
	 * @author An Dang
	 */
	public Sperrliste sperreNutzerprofil(int SperrlisteID, int SperrenderID, int GesperrterID ) throws IllegalArgumentException;

	public void entsperreNutzerprofil(int SperrenderID, int GesperrterID) throws IllegalArgumentException;
	
	public void deleteSperrlisteOf(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	
	public ArrayList<Nutzerprofil> findBySperrenderID(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	/**
	 * ABSCHNITT Ende Merkzettel 
	 */

	
	/**
	 * Speichern des Nutzerprofils
	 * 
	 * @author Dominik Sasse
	 * 
	 */
	
	/**
	 * Profil zu BesuchteprofilListe hinzufuegen
	 * 
	 * @author Tino Hauler
	 * 
	 */
	public BesuchteProfilListe besucheNutzerprofil(int BesuchteProfilListeID, int BesuchteID, int BesucherID)
			throws IllegalArgumentException;
	

	/**
	 * Profil von BesuchteProfilListe entfernen
	 * 
	 * @author Tino Hauler
	 * 
	 */
	public void deleteNutzerprofilvonBesuchteProfilListe(BesuchteProfilListe besuchteProfilListe) throws IllegalArgumentException;
	
	
	/**
	 * Entfernen der BesuchteProfilListe, wenn der Nutzer gelöscht wird.
	 * 
	 * @author Tino Hauler
	 * 
	 */

	public void deleteBesuchteProfilListeOf(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	
	
	/**
	 *Auslesen aller besuchten Profile eines durch Fremdschlüssel
	 * (BesucherID) gegebenen Nutzerprofils
	 * 
	 * @author Tino Hauler
	 */
	
	public ArrayList<Nutzerprofil> findByBesucherID(int nutzerprofil) throws IllegalArgumentException;


	
	void saveNutzerprofil(Nutzerprofil nutzerprofil) throws IllegalArgumentException;

	/**
	 * Speichern des Suchprofils
	 * 
	 * @author Dominik Sasse
	 */
	void saveSuchprofil(Suchprofil suchprofil) throws IllegalArgumentException;



}

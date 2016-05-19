package de.hdm.Gruppe4.Paarsheep.shared;

import java.sql.Date;
import java.util.ArrayList;

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
	public Nutzerprofil createNutzerprofil(String emailAddress, String vorname, String nachname, String geschlecht,
			String religion, int koerpergroesse, String haarfarbe, String raucher) throws IllegalArgumentException;

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
	public Suchprofil createSuchprofil(int altervon, int alterbis, int koerpergroessevon, int koerpergroessbis,
			String raucher, String religion, String haarfarbe, String geschlecht) throws IllegalArgumentException;

	/**
	 * Profil zu Merkliste hinzufuegen
	 * 
	 * @author An Dang
	 * @author Dominik Sasse
	 * 
	 */
	public Merkzettel merkeNutzerprofil(int MerkzettelID, int MerkenderID, int GemerkterID)
			throws IllegalArgumentException;

	/**
	 * Profil von Merkliste entfernen
	 * 
	 * @author Dominik Sasse
	 * 
	 */
	public void deleteNutzerprofilvonMerkliste(Merkzettel merkzettel) throws IllegalArgumentException;

	/**
	 * Entfernen der Merkliste, wenn der Nutzer gelöscht wird.
	 * 
	 * @author An Dang
	 * 
	 */

	public void deleteMerkzettelOf(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	
	/**
	 *Auslesen aller Merkzettel 
	 * 
	 * @author An Dang
	 */
	
	public ArrayList<Nutzerprofil> findByMerkenderID(int nutzerprofil) throws IllegalArgumentException;

	/**
	 * Profil sperren
	 * 
	 * @author An Dang
	 * @author Dominik Sasse
	 * 
	 */
	public Sperrliste sperreNutzerprofil(int SperrlisteID, int SperrenderID, int GesperrterID ) throws IllegalArgumentException;

	/**
	 * Sperre aufheben/ Nutzerprofil von Sperrliste entfernen
	 * 
	 * @author An Dang
	 * @author Dominik Sasse
	 * 
	 */

	public void entsperreNutzerprofil(Sperrliste sperrliste) throws IllegalArgumentException;
	
	/**
	 * Entfernen der Sperrliste, wenn der Nutzer gelöscht wird.
	 * 
	 * @author An Dang
	 * 
	 */
	
	public void deleteSperrlisteOf(Nutzerprofil nutzerprofil) throws IllegalArgumentException;
	
	/**
	 * Auslesen aller Gesperrten Nutzerprofile
	 * 
	 * @author An Dang
	 * @return 
	 */
	
	public ArrayList<Nutzerprofil> findBySperrenderID(int nutzerprofil) throws IllegalArgumentException;

	/**
	 * Speichern des Nutzerprofils
	 * 
	 * @author Dominik Sasse
	 * 
	 */

	void saveNutzerprofil(Nutzerprofil nutzerprofil) throws IllegalArgumentException;

	/**
	 * Speichern des Suchprofils
	 * 
	 * @author Dominik Sasse
	 */
	void saveSuchprofil(Suchprofil suchprofil) throws IllegalArgumentException;

	public BesuchteProfilListe besucheNutzerprofil(int ProfilID) throws IllegalArgumentException;

}

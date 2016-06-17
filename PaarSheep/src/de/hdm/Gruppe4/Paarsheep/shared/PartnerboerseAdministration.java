package de.hdm.Gruppe4.Paarsheep.shared;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public ArrayList<Nutzerprofil> getAllNutzerprofile() throws IllegalArgumentException;

	/**
	 * Ein Nutzerprofil anlegen.
	 * 
	 * @author Dominik Sasse
	 */
	
	//Für die Eigenschaften
	
	public ArrayList<Beschreibung> readBeschreibungen() throws IllegalArgumentException;
	
	
	//-------------------------------------------------------------------------
	
	
	
	public Nutzerprofil createNutzerprofil(Date geburtsdatum, String emailAddress, String vorname, String nachname, String geschlecht,
			String religion, int koerpergroesse, String haarfarbe, String raucher) throws IllegalArgumentException;

	public void bearbeiteNutzerprofil(int profilId, String vorname, String nachname, String geschlecht, Date geburtsdatum,
			int koerpergroesse, String haarfarbe, String raucher, String religion) throws IllegalArgumentException;
	
	
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
	//public Auswahl createAuswahl(Auswahloption a) throws IllegalArgumentException;

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
	 * Erstellen eines Suchprofils
	 * 
	 * @author Tino Hauler
	 */
	public Suchprofil insertSuchprofil(int profilid, String suchprofilname, String geschlecht,
			String raucher, String haarfarbe, String religion, int koerpergroesse) throws IllegalArgumentException;
	
	/**
	 * Bearbeiten des Suchprofils
	 * 
	 * @author Tino Hauler
	 */
	void updateSuchprofil(int profilid, int suchprofilid, String suchprofilname, String geschlecht, String raucher, String haarfarbe,
			String religion, int koerpergroesse) throws IllegalArgumentException;
	
	/**
	 * Suchprofil loeschen.
	 */
	public void deleteSuchprofil(int nutzerprofilId, String suchprofilName) throws IllegalArgumentException;
	


	
	/**
	 * Alle Suchprofile eines Nutzers ausgeben.
	 */
	public ArrayList<Suchprofil> findSuchprofilByNutzerID(int nutzerprofilid) throws IllegalArgumentException;
	
	
	/**
	 * Alle Suchprofile eines Nutzers ausgeben.
	 */
	public Suchprofil findSuchprofilBySuchprofilID(int suchprofilId) throws IllegalArgumentException;
	
	/**
	 * Alle Suchprofile des Suchprofilnamens ausgeben.
	 */
	
	Suchprofil findSuchprofiByName(int nutzerprofilid, String suchprofilname) throws Exception;
	
	
	/**
	 * ABSCHNITT Beginn Merkzettel 
	 * @author An Dang
	 */
	public void merkeNutzerprofil(Nutzerprofil nutzerprofilID, int GemerkterID) throws IllegalArgumentException;

	public void deleteNutzerprofilvonMerkliste (int MerkenderID, int GemerkteID) throws IllegalArgumentException;

	public void deleteMerkzettelOf(int nutzerprofil) throws IllegalArgumentException;
	
	public ArrayList<Nutzerprofil> findByMerkenderID(int nutzerprofil) throws IllegalArgumentException;
	/**
	 * ABSCHNITT Ende Merkzettel 
	 */

	/**
	 * ABSCHNITT Beginn Kontaktsperrliste 
	 * @author An Dang
	 */
	public void sperreNutzerprofil(Nutzerprofil nutzerprofilID, int FremdprofilID) throws IllegalArgumentException;

	public void entsperreNutzerprofil(int SperrenderID, int GesperrterID) throws IllegalArgumentException;
	
	public void deleteSperrlisteOf(int nutzerprofil) throws IllegalArgumentException;
	
	public ArrayList<Nutzerprofil> findBySperrenderID(int nutzerprofil) throws IllegalArgumentException;
	/**
	 * ABSCHNITT Ende Merkzettel 
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


	
	void saveNutzerprofil(int profilId, String vorname, String nachname, String geschlecht, Date geburtsdatum,
			String haarfarbe, int koerpergroesse, String raucher, String religion) throws IllegalArgumentException;



	Nutzerprofil getNutzerprofilById(int profilID) throws IllegalArgumentException;
	
	

	List<Nutzerprofil> getNutzerprofileOhneGesetzteSperrung();

	int berechneAehnlichkeitSpFor(int suchprofilId, int fremdprofilId);

	void aehnlichkeitSetzenSp(int suchprofilId, String suchprofilName, int fremdprofilId, int aehnlichkeit);

	void besuchSetzen(int fremdprofilId);

	List<Nutzerprofil> getGeordnetePartnervorschlaegeNp();

	int pruefeSperrstatusFremdprofil(int fremdprofilId);

	int pruefeVermerkstatus(int fremdprofilId);

	int vermerkstatusAendern(int fremdprofilId);

	int sperrstatusAendern(int fremdprofilId);

	List<Nutzerprofil> getGeordnetePartnervorschlaegeSp(String suchprofilName) throws IllegalArgumentException;

	Nutzerprofil getFremdesProfilByID(int fremdprofilID) throws IllegalArgumentException;


}

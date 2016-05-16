package de.hdm.Gruppe4.Paarsheep.server;

import java.sql.Date;
import java.text.*;
import java.util.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.Gruppe4.Paarsheep.server.*;
import de.hdm.Gruppe4.Paarsheep.server.db.*;
import de.hdm.Gruppe4.Paarsheep.shared.*;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

@SuppressWarnings("serial")
public class PartnerboerseAdministrationImpl extends RemoteServiceServlet implements PartnerboerseAdministration {

	private AuswahlMapper auswahlMapper = null;
	private AuswahloptionMapper auswahloptionMapper = null;
	private BeschreibungMapper beschreibungMapper = null;
	private BesuchteProfilListeMapper besuchteProfilListeMapper = null;
	private EigenschaftMapper eigenschaftMapper = null;
	private InformationMapper informationMapper = null;
	private MerkzettelMapper merkzettelMapper = null;
	private NutzerprofilMapper nutzerprofilMapper = null;
	private ProfilMapper profilMapper = null;
	private SperrlisteMapper sperrlisteMapper = null;
	private SuchprofilMapper suchprofilMapper = null;

	private Nutzerprofil nutzerprofil = null;
	private Suchprofil suchprofil = null;

	/**
	 * No-Argument Konstruktor
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
		this.auswahlMapper = AuswahlMapper.auswahlMapper();
		this.auswahloptionMapper = AuswahloptionMapper.auswahloptionMapper();
		this.beschreibungMapper = BeschreibungMapper.beschreibungMapper();
		this.besuchteProfilListeMapper = BesuchteProfilListeMapper.besuchteProfilListeMapper();
		this.eigenschaftMapper = EigenschaftMapper.eigenschaftMapper();
		this.informationMapper = InformationMapper.informationMapper();
		this.merkzettelMapper = MerkzettelMapper.merkzettelMapper();
		this.nutzerprofilMapper = NutzerprofilMapper.nutzerprofilMapper();
		this.profilMapper = ProfilMapper.profilMapper();
		this.sperrlisteMapper = SperrlisteMapper.sperrlisteMapper();
		this.suchprofilMapper = SuchprofilMapper.suchprofilMapper();

	}

	// ABSCHNITT, Ende: Initialisierung

	// ABSCHNITT, Beginn: Methoden für Nutzer-Objekte

	/**
	 * Erstellen der Create Methode der Klasse Profil
	 * 
	 * >>>>>>>>>>>>hierbei handelt es sich um eine abstrakte klasse. Diese sind
	 * nicht instanziierbar. Andere L�sung ben�tigt.<<<<<<<<<<<<
	 * 
	 * @author Dominik Sasse
	 * @author An Dang
	 */

	// @Override
	// public Profil createProfil(Boolean raucher, String haarfarbe, String
	// religion, Integer koerpergroesse, String geschlecht)
	// throws IllegalArgumentException {
	// Profil profil = new Profil();
	// profil.setRaucher(raucher);
	// profil.setHaarfarbe(haarfarbe);
	// profil.setReligion(religion);
	// profil.setKoerpergroesse(koerpergroesse);
	// profil.setGeschlecht(geschlecht);
	// profil.setID(1);

	// return this.profilMapper.insertProfil(profil);
	// return null;
	// }

	/**
	 * Erstellen der Create Methode der Klasse Nutzerprofil
	 * 
	 * @author Dominik Sasse
	 * @author An Dang
	 */
	// -----------------------------------------------------------------------------
	// Diese Methode gibt die Informationen aus NutzerForm an die
	// NutzerprofilMapper Klasse weiter um einen neuen Nutzer zu erstellen.

	@Override
	public Nutzerprofil createNutzerprofil(String emailAddress, String vorname, String nachname, String geschlecht, String religion,
			int koerpergroesse, String haarfarbe, String raucher) throws IllegalArgumentException {

		Nutzerprofil nutzerprofil = new Nutzerprofil();
		nutzerprofil.setEmailAddress(emailAddress);
		nutzerprofil.setVorname(vorname);
		nutzerprofil.setNachname(nachname);
		// nutzerprofil.setGeburtsdatum(geburtsdatum);

		/**
		 * Attribute der abstrakten Klasse Profil deklarieren.
		 * 
		 * @author Dominik Sasse
		 */
		nutzerprofil.setRaucher(raucher);
		nutzerprofil.setHaarfarbe(haarfarbe);
		nutzerprofil.setReligion(religion);
		nutzerprofil.setKoerpergroesse(koerpergroesse);
		nutzerprofil.setGeschlecht(geschlecht);

		nutzerprofil.setID(1);

		return this.nutzerprofilMapper.insert(nutzerprofil);
	}
	
	
	  public Nutzerprofil checkStatus (String emailAdress) {
		 return this.nutzerprofilMapper.checkStatus(emailAdress); 
	  }

	/**
	 * Speichern eines Nutzerprofils.
	 * 
	 * @author Dominik Sasse
	 */
	@Override
	public void saveNutzerprofil(Nutzerprofil nutzerprofil) throws IllegalArgumentException {
		nutzerprofilMapper.update(nutzerprofil);
	}

	/**
	 * setNutzerprofil
	 * 
	 * @author Dominik Sasse
	 */

	@Override
	public void setNutzerprofil(Nutzerprofil p) throws IllegalArgumentException {
		this.nutzerprofil = p;

	}

	/**
	 * Auslesen aller Nutzer
	 * 
	 * @author Dominik Sasse
	 */
	@Override
	public ArrayList<Nutzerprofil> getAllNutzerprofile() throws IllegalArgumentException {
		return this.nutzerprofilMapper.findAllNutzerprofil();
	}

	/**
	 * Auslesen eines bestimmten Nutzerprofils
	 * 
	 * @author Dominik Sasse
	 */

	public Nutzerprofil getNutzerprofil(Nutzerprofil Nutzerprofil_ProfilID) throws IllegalArgumentException {
		return nutzerprofilMapper.findByProfil(Nutzerprofil_ProfilID);
	}

	// -----------------------------------------------------------------------------

	/**
	 * Die Klasse Eigenschaft ist ebenfalls abstrakt und kann daher nicht
	 * erstellt werden?
	 * 
	 * @author Dominik Sasse
	 */
	// @Override
	// public Eigenschaft createEigenschaft(String erlaeuterung) throws
	// IllegalArgumentException {
	// TODO Auto-generated method stub
	// return null;
	// }

	/**
	 * Erstellung der Auswahl. Die Auswahl wird in der Klasse Auswahloption
	 * gew�hlt und in der Datenbank gespeichert.
	 * 
	 * @author Dominik Sasse
	 * 
	 */

	@Override
	public Auswahl createAuswahl(Auswahloption a) throws IllegalArgumentException {

		Auswahl auswahl = new Auswahl();
		auswahl.setBezeichnung(a.getOptionsBezeichnung());

		return this.auswahlMapper.insert(auswahl);
	}

	/**
	 * Eine Auswahloption wird angelegt und in der Datenbank gespeichert.
	 * 
	 * @author Dominik Sasse
	 */
	@Override
	public Auswahloption createAuswahloption(String optionsBezeichnung) throws IllegalArgumentException {

		Auswahloption auswahloption = new Auswahloption();
		auswahloption.setOptionsBezeichnung(optionsBezeichnung);

		return this.auswahloptionMapper.insert(auswahloption);
	}

	/**
	 * Erstellung einer Beschreibung
	 * 
	 * Eine Beschreibung wird angelegt und in der Datenbank gespeichert.
	 * 
	 * @author Dominik Sasse
	 */
	@Override
	public Beschreibung createBeschreibung(String beschreibung) throws IllegalArgumentException {

		Beschreibung b = new Beschreibung();
		b.setBeschreibung(beschreibung);

		return this.beschreibungMapper.insert(b);

	}

	/**
	 * Ein Suchprofil wird angelegt mit den Einschraenkungen koerpergroesse und
	 * alter.
	 * 
	 * @author Dominik Sasse
	 */
	@Override
	public Suchprofil createSuchprofil(int altervon, int alterbis, int koerpergroessevon, 
			int koerpergroessebis, String raucher, String religion, String haarfarbe, String geschlecht)
			throws IllegalArgumentException {

		Suchprofil suchprofil = new Suchprofil();
		suchprofil.setAltervon(altervon);
		suchprofil.setAlterbis(alterbis);
		suchprofil.setKoerpergroessevon(koerpergroessevon);
		suchprofil.setKoerpergroessebis(koerpergroessebis);
		suchprofil.setRaucher(raucher);
		suchprofil.setReligion(religion);
		suchprofil.setHaarfarbe(haarfarbe);
		suchprofil.setGeschlecht(geschlecht);

		return this.suchprofilMapper.insert(suchprofil);
	}

	/**
	 * Speichern eines Suchprofils.
	 * 
	 * @author Dominik Sasse
	 */
	@Override
	public void saveSuchprofil(Suchprofil suchprofil) throws IllegalArgumentException {
		suchprofilMapper.update(suchprofil);
	}

	// ----------------------------------------------------------------------------------
	// von Flo und Marci, nicht löschen und nicht verändern oder wir töten
	// euch. :3
	// Evt. Klärungsbedarf
	public Nutzerprofil getNutzerprofil(int id) throws IllegalArgumentException {
		return this.nutzerprofilMapper.readNutzerProfil(id);

	}
	/*
	 * @Override public void setNutzerprofil(Nutzerprofil p) throws
	 * IllegalArgumentException { // TODO Auto-generated method stub
	 * 
	 * }
	 */
	// @Override
	/*
	 * public ArrayList<Nutzerprofil> getNutzerprofil(Nutzerprofil p) throws
	 * IllegalArgumentException { // TODO Auto-generated method stub return
	 * null; }
	 */

	/**
	 * Profil merken
	 * 
	 * @author Dominik Sasse
	 */
	@Override
	public Merkzettel merkeNutzerprofil(int ProfilID) throws IllegalArgumentException {

		Merkzettel merkzettel = new Merkzettel();
		merkzettel.setMerkenderID(ProfilID);;

		return merkzettelMapper.insert(merkzettel);
	}

	/**
	 * Profil von Merkliste entfernen
	 * 
	 * @author Dominik Sasse
	 */
	@Override
	public void deleteNutzerprofilvonMerkliste(Merkzettel merkzettel) throws IllegalArgumentException {

		merkzettel.getMerkenderID();

		this.merkzettelMapper.delete(merkzettel);
	}

	/**
	 * Profil sperren
	 * 
	 * @author Dominik Sasse
	 */

	@Override
	public Sperrliste sperreNutzerprofil(int ProfilID) throws IllegalArgumentException {

		Sperrliste sperrliste = new Sperrliste();
		sperrliste.setSperrender_NutzerprofilID(ProfilID);

		return sperrlisteMapper.insert(sperrliste);
	}

	/**
	 * Profilsperre aufheben
	 * 
	 * @author Dominik Sasse
	 * 
	 */

	public void entsperreNutzerprofil(Sperrliste sperrliste) throws IllegalArgumentException {

		sperrliste.getSperrender_NutzerprofilID();

		this.sperrlisteMapper.delete(sperrliste);
		;
	}

	/**
	 * Profil sperren
	 * 
	 * @author Dominik Sasse
	 */

	@Override
	public BesuchteProfilListe besucheNutzerprofil(int ProfilID) throws IllegalArgumentException {

		BesuchteProfilListe besuchteProfilListe = new BesuchteProfilListe();
		besuchteProfilListe.setBesuchender_NutzerprofilID(ProfilID);

		return this.besuchteProfilListeMapper.insert(besuchteProfilListe);
	}

	
	
	/**
	 * Suche durchf�hren anhand von Suchprofil 
	 * @author Manuel Weiler @author Dominik Sasse
	 */
	
	public Nutzerprofil suchemitSuchprofil(int ProfilID){
		
		getAllNutzerprofile();

		for (int i = 0; i < getAllNutzerprofile().size(); i++) {

			if (this.suchprofil.getProfilID() == getAllNutzerprofile().get(i)
					.getProfilID())
			if (this.suchprofil.getKoerpergroessevon() >= getAllNutzerprofile().get(i)
					.getKoerpergroesse())
			if (this.suchprofil.getKoepergroessebis() <= getAllNutzerprofile().get(i)
					.getKoerpergroesse())
			//heutiges Datum minus Geburtsdatum = Alter
			//if-Bedingung wie oben
			
			if (this.suchprofil.getHaarfarbe().equals(getAllNutzerprofile().get(i).getHaarfarbe()))
			if (this.suchprofil.getRaucher().equals(getAllNutzerprofile().get(i).getRaucher()))
			if (this.suchprofil.getReligion().equals(getAllNutzerprofile().get(i).getReligion()))

			//return Statement �berarbeiten!
				//Eventuell Array erstellen mit allen �brigen Nutzerprofilen.
			return this.nutzerprofil;
		
		}
		return null;
		
	}
	
	
	
			
			
	
	/**
	 * Berechnung Aehnlichkeitsmass
	 * 
	 * @author Dominik Sasse
	 */

	public float berechneAehnlichkeitsmass() {

		/**
<<<<<<< HEAD
		 * Ben�tigt:
		 * - eigene ProfilID
		 * - ArrayList mit allen Nutzern
		 * - equals-Methode f�r String-Vergleich
		 * - Ausgabe des �hnlichkeitsmass f�r die einzelnen Profil- Vergleiche
=======
		 * Ben�tigt: - eigene ProfilID - ArrayList mit allen Nutzern -
		 * equals-Methode f�r String-Vergleich - Ausgabe des �hnlichkeitsmass
		 * f�r die einzelnen Profil- Vergleiche
>>>>>>> refs/heads/master
		 */


		nutzerprofil.getProfilID();
		
		//ArrayList mit allen Nutzerprofilen

		getAllNutzerprofile();

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

		for (int i = 0; i < getAllNutzerprofile().size(); i++) {

			if (this.nutzerprofil.getProfilID() == getAllNutzerprofile().get(i)
					.getProfilID()) {
				i++;
			}

			else {

				// For-Schleife mit einem Array in welchem alle Eigenschaften
				// sind?
				// Dann könnte auch man das Ergebnis mit durch die Länge des
				// Arrays teilen.

				if (this.nutzerprofil.getHaarfarbe().equals(getAllNutzerprofile().get(i).getHaarfarbe())) {
					zwischenErgebnis = +1;
				} else {
					zwischenErgebnis = +0;

				}
				if (this.nutzerprofil.getRaucher().equals(getAllNutzerprofile().get(i).getRaucher())) {
					zwischenErgebnis = +1;
				} else {
					zwischenErgebnis = +0;

				}
				if (this.nutzerprofil.getReligion().equals(getAllNutzerprofile().get(i).getReligion())) {
					zwischenErgebnis = +1;
				} else {
					zwischenErgebnis = +0;
				}

				float ergebnis = 3 / zwischenErgebnis * 100;

				// Hier wird noch ein Zwischenschritt benötigt in welchem das
				// Ähnlichkeitsmaß dem entsprechenden
				// Profil zuordnet.

				aehnlichkeitsmass.add(ergebnis);
			}

		}
		return 0;

	}
}

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
			this.auswahlMapper =  AuswahlMapper.auswahlMapper();
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

			//ABSCHNITT, Ende: Initialisierung

			//ABSCHNITT, Beginn: Methoden für Nutzer-Objekte


		/**
		 * Erstellen der Create Methode der Klasse Profil
		 * 
		 * >>>>>>>>>>>>hierbei handelt es sich um eine abstrakte klasse. Diese sind nicht instanziierbar. Andere L�sung ben�tigt.<<<<<<<<<<<<
		 * 
		 * @author Dominik Sasse
		 * @author An Dang
		 */
		
		//@Override
		//public Profil createProfil(Boolean raucher, String haarfarbe, String religion, Integer koerpergroesse, String geschlecht) 
			//	throws IllegalArgumentException {
			//Profil profil = new Profil();
			//profil.setRaucher(raucher);
			//profil.setHaarfarbe(haarfarbe);
			//profil.setReligion(religion);
			//profil.setKoerpergroesse(koerpergroesse);
			//profil.setGeschlecht(geschlecht);
			//profil.setID(1);

			//return this.profilMapper.insertProfil(profil);
			//return null;
		//}

		/**
		 * Erstellen der Create Methode der Klasse Nutzerprofil
		 * 
		 * @author Dominik Sasse
		 * @author An Dang
		 */
		
		@Override
		public Nutzerprofil createNutzerprofil(String vorname, String nachname, Date geburtsdatum, 
			Boolean raucher, String haarfarbe, String religion, Integer koerpergroesse, String geschlecht)
			throws IllegalArgumentException {
			
			Nutzerprofil nutzerprofil = new Nutzerprofil();
			nutzerprofil.setVorname(vorname);
			nutzerprofil.setNachname(nachname);
			nutzerprofil.setGeburtsdatum(geburtsdatum);
			
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
			
			nutzerprofil.setID(1);;

			return this.nutzerprofilMapper.insert(nutzerprofil);
		}
		
		/**
		 * Die Klasse Eigenschaft ist ebenfalls abstrakt und kann daher nicht erstellt werden?
		 * @author Dominik Sasse
		 */
		//@Override
		//public Eigenschaft createEigenschaft(String erlaeuterung) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			//return null;
		//}

		/**
		 * Erstellung der Auswahl
		 * 
		 * @author Dominik Sasse
		 * 
		 */
		
		@Override
		public Auswahl createAuswahl(String bezeichnung)
			throws IllegalArgumentException{
			return null;
			
		}
		
		/**
		 * Eine Auswahloption wird angelegt.
		 * @author Dominik Sasse
		 */
		@Override
		public Auswahloption createAuswahloption(String optionsBezeichnung) 
				throws IllegalArgumentException {
			
			Auswahloption auswahloption = new Auswahloption();
			auswahloption.setOptionsBezeichnung(optionsBezeichnung);
			return this.auswahloptionMapper.insert(auswahloption);
		}
		
		/**
		 * Erstellung einer Beschreibung
		 * 
		 * Ich hab keine Ahnung was daran falsch sein soll...
		 * 
		 * @author Dominik Sasse
		 */
		@Override
		public Beschreibung createBeschreibung(String beschreibung) 
				throws IllegalArgumentException {
			
			Beschreibung beschreibung = new Beschreibung();
			beschreibung.setBeschreibung(beschreibung);
			
			return this.beschreibungMapper.insert(beschreibung);
			
		}
			
		
		/**
		 * Ein Suchprofil wird angelegt mit den Einschraenkungen koerpergroesse und alter.
		 * @author Dominik Sasse
		 */
		@Override
		public Suchprofil createSuchprofil(int altervon, int alterbis, int koerpergroessevon, int koerpergroessebis) 
				throws IllegalArgumentException {
			
			Suchprofil suchprofil = new Suchprofil();
			suchprofil.setAltervon(altervon);
			suchprofil.setAlterbis(alterbis);
			suchprofil.setKoerpergroessevon(koerpergroessevon);
			suchprofil.setKoerpergroessebis(koerpergroessebis);
			
			return this.suchprofilMapper.insert(suchprofil);
		}

		@Override
		public Merkzettel createMerkzettel(int ID) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Sperrliste createSperrliste(int ID) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}
		

		
		
		@Override
		public ArrayList<Profil> getAllProfils() throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Profil createProfil(Boolean raucher, String haarfarbe, String religion, Integer koerpergroesse,
				String geschlecht) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Eigenschaft createEigenschaft(String erlaeuterung) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

}
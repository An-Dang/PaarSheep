package de.hdm.Gruppe4.Paarsheep.server;

import java.sql.Date;
import java.text.*;
import java.util.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.Gruppe4.Paarsheep.server.*;
import de.hdm.Gruppe4.Paarsheep.server.db.*;
import de.hdm.Gruppe4.Paarsheep.shared.*;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

	/**
	 * <p>
	 * Implementierungsklasse des Interface <code>MessagingAdministration</code>.
	 * Diese Klasse ist <em>die</em> Klasse, die neben {@link ReportGeneratorImpl}
	 * sämtliche Applikationslogik (oder engl. Business Logic) aggregiert. Sie ist
	 * wie eine Spinne, die saemtliche Zusammenhänge in ihrem Netz (in unserem Fall
	 * die Daten der Applikation) überblickt und für einen geordneten Ablauf und
	 * dauerhafte Konsistenz der Daten und Abläufe sorgt.
	 * </p>
	 * <p>
	 * Die Applikationslogik findet sich in den Methoden dieser Klasse. Jede dieser
	 * Methoden kann als <em>Transaction Script</em> bezeichnet werden. Dieser Name
	 * lässt schon vermuten, dass hier analog zu Datenbanktransaktion pro
	 * Transaktion gleiche mehrere Teilaktionen durchgeführt werden, die das System
	 * von einem konsistenten Zustand in einen anderen, auch wieder konsistenten
	 * Zustand überführen. Wenn dies zwischenzeitig scheitern sollte, dann ist das
	 * jeweilige Transaction Script dafür verwantwortlich, eine Fehlerbehandlung
	 * durchzuführen.
	 * </p>
	 * <p>
	 * Diese Klasse steht mit einer Reihe weiterer Datentypen in Verbindung. Dies
	 * sind:
	 * <ol>
	 * <li>{@link MessagingAdministration}: Dies ist das <em>lokale</em> - also
	 * Server-seitige - Interface, das die im System zur Verf�gung gestellten
	 * Funktionen deklariert.</li>
	 * <li>{@link MessagingAdministrationAsync}:
	 * <code>MessagingAminidstrationImpl</code> und
	 * <code>MessagingAdministration</code> bilden nur die Server-seitige Sicht der
	 * Applikationslogik ab. Diese basiert vollständig auf synchronen
	 * Funktionsaufrufen. Wir müssen jedoch in der Lage sein, Client-seitige
	 * asynchrone Aufrufe zu bedienen. Dies bedingt ein weiteres Interface, das in
	 * der Regel genauso benannt wird, wie das synchrone Interface, jedoch mit dem
	 * zusaetzlichen Suffix "Async". Es steht nur mittelbar mit dieser Klasse in
	 * Verbindung. Die Erstellung und Pflege der Async Interfaces wird durch das
	 * Google Plugin semiautomatisch unterstützt. Weitere Informationen unter
	 * {@link MessagingAdministrationAsync}.</li>
	 * <li>{@link RemoteServiceServlet}: Jede Server-seitig instantiierbare und
	 * Client-seitig über GWT RPC nutzbare Klasse muss die Klasse
	 * <code>RemoteServiceServlet</code> implementieren. Sie legt die funktionale
	 * Basis für die Anbindung von <code>MessagingAdministrationImpl</code> an die
	 * Runtime des GWT RPC-Mechanismus.</li>
	 * </ol>
	 * </p>
	 * <p>
	 * <b>Wichtiger Hinweis:</b> Diese Klasse bedient sich sogenannter
	 * Mapper-Klassen. Sie gehören der Datenbank-Schicht an und bilden die
	 * objektorientierte Sicht der Applikationslogik auf die relationale
	 * organisierte Datenbank ab. Zuweilen kommen "kreative" Zeitgenossen auf die
	 * Idee, in diesen Mappern auch Applikationslogik zu realisieren. Siehe dazu
	 * auch die Hinweise in {@link #delete(Customer)} Einzig nachvollziehbares
	 * Argument für einen solchen Ansatz ist die Steigerung der Performance
	 * umfangreicher Datenbankoperationen. Doch auch dieses Argument zieht nur dann,
	 * wenn wirklich große Datenmengen zu handhaben sind. In einem solchen Fall
	 * würde man jedoch eine entsprechend erweiterte Architektur realisieren, die
	 * wiederum sämtliche Applikationslogik in der Applikationsschicht isolieren
	 * würde. Also, keine Applikationslogik in die Mapper-Klassen "stecken" sondern
	 * dies auf die Applikationsschicht konzentrieren!
	 * </p>
	 * <p>
	 * Beachten Sie, dass s�mtliche Methoden, die mittels GWT RPC aufgerufen werden
	 * können ein <code>throws Exception</code> in der
	 * Methodendeklaration aufweisen. Diese Methoden dürfen also Instanzen von
	 * {@link IllegalArgumentException} auswerfen. Mit diesen Exceptions können z.B.
	 * Probleme auf der Server-Seite in einfacher Weise auf die Client-Seite
	 * transportiert und dort systematisch in einem Catch-Block abgearbeitet werden.
	 * </p>
	 * <p>
	 * Es gibt sicherlich noch viel mehr über diese Klasse zu schreiben. Weitere
	 * Infos erhalten Sie in der Lehrveranstaltung.
	 * </p>
	 * 
	 * @see MessagingAdministration
	 * @see MessagingAdministrationAsync
	 * @see RemoteServiceServlet
	 * 
	 * @author Thies
	 * @author Dang
	 * @author Hauler
	 * 
	 *
	 */

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
			this.profilMapper = ProfilMapper.profillMapper();
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
		
		
}
	
	
	
	
	
	
	
	
	
	
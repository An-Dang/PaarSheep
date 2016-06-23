package de.hdm.Gruppe4.Paarsheep.server;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.Gruppe4.Paarsheep.server.db.AuswahloptionMapper;
import de.hdm.Gruppe4.Paarsheep.server.db.BeschreibungMapper;
import de.hdm.Gruppe4.Paarsheep.server.db.BesuchteProfilListeMapper;
import de.hdm.Gruppe4.Paarsheep.server.db.EigenschaftMapper;
import de.hdm.Gruppe4.Paarsheep.server.db.InformationMapper;
import de.hdm.Gruppe4.Paarsheep.server.db.MerkzettelMapper;
import de.hdm.Gruppe4.Paarsheep.server.db.NutzerprofilMapper;
import de.hdm.Gruppe4.Paarsheep.server.db.SperrlisteMapper;
import de.hdm.Gruppe4.Paarsheep.server.db.SuchprofilMapper;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministration;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGenerator;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Eigenschaft;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Information;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;
import de.hdm.Gruppe4.Paarsheep.shared.report.Column;
import de.hdm.Gruppe4.Paarsheep.shared.report.CompositeParagraph;
import de.hdm.Gruppe4.Paarsheep.shared.report.InfoObjekteByNutzerReport;
import de.hdm.Gruppe4.Paarsheep.shared.report.PartnervorschleageBySuchprofilReport;
import de.hdm.Gruppe4.Paarsheep.shared.report.ProfilInfoByNutzerprofilReport;
import de.hdm.Gruppe4.Paarsheep.shared.report.Report;
import de.hdm.Gruppe4.Paarsheep.shared.report.Row;
import de.hdm.Gruppe4.Paarsheep.shared.report.SimpleParagraph;


@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet
implements ReportGenerator {
	
	
	
	private SuchprofilMapper suchprofilMapper = null;
	private NutzerprofilMapper nutzerprofilMapper = null;
	
	/**
	   * Ein ReportGenerator benötigt Zugriff auf die BankAdministration, da diese die
	   * essentiellen Methoden für die Koexistenz von Datenobjekten (vgl.
	   * bo-Package) bietet.
	   */
	  private PartnerboerseAdministration partnerboerseadministration = null;
	  
	  
	  /**
	   * <p>
	   * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	   * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	   * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
	   * Konstruktors ist durch die Client-seitige Instantiierung durch
	   * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
	   * möglich.
	   * </p>
	   * <p>
	   * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
	   * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
	   * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	   * </p>
	   */
	  public ReportGeneratorImpl() throws IllegalArgumentException {
	  }
	  
	  /**
	   * Initialsierungsmethode. Siehe dazu Anmerkungen zum No-Argument-Konstruktor.
	   * 
	   * @see #ReportGeneratorImpl()
	   */
	  @Override
	public void init() throws IllegalArgumentException {
		    /*
		     * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf eine
		     * BankVerwaltungImpl-Instanz.
		     */
		    PartnerboerseAdministrationImpl a = new PartnerboerseAdministrationImpl();
		    a.init();
		    this.partnerboerseadministration = a;
		  }
	  
	  /**
		 * Zugeh�rige PartnerboerseAdministration auslesen (interner Geburach).
		 * 
		 * @return das PartnerboerseAdministration-Objekt.
		 */
		protected PartnerboerseAdministration getPartnerboerseAdministration() {
			return this.partnerboerseadministration;
		}

		/**
		 * Die Methode soll dem Report ein Impressum hinzufuegen. Dazu wird zunaechst
		 * ein neuer CompositeParagraph angelegt, da das Impressum mehrzeilig sein soll.
		 * Danach werden belibige SimpleParagraph dem CompositeParagraph hinzugefuegt. Zum
		 * Schluss wird CompositeParagraph dem Report hinzugef�gt �ber setImprint.
		 * 
		 * @param r der um das Impressum zu erweiternde Report.
		 */
		protected void addImprint(Report r) {

			CompositeParagraph imprint = new CompositeParagraph();

			imprint.addSubParagraph(new SimpleParagraph("Lonely Hearts"));

			r.setImprint(imprint);
		}

//		/**
//		 * Methode, die einen fertigen Report vom Typ AllInfosOfNutzerReport zurueckliefert. 
//		 * Der Report stellt alle Infos eines Nutzerprofils dar.
//		 * 
//		 */
//
//		public InfoObjekteByNutzerReport createInfoObjekteByNutzerReport(Nutzerprofil nutzerprofil)
//				throws IllegalArgumentException {
//
//			if (this.getPartnerboerseAdministration() == null)
//				return null;
//
//			InfoObjekteByNutzerReport result = new InfoObjekteByNutzerReport();
//
//			result.setTitle(" ");
//
//		
//			/*
//			 * Ab hier erfolgt ein zeilenweises Hinzufuegen von
//			 * Nutzerprofil-Informationen.
//			 */
//
//			/*
//			 * Zunaechst legen wir eine Kopfzeile fuer die Info-Tabelle an.
//			 */
//			Row headline = new Row();
//			
//			headline.addColumn(new Column("Eigenschaft"));
//
//			headline.addColumn(new Column("Infotext"));
//			
//			// Hinzufuegen der Kopfzeile
//			result.addRow(headline);
//			
//			/*
//			 * Nun werden saemtliche Infos des Nutzerprofils ausgelesen
//			 */
//			Map<List<Information>,List<Eigenschaft>> resultMap = this.partnerboerseadministration.getAllInfos(nutzerprofil.getProfilID());
//			
//			Set<List<Info>> output = resultMap.keySet();
//			
//			for (List<Info> listI : output) {
//				
//				List<Eigenschaft> listE = new ArrayList<Eigenschaft>();
//				listE = resultMap.get(listI);
//				
//				for (int e = 0; e < listE.size(); e++) {
//					
//					// Eine leere Zeile anlegen.
//					Row infoRow = new Row();
//					
//					infoRow.addColumn(new Column(listE.get(e).getErlaeuterung()));
//					
//						infoRow.addColumn(new Column(listI.get(e).getInfotext()));
//					
////					und schliesslich die Zeile dem Report hinzufuegen.
//					result.addRow(infoRow);	
//				}
//			}
//			
//			/*
//			 * Zum Schluss muss der fertige Report zurueckgeben werden.
//			 */
//			return result;
//		}
		
		
		/**
		 * Methode, die einen fertigen Report vom Typ ProfilInfoByNutzerprofilReport zurueckliefert.
		 * Der Report stellt alle Profildaten eines Nutzerprofils dar.
		 *  
		 */
		@Override
		public ProfilInfoByNutzerprofilReport createProfilInfoByNutzerprofilReport(
				Nutzerprofil nutzerprofil) throws IllegalArgumentException {

			if (this.getPartnerboerseAdministration() == null)
				return null;

			/*
			 * Zunaechst wird ein leerer Report angelegt.
			 */
			ProfilInfoByNutzerprofilReport result = new ProfilInfoByNutzerprofilReport();

			// Jeder Report hat einen Titel (Bezeichnung / Ueberschrift).
			result.setTitle(nutzerprofil.getVorname() + " " + nutzerprofil.getNachname());

			/*
			 * Ab hier erfolgt ein zeilenweises Hinzufuegen von Profildaten.
			 */

			/*
			 * Zunaechst legen wir eine Kopfzeile fuer die Konto-Tabelle an.
			 */
			Row headline = new Row();

			headline.addColumn(new Column("Profil-ID"));

			headline.addColumn(new Column("Vorname"));

			headline.addColumn(new Column("Nachname"));

			headline.addColumn(new Column("Geschlecht"));

			headline.addColumn(new Column("Geburtsdatum"));

			headline.addColumn(new Column("Koerpergroessse"));

			headline.addColumn(new Column("Haarfarbe"));

			headline.addColumn(new Column("Raucherstatus"));

			headline.addColumn(new Column("Religion"));

			// Hinzufuegen der Kopfzeile
			result.addRow(headline);
			
			/*
			 * Nun werden saemtliche Profildaten des Nutzerprofils ausgelesen
			 */

			Nutzerprofil np = this.partnerboerseadministration.getNutzerprofilById(nutzerprofil.getProfilID());

			// Eine leere Zeile anlegen.
			Row profildatenoRow = new Row();

			// Spalten hinzufuegen
			profildatenoRow.addColumn(new Column(String.valueOf(np.getProfilID())));
			profildatenoRow.addColumn(new Column(np.getVorname()));
			profildatenoRow.addColumn(new Column(np.getNachname()));
			profildatenoRow.addColumn(new Column(np.getGeschlecht()));
			profildatenoRow.addColumn(new Column(String.valueOf(np
					.getGeburtsdatum())));
			profildatenoRow.addColumn(new Column(String.valueOf(np
					.getKoerpergroesse())));
			profildatenoRow.addColumn(new Column(np.getHaarfarbe()));
			profildatenoRow.addColumn(new Column(np.getRaucher()));
			profildatenoRow.addColumn(new Column(np.getReligion()));
			
			// und schlie�lich die Zeile dem Report hinzufuegen.
			result.addRow(profildatenoRow);

			return result;
		}

		
		/**
		  * Methode, die einen fertigen Report vom Typ AllPartnervorschlaegeSpReport zurueckliefert.
		 * Der Report stellt alle Partnervorschlaege, die anhand eines Suchprofils ermittelt wurden, 
		 * f�r ein Nutzerprofil dar.
		 * 
		 */
		@Override
		public PartnervorschleageBySuchprofilReport createPartnervorschleageBySuchprofilReport(Nutzerprofil nutzerprofil,
				String suchprofilname) throws IllegalArgumentException {

			if (this.getPartnerboerseAdministration() == null)
				return null;

			/*
			 * Anlegen eines leeren Reports.
			 */
			PartnervorschleageBySuchprofilReport result = new PartnervorschleageBySuchprofilReport();

			// Jeder Report hat einen Titel (Bezeichnung / Ueberschrift).
			result.setTitle("Alle Partnervorschlaege anhand des Suchprofils: "
					+ suchprofilname);

			// Imressum hinzufuegen
			this.addImprint(result);

			/*
			 * Datum der Erstellung hinzufuegen. new Date() erzeugt autom. einen
			 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
			 */
			result.setCreated(new Date());

			/*
			 * Ab hier: Kopfdaten des Reports zusammenstellen. Die Kopfdaten sind
			 * mehrzeilig, daher die Verwendung von CompositeParagraph.
			 */
			CompositeParagraph header = new CompositeParagraph();

			// Name und Vorname des Nutzers aufnehmen.
			header.addSubParagraph(new SimpleParagraph(nutzerprofil.getVorname() + " "
					+ nutzerprofil.getNachname()));

			// Nutzerprofil-ID aufnehmen.
			header.addSubParagraph(new SimpleParagraph("Nutzerprofil-ID: "
					+ nutzerprofil.getProfilID()));
			
			// Zusammengestellte Kopfdaten zum Report hinzufuegen.
			result.setHeaderData(header);
			/*
			 * Nun werden saemtliche Nutzerprofil-Objekte ausgelesen.
			 * Anschlie�end wird fuer jedes Nutzerprofil-Objekt n ein Aufruf von
			 * createAllProfildatenOfNutzerReport(n) und ein Aufruf von 
			 * createAllInfosOfNutzerReport(n) durchgefuehrt und somit jeweils
			 * ein AllProfildatenOfNutzerReport-Objekt und ein AllInfosOfNutzerReport-Objekt
			 * erzeugt. Diese Objekte werden sukzessive der result-Variable hinzugefuegt. 
			 * Sie ist vom Typ AllPartnervorschlaegeSpReport, welches eine Subklasse von
			 * CompositeReport ist.
			 */
			List<Nutzerprofil> allNutzer = this.partnerboerseadministration
					.getAllNutzerprofile(nutzerprofil.getProfilID());

			for (Nutzerprofil np : allNutzer) {
				/*
				 * Anlegen des jew. Teil-Reports und Hinzufuegen zum Gesamt-Report.
				 */
				result.addSubReport(this.createProfilInfoByNutzerprofilReport(np));
	//		result.addSubReport(this.createInfoObjekteByNutzerReport(np));
			}

			/*
			 * Fertigen Report zurueckgeben.
			 */
			return result;
		}
		
		
		
		
		
		/**
		 * Aehnlichkeit zwischen den Profilinfos und (InfoObjekten) eines Suchprofils eines
		 * Nutzers und den Profilinfos und (InfoObjekten) anderer Nutzerprofile berechnen.
		 * 
		 */
		public void AehnlichkeitBerechnungSuchprofil(int profilId) throws IllegalArgumentException {
			
			List<Suchprofil> referenzprofil = suchprofilMapper.findSuchprofilByNutzerID(profilId);
			List<Nutzerprofil> vergleichsprofil = nutzerprofilMapper.findAllNutzerprofil(profilId);
			
			
			for (Suchprofil sp : referenzprofil) {
				for (Nutzerprofil np : vergleichsprofil) {
					int aehnlichkeitSp = 0;
					int counter = 70;

					int suchprofilId = sp.getProfilID();
					int fremdprofilId = np.getProfilID();

					if (sp.getGeschlecht().equals("Keine Auswhal")) {
						aehnlichkeitSp = aehnlichkeitSp + 30;

					} else {

						if (sp.getGeschlecht().equals(np.getGeschlecht())) {
							aehnlichkeitSp = aehnlichkeitSp + 30;
						}
					}

					if (sp.getHaarfarbe().equals("Keine Auswhal")) {
						aehnlichkeitSp = aehnlichkeitSp + 10;

					} else {

						if (sp.getHaarfarbe().equals(np.getHaarfarbe())) {
							aehnlichkeitSp = aehnlichkeitSp + 10;
						}

					}

					if (sp.getKoerpergroesse() + 5 >= np.getKoerpergroesse()) {
						if (sp.getKoerpergroesse() - 5 <= np.getKoerpergroesse()) {
							aehnlichkeitSp = aehnlichkeitSp + 10;
						}
					}

					if (sp.getRaucher().equals("Keine Auswahl")) {
						aehnlichkeitSp = aehnlichkeitSp + 10;

					} else {

						if (sp.getRaucher().equals(np.getRaucher())) {
							aehnlichkeitSp = aehnlichkeitSp + 10;
						}

					}
					if (sp.getRaucher().equals("Keine Auswahl")) {
						aehnlichkeitSp = aehnlichkeitSp + 10;

					} else {

						if (sp.getReligion().equals(np.getReligion())) {
							aehnlichkeitSp = aehnlichkeitSp + 10;
						}

					}
			

					/**
					 * Berechnung des Prozentwertes.
					 */
					aehnlichkeitSp = aehnlichkeitSp * (100 / counter);

					}

				}
			}
	
		
		/**
		 * Alle Partnervorschlaege anhand von Suchprofilen fuer einen Nutzer
		 * auslesen. Es werden aktuell alle Nutzerprofile ausgegeben (auch die gesperrten)
		 * 
		 */

		public List<Nutzerprofil> getGeordnetePartnervorschlaegeSp(int profilId, String suchprofilname)
				throws IllegalArgumentException {

			Suchprofil sp = this.suchprofilMapper.findSuchprofiByName(profilId, suchprofilname);

			int suchprofilId = sp.getProfilID();
			return this.nutzerprofilMapper.findAllNutzerprofil(profilId);
		}

	  }
	

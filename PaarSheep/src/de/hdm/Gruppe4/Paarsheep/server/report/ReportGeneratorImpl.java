package de.hdm.Gruppe4.Paarsheep.server.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.google.gwt.logging.client.DefaultLevel.Info;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.Gruppe4.Paarsheep.server.PartnerboerseAdministrationImpl;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministration;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGenerator;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;
import de.hdm.Gruppe4.Paarsheep.shared.report.*;

public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	private PartnerboerseAdministration partnerboerseAdministration = null;

	public ReportGeneratorImpl() throws IllegalArgumentException {

	}

	/**
	 * Instanzierungsmethode
	 */
	public void init() throws IllegalArgumentException {

		PartnerboerseAdministrationImpl a = new PartnerboerseAdministrationImpl();
		a.init();
		this.partnerboerseAdministration = a;
	}

	public void setprofil(Profil profil) throws IllegalArgumentException {
	}

	/**
	 * Auslesen der zugeh�rigen PartnerboerseAdministration
	 * 
	 * @return administration
	 */
	protected PartnerboerseAdministration getPartnerboerseAdministration() {
		return this.partnerboerseAdministration;
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

//	/**
//	 * Methode, die einen fertigen Report vom Typ AllInfosOfNutzerReport zurueckliefert. 
//	 * Der Report stellt alle Infos eines Nutzerprofils dar.
//	 * 
//	 */
//
//	public InfoObjekteByNutzerReport createInfoObjekteByNutzerReport(Nutzerprofil np)
//			throws IllegalArgumentException {
//
//		if (this.getPartnerboerseAdministration() == null)
//			return null;
//
//		InfoObjekteByNutzerReport result = new InfoObjekteByNutzerReport();
//
//		result.setTitle(" ");
//
//	
//		/*
//		 * Ab hier erfolgt ein zeilenweises Hinzufuegen von
//		 * Nutzerprofil-Informationen.
//		 */
//
//		/*
//		 * Zunaechst legen wir eine Kopfzeile fuer die Info-Tabelle an.
//		 */
//		Row headline = new Row();
//		
//		headline.addColumn(new Column("Eigenschaft"));
//
//		headline.addColumn(new Column("Infotext"));
//		
//		// Hinzufuegen der Kopfzeile
//		result.addRow(headline);
//		
//		/*
//		 * Nun werden saemtliche Infos des Nutzerprofils ausgelesen
//		 */
//		Map<List<Info>,List<Eigenschaft>> resultMap = this.partnerboerseAdministration.showProfilEigAuswahl(np.getProfilID());
//		
//		Set<List<Info>> output = resultMap.keySet();
//		
//		for (List<Info> listI : output) {
//			
//			List<Eigenschaft> listE = new ArrayList<Eigenschaft>();
//			listE = resultMap.get(listI);
//			
//			for (int e = 0; e < listE.size(); e++) {
//				
//				// Eine leere Zeile anlegen.
//				Row infoRow = new Row();
//				
//				infoRow.addColumn(new Column(listE.get(e).getErlaeuterung()));
//				
//					infoRow.addColumn(new Column(listI.get(e).getInfotext()));
//				
////				und schliesslich die Zeile dem Report hinzufuegen.
//				result.addRow(infoRow);	
//			}
//		}
//		
//		/*
//		 * Zum Schluss muss der fertige Report zurueckgeben werden.
//		 */
//		return result;
//	}

	/**
	 * Methode, die einen fertigen Report vom Typ AllProfildatenOfNutzerReport zurueckliefert.
	 * Der Report stellt alle Profildaten eines Nutzerprofils dar.
	 *  
	 */
	@Override
	public ProfilInfoByNutzerprofilReport createProfilInfoByNutzerprofilReport(
			Nutzerprofil np) throws IllegalArgumentException {

		if (this.getPartnerboerseAdministration() == null)
			return null;

		/*
		 * Zunaechst wird ein leerer Report angelegt.
		 */
		ProfilInfoByNutzerprofilReport result = new ProfilInfoByNutzerprofilReport();

		// Jeder Report hat einen Titel (Bezeichnung / Ueberschrift).
		result.setTitle(np.getVorname() + " " + np.getNachname());

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

		//headline.addColumn(new Column("Email"));

		// Hinzufuegen der Kopfzeile
		result.addRow(headline);
		
		/*
		 * Nun werden saemtliche Profildaten des Nutzerprofils ausgelesen
		 */

		Nutzerprofil n = this.partnerboerseAdministration.getNutzerprofilById(np
				.getProfilID());

		// Eine leere Zeile anlegen.
		Row profildatenoRow = new Row();

		// Spalten hinzufuegen
		profildatenoRow.addColumn(new Column(String.valueOf(n.getProfilID())));
		profildatenoRow.addColumn(new Column(n.getVorname()));
		profildatenoRow.addColumn(new Column(n.getNachname()));
		profildatenoRow.addColumn(new Column(n.getGeschlecht()));
		profildatenoRow.addColumn(new Column(String.valueOf(n
				.getGeburtsdatum())));
		profildatenoRow.addColumn(new Column(String.valueOf(n
				.getKoerpergroesse())));
		profildatenoRow.addColumn(new Column(n.getHaarfarbe()));
		profildatenoRow.addColumn(new Column(n.getRaucher()));
		profildatenoRow.addColumn(new Column(n.getReligion()));
	//	profildatenoRow.addColumn(new Column(n.getEmailAddress()));

		// und schlie�lich die Zeile dem Report hinzufuegen.
		result.addRow(profildatenoRow);

		return result;
	}

	/**
	 * Methode, die einen fertigen Report vom Typ AllPartnervorschlaegeNpReport zurueckliefert.
	 * Der Report stellt alle unangesehenen Partnervorschlaege eines Nutzerprofils dar.
	 * 
	 */
	@Override
	public PartnervorschleageByUngesehenenNutzerprofilenReport createPartnervorschleageByUngesehenenNutzerprofilenReport(Nutzerprofil np)
			throws IllegalArgumentException {

		if (this.getPartnerboerseAdministration() == null)
			return null;

		/*
		 * Zunaechst wird ein leerer Report angelegt.
		 */
		PartnervorschleageByUngesehenenNutzerprofilenReport result = new PartnervorschleageByUngesehenenNutzerprofilenReport();

		// Jeder Report hat einen Titel (Bezeichnung / Ueberschrift).
		result.setTitle("Alle unangesehenen Partnervorschlaege");

		// Imressum hinzufuegen
		this.addImprint(result);

		/*
		 * Erstellungsdatum hinzufuegen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Ab hier: Kopfdaten des Reports zusammenstellen. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		// Name und Vorname des Nutzerprofils aufnehmen.
		header.addSubParagraph(new SimpleParagraph(np.getVorname() + " "
				+ np.getNachname()));

		// Nutzerprofil-ID aufnehmen.
		header.addSubParagraph(new SimpleParagraph("Nutzerprofil-ID: "
				+ np.getProfilID()));

		// Zusammengestellte Kopfdaten zum Report hinzufuegen.
		result.setHeaderData(header);

		/*
		 * Nun werden saemtliche Nutzerprofil-Objekte ausgelesen.
		 * Anschlie�end wird fuer jedes Nutzerprofil-Objekt n ein Aufruf von
		 * createProfilInfoByNutzerprofilReport(n) und ein Aufruf von 
		 * createInfoObjekteByNutzerReport(n) durchgefuehrt und somit jeweils
		 * ein AllProfildatenOfNutzerReport-Objekt und ein AllInfosOfNutzerReport-Objekt
		 * erzeugt. Diese Objekte werden sukzessive der result-Variable hinzugefuegt. 
		 * Sie ist vom Typ AllPartnervorschlaegeNpReport, welches eine Subklasse von
		 * CompositeReport ist.
		 */

		List<Nutzerprofil> allNutzer = this.partnerboerseAdministration
				.getPartnervorschlaegeNp(np.getProfilID());
		for (Nutzerprofil n : allNutzer) {
			/*
			 * Anlegen des jew. Teil-Reports und Hinzufuegen zum Gesamt-Report.
			 */

			result.addSubReport(this.createProfilInfoByNutzerprofilReport(n));
			result.addSubReport(this.createInfoObjekteByNutzerReport(n));

		}

		/*
		 *Fertigen Report zurueckgeben.
		 */
		return result;
	}

	@Override
	public InfoObjekteByNutzerReport createInfoObjekteByNutzerReport(Nutzerprofil nutzerprofil)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartnervorschleageBySuchprofilReport createPartnervorschleageBySuchprofilReport(Nutzerprofil nutzerprofil,
			String suchprofilname) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


}

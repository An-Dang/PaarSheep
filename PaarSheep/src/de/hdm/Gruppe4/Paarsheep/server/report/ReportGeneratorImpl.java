package de.hdm.Gruppe4.Paarsheep.server.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.Gruppe4.Paarsheep.server.PartnerboerseAdministrationImpl;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministration;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGenerator;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;
import de.hdm.Gruppe4.Paarsheep.shared.report.*;


public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	private PartnerboerseAdministration administration = null;

	public ReportGeneratorImpl() throws IllegalArgumentException {

	}

	/**
	 * Instanzierungsmethode
	 */
	public void init() throws IllegalArgumentException {

		PartnerboerseAdministrationImpl a = new PartnerboerseAdministrationImpl();
		a.init();
		this.administration = a;
	}
	
	public void setprofil(Profil profil) throws IllegalArgumentException {
	}
		

	/**
	 * Auslesen der zugehörigen PartnerboerseAdministration
	 * 
	 * @return
	 */
	protected PartnerboerseAdministration getPartnerboerseAdministration() {
		return this.administration;
	}

	public void setProfil(Profil p) {
		this.administration.setProfil(p); //Fehlt noch in der PartnerboerseAdministration
	}

	protected void addImprint(Report r){
		
		/**
		 * Das Impressum erhält die Informationen über die Profile
		 */
		//Fehlt noch in der PartnerboerseAdministration
		Partnerboerse partnerboerse = this.administration.getPartnerboerse(); 
		
		/**
		 * Impressum soll mehrzeilig sein
		 */
		CompositeParagraph imprint = new CompositeParagraph();

	    imprint.addSubParagraph(new SimpleParagraph(()));
	    imprint.addSubParagraph(new SimpleParagraph(()));
	    imprint.addSubParagraph(new SimpleParagraph(()) + " " +()));
	    
	 // Das eigentliche Hinzufügen des Impressums zum Report.
	    r.setImprint(imprint);
	}
	

	public ReportByProfil createReportByProfil(Profil p, Aehnlichkeitsmass a) 
			throws IllegalArgumentException {

		if (this.getPartnerboerseAdministration() == null) {
			return null;
		}

		// Erstellen eines leeren Reports
		ReportByProfil result = new ReportByProfil();

		// Dem Report einen Titel geben
		result.setTitel("Liste von möglichen Partnern");

		// Imressum hinzufügen
		this.addImprint(result);

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		// Name und Vorname des Kunden aufnehmen
		
		//Kein Vormname und kein Nachname zur Auswahl verfügbar
		header.addSubParagraph(new SimpleParagraph(p.getNachname() + ", " + p.getVorname()));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		/*
		 * Anlegen einer Kopfzeile für die Report-Tabelle an.
		 */
		Row headline = new Row();

		/*
		 * Nun werden sämtliche Konten des Kunden ausgelesen und deren Kto.-Nr.
		 * und Kontostand sukzessive in die Tabelle eingetragen.
		 */
		Vector<Profil> profil = this.administration.getAllProfils(p);

		for (Profil b : profil) {

			// Eine leere Zeile anlegen.
			Row accountRow = new Row();
			
			 accountRow.addColumn(new Column(String.valueOf(p.g())));

			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(accountRow);
		}

		return result;
	}

	public ReportByAllProfile createReportByAllProfile() throws IllegalArgumentException {

		if (this.getPartnerboerseAdministration() == null)
			return null;

		// Erstellen eines Leeren Report
		ReportByAllProfile result = new ReportByAllProfile();

		// Dem Report einen Namen geben
		result.setTitel("Liste von möglichen Partnern");

		// Imressum hinzufügen
		this.addImprint(result);

		result.setCreated(new Date());

		ArrayList<Profil> allProfile = this.administration.getAllProfils();

		for (Profil p : allProfile) {

			// Anlegen des jew. Teil-Reports und Hinzufügen zum Gesamt-Report.
			result.addTeilReport(this.createReportByAllProfile());
		}

		return result;
	}
}

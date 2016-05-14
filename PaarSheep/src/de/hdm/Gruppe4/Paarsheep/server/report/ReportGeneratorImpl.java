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
	 * @return administration
	 */
	protected PartnerboerseAdministration getPartnerboerseAdministration() {
		return this.administration;
	}

	/**
	 * Setzen des Nutzerprofils
	 */
	public void setNutzerprofil(Nutzerprofil p) {
		this.administration.setNutzerprofil(p);
	}

	/**
	 * Impressum zum Report hinzufügen
	 * 
	 * @param r
	 */
	protected void addImprint(Report r) {

		/**
		 * Das Impressum erhält die Informationen über die Profile
		 */
		// Möglicherweise Ähnlichkeitsmaß auch anzeigen lassen
		Nutzerprofil nutzerprofil = this.administration.getNutzerprofil();

		/**
		 * Impressum soll einzeilig sein
		 */
		CompositeParagraph imprint = new CompositeParagraph();

		imprint.addSubParagraph(new SimpleParagraph(
				nutzerprofil.getVorname() + " " + nutzerprofil.getNachname() + " " + nutzerprofil.getID()));

		// Das eigentliche Hinzufügen des Impressums zum Report.
		r.setImprint(imprint);
	}

	public ReportByProfil createReportByProfil(Nutzerprofil p, Aehnlichkeitsmass a) throws IllegalArgumentException {

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
		result.setErstelldatum(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		// Name und Vorname des Kunden aufnehmen
		header.addSubParagraph(new SimpleParagraph(p.getVorname() + ", " + p.getNachname()));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		// Anlegen einer Kopfzeile für die Report-Tabelle an.
		Row headline = new Row();

		/**
		 * ArrayList<Nutzerprofil> nutzerprofil =
		 * this.administration.getNutzerprofil(p);
		 * 
		 * for (Nutzerprofil b : nutzerprofil) {
		 * 
		 * // Eine leere Zeile anlegen. Row accountRow = new Row();
		 * 
		 * accountRow.addColumn(new Column(String.valueOf(p.g())));
		 * 
		 * // und schließlich die Zeile dem Report hinzufügen.
		 * result.addRow(accountRow); }
		 */

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

		result.setErstelldatum(new Date());

		ArrayList<Profil> allProfile = this.administration.getAllProfils();

		for (Profil p : allProfile) {

			// Anlegen des jew. Teil-Reports und Hinzufügen zum Gesamt-Report.
			result.addTeilReport(this.createReportByAllProfile());
		}
		
		
		
		return result;
	}
	
	/**
	 * Sortieren des Ähnlichkeitsmaß um die Werte in geordneter Reihenfolge auszugeben
	 * @param Nutzerprofil
	 * @author Manuel Weiler
	 */
	public void aehnlichkeitsmassSortieren(int[] Nutzerprofil) {
		int merk = 0;
		for (int i = 0; i < Nutzerprofil.length -1; i++){
			
			if(Nutzerprofil[i] <= Nutzerprofil[i+1]){
				continue;
			}
			
			merk = Nutzerprofil[i];
			Nutzerprofil[i] = Nutzerprofil[i+1];
			Nutzerprofil[i+1] = merk;
			aehnlichkeitsmassSortieren(Nutzerprofil);
		}
	}

	

}

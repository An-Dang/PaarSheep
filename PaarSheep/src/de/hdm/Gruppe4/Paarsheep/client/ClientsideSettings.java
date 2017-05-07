package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.GWT;

import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGenerator;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministration;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGeneratorAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.CommonSettings;

/**
 * Die Klasse ClientsideSettings erweitert die CommonSettings.
 * Dies ist eine Klasse mit Eigenschaften und Diensten, die fuer alle client-
 * seitigen Klassen relevant sind.
 * 
 * @author andang
 * @author Tino Hauler
 */
public class ClientsideSettings extends CommonSettings {
	
	/*
	 * Leere Objektvariable, welche es uns erlaubt, die Klasse
	 * PartnerboerseAdministrationAsync einmalig zu instanziieren
	 */
	private static PartnerboerseAdministrationAsync partnerboerseAdministration = null;

	private static PartnerboerseAdministrationAsync partnerboerseVerwaltung = null;

	private static ReportGeneratorAsync reportGenerator = null;
	
	private static Nutzerprofil aktuellerUser = null;


	/**
	 * gibt den aktuelle eingeloggten User zurueck
	 * @return aktuellerUser
	 */
	public static Nutzerprofil getAktuellerUser() {
		return aktuellerUser;
	}

	/**
	 * setzt den aktuell eingeloggten User als User
	 * @param nutzerprofil
	 */
	public static void setAktuellerUser(Nutzerprofil nutzerprofil) {
		ClientsideSettings.aktuellerUser = nutzerprofil;
	}

	/**
	 * Stellt die Verbindung zur Partnerboerse Administration her
	 * @return partnerboerseAdministration stellt Verbindung her
	 */
	public static PartnerboerseAdministrationAsync getPartnerboerseAdministration() {

		if (partnerboerseAdministration == null) {
			partnerboerseAdministration = GWT.create(PartnerboerseAdministration.class);
		}
		return partnerboerseAdministration;
	}
	
	/**
	 * Sollte es noch keine Instanz von PartnerboerseAdministration geben, wird diese
	 * hier erzeugt. Anschliessend wird diese zurueckgegeben.
	 * @return partnerboerseVerwaltung
	 */
	public static PartnerboerseAdministrationAsync getPartnerboerseVerwaltung() {
		/*
		 * Gab es bislang noch keine PartnerboerseAdministration-Instanz,
		 * dann...
		 */
		if (partnerboerseVerwaltung == null) {
			// ... dann instantiieren wir PartnerboerseAdministrationAsync
			partnerboerseVerwaltung = GWT.create(PartnerboerseAdministration.class);
		}

		/*
		 * So, nun brauchen wir die BankAdministration nur noch zur�ckzugeben.
		 */
		return partnerboerseVerwaltung;
	}

	

	// -------------------------------------------------------------------------

	/**
	 * Anlegen und Auslesen des applikationsweit eindeutigen ReportGenerators.
	 * Diese Methode erstellt den ReportGenerator, sofern dieser noch nicht
	 * existiert. Bei wiederholtem Aufruf dieser Methode wird stets das bereits
	 * zuvor angelegte Objekt zurueckgegeben.
	 * 
	 * @return Instanz des Typs ReportGeneratorAsync
	 */
	public static ReportGeneratorAsync getReportGenerator() {

		if (reportGenerator == null) {
			reportGenerator = GWT.create(ReportGenerator.class);
		}
		return reportGenerator;

	}


}

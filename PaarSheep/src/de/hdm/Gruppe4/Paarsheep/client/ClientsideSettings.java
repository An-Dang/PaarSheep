package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.GWT;

import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGenerator;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministration;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGeneratorAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.CommonSettings;

//-------------------------------------------------------------------------

/**
 * @author andang
 *@author Tino Hauler
 */
public class ClientsideSettings extends CommonSettings {
	/*
	 * Klasse mit Eigenschaften und Diensten, die f�r alle Client-seitigen #
	 * Klassen relevant sind.
	 */

	/*
	 * Leere Objektvariable, welche es uns erlaubt, die Klasse
	 * PartnerboerseAdministrationAsync einmalig zu instanziieren
	 */
	private static PartnerboerseAdministrationAsync partnerboerseAdministration = null;
	
	private static PartnerboerseAdministrationAsync partnerboerseVerwaltung = null;

	private static ReportGeneratorAsync reportGenerator = null;
	
	private static Nutzerprofil aktuellerUser = null;

	// -------------------------------------------------------------------------

	/**
	 * @return aktuellerUser
	 */
	public static Nutzerprofil getAktuellerUser() {
		return aktuellerUser;
	}

	/**
	 * @param nutzerprofil
	 */
	public static void setAktuellerUser(Nutzerprofil nutzerprofil) {
		ClientsideSettings.aktuellerUser = nutzerprofil;
	}

	/**
	 * @return partnerboerseAdministration
	 */
	public static PartnerboerseAdministrationAsync getPartnerboerseAdministration() {

		if (partnerboerseAdministration == null) {
			partnerboerseAdministration = GWT.create(PartnerboerseAdministration.class);
		}
		return partnerboerseAdministration;
	}
	
	/**
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

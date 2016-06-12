package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.GWT;

import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministration;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGeneratorAsync;
import de.hdm.Gruppe4.Paarsheep.shared.CommonSettings;

//-------------------------------------------------------------------------

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

	// -------------------------------------------------------------------------

	public static PartnerboerseAdministrationAsync getPartnerboerseAdministration() {

		if (partnerboerseAdministration == null) {
			partnerboerseAdministration = GWT.create(PartnerboerseAdministration.class);
		}
		return partnerboerseAdministration;
	}
	
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

	/*
	 * public static ReportGeneratorAsync getReportGenerator() { 
	 * // Gab es
	 * bislang noch keine ReportGenerator-Instanz, dann... if (reportGenerator
	 * == null) { // ... dann instantiieren wir ReportGenerator reportGenerator
	 * = GWT.create(ReportGenerator.class);
	 * 
	 * // final AsyncCallback<Void> initReportGeneratorCallback = new
	 * AsyncCallback<Void>() { // @Override /* public void onFailure(Throwable
	 * caught) { ClientsideSettings.getLogger().severe(
	 * "Der ReportGenerator konnte nicht initialisiert werden!"); }
	 */

	// @Override
	/*
	 * public void onSuccess(Void result) { ClientsideSettings.getLogger().info(
	 * "Der ReportGenerator wurde initialisiert."); }
	 */
	// };

	// reportGenerator.init(initReportGeneratorCallback);
	// }

	// So, nun brauchen wir den ReportGenerator nur noch zur�ckzugeben.
	// return reportGenerator;

}

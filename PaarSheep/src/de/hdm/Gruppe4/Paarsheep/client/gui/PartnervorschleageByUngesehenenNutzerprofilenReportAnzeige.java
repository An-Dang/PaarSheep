package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.report.HTMLReportWriter;
import de.hdm.Gruppe4.Paarsheep.shared.report.PartnervorschleageByUngesehenenNutzerprofilenReport;

public class PartnervorschleageByUngesehenenNutzerprofilenReportAnzeige extends VerticalPanel {

	
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
	

	/**
	 * Vertikales Panel erzeugen.
	 */
	private VerticalPanel verPanel = new VerticalPanel();

	/**
	 * Widgets erzeugen.
	 */
	private Label infoLabel = new Label();
	private Label ueberschriftLabel = new Label();

	/**
	 * Konstruktor erstellen.
	 */
	public PartnervorschleageByUngesehenenNutzerprofilenReportAnzeige(Nutzerprofil nutzerprofil) {
		this.nutzerprofil=nutzerprofil;
		run();
	}

	/**
	 * Die Methode startet den Aufbau der Seite.
	 */
	public void run() {
		this.add(verPanel);


		ueberschriftLabel.addStyleName("partnerboerse-label");

		reportAusgeben();
		verPanel.add(ueberschriftLabel);
		verPanel.add(infoLabel);
	}

	/**
	 * Report auslesen.
	 */
	public void reportAusgeben() {
		ClientsideSettings.getReportGenerator().createPartnervorschleageByUngesehenenNutzerprofilenReport(nutzerprofil,
				new AsyncCallback<PartnervorschleageByUngesehenenNutzerprofilenReport>() {

					@Override
					public void onFailure(Throwable caught) {
						infoLabel.setText("Es trat ein Fehler auf.");
					}

					@Override
					public void onSuccess(PartnervorschleageByUngesehenenNutzerprofilenReport report) {
						if (report != null) {
							/*
							 * Neue HTML-Seite fuer den Report erzeugen.
							 */
							HTMLReportWriter writer = new HTMLReportWriter();
							writer.process(report);
							RootPanel.get("Details").clear();
							RootPanel.get("Details").add(new HTML(writer.getReportText()));
						}
					}
				});
	}
}

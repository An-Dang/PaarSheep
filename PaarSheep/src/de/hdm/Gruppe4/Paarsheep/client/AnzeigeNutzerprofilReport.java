package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.report.HTMLReportWriter;
import de.hdm.Gruppe4.Paarsheep.shared.report.ProfilInfoByNutzerprofilReport;

/**
 * @author andang
 *
 */
public class AnzeigeNutzerprofilReport extends HorizontalPanel{
	/**
	 * Neues Nutzerprofil-Objekt anlegen mit Login-Infos.
	 */
	private Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	/**
	 * VerticalPanel hinzufügen.
	 */
	private VerticalPanel verPanel = new VerticalPanel();

	/**
	 * Label zur Information hinzufügen.
	 */
	private Label infoLabel = new Label();
	private Label ueberschriftLabel = new Label();
	
	/**
	 * 
	 */
	public AnzeigeNutzerprofilReport(){
		run();
	}
	
	/**
	 * 
	 */
	public void run(){
		this.add(verPanel);

		ueberschriftLabel.setText("NutzerprofilAusgabe");
		ueberschriftLabel.addStyleName("partnerboerse-label");

		reportAuslesen();
		verPanel.add(ueberschriftLabel);
		verPanel.add(infoLabel);
	}
	
	/**
	 * Report auslesen.
	 */
	
	public void reportAuslesen() {
		ClientsideSettings.getReportGenerator().createProfilInfoByNutzerprofilReport(nutzerprofil, 100,
				new AsyncCallback<ProfilInfoByNutzerprofilReport>() {
			
					public void onFailure(Throwable caught) {
						infoLabel.setText("Es trat ein Fehler auf.");
					}
					
					public void onSuccess(ProfilInfoByNutzerprofilReport report) {
						if (report != null) {
							/*
							 * Neue HTML-Seite fuer den Report erzeugen.
							 */
							HTMLReportWriter writer = new HTMLReportWriter();
							writer.process(report);
							RootPanel.get("Container").clear();
							RootPanel.get("Container").add(new HTML(writer.getReportText()));
						}
					}
				});
	}
}

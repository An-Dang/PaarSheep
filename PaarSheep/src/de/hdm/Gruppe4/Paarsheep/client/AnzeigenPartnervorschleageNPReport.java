package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.report.AllPartnervorschlaegeNpReport;
import de.hdm.Gruppe4.Paarsheep.shared.report.HTMLReportWriter;
/**
 * Die Klasse AnzeigenPartnervorschlaegeNPReport erweitert das VerticalPanel.
 * @author andang
 *
 */
public class AnzeigenPartnervorschleageNPReport extends VerticalPanel{

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
	 * Konstruktor
	 */
	public AnzeigenPartnervorschleageNPReport(){
		run();
	}
	
	/**
	 * Zum Ausgeben des Reports wird ein Text an den User gegeben
	 */
	public void run(){
		this.add(verPanel);

		ueberschriftLabel.setText("Einen kleinen Moment Bitte....");
		ueberschriftLabel.addStyleName("partnerboerse-label");

		reportAuslesen();
		verPanel.add(ueberschriftLabel);
		verPanel.add(infoLabel);
	}
	
	/**
	 * Report auslesen.
	 */
	public void reportAuslesen() {
		ClientsideSettings.getReportGenerator().createAllPartnervorschlaegeNpReport(nutzerprofil,
				new AsyncCallback<AllPartnervorschlaegeNpReport>() {
			
			/** Fehler abfangen; Fehlermeldung ausgeben */
					public void onFailure(Throwable caught) {
						infoLabel.setText("Es trat ein Fehler auf.");
					}
					
					/**
					 * Report ausgeben
					 * @param writer HTML Seite fuer Report erzeugen und Report ausgeben
					 */
					public void onSuccess(AllPartnervorschlaegeNpReport report) {
						if (report != null) {
							/**
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

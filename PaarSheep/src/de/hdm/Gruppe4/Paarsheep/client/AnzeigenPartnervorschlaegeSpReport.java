package de.hdm.Gruppe4.Paarsheep.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;
import de.hdm.Gruppe4.Paarsheep.shared.report.HTMLReportWriter;
import de.hdm.Gruppe4.Paarsheep.shared.report.PartnervorschlaegeSpReport;

/**
 * Die Klasse AnzeigenPartnervorschlaegeSpReport erweitert das VerticalPanel.
 * @author andang
 *
 */
public class AnzeigenPartnervorschlaegeSpReport extends VerticalPanel {

	/**
	 * Neues Nutzerprofil-Objekt anlegen mit Login-Infos.
	 */
	private Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
	Suchprofil suchprofil = new Suchprofil();

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
	 * Konstruktor zum anzeigen des Reports
	 */
	public AnzeigenPartnervorschlaegeSpReport() {
		run();
	}

	/**
	 * alle angelegten Suchprofile werden im Report ausgegeben.
	 * @param auswahlPanel VerticalPanel erweitern um Auswahl Moeglichkeit
	 * @param auswahlListBox zum auswaehlen der Suchprofile
	 * @param anzeigenButton zum bestaetigen der Auswahl eines Profils, welches angezeigt wird
	 */
	public void run() {
		this.add(verPanel);
		
		final VerticalPanel auswahlPanel = new VerticalPanel();
		final ListBox auswahlListBox = new ListBox();
		final Button anzeigenButton = new Button("Auswahl");
		
		/**
		 * Suchprofile auslesen und anzeigen
		 */
			ClientsideSettings.getPartnerboerseAdministration().findSuchprofilByNutzerID(nutzerprofil.getProfilID(),
					new AsyncCallback<ArrayList<Suchprofil>>() {
						

						/**
						 * Fehler abfangen
						 */
						public void onFailure(Throwable caught) {

						}
						
						/**
						 * Falls kein Suchprofil vorhanden ist, wird User darauf hingewiesen,
						 * eines zu erstellen.
						 * Ansonsten werden Suchprofile ausgelesen und ausgegeben
						 */
						public void onSuccess(ArrayList<Suchprofil> result) {
							if (result.isEmpty()) {
								auswahlListBox.setVisible(false);
								anzeigenButton.setVisible(false);
								Window.alert("Sie haben bisher noch kein Suchprofil angelegt");
							} else {
								// suchprofile = result;
								for (final Suchprofil suchprofil : result) {
									auswahlListBox.addItem(suchprofil.getSuchprofilName());
									final int suchprofilID = suchprofil.getProfilID();
									
									/**
									 * Button dem ClickHandler hinzufuegen
									 */
									anzeigenButton.addClickHandler(new ClickHandler(){
										public void onClick(ClickEvent event) {
											ClientsideSettings.getReportGenerator().createPartnervorschleageBySpReport(nutzerprofil, suchprofil, 
													new AsyncCallback<PartnervorschlaegeSpReport>(){
												
														/**
														 * Fehler abfangen
														 */
														public void onFailure(Throwable caught) {
															infoLabel.setText("Es trat ein Fehler auf.");
														}
														
														/**
														 * Suchprofile anzeigen lassen
														 * @param writer HTML Seite fuer Ausgabe erzeugen
														 */
														public void onSuccess(PartnervorschlaegeSpReport report) {
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
									});
								}
							}
						}
					});
			
		RootPanel.get("Details").clear();
		RootPanel.get("Details").add(auswahlPanel);
		ueberschriftLabel.setText("Einen kleinen Moment Bitte....");
		ueberschriftLabel.addStyleName("partnerboerse-label");

//		reportAuslesen();
		auswahlPanel.add(auswahlListBox);
		auswahlPanel.add(anzeigenButton);
		verPanel.add(ueberschriftLabel);
		verPanel.add(infoLabel);
	}

	/**
	 * Report auslesen.
	 */
	
//	public void reportAuslesen() {
//		ClientsideSettings.getReportGenerator().createPartnervorschleageBySpReport(nutzerprofil, suchprofil, 
//				new AsyncCallback<PartnervorschlaegeSpReport>(){
//			
//					public void onFailure(Throwable caught) {
//						infoLabel.setText("Es trat ein Fehler auf.");
//					}
//					public void onSuccess(PartnervorschlaegeSpReport report) {
//						if (report != null) {
//							/*
//							 * Neue HTML-Seite fuer den Report erzeugen.
//							 */
//							HTMLReportWriter writer = new HTMLReportWriter();
//							writer.process(report);
//							RootPanel.get("Container").clear();
//							RootPanel.get("Container").add(new HTML(writer.getReportText()));
//						}
//					}
//		});
//	}
}
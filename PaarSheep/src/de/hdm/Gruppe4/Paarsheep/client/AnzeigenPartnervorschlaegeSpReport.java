package de.hdm.Gruppe4.Paarsheep.client;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Benutzer;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;
import de.hdm.Gruppe4.Paarsheep.shared.report.*;

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
	 * 
	 */
	public AnzeigenPartnervorschlaegeSpReport(){
		run();
	}
	
	/**
	 * 
	 */
	public void run(){
		this.add(verPanel);

		ueberschriftLabel.setText("Ingo Stinkt!#");
		ueberschriftLabel.addStyleName("partnerboerse-label");

//		reportAuslesen();
		verPanel.add(ueberschriftLabel);
		verPanel.add(infoLabel);
	}
	
	/**
	 * Report auslesen.
	 */
	
//	public void reportAuslesen() {
//		ClientsideSettings.getReportGenerator().createPartnervorschleageBySuchprofilReport(nutzerprofil,suchprofil,
//				new AsyncCallback<AnzeigenPartnervorschlaegeSpReport>() {
//			
//					public void onFailure(Throwable caught) {
//						infoLabel.setText("Es trat ein Fehler auf.");
//					}
//					
//					public void onSuccess(AnzeigenPartnervorschlaegeSpReport report) {
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
//				});
//	}
}
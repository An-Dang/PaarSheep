package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Formular für die Darstellung der zu bearbeitenden Profilinformationen
 * 
 * @author Marcel Pleyer
 */

public class ProfilBearbeiten {
	public void loadProfilEditieren() {

		// VerticalPanel links für Steckbriefinformationen
		VerticalPanel vPanellinks = new VerticalPanel();

		// VerticalPanel rechts für zusätzliche Informationen
		VerticalPanel vPanelrechts = new VerticalPanel();
		HorizontalPanel hPanel = new HorizontalPanel();

		// Widgets werden erstellt

		// Label platzhalterLabel = new Label("");

		Label profilLabel = new Label("Dein Profil");

		Label steckbriefLabel = new Label("Steckbrief");
		vPanellinks.add(steckbriefLabel);

		Label vornameLabel = new Label("Vorname: ");
		Label vornameErgebnisLbl = new Label("Wert");
		vPanellinks.add(vornameLabel);
		vPanellinks.add(vornameErgebnisLbl);

		Label nachnameLabel = new Label("Nachname: ");
		Label nachnameLbl = new Label("Wert");
		vPanellinks.add(nachnameLabel);
		vPanellinks.add(nachnameLbl);

		Label koerpergroesseLabel = new Label("Körpergröße: ");
		TextBox koerpergroesseErgebnisTb = new TextBox();
		vPanellinks.add(koerpergroesseLabel);
		vPanellinks.add(koerpergroesseErgebnisTb);

		Label haarfarbeLabel = new Label("Haarfarbe: ");
		ListBox haarfarbeErgebnisLb = new ListBox();
		vPanellinks.add(haarfarbeLabel);
		vPanellinks.add(haarfarbeErgebnisLb);

		Label raucherLabel = new Label("Raucher: ");
		ListBox raucherErgebnisLb = new ListBox();
		vPanellinks.add(raucherLabel);
		vPanellinks.add(raucherErgebnisLb);

		Label religionLabel = new Label("Religion: ");
		ListBox religionErgebnisLb = new ListBox();
		vPanellinks.add(religionLabel);
		vPanellinks.add(religionErgebnisLb);

		// Zusätzliche Informationen
		Label zusinfLabel = new Label("Zusätzliche Informationen ");

		vPanelrechts.add(zusinfLabel);

		Label buchLabel = new Label("Mein Lieblingsbuch ist: ");
		TextBox buchErgebnisTb = new TextBox();
		vPanelrechts.add(buchLabel);
		vPanelrechts.add(buchErgebnisTb);

		Label urlaubLabel = new Label("Mein Lieblingsurlaubsort ist: ");
		TextBox urlaubErgebnisTb = new TextBox();
		vPanelrechts.add(urlaubLabel);
		vPanelrechts.add(urlaubErgebnisTb);

		Label sportLabel = new Label("Sport: ");
		TextBox sportErgebnisTb = new TextBox();
		vPanelrechts.add(sportLabel);
		vPanelrechts.add(sportErgebnisTb);

		Label musikLabel = new Label("Musik: ");
		TextBox musikErgebnisTb = new TextBox();
		vPanelrechts.add(musikLabel);
		vPanelrechts.add(musikErgebnisTb);

		Button speichernBtn = new Button("speichern", new ProfilinfoSpeichernClickHandler());
		vPanelrechts.add(speichernBtn);
		hPanel.add(vPanelrechts);

		Button abbrechenBtn = new Button("abbrechen", new ProfilinfoAbbrechenClickHandler());
		vPanelrechts.add(abbrechenBtn);
		hPanel.add(vPanelrechts);

		// Übergabe der Widgets/ Panels and das RootPanel
		RootPanel.get("Steckbrief").add(vPanellinks);
		RootPanel.get("Zusinf").add(vPanelrechts);

		RootPanel.get("Zusinf").add(hPanel);
	}

	private class ProfilinfoSpeichernClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub

		}

	}

	private class ProfilinfoAbbrechenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			final ProfilseiteForm profilseiteForm = new ProfilseiteForm();
			final ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();

			profilBearbeiten.loescheEditInhalt();
			profilseiteForm.loadProfilInformationen();

		}

	}

	public void loescheEditInhalt() {
		RootPanel.get("Steckbrief").clear();
		RootPanel.get("Zusinf").clear();
		RootPanel.get("Profil").clear();
	}

}

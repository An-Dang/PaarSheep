package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Formular für die Darstellung der angegebenen Profilinformationen
 * 
 * @author Marcel Pleyer
 */

public class ProfilseiteForm {
	// private ProfilBearbeiten pb;

	public void loadProfilInformationen() {

		// this.pb = pb;
		// VerticalPanel links für Steckbriefinformationen
		VerticalPanel vPanellinks = new VerticalPanel();

		// VerticalPanel rechts für zusätzliche Informationen
		VerticalPanel vPanelrechts = new VerticalPanel();
		HorizontalPanel hPanel = new HorizontalPanel();

		VerticalPanel vpPanel = new VerticalPanel();

		// Widgets werden erstellt

		// Label platzhalterLabel = new Label("");

		Label profilLabel = new Label("Dein Profil");
		vpPanel.add(profilLabel);
		RootPanel.get().add(vpPanel);

		// Widgets für Steckbrief

		Label steckbriefLabel = new Label("Steckbrief");
		vPanellinks.add(steckbriefLabel);

		Label vornameLabel = new Label("Vorname: ");
		Label vornameErgebnisLbl = new Label("Wert");
		vPanellinks.add(vornameLabel);
		vPanellinks.add(vornameErgebnisLbl);

		Label nachnameLabel = new Label("Nachname: ");
		Label nachnameTb = new Label();
		vPanellinks.add(nachnameLabel);
		vPanellinks.add(nachnameTb);

		Label koerpergroesseLabel = new Label("Körpergröße: ");
		Label koerpergroesseErgebnisLbl = new Label("Wert");
		vPanellinks.add(koerpergroesseLabel);
		vPanellinks.add(koerpergroesseErgebnisLbl);

		Label haarfarbeLabel = new Label("Haarfarbe: ");
		Label haarfarbeErgebnisLbl = new Label("Wert");
		vPanellinks.add(haarfarbeLabel);
		vPanellinks.add(haarfarbeErgebnisLbl);

		Label raucherLabel = new Label("Raucher: ");
		Label raucherErgebnisLbl = new Label("Wert");
		vPanellinks.add(raucherLabel);
		vPanellinks.add(raucherErgebnisLbl);

		Label religionLabel = new Label("Religion: ");
		Label religionErgebnisLbl = new Label("Wert");
		vPanellinks.add(religionLabel);
		vPanellinks.add(religionErgebnisLbl);

		// Widgets für zusätzliche Informationen
		Label zusinfLabel = new Label("Zusätzliche Informationen ");

		vPanelrechts.add(zusinfLabel);

		Label buchLabel = new Label("Mein Lieblingsbuch ist: ");
		Label buchErgebnisLbl = new Label("Wert");

		vPanelrechts.add(buchLabel);
		vPanelrechts.add(buchErgebnisLbl);

		Label urlaubLabel = new Label("Mein Lieblingsurlaubsort ist: ");
		Label urlaubErgebnisLbl = new Label("Wert");
		vPanelrechts.add(urlaubLabel);
		vPanelrechts.add(urlaubErgebnisLbl);

		Label sportLabel = new Label("Sport: ");
		Label sportErgebnisLbl = new Label("Wert");
		vPanelrechts.add(sportLabel);
		vPanelrechts.add(sportErgebnisLbl);

		Label musikLabel = new Label("Musik: ");
		Label musikErgebnisLbl = new Label("Wert");
		vPanelrechts.add(musikLabel);
		vPanelrechts.add(musikErgebnisLbl);

		// Button zum Bearbeiten der Profilinformationen
		Button aendernBtn = new Button("bearbeiten", new ProfilinfoClickHandler());
		vPanelrechts.add(aendernBtn);
		hPanel.add(vPanelrechts);

		// Übergabe der Widgets/ Panels and das RootPanel

		// Notiz: RootPanel.get.clear(); funktioniert nicht, wenn man sich etwas
		// dem div Container hinzugefügt hat.
		RootPanel.get("Steckbrief").add(vPanellinks);
		RootPanel.get("Zusinf").add(vPanelrechts);
		RootPanel.get("Profil").add(vpPanel);

		// RootPanel.get().add(vPanellinks);
		// hPanel.add(vPanelrechts);
		// RootPanel.get().add(hPanel);

		// RootPanel.get("Zusinf").add(hPanel);

		// Profilinfo ändern Button ClickHandler

	}

	private class ProfilinfoClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			final ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
			final ProfilseiteForm profilseiteForm = new ProfilseiteForm();
			profilseiteForm.loescheInhalt();
			// pb.loadProfilEditieren();
			profilBearbeiten.loadProfilEditieren();

		}

	}

	/*
	 * Hiermit wird der Inhalt der Profil Informationen gelöscht, somit kann das
	 * Profil bearbeitet werden, ohne dass die vorherigen Informationen
	 * angezeigt werden.
	 */
	public void loescheInhalt() {
		RootPanel.get("Steckbrief").clear();
		RootPanel.get("Zusinf").clear();
		RootPanel.get("Profil").clear();
	}
}

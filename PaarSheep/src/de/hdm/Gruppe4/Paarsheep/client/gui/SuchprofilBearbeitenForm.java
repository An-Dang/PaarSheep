package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

/**
 * Klasse zur Bearbeitung der Suchprofilinformationen
 * @author Dominik Sasse
 *
 */

public class SuchprofilBearbeitenForm {

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	ListBox geschlechtListBox = new ListBox();
	TextBox koerpergroessevonTextBox = new TextBox();
	TextBox koerpergroessebisTextBox = new TextBox();
	TextBox altervonTextBox = new TextBox();
	TextBox alterbisTextBox = new TextBox();
	TextBox haarfarbeTextBox = new TextBox();
	ListBox raucherListBox = new ListBox();
	ListBox religionListBox = new ListBox();
	
	Label idValueLabel = new Label();
	VerticalPanel vertPanel = new VerticalPanel();

	private HorizontalPanel horPanel = new HorizontalPanel();

	private FlexTable suchprofilAnzeigen = new FlexTable();
	private VerticalPanel vertpanel = new VerticalPanel();

	private Label geschlechtLabel = new Label("Geschlecht: ");
	private Label altervonLabel = new Label("Mindestalter: ");
	private Label alterbisLabel = new Label("Höchstalter: ");
	private Label religionLabel = new Label("Religion: ");
	private Label haarfarbeLabel = new Label("Haarfarbe: ");
	private Label raucherLabel = new Label("Raucher: ");
	private Label koerpergroessevonLabel = new Label("Mindestgröße: ");
	private Label koerpergroessebisLabel = new Label("Höchstgröße: ");

	
	private Button speichernButton = new Button("speichern");
	private Button abbrechenButton = new Button("abbrechen");
	
	private String geschlecht = null;
	private int altervon = 0;
	private int alterbis = 0;
	private int koerpergroessevon = 0;
	private int koerpergroessebis = 0;
	private String religion = null;
	private String haarfarbe = null;
	private String raucher = null;
	
	
	public void suchprofilEditieren(Suchprofil suchprofil){
		final Suchprofil gesuchtesprofil = suchprofil;
		
		
		
		//Erstes RootPanel ändern auf "SuchprofilErstellenForm"?
		
		RootPanel.get("NutzerForm").clear();
		RootPanel.get("Profil").clear();
		//RootPanel.get("ZusInf").clear();
		RootPanel.get("Steckbrief").clear();
		
		
		Label profilLabel = new Label("Dein Suchprofil");
		vertPanel.add(profilLabel);
		
		//Ändern auf "Suchprofil"?
		RootPanel.get("Profil").add(vertPanel);
		
		
		
		suchprofilAnzeigen.setText(0, 0, "Attribut");
		suchprofilAnzeigen.setText(0, 1, "Inhalt");
		suchprofilAnzeigen.setText(0, 2, "neuer Inhalt");
		
		suchprofilAnzeigen.setWidget(1, 0, geschlechtLabel);
		suchprofilAnzeigen.setText(1, 1, suchprofil.getGeschlecht());
		suchprofilAnzeigen.setWidget(1, 2, geschlechtListBox);
		
		suchprofilAnzeigen.setWidget(2, 0, altervonLabel);
		altervonLabel.setText(String.valueOf(suchprofil.getAltervon()));
		suchprofilAnzeigen.setWidget(2, 1, altervonLabel);
		suchprofilAnzeigen.setWidget(2, 2, altervonTextBox);
		
		suchprofilAnzeigen.setWidget(3, 0, alterbisLabel);
		alterbisLabel.setText(String.valueOf(suchprofil.getAlterbis()));
		suchprofilAnzeigen.setWidget(3, 1, alterbisLabel);
		suchprofilAnzeigen.setWidget(3, 2, alterbisTextBox);
		
		suchprofilAnzeigen.setWidget(4, 0, religionLabel);
		suchprofilAnzeigen.setText(4, 1, suchprofil.getReligion());
		suchprofilAnzeigen.setWidget(4, 2, religionListBox);
		
		suchprofilAnzeigen.setWidget(5, 0, haarfarbeLabel);
		suchprofilAnzeigen.setText(5, 1, suchprofil.getHaarfarbe());
		suchprofilAnzeigen.setWidget(5, 2, haarfarbeTextBox);

		suchprofilAnzeigen.setWidget(6, 0, raucherLabel);
		suchprofilAnzeigen.setText(6, 1, suchprofil.getRaucher());
		suchprofilAnzeigen.setWidget(6, 2, raucherListBox);

		suchprofilAnzeigen.setWidget(7, 0, koerpergroessevonLabel);
		koerpergroessevonLabel.setText(String.valueOf(suchprofil.getKoerpergroessevon()));
		suchprofilAnzeigen.setWidget(7, 1, koerpergroessevonLabel);
		suchprofilAnzeigen.setWidget(7, 2, koerpergroessevonTextBox);
		
		suchprofilAnzeigen.setWidget(8, 0, koerpergroessebisLabel);
		koerpergroessebisLabel.setText(String.valueOf(suchprofil.getKoepergroessebis()));
		suchprofilAnzeigen.setWidget(8, 1, koerpergroessebisLabel);
		suchprofilAnzeigen.setWidget(8, 2, koerpergroessebisTextBox);
		
		
		vertpanel.add(suchprofilAnzeigen);

		RootPanel.get("Steckbrief").add(vertpanel);


			horPanel.add(speichernButton);
			horPanel.add(abbrechenButton);

		RootPanel.get("Steckbrief").add(horPanel);
	
		//---------------------------------------------------------------------
		
		speichernButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				speicherSuchprofil(suchprofil);
			}
		});

		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loadSuchprofilBearbeiten(suchprofil);
			}
		});

		
	}
	private void loadSuchprofilBearbeiten(Suchprofil suchprofil) {
		Suchprofil suchendesprofil = suchprofil;
		SuchprofilBearbeitenForm suchprofilBearbeiten = new SuchprofilBearbeitenForm();
		suchprofilBearbeiten.suchprofilEditieren(suchprofil);
		
	}
	
	 
	private void speicherSuchprofil(Suchprofil suchprofil) {
		final Suchprofil suchendesprofil = suchprofil;
		
		String test = "Die Methode funktioniert";
		Window.alert(test);
		
		geschlecht = geschlechtListBox.getSelectedItemText();
		
		altervon = Integer.parseInt(altervonTextBox.getSelectedText());
		alterbis = Integer.parseInt(alterbisTextBox.getSelectedText());
		
		religion = religionListBox.getSelectedItemText();
		haarfarbe = haarfarbeTextBox.getText();
		raucher = raucherListBox.getSelectedItemText();
		
		koerpergroessevon = Integer.parseInt(koerpergroessevonTextBox.getSelectedText());
		koerpergroessebis = Integer.parseInt(koerpergroessebisTextBox.getSelectedText());
		
		
		//Test aus "ProfilBearbeiten" Klasse
		//koerpergroesseString = koerpergroesseTextBox.getText();
		//koerpergroesse = Integer.parseInt(koerpergroesseString);
		

		if (geschlecht != "") {
			suchprofil.setGeschlecht(geschlecht);
			}
		if (altervon != 0){
			suchprofil.setAltervon(altervon);
		}
		if (alterbis != 0){
			suchprofil.setAlterbis(alterbis);
		}
		if (religion != "") {
			suchprofil.setReligion(religion);
			}
		if (haarfarbe != "") {
			suchprofil.setHaarfarbe(haarfarbe);
			}
		if (raucher != "") {
		suchprofil.setRaucher(raucher);
			}
		if (koerpergroessevon != 0){
			suchprofil.setKoerpergroessevon(koerpergroessevon);
		}
		if (koerpergroessebis != 0){
			suchprofil.setKoerpergroessebis(koerpergroessebis);
		}
		
		
		
		partnerboerseVerwaltung.bearbeiteSuchprofil(suchprofil, new BearbeiteSuchprofilCallback());

	}
}
	//Diese Methode organisiert den asynchronen Callback und gibt uns eine
	//Nachricht aus, ob dieser Callback funktioniert
	class BearbeiteSuchprofilCallback implements AsyncCallback<Suchprofil> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Bearbeiten des Nutzers ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Suchprofil suchprofil) {
			Suchprofil suchendesprofil = suchprofil;
			
			if (suchprofil != null) {
				Startseite startseite = new Startseite();
				startseite.ladeStartseite(suchprofil);

				Window.alert("Das Bearbeiten des Nutzers war erfolgreich!");
			}
		} 
	
}
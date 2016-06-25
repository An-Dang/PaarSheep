package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.Date;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
//import de.hdm.Gruppe4.Paarsheep.client.gui.SuchprofilAnzeigen.AnzeigenHandler;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Nutzerprofilbearbeiten
 * 
 * @author andang
 *
 */
public class ProfilBearbeiten extends VerticalPanel {

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	private FlexTable profilBearbeitenFlexTable = new FlexTable();

	private VerticalPanel vpPanel = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private HorizontalPanel EButtonPanel = new HorizontalPanel();
	

	private TextBox vornameTextBox = new TextBox();
	private TextBox nachnameTextBox = new TextBox();
	private IntegerBox koerpergroesseIntegerBox = new IntegerBox();
	private TextBox haarfarbeTextBox = new TextBox();
	private ListBox raucherListBox = new ListBox();
	private ListBox religionListBox = new ListBox();
	private ListBox geschlechtListBox = new ListBox();
	private DateTimeFormat geburtsdatumFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
	private DateBox geburtsdatumDateBox = new DateBox();
	private Label geburtsdatumInhalt = new Label();
	private Label Eigenschaftsauswahl = new Label("Wähle eine Zusatzeigenschaft aus:");
	private Label infoLabel = new Label();

	private Button profilBearbeitenButton = new Button("Speichern");
	private Button abbrechenButton = new Button("Abbrechen");

	private Button profilinfoButton = new Button("Profilinfo");
	private Button profilInfoBearbeitenButton = new Button ("Profilinfo Bearbeiten");

	/**
	 * 
	 */
	public ProfilBearbeiten() {

			this.add(vpPanel);
			
		/**
		 * Erste Spalte profilBearbeitenFlexTable
		 */
		profilBearbeitenFlexTable.setText(0, 0, "Vorname: ");
		profilBearbeitenFlexTable.setText(1, 0, "Nachname: ");
		profilBearbeitenFlexTable.setText(2, 0, "Geschlecht: ");
		profilBearbeitenFlexTable.setText(3, 0, "Geburtsdatum: ");
		profilBearbeitenFlexTable.setText(4, 0, "Körpergröße: ");
		profilBearbeitenFlexTable.setText(5, 0, "Haarfarbe: ");
		profilBearbeitenFlexTable.setText(6, 0, "Religion: ");
		profilBearbeitenFlexTable.setText(7, 0, "Raucher: ");

		/**
		 * zweite dritte Spalte profilBearbeitenFlexTable
		 */
		profilBearbeitenFlexTable.setWidget(0, 2, vornameTextBox);
		profilBearbeitenFlexTable.setWidget(1, 2, nachnameTextBox);

		geschlechtListBox.addItem("Männlich");
		geschlechtListBox.addItem("Weiblich");
		geschlechtListBox.addItem("Andere");
		profilBearbeitenFlexTable.setWidget(2, 2, geschlechtListBox);

		geburtsdatumDateBox.setFormat(new DateBox.DefaultFormat(geburtsdatumFormat));
		geburtsdatumDateBox.getDatePicker().setYearAndMonthDropdownVisible(true);
		geburtsdatumDateBox.getDatePicker().setVisibleYearCount(20);
		
		geburtsdatumDateBox.setValue(new Date());

		geburtsdatumDateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date geburtsdatum = event.getValue();
				String geburtsdatumString = DateTimeFormat.getFormat("dd.MM.yyyy").format(geburtsdatum);
				geburtsdatumInhalt.setText(geburtsdatumString);

				if (event.getValue().after(today())) {
					geburtsdatumDateBox.setValue(today(), false);
				}
			}
		});

		profilBearbeitenFlexTable.setWidget(3, 2, geburtsdatumDateBox);
		
		profilBearbeitenFlexTable.setWidget(4, 2, koerpergroesseIntegerBox);
		koerpergroesseIntegerBox.setValue(nutzerprofil.getKoerpergroesse());

		profilBearbeitenFlexTable.setWidget(5, 2, haarfarbeTextBox);

		religionListBox.addItem("Keine Angabe");
		religionListBox.addItem("Christentum");
		religionListBox.addItem("Islam");
		religionListBox.addItem("Judentum");
		religionListBox.addItem("Buddhismus");
		religionListBox.addItem("Hinduismus");
		religionListBox.addItem("Andere");
		profilBearbeitenFlexTable.setWidget(6, 2, religionListBox);

		raucherListBox.addItem("Ja");
		raucherListBox.addItem("Nein");
		raucherListBox.addItem("Keine Angabe");
		profilBearbeitenFlexTable.setWidget(7, 2, raucherListBox);
		
		
		profilDatenauslesen();
		
		profilBearbeitenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				aktualisiereNutzerprofil();
				
				RootPanel.get("Profil").clear();
				RootPanel.get("NutzerForm").clear();
				Startseite ladeStartseite = new Startseite();
				ladeStartseite.ladeStartseite();
			}
		}); 
		
		profilInfoBearbeitenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("Profil").clear();
				RootPanel.get("NutzerForm").clear();
				ProfilInfoBearbeiten profilInfoBearbeiten = new ProfilInfoBearbeiten();
				profilInfoBearbeiten.ladeProfilInfoBearbeiten();
			}
		});
		
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				RootPanel.get("Profil").clear();
				RootPanel.get("NutzerForm").clear();
				Startseite ladeStartseite = new Startseite();
				ladeStartseite.ladeStartseite();
			}
		});
		
		profilinfoButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("Profil").clear();
				RootPanel.get("NutzerForm").clear();
				ProfilInfo profilInfo = new ProfilInfo();
				RootPanel.get("NutzerForm").add(profilInfo);
			}
		});
		
		vpPanel.add(profilBearbeitenFlexTable);
		vpPanel.add(profilBearbeitenFlexTable);
		vpPanel.add(buttonPanel);
		
		buttonPanel.add(profilBearbeitenButton);
		buttonPanel.add(abbrechenButton);
		}
		
		
	public void profilDatenauslesen(){
		partnerboerseVerwaltung.getNutzerprofilById(nutzerprofil.getProfilID(), new AsyncCallback<Nutzerprofil>() {

			public void onFailure(Throwable caught) {
				Window.alert("Es trat ein Fehler auf");
			}

			public void onSuccess(Nutzerprofil result) {
				
				vornameTextBox.setText(result.getVorname());

				nachnameTextBox.setText(result.getNachname());

				for (int i = 0; i < geschlechtListBox.getItemCount(); i++) {
					if (result.getGeschlecht().equals(geschlechtListBox.getValue(i))) {
						geschlechtListBox.setSelectedIndex(i);
					}
				}
				
				Date geburtsdatum = result.getGeburtsdatum(); 
				String geburtsdatumString = DateTimeFormat.getFormat("dd.MM.yyyy").format(geburtsdatum);
				
				geburtsdatumDateBox.setValue(geburtsdatum);
				
				geburtsdatumInhalt.setText(geburtsdatumString);
				
				koerpergroesseIntegerBox.setValue(result.getKoerpergroesse());

				haarfarbeTextBox.setText(result.getHaarfarbe());

				for (int i = 0; i < religionListBox.getItemCount(); i++) {
					if (result.getReligion().equals(religionListBox.getValue(i))) {
						religionListBox.setSelectedIndex(i);
					}
				}

				for (int i = 0; i < raucherListBox.getItemCount(); i++) {
					if (result.getRaucher().equals(raucherListBox.getValue(i))) {
						raucherListBox.setSelectedIndex(i);
					}
				}

			}

		});
		
		vpPanel.add(profilBearbeitenFlexTable);
		vpPanel.add(profilBearbeitenFlexTable);
		vpPanel.add(buttonPanel);
		
		buttonPanel.add(profilBearbeitenButton);
		buttonPanel.add(abbrechenButton);
	}

		public void aktualisiereNutzerprofil(){
					partnerboerseVerwaltung.bearbeiteNutzerprofil(nutzerprofil.getProfilID(), vornameTextBox.getText(),
							nachnameTextBox.getText(), geschlechtListBox.getSelectedItemText(), getGeburtsdatum(),
							koerpergroesseIntegerBox.getValue(), haarfarbeTextBox.getText(),
							raucherListBox.getSelectedItemText(), religionListBox.getSelectedItemText(),
							new AsyncCallback<Void>() {

								public void onFailure(Throwable caught) {

									Window.alert("Es trat ein Fehler auf!");
								}

								public void onSuccess(Void result) {
//									RootPanel.get("NutzerForm").clear();
//									RootPanel.get("Profil").clear();
//									RootPanel.get("EigenschaftForm").clear();
//									Window.alert("Erfolgreich Aktualisiert!");
//									
										

								}
							});
					}
		

	/**
	 * Geburtsdatum
	 */
	Date getGeburtsdatum() {
		Date geburtsdatum = geburtsdatumFormat.parse(geburtsdatumInhalt.getText());
		java.sql.Date sqlDate = new java.sql.Date(geburtsdatum.getTime());
		return sqlDate;
	}
	
	private static Date today() {
		return zeroTime(new Date());
	}

	private static Date zeroTime(Date date) {
		return DateTimeFormat.getFormat("yyyyMMdd").parse(DateTimeFormat.getFormat("yyyyMMdd").format(date));
	}

}

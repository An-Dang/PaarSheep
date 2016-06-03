package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

/**
 * Formular f�r die Darstellung der zu bearbeitenden Profilinformationen
 * 
 * @author Marcel Pleyer
 */

public class ProfilBearbeiten  {
	
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	private TextBox vornameTextBox = new TextBox();
	private TextBox nachnameTextBox = new TextBox();
	private IntegerBox koerpergroesseIntegerBox = new IntegerBox();
	private TextBox haarfarbeTextBox = new TextBox();
	private ListBox raucherListBox = new ListBox();
	private ListBox religionListBox = new ListBox();
	private ListBox geschlechtListBox = new ListBox();

	private DateTimeFormat geburtsdatumFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
	private DateBox geburtsdatumDateBox = new DateBox();
	private Label geburtsdatumInhalt = new Label(); 
	
	private VerticalPanel vpPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();

	private FlexTable nutzerAnzeigen = new FlexTable();
	private VerticalPanel vpanel = new VerticalPanel();

	private Label vornameLabel = new Label("Vorname: ");
	private Label nachnameLabel = new Label("Nachname: ");
	private Label geschlechtLabel = new Label("Geschlecht: ");
	private Label religionLabel = new Label("Religion: ");
	private Label koerpergroesseLabel = new Label("Körpergröße: ");
	private Label raucherLabel = new Label("Raucher: ");
	private Label geburtsdatumLabel = new Label("Geburtsdatum: ");
	private Label haarfarbeLabel = new Label("Haarfarbe: ");

	private Button speichernBtn = new Button("speichern");
	private Button abbrechenBtn = new Button("abbrechen");
	
	private String vorname = null;
	private String nachname = null;
	private Date geburtsdatum = null;
	private String geschlecht = null;
	
	
	private String religion = null;
	private int koerpergroesseString = 0;
	private String haarfarbe = null;
	private String raucher = null;
	

	// -----------------------------------------------------------------------------

	public void loadProfilEditieren(Nutzerprofil profil) {
		final Nutzerprofil nutzerprofil = profil;
		
	String test = "Die Methode wird erfolgreich aufgerufen!";
	Window.alert(test);
		


		RootPanel.get("NutzerForm").clear();
		RootPanel.get("Profil").clear();
		//RootPanel.get("ZusInf").clear();
		RootPanel.get("Steckbrief").clear();
		
		koerpergroesseIntegerBox.setValue(nutzerprofil.getKoerpergroesse());
		
		// ---------------------------------------------------------------------

		Label profilLabel = new Label("Dein Profil");
		vpPanel.add(profilLabel);
		RootPanel.get("Profil").add(vpPanel);

		// ---------------------------------------------------------------------

		nutzerAnzeigen.setText(0, 0, "Attribut");
		nutzerAnzeigen.setText(0, 1, "Inhalt");
		nutzerAnzeigen.setText(0, 2, "Neuer Inhalt");

		nutzerAnzeigen.setWidget(1, 0, vornameLabel);
		nutzerAnzeigen.setText(1, 1, nutzerprofil.getVorname());
		nutzerAnzeigen.setWidget(1, 2, vornameTextBox);

		nutzerAnzeigen.setWidget(2, 0, nachnameLabel);
		nutzerAnzeigen.setText(2, 1, nutzerprofil.getNachname());
		nutzerAnzeigen.setWidget(2, 2, nachnameTextBox);

		nutzerAnzeigen.setWidget(3, 0, geschlechtLabel);
		nutzerAnzeigen.setText(3, 1, nutzerprofil.getGeschlecht());
		nutzerAnzeigen.setWidget(3, 2, geschlechtListBox);
		geschlechtListBox.addItem("Keine Angabe");
		geschlechtListBox.addItem("männlich");
		geschlechtListBox.addItem("weiblich");
		
		nutzerAnzeigen.setWidget(4, 0, religionLabel);
		nutzerAnzeigen.setText(4, 1, nutzerprofil.getReligion());
		nutzerAnzeigen.setWidget(4, 2, religionListBox);
		religionListBox.addItem("Keine Angabe");
		religionListBox.addItem("Christentum");
		religionListBox.addItem("Islam");
		religionListBox.addItem("Judentum");
		religionListBox.addItem("Buddhismus");
		religionListBox.addItem("Hinduismus");
		religionListBox.addItem("Andere");
		
		nutzerAnzeigen.setWidget(5, 0, koerpergroesseLabel);
		Label koerpergroesseLabel = new Label();	
		koerpergroesseLabel.setText(String.valueOf(nutzerprofil.getKoerpergroesse()));
		nutzerAnzeigen.setWidget(5, 1, koerpergroesseLabel);
		
		nutzerAnzeigen.setWidget(5, 2, koerpergroesseIntegerBox);
		
		nutzerAnzeigen.setWidget(6, 0, raucherLabel);
		nutzerAnzeigen.setText(6, 1, nutzerprofil.getRaucher());
		nutzerAnzeigen.setWidget(6, 2, raucherListBox);
		raucherListBox.addItem("Keine Angabe");
		raucherListBox.addItem("Ja");
		raucherListBox.addItem("Nein");
		
		// Geburtsdatum
		geburtsdatumDateBox.setFormat(new DateBox.DefaultFormat(geburtsdatumFormat)); //Diese Zeile bestimmt das DateFormat der GeburtsdatumDateBox
		geburtsdatumDateBox.getDatePicker().setYearAndMonthDropdownVisible(true);
		geburtsdatumDateBox.getDatePicker().setVisibleYearCount(50);
		
		geburtsdatumDateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date geburtsdatum = event.getValue();
				String geburtsdatumString = DateTimeFormat.getFormat("yyyy-MM-dd").format(geburtsdatum);
				geburtsdatumInhalt.setText(geburtsdatumString);
				}
			});
		geburtsdatumDateBox.setValue(new Date()); //Hier wird der Inhalt der geburtsdatumDateBox mit dem neuen Wert beschrieben
		
		
		nutzerAnzeigen.setWidget(7, 0, geburtsdatumLabel);
		DateLabel geburtsdatumLabel = new DateLabel();
		geburtsdatumLabel.setValue(nutzerprofil.getGeburtsdatum());
		nutzerAnzeigen.setWidget(7, 1, geburtsdatumLabel);
		nutzerAnzeigen.setWidget(7, 2, geburtsdatumDateBox);
		
		nutzerAnzeigen.setWidget(8, 0, haarfarbeLabel);
		nutzerAnzeigen.setText(8, 1, nutzerprofil.getHaarfarbe());
		nutzerAnzeigen.setWidget(8, 2, haarfarbeTextBox);

		vpanel.add(nutzerAnzeigen);

		RootPanel.get("Steckbrief").add(vpanel);

		// ---------------------------------------------------------------------
		
			hPanel.add(speichernBtn);
			hPanel.add(abbrechenBtn);

		RootPanel.get("Steckbrief").add(hPanel);
	
		//---------------------------------------------------------------------
		
		speichernBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				speicherProfil(nutzerprofil);
			}
		});
		//---------------------------------------------------------------------

		abbrechenBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loadProfilBearbeiten(nutzerprofil);
			}
		});
	
		//---------------------------------------------------------------------
	}
	
	//-------------------------------------------------------------------------
	
	private void loadProfilBearbeiten(Nutzerprofil profil) {
		Nutzerprofil nutzerprofil = profil;
		ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
		profilBearbeiten.loadProfilEditieren(nutzerprofil);
		
	}
	
	 
	private void speicherProfil(Nutzerprofil profil) {
		final Nutzerprofil nutzerprofil = profil;
		
		Date neuesgeburtsdatum = null;
		int koerpergroesse = nutzerprofil.getKoerpergroesse();
		
		
		vorname = vornameTextBox.getText();
		nachname = nachnameTextBox.getText();
	    geschlecht = geschlechtListBox.getSelectedItemText();
	    neuesgeburtsdatum = getGeburtsdatum();// Diese beiden Zeilen verursachen das Problem, dass der Clickhandler nicht mehr funktioniert wenn die TextBoexen alle sind
		religion = religionListBox.getSelectedItemText();
		koerpergroesse = koerpergroesseIntegerBox.getValue(); //Diese beiden Zeilen verursachen das Problem, dass der Clickhandler nicht mehr funktioniert wenn die TextBoexen alle sind
		haarfarbe = haarfarbeTextBox.getText();
		raucher = raucherListBox.getSelectedItemText();	
	String test = "Die Methode funktioniert";
	Window.alert(test );
		if (vorname != "") {
		nutzerprofil.setVorname(vorname);
			}
		if (nachname != "") {
		nutzerprofil.setNachname(nachname);
			}
		if (neuesgeburtsdatum != null) {
		nutzerprofil.setGeburtsdatum(neuesgeburtsdatum);
			}
		if (geschlecht != "") {
		nutzerprofil.setGeschlecht(geschlecht);
			}
		if (religion != "") {
		nutzerprofil.setReligion(religion);
			}
		if (koerpergroesse != 0) {
		nutzerprofil.setKoerpergroesse(koerpergroesse);
			}
		if (haarfarbe != "") {
		nutzerprofil.setHaarfarbe(haarfarbe);
			}
		if (raucher != "") {
		nutzerprofil.setRaucher(raucher);
			}
		
		partnerboerseVerwaltung.bearbeiteNutzerprofil(nutzerprofil, new BearbeiteNutzerprofilCallback());
	}		
	
		//---------------------------------------------------------------------
	
	
	
	Date getGeburtsdatum(){
		Date geburtsdatum = geburtsdatumFormat.parse(geburtsdatumInhalt.getText());
		java.sql.Date sqlDate = new java.sql.Date(geburtsdatum.getTime());
		return sqlDate;
	}
}

//-----------------------------------------------------------------------------

//Diese Methode organisiert den asynchronen Callback und gibt uns eine
//Nachricht aus, ob dieser Callback funktioniert
class BearbeiteNutzerprofilCallback implements AsyncCallback<Nutzerprofil> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Das Bearbeiten des Nutzers ist fehlgeschlagen!");
	}

	@Override
	public void onSuccess(Nutzerprofil profil) {
		Nutzerprofil nutzerprofil = profil;
		
		if (nutzerprofil != null) {
			Startseite startseite = new Startseite();
			startseite.ladeStartseite(nutzerprofil);

			Window.alert("Das Bearbeiten des Nutzers war erfolgreich!");
		}
	} 
}

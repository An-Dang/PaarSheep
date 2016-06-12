package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.Date;
import java.util.ArrayList;
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
import de.hdm.Gruppe4.Paarsheep.shared.bo.Beschreibung;
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
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel beschreibungpanel = new VerticalPanel();
	private VerticalPanel nutzerAttributPanel = new VerticalPanel();
	private VerticalPanel nutzerAnzeigenPanel = new VerticalPanel ();
	
	private FlexTable nutzerAnzeigen = new FlexTable();
	private FlexTable beschreibungenAnzeigen = new FlexTable();

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
	
	private ArrayList<Beschreibung> arrayListBeschreibung = new ArrayList<Beschreibung>();
	

// -----------------------------------------------------------------------------

	public void loadProfilEditieren(Nutzerprofil profil) {
		final Nutzerprofil nutzerprofil = profil;
		
		RootPanel.get("NutzerForm").clear();
		RootPanel.get("Profil").clear();
		RootPanel.get("Zusinf").clear();
		RootPanel.get("Steckbrief").clear();
		
	    //---------------------------------------------------------------------

		Label profilLabel = new Label("Dein Profil");
		vpPanel.add(profilLabel);
		RootPanel.get("Profil").add(vpPanel);

		// ---------------------------------------------------------------------
		
		
		
		
		
		//---------------------------------------------------------------------

		loadProfilAnzeigen(nutzerprofil);
		loadBeschreibungenAnzeigen();
		//loadAuswahlAnzeigen();
		
		Label text = new Label();
		text.setText("Erläuterung");
		Label id = new Label();
		id.setText("ID");
		beschreibungenAnzeigen.setWidget(0, 0, id);
		beschreibungenAnzeigen.setWidget(0, 1, text);
		int index = 0;
		String erlaeuterung = null;
		String msg = "Er kommt bis zu for Schleife";
		Window.alert(msg);
		
		String test = arrayListBeschreibung.get(0).getErlaeuterung();
		Window.alert(test);
		
		String test2 = Integer.toString(arrayListBeschreibung.size());
		Window.alert(test2);
		
		while (index < arrayListBeschreibung.size()) {
		
			Label label = new Label();
			String bezeichnung = arrayListBeschreibung.get(index).getErlaeuterung(); 
			label.setText(bezeichnung);
			beschreibungenAnzeigen.setWidget(index + 1, 1, label);
			
			Label label2 = new Label();
			String beschreibungID = Integer.toString(arrayListBeschreibung.get(index).getID()); 
			label2.setText(beschreibungID);
			beschreibungenAnzeigen.setWidget(index + 1, 0, label2);
			
			
			BearbeiteBeschreibungWidget bb = new BearbeiteBeschreibungWidget(bezeichnung);
			beschreibungenAnzeigen.setWidget(index +1, 2, bb);
			
			index = index +1 ;
		}
		
		
	/*	for (index = 0; index == arrayListBeschreibung.size(); index++) {
			Beschreibung b = arrayListBeschreibung.get(index);
			String test2 = b.toString();
			Window.alert(test2);
			erlaeuterung = b.getErlaeuterung();
			text.setText(erlaeuterung);
			beschreibungenAnzeigen.setWidget(index+1, 0 , text);
			Window.alert(erlaeuterung);
		}*/
		
	
		/*for (Beschreibung b : arrayListBeschreibung) {
			
			erlaeuterung = b.getErlaeuterung();
			text.setText(erlaeuterung);
				beschreibungenAnzeigen.setWidget(0, index, text);
			index++;
		}*/
		
		
		
		
		beschreibungpanel.add(beschreibungenAnzeigen);
		
		RootPanel.get("Zusinf").add(beschreibungpanel);
		
		buttonPanel.add(speichernBtn);
		buttonPanel.add(abbrechenBtn);
		RootPanel.get("Steckbrief").add(buttonPanel);

	}
	
//-----------------------------------------------------------------------------	
	private void loadBeschreibungenAnzeigen() {
		partnerboerseVerwaltung.readBeschreibungen(new AsyncCallback<ArrayList<Beschreibung>>() {

			@Override
			public void onFailure(Throwable caught) {
				String test = "Fehler beim laden der Beschreibungen";
				Window.alert(test);
				return;
			}

			@Override
			public void onSuccess(ArrayList<Beschreibung> result) {
			arrayListBeschreibung = result;
			return;
			}
			
		});
		}
	
	
	//-----------------------------------------------------------------------------		
	
	private void loadProfilAnzeigen(Nutzerprofil profil){
		final Nutzerprofil nutzerprofil = profil;
		nutzerAnzeigen.setText(0, 0, "Attribut");
//		nutzerAnzeigen.setText(0, 1, "Inhalt");
		nutzerAnzeigen.setText(0, 1, "Neuer Inhalt");
		
		//CSS-Anbindung
		nutzerAnzeigen.setCellPadding(6);
		nutzerAnzeigen.addStyleName("flexTable");
		
		nutzerAnzeigen.setWidget(1, 0, vornameLabel);
		// in vornameTextBox wird der Inhalt geholt
		this.vornameTextBox.setText(nutzerprofil.getVorname());
		// In Widget steht jetzt ein zu bearbeitender TextBox mit dem geholtem Inhalt.
		nutzerAnzeigen.setWidget(1, 1, this.vornameTextBox);


		nutzerAnzeigen.setWidget(2, 0, nachnameLabel);
		this.nachnameTextBox.setText(nutzerprofil.getNachname());
		nutzerAnzeigen.setWidget(2, 1, this.nachnameTextBox);

		nutzerAnzeigen.setWidget(3, 0, geschlechtLabel);
		nutzerAnzeigen.setWidget(3, 1, geschlechtListBox);
		geschlechtListBox.addItem("Keine Angabe");
		geschlechtListBox.addItem("männlich");
		geschlechtListBox.addItem("weiblich");
		
		nutzerAnzeigen.setWidget(4, 0, religionLabel);
		nutzerAnzeigen.setWidget(4, 1, religionListBox);
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
		koerpergroesseIntegerBox.setValue(nutzerprofil.getKoerpergroesse());
		nutzerAnzeigen.setWidget(5, 1, koerpergroesseIntegerBox);
		
		nutzerAnzeigen.setWidget(6, 0, raucherLabel);
		nutzerAnzeigen.setWidget(6, 1, raucherListBox);
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
		nutzerAnzeigen.setWidget(7, 1, geburtsdatumDateBox);
		
		nutzerAnzeigen.setWidget(8, 0, haarfarbeLabel);
		this.haarfarbeTextBox.setText(nutzerprofil.getHaarfarbe());
		nutzerAnzeigen.setWidget(8, 1, this.haarfarbeTextBox);

		nutzerAttributPanel.add(nutzerAnzeigen);	

		// ---------------------------------------------------------------------
		
		nutzerAnzeigenPanel.add(nutzerAttributPanel);
		
		RootPanel.get("Steckbrief").add(nutzerAnzeigenPanel);
	
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
	}
	
//-----------------------------------------------------------------------------
	
	private void loadProfilBearbeiten(Nutzerprofil profil) {
		Nutzerprofil nutzerprofil = profil;
		ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
		profilBearbeiten.loadProfilEditieren(nutzerprofil);
		}
	
//-----------------------------------------------------------------------------		
	//Für den ClickHandler 'speichern'
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
		//Diese Methode führt die Speicherung der neuen Informationen aus
		partnerboerseVerwaltung.bearbeiteNutzerprofil(nutzerprofil, new BearbeiteNutzerprofilCallback());
	}		
	
		//---------------------------------------------------------------------
	
	Date getGeburtsdatum(){
		Date geburtsdatum = geburtsdatumFormat.parse(geburtsdatumInhalt.getText());
		java.sql.Date sqlDate = new java.sql.Date(geburtsdatum.getTime());
		return sqlDate;
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
}}

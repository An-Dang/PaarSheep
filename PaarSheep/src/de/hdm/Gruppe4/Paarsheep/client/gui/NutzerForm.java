package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.user.datepicker.client.DatePicker;
import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

public class NutzerForm extends VerticalPanel {
//-----------------------------------------------------------------------------
	//Hier wird die Klasse PartnerboerseAdministration/Asyn instanziiert, damit 
	//wir auf die Methode createNutzerprofil dieser Klasse zugreifen koennen. 
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = 
			ClientsideSettings.getPartnerboerseVerwaltung();
	
//-----------------------------------------------------------------------------	

	TextBox vornameTextBox = new TextBox();
	TextBox nachnameTextBox = new TextBox();
	ListBox geschlechtListBox = new ListBox();
	// DateTimeFormat format = DateTimeFormat.getFormat("mm-dd-yyyy");
	DateBox geburtsdatumDateBox = new DateBox();
	DefaultFormat format = new DateBox.DefaultFormat
			(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT));
	Label idValueLabel = new Label();
	VerticalPanel vPanel = new VerticalPanel();
	ListBox religionListBox = new ListBox();
//TODO Alle m�ssen die Spalte Koerpergroesse in ihrer Datenbank auf INT aendern
	TextBox koerpergroesseTextBox = new TextBox();
	TextBox haarfarbeTextBox = new TextBox();
	ListBox raucherListBox = new ListBox();

// -------------------------------------------------------------------------

	//Diese Methode laedt das Formular zur Erstellung eines neuen Nutzers
	public void ladeNutzerForm() {

		geburtsdatumDateBox.setFormat(format);

		//Erzeugt und strukturiert die Widgets, welche genutzt werden um 
		//einen neuen Nutzer anzulegen.
		Grid nutzerGrid = new Grid(9, 3);
		this.add(nutzerGrid);

		Label idLabel = new Label("");
		nutzerGrid.setWidget(0, 0, idLabel);
		nutzerGrid.setWidget(0, 1, idValueLabel);

		Label vornameLabel = new Label("Vorname:");
		nutzerGrid.setWidget(1, 0, vornameLabel);
		nutzerGrid.setWidget(1, 1, vornameTextBox);

		Label nachnameLabel = new Label("Nachname:");
		nutzerGrid.setWidget(2, 0, nachnameLabel);
		nutzerGrid.setWidget(2, 1, nachnameTextBox);

		Label geschlechtLabel = new Label("Geschlecht:");
		geschlechtListBox.addItem("Keine Angabe");
		geschlechtListBox.addItem("männlich");
		geschlechtListBox.addItem("weiblich");
		nutzerGrid.setWidget(3, 0, geschlechtLabel);
		nutzerGrid.setWidget(3, 1, geschlechtListBox);

		Label geburtsdatumLabel = new Label("Geburtsdatum:");
		nutzerGrid.setWidget(4, 0, geburtsdatumLabel);
		nutzerGrid.setWidget(4, 1, geburtsdatumDateBox);

		Label religionLabel = new Label("Religion");
		nutzerGrid.setWidget(5, 0, religionLabel);
		nutzerGrid.setWidget(5, 1, religionListBox);
		religionListBox.addItem("Keine Angabe");
		religionListBox.addItem("Christentum");
		religionListBox.addItem("Islam");
		religionListBox.addItem("Judentum");
		religionListBox.addItem("Buddhismus");
		religionListBox.addItem("Hinduismus");
		religionListBox.addItem("Andere");

		Label koerpergroesseLabel = new Label("Koerpergroesse");
		nutzerGrid.setWidget(6, 0, koerpergroesseLabel);
		nutzerGrid.setWidget(6, 1, koerpergroesseTextBox);

		Label haarfarbeLabel = new Label("Haarfarbe");
		nutzerGrid.setWidget(7, 0, haarfarbeLabel);
		nutzerGrid.setWidget(7, 1, haarfarbeTextBox);

		Label raucherLabel = new Label("Raucher");
		nutzerGrid.setWidget(8, 0, raucherLabel);
		nutzerGrid.setWidget(8, 1, raucherListBox);
		raucherListBox.addItem("Keine Angabe");
		raucherListBox.addItem("Ja");
		raucherListBox.addItem("Nein");



		vPanel.add(nutzerGrid);
		RootPanel.get().add(vPanel);
		HorizontalPanel nutzerButtonsPanel = new HorizontalPanel();
		this.add(nutzerButtonsPanel);

		Button anlegenButton = new Button("Anlegen");
		nutzerButtonsPanel.add(anlegenButton);
		Button abbrechenButton = new Button("Abbrechen");
		nutzerButtonsPanel.add(abbrechenButton);

		RootPanel.get().add(nutzerButtonsPanel);
		
		
//-----------------------------------------------------------------------------
		
		//Der Button, mit zugehoeriger Methode, zur Erzeugung eines neuen 
		//Nutzers
		anlegenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				String vorname = vornameTextBox.getText();
				String nachname = nachnameTextBox.getText();
				// Date geburtsdatum = geburtsdatumDateBox.getValue();
				String geschlecht = geschlechtListBox.getSelectedItemText();

				String religion = religionListBox.getSelectedItemText();
				String koerpergroesseString = koerpergroesseTextBox.getText();
				int koerpergroesse = Integer.parseInt(koerpergroesseString);
				String haarfarbe = haarfarbeTextBox.getText();
				String raucher = raucherListBox.getSelectedItemText();

				//-------------------------------------------------------------
				// Testausgabe
				String test = ("Vorname: " + vorname + " Nachname: " + nachname 
						+ " Geschlecht: " + geschlecht
						+ " Religion: " + religion + " Koerpergroesse: " 
						+ koerpergroesse + " Haarfarbe: " + haarfarbe
						+ " Raucher: " + raucher);
				Window.alert(test);
				
				//-------------------------------------------------------------

				partnerboerseVerwaltung.createNutzerprofil(vorname, nachname, 
						geschlecht, religion, koerpergroesse,
						haarfarbe, raucher, new CreateNutzerprofilCallback());
			}
		});

	}
}


//-----------------------------------------------------------------------------

//Diese Methode organisiert den asynchronen Callback und gibt uns eine 
//Nachricht aus, ob dieser Callback funktioniert
class CreateNutzerprofilCallback implements AsyncCallback<Nutzerprofil> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Das Anlegen eines neuen Kunden ist fehlgeschlagen!");
	}

	@Override
	public void onSuccess(Nutzerprofil nutzerprofil) {
		if (nutzerprofil != null) {

			Window.alert("Das Anlegen eines neuen Kunden war erfolgreich!");
		}
	}
}
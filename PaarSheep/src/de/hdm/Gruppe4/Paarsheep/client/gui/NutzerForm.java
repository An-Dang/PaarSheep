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

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	
	TextBox vornameTextBox = new TextBox();
	TextBox nachnameTextBox = new TextBox();
	ListBox geschlechtListBox = new ListBox();
	// DateTimeFormat format = DateTimeFormat.getFormat("mm-dd-yyyy");
	DateBox geburtsdatumDateBox = new DateBox();
	DefaultFormat format = new DateBox.DefaultFormat(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT));
	Label idValueLabel = new Label();
	VerticalPanel vPanel = new VerticalPanel();

	public void ladeNutzerForm() {

		geburtsdatumDateBox.setFormat(format);

		Grid nutzerGrid = new Grid(5, 3);
		this.add(nutzerGrid);

		Label idLabel = new Label("ID");
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

		vPanel.add(nutzerGrid);
		RootPanel.get().add(vPanel);
		HorizontalPanel nutzerButtonsPanel = new HorizontalPanel();
		this.add(nutzerButtonsPanel);

		Button anlegenButton = new Button("Anlegen");
		nutzerButtonsPanel.add(anlegenButton);
		Button abbrechenButton = new Button("Abbrechen");
		nutzerButtonsPanel.add(abbrechenButton);

		RootPanel.get().add(nutzerButtonsPanel);
		/*
		 * Button changeButton = new Button( "Ã„ndern");
		 * changeButton.addClickHandler(new
		 * ChangeClickHandler());nutzerButtonsPanel.add(changeButton);
		 * 
		 * Button searchButton = new
		 * Button("Suchen");nutzerButtonsPanel.add(searchButton);
		 * 
		 * 
		 * Button deleteButton = new Button(
		 * "LÃ¶schen");deleteButton.addClickHandler(new DeleteClickHandler());
		 * nutzerButtonsPanel.add(deleteButton);
		 * 
		 * Button newButton = new Button( "Neu");newButton.addClickHandler(new
		 * NewClickHandler()); nutzerButtonsPanel.add(newButton); }
		 */

		anlegenButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

                String vorname = vornameTextBox.getText();
                String nachname = nachnameTextBox.getText();
                //Date geburtsdatum = geburtsdatumDateBox.getValue();
                String geschlecht = geschlechtListBox.getSelectedItemText();

                String test = ("Vorname: " + vorname + "Nachname: " + nachname + "Geschlecht: " + geschlecht);
                Window.alert(test);
                partnerboerseVerwaltung.createNutzerprofil(vorname, nachname, geschlecht, new CreateNutzerprofilCallback());
            }
        });

    }
}

class CreateNutzerprofilCallback implements AsyncCallback<Nutzerprofil> {

    @Override
    public void onFailure(Throwable caught) {
        Window.alert("Das Anlegen eines neuen Kunden ist fehlgeschlagen!");
    }

    @Override
    public void onSuccess(Nutzerprofil nutzerprofil) {
        if (nutzerprofil != null) {
            // Das erfolgreiche Hinzufügen eines Nutzerprofils wird an den
            // Kunden- und
            // Kontenbaum propagiert.
            // catvm.addCustomer(customer);
            Window.alert("Das Anlegen eines neuen Kunden war erfolgreich!");
        }
    }
}
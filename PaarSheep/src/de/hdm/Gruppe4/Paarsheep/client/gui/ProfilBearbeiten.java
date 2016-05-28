package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

/**
 * Formular f�r die Darstellung der zu bearbeitenden Profilinformationen
 * 
 * @author Marcel Pleyer
 */

public class ProfilBearbeiten extends VerticalPanel {
	
	private TextBox vornameTextBox = new TextBox();
	private TextBox nachnameTextBox = new TextBox();
	private TextBox koerpergroesseLbL = new TextBox();
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

	private Label vorname = new Label("Vorname: ");
	private Label nachname = new Label("Nachname: ");
	private Label geschlecht = new Label("Geschlecht: ");
	private Label religion = new Label("Religion: ");
	private Label koerpergroesse = new Label("Körpergröße: ");
	private Label raucher = new Label("Raucher: ");
	private Label geburtsdatum = new Label("Geburtsdatum: ");
	private Label haarfarbe = new Label("Haarfarbe: ");

	private Button speichernBtn = new Button("speichern");
	private Button abbrechenBtn = new Button("abbrechen");

	// -----------------------------------------------------------------------------

	public void loadProfilEditieren(Nutzerprofil profil) {
		final Nutzerprofil nutzerprofil = profil;

		RootPanel.get("NutzerForm").clear();
		RootPanel.get("Profil").clear();
		//RootPanel.get("ZusInf").clear();
		RootPanel.get("Steckbrief").clear();
		// ---------------------------------------------------------------------

		Label profilLabel = new Label("Dein Profil");
		vpPanel.add(profilLabel);
		RootPanel.get("Profil").add(vpPanel);

		// ---------------------------------------------------------------------

		nutzerAnzeigen.setText(0, 0, "Attribut");
		nutzerAnzeigen.setText(0, 1, "Inhalt");
		nutzerAnzeigen.setText(0, 2, "Neuer Inhalt");

		nutzerAnzeigen.setWidget(1, 0, vorname);
		nutzerAnzeigen.setText(1, 1, nutzerprofil.getVorname());
		nutzerAnzeigen.setWidget(1, 2, vornameTextBox);

		nutzerAnzeigen.setWidget(2, 0, nachname);
		nutzerAnzeigen.setText(2, 1, nutzerprofil.getNachname());
		nutzerAnzeigen.setWidget(2, 2, nachnameTextBox);

		nutzerAnzeigen.setWidget(3, 0, geschlecht);
		nutzerAnzeigen.setText(3, 1, nutzerprofil.getGeschlecht());
		nutzerAnzeigen.setWidget(3, 2, geschlechtListBox);
		
		nutzerAnzeigen.setWidget(4, 0, religion);
		nutzerAnzeigen.setText(4, 1, nutzerprofil.getReligion());
		nutzerAnzeigen.setWidget(4, 2, religionListBox);
		
		nutzerAnzeigen.setWidget(5, 0, koerpergroesse);
		Label koerpergroesseLabel = new Label();	
		koerpergroesseLabel.setText(String.valueOf(nutzerprofil.getKoerpergroesse()));
		nutzerAnzeigen.setWidget(5, 1, koerpergroesseLabel);
		nutzerAnzeigen.setWidget(5, 2, koerpergroesseLbL);
		
		nutzerAnzeigen.setWidget(6, 0, raucher);
		nutzerAnzeigen.setText(6, 1, nutzerprofil.getRaucher());
		nutzerAnzeigen.setWidget(6, 2, raucherListBox);		
		
		geburtsdatumDateBox.setFormat(new DateBox.DefaultFormat(geburtsdatumFormat));
		geburtsdatumDateBox.getDatePicker().setYearAndMonthDropdownVisible(true);
		geburtsdatumDateBox.getDatePicker().setVisibleYearCount(50);
		geburtsdatumDateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
					
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date geburtsdatum = event.getValue();
				String geburtsdatumString = DateTimeFormat.getFormat("yyyy-MM-dd").format(geburtsdatum);
				geburtsdatumInhalt.setText(geburtsdatumString);
				}
			});
		geburtsdatumDateBox.setValue(new Date());
		nutzerAnzeigen.setWidget(7, 0, geburtsdatum);
		DateLabel geburtsdatumLabel = new DateLabel();
		geburtsdatumLabel.setValue(nutzerprofil.getGeburtsdatum());
		nutzerAnzeigen.setWidget(7, 1, geburtsdatumLabel);
		nutzerAnzeigen.setWidget(7, 2, geburtsdatumDateBox);
		
		nutzerAnzeigen.setWidget(8, 0, haarfarbe);
		nutzerAnzeigen.setText(8, 1, nutzerprofil.getHaarfarbe());
		nutzerAnzeigen.setWidget(8, 2, haarfarbeTextBox);

		vpanel.add(nutzerAnzeigen);

		RootPanel.get("Steckbrief").add(vpanel);

		// ---------------------------------------------------------------------
			hPanel.add(speichernBtn);
			hPanel.add(abbrechenBtn);
	

		RootPanel.get("Steckbrief").add(hPanel);

		abbrechenBtn.addClickHandler(new ClickHandler() {

		
			public void onClick(ClickEvent event) {
				loadProfilBearbeiten(nutzerprofil);
			}



		});

	}

	private void loadProfilBearbeiten(Nutzerprofil profil) {
		Nutzerprofil nutzerprofil = profil;
		ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
		profilBearbeiten.loadProfilEditieren(nutzerprofil);

		
	}
	
	Date getGeburtsdatum(){
		Date geburtsdatum = geburtsdatumFormat.parse(geburtsdatumInhalt.getText());
		java.sql.Date sqlDate = new java.sql.Date(geburtsdatum.getTime());
		return sqlDate;
	}
}

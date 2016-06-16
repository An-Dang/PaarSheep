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

	private Button profilBearbeitenButton = new Button("Speichern");

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

		geburtsdatumDateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date geburtsdatum = event.getValue();
				String geburtsdatumString = DateTimeFormat.getFormat("yyyy-MM-dd").format(geburtsdatum);
				geburtsdatumInhalt.setText(geburtsdatumString);
			}
		});

		geburtsdatumDateBox.setValue(new Date());
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

		partnerboerseVerwaltung.getNutzerprofilById(nutzerprofil.getProfilID(), new AsyncCallback<Nutzerprofil>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Nutzerprofil result) {
				vornameTextBox.setText(result.getVorname());

				nachnameTextBox.setText(result.getNachname());

				haarfarbeTextBox.setText(result.getHaarfarbe());

				for (int i = 0; i < geschlechtListBox.getItemCount(); i++) {
					if (result.getGeschlecht().equals(geschlechtListBox.getValue(i))) {
						geschlechtListBox.setSelectedIndex(i);
					}
				}

				geburtsdatumDateBox.setValue(result.getGeburtsdatum());

				koerpergroesseIntegerBox.setValue(result.getKoerpergroesse());

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

		profilBearbeitenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (vornameTextBox.getText().length() == 0) {
					Window.alert("Geben Sie Ihr Vor-/Nachname ein!");

				} else if (nachnameTextBox.getText().length() == 0) {
					Window.alert("Geben Sie Ihr Nachname ein!");
				} else {

					partnerboerseVerwaltung.saveNutzerprofil(nutzerprofil.getProfilID(), vornameTextBox.getText(),
							nachnameTextBox.getText(), haarfarbeTextBox.getText(),
							getGeburtsdatum(), geschlechtListBox.getSelectedItemText(),
							koerpergroesseIntegerBox.getValue(), religionListBox.getSelectedItemText(),
							raucherListBox.getSelectedItemText(), new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Es trat ein Fehler auf!");
									
								}

								@Override
								public void onSuccess(Void result) {
									
									Window.alert(Integer.toString(nutzerprofil.getProfilID()));
									Window.alert("Erfolgreich Aktualisiert!");

								}

							});
				}
			}
		});

		/**
		 * Widgets zum Panel hinzufuegen.
		 */
		vpPanel.add(profilBearbeitenFlexTable);

		RootPanel.get("NutzerForm").add(profilBearbeitenFlexTable);
		RootPanel.get("NutzerForm").add(profilBearbeitenButton);

	}

	/**
	 * Geburtsdatum
	 */
	Date getGeburtsdatum() {
		Date geburtsdatum = geburtsdatumFormat.parse(geburtsdatumInhalt.getText());
		java.sql.Date sqlDate = new java.sql.Date(geburtsdatum.getTime());
		return sqlDate;
	}

}

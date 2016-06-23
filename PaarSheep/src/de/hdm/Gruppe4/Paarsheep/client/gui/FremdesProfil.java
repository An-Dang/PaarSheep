package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.client.FremdesProfilInfo;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

public class FremdesProfil extends VerticalPanel {

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	/**
	 * Merken-Button und Sperren-Button hinzufuegen.
	 */
	private Button mButton = new Button();
	private Button sButton = new Button();

	private VerticalPanel vPanel = new VerticalPanel();


	/**
	 * Panels hinzufuegen.
	 */
	private VerticalPanel verPanel1 = new VerticalPanel();
	private VerticalPanel verPanel2 = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();
	private HorizontalPanel horPanel2 = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();

	/**
	 * Widgets hinzufuegen.
	 */
	private FlexTable showFremdprofilFlexTable = new FlexTable();
	private Label infoLabel = new Label();
	private Label sperrLabel = new Label();

	/**
	 * Konstruktor hinzuf�gen.
	 * @param fremdprofilID 
	 */
	public void loadFremdesProfil(final int fremdprofilID) {
		this.add(horPanel);

		// public void FremdesProfil() {

		// Einfügen der horizontalen Navigationsleiste
		final Navigationsleiste navigatorleiste = new Navigationsleiste();
		navigatorleiste.loadNavigator();

		horPanel.add(verPanel1);
		horPanel.add(verPanel2);

		/**
		 * Erste Spalte der Tabelle festlegen.
		 */

		showFremdprofilFlexTable.setText(0, 0, "Vorname");
		showFremdprofilFlexTable.setText(1, 0, "Nachname");
		showFremdprofilFlexTable.setText(2, 0, "Geschlecht");
		showFremdprofilFlexTable.setText(3, 0, "Geburtsdatum");
		showFremdprofilFlexTable.setText(4, 0, "Körpergröße");
		showFremdprofilFlexTable.setText(5, 0, "Haarfarbe");
		showFremdprofilFlexTable.setText(6, 0, "Raucherstatus");
		showFremdprofilFlexTable.setText(7, 0, "Religion");

		/**
		 * Fremdennutzer anhand der fremdprofilID auslesen.
		 */
		ClientsideSettings.getPartnerboerseAdministration().getFremdesProfilByID(fremdprofilID,
				new AsyncCallback<Nutzerprofil>() {

					public void onFailure(Throwable caught) {
						infoLabel.setText("Es trat ein Fehler auf.");
					}

					public void onSuccess(Nutzerprofil result) {

						// Vorname aus Datenbank aus der Datenbank holen
						// und in Tabelle eintragen
						showFremdprofilFlexTable.setText(0, 1, result.getVorname());

						// Nachname aus der Datenbank holen
						// und in Tabelle eintragen
						showFremdprofilFlexTable.setText(1, 1, result.getNachname());

						// Geschlecht aus der Datenbank holen
						// und in Tabelle eintragen
						showFremdprofilFlexTable.setText(2, 1, result.getGeschlecht());

						// Geburtsdatum aus der Datenbank holen
						// und in Tabelle eintragen
						showFremdprofilFlexTable.setText(3, 1, String.valueOf(result.getGeburtsdatum()));

						// Koerpergroesse aus der Datenbank holen
						// und in Tabelle eintragen
						showFremdprofilFlexTable.setText(4, 1, (Integer.toString(result.getKoerpergroesse())));

						// Haarfarbe aus der Datenbank holen
						// und in Tabelle eintragen
						showFremdprofilFlexTable.setText(5, 1, result.getHaarfarbe());

						// Raucher aus der Datenbank holen
						// und in Tabelle eintragen
						showFremdprofilFlexTable.setText(6, 1, result.getRaucher());

						// Religion aus der Datenbank holen
						// und in Tabelle eintragen
						showFremdprofilFlexTable.setText(7, 1, result.getReligion());

						// EMail aus der Datenbank holen
						// und in Tabelle eintragen
						showFremdprofilFlexTable.setText(8, 1, result.getEmailAddress());
					}

				});

		/**
		 * ABSCHNITT MERKLISTE UND SPERRLISTE BEGINN: Programmierung
		 * "Vermerk setzen" / "Vermerk löschen", "Sperrung setzen" /
		 * "Sperrung löschen" Button.
		 */

		// Beim Aufruf des Fremdprofils pruefen, ob dieses vom Benutzer gesperrt
		// wurde.
		ClientsideSettings.getPartnerboerseAdministration().pruefeSperrstatusFremdprofil(fremdprofilID,
				nutzerprofil.getProfilID(), new AsyncCallback<Integer>() {

					public void onFailure(Throwable caught) {
						infoLabel.setText("Es trat ein Fehler auf.");
					}

					public void onSuccess(Integer result) {
						if (result == 1) {
							// Falls eine Sperrung vorliegt, lautet die
							// Aufschrift des Sperrung-Buttons "Sperrung
							// loeschen".
							sperrLabel.setText("Nutzer Gesperrt");
							sButton.setVisible(false);
							// Falls eine Sperrung vorliegt, wird der
							// Vermerk-Button ausgeblendet.
							mButton.setVisible(false);
						} else {
							// Falls keine Sperrung vorliegt, lautet die
							// Aufschrift des Sperrung-Buttons "Sperrung
							// setzen".
							sButton.setText("Sperrung setzen");
						}
					}
				});

		// ClickHandler für den Sperrung-Button hinzufuegen.
		sButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				// Sperrstatus aendern.
				ClientsideSettings.getPartnerboerseVerwaltung().sperreNutzerprofil(nutzerprofil.getProfilID(),
						fremdprofilID, new AsyncCallback<Integer>() {

							public void onFailure(Throwable caught) {
								infoLabel.setText("Es trat ein Fehler auf.");
							}

							public void onSuccess(Integer result) {
								if (result == fremdprofilID) {
									sperrLabel.setText("Nutzer Gesperrt");
									// Falls eine Sperrung vorliegt, wird die
									// Aufschrift des Sperrung-Buttons zu
									// "Sperrung loeschen" geaendert.
									sButton.setVisible(false);
									// Falls eine Sperrung vorliegt, wird der
									// Vermerk-Button ausgeblendet.
									mButton.setVisible(false);

								} else {
									// Falls keine Sperrung vorliegt, wird die
									// Aufschrift des Sperrung-Buttons zu
									// "Sperrung setzen" geaendert.
									sButton.setText("Sperrung setzen");
									// Falls keine Sperrung vorliegt, wird die
									// Aufschrift des Vermerk-Buttons zu
									// "Sperrung setzen" geaendert.
									mButton.setText("Vermerk setzen");
									// Falls keine Sperrung vorliegt, wird der
									// Vermerk-Button eingeblendet.
									mButton.setVisible(true);
								}

							}

						});

			}

		});

		// Beim Aufruf des Fremdprofils pruefen, ob dieses vom Benutzer vermerkt
		// wurde.
		ClientsideSettings.getPartnerboerseAdministration().pruefeVermerkstatus(nutzerprofil.getProfilID(),fremdprofilID,
				new AsyncCallback<Integer>() {

					public void onFailure(Throwable caught) {
						infoLabel.setText("Es trat ein Fehler auf.");
					}

					public void onSuccess(Integer result) {
						if (result == 1) {
							// Falls ein Vermerk vorliegt, lautet die Aufschrift
							// des Vermerk-Buttons "Vermerk loeschen".
							mButton.setText("Vermerk löschen");
							// Falls kein Vermerk vorliegt, lautet die
							// Aufschrift des Vermerk-Buttons "Vermerk setzen".
						} else {
							mButton.setText("Vermerk setzen");
						}
					}
				});

		// ClickHandler fuer den Vermerk-Button hinzufuegen.
		mButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				// Vermerkstatus aendern.
				partnerboerseVerwaltung.merkeNutzerprofil(nutzerprofil.getProfilID(), fremdprofilID,
						new AsyncCallback<Integer>() {

							public void onFailure(Throwable caught) {
								infoLabel.setText("Es trat ein Fehler auf.");
							}

							public void onSuccess(Integer result) {
								if (result == 0) {
									// Falls ein Vermerk vorliegt, wird die
									// Aufschrift des Vermerk-Buttons zu
									// "Vermerk loeschen" geaendert.
									mButton.setText("Vermerk löschen");
								} else {
									// Falls kein Vermerk vorliegt, wird die
									// Aufschrift des Vermerk-Buttons zu
									// "Vermerk setzen" geaendert.
									mButton.setText("Vermerk setzen");
								}

							}

						});

			}

		});

		buttonPanel.add(mButton);
		buttonPanel.add(sButton);

		/**
		 * ABSCHNITT MERKLISTE UND SPERRLISTE ENDE: Programmierung
		 * "Vermerk setzen" / "Vermerk löschen", "Sperrung setzen" /
		 * "Sperrung löschen" Button.
		 */

		verPanel1.add(showFremdprofilFlexTable);
		verPanel1.add(sperrLabel);
		verPanel1.add(infoLabel);
		verPanel1.add(buttonPanel);
		horPanel.add(verPanel1);

		FremdesProfilInfo fremdinfo = new FremdesProfilInfo();
		verPanel2.add(fremdinfo);
		horPanel2.add(verPanel2);

		RootPanel.get("Profil").add(showFremdprofilFlexTable);
		RootPanel.get("Profil").add(buttonPanel);
		RootPanel.get("Profil").add(vPanel);
		RootPanel.get("Profil").add(horPanel);
		RootPanel.get("Profil").add(infoLabel);
		// }
	}
}
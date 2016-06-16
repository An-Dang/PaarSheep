package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public class Startseite {
	
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
	
	
	private VerticalPanel vPanel = new VerticalPanel();

	private Label ueberschriftLabel = new Label("Willkommen bei Paarsheep ");
	private VerticalPanel einfuehrungPanel = new VerticalPanel();
	
	/**
	 * Panels hinzufuegen.
	 */
	private VerticalPanel verPanel1 = new VerticalPanel();
	private VerticalPanel verPanel2 = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();

	/**
	 * Widgets hinzufuegen.
	 */
	private FlexTable showEigenesNpFlexTable = new FlexTable();
	private Label infoLabel = new Label();
	private Button bearbeitenButton = new Button("Bearbeiten");

	public void ladeStartseite() {
		
		// Einfügen der horizontalen Navigationsleiste
		final Navigationsleiste navigatorleiste = new Navigationsleiste();
		navigatorleiste.loadNavigator();
		
		
//		RootPanel.get("NutzerForm").clear();
//		RootPanel.get("Profil").clear();
//		RootPanel.get("Steckbrief").clear();
//		RootPanel.get("Zusinf").clear();
		

		horPanel.add(verPanel1);
		horPanel.add(verPanel2);

		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		showEigenesNpFlexTable.setText(0, 0, "Nutzerprofil-Id");
		showEigenesNpFlexTable.setText(1, 0, "Vorname");
		showEigenesNpFlexTable.setText(2, 0, "Nachname");
		showEigenesNpFlexTable.setText(3, 0, "Geschlecht");
		showEigenesNpFlexTable.setText(4, 0, "Geburtsdatum");
		showEigenesNpFlexTable.setText(5, 0, "Körpergröße");
		showEigenesNpFlexTable.setText(6, 0, "Haarfarbe");
		showEigenesNpFlexTable.setText(7, 0, "Raucherstatus");
		showEigenesNpFlexTable.setText(8, 0, "Religion");
		showEigenesNpFlexTable.setText(9, 0, "EMail");

		/**
		 * Nutzerprofil anhand der Profil-ID auslesen.
		 */
		ClientsideSettings.getPartnerboerseAdministration().getNutzerprofilById(nutzerprofil.getProfilID(),
				new AsyncCallback<Nutzerprofil>() {

					public void onFailure(Throwable caught) {
						infoLabel.setText("Es trat ein Fehler auf.");
					}

					public void onSuccess(Nutzerprofil result) {
						// Nutzerprofil-Id aus der Datenabank holen
						// und in Tabelle eintragen
						String nutzerprofilId = String.valueOf(result.getProfilID());
						showEigenesNpFlexTable.setText(0, 1, nutzerprofilId);

						// Vorname aus Datenbank aus der Datenbank holen
						// und in Tabelle eintragen
						showEigenesNpFlexTable.setText(1, 1, result.getVorname());

						// Nachname aus der Datenbank holen
						// und in Tabelle eintragen
						showEigenesNpFlexTable.setText(2, 1, result.getNachname());

						// Geschlecht aus der Datenbank holen
						// und in Tabelle eintragen
						showEigenesNpFlexTable.setText(3, 1, result.getGeschlecht());

						// Geburtsdatum aus der Datenbank holen
						// und in Tabelle eintragen
						showEigenesNpFlexTable.setText(4, 1, String.valueOf(result.getGeburtsdatum()));

						// Koerpergroesse aus der Datenbank holen
						// und in Tabelle eintragen
						showEigenesNpFlexTable.setText(5, 1, (Integer.toString(result.getKoerpergroesse())));

						// Haarfarbe aus der Datenbank holen
						// und in Tabelle eintragen
						showEigenesNpFlexTable.setText(6, 1, result.getHaarfarbe());

						// Raucher aus der Datenbank holen
						// und in Tabelle eintragen
						showEigenesNpFlexTable.setText(7, 1, result.getRaucher());

						// Religion aus der Datenbank holen
						// und in Tabelle eintragen
						showEigenesNpFlexTable.setText(8, 1, result.getReligion());

						// EMail aus der Datenbank holen
						// und in Tabelle eintragen
						showEigenesNpFlexTable.setText(9, 1, result.getEmailAddress());
					}

				});
		
		//bearbeiteProfil-Button
		bearbeitenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
		    	RootPanel.get("Profil").clear();
				RootPanel.get("NutzerForm").add(profilBearbeiten);
		
			}
			
		});
		

		einfuehrungPanel.add(ueberschriftLabel);
		/**
		 * Widgets den Panels hinzufuegen.
		 */
		//verPanel1.add(ueberschriftLabel);
		verPanel1.add(showEigenesNpFlexTable);
		buttonPanel.add(bearbeitenButton);
		verPanel1.add(buttonPanel);
		verPanel1.add(infoLabel);
		
		RootPanel.get("Profil").add(einfuehrungPanel);		
		RootPanel.get("Profil").add(vPanel);
		RootPanel.get("Profil").add(showEigenesNpFlexTable);
		RootPanel.get("Profil").add(bearbeitenButton);
		RootPanel.get("Profil").add(infoLabel);
		
	}

}


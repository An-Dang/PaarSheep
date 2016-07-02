package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.appengine.labs.repackaged.com.google.common.collect.Sets.SetView;
import com.google.gwt.dev.util.Empty;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * @author andang
 *
 */
public class Startseite extends HorizontalPanel {

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	private Label ueberschriftLabel = new Label("Willkommen bei Paarsheep ");

	/**
	 * Panels hinzufuegen.
	 */
	private VerticalPanel vpPanel = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();
	private VerticalPanel einfuehrungPanel = new VerticalPanel();
	private VerticalPanel eigenschaftPanel = new VerticalPanel();
	private HorizontalPanel profilPanel = new HorizontalPanel();

	/**
	 * Widgets hinzufuegen.
	 */
	private FlexTable startseiteFlexTable = new FlexTable();
	private FlexTable showEigeneEigenschaften = new FlexTable();
	private Label infoLabel = new Label();
	private Label eigenschaftsLabel = new Label();

	private int row;
	private int beschreibungInt;
	private int beschreibungsTable;
	protected int Beschreibung;

	/**
	 * 
	 */
	public void ladeStartseite() {
		this.add(horPanel);
		horPanel.add(vpPanel);

		// Einfügen der horizontalen Navigationsleiste
		final Navigationsleiste navigatorleiste = new Navigationsleiste();
		navigatorleiste.loadNavigator();

		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		startseiteFlexTable.setText(0, 0, "Vorname");
		startseiteFlexTable.setText(1, 0, "Nachname");
		startseiteFlexTable.setText(2, 0, "Geschlecht");
		startseiteFlexTable.setText(3, 0, "Geburtsdatum");
		startseiteFlexTable.setText(4, 0, "Körpergröße");
		startseiteFlexTable.setText(5, 0, "Haarfarbe");
		startseiteFlexTable.setText(6, 0, "Raucherstatus");
		startseiteFlexTable.setText(7, 0, "Religion");
		startseiteFlexTable.setText(8, 0, "EMail");

		/**
		 * CSS-Anbindung
		 */
		startseiteFlexTable.addStyleName("flexTable");
		showEigeneEigenschaften.addStyleName("flexTable");
		eigenschaftPanel.setStyleName("Abstand-Links", true);
		infoLabel.setStyleName("Label-Style");
		eigenschaftsLabel.setStyleName("Label-Style");
		
		/**
		 * Nutzerprofil anhand der Profil-ID auslesen.
		 */
		ClientsideSettings.getPartnerboerseAdministration().getNutzerprofilById(nutzerprofil.getProfilID(),
				new AsyncCallback<Nutzerprofil>() {

					public void onFailure(Throwable caught) {
						infoLabel.setText("Es trat ein Fehler auf.");
					}

					public void onSuccess(Nutzerprofil result) {

						// Vorname aus Datenbank aus der Datenbank holen
						// und in Tabelle eintragen
						startseiteFlexTable.setText(0, 1, result.getVorname());

						// Nachname aus der Datenbank holen
						// und in Tabelle eintragen
						startseiteFlexTable.setText(1, 1, result.getNachname());

						// Geschlecht aus der Datenbank holen
						// und in Tabelle eintragen
						startseiteFlexTable.setText(2, 1, result.getGeschlecht());

						// Geburtsdatum aus der Datenbank holen
						// und in Tabelle eintragen
						startseiteFlexTable.setText(3, 1, String.valueOf(result.getGeburtsdatum()));

						// Koerpergroesse aus der Datenbank holen
						// und in Tabelle eintragen
						startseiteFlexTable.setText(4, 1, (Integer.toString(result.getKoerpergroesse())));

						// Haarfarbe aus der Datenbank holen
						// und in Tabelle eintragen
						startseiteFlexTable.setText(5, 1, result.getHaarfarbe());

						// Raucher aus der Datenbank holen
						// und in Tabelle eintragen
						startseiteFlexTable.setText(6, 1, result.getRaucher());

						// Religion aus der Datenbank holen
						// und in Tabelle eintragen
						startseiteFlexTable.setText(7, 1, result.getReligion());

						// EMail aus der Datenbank holen
						// und in Tabelle eintragen
						startseiteFlexTable.setText(8, 1, result.getEmailAddress());
					}

				});

		partnerboerseVerwaltung.showProfilAllEigBeschreibung(nutzerprofil.getProfilID(),
				new AsyncCallback<Map<List<Beschreibung>, List<Information>>>() {
					public void onFailure(Throwable caught) {
						infoLabel.setText("Es trat ein Fehler auf!");
					}
					public void onSuccess(Map<List<Beschreibung>, List<Information>> result) {
						Set<List<Beschreibung>> output = result.keySet();
						for (List<Beschreibung> listEig : output) {
							row = showEigeneEigenschaften.getRowCount();
							for (Beschreibung beschreibung : listEig) {
								row++;
								String beschreibungsID = String.valueOf(beschreibung.getID());
								Label l = new Label(beschreibungsID);
								l.setVisible(false);
								showEigeneEigenschaften.setText(row, 0, beschreibung.getErlaeuterung());
								showEigeneEigenschaften.setWidget(row, 2, l);
							}
							List<Information> listInfo = new ArrayList<Information>();
							listInfo = result.get(listEig);
							row = 0;
							row = showEigeneEigenschaften.getRowCount();
							for (Information information : listInfo) {
								row++;
								beschreibungInt = 0;
								beschreibungInt = information.getID();
								for (int i = 1; i < showEigeneEigenschaften.getRowCount(); i++) {
									beschreibungsTable = 0;
									beschreibungsTable = Integer.valueOf(showEigeneEigenschaften.getText(i, 2));
									if (beschreibungInt == beschreibungsTable) {
										showEigeneEigenschaften.setText(i, 1, information.getInformation());
									}
								}
							}
						}
					}
				});
		
		/**
		 * Widgets den Panels hinzufuegen.
		 */
		vpPanel.add(startseiteFlexTable);
		vpPanel.add(infoLabel);
		horPanel.add(vpPanel);
		profilPanel.add(horPanel);
		eigenschaftPanel.add(eigenschaftsLabel);
		eigenschaftPanel.add(showEigeneEigenschaften);
		profilPanel.add(eigenschaftPanel);
		RootPanel.get("Profil").add(profilPanel);
		RootPanel.get("Container").add(ueberschriftLabel);
	}
}

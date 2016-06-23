package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

/**
 * Formular für die Darstellung der zu bearbeitenden Merkzettel
 * 
 * @author An Dang
 */

public class AlleNutzerAnzeigenTest extends VerticalPanel {
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	/**
	 * VerticalPanel hinzufügen.
	 */
	private VerticalPanel verPanel = new VerticalPanel();

	/**
	 * Konstrukto
	 */
	public AlleNutzerAnzeigenTest() {
		this.add(verPanel);

		/**
		 * Überschrift-Label hinzufügen.
		 */
		final Label AlleNutzerAnzeigen = new Label("Alle Nutzer:");

		/**
		 * Information-Label hinzufügen.
		 */
		final Label infoLabel = new Label();

		/**
		 * Tabelle zur Anzeige der gemerkten Kontakte hinzufügen.
		 */
		final FlexTable flexTable = new FlexTable();

		/**
		 * Header-Zeile der Tabelle festlegen.
		 */

		flexTable.setText(0, 0, "Vorname");
		flexTable.setText(0, 1, "Nachname");
		flexTable.setText(0, 2, "Anzeigen");

		// CSS-Anbindung
		flexTable.setCellPadding(6);
		flexTable.addStyleName("flexTable");

		partnerboerseVerwaltung.getAllNutzerprofile(nutzerprofil.getProfilID(),
				new AsyncCallback<ArrayList<Nutzerprofil>>() {

					@Override
					public void onFailure(Throwable caught) {
						infoLabel.setText("Fehler");
					}

					@Override
					public void onSuccess(ArrayList<Nutzerprofil> result) {
						int row = flexTable.getRowCount();

						for (Nutzerprofil n : result) {
							row++;

							// String test =
							// Integer.toString(nutzerprofil.getID());
							// Window.alert(test);
							final String FremdprofilID = String.valueOf(n.getID());
							flexTable.setText(row, 0, n.getVorname());
							flexTable.setText(row, 1, n.getNachname());

							// Anzeige
							final Button anzeigeButton = new Button("Anzeigen");
							flexTable.setWidget(row, 2, anzeigeButton);

							anzeigeButton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
									partnerboerseVerwaltung.besucheNutzerprofil(nutzerprofil.getProfilID(),
											Integer.valueOf(FremdprofilID), new AsyncCallback<Integer>() {

												public void onFailure(Throwable caught) {
													infoLabel.setText("Fehler!");
													
												}

												public void onSuccess(Integer result) {
													
												}

											});
									
									RootPanel.get("NutzerForm").clear();
									RootPanel.get("Profil").clear();
									RootPanel.get("Steckbrief").clear();
									RootPanel.get("Zusinf").clear();
									FremdesProfil fremdesProfil = new FremdesProfil();
									fremdesProfil.loadFremdesProfil(Integer.valueOf(FremdprofilID));
								}
							});
							partnerboerseVerwaltung.getPartnervorschlaegeNp(n, new AsyncCallback<Double>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("geht ned");
								}

								@Override
								public void onSuccess(Double result) {

								}

							});
						}

					}

				});

		// Widgets zum VerticalPanel hinzufügen.
		verPanel.add(AlleNutzerAnzeigen);
		verPanel.add(flexTable);
		verPanel.add(infoLabel);

	}

}

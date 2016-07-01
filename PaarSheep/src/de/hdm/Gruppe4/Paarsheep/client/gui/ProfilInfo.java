package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Beschreibung;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Information;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Option;

/**
 * @author andang
 *
 */
public class ProfilInfo extends VerticalPanel {

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	private VerticalPanel vpPanel = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();
	private HorizontalPanel ButtonPanel = new HorizontalPanel();

	private FlexTable eigenschaftFlexTable = new FlexTable();

	private Label infoLabel = new Label();
	int row = 0;

	private Button abbrechenButton = new Button("Abbrechen");

	/**
	 * 
	 */
	public ProfilInfo() {
		this.add(horPanel);

		horPanel.add(vpPanel);
		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		eigenschaftFlexTable.setText(0, 0, "EigenschaftsID");
		eigenschaftFlexTable.setText(0, 1, "Erläuterung");
		eigenschaftFlexTable.setText(0, 2, "Bearbeiten");
		eigenschaftFlexTable.setText(0, 3, "Speichern");
		
		/**
		 * CSS-Anbindung
		 */
		eigenschaftFlexTable.setCellPadding(6);
		eigenschaftFlexTable.addStyleName("flexTable");

		/**
		 * Eigenschaften aus der DB Auslesen
		 */
		partnerboerseVerwaltung.readEigenschaft(new AsyncCallback<ArrayList<Beschreibung>>() {

			/**
			 * @param caught
			 */
			public void onFailure(Throwable caught) {
				infoLabel.setText("Es trat ein Fehler auf.");

			}

			/**
			 * @param result
			 */
			public void onSuccess(ArrayList<Beschreibung> result) {
				// if(result == 1){
				//
				// }else{

				int row = eigenschaftFlexTable.getRowCount();

				for (Beschreibung beschreibung : result) {
					row++;

					final String eigID = String.valueOf(beschreibung.getID());
					eigenschaftFlexTable.setText(row, 0, eigID);
					eigenschaftFlexTable.setText(row, 1, beschreibung.getErlaeuterung());

					final TextBox eigenschaftsbeschreibung = new TextBox();
					eigenschaftFlexTable.setWidget(row, 2, eigenschaftsbeschreibung);

					final Button speichernButton = new Button("Speichern");
					eigenschaftFlexTable.setWidget(row, 3, speichernButton);
					speichernButton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {

							final Information information = new Information();

							if (eigenschaftsbeschreibung.getText().length() == 0) {
								Window.alert("Bitte beschreiben Sie ihre ausgewähle Eigenschaft näher");
							} else {

								for (int i = 2; i <= eigenschaftFlexTable.getRowCount(); i++) {

									String flexTable2 = eigenschaftFlexTable.getText(i, 0);

									if (Integer.valueOf(flexTable2) == Integer.valueOf(eigID)) {

										ClientsideSettings.getPartnerboerseAdministration().insertInformation(
												information, nutzerprofil.getProfilID(), Integer.valueOf(eigID),
												eigenschaftsbeschreibung.getText(), new AsyncCallback<Information>() {

													public void onFailure(Throwable caught) {
														infoLabel.setText("Es trat ein Fehler auf.");

													}

													public void onSuccess(Information result) {
														Window.alert("Deine Eigenschaft wurde hinzugefügt");

													}

												});
										eigenschaftFlexTable.removeRow(i);
										break;
									}
								}
							}

						}

					});
				}
			}
		});
		partnerboerseVerwaltung.readOption(new AsyncCallback<ArrayList<Option>>() {
			public void onFailure(Throwable caught) {
				infoLabel.setText("Es trat ein Fehler auf.");
			}

			public void onSuccess(ArrayList<Option> result) {
				row = eigenschaftFlexTable.getRowCount();
				for (Option option : result) {
					row++;
					final String eigID = String.valueOf(option.getID());
					eigenschaftFlexTable.setText(row, 0, eigID);
					eigenschaftFlexTable.setText(row, 1, option.getErlaeuterung());
					final ListBox eigenschaftsoptionen = new ListBox();
					eigenschaftFlexTable.setWidget(row, 2, eigenschaftsoptionen);
					
					partnerboerseVerwaltung.readOptionAuswahl(option.getID(),
					new GetAuswahlCallback(eigenschaftsoptionen, option.getOptionsBezeichnung()));
					
					final Button speichernButton = new Button("Speichern");
					eigenschaftFlexTable.setWidget(row, 3, speichernButton);
					speichernButton.addClickHandler(new ClickHandler() {

						public void onClick(ClickEvent event) {

							final Information information = new Information();

							if (eigenschaftsoptionen.getSelectedItemText().length() == 0) {
								Window.alert("Bitte beschreiben Sie ihre ausgewähle Eigenschaft näher");
							} else {
								ClientsideSettings.getPartnerboerseAdministration().insertInformation(information,
										nutzerprofil.getProfilID(), Integer.valueOf(eigID),
										eigenschaftsoptionen.getSelectedItemText(), new AsyncCallback<Information>() {

											public void onFailure(Throwable caught) {
												infoLabel.setText("Es trat ein Fehler auf.");
											}

											public void onSuccess(Information result) {
												Window.alert("Deine Eigenschaft wurde hinzugefügt");
											}
										});
							}
						}

					});
				}
			}
		});
		
		abbrechenButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				RootPanel.get("Profil").clear();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("EigenschaftForm").clear();
				ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
				RootPanel.get("Profil").add(profilBearbeiten);
			}
		});
		
		vpPanel.add(eigenschaftFlexTable);
		vpPanel.add(abbrechenButton);
		vpPanel.add(ButtonPanel);
	}

	private class GetAuswahlCallback implements AsyncCallback<ArrayList<Option>> {

		private ListBox eigenschaftsoptionen;
		private String option;

		public GetAuswahlCallback(ListBox eigenschaftsoptionen, String option) {
			this.eigenschaftsoptionen = eigenschaftsoptionen;
			this.option = option;
		}

		public void onFailure(Throwable caught) {
			Window.alert("GetAuswahlCallbackFailure");
		}

		public void onSuccess(ArrayList<Option> result) {
			for (Option option : result) {
				eigenschaftsoptionen.addItem(option.getOptionsBezeichnung());
			}
			if (option != null) {
				for (int i = 0; i < eigenschaftsoptionen.getItemCount(); i++) {
					if (eigenschaftsoptionen.getItemText(i).equals(option)) {
						eigenschaftsoptionen.setSelectedIndex(i);
						break;

					}

				}
			}
		}
	}
}

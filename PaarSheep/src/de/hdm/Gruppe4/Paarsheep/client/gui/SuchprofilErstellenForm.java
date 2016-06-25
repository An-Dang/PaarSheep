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
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

/**
 * @author andang
 *
 */
public class SuchprofilErstellenForm extends VerticalPanel {

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();

	private Label ueberschriftLabel = new Label("Hier kannst du dein Suchprofil erstellen ");
	private Label infoLabel = new Label();

	private FlexTable eigenschaftFlexTable = new FlexTable();

	/**
	 * Widgets hinzufuegen.
	 */
	private Button erstellenButton = new Button("neues Suchprofil erstellen");
	private Button abbrechenButton = new Button("Abbrechen");

	/**
	 * Tabelle zum Anlegen des Suchprofils erstellen.
	 */
	private FlexTable erstelleSuchprofilFlexTable = new FlexTable();

	private TextBox suchprofilNameTextBox = new TextBox();
	private ListBox religionListBox = new ListBox();
	private TextBox koerpergroesseTextBox = new TextBox();
	private ListBox haarfarbeListBox = new ListBox();
	private ListBox raucherListBox = new ListBox();
	private ListBox geschlechtListBox = new ListBox();

	int row = 0;

	/**
	 * Button zum Anlegen eines neuen Suchprofils.
	 */
	private Button erstelleSuchprofilButton = new Button("Neues Suchprofil anlegen");

	/**
	 * 
	 */
	public SuchprofilErstellenForm() {
		this.add(vPanel);
		vPanel.add(buttonPanel);

		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		erstelleSuchprofilFlexTable.setText(0, 0, "Suchprofilname");
		erstelleSuchprofilFlexTable.setText(1, 0, "Religion");
		erstelleSuchprofilFlexTable.setText(2, 0, "Körpergröße");
		erstelleSuchprofilFlexTable.setText(3, 0, "Haarfarbe");
		erstelleSuchprofilFlexTable.setText(4, 0, "Raucher");
		erstelleSuchprofilFlexTable.setText(5, 0, "Geschlecht");

		erstelleSuchprofilFlexTable.setWidget(0, 2, suchprofilNameTextBox);

		religionListBox.addItem("Keine Angabe");
		religionListBox.addItem("Christentum");
		religionListBox.addItem("Islam");
		religionListBox.addItem("Judentum");
		religionListBox.addItem("Buddhismus");
		religionListBox.addItem("Hinduismus");
		religionListBox.addItem("Andere");
		erstelleSuchprofilFlexTable.setWidget(1, 2, religionListBox);

		erstelleSuchprofilFlexTable.setWidget(2, 2, koerpergroesseTextBox);

		haarfarbeListBox.addItem("Keine Angabe");
		haarfarbeListBox.addItem("Blond");
		haarfarbeListBox.addItem("Rot");
		haarfarbeListBox.addItem("Schwarz");
		haarfarbeListBox.addItem("Braun");
		haarfarbeListBox.addItem("Andere");
		erstelleSuchprofilFlexTable.setWidget(3, 2, haarfarbeListBox);

		raucherListBox.addItem("Keine Angabe");
		raucherListBox.addItem("Nichtraucher");
		raucherListBox.addItem("Gelegenheitsraucher");
		raucherListBox.addItem("Raucher");
		erstelleSuchprofilFlexTable.setWidget(4, 2, raucherListBox);

		geschlechtListBox.addItem("Keine Angabe");
		geschlechtListBox.addItem("Weiblich");
		geschlechtListBox.addItem("Männlich");
		geschlechtListBox.addItem("Andere");
		erstelleSuchprofilFlexTable.setWidget(5, 2, geschlechtListBox);
		
		
		/**
		 * ClickHandler für den Abbrechen-Button hinzufügen.
		 */
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				RootPanel.get("Profil").clear();
				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
				RootPanel.get("Profil").add(suchprofilAnzeigen);
			}
		});

		/**
		 * ClickHandler für den Suchprofil-Anlegen-Button hinzufügen.
		 */
		erstelleSuchprofilButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				String suchprofilName = suchprofilNameTextBox.getText();
				String religion = religionListBox.getSelectedItemText();
				String koerpergroesseString = koerpergroesseTextBox.getText();
				int koerpergroesse = Integer.parseInt(koerpergroesseString);
				String haarfarbe = haarfarbeListBox.getSelectedItemText();
				String raucher = raucherListBox.getSelectedItemText();
				String geschlecht = geschlechtListBox.getSelectedItemText();

				partnerboerseVerwaltung.insertSuchprofil(nutzerprofil.getProfilID(), suchprofilName, geschlecht,
						raucher, haarfarbe, religion, koerpergroesse, new InsertSuchprofilCallback());
				int suchprofilid = 0;
				RootPanel.get("Profil").clear();

				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
				RootPanel.get("Profil").add(suchprofilAnzeigen);

			}
		});
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
					final Button speichernButton = new Button("Speichern");
					eigenschaftFlexTable.setWidget(row, 3, speichernButton);
					speichernButton.addClickHandler(new ClickHandler() {

						public void onClick(ClickEvent event) {

							final Information information = new Information();

							if (eigenschaftsoptionen.getSelectedItemText().length() == 0) {
								Window.alert("Bitte beschreiben Sie ihre ausgewähle Eigenschaft näher");
							} else {
								ClientsideSettings.getPartnerboerseAdministration().insertInformation(information,
										nutzerprofil.getID(), Integer.valueOf(eigID),
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
					partnerboerseVerwaltung.readOptionAuswahl(option.getID(),
							new GetAuswahlCallback(eigenschaftsoptionen, option.getOptionsBezeichnung()));
				}
			}
		});

		
		/**
		 * Widgets zum VerticalPanel hinzufuegen.
		 */
		vPanel.add(ueberschriftLabel);
		vPanel.add(erstelleSuchprofilFlexTable);
		vPanel.add(eigenschaftFlexTable);
		vPanel.add(buttonPanel);
		buttonPanel.add(erstellenButton);
		buttonPanel.add(abbrechenButton);
	}

	// Diese Methode organisiert den asynchronen Callback und gibt uns eine
	// Nachricht aus, ob dieser Callback funktioniert
	class InsertSuchprofilCallback implements AsyncCallback<Suchprofil> {
		public void onFailure(Throwable caught) {
			Window.alert("Das Anlegen eines neuen Suchprofils ist fehlgeschlagen!");
		}
		public void onSuccess(Suchprofil suchprofil) {
			Window.alert("Das Anlegen eines neuen Suchprofils war erfolgreich!");
		}

	};
	
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

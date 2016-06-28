package de.hdm.Gruppe4.Paarsheep.client.gui;

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
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public class SuchprofilBearbeiten extends VerticalPanel {

	/**
	 * Neues Nutzerprofil-Objekt anlegen mit Login-Infos.
	 */
	private Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	/**
	 * VerticalPanel hinzufuegen.
	 */
	private VerticalPanel verPanel = new VerticalPanel();

	private VerticalPanel suchprofilPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();

	/**
	 * Label fuer die Ueberschrift hinzufuegen.
	 */
	private Label ueberschriftLabel = new Label("Suchprofil bearbeiten:");

	/**
	 * Tabelle zur Anzeige und zur Bearbeitung des aktuellen Suchprofils
	 * hinzufuegen.
	 */
	private FlexTable suchprofilBearbeitenFlexTable = new FlexTable();

	/**
	 * TextBoxen und ListBoxen hinzufuegen.
	 */

	private TextBox suchprofilNameTextBox = new TextBox();
	private ListBox religionListBox = new ListBox();
	private TextBox koerpergroesseTextBox = new TextBox();
	private ListBox haarfarbeListBox = new ListBox();
	private ListBox raucherListBox = new ListBox();
	private ListBox geschlechtListBox = new ListBox();

	/**
	 * Button zum Speichern der Aenderungen hinzufuegen.
	 */
	private Button SuchprofilSpeichernButton = new Button("Suchprofil speichern");
	private Button abbrechenButton = new Button("Abbrechen");
	private Button profilInfoButton = new Button("Profilinfo hinzufügen");

	public SuchprofilBearbeiten(final String suchprofilName) {
		this.add(verPanel);
		verPanel.add(suchprofilPanel);
		verPanel.add(buttonPanel);

		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		suchprofilBearbeitenFlexTable.setText(0, 0, "Suchprofil-ID");
		suchprofilBearbeitenFlexTable.setText(1, 0, "Suchprofilname");
		suchprofilBearbeitenFlexTable.setText(2, 0, "Religion");
		suchprofilBearbeitenFlexTable.setText(3, 0, "Körpergröße");
		suchprofilBearbeitenFlexTable.setText(4, 0, "Haarfarbe");
		suchprofilBearbeitenFlexTable.setText(5, 0, "Raucher");
		suchprofilBearbeitenFlexTable.setText(6, 0, "Geschlecht");

		/**
		 * CSS-Anbindung
		 */
		suchprofilBearbeitenFlexTable.setCellPadding(6);
		suchprofilBearbeitenFlexTable.addStyleName("flexTable");

		suchprofilPanel.add(suchprofilBearbeitenFlexTable);

		suchprofilBearbeitenFlexTable.setWidget(1, 2, suchprofilNameTextBox);

		religionListBox.addItem("Keine Angabe");
		religionListBox.addItem("Konfessionslos");
		religionListBox.addItem("Atheistitisch");
		religionListBox.addItem("Bahaisitisch");
		religionListBox.addItem("Buddhistisch");
		religionListBox.addItem("Evangelisch");
		religionListBox.addItem("Hinduistisch");
		religionListBox.addItem("Jüdisch");
		religionListBox.addItem("Katholisch");
		religionListBox.addItem("Muslimisch");
		religionListBox.addItem("Andere");
		suchprofilBearbeitenFlexTable.setWidget(2, 2, religionListBox);

		suchprofilBearbeitenFlexTable.setWidget(3, 2, koerpergroesseTextBox);

		haarfarbeListBox.addItem("Keine Angabe");
		haarfarbeListBox.addItem("Blond");
		haarfarbeListBox.addItem("Rot");
		haarfarbeListBox.addItem("Schwarz");
		haarfarbeListBox.addItem("Braun");
		haarfarbeListBox.addItem("Andere");
		suchprofilBearbeitenFlexTable.setWidget(4, 2, haarfarbeListBox);

		raucherListBox.addItem("Keine Angabe");
		raucherListBox.addItem("Nichtraucher");
		raucherListBox.addItem("Gelegenheitsraucher");
		raucherListBox.addItem("Raucher");
		suchprofilBearbeitenFlexTable.setWidget(5, 2, raucherListBox);

		geschlechtListBox.addItem("Keine Angabe");
		geschlechtListBox.addItem("Weiblich");
		geschlechtListBox.addItem("Männlich");
		geschlechtListBox.addItem("Andere");
		suchprofilBearbeitenFlexTable.setWidget(6, 2, geschlechtListBox);

		/**
		 * Daten des Suchprofils in die Tabelle einfuegen.
		 */
		try {
			ClientsideSettings.getPartnerboerseAdministration().findSuchprofiByName(nutzerprofil.getProfilID(),
					suchprofilName, new AsyncCallback<Suchprofil>() {

						public void onFailure(Throwable caught) {
							Window.alert("Es trat ein Fehler auf");
						}

						public void onSuccess(Suchprofil result) {
							// Suchprofil-ID auslesen und einfuegen.
							final String suchprofilId = String.valueOf(result.getProfilID());
							suchprofilBearbeitenFlexTable.setText(0, 2, suchprofilId);

							// Suchprofilname auslesen und einfuegen.
							suchprofilNameTextBox.setText(result.getSuchprofilName());

							// Religion auslesen und einfügen.
							for (int i = 0; i < religionListBox.getItemCount(); i++) {
								if (result.getReligion().equals(religionListBox.getValue(i))) {
									religionListBox.setSelectedIndex(i);
								}
							}

							// Körpergröße auslesen und einfügen.
							koerpergroesseTextBox.setText(Integer.toString(result.getKoerpergroesse()));

							// Haarfarbe auslesen und einfügen.
							for (int i = 0; i < haarfarbeListBox.getItemCount(); i++) {
								if (result.getHaarfarbe().equals(haarfarbeListBox.getValue(i))) {
									haarfarbeListBox.setSelectedIndex(i);
								}
							}

							// Raucherstatus auslesen und einfügen.
							for (int i = 0; i < raucherListBox.getItemCount(); i++) {
								if (result.getRaucher().equals(raucherListBox.getValue(i))) {
									raucherListBox.setSelectedIndex(i);
								}
							}

							// Geschlecht auslesen und einfügen.
							for (int i = 0; i < geschlechtListBox.getItemCount(); i++) {
								if (result.getGeschlecht().equals(geschlechtListBox.getValue(i))) {
									geschlechtListBox.setSelectedIndex(i);
								}
							}
							profilInfoButton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
									RootPanel.get("Profil").clear();
									RootPanel.get("NutzerForm").clear();
									SuchprofilInfo suchprofilInfo = new SuchprofilInfo();
									suchprofilInfo.SuchprofilInfoHinzufügen(suchprofilId);
								}
							});
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * ClickHandler fuer den Suchprofil-Speichern-Button hinzufuegen.
		 */
		SuchprofilSpeichernButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				ClientsideSettings.getPartnerboerseAdministration().updateSuchprofil(nutzerprofil.getProfilID(),
						Integer.parseInt(suchprofilBearbeitenFlexTable.getText(0, 2)), suchprofilNameTextBox.getText(),
						religionListBox.getSelectedItemText(), Integer.parseInt(koerpergroesseTextBox.getText()),
						haarfarbeListBox.getSelectedItemText(), raucherListBox.getSelectedItemText(),
						geschlechtListBox.getSelectedItemText(), new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler auf");
							}

							public void onSuccess(Void result) {
								Window.alert("Suchprofil wurde bearbeitet");
								SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();

								RootPanel.get("NutzerForm").clear();
								RootPanel.get("Profil").clear();
								RootPanel.get("Profil").add(suchprofilAnzeigen);

							}
						});
			}
		});

		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				RootPanel.get("Profil").clear();
				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
				RootPanel.get("Profil").add(suchprofilAnzeigen);
			}
		});
	}

	{
		verPanel.add(suchprofilBearbeitenFlexTable);
		verPanel.add(SuchprofilSpeichernButton);
		verPanel.add(buttonPanel);
		buttonPanel.add(profilInfoButton);
		buttonPanel.add(SuchprofilSpeichernButton);
		buttonPanel.add(abbrechenButton);
	}
}

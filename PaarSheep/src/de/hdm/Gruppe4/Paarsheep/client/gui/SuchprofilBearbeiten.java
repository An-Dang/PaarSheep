package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
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

	/**
	 * Label fuer die Ueberschrift hinzufuegen.
	 */
	private Label ueberschriftLabel = new Label("Suchprofil bearbeiten:");

	/**
	 * Tabelle zur Anzeige und zur Bearbeitung des aktuellen Suchprofils
	 * hinzufuegen.
	 */
	private FlexTable SuchprofilBearbeitenFlexTable = new FlexTable();

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

	public SuchprofilBearbeiten(final String suchprofilName) {
		this.add(verPanel);
		verPanel.add(suchprofilPanel);
		
		
		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		SuchprofilBearbeitenFlexTable.setText(0, 0, "Suchprofil-ID");
		SuchprofilBearbeitenFlexTable.setText(1, 0, "Suchprofilname");
		SuchprofilBearbeitenFlexTable.setText(2, 0, "Religion");
		SuchprofilBearbeitenFlexTable.setText(3, 0, "Körpergröße");
		SuchprofilBearbeitenFlexTable.setText(4, 0, "Haarfarbe");
		SuchprofilBearbeitenFlexTable.setText(5, 0, "Raucher");
		SuchprofilBearbeitenFlexTable.setText(6, 0, "Geschlecht");
		
		suchprofilPanel.add(SuchprofilBearbeitenFlexTable);
		
		
		SuchprofilBearbeitenFlexTable.setWidget(1, 2, suchprofilNameTextBox);
		
		
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
		SuchprofilBearbeitenFlexTable.setWidget(2, 2, religionListBox);
		
		SuchprofilBearbeitenFlexTable.setWidget(3, 2, koerpergroesseTextBox);
		
		haarfarbeListBox.addItem("Keine Angabe");
		haarfarbeListBox.addItem("Blond");
		haarfarbeListBox.addItem("Rot");
		haarfarbeListBox.addItem("Schwarz");
		haarfarbeListBox.addItem("Braun");
		haarfarbeListBox.addItem("Andere");
		SuchprofilBearbeitenFlexTable.setWidget(4, 2, haarfarbeListBox);

		raucherListBox.addItem("Keine Angabe");
		raucherListBox.addItem("Nichtraucher");
		raucherListBox.addItem("Gelegenheitsraucher");
		raucherListBox.addItem("Raucher");
		SuchprofilBearbeitenFlexTable.setWidget(5, 2, raucherListBox);

		geschlechtListBox.addItem("Keine Angabe");
		geschlechtListBox.addItem("Weiblich");
		geschlechtListBox.addItem("Männlich");
		geschlechtListBox.addItem("Andere");
		SuchprofilBearbeitenFlexTable.setWidget(6, 2, geschlechtListBox);
		
		/**
		 * Daten des Suchprofils in die Tabelle einfuegen.
		 */
		try {
			ClientsideSettings.getPartnerboerseAdministration()
			.findSuchprofiByName(nutzerprofil.getProfilID(), suchprofilName, new AsyncCallback<Suchprofil>() {

						public void onFailure(Throwable caught) {
							Window.alert("Es trat ein Fehler auf");
						}

						public void onSuccess(Suchprofil result) {
							// Suchprofil-ID auslesen und einfuegen.
							final String suchprofilId = String.valueOf(result.getProfilID());
							SuchprofilBearbeitenFlexTable.setText(0, 2, suchprofilId);

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

						}
						
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * ClickHandler fuer den Suchprofil-Speichern-Button hinzufuegen.
		 */
		SuchprofilSpeichernButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

			ClientsideSettings.getPartnerboerseAdministration()
										.updateSuchprofil(
												nutzerprofil.getProfilID(),
												Integer.parseInt(SuchprofilBearbeitenFlexTable.getText(0, 2)),
												suchprofilNameTextBox.getText(),
												religionListBox.getSelectedItemText(),
												Integer.parseInt(koerpergroesseTextBox.getText()),
												haarfarbeListBox.getSelectedItemText(),
												raucherListBox.getSelectedItemText(),
												geschlechtListBox.getSelectedItemText(),
												new AsyncCallback<Void>() {
													@Override
													public void onFailure(Throwable caught) {
														Window.alert("Es trat ein Fehler auf");			
													}
													public void onSuccess(Void result) {
														Window.alert("Suchprofil wurde bearbeitet");
														int suchprofilid = Integer.valueOf(SuchprofilBearbeitenFlexTable.getText(0, 2));
														
														SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
														RootPanel.get("Profil").clear();
														RootPanel.get("Profil").add(suchprofilAnzeigen);
													}
												});
							}
			});
	}
	{
		verPanel.add(SuchprofilBearbeitenFlexTable);
		verPanel.add(SuchprofilSpeichernButton);
	}
};

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

public class SuchprofilBearbeiten extends VerticalPanel{
	
	/**
	 * Neues Nutzerprofil-Objekt anlegen mit Login-Infos.
	 */
	private Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	/**
	 * VerticalPanel hinzufuegen.
	 */
	private VerticalPanel verPanel = new VerticalPanel();

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
	private Button SuchprofilSpeichernButton = new Button("Suchprofil speichern", new SuchprofilSpeichernClickHandler());

	
	public SuchprofilBearbeiten() throws Exception {
		this.add(verPanel);
		
		
		
		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		SuchprofilBearbeitenFlexTable.setText(0, 0, "Suchprofilname");
		SuchprofilBearbeitenFlexTable.setText(1, 0, "Religion");
		SuchprofilBearbeitenFlexTable.setText(2, 0, "Körpergröße");
		SuchprofilBearbeitenFlexTable.setText(3, 0, "Haarfarbe");
		SuchprofilBearbeitenFlexTable.setText(4, 0, "Raucher");
		SuchprofilBearbeitenFlexTable.setText(5, 0, "Geschlecht");
		
		
		SuchprofilBearbeitenFlexTable.setWidget(0, 2, suchprofilNameTextBox);
		
		
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
		SuchprofilBearbeitenFlexTable.setWidget(1, 2, religionListBox);
		
		SuchprofilBearbeitenFlexTable.setWidget(2, 2, koerpergroesseTextBox);
		
		haarfarbeListBox.addItem("Keine Angabe");
		haarfarbeListBox.addItem("Blond");
		haarfarbeListBox.addItem("Rot");
		haarfarbeListBox.addItem("Schwarz");
		haarfarbeListBox.addItem("Braun");
		haarfarbeListBox.addItem("Andere");
		SuchprofilBearbeitenFlexTable.setWidget(3, 2, haarfarbeListBox);

		raucherListBox.addItem("Keine Angabe");
		raucherListBox.addItem("Nichtraucher");
		raucherListBox.addItem("Gelegenheitsraucher");
		raucherListBox.addItem("Raucher");
		SuchprofilBearbeitenFlexTable.setWidget(4, 2, raucherListBox);

		
		geschlechtListBox.addItem("Keine Angabe");
		geschlechtListBox.addItem("Weiblich");
		geschlechtListBox.addItem("Männlich");
		geschlechtListBox.addItem("Andere");
		SuchprofilBearbeitenFlexTable.setWidget(5, 2, geschlechtListBox);
		
		/**
		 * Daten des Suchprofils in die Tabelle einfuegen.
		 */
		ClientsideSettings.getPartnerboerseAdministration()
		.findSuchprofiByName(nutzerprofil.getProfilID(), suchprofilNameTextBox.getText(), new AsyncCallback<Suchprofil>() {

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
	}
	

//Clickhandler zum Updaten des Suchprofils
		private class SuchprofilSpeichernClickHandler implements ClickHandler{
			public void onClick(ClickEvent event) {
				try {
					ClientsideSettings.getPartnerboerseAdministration().updateSuchprofil(
							nutzerprofil.getProfilID(),
							Integer.parseInt(
							SuchprofilBearbeitenFlexTable.getText(0, 2)),
							suchprofilNameTextBox.getText(),
							religionListBox.getItemText(religionListBox.getSelectedIndex()),
							Integer.parseInt(koerpergroesseTextBox.getText()),
							haarfarbeListBox.getItemText(haarfarbeListBox.getSelectedIndex()),
							raucherListBox.getItemText(raucherListBox.getSelectedIndex()), 
							geschlechtListBox.getItemText(geschlechtListBox.getSelectedIndex()),
						
							new BearbeitenCallback());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//Callback zum Updaten des Suchprofils 
			private class BearbeitenCallback implements AsyncCallback {
				public void onFailure(Throwable caught) {				
				}
				public void onSuccess(Object result) {
					
		
					SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
					RootPanel.get("Profil")
					.add(suchprofilAnzeigen);
				}
			}
		}
}
		
//		/**
//		 * ClickHandler fuer den Suchprofil-Bearbeiten-Button hinzufuegen.
//		 */
//		SuchprofilspeichernButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//
//				// Suchprofilname ueberpruefen.
//				ClientsideSettings.getPartnerboerseAdministration().pruefeSuchprofilnameEdit(nutzerprofil.getProfilId(),
//						Integer.parseInt(editSuchprofilFlexTable.getText(0, 2)), suchprofilNameTextBox.getText(),
//						new AsyncCallback<Integer>() {
//
//							@Override
//							public void onFailure(Throwable caught) {
//								infoLabel.setText("Es trat ein Fehler auf.");
//							}
//
//							@Override
//							public void onSuccess(Integer result) {
//								// Wenn der Suchprofilname bereits
//								// existiert...
//								
//								
//								if (result == 1) {
//									warnungLabel.setText("Der Suchprofilname existiert bereits.");
//									editSuchprofilFlexTable.setWidget(1, 4, warnungLabel);
//								} else {
//									// Wenn der Suchprofilname leer
//									// ist...
//									if (result == 2) {
//										warnungLabel.setText("Der Suchprofilname darf nicht leer sein.");
//										editSuchprofilFlexTable.setWidget(1, 4, warnungLabel);
//									} else {
//										// Wenn kein AlterMin angegeben
//										// wurde...
//										if (alterMinTextBox.getText().length() == 0) {
//											warnungLabel.setText("Bitte geben Sie 'Alter von' an.");
//											editSuchprofilFlexTable.setWidget(3, 4, warnungLabel);
//										} else {
//											// Wenn kein AlterMax
//											// angegeben wurde...
//											if (alterMaxTextBox.getText().length() == 0) {
//												warnungLabel.setText("Bitte geben Sie 'Alter bis' an.");
//												editSuchprofilFlexTable.setWidget(4, 4, warnungLabel);
//											} else {
//												// Wenn Alter von
//												// groesser als Alter
//												// bis ist...
//												if (Integer.parseInt(alterMinTextBox.getText()) > Integer
//														.parseInt(alterMaxTextBox.getText())) {
//													warnungLabel
//															.setText("'Alter von' muss kleiner als 'Alter bis' sein.");
//													editSuchprofilFlexTable.setWidget(3, 4, warnungLabel);
//												} else {
//													// Wenn keine
//													// Koerpergroesse
//													// angegeben
//													// wurde...
//													if (koerpergroesseTextBox.getText().length() == 0) {
//														warnungLabel.setText("Bitte geben Sie eine Körpergröße an.");
//														editSuchprofilFlexTable.setWidget(5, 4, warnungLabel);
//													} else {
//														// Suchprofil
//														// aktualisieren.
//														ClientsideSettings.getPartnerboerseAdministration()
//																.updateSuchprofil(nutzerprofil.getProfilId(),
//																		Integer.parseInt(
//																				editSuchprofilFlexTable.getText(0, 2)),
//																		suchprofilNameTextBox.getText(),
//																		religionListBox.getSelectedItemText(),
//																		haarfarbeListBox.getSelectedItemText(),
//																		raucherListBox.getSelectedItemText(),
//																		Integer.parseInt(
//																				koerpergroesseTextBox.getText()),
//																		geschlechtListBox.getSelectedItemText(),
//																		
//																		
//																		
//																		new AsyncCallback<Void>() {
//
//																			@Override
//																			public void onFailure(Throwable caught) {
//																				infoLabel.setText(
//																						"Es trat ein Fehler auf");
//																			}
//
//																			@Override
//																			public void onSuccess(Void result) {
//																				String suchprofilName = suchprofilNameTextBox.getText();
//																				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen(suchprofilName);
//																				RootPanel.get("Profil").clear();
//																				RootPanel.get("Profil")
//																						.add(suchprofilAnzeigen);
//																			}
//
//																});
//
//											}
//										}
//									}
//								}
//
//							}
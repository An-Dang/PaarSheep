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
public class ProfilInfoBearbeiten extends VerticalPanel {

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	private Label ueberschriftLabel = new Label("Willkommen bei Paarsheep ");

	/**
	 * Panels hinzufuegen.
	 */
	private VerticalPanel vpPanel = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();
	private HorizontalPanel ButtonPanel = new HorizontalPanel();

	/**
	 * Widgets hinzufuegen.
	 */
	private FlexTable showEigenesNpFlexTable = new FlexTable();
	private FlexTable showEigeneEigenschaften = new FlexTable();
	private Label infoLabel = new Label();
	private Button alleInfoLoeschen = new Button("Alle Zusatzinformationen Löschen");

	private int row;
	private int beschreibungInt;
	private int beschreibungsTable;

	/**
	 * 
	 */
	public void ladeProfilInfoBearbeiten() {

		// Einfügen der horizontalen Navigationsleiste
		final Navigationsleiste navigatorleiste = new Navigationsleiste();
		navigatorleiste.loadNavigator();

		horPanel.add(vpPanel);

		/**
		 * Tabelle formatieren und CSS einbinden.
		 */
		showEigenesNpFlexTable.setCellPadding(6);
		showEigenesNpFlexTable.getRowFormatter().addStyleName(0, "TableHeader");
		showEigenesNpFlexTable.addStyleName("FlexTable");

		partnerboerseVerwaltung.showProfilEigBeschreibung(nutzerprofil.getProfilID(),
				new AsyncCallback<Map<List<Beschreibung>, List<Information>>>() {

					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(Map<List<Beschreibung>, List<Information>> result) {
						Set<List<Beschreibung>> output = result.keySet();

						row = showEigeneEigenschaften.getRowCount();

						for (List<Beschreibung> ListEig : output) {
							for (Beschreibung beschreibung : ListEig) {

								row++;
								final String eigID = String.valueOf(beschreibung.getID());
								showEigeneEigenschaften.setText(row, 0, eigID);
								showEigeneEigenschaften.setText(row, 1, beschreibung.getErlaeuterung());

								List<Information> listInfo = new ArrayList<Information>();
								final TextBox info = new TextBox();

								listInfo = result.get(ListEig);
								for (Information information : listInfo) {

									// Jede Auswahloption wird in die Listbox
									// hinzugfügt

									showEigeneEigenschaften.setWidget(row, 2, info);
									info.setText(information.getInformation());

									final Button speichernButton = new Button("Speichern");
									showEigeneEigenschaften.setWidget(row, 3, speichernButton);
									speichernButton.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {

											// final Information information =
											// new Information();

											if (info.getText().length() == 0) {
												Window.alert("Bitte beschreiben Sie ihre ausgewähle Eigenschaft näher");
											} else {

												ClientsideSettings.getPartnerboerseAdministration()
														.bearbeiteNutzerprofilInfo(info.getText(),
																nutzerprofil.getProfilID(), Integer.valueOf(eigID),
																new AsyncCallback<Void>() {

																	public void onFailure(Throwable caught) {
																		infoLabel.setText("Es trat ein Fehler auf.");
																	}

																	public void onSuccess(Void result) {
																		Window.alert(
																				"Deine Eigenschaft wurde hinzugefügt");
																	}
																});
											}

										}

									});

								}

							}

						}

					}

				});

		partnerboerseVerwaltung.findOptionByProfil(nutzerprofil.getProfilID(), new AsyncCallback<ArrayList<Option>>() {
			public void onFailure(Throwable caught) {
				infoLabel.setText("Es trat ein Fehler auf.");
			}

			public void onSuccess(ArrayList<Option> result) {
				row = showEigeneEigenschaften.getRowCount();
				for (Option option : result) {
					row++;
					
					final String eigID = String.valueOf(option.getID());
					showEigeneEigenschaften.setText(row, 0, eigID);
					showEigeneEigenschaften.setText(row, 1, option.getErlaeuterung());
					final ListBox eigenschaftsoptionen = new ListBox();
					showEigeneEigenschaften.setWidget(row, 2, eigenschaftsoptionen);
					final Button speichernButton = new Button("Speichern");
					showEigeneEigenschaften.setWidget(row, 3, speichernButton);
					
					speichernButton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							if (eigenschaftsoptionen.getSelectedItemText().length() == 0) {
								Window.alert("Bitte beschreiben Sie ihre ausgewähle Eigenschaft näher");
							} else {
								ClientsideSettings.getPartnerboerseAdministration()
								.bearbeiteNutzerprofilInfo(eigenschaftsoptionen.getSelectedItemText(),
										nutzerprofil.getProfilID(), Integer.valueOf(eigID),
										new AsyncCallback<Void>() {
											public void onFailure(Throwable caught) {
												infoLabel.setText("Es trat ein Fehler auf.");
											}
											public void onSuccess(Void result) {
												Window.alert(
														"Deine Eigenschaft wurde hinzugefügt");
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
		
		alleInfoLoeschen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				partnerboerseVerwaltung.deleteAllNutzerInfo(nutzerprofil.getProfilID(), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Sie haben nun keine Zusatzinformationen mehr!");
						RootPanel.get("Profil").clear();
						RootPanel.get("NutzerForm").clear();
						ProfilInfoBearbeiten profilInfoBearbeiten = new ProfilInfoBearbeiten();
						profilInfoBearbeiten.ladeProfilInfoBearbeiten();

					}

				});
			}
		});

		/**
		 * Widgets den Panels hinzufuegen.
		 */
		ButtonPanel.add(alleInfoLoeschen);
		vpPanel.add(showEigeneEigenschaften);
		vpPanel.add(infoLabel);
		vpPanel.add(ButtonPanel);

		horPanel.add(vpPanel);

		RootPanel.get("Profil").add(horPanel);

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


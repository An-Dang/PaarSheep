package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	private int row;
	

	/**
	 * 
	 */
	public void ladeStartseite() {
		
		// Einfügen der horizontalen Navigationsleiste
		final Navigationsleiste navigatorleiste = new Navigationsleiste();
		navigatorleiste.loadNavigator();
		
		horPanel.add(verPanel1);
		horPanel.add(verPanel2);

		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		showEigenesNpFlexTable.setText(0, 0, "Nutzerprofil-ID");
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
		 * Tabelle formatieren und CSS einbinden.
		 */
		showEigenesNpFlexTable.setCellPadding(6);
		showEigenesNpFlexTable.getRowFormatter().addStyleName(0, "TableHeader");
		showEigenesNpFlexTable.addStyleName("FlexTable");

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
		
		
		partnerboerseVerwaltung.findEigenschaftByProfil(nutzerprofil.getID(), new AsyncCallback<ArrayList<Beschreibung>>(){

			@Override
			public void onFailure(Throwable caught) {
				infoLabel.setText("Es trat ein Fehler auf.");
				
			}

			@Override
			public void onSuccess(ArrayList<Beschreibung> result) {
				
				int row = showEigenesNpFlexTable.getRowCount();
				
				for (Beschreibung beschreibung : result){
					row++;
					
					showEigenesNpFlexTable.setText(row, 0, beschreibung.getErlaeuterung());
					}
				
				}
			});
		
//		partnerboerseVerwaltung.findEigenschaftauswahlByProfil(nutzerprofil.getID(), new AsyncCallback<ArrayList<Beschreibung>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				infoLabel.setText("Es trat ein Fehler auf.");
//				
//			}
//
//			@Override
//			public void onSuccess(ArrayList<Beschreibung> result) {
//				
//				int row = showEigenesNpFlexTable.getRowCount();
//				
//				for (Beschreibung beschreibung : result){
//					row++;
//					
//					showEigenesNpFlexTable.setText(row, 0, beschreibung.getErlaeuterung());
//					}
//				
//				}
//			});
		
		partnerboerseVerwaltung.findInfoByProfil(nutzerprofil.getProfilID(), new AsyncCallback<ArrayList<Information>>(){

			@Override
			public void onFailure(Throwable caught) {
				infoLabel.setText("Es trat ein Fehler auf.");
				
			}

			@Override
			public void onSuccess(ArrayList<Information> result) {
				
				int row = showEigenesNpFlexTable.getRowCount();
				
				for (Information information : result){
					row++;
					
					showEigenesNpFlexTable.setText(row, 1, information.getInformation());
					
				}
			}
			
		});
		
//		partnerboerseVerwaltung.getAllProfilEig( nutzerprofil.getProfilID(), new AsyncCallback<Map<List<Beschreibung>, List<Information>>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				infoLabel.setText("Es trat ein Fehler auf.");
//				
//			}
//
//			@Override
//			public void onSuccess(Map<List<Beschreibung>, List<Information>> result) {
//				
//				Set<List<Beschreibung>> output = result.keySet();
//				
//				for (List<Beschreibung> listEig : output) {
//					
//					row = showEigenesNpFlexTable.getRowCount();
//					
//					for (Beschreibung i : listEig) {
//						
//						row++;
//						
//						showEigenesNpFlexTable.setText(row, 0, i.getErlaeuterung());
//						
//						
//		List<Information> listInfo = new ArrayList<Information>();
//						
//					
//						
//						for (Information info : listInfo){
//						row++;
//						
//						showEigenesNpFlexTable.setText( i1, 1, info.getInformation());
//						
//								
//						}
//						}
//					
//					}
//				}
//		});
		
		
		
		

		einfuehrungPanel.add(ueberschriftLabel);
		/**
		 * Widgets den Panels hinzufuegen.
		 */
		//verPanel1.add(ueberschriftLabel);
		verPanel1.add(showEigenesNpFlexTable);
		verPanel1.add(buttonPanel);
		verPanel1.add(infoLabel);
		
		RootPanel.get("Profil").add(einfuehrungPanel);		
		RootPanel.get("Profil").add(vPanel);
		RootPanel.get("Profil").add(showEigenesNpFlexTable);
		RootPanel.get("Profil").add(infoLabel);
		
	}

}


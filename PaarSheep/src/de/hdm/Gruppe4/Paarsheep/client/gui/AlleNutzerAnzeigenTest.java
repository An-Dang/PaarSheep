package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;


	
/**
* Formular für die Darstellung der zu bearbeitenden Merkzettel
* 
* @author An Dang
*/

public class AlleNutzerAnzeigenTest extends VerticalPanel{
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

		
	/**
	 * VerticalPanel hinzufügen.  
	 */
	private VerticalPanel verPanel = new VerticalPanel();
	
	//Konstruktor
	public AlleNutzerAnzeigenTest( final Nutzerprofil nutzerprofil){
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
		flexTable.setText(0, 0, "NutzerprofilID");
		flexTable.setText(0, 1, "Vorname");
		flexTable.setText(0, 2, "Nachname");
		flexTable.setText(0, 3, "merken");
		flexTable.setText(0, 4, "sperren");
		
		//CSS-Anbindung
				flexTable.setCellPadding(6);
				flexTable.addStyleName("flexTable");
		
		partnerboerseVerwaltung.getAllNutzerprofile(new AsyncCallback<ArrayList<Nutzerprofil>>(){

			@Override
			public void onFailure(Throwable caught) {
				infoLabel.setText("Fehler");
			}
				@Override
				public void onSuccess(ArrayList<Nutzerprofil> result) {
					int row = flexTable.getRowCount();
					
					for(Nutzerprofil n : result){
						row++;
						
						//final Nutzerprofil nutzerprofil = new Nutzerprofil() ;
//						String test = Integer.toString(nutzerprofil.getID());
//						Window.alert(test);
						final String FremdprofilID = String.valueOf(n.getID());
						flexTable.setText (row, 0, FremdprofilID);
						flexTable.setText(row, 1, n.getVorname());
						flexTable.setText(row, 2, n.getNachname());
						
						//merken-Button
						final Button merkenButton = new Button("merken");
						flexTable.setWidget(row, 3, merkenButton); 
						
//						//sperren-Button
						final Button sperrenButton = new Button("Nutzer Sperren");
						flexTable.setWidget(row, 4, sperrenButton); 
						
						
						//Clickhandler für Merken
						merkenButton.addClickHandler(new ClickHandler(){
							public void onClick(ClickEvent event) {
						
								for(int i=2; i<=flexTable.getRowCount(); i++ ) {
						
										String flexTable1 = flexTable.getText(i, 0);
										
										if (Integer.valueOf(flexTable1) == Integer.valueOf(FremdprofilID)) {
											
											// Inhalte aus der Datenbank entfernen. 
											partnerboerseVerwaltung. merkeNutzerprofil(nutzerprofil,  Integer.valueOf(FremdprofilID),
													new AsyncCallback<Void>()
											{
												
						
			
												@Override
												public void onFailure(Throwable caught) {
													String Ausgabe = "Nutzer konnte nciht vermerkt werden!";
													Window.alert(Ausgabe);
												}
				
												@Override
												public void onSuccess(Void result) {
													String Ausgabe = "Nutzer wurder vermerkt!";
													Window.alert(Ausgabe);
												}

												
											});
											
											break;
											
										}
									}			         
								
							}
							
						});
						
						//Clickhandler für sperren
						sperrenButton.addClickHandler(new ClickHandler(){
							public void onClick(ClickEvent event) {
						
								for(int i=2; i<=flexTable.getRowCount(); i++) {
						
										String flexTable2 = flexTable.getText(i, 0);
										
										if (Integer.valueOf(flexTable2) == Integer.valueOf(FremdprofilID)) {
											
											// Inhalte aus der Datenbank entfernen. 
											ClientsideSettings.getPartnerboerseVerwaltung().sperreNutzerprofil(nutzerprofil,  Integer.valueOf(FremdprofilID),
													new AsyncCallback<Void>()
											{
												
						
			
												@Override
												public void onFailure(Throwable caught) {
													String Ausgabe = "Nutzer konnte nicht gesperrt werden!";
													Window.alert(Ausgabe);
												}
				
												@Override
												public void onSuccess(Void result) {
													String Ausgabe = "Nutzer wurder gesperrt!";
													Window.alert(Ausgabe);
												}

												
											});
											
											
											break;
											
										}
									}			         
								
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

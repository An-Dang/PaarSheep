package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;


/**
* Formular für die Darstellung der zu bearbeitenden Merkzettel
* 
* @author An Dang
*/

public class KontaktsperreForm extends VerticalPanel{
		
	/**
	 * VerticalPanel hinzufügen.  
	 */
	private VerticalPanel verPanel = new VerticalPanel();
	
	//Konstruktor
	public KontaktsperreForm(){
		this.add(verPanel);
		

		/**
		 * Überschrift-Label hinzufügen. 
		 */
		final Label sperrliste = new Label("Kontaktsperrliste:");
		
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
		flexTable.setText(0, 3, "Löschen");
		
		ClientsideSettings.getPartnerboerseVerwaltung().
		findBySperrenderID( Benutzer.getProfilId() , new AsyncCallback<ArrayList<Nutzerprofil>>(){

			@Override
			public void onFailure(Throwable caught) {
				infoLabel.setText("Fehler");
				
			}

			@Override
			public void onSuccess(ArrayList<Nutzerprofil> result) {
				int row = flexTable.getRowCount();
				
				for(Nutzerprofil n : result){
					row++;
					
					final String nutzerprofilID = String.valueOf(n.getID());
					flexTable.setText(row, 0, nutzerprofilID);
					flexTable.setText(row, 1, n.getVorname());
					flexTable.setText(row, 2, n.getNachname());
					
					//Löschen-Button
					final Button loeschenButton = new Button("Löschen");
					flexTable.setWidget(row, 3, loeschenButton); 
					
					//Clickhandler für Löschen
					loeschenButton.addClickHandler(new ClickHandler(){
						public void onClick(ClickEvent event) {
					
							for(int i=2; i<=flexTable.getRowCount(); i++) {
					
									String fremdprofilIdFlexTable = flexTable.getText(i, 0);
									
									if (Integer.valueOf(fremdprofilIdFlexTable) == Integer.valueOf(nutzerprofilID)) {
										
										// Inhalte aus der Datenbank entfernen. 
										ClientsideSettings.getPartnerboerseVerwaltung().
										entsperreNutzerprofil(Benutzer.getProfilId(), Integer.valueOf(nutzerprofilID), new AsyncCallback<Void>(){
			
											@Override
											public void onFailure(Throwable caught) {
												infoLabel.setText(" Fehler");
											}
			
											@Override
											public void onSuccess(Void result) {
												infoLabel.setText("Sperrliste entfernt.");
											}
											
										});
										
										// Zeile in Tabelle löschen. 
										flexTable.removeRow(i);
										break;
									}
								}			         
							
						}
						
					});
				}
				
			}
			
		});
		
		// Widgets zum VerticalPanel hinzufügen. 
		verPanel.add(sperrliste); 
		verPanel.add(flexTable); 
		verPanel.add(infoLabel);
		
	}


}

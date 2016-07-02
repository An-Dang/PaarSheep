package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;


/**
* Formular für die Darstellung der zu bearbeitenden Kontaktsperrliste
* 
* @author An Dang
*/

public class KontaktsperreForm extends VerticalPanel{
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
	
	/**
	 * VerticalPanel hinzufügen.  
	 */
	private VerticalPanel verPanel = new VerticalPanel();
	
	/**
	 * Konstruktoren fuer KontaktsperreForm 
	 */
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
		
		//CSS-Anbindung
				flexTable.addStyleName("flexTable");
				sperrliste.addStyleName("Label-Style");
		
		partnerboerseVerwaltung.
		findBySperrenderID( nutzerprofil.getProfilID(), new AsyncCallback<ArrayList<Nutzerprofil>>(){

			/**
			 * um Fehler abzufangen
			 */
			@Override
			public void onFailure(Throwable caught) {
				infoLabel.setText("Fehler");
				
			}

			/**
			 * enthaelt Methoden um eine Kontaktsperre aufheben zu koennen
			 * @param row fuegt der FlexTable eine Nummerierung hinzu
			 * @param result Profil wird uebergeben
			 * @param GesperrterID ID des gesperrten Profils
			 * @param loeschenButton Button zum loeschen einer Sperre
			 */
			@Override
			public void onSuccess(ArrayList<Nutzerprofil> result) {
				int row = flexTable.getRowCount();
				
				for(Nutzerprofil n : result){
					row++;
					
					final String GesperrterID = String.valueOf(n.getID());
					flexTable.setText(row, 0, GesperrterID);
					flexTable.setText(row, 1, n.getVorname());
					flexTable.setText(row, 2, n.getNachname());
					
					//Löschen-Button
					final Button loeschenButton = new Button("Löschen");
					flexTable.setWidget(row, 3, loeschenButton); 
					
					//Clickhandler für Löschen
					loeschenButton.addClickHandler(new ClickHandler(){
						
						/**
						 * bisher gesperrtes Profil von Sperrliste loeschen und wieder freigeben
						 * @param flexTable2 Hilfsvariable um Kontakt zu sperren
						 */
						public void onClick(ClickEvent event) {
					
							for(int i=2; i<=flexTable.getRowCount(); i++) {
					
									String flexTable2 = flexTable.getText(i, 0);
									
									if (Integer.valueOf(flexTable2) == Integer.valueOf(GesperrterID)) {
										
										// Inhalte aus der Datenbank entfernen. 
										ClientsideSettings.getPartnerboerseVerwaltung().
										entsperreNutzerprofil(nutzerprofil.getProfilID(), Integer.valueOf(GesperrterID), new AsyncCallback<Void>(){
			
											/**
											 * Um Fehler abzufangen
											 */
											@Override
											public void onFailure(Throwable caught) {
												infoLabel.setText(" Fehler");
											}
			
											/**
											 * Meldung ausgeben, dass Sperrliste entfernt wurde
											 */
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

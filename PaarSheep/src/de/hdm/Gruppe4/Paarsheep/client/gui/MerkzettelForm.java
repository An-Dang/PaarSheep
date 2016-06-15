package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
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

public class MerkzettelForm extends VerticalPanel{
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
		
	/**
	 * VerticalPanel hinzufügen.  
	 */
	private VerticalPanel verPanel = new VerticalPanel();
	
	//Konstruktor
	public MerkzettelForm(){
		this.add(verPanel);
		

		/**
		 * Überschrift-Label hinzufügen. 
		 */
		final Label merkzettel1 = new Label("Merkliste:");
		
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
		flexTable.setCellPadding(6);
		flexTable.addStyleName("flexTable");
		
		partnerboerseVerwaltung.findByMerkenderID(nutzerprofil.getProfilID(), new AsyncCallback<ArrayList<Nutzerprofil>>(){

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
						final String GemerkteID = String.valueOf(n.getID());
						flexTable.setText (row, 0, GemerkteID);
						flexTable.setText(row, 1, n.getVorname());
						flexTable.setText(row, 2, n.getNachname());
						
						//Löschen-Button
						final Button loeschenButton = new Button("Löschen");
						flexTable.setWidget(row, 3, loeschenButton); 
						
						
						//Clickhandler für Löschen
						loeschenButton.addClickHandler(new ClickHandler(){
							public void onClick(ClickEvent event) {
						
								for(int i=2; i<=flexTable.getRowCount(); i++ ) {
						
										String flexTable2 = flexTable.getText(i, 0);
										
										if (Integer.valueOf(flexTable2) == Integer.valueOf(GemerkteID)) {
											
											// Inhalte aus der Datenbank entfernen. 
											ClientsideSettings.getPartnerboerseVerwaltung().deleteNutzerprofilvonMerkliste(nutzerprofil , Integer.valueOf(GemerkteID),
													new AsyncCallback<Void>()
											{
			
												@Override
												public void onFailure(Throwable caught) {
													infoLabel.setText(" Fehler");
												}
				
												@Override
												public void onSuccess(Void result) {
													infoLabel.setText("entfernt.");
//													String test = Integer.toString(nutzerprofil.getID());
//													Window.alert(test);
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
			verPanel.add(merkzettel1); 
			verPanel.add(flexTable); 
			verPanel.add(infoLabel);
			
		}


	}

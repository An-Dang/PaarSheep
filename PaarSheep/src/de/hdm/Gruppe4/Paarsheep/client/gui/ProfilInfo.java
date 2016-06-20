package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Beschreibung;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Information;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

/**
 * @author andang
 *
 */
public class ProfilInfo extends VerticalPanel{
	
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
	
	private VerticalPanel vpPanel = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();
	private HorizontalPanel ButtonPanel = new HorizontalPanel();

	private FlexTable eigenschaftFlexTable = new FlexTable();
	
	private Label infoLabel = new Label();
	
	
	private Button abbrechenButton = new Button("Abbrechen");
	
	/**
	 * 
	 */
	public ProfilInfo(){
		this.add(horPanel);
		
		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		eigenschaftFlexTable.setText(0, 0, "EigenschaftsID");
		eigenschaftFlexTable.setText(0, 1, "Erläuterung");
		eigenschaftFlexTable.setText(0, 2, "Bearbeiten");
		eigenschaftFlexTable.setText(0, 3, "Speichern");
		
		/**
		 * Eigenschaften aus der DB Auslesen
		 */
	partnerboerseVerwaltung.readEigenschaft(new AsyncCallback<ArrayList<Beschreibung>>(){

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
			int row = eigenschaftFlexTable.getRowCount();
			
			for(Beschreibung beschreibung : result){
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
							ClientsideSettings.getPartnerboerseAdministration().insertInformation(information,
									 nutzerprofil.getProfilID(), Integer.valueOf(eigID),
									eigenschaftsbeschreibung.getText(), new AsyncCallback<Information>() {

										public void onFailure(Throwable caught) {
											
											
										}

										public void onSuccess(Information result) {
											
											
										}
								
							});
								
							}

						
						}
						
					});
			}
			
		}
	});
	
	vpPanel.add(eigenschaftFlexTable);
	vpPanel.add(abbrechenButton);
	vpPanel.add(ButtonPanel);
	
	horPanel.add(vpPanel);
	
	
//	RootPanel.get("Nutzerform").add(horPanel);
	}
}

package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

public class PartnervorschleageBySuchprofilReportAnzeige extends VerticalPanel{
	
	
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
	

	/**
	 * Widgets erzeugen. 
	 */
	private VerticalPanel verPanel = new VerticalPanel();
	private HorizontalPanel auswahlPanel = new HorizontalPanel();
	private Label auswahlLabel = new Label(
			"Waehlen Sie ein Suchprofil aus, zu welchem Sie Partnervorschlaege anzeigen mÃ¶chten.");
	private Label infoLabel = new Label();
	private ListBox auswahlListBox = new ListBox();
	private Button anzeigenButton = new Button("Partnervorschlaege anzeigen");
	
	
	/**
	 * Konstruktor erstellen.
	 */
	public PartnervorschleageBySuchprofilReportAnzeige(Nutzerprofil nutzerprofil) {
		this.nutzerprofil = nutzerprofil;
		//run();

	}
}

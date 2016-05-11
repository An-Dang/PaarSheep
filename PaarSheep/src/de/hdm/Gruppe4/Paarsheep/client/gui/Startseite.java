package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

public class Startseite {

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	VerticalPanel vPanel = new VerticalPanel();
	Label startseisteLabel = new Label("Startseite");
	Label sucheProfilLabel = new Label("Geben Sie eine ID ein: ");
	TextBox sucheProfilTextBox = new TextBox();
	Button sucheProfilButton = new Button("Suche Profil");
	Label erstelleProfilLabel = new Label("Erstelle ein neues Profil: ");
	Button erstelleProfilButton = new Button("Erstelle Profil");
	Label bearbeiteProfilLabel = new Label("Bearbeite dein Profil: ");
	Button bearbeiteProfilButton = new Button("Bearbeite Profil");
	TextBox profilIDTextBox = new TextBox();
	
	Label erklaerungsLabel = new Label("Hier fuegen wir neue Funktionen ein um diese zu testen. Spaeter wird auf dieser Seite das eigene Profil geladen.");
	
	VerticalPanel einfuehrungPanel = new VerticalPanel();
	

	public void ladeStartseite() {
		
		einfuehrungPanel.add(startseisteLabel);
		einfuehrungPanel.add(erklaerungsLabel);
		
		vPanel.add(erstelleProfilLabel);
		vPanel.add(erstelleProfilButton);
		vPanel.add(bearbeiteProfilLabel);
		vPanel.add(bearbeiteProfilButton);
		vPanel.add(sucheProfilLabel);
		vPanel.add(profilIDTextBox);
		vPanel.add(sucheProfilButton);
		
		RootPanel.get("NutzerForm").add(einfuehrungPanel);		
		RootPanel.get("NutzerForm").add(vPanel);
			
		
		
		erstelleProfilButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("NutzerForm").clear();
				NutzerForm nutzerForm = new NutzerForm();
				nutzerForm.ladeNutzerForm();
				
				
			}
			
		});

		
		//---------------------------------------------------------------------
		
		sucheProfilButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {	
				RootPanel.get("NutzerForm").clear();

				int id = Integer.parseInt(profilIDTextBox.getValue());

				partnerboerseVerwaltung.getNutzerprofil(id, new GetNutzerprofilCallback());

			}

		});
		
		
		//---------------------------------------------------------------------
		bearbeiteProfilButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				RootPanel.get("NutzerForm").clear();
				ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
				profilBearbeiten.loadProfilEditieren();
				
			}
			
		});
	}
	
	

}

//-----------------------------------------------------------------------------

class GetNutzerprofilCallback implements AsyncCallback<Nutzerprofil>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Das Suchen eines Nutzers ist fehlgeschlagen. Du Spasst.");
	}

	@Override
	public void onSuccess(Nutzerprofil nutzerprofil) {
		if (nutzerprofil != null) {

		ProfilseiteForm profilseiteForm = new ProfilseiteForm();
		profilseiteForm.loadProfilInformationen(nutzerprofil);
	
		}
	}
}


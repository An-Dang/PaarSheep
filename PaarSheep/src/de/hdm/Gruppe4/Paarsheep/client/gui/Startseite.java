package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlexTable;
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
	Label erstelleSuchprofilLabel = new Label("Erstelle dein Suchprofil: ");
	Button erstelleSuchprofilButton = new Button("Erstelle Suchprofil");
	TextBox profilIDTextBox = new TextBox();
	
	Label erklaerungsLabel = new Label("Hier fuegen wir neue Funktionen ein um diese zu testen. Spaeter wird auf dieser Seite das eigene Profil geladen.");
	
	VerticalPanel einfuehrungPanel = new VerticalPanel();
	

	public void ladeStartseite(Nutzerprofil profil) {
		final Nutzerprofil nutzerprofil = profil;
		// Einf�gen der horizontalen Navigationsleiste
		
		final Navigationsleiste navigatorleiste = new Navigationsleiste();
		navigatorleiste.loadNavigator(nutzerprofil);
		
		
		RootPanel.get("NutzerForm").clear();
		RootPanel.get("Profil").clear();
		RootPanel.get("Steckbrief").clear();
		RootPanel.get("Zusinf").clear();
		
		
		//---------------------------------------------------------------------
		//Hier wird der Nutzer ausgegeben
				FlexTable nutzerAnzeigen = new FlexTable();
				VerticalPanel vpanel = new VerticalPanel();
				
				Label vorname = new Label("Vorname: ");
				Label nachname = new Label("Nachname: ");
				Label geschlecht = new Label("Geschlecht: ");
				Label religion = new Label("Religion: ");
				Label koerpergroesse = new Label("Körpergröße: ");
				Label raucher = new Label("Raucher: ");
				Label geburtsdatum = new Label("Geburtsdatum: ");
				Label haarfarbe = new Label("Haarfarbe: ");
				
				
				nutzerAnzeigen.setText(0, 0, "Attribut");
				nutzerAnzeigen.setText(0, 1, "Inhalt");

				nutzerAnzeigen.setWidget(1, 0, vorname);
				nutzerAnzeigen.setText(1, 1, nutzerprofil.getVorname());
				
				nutzerAnzeigen.setWidget(2, 0, nachname);
				nutzerAnzeigen.setText(2, 1, nutzerprofil.getNachname());
				
				nutzerAnzeigen.setWidget(3, 0, geschlecht);
				nutzerAnzeigen.setText(3, 1, nutzerprofil.getGeschlecht());
				
				nutzerAnzeigen.setWidget(4, 0, religion);
				nutzerAnzeigen.setText(4, 1, nutzerprofil.getReligion());
			
				nutzerAnzeigen.setWidget(5, 0, koerpergroesse);
				Label koerpergroesseLabel = new Label();
				koerpergroesseLabel.setText(String.valueOf(nutzerprofil.getKoerpergroesse()));
				nutzerAnzeigen.setWidget(5, 1, koerpergroesseLabel);
				
				nutzerAnzeigen.setWidget(6, 0, raucher);
				nutzerAnzeigen.setText(6, 1, nutzerprofil.getRaucher());
				
				nutzerAnzeigen.setWidget(7, 0, geburtsdatum);
				DateLabel geburtsdatumLabel = new DateLabel();
				geburtsdatumLabel.setValue(nutzerprofil.getGeburtsdatum());
				nutzerAnzeigen.setWidget(7, 1, geburtsdatumLabel);
				
				nutzerAnzeigen.setWidget(6, 0, haarfarbe);
				nutzerAnzeigen.setText(6, 1, nutzerprofil.getHaarfarbe());
				
				
				vpanel.add(nutzerAnzeigen);
				
				RootPanel.get("Steckbrief").add(vpanel);
			
		//---------------------------------------------------------------------
		
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
			
		
		
		/*erstelleProfilButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("NutzerForm").clear();
				NutzerForm nutzerForm = new NutzerForm();
				nutzerForm.ladeNutzerForm(email);
				
				
			}
			
		});
*/
		
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
		//---------------------------------------------------------------------
		
		
	}
	
	

}

//-----------------------------------------------------------------------------

class GetNutzerprofilCallback implements AsyncCallback<Nutzerprofil>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Das Suchen eines Nutzers ist fehlgeschlagen.");
	}

	@Override
	public void onSuccess(Nutzerprofil nutzerprofil) {
		if (nutzerprofil != null) {

		ProfilseiteForm profilseiteForm = new ProfilseiteForm();
		profilseiteForm.loadProfilInformationen(nutzerprofil);
	
		}
	}
}


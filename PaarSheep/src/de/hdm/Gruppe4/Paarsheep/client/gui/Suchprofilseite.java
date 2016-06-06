package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public class Suchprofilseite {
	
	
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = 
			ClientsideSettings.getPartnerboerseVerwaltung();

	
	
	private VerticalPanel vertPanel = new VerticalPanel();
	private Label suchprofilseisteLabel = new Label("Suchprofilseite");
	//private Label sucheProfilLabel = new Label("Geben Sie eine ID ein: ");
	//private TextBox sucheProfilTextBox = new TextBox();
	//private Button sucheProfilButton = new Button("Suche Profil");
	private Label erstelleSuchprofilLabel = new Label("Erstelle ein neues Suchprofil: ");
	private Button erstelleSuchprofilButton = new Button("Erstelle Suchprofil");
	private Label bearbeiteSuchprofilLabel = new Label("Bearbeite dein Suchprofil: ");
	private Button bearbeiteSuchprofilButton = new Button("Bearbeite Suchprofil");

	private TextBox suchprofilIDTextBox = new TextBox();
	
	private Label erklaerungsLabel = new Label(
			"Hier fuegen wir neue Funktionen ein um diese zu testen. "
			+ "Spaeter wird auf dieser Seite das eigene Profil geladen.");

	
	private VerticalPanel einfuehrungPanel = new VerticalPanel();
	

	public void ladeSuchprofilseite(Nutzerprofil nutzerprofil) {
		final Nutzerprofil profil = nutzerprofil;
		// Einfï¿½gen der horizontalen Navigationsleiste
		
		//final Navigationsleiste navigatorleiste = new Navigationsleiste();
		//navigatorleiste.loadNavigator(suchprofil);
		
		Suchprofil suchprofil = new Suchprofil();
		suchprofil.setSuchprofilNutzerprofilID(profil.getID());
		
		
		RootPanel.get("NutzerForm").clear();
		RootPanel.get("Profil").clear();
		RootPanel.get("Steckbrief").clear();
		RootPanel.get("Zusinf").clear();
		
		RootPanel.get("Zusinf").setVisible(false);
		
		//---------------------------------------------------------------------
		//Hier wird der Nutzer ausgegeben
				FlexTable suchprofilAnzeigen = new FlexTable();
				VerticalPanel vertpanel = new VerticalPanel();
				
				Label geschlecht = new Label("Geschlecht: ");
				Label altervon = new Label("Alter von: ");	
				Label alterbis = new Label("Alter bis: ");
				Label religion = new Label("Religion: ");
				Label haarfarbe = new Label("Haarfarbe: ");
				Label raucher = new Label("Raucher: ");
				Label koerpergroessevon = new Label("Körpergröße von: ");
				Label koerpergroessebis = new Label("Körpergröße bis: ");
				
				
				suchprofilAnzeigen.setText(0, 0, "Attribut");
				suchprofilAnzeigen.setText(0, 1, "Inhalt");

				suchprofilAnzeigen.addStyleName("SuchprofilAnzeigenFlexTable"); 
				
				
				suchprofilAnzeigen.setWidget(1, 0, geschlecht);
				suchprofilAnzeigen.setText(1, 1, suchprofil.getGeschlecht());
				
				suchprofilAnzeigen.setWidget(2, 0, altervon);
				Label altervonLabel = new Label();
				altervonLabel.setText(String.valueOf(suchprofil.getAltervon()));
				suchprofilAnzeigen.setWidget(2, 1, altervonLabel);
				
				suchprofilAnzeigen.setWidget(3, 0, alterbis);
				Label alterbisLabel = new Label();
				alterbisLabel.setText(String.valueOf(suchprofil.getAlterbis()));
				suchprofilAnzeigen.setWidget(3, 1, alterbisLabel);

				suchprofilAnzeigen.setWidget(4, 0, religion);
				suchprofilAnzeigen.setText(4, 1, suchprofil.getReligion());
			
				suchprofilAnzeigen.setWidget(5, 0, haarfarbe);
				suchprofilAnzeigen.setText(5, 1, suchprofil.getHaarfarbe());
				
				suchprofilAnzeigen.setWidget(6, 0, raucher);
				suchprofilAnzeigen.setText(6, 1, suchprofil.getRaucher());
				
				suchprofilAnzeigen.setWidget(7, 0, koerpergroessevon);
				Label koerpergroessevonLabel = new Label();
				koerpergroessevonLabel.setText(String.valueOf(suchprofil.getKoerpergroessevon()));
				suchprofilAnzeigen.setWidget(7, 1, koerpergroessevonLabel);
				
				suchprofilAnzeigen.setWidget(8, 0, koerpergroessebis);
				Label koerpergroessebisLabel = new Label();
				alterbisLabel.setText(String.valueOf(suchprofil.getKoerpergroessebis()));
				suchprofilAnzeigen.setWidget(8, 1, koerpergroessebisLabel);

				
				vertpanel.add(suchprofilAnzeigen);
				
				RootPanel.get("Steckbrief").add(vertpanel);
			
		//---------------------------------------------------------------------
		
		einfuehrungPanel.add(suchprofilseisteLabel);
		einfuehrungPanel.add(erklaerungsLabel);
		
		vertPanel.add(erstelleSuchprofilLabel);
		vertPanel.add(erstelleSuchprofilButton);
		vertPanel.add(bearbeiteSuchprofilLabel);
		vertPanel.add(bearbeiteSuchprofilButton);
		//vertPanel.add(sucheProfilLabel);
		vertPanel.add(suchprofilIDTextBox);
		//vertPanel.add(sucheProfilButton);
		
		RootPanel.get("Profil").add(einfuehrungPanel);		
		RootPanel.get("Profil").add(vertPanel);
			
		
		
		erstelleSuchprofilButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
			SuchprofilErstellenForm suchprofilErstellenForm = new SuchprofilErstellenForm();
			suchprofilErstellenForm.ladeSuchprofilErstellenForm(profil);
			
			
				//NutzerForm nutzerForm = new NutzerForm();
				//nutzerForm.ladeNutzerForm(email);
			
		
				
				
			}
			
		});

		
		//---------------------------------------------------------------------
		
/*		sucheSuchprofilButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {	
				RootPanel.get("NutzerForm").clear();

				int id = Integer.parseInt(suchprofilIDTextBox.getValue());

				partnerboerseVerwaltung.getNutzerprofil(id, new GetNutzerprofilCallback());

			}

		});
		
	*/	
		
		//---------------------------------------------------------------------
		bearbeiteSuchprofilButton.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event) {

				
				final SuchprofilBearbeitenForm suchprofilBearbeiten = new SuchprofilBearbeitenForm();
				suchprofilBearbeiten.suchprofilEditieren(suchprofil);

			}
			
		}); 
		//---------------------------------------------------------------------
	}
}
//-----------------------------------------------------------------------------

class GetSuchprofilCallback implements AsyncCallback<Suchprofil>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Das Suchen eines Nutzers ist fehlgeschlagen.");
	}

	@Override
	public void onSuccess(Suchprofil profil) {
		if (profil != null) {

		SuchprofilAnzeigenForm suchprofilAnzeigenForm = new SuchprofilAnzeigenForm();
		suchprofilAnzeigenForm.loadSuchprofilInformationen(profil);
	
		}
	} 
} 

package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;

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
import de.hdm.Gruppe4.Paarsheep.shared.bo.Beschreibung;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public class Suchprofilseite {
	
	
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = 
			ClientsideSettings.getPartnerboerseVerwaltung();

	
	private VerticalPanel vertPanel = new VerticalPanel();
	private VerticalPanel einfuehrungPanel = new VerticalPanel();
	private VerticalPanel suchprofilePanel = new VerticalPanel();
	
	private Label suchprofilseisteLabel = new Label("Suchprofilseite");
	//private Label sucheProfilLabel = new Label("Geben Sie eine ID ein: ");
	//private TextBox sucheProfilTextBox = new TextBox();
	//private Button sucheProfilButton = new Button("Suche Profil");
	private Label erstelleSuchprofilLabel = new Label("Erstelle ein neues Suchprofil: ");
	private Label bearbeiteSuchprofilLabel = new Label("Bearbeite dein Suchprofil: ");
	private Label erklaerungsLabel = new Label(
			"Hier fuegen wir neue Funktionen ein um diese zu testen. "
			+ "Spaeter wird auf dieser Seite das eigene Profil geladen.");	
	
	private Button erstelleSuchprofilButton = new Button("Erstelle Suchprofil");
	private Button bearbeiteSuchprofilButton = new Button("Bearbeite Suchprofil");

	private TextBox suchprofilIDTextBox = new TextBox();
	
	private ArrayList<Suchprofil> suchprofile = new ArrayList<Suchprofil>();
	
	
//-----------------------------------------------------------------------------
	
	public void ladeSuchprofilseite(Nutzerprofil nutzerprofil) {
		final Nutzerprofil profil = nutzerprofil;
		
		
		RootPanel.get("NutzerForm").clear();
		RootPanel.get("Profil").clear();
		RootPanel.get("Steckbrief").clear();
		RootPanel.get("Zusinf").clear();
		
		//---------------------------------------------------------------------
	
		loadSuchprofileAnzeigen(profil);
		
		
		
		
		
		
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

			}
			
		});

	}
	
	//-----------------------------------------------------------------------------	
		private void loadSuchprofileAnzeigen(Nutzerprofil nutzerprofil) {
			final Nutzerprofil profil = nutzerprofil;
			partnerboerseVerwaltung.findeSuchprofile(profil, new AsyncCallback<ArrayList<Suchprofil>>() {
				

				@Override
				public void onFailure(Throwable caught) {
					String test = "Fehler beim laden der Suchprofile";
					Window.alert(test);
					return;
				}

				@Override
				public void onSuccess(ArrayList<Suchprofil> result) {
				suchprofile = result;
				String test2 = "Der Callback zum Abrufen derSuchprofile funktioniert";
				Window.alert(test2);
				String test = Integer.toString(suchprofile.size() );
				Window.alert(test + "Das ist die Anzahl der in der ArrayList vorhandenen Suchprofile");
				
				int index = 0;
				
				while (index < suchprofile.size()) {
					
					
					SuchprofilAnzeigenWidget suchprofilAnzeigenWidget = new SuchprofilAnzeigenWidget(suchprofile.get(index));
					suchprofilePanel.add(suchprofilAnzeigenWidget);
					index = index + 1;
				}
				
				RootPanel.get("Steckbrief").add(suchprofilePanel);
				
				return;
				}
				
			});
			}
		
		
		//-----------------------------------------------------------------------------	
	
}
		//---------------------------------------------------------------------
		/*
		sucheSuchprofilButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {	
				RootPanel.get("NutzerForm").clear();

				int id = Integer.parseInt(suchprofilIDTextBox.getValue());

				partnerboerseVerwaltung.getNutzerprofil(id, new GetNutzerprofilCallback());

			}

		});
		
		
		
		//---------------------------------------------------------------------
		bearbeiteSuchprofilButton.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event) {

				
				final SuchprofilBearbeitenForm suchprofilBearbeiten = new SuchprofilBearbeitenForm();
				suchprofilBearbeiten.suchprofilEditieren(suchprofil1);

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
} */

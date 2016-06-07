package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Merkzettel;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public class Navigationsleiste {
	
	
	//-------------------------------------------------------------------------
	private HorizontalPanel navigatorpanel = new HorizontalPanel();
	private HorizontalPanel leftpanel = new HorizontalPanel();
	private HorizontalPanel rightpanel = new HorizontalPanel();

	private Label paarsheeplabel = new Label("PaarSheep");

	private Button logout = new Button("Logout");


	private Button suchprofilBtn = new Button("Suchprofil");


	private Button kontaktsperrliste = new Button("Kontaktsperrliste");
	private Button merkzettel = new Button("Merkzettel");
	private Button startseite = new Button("Startseite");
	private Button AlleNutzerAnzeigen = new Button("AlleNutzerAnzeigen");


	    //-------------------------------------------------------------------------

		public void loadNavigator(Nutzerprofil nutzerprofil) {
			final Nutzerprofil profil = nutzerprofil;
			
		RootPanel.get("navigator").clear();
			
		leftpanel.add(paarsheeplabel);
		
		rightpanel.add(startseite);
		rightpanel.add(merkzettel);
		rightpanel.add(kontaktsperrliste);
		rightpanel.add(AlleNutzerAnzeigen);

		rightpanel.add(suchprofilBtn);
		rightpanel.add(logout);
		
		leftpanel.add(logout);

		
		navigatorpanel.add(leftpanel);
		navigatorpanel.add(rightpanel);
	
		RootPanel.get("navigator").add(navigatorpanel);
		
		//-------------------------------------------------------------------------
		//Button zeigt das Eigene Profil an.
		startseite.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  
			    	RootPanel.get("NutzerForm").clear();
			    	
			    	RootPanel.get("Profil").clear();
					RootPanel.get("Steckbrief").clear();
					RootPanel.get("Zusinf").clear();
			    	
			        Startseite startseite = new Startseite();
			        startseite.ladeStartseite(profil);
		      }
		    });
		
		logout.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        loadLogout(profil);
		      }
		    });

		
		//Suchprofil-Button
		suchprofilBtn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Hier entsteht das Suchrprofil");
				Suchprofilseite suchprofilseite = new Suchprofilseite();
				suchprofilseite.ladeSuchprofilseite(profil);;			
				
			}
			
		});
		
		//Kontaktsperre-Button
		kontaktsperrliste.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				KontaktsperreForm kontaktsperreform = new KontaktsperreForm(profil);
		    	RootPanel.get("NutzerForm").clear();
		    	RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				RootPanel.get("Profil").add(kontaktsperreform);
			}
		});
		
		//Merkzettel-Button
		merkzettel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				MerkzettelForm merkzettelForm = new MerkzettelForm(profil);
		    	RootPanel.get("NutzerForm").clear();
		    	RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				RootPanel.get("Profil").add(merkzettelForm);
			}
		});
		
		
		//AlleNutzerAnzeigen-Button
		AlleNutzerAnzeigen.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						AlleNutzerAnzeigenTest alleNutzerAnzeigen = new AlleNutzerAnzeigenTest(profil);
				    	RootPanel.get("NutzerForm").clear();
				    	RootPanel.get("Profil").clear();
						RootPanel.get("Steckbrief").clear();
						RootPanel.get("Zusinf").clear();
						RootPanel.get("Profil").add(alleNutzerAnzeigen);
					}
				});
		
		}
		//-------------------------------------------------------------------------
		
		public void loadStartseite(){
			
		}
		
	    public void loadLogout(Nutzerprofil profil){
	    	final String logoutURL = profil.getLogoutUrl();
	    	Window.Location.assign(logoutURL);
	    	
		}
	  //-------------------------------------------------------------------------
		public void loadSuchprofilseite(){
			
		}
	}
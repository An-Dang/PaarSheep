package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Navigationsleiste {
	
	
	//-------------------------------------------------------------------------
	private HorizontalPanel navigatorpanel = new HorizontalPanel();
	private HorizontalPanel leftpanel = new HorizontalPanel();
	private HorizontalPanel rightpanel = new HorizontalPanel();

	private Label paarsheeplabel = new Label("PaarSheep");

	private Button logout = new Button("Logout");
	private Button kontaktsperrliste = new Button("Kontaktsperrliste");
	private Button merkzettel = new Button("Merkzettel");
	private Button startseite = new Button("Startseite");


	    //-------------------------------------------------------------------------

		public void loadNavigator() {
			
		leftpanel.add(paarsheeplabel);
		
		rightpanel.add(startseite);
		rightpanel.add(merkzettel);
		rightpanel.add(kontaktsperrliste);
		rightpanel.add(logout);
		
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
			        startseite.ladeStartseite();
		      }
		    });
		
		logout.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        loadLogout();
		      }
		    });
		
		kontaktsperrliste.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        loadKontaktsperrliste();
		      }
		    });
		
		merkzettel.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        loadMerkzettel();
		      }
		    });
		
		}
		//-------------------------------------------------------------------------
		
		public void loadStartseite(){
			
		}
		
	    public void loadLogout(){
			
		}

	    public void loadKontaktsperrliste(){
		
	    }

	    public void loadMerkzettel(){
		
	    }
	    
	  //-------------------------------------------------------------------------
	    
	}
package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
	
	}
	
	
	//-------------------------------------------------------------------------
	
}

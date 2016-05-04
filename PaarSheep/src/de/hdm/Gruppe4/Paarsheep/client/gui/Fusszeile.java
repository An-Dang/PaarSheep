package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Fusszeile {
	
	private HorizontalPanel fusspanel = new HorizontalPanel();
	private Label fusslabel = new Label("Dies ist ein Projekt von An "
			+ "Dang, Dominik Sasse, Florian Maurer, Manuel Weiler, Marcel "
			+ "Pleyer und Tino Hauler");


public void loadFusszeile() {
	fusspanel.add(fusslabel);
	RootPanel.get("trailer").add(fusspanel);
}
}
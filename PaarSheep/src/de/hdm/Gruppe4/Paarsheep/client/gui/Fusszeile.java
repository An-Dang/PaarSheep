package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Diese Klasse enthaelt Methoden zum anzeigen einer Fusszeile.
 */
public class Fusszeile {
	
	private HorizontalPanel fusspanel = new HorizontalPanel();
	private Label fusslabel = new Label("Dies ist ein Projekt von An "
			+ "Dang und Tino Hauler");

/**
 * Fusszeile wird als panel hinzugefuegt und ausgegeben.
 */
public void loadFusszeile() {
	fusspanel.add(fusslabel);
	RootPanel.get("trailer").add(fusspanel);
}
}
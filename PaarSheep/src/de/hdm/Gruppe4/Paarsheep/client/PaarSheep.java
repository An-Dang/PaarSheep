package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PaarSheep implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		VerticalPanel topPanel = new VerticalPanel();
		RootPanel.get("TopPAnel").add(topPanel);
		
		VerticalPanel botPanel = new VerticalPanel();
		RootPanel.get("BotPanel").add(botPanel);
	
	}
}

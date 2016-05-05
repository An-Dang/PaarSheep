package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.gui.Fusszeile;
import de.hdm.Gruppe4.Paarsheep.client.gui.Navigationsleiste;
import de.hdm.Gruppe4.Paarsheep.client.gui.ProfilseiteForm;

public class PaarSheep implements EntryPoint {

	//-------------------------------------------------------------------------

	public void onModuleLoad() {
		
	//Einfügen der horizontalen Navigationsleiste
			final Navigationsleiste navigatorleiste = new Navigationsleiste();
			navigatorleiste.loadNavigator();
			
	//Einfügen der horizontalen Navigationszeile		
			final Fusszeile fusszeile = new Fusszeile();
			fusszeile.loadFusszeile();
			
	//-------------------------------------------------------------------------
				
				
			final ProfilseiteForm profilseiteForm = new ProfilseiteForm();				
			profilseiteForm.loadProfilInformationen();


	}
}

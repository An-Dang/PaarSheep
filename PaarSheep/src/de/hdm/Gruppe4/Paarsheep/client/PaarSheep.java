package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.EntryPoint;


import de.hdm.Gruppe4.Paarsheep.client.gui.ProfilseiteForm;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PaarSheep implements EntryPoint {
	/**
	 * This is the entry point method.
	 */

		@Override
		public void onModuleLoad() {
		final ProfilseiteForm profilseiteForm = new ProfilseiteForm();
		
		profilseiteForm.loadProfilInformationen();


	}
}

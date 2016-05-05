package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.EntryPoint;

import de.hdm.Gruppe4.Paarsheep.client.gui.NutzerForm;
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
		/*
		 * Dieser Methodenaufruf lädt die Profilansicht des Nutzers.
		 */
	//	final ProfilseiteForm profilseiteForm = new ProfilseiteForm();
	//	profilseiteForm.loadProfilInformationen();
		/*
		 * Dieser Methodenaufruf lädt das Formular, das dazu dient einen neuen
		 * Nutzer anzulegen.
		 */
		final NutzerForm nutzerForm = new NutzerForm();
		nutzerForm.ladeNutzerForm();

	}
}

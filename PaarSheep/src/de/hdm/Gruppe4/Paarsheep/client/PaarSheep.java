package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.gui.NutzerForm;

import de.hdm.Gruppe4.Paarsheep.client.gui.Fusszeile;
import de.hdm.Gruppe4.Paarsheep.client.gui.Navigationsleiste;

import de.hdm.Gruppe4.Paarsheep.client.gui.Startseite;
import de.hdm.Gruppe4.Paarsheep.shared.LoginService;
import de.hdm.Gruppe4.Paarsheep.shared.LoginServiceAsync;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

public class PaarSheep implements EntryPoint {

	/*
	 * Instanz von PartnerboerseAdministrationAsync, welche es uns erlaubt, die
	 * Methoden zu verwenden.
	 */
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	// -----------------------------------------------------------------------------
	/*
	 * Diese Dinge werden für den Login gebraucht
	 */
	private Nutzerprofil loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access the PaarSheep application.");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
	// -----------------------------------------------------------------------------

	public void onModuleLoad() { // Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<Nutzerprofil>() {
			public void onFailure(Throwable error) {
			}

			public void onSuccess(Nutzerprofil result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {

					partnerboerseVerwaltung.checkStatus(loginInfo, new CheckStatusNutzerprofilCallback());

				} else {
					loadLogin();
				}
			}
		});
	}

	// -----------------------------------------------------------------------------

	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("NutzerForm").add(loginPanel);
		
		
		//Diese Divs werden nicht auf der Loginseite angezeigt.
		RootPanel.get("Profil").setVisible(false);
		RootPanel.get("Steckbrief").setVisible(false);
	}

	// -----------------------------------------------------------------------------

	private void loadPaarsheep() {
		
		
		signOutLink.setHref(loginInfo.getLogoutUrl());
		RootPanel.get("Profil").add(signOutLink);

		/*
		 * Einfügen der horizontalen Navigationsleiste
		 */
		final Navigationsleiste navigatorleiste = new Navigationsleiste();
		navigatorleiste.loadNavigator();

		// Einf�gen der horizontalen Navigationszeile
		final Fusszeile fusszeile = new Fusszeile();
		fusszeile.loadFusszeile();

		/*
		 * Dieser Methodenaufruf l�dt die Profilansicht des Nutzers.
		 */

		// final ProfilseiteForm profilseiteForm = new ProfilseiteForm();
		// profilseiteForm.loadProfilInformationen();

		final Startseite startseite = new Startseite();
		startseite.ladeStartseite();
	}

}
// -----------------------------------------------------------------------------

// Diese Methode organisiert den asynchronen Callback und gibt uns eine
// Nachricht aus, ob dieser Callback funktioniert
class CheckStatusNutzerprofilCallback implements AsyncCallback<Nutzerprofil> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Die Datenbank konnte nicht abgefragt werden!");
	}

	@Override
	public void onSuccess(Nutzerprofil profil) {
		
		ClientsideSettings.setAktuellerUser(profil);
		
		Window.alert(profil.toString());
		final boolean status = profil.getStatus();

		if (status == true) {
			Startseite startseite = new Startseite();
			startseite.ladeStartseite();

		} else {
			Window.alert( "Die Email des Nutzers ist nicht in der Datenbank." + " Bitte erstelle ein neues Nutzerporofil");

			NutzerForm nutzerForm = new NutzerForm();
			nutzerForm.ladeNutzerForm(profil.getEmailAddress());
		}

	}
}

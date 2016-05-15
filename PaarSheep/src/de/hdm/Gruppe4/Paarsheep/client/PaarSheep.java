package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.gui.NutzerForm;

import de.hdm.Gruppe4.Paarsheep.client.gui.Fusszeile;
import de.hdm.Gruppe4.Paarsheep.client.gui.Navigationsleiste;

import de.hdm.Gruppe4.Paarsheep.client.gui.ProfilseiteForm;
import de.hdm.Gruppe4.Paarsheep.client.gui.Startseite;
import de.hdm.Gruppe4.Paarsheep.shared.LoginService;
import de.hdm.Gruppe4.Paarsheep.shared.LoginServiceAsync;

public class PaarSheep implements EntryPoint {
	
	
//-----------------------------------------------------------------------------
	
	//Diese Dinge werden für den Login gebraucht
	
	 private LoginInfo loginInfo = null;
	  private VerticalPanel loginPanel = new VerticalPanel();
	  private Label loginLabel = new Label(
	      "Please sign in to your Google Account to access the PaarSheep application.");
	  private Anchor signInLink = new Anchor("Sign In");
	  private Anchor signOutLink = new Anchor("Sign Out");

//-----------------------------------------------------------------------------

	public void onModuleLoad() { // Check login status using login service.
	    LoginServiceAsync loginService = GWT.create(LoginService.class);
	    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
	      public void onFailure(Throwable error) {
	      }

	      public void onSuccess(LoginInfo result) {
	        loginInfo = result;
	        if(loginInfo.isLoggedIn()) {
	          loadPaarsheep();
	        } else {
	          loadLogin();
	        }
	      }
	    });}
	
	
	private void loadLogin() {
	    // Assemble login panel.
	    signInLink.setHref(loginInfo.getLoginUrl());
	    loginPanel.add(loginLabel);
	    loginPanel.add(signInLink);
	    RootPanel.get("NutzerForm").add(loginPanel);
	  }
	
	
	
//-----------------------------------------------------------------------------	

	private void loadPaarsheep() {
		signOutLink.setHref(loginInfo.getLogoutUrl());
		RootPanel.get("Profil").add(signOutLink);
		
		// Einf�gen der horizontalen Navigationsleiste
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

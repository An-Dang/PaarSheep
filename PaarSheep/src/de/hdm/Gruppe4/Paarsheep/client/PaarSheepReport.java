package de.hdm.Gruppe4.Paarsheep.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.shared.LoginService;
import de.hdm.Gruppe4.Paarsheep.shared.LoginServiceAsync;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGenerator;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGeneratorAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

public class PaarSheepReport extends VerticalPanel implements EntryPoint {

	/*
	 * Instanz von PartnerboerseAdministrationAsync, welche es uns erlaubt, die
	 * Methoden zu verwenden.
	 */
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	private static String editorHtmlName = "PaarSheepReport.html";

	/*
	 * Diese Dinge werden für den Login gebraucht
	 */
	private Nutzerprofil loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access the PaarSheep application.");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");

	Button unangesehenePartnervorschlaegeButton = new Button("Unangesehene Partnervorschlaege");

	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
	private static Nutzerprofil np = null;

	public void onModuleLoad() { // Check login status using login service.
		ReportGeneratorAsync reportService = GWT.create(ReportGenerator.class);
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + editorHtmlName, new AsyncCallback<Nutzerprofil>() {
		
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

	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("Container").add(loginPanel);
	}

	public void loadNavigator() {

		// MenuBar erstellen
			MenuBar menu = new MenuBar();
			menu.setAutoOpen(true);
			menu.setWidth("500px");
			menu.setAnimationEnabled(true);

		// MenuBar bauen
		MenuBar reportMenu = new MenuBar(true);
		reportMenu.setAnimationEnabled(true);
		MenuBar editorMenu = new MenuBar(true);
		editorMenu.setAnimationEnabled(true);
		
		menu.addItem(new MenuItem("Deine Traumschafe", reportMenu));
		menu.addItem(new MenuItem("Editor", editorMenu));
		
		reportMenu.addItem("Eigenes Profil ", new Command(){
			public void execute() {
				RootPanel.get("Container").clear();
				AnzeigeNutzerprofilReport nutzerprofilReport = new AnzeigeNutzerprofilReport ();
				RootPanel.get("Container").add(nutzerprofilReport);
			}
		});
		
		reportMenu.addItem("Ungesehene Partnervorschläge", new Command(){
			public void execute() {
				RootPanel.get("Container").clear();
				AnzeigenPartnervorschleageNPReport alleNutzerprofileReport = new AnzeigenPartnervorschleageNPReport ();
				RootPanel.get("Container").add(alleNutzerprofileReport);
			}
		});
		
		reportMenu.addItem("Partnervorschläge nach Suchprofil ", new Command(){
			public void execute() {
				RootPanel.get("Container").clear();
				AnzeigenPartnervorschlaegeSpReport alleNutzerprofileReport = new AnzeigenPartnervorschlaegeSpReport ();
				RootPanel.get("Container").add(alleNutzerprofileReport);
			}
		});
		
		reportMenu.addSeparator();
		
		editorMenu.addItem("Dein Profil", new Command() {
			public void execute() {
				Window.Location.replace("PaarSheep.html");
			}
		});
		
		RootPanel.get("navigator").add(menu);
	}

	// Diese Methode organisiert den asynchronen Callback und gibt uns eine
	// Nachricht aus, ob dieser Callback funktioniert
	class CheckStatusNutzerprofilCallback implements AsyncCallback<Nutzerprofil> {
		public void onFailure(Throwable caught) {
			Window.alert("Die Datenbank konnte nicht abgefragt werden!");
		}
		public void onSuccess(Nutzerprofil profil) {
			ClientsideSettings.setAktuellerUser(profil);
			final boolean status = profil.getStatus();
			if (status == true) {
				loadNavigator();
			} else {
				Window.alert("Die Email des Nutzers ist nicht in der Datenbank."
						+ " Bitte erstelle ein neues Nutzerporofil");
			}
		}
	}
}
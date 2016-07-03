package de.hdm.Gruppe4.Paarsheep.client;

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

/**
 * Diese Klasse erweitert das Vertical Panel und sort fuer die Anzeige
 * des Reports auf der Partnerboerse.
 * @author An Dang
 *
 */
public class PaarSheepReport extends VerticalPanel implements EntryPoint {

	/*
	 * Instanz von PartnerboerseAdministrationAsync, welche es uns erlaubt, die
	 * Methoden zu verwenden.
	 */
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	private static String editorHtmlName = "PaarSheepReport.html";

	/*
	 * Diese Variablen werden für den Login instanziiert
	 */
	private Nutzerprofil loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access the PaarSheep application.");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");

	Button unangesehenePartnervorschlaegeButton = new Button("Unangesehene Partnervorschlaege");

	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	/**
	 * Pruefung, ob Login erfolgreich und Email Adresse vergeben
	 * @param reportService
	 * @param loginService
	 */
	public void onModuleLoad() { 
		ReportGeneratorAsync reportService = GWT.create(ReportGenerator.class);
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + editorHtmlName, new AsyncCallback<Nutzerprofil>() {
		
			/** um Fehler abzufangen */
			public void onFailure(Throwable error) {
			}
			/**
			 * pruefen, ob User eingeloggt ist bzw. Profil mit Email Adresse
			 * vorhanden ist
			 */
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

	/**
	 * Login Panel fuer User
	 */
	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("Container").add(loginPanel);
	}

	/**
	 * Navigationsleiste wird ausgegeben; Menuleiste wird zusammen
	 * gestellt.
	 * @param menu Menubar speichern
	 * @param reportMenu Menu des Reports speichern
	 * @param editorMenu Menu zum editieren von Inhalten speichern
	 * 
	 */
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
		MenuBar logoutMenu = new MenuBar(true);
		logoutMenu.setAnimationEnabled(true);
		
		menu.addItem(new MenuItem("Deine Traumschafe", reportMenu));
		menu.addItem(new MenuItem("Editor", editorMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Logout", new Command(){
			public void execute() {
				loadLogout(nutzerprofil);
			}
		}));
		/**
		 * Button fuer Report Anzeige hinzufuegen
		 * @param nutzerprofilReport Nutzerprofil im Report
		 */
		reportMenu.addItem("Eigenes Profil ", new Command(){
			public void execute() {
				RootPanel.get("Container").clear();
				AnzeigeNutzerprofilReport nutzerprofilReport = new AnzeigeNutzerprofilReport ();
				RootPanel.get("Container").add(nutzerprofilReport);
			}
		});
		
		/**
		 * Button fuer nicht angesehene Profile hinzufuegen
		 * @param alleNutzerprofileReport Nutzerprofile fuer Report speichern
		 */
		reportMenu.addItem("Ungesehene Partnervorschläge", new Command(){
			public void execute() {
				RootPanel.get("Container").clear();
				RootPanel.get("Details").clear();
				AnzeigenPartnervorschleageNPReport alleNutzerprofileReport = new AnzeigenPartnervorschleageNPReport ();
				RootPanel.get("Container").add(alleNutzerprofileReport);
			}
		});
		
		/**
		 * Button fuer Suchprofile hinzufuegen
		 * @param alleNutzerprofileReport Nutzerprofile fuer den Report speichern
		 */
		reportMenu.addItem("Partnervorschläge nach Suchprofil ", new Command(){
			public void execute() {
				RootPanel.get("Container").clear();
				RootPanel.get("Details").clear();
				AnzeigenPartnervorschlaegeSpReport alleNutzerprofileReport = new AnzeigenPartnervorschlaegeSpReport ();
				RootPanel.get("Container").add(alleNutzerprofileReport);
			}
		});
		
		reportMenu.addSeparator();
		
		/**
		 * Button fuer ansehen von eigenem Profil hinzufuegen
		 */
		editorMenu.addItem("Dein Profil", new Command() {
			public void execute() {
				Window.Location.replace("PaarSheep.html");
			}
		});
		
		RootPanel.get("navigator").add(menu);
	}

	/**
	 * Diese Klasse organisiert den asynchronen Callback und gibt eine Nach-
	 * richt aus, ob der Callback funktioniert hat.
	 * @author An Dang
	 *
	 */
	class CheckStatusNutzerprofilCallback implements AsyncCallback<Nutzerprofil> {
		/** um Fehler abzufangen */
		public void onFailure(Throwable caught) {
			Window.alert("Die Datenbank konnte nicht abgefragt werden!");
		}
		/**
		 * Profil des gerade eingeloggten Users anzeigen
		 * @param status Status, ob User eingeloggt ist oder nicht
		 */
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
	/**
	 * Logout des Users durchfuehren und andere Seite zeigen.
	 * @param profil Profil ID des aktuellen Users
	 * @param logoutURL um auf ausgeloggte URL zu leiten
	 */
	public void loadLogout(Nutzerprofil profil) {
		final String logoutURL = profil.getLogoutUrl();
		Window.Location.assign(logoutURL);

	}
}
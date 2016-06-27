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

import de.hdm.Gruppe4.Paarsheep.client.gui.NutzerForm;
import de.hdm.Gruppe4.Paarsheep.client.gui.PartnervorschleageByUngesehenenNutzerprofilenReportAnzeige;
import de.hdm.Gruppe4.Paarsheep.client.gui.Startseite;
import de.hdm.Gruppe4.Paarsheep.shared.LoginService;
import de.hdm.Gruppe4.Paarsheep.shared.LoginServiceAsync;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.ReportGeneratorAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.report.PartnervorschleageByUngesehenenNutzerprofilenReport;

public class PaarSheepReport extends VerticalPanel implements EntryPoint {


	/*
	 * Instanz von PartnerboerseAdministrationAsync, welche es uns erlaubt, die
	 * Methoden zu verwenden.
	 */
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	
	private static String editorHtmlName = "PaarSheepReport.html";


	// -----------------------------------------------------------------------------
	/*
	 * Diese Dinge werden für den Login gebraucht
	 */
	private Nutzerprofil loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access the PaarSheep application.");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	

	Button unangesehenePartnervorschlaegeButton = new Button(
			"Unangesehene Partnervorschlaege");
	
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
	
	private static Nutzerprofil np = null;
	// -----------------------------------------------------------------------------

	public void onModuleLoad() { // Check login status using login service.
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

	// -----------------------------------------------------------------------------

	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("Login").add(loginPanel);
	}
	
	
	
	private void createNavigator() {
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setWidth("720px");
		menu.setHeight("36px");
		menu.setStyleName("MenuBarRep");
		menu.setAnimationEnabled(true);

		// Create the file menu
		MenuBar partnervorschlaegeMenu = new MenuBar(true);
		partnervorschlaegeMenu.setAnimationEnabled(true);

		MenuItem unangesehenePartnervorschlaege = partnervorschlaegeMenu
				.addItem("Unangesehene Partnervorschlaege", new Command() {
	
					public void execute() {
						PartnervorschleageByUngesehenenNutzerprofilenReportAnzeige partnervorschleageByUngesehenenNutzerprofilenReportAnzeige = new PartnervorschleageByUngesehenenNutzerprofilenReportAnzeige(np);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(partnervorschleageByUngesehenenNutzerprofilenReportAnzeige);
					}
				});

		unangesehenePartnervorschlaege.setStyleName("MenuItemRep");

//		MenuItem partnervorschlaegeSp = partnervorschlaegeMenu.addItem(
//				"Partnervorschlaege anhand von Suchprofilen", new Command() {
//
//					@Override
//					public void execute() {
//						ShowAllPartnervorschlaegeSpReport showAllPartnervorschlaegeSpReport = new ShowAllPartnervorschlaegeSpReport(np);
//
//						RootPanel.get("Details").clear();
//						RootPanel.get("Details").add(
//								showAllPartnervorschlaegeSpReport);
//
//					}
//
//				});
		
		
	//	partnervorschlaegeSp.setStyleName("MenuItemRep");

		partnervorschlaegeMenu.addSeparator();

		menu.addItem(new MenuItem("Meine Partnervorschlaege",
				partnervorschlaegeMenu));
		menu.addSeparator();
		
		// add the menu to the root panel
				RootPanel.get("navigator").add(menu);
	
}
	
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
			
			final boolean status = profil.getStatus();

			if (status == true) {
			
				createNavigator();

			} else {
				Window.alert( "Die Email des Nutzers ist nicht in der Datenbank." + " Bitte erstelle ein neues Nutzerporofil");

				
			}

		}
	}

}
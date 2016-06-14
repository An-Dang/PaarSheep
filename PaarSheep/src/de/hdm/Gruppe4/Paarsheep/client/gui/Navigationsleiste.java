package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Merkzettel;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public class Navigationsleiste {

	// -------------------------------------------------------------------------
	private HorizontalPanel navigatorpanel = new HorizontalPanel();
	private HorizontalPanel leftpanel = new HorizontalPanel();
	private HorizontalPanel rightpanel = new HorizontalPanel();

	private Label paarsheeplabel = new Label("PaarSheep");

	private Button logout = new Button("Logout");

	private Button suchprofilBtn = new Button("Suchprofil");

	private Button kontaktsperrliste = new Button("Kontaktsperrliste");
	private Button merkzettel = new Button("Merkzettel");
	private Button startseite = new Button("Startseite");
	private Button AlleNutzerAnzeigen = new Button("AlleNutzerAnzeigen");

	// -------------------------------------------------------------------------

	public void loadNavigator(Nutzerprofil nutzerprofil) {
		final Nutzerprofil profil = nutzerprofil;

		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setWidth("900px");
		menu.setAnimationEnabled(true);

		// MenuBar wird erstellt
		MenuBar nutzerprofilMenu = new MenuBar(true);
		nutzerprofilMenu.setAnimationEnabled(true);
		
		nutzerprofilMenu.addItem("Profil anzeigen", new Command() {
			@Override
			public void execute() {

				Startseite ladeStartseite = new Startseite();
				ladeStartseite.ladeStartseite(profil);

			}
		});

		nutzerprofilMenu.addItem("Profil bearbeiten", new Command() {
			@Override
			public void execute() {
				RootPanel.get("NutzerForm").clear();

				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();

				ProfilBearbeiten bearbeiteProfil = new ProfilBearbeiten();
				bearbeiteProfil.loadProfilEditieren(profil);

			}
		});

		// MERKLISTE

		nutzerprofilMenu.addItem("Merklise anzeigen", new Command() {

			@Override
			public void execute() {
				RootPanel.get("NutzerForm").clear();

				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();

				MerkzettelForm zeigeMerkliste = new MerkzettelForm(profil);

				RootPanel.get("NutzerForm").add(zeigeMerkliste);

				// HIER METHODE ZUM ANZEIGEN DER MERKLISTE EINF�GEN

			}

		});

		// SPERRLISTE

		nutzerprofilMenu.addItem("Sperrliste anzeigen", new Command() {

			@Override
			public void execute() {
				RootPanel.get("NutzerForm").clear();

				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();

				final KontaktsperreForm zeigeKontaktsperre = new KontaktsperreForm(profil);

				RootPanel.get("NutzerForm").add(zeigeKontaktsperre);

				// HIER METHODE ZUM ANZEIGEN DER KONTAKTSPERRE EINF�GEN

			}

		});

		nutzerprofilMenu.addSeparator();

		// Men� f�r das Suchprofil
		MenuBar suchprofilMenu = new MenuBar(true);
		suchprofilMenu.setAnimationEnabled(true);

		suchprofilMenu.addItem("Neues Suchprofil anlegen", new Command() {

			@Override
			public void execute() {

				/**
				 * Sobald ein neues Suchprofil angelegt wird, wird die Tabelle
				 * in der Datenbank, die die Partnervorschlaege anhand von
				 * Suchprofilen enthaelt, geleert.
				 */
				RootPanel.get("NutzerForm").clear();

				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();

				SuchprofilErstellenForm erstelleSuchprofil = new SuchprofilErstellenForm();
				erstelleSuchprofil.ladeSuchprofilErstellenForm(profil);
				// HIER METHODE ZUM ERSTELLEN EINES SUCHPROFILS EINF�GEN

				// RootPanel.get("NutzerForm").add(erstelleSuchprofil);
			}

		});

		suchprofilMenu.addItem("Suchprofile anzeigen", new Command() {
			@Override
			public void execute() {
				Suchprofilseite zeigeSuchprofil = new Suchprofilseite();
				RootPanel.get("NutzerForm").clear();

				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				zeigeSuchprofil.ladeSuchprofilseite(profil);
			}
		});

		suchprofilMenu.addSeparator();

		MenuBar partnervorschlaegeMenu = new MenuBar(true);
		partnervorschlaegeMenu.setAnimationEnabled(true);

		partnervorschlaegeMenu.addItem("Unangesehene Partnervorschlaege anzeigen", new Command() {

			@Override
			public void execute() {

			}

		});

		partnervorschlaegeMenu.addItem("Partnervorschlaege anhand von Suchprofilen anzeigen", new Command() {

			@Override
			public void execute() {

			}

		});

		partnervorschlaegeMenu.addSeparator();

		MenuBar alleNutzeranzeigen = new MenuBar(true);
		alleNutzeranzeigen.setAnimationEnabled(true);
		alleNutzeranzeigen.addItem("Alle Nutzer der Partnerboerse anzeigen", new Command() {

			@Override
			public void execute() {
				AlleNutzerAnzeigenTest zeigeAlleNutzer = new AlleNutzerAnzeigenTest(profil);
				RootPanel.get("NutzerForm").clear();

				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				RootPanel.get("NutzerForm").add(zeigeAlleNutzer);

			}

		});

		alleNutzeranzeigen.addSeparator();

		menu.addItem(new MenuItem("Mein Profil", nutzerprofilMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Mein Suchprofil", suchprofilMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Meine Partnervorschlaege", partnervorschlaegeMenu));
		menu.addItem(new MenuItem("Alle Nutzer der Partnerboerse", alleNutzeranzeigen));
		

		// add the menu to the root panel

		RootPanel.get("navigator").clear();

		leftpanel.add(paarsheeplabel);

		rightpanel.add(startseite);
		rightpanel.add(merkzettel);
		rightpanel.add(kontaktsperrliste);
		rightpanel.add(AlleNutzerAnzeigen);

		rightpanel.add(suchprofilBtn);
		rightpanel.add(logout);

		leftpanel.add(logout);

		navigatorpanel.add(leftpanel);
		navigatorpanel.add(rightpanel);

		RootPanel.get("navigator").add(menu);
		// RootPanel.get("navigator").add(navigatorpanel);

		// -------------------------------------------------------------------------
		// Button zeigt das Eigene Profil an.
		startseite.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				RootPanel.get("NutzerForm").clear();

				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();

				Startseite startseite = new Startseite();
				startseite.ladeStartseite(profil);
			}
		});

		logout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loadLogout(profil);
			}
		});

		// Suchprofil-Button
		suchprofilBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Hier entsteht das Suchrprofil");
				Suchprofilseite suchprofilseite = new Suchprofilseite();
				suchprofilseite.ladeSuchprofilseite(profil);
				;

			}

		});

		// Kontaktsperre-Button
		kontaktsperrliste.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				KontaktsperreForm kontaktsperreform = new KontaktsperreForm(profil);
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				RootPanel.get("Profil").add(kontaktsperreform);
			}
		});

		// Merkzettel-Button
		merkzettel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// MerkzettelForm merkzettelForm = new MerkzettelForm(profil);
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				// RootPanel.get("Profil").add(merkzettelForm);
			}
		});

		// AlleNutzerAnzeigen-Button
		AlleNutzerAnzeigen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				AlleNutzerAnzeigenTest alleNutzerAnzeigen = new AlleNutzerAnzeigenTest(profil);
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				RootPanel.get("Profil").add(alleNutzerAnzeigen);
			}
		});

	}
	// -------------------------------------------------------------------------

	public void loadStartseite() {

	}

	public void loadLogout(Nutzerprofil profil) {
		final String logoutURL = profil.getLogoutUrl();
		Window.Location.assign(logoutURL);

	}

	// -------------------------------------------------------------------------
	public void loadSuchprofilseite() {

	}

}
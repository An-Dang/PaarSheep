package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

public class Navigationsleiste extends VerticalPanel{

	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	public void loadNavigator() {

		// MenuBar erstellen
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setWidth("300px");
		menu.setAnimationEnabled(true);

		// MenuBar bauen
		MenuBar nutzerprofilMenu = new MenuBar(true);
		nutzerprofilMenu.setAnimationEnabled(true);
		MenuBar suchprofilMenu = new MenuBar(true);
		suchprofilMenu.setAnimationEnabled(true);
		MenuBar logout = new MenuBar(true);
		logout.setAnimationEnabled(true);
		
		menu.addItem(new MenuItem("Mein Profil", nutzerprofilMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Mein Suchprofil", suchprofilMenu));
		menu.addSeparator();
//		menu.addItem(new MenuItem("Meine Partnervorschlaege", partnervorschlaegeMenu));
		menu.addItem(new MenuItem("Logout", logout));

		// Erster Reiter Dein Profil
		nutzerprofilMenu.addItem("Dein Profil", new Command() {

			@Override
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				Startseite ladeStartseite = new Startseite();
				ladeStartseite.ladeStartseite();

			}

		});

		nutzerprofilMenu.addItem("Profil bearbeiten", new Command() {

			@Override
			public void execute() {
				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
				RootPanel.get("NutzerForm").add(profilBearbeiten);

			}

		});
		
		
		nutzerprofilMenu.addItem("Deine Lieblingsschaafe", new Command() {

			@Override
			public void execute() {
				MerkzettelForm merkzettelForm = new MerkzettelForm();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				RootPanel.get("Profil").add(merkzettelForm);

			}

		});
		
		nutzerprofilMenu.addItem("Deine Schwarzenschaafe", new Command(){

			@Override
			public void execute() {
				KontaktsperreForm kontaktsperreform = new KontaktsperreForm();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				RootPanel.get("Profil").add(kontaktsperreform);
				
			}
			
		});
		
		nutzerprofilMenu.addItem("Alle Nutzer Anzeigen", new Command(){

			@Override
			public void execute() {
				AlleNutzerAnzeigenTest alleNutzerAnzeigen = new AlleNutzerAnzeigenTest(nutzerprofil);
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				RootPanel.get("Profil").add(alleNutzerAnzeigen);
				
			}
			
		});

		
		nutzerprofilMenu.addSeparator();
		
		//Suchprofil
		suchprofilMenu.addItem("Deine Suchprofile", new Command(){

			@Override
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
				RootPanel.get("Profil").add(suchprofilAnzeigen);
				;
				
			}
			
		});
		
		
		//Suchprofil
		suchprofilMenu.addItem("Suchprofil erstellen", new Command(){

			@Override
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				SuchprofilErstellenForm suchprofilErstellenForm = new SuchprofilErstellenForm();
				RootPanel.get("Profil").add(suchprofilErstellenForm);
				;
				
			}
			
		});
		
		
		nutzerprofilMenu.addSeparator();
		
		//Logout
		
		logout.addItem("Logout", new Command(){

			@Override
			public void execute() {
				loadLogout(nutzerprofil);
				
			}
			
		});

		RootPanel.get("navigator").clear();
		RootPanel.get("navigator").add(menu);

	}
//
//	public void loadStartseite() {
//
//	}

	// Logout
	public void loadLogout(Nutzerprofil profil) {
		final String logoutURL = profil.getLogoutUrl();
		Window.Location.assign(logoutURL);

	}
//
//	public void loadSuchprofilseite() {
//
//	}
}
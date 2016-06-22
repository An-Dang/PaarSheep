package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

//import de.hdm.Gruppe4.Paarsheep.client.AnzeigenPartnervorschlaege;
import de.hdm.Gruppe4.Paarsheep.client.AnzeigenPartnervorschlaegeNp;
import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * @author andang
 *
 */
public class Navigationsleiste extends VerticalPanel{

	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	/**
	 * 
	 */
	public void loadNavigator() {

		// MenuBar erstellen
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setWidth("500px");
		menu.setAnimationEnabled(true);

		// MenuBar bauen
		MenuBar nutzerprofilMenu = new MenuBar(true);
		nutzerprofilMenu.setAnimationEnabled(true);
		MenuBar suchprofilMenu = new MenuBar(true);
		suchprofilMenu.setAnimationEnabled(true);
		MenuBar partnervorschlaegeMenu = new MenuBar(true);
		partnervorschlaegeMenu.setAnimationEnabled(true);
		
		menu.addItem(new MenuItem("Mein Profil", nutzerprofilMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Mein Suchprofil", suchprofilMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Meine Partnervorschlaege", partnervorschlaegeMenu));

		// Erster Reiter Dein Profil
		nutzerprofilMenu.addItem("Dein Profil", new Command() {

			@Override
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				Startseite ladeStartseite = new Startseite();
				ladeStartseite.ladeStartseite();

			}

		});

		nutzerprofilMenu.addItem("Profil bearbeiten", new Command() {

			@Override
			public void execute() {
				RootPanel.get("Profil").clear();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("EigenschaftForm").clear();
				ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
				RootPanel.get("NutzerForm").add(profilBearbeiten);

			}

		});
		
//		nutzerprofilMenu.addItem("Dein Profilinfo", new Command() {
//
//			@Override
//			public void execute() {
//				RootPanel.get("Profil").clear();
//				RootPanel.get("NutzerForm").clear();
//				RootPanel.get("EigenschaftForm").clear();
//				ProfilInfo profilInfo = new ProfilInfo();
//				RootPanel.get("NutzerForm").add(profilInfo);
//
//			}
//
//		});

		
		
		nutzerprofilMenu.addItem("Deine Lieblingsschafe", new Command() {

			@Override
			public void execute() {
				MerkzettelForm merkzettelForm = new MerkzettelForm();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				RootPanel.get("Profil").add(merkzettelForm);

			}

		});
		
		nutzerprofilMenu.addItem("Deine Schwarzenschafe", new Command(){

			@Override
			public void execute() {
				KontaktsperreForm kontaktsperreform = new KontaktsperreForm();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				RootPanel.get("Profil").add(kontaktsperreform);
				
			}
			
		});
		
		//Logout
		
		nutzerprofilMenu.addItem("Logout", new Command(){

			@Override
			public void execute() {
				loadLogout(nutzerprofil);
				
			}
			
		});

		
		nutzerprofilMenu.addSeparator();
		
		//partnervorschläge
		partnervorschlaegeMenu.addItem("Ähnlichkeitsmaß", new Command(){

			@Override
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				AnzeigenPartnervorschlaegeNp anzeigenPartnervorschlaegeNp = new AnzeigenPartnervorschlaegeNp();
				RootPanel.get("Profil").add(anzeigenPartnervorschlaegeNp);
				
			}
			
		});
		
		
		partnervorschlaegeMenu.addItem("Deine Traumschafe", new Command(){

			@Override
			public void execute() {
				AlleNutzerAnzeigenTest alleNutzerAnzeigen = new AlleNutzerAnzeigenTest();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				RootPanel.get("Profil").add(alleNutzerAnzeigen);
				
			}
			
		});
		
		nutzerprofilMenu.addSeparator();
		
		//Eigene Suchprofile anzeigen
		suchprofilMenu.addItem("Deine Suchprofile", new Command(){

			@Override
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();

				RootPanel.get("Steckbrief").clear();
				RootPanel.get("Zusinf").clear();
				
				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();

				RootPanel.get("Profil").add(suchprofilAnzeigen);
			}
		});
		
		
		//Suchprofil erstellen
		suchprofilMenu.addItem("Suchprofil erstellen", new Command(){

			@Override
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				SuchprofilErstellenForm suchprofilErstellenForm = new SuchprofilErstellenForm();
				RootPanel.get("Profil").add(suchprofilErstellenForm);	
			}
		});
		

		RootPanel.get("navigator").clear();
		RootPanel.get("navigator").add(menu);

	}


	// Logout
	public void loadLogout(Nutzerprofil profil) {
		final String logoutURL = profil.getLogoutUrl();
		Window.Location.assign(logoutURL);

	}
}
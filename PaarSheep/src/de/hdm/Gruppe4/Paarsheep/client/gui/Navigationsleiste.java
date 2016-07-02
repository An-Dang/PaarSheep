package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

//import de.hdm.Gruppe4.Paarsheep.client.AnzeigenPartnervorschlaege;
import de.hdm.Gruppe4.Paarsheep.client.AnzeigenPartnervorschlaegeNp;
import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * @author andang
 *
 */
public class Navigationsleiste extends VerticalPanel{
	
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	/**
	 * lädt die Navigationsleiste
	 */
	public void loadNavigator() {

		// MenuBar erstellen
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setWidth("517px");
		menu.setAnimationEnabled(true);

		// MenuBar bauen
		MenuBar nutzerprofilMenu = new MenuBar(true);
		nutzerprofilMenu.setAnimationEnabled(true);
		MenuBar suchprofilMenu = new MenuBar(true);
		suchprofilMenu.setAnimationEnabled(true);
		MenuBar partnervorschlaegeMenu = new MenuBar(true);
		partnervorschlaegeMenu.setAnimationEnabled(true);
		MenuBar reportMenu = new MenuBar(true);
		reportMenu.setAnimationEnabled(true);
		MenuBar logoutMenu = new MenuBar(true);
		logoutMenu.setAnimationEnabled(true);
		
		menu.addItem(new MenuItem("Mein Profil", nutzerprofilMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Mein Suchprofil", suchprofilMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Meine Partnervorschläge", partnervorschlaegeMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Report", reportMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Logout", new Command(){
			public void execute() {
				loadLogout(nutzerprofil);
			}
		}));
		

		// Erster Reiter Dein Profil
		nutzerprofilMenu.addItem("Dein Profil", new Command() {
			public void execute() {
				RootPanel.get("Profil").clear();
				Startseite ladeStartseite = new Startseite();
				ladeStartseite.ladeStartseite();
			}
		});

		nutzerprofilMenu.addItem("Profil bearbeiten", new Command() {
			public void execute() {
				RootPanel.get("Profil").clear();
				ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
				RootPanel.get("Profil").add(profilBearbeiten);
			}
		});
		
		nutzerprofilMenu.addItem("Deine Lieblingsschafe", new Command() {
			public void execute() {
				MerkzettelForm merkzettelForm = new MerkzettelForm();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				RootPanel.get("Profil").add(merkzettelForm);
			}
		});
		
		nutzerprofilMenu.addItem("Deine Schwarzenschafe", new Command(){
			public void execute() {
				KontaktsperreForm kontaktsperreform = new KontaktsperreForm();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				RootPanel.get("Profil").add(kontaktsperreform);
			}
		});
		
		//Profil löschen
		nutzerprofilMenu.addItem("Dein Profil löschen", new Command(){
			public void execute() {
				partnerboerseVerwaltung.deleteNutzerprofil(nutzerprofil.getProfilID(), new AsyncCallback<Void>(){
					public void onFailure(Throwable caught) {
						Window.alert("Du konntest dein Profil nicht löschen!");
					}

					public void onSuccess(Void result) {
						Window.alert("Du hast dein Profil Gelöscht! "+
								"Wir hoffen du hast deinen Traumschaf gefunden =) ");
							loadLogout(nutzerprofil);
					}
					
				});
			}
		});
		nutzerprofilMenu.addSeparator();
		
		//partnervorschläge
		partnervorschlaegeMenu.addItem("Ähnlichkeitsmaß", new Command(){
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				AnzeigenPartnervorschlaegeNp anzeigenPartnervorschlaegeNp = new AnzeigenPartnervorschlaegeNp();
				RootPanel.get("Profil").add(anzeigenPartnervorschlaegeNp);
			}
		});
		
		partnervorschlaegeMenu.addItem("Alle Traumschafe", new Command(){
			public void execute() {
				AlleNutzerAnzeigen alleNutzerAnzeigen = new AlleNutzerAnzeigen();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				RootPanel.get("Profil").add(alleNutzerAnzeigen);
			}
		});
		nutzerprofilMenu.addSeparator();
		
		//Eigene Suchprofile anzeigen
		suchprofilMenu.addItem("Deine Suchprofile", new Command(){
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
				RootPanel.get("Profil").add(suchprofilAnzeigen);
		
			}
		});
		
		//Suchprofil erstellen
		suchprofilMenu.addItem("Suchprofil erstellen", new Command(){
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				SuchprofilErstellenForm suchprofilErstellenForm = new SuchprofilErstellenForm();
				RootPanel.get("Profil").add(suchprofilErstellenForm);	
			}
		});
		nutzerprofilMenu.addSeparator();
		
		//Report
		reportMenu.addItem("Report", new Command(){
			public void execute() {
				Window.Location.replace("PaarSheepReport.html");
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
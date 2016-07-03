package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * Diese Klasse erweitert das Vertical Panel.
 * @author andang
 *
 */
public class Navigationsleiste extends VerticalPanel{
	
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	/**
	 * lädt die Navigationsleiste
	 * @param menu erstellt neues Menu
	 * @param nutzerprofilMenu neues Element fuer Nutzer
	 * @param suchprofilMenu Suchprofil als Element hinzufuegen
	 * @param partnervorschlaegeMenu Partnervorschlaege/Aehnlichkeit hinzufuegen
	 * @param reportMenu Report hinzufuegen
	 * @param logoutMenu Logout hinzufuegen
	 */
	public void loadNavigator() {

		// MenuBar erstellen
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setWidth("430px");
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
		menu.addItem(new MenuItem("Alle Schafe", new Command(){
			public void execute() {
				AlleNutzerAnzeigen alleNutzerAnzeigen = new AlleNutzerAnzeigen();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				RootPanel.get("Profil").add(alleNutzerAnzeigen);
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Report",  new Command(){
			public void execute() {
				Window.Location.replace("PaarSheepReport.html");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Logout", new Command(){
			public void execute() {
				loadLogout(nutzerprofil);
			}
		}));
		
		nutzerprofilMenu.addSeparator();


			/**
			 * Startseite um neuen Reiter erweitern (Dein Profil anzeigen)
			 * @param ladeStartseite zum neuladen der Seite
			 */		
		nutzerprofilMenu.addItem("Dein Profil", new Command() {
			public void execute() {
				RootPanel.get("Profil").clear();
				Startseite ladeStartseite = new Startseite();
				ladeStartseite.ladeStartseite();
			}
		});

		/**
		 * Startseite um neuen Reiter erweitern (Profil bearbeiten)
		 * @param profilBearbeiten zum neuladen der Seite
		 */
		nutzerprofilMenu.addItem("Profil bearbeiten", new Command() {
			public void execute() {
				RootPanel.get("Profil").clear();
				ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
				RootPanel.get("Profil").add(profilBearbeiten);
			}
		});
		
		/**
		 * Startseite um neuen Reiter erweitern (Deine Lieblingsschafe bzw. Merkliste)
		 * @param merkzettelForm Reiter hinzufuegen
		 */
		nutzerprofilMenu.addItem("Deine Lieblingsschafe", new Command() {
			public void execute() {
				MerkzettelForm merkzettelForm = new MerkzettelForm();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				RootPanel.get("Profil").add(merkzettelForm);
			}
		});
		
		/**
		 * Startseite um neuen Reiter erweitern (Sperrliste)
		 * @param kontaktsperreform Reiter fuer Sperrliste
		 */
		nutzerprofilMenu.addItem("Deine Schwarzenschafe", new Command(){
			public void execute() {
				KontaktsperreForm kontaktsperreform = new KontaktsperreForm();
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				RootPanel.get("Profil").add(kontaktsperreform);
			}
		});
		
		/**
		 * Startseite um neuen Reiter erweitern (Profil loeschen)
		 */
		nutzerprofilMenu.addItem("Dein Profil löschen", new Command(){
			public void execute() {
				partnerboerseVerwaltung.deleteNutzerprofil(nutzerprofil.getProfilID(), new AsyncCallback<Void>(){
					/**
					 * Fehlermeldung, falls Profil nicht geloescht werden konnte
					 */
					public void onFailure(Throwable caught) {
						Window.alert("Du konntest dein Profil nicht löschen!");
					}

					/**
					 * Bestaetigung, dass Profil geloescht wurde
					 */
					public void onSuccess(Void result) {
						Window.alert("Du hast dein Profil Gelöscht! "+
								"Wir hoffen du hast deinen Traumschaf gefunden =) ");
							loadLogout(nutzerprofil);
					}
					
				});
			}
		});
		nutzerprofilMenu.addSeparator();

		/**
		 * Untermenu von Suchprofil: anegelegte Suchprofile anzeigen lassen
		 * @param suchprofilAnzeigen Anzeigen von gespeicherten Suchprofilen
		 */
		suchprofilMenu.addItem("Deine Suchprofile", new Command(){
			public void execute() {
				RootPanel.get("NutzerForm").clear();
				RootPanel.get("Profil").clear();
				RootPanel.get("EigenschaftForm").clear();
				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
				RootPanel.get("Profil").add(suchprofilAnzeigen);
		
			}
		});

		/**
		 * Untermenu von Suchprofil: Suchprofil anlegen und speichern
		 * @param suchprofilErstellenForm Suchprofil anlegen
		 */
		suchprofilMenu.addItem("Suchprofil erstellen", new Command(){
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
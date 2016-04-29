package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PaarSheep implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		VerticalPanel vpanel = new VerticalPanel();
		MenuBar menuBar = new MenuBar();
		menuBar.setStylePrimaryName("menuBar");

		Label platzhalterLabel = new Label("");
		platzhalterLabel.setStylePrimaryName("platzhalterLabel");

		Label profilLabel = new Label("Dein Profil");
		profilLabel.setStylePrimaryName("profilLabel");

		Label steckbriefLabel = new Label("Steckbrief");
		steckbriefLabel.setStylePrimaryName("steckbriefLabel");

		Label vornameLabel = new Label("Vorname: ");
		vornameLabel.setStylePrimaryName("vornameLabel");

		Label nachnameLabel = new Label("Nachname: ");
		nachnameLabel.setStylePrimaryName("nachnameLabel");

		Label koerpergroesseLabel = new Label("Körpergröße: ");
		koerpergroesseLabel.setStylePrimaryName("koerpergroesseLabel");

		Label haarfarbeLabel = new Label("Haarfarbe: ");
		haarfarbeLabel.setStylePrimaryName("haarfarbeLabel");

		Label raucherLabel = new Label("Raucher: ");
		raucherLabel.setStylePrimaryName("raucherLabel");

		Label religionLabel = new Label("Religion: ");
		religionLabel.setStylePrimaryName("religionLabel");

		// Zusätzliche Informationen
		Label zusinfLabel = new Label("Zusätzliche Informationen ");
		zusinfLabel.setStylePrimaryName("zusinfLabel");

		Label buchLabel = new Label("Mein Lieblingsbuch ist: ");
		buchLabel.setStylePrimaryName("buchLabel");

		Label urlaubLabel = new Label("Mein Lieblingsurlaubsort ist: ");
		urlaubLabel.setStylePrimaryName("urlaubLabel");

		Label sportLabel = new Label("Sport: ");
		sportLabel.setStylePrimaryName("sportLabel");

		Label musikLabel = new Label("Musik: ");
		musikLabel.setStylePrimaryName("musikLabel");

		Label userLabel = new Label("Bitte geben Sie Ihren Benutzernamen ein: ");
		userLabel.setStylePrimaryName("userLabel");

		TextBox userBox = new TextBox();
		userBox.setStylePrimaryName("userBox");

		Label pwLabel = new Label("Bitte geben Sie Ihr Passwort ein: ");
		pwLabel.setStylePrimaryName("pwLabel");

		PasswordTextBox pwBox = new PasswordTextBox();
		pwBox.setStylePrimaryName("passwordBox");

		Button loginBtn = new Button("Login");
		loginBtn.setStylePrimaryName("loginButton");

		Command cmd = new Command() {
			@Override
			public void execute() {
				Window.alert("Mein Profil wurde geklickt");
			}
		};
		menuBar.addItem(new MenuItem("Mein Profil", cmd));

		RootPanel.get().add(menuBar);

		RootPanel.get("Navigator").add(vpanel);

		vpanel.add(loginBtn);
		RootPanel.get().add(userLabel);
		RootPanel.get().add(userBox);
		RootPanel.get().add(pwLabel);
		RootPanel.get().add(pwBox);
		RootPanel.get().add(vpanel);

		RootPanel.get().add(platzhalterLabel);
		RootPanel.get().add(profilLabel);
		RootPanel.get().add(steckbriefLabel);
		RootPanel.get().add(vornameLabel);
		RootPanel.get().add(nachnameLabel);
		RootPanel.get().add(koerpergroesseLabel);
		RootPanel.get().add(haarfarbeLabel);
		RootPanel.get().add(raucherLabel);
		RootPanel.get().add(religionLabel);

		RootPanel.get().add(zusinfLabel);
		RootPanel.get().add(buchLabel);
		RootPanel.get().add(urlaubLabel);
		RootPanel.get().add(sportLabel);
		RootPanel.get().add(musikLabel);

		// Cursor ist direkt im Benutzernamen-Feld
		userBox.setFocus(true);

		// ClickHandler für den Login-Button
		loginBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Hallo du hast dich eingeloggt!");
			}

		});

	}
}

package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Beschreibung;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Information;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Option;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

/**
 * @author andang
 *
 */
public class SuchprofilErstellenForm extends VerticalPanel {

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();

	private Label ueberschriftLabel = new Label("Hier kannst du dein Suchprofil erstellen ");
	private Label infoLabel = new Label();

	private FlexTable eigenschaftFlexTable = new FlexTable();

	/**
	 * Widgets hinzufuegen.
	 */
	private Button erstelleSuchprofilButton = new Button("neues Suchprofil erstellen");
	private Button abbrechenButton = new Button("Abbrechen");

	/**
	 * Tabelle zum Anlegen des Suchprofils erstellen.
	 */
	private FlexTable erstelleSuchprofilFlexTable = new FlexTable();

	private TextBox suchprofilNameTextBox = new TextBox();
	private ListBox religionListBox = new ListBox();
	private TextBox koerpergroesseTextBox = new TextBox();
	private ListBox haarfarbeListBox = new ListBox();
	private ListBox raucherListBox = new ListBox();
	private ListBox geschlechtListBox = new ListBox();

	int row = 0;

	/**
	 * Button zum Anlegen eines neuen Suchprofils.
	 */
//	private Button erstelleSuchprofilButton = new Button("Neues Suchprofil anlegen");

	/**
	 * 
	 */
	public SuchprofilErstellenForm() {
		this.add(vPanel);
		vPanel.add(buttonPanel);

		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		erstelleSuchprofilFlexTable.setText(0, 0, "Suchprofilname");
		erstelleSuchprofilFlexTable.setText(1, 0, "Religion");
		erstelleSuchprofilFlexTable.setText(2, 0, "Körpergröße");
		erstelleSuchprofilFlexTable.setText(3, 0, "Haarfarbe");
		erstelleSuchprofilFlexTable.setText(4, 0, "Raucher");
		erstelleSuchprofilFlexTable.setText(5, 0, "Geschlecht");
		
		/**
		 * CSS-Anbindung
		 */
		erstelleSuchprofilFlexTable.setCellPadding(6);
		erstelleSuchprofilFlexTable.addStyleName("flexTable");

		erstelleSuchprofilFlexTable.setWidget(0, 2, suchprofilNameTextBox);

		religionListBox.addItem("Keine Angabe");
		religionListBox.addItem("Christentum");
		religionListBox.addItem("Islam");
		religionListBox.addItem("Judentum");
		religionListBox.addItem("Buddhismus");
		religionListBox.addItem("Hinduismus");
		religionListBox.addItem("Andere");
		erstelleSuchprofilFlexTable.setWidget(1, 2, religionListBox);

		erstelleSuchprofilFlexTable.setWidget(2, 2, koerpergroesseTextBox);

		haarfarbeListBox.addItem("Keine Angabe");
		haarfarbeListBox.addItem("Blond");
		haarfarbeListBox.addItem("Rot");
		haarfarbeListBox.addItem("Schwarz");
		haarfarbeListBox.addItem("Braun");
		haarfarbeListBox.addItem("Andere");
		erstelleSuchprofilFlexTable.setWidget(3, 2, haarfarbeListBox);

		raucherListBox.addItem("Keine Angabe");
		raucherListBox.addItem("Nichtraucher");
		raucherListBox.addItem("Gelegenheitsraucher");
		raucherListBox.addItem("Raucher");
		erstelleSuchprofilFlexTable.setWidget(4, 2, raucherListBox);

		geschlechtListBox.addItem("Keine Angabe");
		geschlechtListBox.addItem("Weiblich");
		geschlechtListBox.addItem("Männlich");
		geschlechtListBox.addItem("Andere");
		erstelleSuchprofilFlexTable.setWidget(5, 2, geschlechtListBox);
		
		
		/**
		 * ClickHandler für den Abbrechen-Button hinzufügen.
		 */
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				RootPanel.get("Profil").clear();
				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
				RootPanel.get("Profil").add(suchprofilAnzeigen);
			}
		});

		/**
		 * ClickHandler für den Suchprofil-Anlegen-Button hinzufügen.
		 */
		erstelleSuchprofilButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				String suchprofilName = suchprofilNameTextBox.getText();
				String religion = religionListBox.getSelectedItemText();
				String koerpergroesseString = koerpergroesseTextBox.getText();
				int koerpergroesse = Integer.parseInt(koerpergroesseString);
				String haarfarbe = haarfarbeListBox.getSelectedItemText();
				String raucher = raucherListBox.getSelectedItemText();
				String geschlecht = geschlechtListBox.getSelectedItemText();

				partnerboerseVerwaltung.insertSuchprofil(nutzerprofil.getProfilID(), suchprofilName, geschlecht,
						raucher, haarfarbe, religion, koerpergroesse, new InsertSuchprofilCallback());
				int suchprofilid = 0;
				RootPanel.get("Profil").clear();

				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
				RootPanel.get("Profil").add(suchprofilAnzeigen);

			}
		});
		
		/**
		 * Widgets zum VerticalPanel hinzufuegen.
		 */
		vPanel.add(ueberschriftLabel);
		vPanel.add(erstelleSuchprofilFlexTable);
		vPanel.add(eigenschaftFlexTable);
		vPanel.add(buttonPanel);
		buttonPanel.add(erstelleSuchprofilButton);
		buttonPanel.add(abbrechenButton);
	}

	// Diese Methode organisiert den asynchronen Callback und gibt uns eine
	// Nachricht aus, ob dieser Callback funktioniert
	class InsertSuchprofilCallback implements AsyncCallback<Suchprofil> {
		public void onFailure(Throwable caught) {
			Window.alert("Das Anlegen eines neuen Suchprofils ist fehlgeschlagen!");
		}
		public void onSuccess(Suchprofil suchprofil) {
			Window.alert("Das Anlegen eines neuen Suchprofils war erfolgreich!");
		}

	};
	
}

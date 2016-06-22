package de.hdm.Gruppe4.Paarsheep.client.gui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

/**
 * @author andang
 *
 */
public class SuchprofilErstellenForm extends VerticalPanel{
	
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
	
	
	private VerticalPanel vPanel = new VerticalPanel();

	private Label ueberschriftLabel = new Label("Hier kannst du dein Suchprofil erstellen ");

	

	/**
	 * Widgets hinzufuegen.
	 */
	private Button erstellenButton = new Button("Erstellen");

	
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
	

	/**
	 * Button zum Anlegen eines neuen Suchprofils.
	 */
	private Button erstelleSuchprofilButton = new Button("neues Suchprofil anlegen");


	/**
	 * 
	 */
	public SuchprofilErstellenForm() {
		this.add(vPanel);

	
	/**
	 * Erste Spalte der Tabelle festlegen.
	 */
	erstelleSuchprofilFlexTable.setText(0, 0, "Suchprofilname");
	erstelleSuchprofilFlexTable.setText(1, 0, "Religion");
	erstelleSuchprofilFlexTable.setText(2, 0, "Körpergröße");
	erstelleSuchprofilFlexTable.setText(3, 0, "Haarfarbe");
	erstelleSuchprofilFlexTable.setText(4, 0, "Raucher");
	erstelleSuchprofilFlexTable.setText(5, 0, "Geschlecht");
	
	
	erstelleSuchprofilFlexTable.setWidget(0, 2, suchprofilNameTextBox);
	
	
	religionListBox.addItem("Keine Angabe");
	religionListBox.addItem("Konfessionslos");
	religionListBox.addItem("Atheistitisch");
	religionListBox.addItem("Bahaisitisch");
	religionListBox.addItem("Buddhistisch");
	religionListBox.addItem("Evangelisch");
	religionListBox.addItem("Hinduistisch");
	religionListBox.addItem("Jüdisch");
	religionListBox.addItem("Katholisch");
	religionListBox.addItem("Muslimisch");
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

			

			partnerboerseVerwaltung.insertSuchprofil( nutzerprofil.getProfilID(), suchprofilName, geschlecht, raucher, haarfarbe,
					 religion, koerpergroesse,new InsertSuchprofilCallback());
			int suchprofilid = 0;
			RootPanel.get("Profil").clear();
			SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen(suchprofilid);
			
			RootPanel.get("Profil").add(suchprofilAnzeigen);
			
		}
	});

	}
	// Diese Methode organisiert den asynchronen Callback und gibt uns eine
	// Nachricht aus, ob dieser Callback funktioniert
	class InsertSuchprofilCallback implements AsyncCallback<Suchprofil> {
		
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Anlegen eines neuen Suchprofils ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Suchprofil suchprofil) {
		
				Window.alert("Das Anlegen eines neuen Suchprofils war erfolgreich!");
//				RootPanel.get("NutzerForm").clear();
//				RootPanel.get("Profil").clear();
//				RootPanel.get("Steckbrief").clear();
//				RootPanel.get("Zusinf").clear();
////				
//				SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
//				RootPanel.get("Profil").add(suchprofilAnzeigen);
//				

		}
		
		
	};


{
/**
 * Widgets zum VerticalPanel hinzufuegen.
 */
vPanel.add(ueberschriftLabel);
vPanel.add(erstelleSuchprofilFlexTable);
vPanel.add(erstelleSuchprofilButton);
}
}


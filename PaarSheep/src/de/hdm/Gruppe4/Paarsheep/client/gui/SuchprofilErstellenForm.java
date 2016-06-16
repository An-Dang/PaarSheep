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
			
			SuchprofilAnzeigenForm suchprofilAnzeigenForm = new SuchprofilAnzeigenForm();
	    	RootPanel.get("Profil").clear();
			RootPanel.get("Nutzerform").add(suchprofilAnzeigenForm);

				Window.alert("Das Anlegen eines neuen Suchprofils war erfolgreich!");

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

//
//		//Hier wird die Klasse PartnerboerseAdministration/Asyn instanziiert, damit 
//		//wir auf die Methode createNutzerprofil dieser Klasse zugreifen koennen. 
//		PartnerboerseAdministrationAsync partnerboerseVerwaltung = 
//				ClientsideSettings.getPartnerboerseVerwaltung();
//		
//		/**
//		 * Neues Nutzerprofil-Objekt anlegen mit Login-Infos.
//		 */
//		private Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();
//
//		private TextBox suchprofilTextBox = new TextBox();
//		private ListBox geschlechtListBox = new ListBox();
//		private TextBox haarfarbeTextBox = new TextBox();
//		private ListBox raucherListBox = new ListBox();
//		private ListBox religionListBox = new ListBox();
//		private TextBox koerpergroesseTextBox = new TextBox();
//
//		
//		Label idValueLabel = new Label();
//		VerticalPanel vPanel = new VerticalPanel();
//
//		
//
//		//Diese Methode laedt das Formular zur Erstellung eines neuen Nutzers
//		public void ladeSuchprofilErstellenForm() {
//			//final Nutzerprofil profil = nutzerprofil;
//			
//			RootPanel.get("Profil").clear();
//			RootPanel.get("NutzerForm").clear();
//			RootPanel.get("Steckbrief").clear();
//			RootPanel.get("Zusinf").clear();
//			
//			//Erzeugt und strukturiert die Widgets, welche genutzt werden um 
//			//einen neuen Nutzer anzulegen.
//			Grid suchprofilGrid = new Grid(9, 3);
//			this.add(suchprofilGrid);
//			
//			Label suchprofilLabel = new Label ("Name des Suchprofils");
//			suchprofilGrid.setWidget(0, 0, suchprofilLabel);
//			suchprofilGrid.setWidget(0, 1, suchprofilTextBox);
//
//			
//			Label geschlechtLabel = new Label("Geschlecht:");
//			geschlechtListBox.addItem("Keine Angabe");
//			geschlechtListBox.addItem("männlich");
//			geschlechtListBox.addItem("weiblich");
//			suchprofilGrid.setWidget(1, 0, geschlechtLabel);
//			suchprofilGrid.setWidget(1, 1, geschlechtListBox);
//
//
//			Label religionLabel = new Label("Religion");
//			suchprofilGrid.setWidget(4, 0, religionLabel);
//			suchprofilGrid.setWidget(4, 1, religionListBox);
//			religionListBox.addItem("Keine Angabe");
//			religionListBox.addItem("Christentum");
//			religionListBox.addItem("Islam");
//			religionListBox.addItem("Judentum");
//			religionListBox.addItem("Buddhismus");
//			religionListBox.addItem("Hinduismus");
//			religionListBox.addItem("Andere");
//
//			Label haarfarbeLabel = new Label("Haarfarbe");
//			suchprofilGrid.setWidget(5, 0, haarfarbeLabel);
//			suchprofilGrid.setWidget(5, 1, haarfarbeTextBox);
//
//			Label raucherLabel = new Label("Raucher");
//			suchprofilGrid.setWidget(6, 0, raucherLabel);
//			suchprofilGrid.setWidget(6, 1, raucherListBox);
//			raucherListBox.addItem("Keine Angabe");
//			raucherListBox.addItem("Ja");
//			raucherListBox.addItem("Nein");
//			
//			Label koerpergroesseLabel = new Label("Körpergröße: ");
//			suchprofilGrid.setWidget(7, 0, koerpergroesseLabel);
//			suchprofilGrid.setWidget(7, 1, koerpergroesseTextBox);
//
//
//			vPanel.add(suchprofilGrid);
//			RootPanel.get("Profil").add(vPanel);
//			HorizontalPanel nutzerButtonsPanel = new HorizontalPanel();
//			//this.add(nutzerButtonsPanel);
//
//			Button anlegenButton = new Button("Anlegen");
//			nutzerButtonsPanel.add(anlegenButton);
//			Button abbrechenButton = new Button("Abbrechen");
//			nutzerButtonsPanel.add(abbrechenButton);
//
//			RootPanel.get("Profil").add(nutzerButtonsPanel);
//			
//		
//			
//	//-----------------------------------------------------------------------------		
//			//Button um Eingabe abzubrechen.
//			abbrechenButton.addClickHandler(new ClickHandler(){
//
//				@Override
//				public void onClick(ClickEvent event) {
//				 
//					
//					SuchprofilErstellenForm suchprofilErstellenform = new SuchprofilErstellenForm();
//					suchprofilErstellenform.ladeSuchprofilErstellenForm();			
//				}
//				
//			});	
//	//-----------------------------------------------------------------------------
//			
//			//Der Button, mit zugehoeriger Methode, zur Erzeugung eines neuen 
//			//Nutzers
//			anlegenButton.addClickHandler(new ClickHandler() {
//				public void onClick(ClickEvent event) {
//					
//					//Diese Zeile erstellt eine Variable, die der ID des Nutzers entspricht
//					nutzerprofil.getProfilID();
//					
//					String suchprofilname = suchprofilTextBox.getText();
//
//					String geschlecht = geschlechtListBox.getSelectedItemText();
//					
//					String koerpergroessevonString = koerpergroesseTextBox.getText();
//					int koerpergroesse = Integer.parseInt(koerpergroessevonString);					
//					
//					String religion = religionListBox.getSelectedItemText();
//					
//					String haarfarbe = haarfarbeTextBox.getText();
//					
//					String raucher = raucherListBox.getSelectedItemText();
//		
//
//					
//					// Inhalte aus der Datenbank entfernen. 
//					ClientsideSettings.getPartnerboerseVerwaltung().insertSuchprofil( nutzerprofil.getProfilID(), suchprofilname,  geschlecht,  raucher,
//							haarfarbe, religion, koerpergroesse,  new  AsyncCallback<Suchprofil> () {
//						@Override
//						public void onFailure(Throwable caught) {
//							String test = "Geht ned" ;
//							Window.alert(test);
//						}
//
//						@Override
//						public void onSuccess(Suchprofil result) {
//							String test = "geht :D";
//							Suchprofilseite suchprofilseite = new Suchprofilseite();
//							
//							suchprofilseite.ladeSuchprofilseite(nutzerprofil);
//							Window.alert(test);
//						}
//						
//					});
//				}
//			});
//		}
	//-----------------------------------------------------------------------------

	//Diese Methode organisiert den asynchronen Callback und gibt uns eine 
	//Nachricht aus, ob dieser Callback funktioniert
			
//	class CreateSuchprofilCallback implements AsyncCallback<Suchprofil> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Das Anlegen eines neuen Suchprofils ist fehlgeschlagen!");
//		}
//
//		@Override
//		public void onSuccess(Suchprofil suchprofil) {
//			if (suchprofil != null) {
//				//Suchprofilseite suchprofilseite = new Suchprofilseite();
//				//suchprofilseite.ladeSuchprofilseite(suchprofil);
//
//
//				Window.alert("Das Anlegen eines neuen Suchprofils war erfolgreich!");
//			} 
//		} 
//		
//	}



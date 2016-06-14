package de.hdm.Gruppe4.Paarsheep.client.gui;



//import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.i18n.client.DateTimeFormat;
//import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.user.datepicker.client.DateBox;
//import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
//import com.google.gwt.user.datepicker.client.DatePicker;
import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
//import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public class SuchprofilErstellenForm extends VerticalPanel{


		//Hier wird die Klasse PartnerboerseAdministration/Asyn instanziiert, damit 
		//wir auf die Methode createNutzerprofil dieser Klasse zugreifen koennen. 
		PartnerboerseAdministrationAsync partnerboerseVerwaltung = 
				ClientsideSettings.getPartnerboerseVerwaltung();

		private TextBox suchprofilTextBox = new TextBox();
		private ListBox geschlechtListBox = new ListBox();
		private TextBox haarfarbeTextBox = new TextBox();
		private ListBox raucherListBox = new ListBox();
		private ListBox religionListBox = new ListBox();
		private TextBox koerpergroesseTextBox = new TextBox();

		
		Label idValueLabel = new Label();
		VerticalPanel vPanel = new VerticalPanel();

		

		//Diese Methode laedt das Formular zur Erstellung eines neuen Nutzers
		public void ladeSuchprofilErstellenForm( final Nutzerprofil nutzerprofil) {
			//final Nutzerprofil profil = nutzerprofil;
			
			RootPanel.get("Profil").clear();
			RootPanel.get("NutzerForm").clear();
			RootPanel.get("Steckbrief").clear();
			RootPanel.get("Zusinf").clear();
			
			//Erzeugt und strukturiert die Widgets, welche genutzt werden um 
			//einen neuen Nutzer anzulegen.
			Grid suchprofilGrid = new Grid(9, 3);
			this.add(suchprofilGrid);
			
			Label suchprofilLabel = new Label ("Name des Suchprofils");
			suchprofilGrid.setWidget(0, 0, suchprofilLabel);
			suchprofilGrid.setWidget(0, 1, suchprofilTextBox);

			
			Label geschlechtLabel = new Label("Geschlecht:");
			geschlechtListBox.addItem("Keine Angabe");
			geschlechtListBox.addItem("männlich");
			geschlechtListBox.addItem("weiblich");
			suchprofilGrid.setWidget(1, 0, geschlechtLabel);
			suchprofilGrid.setWidget(1, 1, geschlechtListBox);


			Label religionLabel = new Label("Religion");
			suchprofilGrid.setWidget(4, 0, religionLabel);
			suchprofilGrid.setWidget(4, 1, religionListBox);
			religionListBox.addItem("Keine Angabe");
			religionListBox.addItem("Christentum");
			religionListBox.addItem("Islam");
			religionListBox.addItem("Judentum");
			religionListBox.addItem("Buddhismus");
			religionListBox.addItem("Hinduismus");
			religionListBox.addItem("Andere");

			Label haarfarbeLabel = new Label("Haarfarbe");
			suchprofilGrid.setWidget(5, 0, haarfarbeLabel);
			suchprofilGrid.setWidget(5, 1, haarfarbeTextBox);

			Label raucherLabel = new Label("Raucher");
			suchprofilGrid.setWidget(6, 0, raucherLabel);
			suchprofilGrid.setWidget(6, 1, raucherListBox);
			raucherListBox.addItem("Keine Angabe");
			raucherListBox.addItem("Ja");
			raucherListBox.addItem("Nein");
			
			Label koerpergroesseLabel = new Label("Körpergröße: ");
			suchprofilGrid.setWidget(7, 0, koerpergroesseLabel);
			suchprofilGrid.setWidget(7, 1, koerpergroesseTextBox);


			vPanel.add(suchprofilGrid);
			RootPanel.get("Profil").add(vPanel);
			HorizontalPanel nutzerButtonsPanel = new HorizontalPanel();
			//this.add(nutzerButtonsPanel);

			Button anlegenButton = new Button("Anlegen");
			nutzerButtonsPanel.add(anlegenButton);
			Button abbrechenButton = new Button("Abbrechen");
			nutzerButtonsPanel.add(abbrechenButton);

			RootPanel.get("Profil").add(nutzerButtonsPanel);
			
		
			
	//-----------------------------------------------------------------------------		
			//Button um Eingabe abzubrechen.
			abbrechenButton.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
				 
					
					SuchprofilErstellenForm suchprofilErstellenform = new SuchprofilErstellenForm();
					suchprofilErstellenform.ladeSuchprofilErstellenForm(nutzerprofil);			
				}
				
			});	
	//-----------------------------------------------------------------------------
			
			//Der Button, mit zugehoeriger Methode, zur Erzeugung eines neuen 
			//Nutzers
			anlegenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					//Diese Zeile erstellt eine Variable, die der ID des Nutzers entspricht
					nutzerprofil.getProfilID();
					
					String suchprofilname = suchprofilTextBox.getText();

					String geschlecht = geschlechtListBox.getSelectedItemText();
					
					String koerpergroessevonString = koerpergroesseTextBox.getText();
					int koerpergroesse = Integer.parseInt(koerpergroessevonString);					
					
					String religion = religionListBox.getSelectedItemText();
					
					String haarfarbe = haarfarbeTextBox.getText();
					
					String raucher = raucherListBox.getSelectedItemText();
		

					
					// Inhalte aus der Datenbank entfernen. 
					ClientsideSettings.getPartnerboerseVerwaltung().insertSuchprofil( suchprofilname,  geschlecht,  raucher,
							haarfarbe, religion, koerpergroesse,  new  AsyncCallback<Suchprofil> () {
						@Override
						public void onFailure(Throwable caught) {
							String test = "Geht ned" ;
							Window.alert(test);
						}

						@Override
						public void onSuccess(Suchprofil result) {
							String test = "geht :D";
							Suchprofilseite suchprofilseite = new Suchprofilseite();
							
							suchprofilseite.ladeSuchprofilseite(nutzerprofil);
							Window.alert(test);
						}
						
					});
				}
			});
		}
}
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



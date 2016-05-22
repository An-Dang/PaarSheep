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
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;


public class SuchprofilForm extends VerticalPanel{


	//-----------------------------------------------------------------------------
		//Hier wird die Klasse PartnerboerseAdministration/Asyn instanziiert, damit 
		//wir auf die Methode createNutzerprofil dieser Klasse zugreifen koennen. 
		PartnerboerseAdministrationAsync partnerboerseVerwaltung = 
				ClientsideSettings.getPartnerboerseVerwaltung();
		
	//-----------------------------------------------------------------------------	

		ListBox geschlechtListBox = new ListBox();
		TextBox koerpergroessevonTextBox = new TextBox();
		TextBox koerpergroessebisTextBox = new TextBox();
		TextBox altervonTextBox = new TextBox();
		TextBox alterbisTextBox = new TextBox();
		TextBox haarfarbeTextBox = new TextBox();
		ListBox raucherListBox = new ListBox();
		ListBox religionListBox = new ListBox();
		
		Label idValueLabel = new Label();
		VerticalPanel vPanel = new VerticalPanel();

	// -------------------------------------------------------------------------

		//Diese Methode laedt das Formular zur Erstellung eines neuen Nutzers
		public void ladeNutzerForm(String email) {
			final String emailAddress = email;
			
			RootPanel.get("NutzerForm").clear();
			RootPanel.get("Profil").clear();
			RootPanel.get("Steckbrief").clear();
			RootPanel.get("Zusinf").clear();
			
			//Erzeugt und strukturiert die Widgets, welche genutzt werden um 
			//einen neuen Nutzer anzulegen.
			Grid nutzerGrid = new Grid(9, 3);
			this.add(nutzerGrid);

			Label koerpergroessevonLabel = new Label("Mindeste K�rpergr��e: ");
			nutzerGrid.setWidget(0, 0, koerpergroessevonLabel);
			nutzerGrid.setWidget(0, 1, koerpergroessevonTextBox);

			Label koerpergroessebisLabel = new Label("Maximale K�rpergr��e: ");
			nutzerGrid.setWidget(1, 0, koerpergroessebisLabel);
			nutzerGrid.setWidget(1, 1, koerpergroessebisTextBox);

			Label altervonLabel = new Label("Mindestalter:");
			nutzerGrid.setWidget(2, 0, altervonLabel);
			nutzerGrid.setWidget(2, 1, altervonTextBox);

			Label alterbisLabel = new Label("H�chstalter:");
			nutzerGrid.setWidget(3, 0, alterbisLabel);
			nutzerGrid.setWidget(3, 1, alterbisTextBox);
			
			Label geschlechtLabel = new Label("Geschlecht:");
			geschlechtListBox.addItem("Keine Angabe");
			geschlechtListBox.addItem("männlich");
			geschlechtListBox.addItem("weiblich");
			nutzerGrid.setWidget(4, 0, geschlechtLabel);
			nutzerGrid.setWidget(4, 1, geschlechtListBox);

			Label religionLabel = new Label("Religion");
			nutzerGrid.setWidget(5, 0, religionLabel);
			nutzerGrid.setWidget(5, 1, religionListBox);
			religionListBox.addItem("Keine Angabe");
			religionListBox.addItem("Christentum");
			religionListBox.addItem("Islam");
			religionListBox.addItem("Judentum");
			religionListBox.addItem("Buddhismus");
			religionListBox.addItem("Hinduismus");
			religionListBox.addItem("Andere");

			Label haarfarbeLabel = new Label("Haarfarbe");
			nutzerGrid.setWidget(6, 0, haarfarbeLabel);
			nutzerGrid.setWidget(6, 1, haarfarbeTextBox);

			Label raucherLabel = new Label("Raucher");
			nutzerGrid.setWidget(7, 0, raucherLabel);
			nutzerGrid.setWidget(7, 1, raucherListBox);
			raucherListBox.addItem("Keine Angabe");
			raucherListBox.addItem("Ja");
			raucherListBox.addItem("Nein");


			vPanel.add(nutzerGrid);
			RootPanel.get("SuchprofilForm").add(vPanel);
			HorizontalPanel nutzerButtonsPanel = new HorizontalPanel();
			this.add(nutzerButtonsPanel);

			Button anlegenButton = new Button("Anlegen");
			nutzerButtonsPanel.add(anlegenButton);
			Button abbrechenButton = new Button("Abbrechen");
			nutzerButtonsPanel.add(abbrechenButton);

			RootPanel.get("SuchprofilForm").add(nutzerButtonsPanel);
			
	//-----------------------------------------------------------------------------		
			//Button um Eingabe abzubrechen.
			abbrechenButton.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					RootPanel.get("SuchprofilForm").clear();
					NutzerForm nutzerform = new NutzerForm();
					nutzerform.ladeNutzerForm(emailAddress);
					
				}
				
			});	
			
			
	//-----------------------------------------------------------------------------
			
			//Der Button, mit zugehoeriger Methode, zur Erzeugung eines neuen 
			//Nutzers
			anlegenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					String koerpergroessevonString = koerpergroessevonTextBox.getText();
					int koerpergroessevon = Integer.parseInt(koerpergroessevonString);
					String koerpergroessebisString = koerpergroessebisTextBox.getText();
					int koerpergroessebis = Integer.parseInt(koerpergroessebisString);
					String altervonString = altervonTextBox.getText();
					int altervon = Integer.parseInt(altervonString);
					String alterbisString = alterbisTextBox.getText();
					int alterbis = Integer.parseInt(alterbisString);
					String geschlecht = geschlechtListBox.getSelectedItemText();
					String religion = religionListBox.getSelectedItemText();
					String haarfarbe = haarfarbeTextBox.getText();
					String raucher = raucherListBox.getSelectedItemText();
					

					//-------------------------------------------------------------
					// Testausgabe
					String test = ("Mindeste K�rpergr��e: " + koerpergroessevon 
							+ " Maximale K�rpergr��e: " + koerpergroessebis 
							+ " Mindestalter: " + altervon
							+ " H�chstalter: " + alterbis
							+ " Geschlecht: " + geschlecht
							+ " Religion: " + religion 
							+ " Haarfarbe: " + haarfarbe
							+ " Raucher: " + raucher);
					Window.alert(test);
					
					//-------------------------------------------------------------

					partnerboerseVerwaltung.createSuchprofil(koerpergroessevon,
							koerpergroessebis, altervon, alterbis,
							geschlecht, religion, haarfarbe, raucher, 
							new CreateSuchprofilCallback());
				}
			});

		}
	}


	//-----------------------------------------------------------------------------

	//Diese Methode organisiert den asynchronen Callback und gibt uns eine 
	//Nachricht aus, ob dieser Callback funktioniert
	class CreateSuchprofilCallback implements AsyncCallback<Suchprofil> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Anlegen eines neuen Suchprofils ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Suchprofil suchprofil) {
			if (suchprofil != null) {
				Startseite startseite = new Startseite();
				startseite.ladeStartseite();

				Window.alert("Das Anlegen eines neuen Suchprofils war erfolgreich!");
			}
		}
	}
	


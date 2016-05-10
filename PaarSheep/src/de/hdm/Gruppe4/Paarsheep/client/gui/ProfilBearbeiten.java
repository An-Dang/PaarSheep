package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Formular f�r die Darstellung der zu bearbeitenden Profilinformationen
 * 
 * @author Marcel Pleyer
 */

public class ProfilBearbeiten extends VerticalPanel{
	Label steckbriefValue = new Label();
	TextBox vornameTextBox = new TextBox();
	TextBox nachnameTextBox = new TextBox();
	TextBox koerpergroesseLbL = new TextBox();
	ListBox haarfarbeListBox = new ListBox();
	ListBox raucherListBox = new ListBox();
	ListBox religionListBox = new ListBox();
	VerticalPanel vpPanel = new VerticalPanel();
	VerticalPanel vPanellinks = new VerticalPanel();
	VerticalPanel vPanelrechts = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();

	TextBox buchTextBox = new TextBox();
	TextBox urlaubTextBox = new TextBox();
	TextBox musikTextBox = new TextBox();
	TextBox sportTextBox = new TextBox();

	public void loadProfilEditieren() {

		Grid steckbriefGrid = new Grid(7, 3);
		

		this.add(steckbriefGrid);

		Label profilLabel = new Label("Dein Profil");
		vpPanel.add(profilLabel);
		RootPanel.get("Profil").add(vpPanel);
		// Widgets f�r Steckbrief

		Label steckbriefLabel = new Label("Steckbrief");
		steckbriefGrid.setWidget(0, 0, steckbriefLabel);
		steckbriefGrid.setWidget(0, 1, steckbriefValue);

		Label vornameLabel = new Label("Vorname: ");
		steckbriefGrid.setWidget(1, 0, vornameLabel);
		steckbriefGrid.setWidget(1, 1, vornameTextBox);

		Label nachnameLabel = new Label("Nachname: ");
		steckbriefGrid.setWidget(2, 0, nachnameLabel);
		steckbriefGrid.setWidget(2, 1, nachnameTextBox);

		Label koerpergroesseLabel = new Label("Körpergröße: ");
		steckbriefGrid.setWidget(3, 0, koerpergroesseLabel);
		steckbriefGrid.setWidget(3, 1, koerpergroesseLbL);

		Label haarfarbeLabel = new Label("Haarfarbe: ");
		steckbriefGrid.setWidget(4, 0, haarfarbeLabel);
		steckbriefGrid.setWidget(4, 1, haarfarbeListBox);

		Label raucherLabel = new Label("Raucher: ");
		steckbriefGrid.setWidget(5, 0, raucherLabel);
		steckbriefGrid.setWidget(5, 1, raucherListBox);

		Label religionLabel = new Label("Religion: ");
		steckbriefGrid.setWidget(6, 0, religionLabel);
		steckbriefGrid.setWidget(6, 1, religionListBox);
		
		
		Grid zusinfGrid = new Grid (5, 3);
		this.add(zusinfGrid);
		
		// Widgets f�r zus�tzliche Informationen
		Label zusinfLabel = new Label("Zusätzliche Informationen ");
		zusinfGrid.setWidget(0, 0, zusinfLabel);


		Label buchLabel = new Label("Mein Lieblingsbuch ist: ");
		zusinfGrid.setWidget(1, 0, buchLabel);
		zusinfGrid.setWidget(1, 1, buchTextBox);


		Label urlaubLabel = new Label("Mein Lieblingsurlaubsort ist: ");
		zusinfGrid.setWidget(2, 0, urlaubLabel);
		zusinfGrid.setWidget(2, 1, urlaubTextBox);

		Label sportLabel = new Label("Sport: ");
		zusinfGrid.setWidget(3, 0, sportLabel);
		zusinfGrid.setWidget(3, 1, sportTextBox);

		Label musikLabel = new Label("Musik: ");
		zusinfGrid.setWidget(4, 0, musikLabel);
		zusinfGrid.setWidget(4, 1, musikTextBox);
		

		vPanellinks.add(steckbriefGrid);
		vPanelrechts.add(zusinfGrid);
		hPanel.add(vPanelrechts);
		RootPanel.get().add(vPanellinks);
		RootPanel.get().add(hPanel);

		Button speichernBtn = new Button("speichern", new ProfilinfoSpeichernClickHandler());
		vPanelrechts.add(speichernBtn);
		hPanel.add(vPanelrechts);

		Button abbrechenBtn = new Button("abbrechen", new ProfilinfoAbbrechenClickHandler());
		vPanelrechts.add(abbrechenBtn);
		hPanel.add(vPanelrechts);

		// �bergabe der Widgets/ Panels and das RootPanel
		RootPanel.get("Steckbrief").add(vPanellinks);
		RootPanel.get("Zusinf").add(vPanelrechts);

		RootPanel.get("Zusinf").add(hPanel);
	}

	private class ProfilinfoSpeichernClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub

		}

	}

	private class ProfilinfoAbbrechenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			final ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();

			profilBearbeiten.loescheEditInhalt();
			profilBearbeiten.loadProfilEditieren();

		}

	}

	public void loescheEditInhalt() {
		RootPanel.get("Steckbrief").clear();
		RootPanel.get("Zusinf").clear();
		RootPanel.get("Profil").clear();
	}

}

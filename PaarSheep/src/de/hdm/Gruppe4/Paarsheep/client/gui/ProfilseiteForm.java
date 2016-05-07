package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Formular für die Darstellung der angegebenen Profilinformationen
 * 
 * @author Marcel Pleyer
 */

public class ProfilseiteForm extends VerticalPanel {

	Label steckbriefValue = new Label();
	Label vornameLbl = new Label("Max");
	Label nachnameLbl = new Label("Mustermann");
	Label koerpergroesseErgebnisLbL = new Label("175");
	Label haarfarbeErgebnisLbl = new Label("blond");
	Label raucherErgebnisLbl = new Label("nein");
	Label religionErgebnisLbl = new Label("evangelisch");
	VerticalPanel vpPanel = new VerticalPanel();
	VerticalPanel vPanellinks = new VerticalPanel();
	VerticalPanel vPanelrechts = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();

	Label buchErgebnisLbl = new Label("Harry Potter");
	Label urlaubErgebnisLbl = new Label("Ibiza");
	Label musikErgebnisLbl = new Label("Metal");
	Label sportErgebnisLbl = new Label("Fußball");

	public void loadProfilInformationen() {

		Grid steckbriefGrid = new Grid(7, 3);
		

		this.add(steckbriefGrid);

		Label profilLabel = new Label("Dein Profil");
		vpPanel.add(profilLabel);
		RootPanel.get("Profil").add(vpPanel);
		// Widgets für Steckbrief

		Label steckbriefLabel = new Label("Steckbrief");
		steckbriefGrid.setWidget(0, 0, steckbriefLabel);
		steckbriefGrid.setWidget(0, 1, steckbriefValue);

		Label vornameLabel = new Label("Vorname: ");
		steckbriefGrid.setWidget(1, 0, vornameLabel);
		steckbriefGrid.setWidget(1, 1, vornameLbl);

		Label nachnameLabel = new Label("Nachname: ");
		steckbriefGrid.setWidget(2, 0, nachnameLabel);
		steckbriefGrid.setWidget(2, 1, nachnameLbl);

		Label koerpergroesseLabel = new Label("Körpergröße: ");
		steckbriefGrid.setWidget(3, 0, koerpergroesseLabel);
		steckbriefGrid.setWidget(3, 1, koerpergroesseErgebnisLbL);

		Label haarfarbeLabel = new Label("Haarfarbe: ");
		steckbriefGrid.setWidget(4, 0, haarfarbeLabel);
		steckbriefGrid.setWidget(4, 1, haarfarbeErgebnisLbl);

		Label raucherLabel = new Label("Raucher: ");
		steckbriefGrid.setWidget(5, 0, raucherLabel);
		steckbriefGrid.setWidget(5, 1, raucherErgebnisLbl);

		Label religionLabel = new Label("Religion: ");
		steckbriefGrid.setWidget(6, 0, religionLabel);
		steckbriefGrid.setWidget(6, 1, religionErgebnisLbl);
		
		
		Grid zusinfGrid = new Grid (5, 3);
		this.add(zusinfGrid);
		
		// Widgets für zusätzliche Informationen
		Label zusinfLabel = new Label("Zusätzliche Informationen ");
		zusinfGrid.setWidget(0, 0, zusinfLabel);


		Label buchLabel = new Label("Mein Lieblingsbuch ist: ");
		zusinfGrid.setWidget(1, 0, buchLabel);
		zusinfGrid.setWidget(1, 1, buchErgebnisLbl);


		Label urlaubLabel = new Label("Mein Lieblingsurlaubsort ist: ");
		zusinfGrid.setWidget(2, 0, urlaubLabel);
		zusinfGrid.setWidget(2, 1, urlaubErgebnisLbl);

		Label sportLabel = new Label("Sport: ");
		zusinfGrid.setWidget(3, 0, sportLabel);
		zusinfGrid.setWidget(3, 1, sportErgebnisLbl);

		Label musikLabel = new Label("Musik: ");
		zusinfGrid.setWidget(4, 0, musikLabel);
		zusinfGrid.setWidget(4, 1, musikErgebnisLbl);
		

		vPanellinks.add(steckbriefGrid);
		vPanelrechts.add(zusinfGrid);
		hPanel.add(vPanelrechts);
		RootPanel.get().add(vPanellinks);
		RootPanel.get().add(hPanel);
		// Profilinfo ändern Button ClickHandler

		/**
		 * Button zum Bearbeiten der Profilinformationen
		 */
		Button aendernBtn = new Button("bearbeiten", new ProfilinfoClickHandler());
		vPanelrechts.add(aendernBtn);
		hPanel.add(vPanelrechts);

		/**
		 * Übergabe der Widgets/ Panels and das RootPanel
		 */
		// Notiz: RootPanel.get.clear(); funktioniert nicht, wenn man sich etwas
		// dem div Container hinzugefügt hat.
		RootPanel.get("Steckbrief").add(vPanellinks);
		RootPanel.get("Zusinf").add(vPanelrechts);
		RootPanel.get("Profil").add(vpPanel);

		// Profilinfo ändern Button ClickHandler

	}

	private class ProfilinfoClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			final ProfilBearbeiten profilBearbeiten = new ProfilBearbeiten();
			final ProfilseiteForm profilseiteForm = new ProfilseiteForm();
			profilseiteForm.loescheInhalt();
			// pb.loadProfilEditieren();
			profilBearbeiten.loadProfilEditieren();

		}

	}

	/*
	 * Hiermit wird der Inhalt der Profil Informationen gelöscht, somit kann das
	 * Profil bearbeitet werden, ohne dass die vorherigen Informationen
	 * angezeigt werden.
	 */
	public void loescheInhalt() {
		RootPanel.get("Steckbrief").clear();
		RootPanel.get("Zusinf").clear();
		RootPanel.get("Profil").clear();
	}

}

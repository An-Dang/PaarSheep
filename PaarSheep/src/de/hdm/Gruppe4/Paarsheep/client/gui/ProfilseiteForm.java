package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
/**
 * Formular f�r die Darstellung der angegebenen Profilinformationen
 * 
 * @author Marcel Pleyer
 */

public class ProfilseiteForm extends VerticalPanel {

	Label steckbriefValue = new Label();
	Label vornameLbl = new Label();
	Label nachnameLbl = new Label();
	Label koerpergroesseErgebnisLbL = new Label();
	Label haarfarbeErgebnisLbl = new Label();
	Label raucherErgebnisLbl = new Label();
	Label religionErgebnisLbl = new Label();
	VerticalPanel vpPanel = new VerticalPanel();
	VerticalPanel vPanellinks = new VerticalPanel();
	VerticalPanel vPanelrechts = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();

	Label buchErgebnisLbl = new Label();
	Label urlaubErgebnisLbl = new Label();
	Label musikErgebnisLbl = new Label();
	Label sportErgebnisLbl = new Label();

	public void loadProfilInformationen(Nutzerprofil nutzerprofil) {

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
		steckbriefGrid.setText(1, 1, nutzerprofil.getVorname());

		Label nachnameLabel = new Label("Nachname: ");
		steckbriefGrid.setWidget(2, 0, nachnameLabel);
		steckbriefGrid.setText(2, 1, nutzerprofil.getNachname());

		Label koerpergroesseLabel = new Label("Körpergröße: ");
		steckbriefGrid.setWidget(3, 0, koerpergroesseLabel);
		steckbriefGrid.setText(3, 1, String.valueOf(nutzerprofil.getKoerpergroesse()));

		Label haarfarbeLabel = new Label("Haarfarbe: ");
		steckbriefGrid.setWidget(4, 0, haarfarbeLabel);
		steckbriefGrid.setText(4, 1, nutzerprofil.getHaarfarbe());

		Label raucherLabel = new Label("Raucher: ");
		steckbriefGrid.setWidget(5, 0, raucherLabel);
		steckbriefGrid.setText(5, 1, nutzerprofil.getRaucher());

		Label religionLabel = new Label("Religion: ");
		steckbriefGrid.setWidget(6, 0, religionLabel);
		steckbriefGrid.setText(6, 1, nutzerprofil.getReligion());
		
		
		Grid zusinfGrid = new Grid (5, 3);
		this.add(zusinfGrid);
		
		// Widgets f�r zus�tzliche Informationen
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
		// Profilinfo �ndern Button ClickHandler

		/**
		 * Button zum Bearbeiten der Profilinformationen
		 */
		Button aendernBtn = new Button("bearbeiten", new ProfilinfoClickHandler());
		vPanelrechts.add(aendernBtn);
		hPanel.add(vPanelrechts);

		/**
		 * �bergabe der Widgets/ Panels and das RootPanel
		 */
		// Notiz: RootPanel.get.clear(); funktioniert nicht, wenn man sich etwas
		// dem div Container hinzugef�gt hat.
		RootPanel.get("Steckbrief").add(vPanellinks);
		RootPanel.get("Zusinf").add(vPanelrechts);
		RootPanel.get("Profil").add(vpPanel);

		// Profilinfo �ndern Button ClickHandler

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
	 * Hiermit wird der Inhalt der Profil Informationen gel�scht, somit kann das
	 * Profil bearbeitet werden, ohne dass die vorherigen Informationen
	 * angezeigt werden.
	 */
	public void loescheInhalt() {
		RootPanel.get("Steckbrief").clear();
		RootPanel.get("Zusinf").clear();
		RootPanel.get("Profil").clear();
	}

}

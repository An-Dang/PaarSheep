package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
//import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

/**
 * Klasse zur Darstellung des Suchprofils
 * @author Dominik Sasse
 *
 */
public class SuchprofilAnzeigenForm extends VerticalPanel{
	
	Label suchprofilAnzeige = new Label();
	Label geschlechtLabel = new Label();
	Label altervonLabel = new Label();
	Label alterbisLabel = new Label();
	Label religionLabel = new Label();
	Label haarfarbeLabel = new Label();
	Label raucherLabel = new Label();
	Label koepergroessevonLabel = new Label();
	Label koerpergroessebisLabel = new Label();

	
	VerticalPanel vertPanel = new VerticalPanel();
	VerticalPanel vertPanellinks = new VerticalPanel();
	
	/**
	 * Die folgenden 2 Panels werden nur benötigt, wenn auf der rechten
	 * Seite der Anzeige etwas angezeigt, oder bearbeitet werden soll.
	 * @author Dominik Sasse
	 */
	
	//VerticalPanel vertPanelrechts = new VerticalPanel();
	//HorizontalPanel horPanel = new HorizontalPanel();
	
	public void loadSuchprofilInformationen(Suchprofil profil){
		
		Grid anzeigeGrid = new Grid (8, 3);
		
		this.add(anzeigeGrid);
		
		Label suchprofilLabel = new Label("Dein Suchprofil");
		vertPanel.add(suchprofilLabel);
		RootPanel.get("Suchprofil").add(vertPanel);
		
		Label suchprofilAnzeigeLabel = new Label("Suchprofil");
		anzeigeGrid.setWidget(0, 0, suchprofilAnzeigeLabel);
		anzeigeGrid.setWidget(0, 1, suchprofilAnzeige);
		
		Label geschlecht = new Label("Geschlecht");
		anzeigeGrid.setWidget(1, 0, geschlecht);
		anzeigeGrid.setWidget(1, 1, geschlechtLabel);
		
		Label altervon = new Label ("Mindestalter");
		anzeigeGrid.setWidget(2, 0, altervon);
		anzeigeGrid.setWidget(2, 1, altervonLabel);
		
		Label alterbis = new Label ("Höchstalter");
		anzeigeGrid.setWidget(3, 0, alterbis);
		anzeigeGrid.setWidget(3, 1, alterbisLabel);
		
		Label religion = new Label ("Religion");
		anzeigeGrid.setWidget(4, 0, religion);
		anzeigeGrid.setWidget(4, 1, religionLabel);
		
		Label haarfarbe = new Label ("Haarfarbe");
		anzeigeGrid.setWidget(5, 0, haarfarbe);
		anzeigeGrid.setWidget(5, 1, haarfarbeLabel);
		
		Label raucher = new Label ("Raucher");
		anzeigeGrid.setWidget(6, 0, raucher);
		anzeigeGrid.setWidget(6, 1, raucherLabel);
		
		Label koerpergroessevon =  new Label("Mindeste Körpergröße");
		anzeigeGrid.setWidget(7, 0, koerpergroessevon);
		anzeigeGrid.setWidget(7, 1, koepergroessevonLabel);
		
		Label koerpergroessebis = new Label ("Maximale Körpergröße");
		anzeigeGrid.setWidget(8, 0, koerpergroessebis);
		anzeigeGrid.setWidget(8, 1, koerpergroessebisLabel);

		
		vertPanellinks.add(anzeigeGrid);
		RootPanel.get().add(vertPanellinks);
		

		/**
		 * Button zum Bearbeiten der Suchprofilinformationen
		 */
		Button bearbeiteSuchprofil = new Button("bearbeiten");
		vertPanellinks.add(bearbeiteSuchprofil);

		/**
		 * ï¿½bergabe der Widgets/ Panels and das RootPanel
		 */
		// Notiz: RootPanel.get.clear(); funktioniert nicht, wenn man sich etwas
		// dem div Container hinzugefï¿½gt hat.
		
		
		RootPanel.get("Steckbrief").add(vertPanellinks);
		//RootPanel.get("Zusinf").add(vertPanelrechts);
		RootPanel.get("Profil").add(vertPanel);

	}

	/*
	 * Hiermit wird der Inhalt der Suchprofil Informationen gelöscht, somit kann das
	 * Suchprofil bearbeitet werden, ohne dass die vorherigen Informationen
	 * angezeigt werden.
	 */
	public void loescheInhalt() {
		RootPanel.get("Steckbrief").clear();
		//RootPanel.get("Zusinf").clear();
		RootPanel.get("Profil").clear();
	}

}

package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProfilseiteForm {

	public void loadProfilInformationen() {

		VerticalPanel vPanellinks = new VerticalPanel();
		VerticalPanel vPanelrechts = new VerticalPanel();
		HorizontalPanel hPanel = new HorizontalPanel();

		Button aendernBtn = new Button("bearbeiten");

		Label profilLabel = new Label("Dein Profil");

		Label steckbriefLabel = new Label("Steckbrief");

		vPanellinks.add(steckbriefLabel);

		Label vornameLabel = new Label("Vorname: ");
		TextBox vornameTb = new TextBox();
		vPanellinks.add(vornameLabel);
		vPanellinks.add(vornameTb);

		Label nachnameLabel = new Label("Nachname: ");
		TextBox nachnameTb = new TextBox();
		vPanellinks.add(nachnameLabel);
		vPanellinks.add(nachnameTb);

		Label koerpergroesseLabel = new Label("Körpergröße: ");
		TextBox koerpergroesseTb = new TextBox();
		vPanellinks.add(koerpergroesseLabel);
		vPanellinks.add(koerpergroesseTb);

		Label haarfarbeLabel = new Label("Haarfarbe: ");
		TextBox haarfarbeTb = new TextBox();
		vPanellinks.add(haarfarbeLabel);
		vPanellinks.add(haarfarbeTb);

		Label raucherLabel = new Label("Raucher: ");
		TextBox raucherTb = new TextBox();
		vPanellinks.add(raucherLabel);
		vPanellinks.add(raucherTb);

		Label religionLabel = new Label("Religion: ");
		TextBox religionTb = new TextBox();
		vPanellinks.add(religionLabel);
		vPanellinks.add(religionTb);

		// Zusätzliche Informationen
		Label zusinfLabel = new Label("Zusätzliche Informationen ");

		vPanelrechts.add(zusinfLabel);

		Label buchLabel = new Label("Mein Lieblingsbuch ist: ");
		TextBox buchTb = new TextBox();
		vPanelrechts.add(buchLabel);
		vPanelrechts.add(buchTb);

		Label urlaubLabel = new Label("Mein Lieblingsurlaubsort ist: ");
		TextBox urlaubTb = new TextBox();
		vPanelrechts.add(urlaubLabel);
		vPanelrechts.add(urlaubTb);

		Label sportLabel = new Label("Sport: ");
		TextBox sportTb = new TextBox();
		vPanelrechts.add(sportLabel);
		vPanelrechts.add(sportTb);

		Label musikLabel = new Label("Musik: ");
		TextBox musikTb = new TextBox();
		vPanelrechts.add(musikLabel);
		vPanelrechts.add(musikTb);
//Widgets auf RootPanel sichtbar.
		hPanel.add(vPanelrechts);
		RootPanel.get("Steckbrief").add(vPanellinks);

		RootPanel.get("Zusinf").add(hPanel);
		RootPanel.get().add(aendernBtn);
	}

}

package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public class SuchprofilAnzeigenWidget extends Composite{
	
	
	public SuchprofilAnzeigenWidget(Suchprofil profil) {
	final Suchprofil suchprofil = profil;	
	
	VerticalPanel suchprofilPanel = new VerticalPanel();
	FlexTable suchprofilAnzeigen = new FlexTable();

	Button suchprofilBearbeiten = new Button("Bearbeiten");
	Button suchprofilLoeschen = new Button("Löschen");
	
	Label SuchprofilNameErlaeuterung = new Label("Name des Suchprofils: ");
	Label koerpergroesseErlaeuterung = new Label("Körpergröße: ");
	Label haarfarbeErlaeuterung = new Label("Haarfarbe: ");
	Label geschlechtErlaeuterung = new Label("Geschlecht: ");
	Label religionErlaeuterung = new Label("Religion: ");
	Label raucherErlaeuterung = new Label("Raucher: ");
	
	Label SuchprofilNameInhalt = new Label(suchprofil.getSuchprofilName());
	Label haarfarbeInhalt = new Label(suchprofil.getHaarfarbe());
	Label geschlechtInhalt = new Label(suchprofil.getGeschlecht());
	Label religionInhalt = new Label(suchprofil.getRaucher());
	Label raucherInhalt = new Label(suchprofil.getRaucher());
	

	
	suchprofilAnzeigen.setWidget(0, 0, SuchprofilNameErlaeuterung);
	suchprofilAnzeigen.setWidget(0, 1, SuchprofilNameInhalt);
	
	suchprofilAnzeigen.setWidget(1, 0, geschlechtErlaeuterung);
	suchprofilAnzeigen.setWidget(1, 1, geschlechtInhalt);
	
	suchprofilAnzeigen.setWidget(2, 0, koerpergroesseErlaeuterung);
	suchprofilAnzeigen.setWidget(2, 1, koerpergroesseErlaeuterung);
	
	suchprofilAnzeigen.setWidget(6, 0, haarfarbeErlaeuterung);
	suchprofilAnzeigen.setWidget(6, 1, haarfarbeInhalt);
	
	suchprofilAnzeigen.setWidget(7, 0, religionErlaeuterung);
	suchprofilAnzeigen.setWidget(7, 1, religionInhalt);
	
	suchprofilAnzeigen.setWidget(8, 0, raucherErlaeuterung);
	suchprofilAnzeigen.setWidget(8, 1, raucherInhalt);
	
	suchprofilAnzeigen.setWidget(9, 0, suchprofilBearbeiten);
	suchprofilAnzeigen.setWidget(9, 1, suchprofilLoeschen);
	
	
	suchprofilPanel.add(suchprofilAnzeigen);
	
	
	initWidget(suchprofilPanel);
	
	}
}
	
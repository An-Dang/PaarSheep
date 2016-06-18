package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.Gruppe4.Paarsheep.client.ClientsideSettings;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public class SuchprofilAnzeigen extends VerticalPanel {

	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	private Label ueberschriftLabel = new Label("Hier kannst du deine Suchprofile anzeigen lassen ");
	
	/**
	 * VerticalPanels und HorizontalPanels hinzufuegen.
	 */
	private VerticalPanel suchprofilPanel = new VerticalPanel();
	private VerticalPanel infoPanel = new VerticalPanel();
	private VerticalPanel auswahlPanel = new VerticalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel =  new HorizontalPanel();
	private HorizontalPanel loeschenPanel = new HorizontalPanel();
	private HorizontalPanel bearbeitenPanel = new HorizontalPanel();
	private HorizontalPanel speichernPanel = new HorizontalPanel();

	/**
	 * Widgets hinzufuegen.
	 */
	private Label auswahlLabel = new Label("Wähl dein Suchprofil aus.");
//	private Label infoLabel = new Label();
	private ListBox auswahlListBox = new ListBox();
	private FlexTable SuchprofilAnzeigenFlexTable = new FlexTable();

	private Button anzeigenButton = new Button("Anzeigen", new AnzeigenHandler());
	private Button loeschenButton = new Button("Löschen", new DeleteSuchprofilClickHandler());
	private Button bearbeitenButton = new Button("Bearbeiten");

	public SuchprofilAnzeigen() {
		
		this.add(mainPanel);

		mainPanel.add(suchprofilPanel);
		mainPanel.add(infoPanel);
		
		suchprofilPanel.add(auswahlLabel);
		auswahlPanel.add(auswahlListBox);
		auswahlPanel.add(anzeigenButton);
		auswahlPanel.add(loeschenButton);
		suchprofilPanel.add(auswahlPanel);
		
		
		SuchprofilAnzeigenFlexTable.setText(0, 0, "Suchprofilname:");
		SuchprofilAnzeigenFlexTable.setText(1, 0, "Religion:");
		SuchprofilAnzeigenFlexTable.setText(2, 0, "Körpergröße:");
		SuchprofilAnzeigenFlexTable.setText(3, 0, "Haarfarbe:");
		SuchprofilAnzeigenFlexTable.setText(4, 0, "Raucher:");
		SuchprofilAnzeigenFlexTable.setText(5, 0, "Geschlecht:");
		
		/**
		 * Suchprofile anhand der NutzerprofilID auslesen.
		 */
		ClientsideSettings.getPartnerboerseAdministration().findSuchprofilByNutzerID(nutzerprofil.getProfilID(),
																	new AsyncCallback<ArrayList<Suchprofil>>(){
			public void onFailure(Throwable caught) {
			
			}
			public void onSuccess(ArrayList<Suchprofil> result) {
				if (result.isEmpty()) {
					auswahlListBox.setVisible(false);
					anzeigenButton.setVisible(false);
					suchprofilPanel.setVisible(false);
					Window.alert("Sie haben bisher noch kein Suchprofil angelegt");
				} else {
						for (Suchprofil suchprofil : result) {
							auswahlListBox.addItem(suchprofil.getSuchprofilName());
						}
				}
			}	
		});
	}
		
		
					
	/**
	 * ClickHandler fuer den Anzeigen-Button hinzufuegen.
	 */
	private class AnzeigenHandler implements ClickHandler{
		public void onClick(ClickEvent e){
					// Tabelle mit Suchprofildaten befuellen.
					try {
						ClientsideSettings.getPartnerboerseAdministration()
						.findSuchprofiByName(nutzerprofil.getProfilID(), auswahlListBox.getSelectedItemText(), new AsyncCallback<Suchprofil>() {
							public void onFailure(Throwable caught) {
				
							}
							public void onSuccess(Suchprofil result) {
								SuchprofilAnzeigenFlexTable.setText(0, 1, result.getSuchprofilName());
								SuchprofilAnzeigenFlexTable.setText(1, 1, result.getReligion());
								SuchprofilAnzeigenFlexTable.setText(2, 1, Integer.toString(result.getKoerpergroesse()));
								SuchprofilAnzeigenFlexTable.setText(3, 1, result.getHaarfarbe());
								SuchprofilAnzeigenFlexTable.setText(4, 1, result.getRaucher());
								SuchprofilAnzeigenFlexTable.setText(5, 1, result.getGeschlecht());
								
								suchprofilPanel.add(SuchprofilAnzeigenFlexTable);
							}
						});
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		}
	
	// ClickHandler um das Suchprofil auch aus der Datenbank zu lÃ¶schen
				private class DeleteSuchprofilClickHandler implements ClickHandler{
					public void onClick(ClickEvent event) {
						try {
							ClientsideSettings.getPartnerboerseAdministration().deleteSuchprofil(nutzerprofil.getProfilID(),auswahlListBox.getItemText(auswahlListBox.getSelectedIndex()),
									new SuchprofilLoeschenCallback());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Window.alert("Das Suchprofil wurde erfolgreich gelöscht");
					}
					
						// Callback zum lÃ¶schen des Suchprofils
						private class SuchprofilLoeschenCallback implements AsyncCallback{
							public void onFailure(Throwable caught) {
								suchprofilPanel.add(new Label (caught.toString()));
							}
							public void onSuccess(Object result) {
								
								suchprofilPanel.clear();
								SuchprofilAnzeigen suchprofilAnzeigen = new SuchprofilAnzeigen();
								RootPanel.get("Profil")
								.add(suchprofilAnzeigen);
								
							}
						}
					}
				}



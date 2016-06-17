package de.hdm.Gruppe4.Paarsheep.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

	private HorizontalPanel buttonPanel = new HorizontalPanel();

	/**
	 * Widgets hinzufuegen.
	 */
	private Label auswahlLabel = new Label("Wähl dein Suchprofil aus.");
	private Label infoLabel = new Label();
	private ListBox auswahlListBox = new ListBox();
	private FlexTable SuchprofilAnzeigenFlexTable = new FlexTable();

	private Button anzeigenButton = new Button("Anzeigen", new AnzeigenHandler());

	public SuchprofilAnzeigen() {
		
		this.add(mainPanel);
		mainPanel.add(suchprofilPanel);
		mainPanel.add(infoPanel);
		mainPanel.add(ueberschriftLabel);
		suchprofilPanel.add(auswahlLabel);
		auswahlPanel.add(auswahlListBox);
		auswahlPanel.add(anzeigenButton);
		suchprofilPanel.add(auswahlPanel);
		suchprofilPanel.add(SuchprofilAnzeigenFlexTable);
		
		SuchprofilAnzeigenFlexTable.setText(0, 0, "Suchprofilname");
		SuchprofilAnzeigenFlexTable.setText(1, 0, "Religion");
		SuchprofilAnzeigenFlexTable.setText(2, 0, "Körpergröße");
		SuchprofilAnzeigenFlexTable.setText(3, 0, "Haarfarbe");
		SuchprofilAnzeigenFlexTable.setText(4, 0, "Raucher");
		SuchprofilAnzeigenFlexTable.setText(5, 0, "Geschlecht");
		
		/**
		 * Suchprofile anhand der NutzerprofilID auslesen.
		 */
		ClientsideSettings.getPartnerboerseAdministration().findSuchprofilByNutzerID(nutzerprofil.getProfilID(),
																	new AsyncCallback<ArrayList<Suchprofil>>(){
			public void onFailure(Throwable caught) {
				infoLabel.setText("Es trat ein Fehler auf.");
			}
			public void onSuccess(ArrayList<Suchprofil> result) {
				if (result.isEmpty()) {
					auswahlListBox.setVisible(false);
					anzeigenButton.setVisible(false);
					auswahlLabel.setText("Sie haben bisher keine Suchprofile angelegt.");
				} else {
						for (Suchprofil suchprofil : result) {
							auswahlListBox.addItem(suchprofil.getSuchprofilName());
						}
				}
			}	
		});
	}
//		class SuchprofilAnzeigenClickHandler implements ClickHandler {
//			public void onClick(ClickEvent event) {
//				try {
//					ClientsideSettings.getPartnerboerseAdministration().findSuchprofiByName(
//							auswahlListBox.getItemText(auswahlListBox.getSelectedIndex()), 
//							new SuchprofilAnzeigenCallback());
//
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}	
		
	
		
//		
//		/**
//		 * Anzeigen des ausgewählen Suchprofils
//		 */
//		anzeigenButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				
//				
		
					
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
								infoLabel.setText("Es trat ein Fehler auf.");
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
}	
//			
//		
//			

//				// Tabelle mit Suchprofildaten befuellen.
//				ClientsideSettings.getPartnerboerseAdministration()
//						.findSuchprofilBySuchprofilID(Integer.parseInt(auswahlListBox.getSelectedItemText()), new AsyncCallback<Suchprofil>() {
//
//							public void onFailure(Throwable caught) {
//								infoLabel.setText("Es trat ein Fehler auf.");
//							}
//
//							public void onSuccess(Suchprofil result) {
//								
//							
//								int suchprofilId = result.getProfilID();
//								
//								SuchprofilAnzeigenFlexTable.setText(0, 0, String.valueOf(suchprofilId));
//								
//								SuchprofilAnzeigenFlexTable.setText(1, 0, result.getSuchprofilName());
//								SuchprofilAnzeigenFlexTable.setText(2, 0, result.getReligion());
//								SuchprofilAnzeigenFlexTable.setText(3, 0, Integer.toString(result.getKoerpergroesse()));
//								SuchprofilAnzeigenFlexTable.setText(4, 0, result.getHaarfarbe());
//								SuchprofilAnzeigenFlexTable.setText(5, 0, result.getRaucher());
//								SuchprofilAnzeigenFlexTable.setText(5, 0, result.getGeschlecht());
//								
//							}
//
//						});
//				suchprofilPanel.add(SuchprofilAnzeigenFlexTable);
//				
//			}
//
//		});
		

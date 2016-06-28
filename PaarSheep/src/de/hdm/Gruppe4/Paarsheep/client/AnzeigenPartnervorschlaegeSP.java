package de.hdm.Gruppe4.Paarsheep.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.Gruppe4.Paarsheep.client.gui.FremdesProfil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Benutzer;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Profil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public class AnzeigenPartnervorschlaegeSP extends VerticalPanel {

	Nutzerprofil nutzerprofil = new Nutzerprofil();
	
	/**
	 * VerticalPanel hinzufügen.
	 */
	private VerticalPanel verPanel = new VerticalPanel();
	
	private HorizontalPanel auswahlPanel = new HorizontalPanel(); 
	
	private ArrayList<Suchprofil>suchprofile;
	
		/**
		 * Variablen
		 */
		int ergebnis = 0; 
	

	/**
	 * Konstruktor hinzufügen.
	 * @param a 
	 */
	public void AnzeigenPartnervorschlaegeSP() {
		
		this.add(verPanel);

		/**
		 * Label, AuswahlBox und Buttons erstellen
		 */
		final Label ueberschriftLabel = new Label(
				"Diese Nutzerprofile koennten zu ihnen passen");
		final Label ueberschriftLabel2 = new Label("W�hlen Sie ein Suchprofil aus");
		ueberschriftLabel.addStyleDependentName("partnerboerse-label"); 
		verPanel.add(ueberschriftLabel2);
		final Label infoLabel = new Label();
		final Label ergebnisLabel = new Label();
		final ListBox auswahlListBox = new ListBox(); 
		final Button anzeigenSpButton = new Button("Partnervorschlaege anzeigen");
		final Button anzeigeButton = new Button();
		
		/**
		 * Tabelle zur Anzeige der Partnervorschlaege hinzufuegen. 
		 */
		final FlexTable partnervorschlaegeSpFlexTable = new FlexTable(); 
		
		/** 
		 * Tabelle formatieren und CSS einbinden. 
		 */
		partnervorschlaegeSpFlexTable.setCellPadding(6);
		partnervorschlaegeSpFlexTable.getRowFormatter().addStyleName(0, "TableHeader");
		partnervorschlaegeSpFlexTable.addStyleName("FlexTable"); 
		
		/**
		 * Erste Zeile der Tabelle festlegen. 
		 */
		partnervorschlaegeSpFlexTable.setText(0, 0, "F-ID");
		partnervorschlaegeSpFlexTable.setText(0, 1, "Uebereinstimmung in %");
		partnervorschlaegeSpFlexTable.setText(0, 2, "Vorname");
		partnervorschlaegeSpFlexTable.setText(0, 3, "Nachname");
		partnervorschlaegeSpFlexTable.setText(0, 4, "Geburtsdatum");
		partnervorschlaegeSpFlexTable.setText(0, 5, "Geschlecht");
		partnervorschlaegeSpFlexTable.setText(0, 6, "Anzeigen");
		
		/**
		 * die AuswahlBox wird mit allen Suchprofilen des Nutzers gef�llt
		 */

			ClientsideSettings.getPartnerboerseAdministration().findSuchprofilByNutzerID(nutzerprofil.getProfilID(),
					new AsyncCallback<ArrayList<Suchprofil>>(){
				public void onFailure(Throwable caught) {
					
				}
				public void onSuccess(ArrayList<Suchprofil> result) {
					if (result.isEmpty()) {
						auswahlListBox.setVisible(false);
						anzeigeButton.setVisible(false);
//						suchprofilPanel.setVisible(false);
						Window.alert("Sie haben bisher noch kein Suchprofil angelegt");
					} else {
						suchprofile = result;
						for (Suchprofil suchprofil : result) {
							auswahlListBox.addItem(suchprofil.getSuchprofilName());
						}
					}
				}	
			});
		
		/**
		 * Bei Bet�tigung des AnzeigenButtons werden alle Partnervorschlaege anhand des 
		 * gew�hlen Suchprofils ausgegeben, nach Aehnlichkeit geordnet
		 */
		
		anzeigenSpButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				
				ClientsideSettings.getPartnerboerseAdministration().getGeordnetePartnervorschlaegeSp( 

						auswahlListBox.getSelectedItemText(), new  AsyncCallback<List<Nutzerprofil>>(){

			@Override
			public void onFailure(Throwable caught) {
				infoLabel.setText("Es trat ein Fehler auf");
				
			}

			@Override
			public void onSuccess(List<Nutzerprofil> result) {
				infoLabel.setText("Es trat kein Fehler auf");
				int row = partnervorschlaegeSpFlexTable.getRowCount();
				
				for (Nutzerprofil np : result){
					
					final int fremdprofilId = np.getProfilID();
					row++;
					partnervorschlaegeSpFlexTable.setText(row, 0, String.valueOf(np.getProfilID())); 
					partnervorschlaegeSpFlexTable.setText(row, 1, String.valueOf(np.getAehnlichkeitSP()) + "%");
					partnervorschlaegeSpFlexTable.setText(row, 2, np.getVorname()); 
					partnervorschlaegeSpFlexTable.setText(row, 3, np.getNachname());
					partnervorschlaegeSpFlexTable.setText(row, 4, String.valueOf(np.getGeburtsdatum()));
					partnervorschlaegeSpFlexTable.setText(row, 5, np.getGeschlecht()); 
					
				}
			}
		});
				/**
				 * Alle Elemente dem verPanel hinzuf�gen
				 */
				verPanel.add(ergebnisLabel);
				verPanel.add(infoLabel);
				verPanel.add(ueberschriftLabel);
				verPanel.add(partnervorschlaegeSpFlexTable);	
			}
		});
		
		/**
		 * Alle Elemente dem vertical und horizontal Panel hinzuf�gen
		 */
							
		verPanel.add(ueberschriftLabel2);
		auswahlPanel.add(auswahlListBox);
		auswahlPanel.add(anzeigenSpButton);
		verPanel.add(auswahlPanel); 	

															//Berechnung des Alters 
															
//															GregorianCalendar geburtstag = new GregorianCalendar();
//													        geburtstag.setTime(m.getGeburtsdatum());
//													        GregorianCalendar heute = new GregorianCalendar();
//													        int alter = heute.get(Calendar.YEAR) - geburtstag.get(Calendar.YEAR);
//													        if (heute.get(Calendar.MONTH) < geburtstag.get(Calendar.MONTH))
//													        {
//													            alter = alter - 1;
//													        }
//													        else if (heute.get(Calendar.MONTH) == geburtstag.get(Calendar.MONTH))
//													        {
//													            if (heute.get(Calendar.DATE) <= geburtstag.get(Calendar.DATE))
//													            {
//													                alter = alter - 1;
//													            }
//													        }
															

															
																// if
															// (suchprofil.getAlterMax()
															// <=
															// nutzerprofil.getGeburtsdatum())
															// return a;

															// if
															// (suchprofil.getAlterMin()
															// >=
															// nutzerprofil.getGeburtsdatum())
															// return a;
	}

}

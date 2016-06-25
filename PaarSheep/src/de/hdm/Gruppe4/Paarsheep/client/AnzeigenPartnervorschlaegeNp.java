package de.hdm.Gruppe4.Paarsheep.client;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministrationAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.*;

/**
 * @author andang
 *
 */
public class AnzeigenPartnervorschlaegeNp extends VerticalPanel {
	PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	Nutzerprofil nutzerprofil = ClientsideSettings.getAktuellerUser();

	/**
	 * VerticalPanel hinzuf�gen.
	 */
	private VerticalPanel verPanel = new VerticalPanel();
	/**
	 * Konstruktor hinzuf�gen.
	 */
	public AnzeigenPartnervorschlaegeNp() {

		this.add(verPanel);

		/**
		 * �berschrift-Label hinzuf�gen.
		 */
		final Label ueberschriftLabel = new Label("Diese Nutzerprofile koennten zu ihnen passen");
		final Label infoLabel = new Label();
		final Label ergebnisLabel = new Label();

		/**
		 * Tabelle zur Anzeige der Partnervorschlaege hinzufuegen.
		 */
		final FlexTable partnervorschlaegeNpFlexTable = new FlexTable();

		/**
		 * Tabelle formatieren und CSS einbinden.
		 */
		partnervorschlaegeNpFlexTable.setCellPadding(6);
		partnervorschlaegeNpFlexTable.getRowFormatter().addStyleName(0, "TableHeader");
		partnervorschlaegeNpFlexTable.addStyleName("FlexTable");

		/**
		 * Erste Zeile der Tabelle festlegen.
		 */
		partnervorschlaegeNpFlexTable.setText(0, 0, "NutzerprofilID");
		partnervorschlaegeNpFlexTable.setText(0, 1, "Ähnlichkeitsmaß");
		partnervorschlaegeNpFlexTable.setText(0, 2, "Vorname");
		partnervorschlaegeNpFlexTable.setText(0, 3, "Nachname");
		partnervorschlaegeNpFlexTable.setText(0, 4, "Geburtsdatum");
		partnervorschlaegeNpFlexTable.setText(0, 5, "Geschlecht");
		
		/**
		 * CSS-Anbindung
		 */
		partnervorschlaegeNpFlexTable.setCellPadding(6);
		partnervorschlaegeNpFlexTable.addStyleName("flexTable");

		ClientsideSettings.getPartnerboerseAdministration().getPartnervorschlaegeNp(nutzerprofil,
				new AsyncCallback<ArrayList<Aehnlichkeitsmass>>() {

					public void onFailure(Throwable caught) {
						infoLabel.setText("Es trat ein Fehler auf");
					}

					public void onSuccess(ArrayList<Aehnlichkeitsmass> result) {
						int row = partnervorschlaegeNpFlexTable.getRowCount();
						for (Aehnlichkeitsmass aehnlichkeits : result) {
							row++;
							partnervorschlaegeNpFlexTable.setText(row, 0,
									String.valueOf(aehnlichkeits.getFremdprofil().getProfilID()));
							partnervorschlaegeNpFlexTable.setText(row, 1,
									String.valueOf(aehnlichkeits.getAehnlichkeitsmass()+ "% "));
							partnervorschlaegeNpFlexTable.setText(row, 2, aehnlichkeits.getFremdprofil().getVorname());
							partnervorschlaegeNpFlexTable.setText(row, 3, aehnlichkeits.getFremdprofil().getNachname());
							partnervorschlaegeNpFlexTable.setText(row, 4, String.valueOf(aehnlichkeits.getFremdprofil().getGeburtsdatum()));
							partnervorschlaegeNpFlexTable.setText(row, 5, aehnlichkeits.getFremdprofil().getGeschlecht());
						}
					}
				});
		verPanel.add(ueberschriftLabel);
		verPanel.add(ergebnisLabel);
		verPanel.add(infoLabel);
		verPanel.add(partnervorschlaegeNpFlexTable);
	}
}

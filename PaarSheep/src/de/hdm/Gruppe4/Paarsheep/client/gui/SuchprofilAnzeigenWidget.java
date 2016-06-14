package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

/**public class SuchprofilAnzeigenWidget extends Composite{
	
	
	
	
	public SuchprofilAnzeigenWidget(Suchprofil profil) {
	final Suchprofil suchprofil = profil;	
	
	FlexTable suchprofilAnzeigen = new FlexTable();

	Button suchprofilBearbeiten = new Button("Bearbeiten");
	Button suchprofilLoeschen = new Button("Löschen");
	
	Label SuchprofilNameErlaeuterung = new Label("Name des Suchprofils: ");
	Label koerpergroesseVonErlaeuterung = new Label("Körpergröße von: ");
	Label koerpergroesseBisErlaeuterung = new Label("Körpergröße bis: ");
	Label alterVonErlaeuterung = new Label("Alter von: ");
	Label alterBisErlaeuterung = new Label("Alter bis: ");
	Label haarfarbeErlaeuterung = new Label("Haarfarbe: ");
	Label geschlechtErlaeuterung = new Label("Geschlecht: ");
	Label religionErlaeuterung = new Label("Religion: ");
	Label raucherErlaeuterung = new Label("Raucher: ");
	
	Label SuchprofilNameInhalt = new Label(suchprofil.getSuchprofilname());
	Label koerpergroesseVonInhalt = new Label(Integer.toString(suchprofil.getKoerpergroessevon()));
	Label koerpergroesseBisInhalt = new Label(Integer.toString(suchprofil.getKoerpergroessebis()));
	Label alterVonInhalt = new Label(Integer.toString(suchprofil.getAltervon()));
	Label alterBisInhalt = new Label(Integer.toString(suchprofil.getAlterbis()));
	Label haarfarbeInhalt = new Label(suchprofil.getHaarfarbe());
	Label geschlechtInhalt = new Label(suchprofil.getGeschlecht());
	Label religionInhalt = new Label(suchprofil.getRaucher());
	Label raucherInhalt = new Label(suchprofil.getRaucher());
	
	VerticalPanel suchprofilPanel = new VerticalPanel();
	
	//-------------------------------------------------------------------------
	
	suchprofilAnzeigen.setWidget(0, 0, SuchprofilNameErlaeuterung);
	suchprofilAnzeigen.setWidget(0, 1, SuchprofilNameInhalt);
	
	suchprofilAnzeigen.setWidget(1, 0, geschlechtErlaeuterung);
	suchprofilAnzeigen.setWidget(1, 1, geschlechtInhalt);
	
	suchprofilAnzeigen.setWidget(2, 0, alterVonErlaeuterung);
	suchprofilAnzeigen.setWidget(2, 1, alterVonInhalt);

	suchprofilAnzeigen.setWidget(3, 0, alterBisErlaeuterung);
	suchprofilAnzeigen.setWidget(3, 1, alterBisInhalt);
	
	suchprofilAnzeigen.setWidget(4, 0, koerpergroesseVonErlaeuterung);
	suchprofilAnzeigen.setWidget(4, 1, koerpergroesseVonInhalt);
	
	suchprofilAnzeigen.setWidget(5, 0, koerpergroesseBisErlaeuterung);
	suchprofilAnzeigen.setWidget(5, 1, koerpergroesseBisInhalt);
	
	suchprofilAnzeigen.setWidget(6, 0, haarfarbeErlaeuterung);
	suchprofilAnzeigen.setWidget(6, 1, haarfarbeInhalt);
	
	suchprofilAnzeigen.setWidget(7, 0, religionErlaeuterung);
	suchprofilAnzeigen.setWidget(7, 1, religionInhalt);
	
	suchprofilAnzeigen.setWidget(8, 0, raucherErlaeuterung);
	suchprofilAnzeigen.setWidget(8, 1, raucherInhalt);
	
	suchprofilAnzeigen.setWidget(9, 0, suchprofilBearbeiten);
	suchprofilAnzeigen.setWidget(9, 1, suchprofilLoeschen);
	
	//---------------------------------------------------------------------------------
	
	suchprofilPanel.add(suchprofilAnzeigen);
	
	
	initWidget(suchprofilPanel);
	
	}**/
	
	import java.util.Arrays;
	import java.util.List;

	import com.google.gwt.cell.client.AbstractCell;
	import com.google.gwt.cell.client.Cell;
	import com.google.gwt.core.client.EntryPoint;
	import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
	import com.google.gwt.user.cellview.client.CellList;
	import com.google.gwt.user.client.ui.RootPanel;
	import com.google.gwt.user.client.ui.VerticalPanel;
	import com.google.gwt.view.client.ProvidesKey;
	import com.google.gwt.view.client.SelectionModel;
	import com.google.gwt.view.client.SingleSelectionModel;

	public class SuchprofilAnzeigenWidget implements EntryPoint {
	   /**
	   * A simple data type that represents a contact.
	   */
	   private static class Contact {
	      private static int nextId = 0;
	      private final int id;
	      private String name;

	      public Contact(String name) {
	         nextId++;
	         this.id = nextId;
	         this.name = name;
	      }
	   }

	   /**
	   * A custom {@link Cell} used to render a {@link Contact}.
	   */
	   private static class ContactCell extends AbstractCell<Contact> {
	      @Override
	      public void render(Contact value, Object key, SafeHtmlBuilder sb) {
	         if (value != null) {
	            sb.appendEscaped(value.name);
	         }		
	      }
	   }

	   /**
	   * The list of data to display.
	   */
	   private static final List<Contact> CONTACTS = Arrays.asList(new Contact(
	      "John"), new Contact("Joe"), new Contact("Michael"),
	      new Contact("Sarah"), new Contact("George"));

	   public void onModuleLoad() {
	      /*
	      * Define a key provider for a Contact. We use the unique ID 
	      * as the key, which allows to maintain selection even if the
	      * name changes.
	      */
	      ProvidesKey<Contact> keyProvider = new ProvidesKey<Contact>() {
	         public Object getKey(Contact item) {
	            // Always do a null check.
	            return (item == null) ? null : item.id;
	         }
	      };

	      // Create a CellList using the keyProvider.
	      CellList<Contact> cellList = new CellList<Contact>(new ContactCell(),      
	      keyProvider);

	      // Push data into the CellList.
	      cellList.setRowCount(CONTACTS.size(), true);
	      cellList.setRowData(0, CONTACTS);

	      // Add a selection model using the same keyProvider.
	      SelectionModel<Contact> selectionModel 
	      = new SingleSelectionModel<Contact>(
	      keyProvider);
	      cellList.setSelectionModel(selectionModel);

	      /*
	      * Select a contact. The selectionModel will select based on the 
	      * ID because we used a keyProvider.
	      */
	      Contact sarah = CONTACTS.get(3);
	      selectionModel.setSelected(sarah, true);

	      // Modify the name of the contact.
	      sarah.name = "Sara";

	      /*
	      * Redraw the CellList. Sarah/Sara will still be selected because we
	      * identify her by ID. If we did not use a keyProvider, 
	      * Sara would not be selected.
	      */
	      cellList.redraw();

	      VerticalPanel panel = new VerticalPanel();
	      panel.setBorderWidth(1);	    
	      panel.setWidth("200");
	      panel.add(cellList);

	      // Add the widgets to the root panel.
	      RootPanel.get().add(panel);
	   }
	}
	
}

package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;



public class NutzerForm extends VerticalPanel {

	TextBox vornameTextBox = new TextBox();
	TextBox nachnameTextBox = new TextBox();
	TextBox geburtsdatumTextBox = new TextBox();
	Label idValueLabel = new Label();
	VerticalPanel vPanel = new VerticalPanel();

	public void ladeNutzerForm() {
		Grid nutzerGrid = new Grid(4, 3);
		this.add(nutzerGrid);

		Label idLabel = new Label("ID");
		nutzerGrid.setWidget(0, 0, idLabel);
		nutzerGrid.setWidget(0, 1, idValueLabel);

		Label vornameLabel = new Label("Vorname");
		nutzerGrid.setWidget(1, 0, vornameLabel);
		nutzerGrid.setWidget(1, 1, vornameTextBox);

		Label nachnameLabel = new Label("Nachname");
		nutzerGrid.setWidget(2, 0, nachnameLabel);
		nutzerGrid.setWidget(2, 1, nachnameTextBox);

		Label geburtsdatumLabel = new Label("Geburtsdatum");
		nutzerGrid.setWidget(3, 0, geburtsdatumLabel);
		nutzerGrid.setWidget(3, 1, geburtsdatumTextBox);

		vPanel.add(nutzerGrid);
		RootPanel.get().add(vPanel);
		HorizontalPanel nutzerButtonsPanel = new HorizontalPanel();
		this.add(nutzerButtonsPanel);

		Button anlegenButton = new Button("Anlegen");
		nutzerButtonsPanel.add(anlegenButton);
		Button abbrechenButton = new Button("Abbrechen");
		nutzerButtonsPanel.add(abbrechenButton);

		RootPanel.get().add(nutzerButtonsPanel);
		/*
		 * Button changeButton = new Button( "Ändern");
		 * changeButton.addClickHandler(new
		 * ChangeClickHandler());nutzerButtonsPanel.add(changeButton);
		 * 
		 * Button searchButton = new
		 * Button("Suchen");nutzerButtonsPanel.add(searchButton);
		 * 
		 * 
		 * Button deleteButton = new Button(
		 * "Löschen");deleteButton.addClickHandler(new DeleteClickHandler());
		 * nutzerButtonsPanel.add(deleteButton);
		 * 
		 * Button newButton = new Button( "Neu");newButton.addClickHandler(new
		 * NewClickHandler()); nutzerButtonsPanel.add(newButton); }
		 */
/*
		/**
		 * ClickHandler für das Nutzerformular
		 
		private class NewClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				String firstName = vornameTextBox.getText();
				String lastName = nachnameTextBox.getText();
				bankVerwaltung.createCustomer(vorname, nachname, new CreateCustomerCallback());
			}
		}

		class CreateCustomerCallback implements AsyncCallback<Customer> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das Anlegen eines neuen Nutzers ist fehlgeschlagen!");
			}

			@Override
			public void onSuccess(Customer customer) {
				if (customer != null) {
					// Das erfolgreiche Hinzufügen eines Kunden wird an den
					// Kunden- und
					// Kontenbaum propagiert.
					catvm.addCustomer(customer);
				}
			}
		}
	}
	*/
}
}
package de.hdm.Gruppe4.Paarsheep.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class BearbeiteBeschreibungWidget extends Composite{
	
	 
		
		
		private Label erlaueterungLabel = new Label();
		private TextBox beschreibungTextBox = new TextBox();
		
		
		public BearbeiteBeschreibungWidget(String erlaueterung){
			final HorizontalPanel hPanel = new HorizontalPanel();
			erlaueterungLabel.setText(erlaueterung);
					
			hPanel.add(erlaueterungLabel);
			hPanel.add(beschreibungTextBox);
			
			initWidget(hPanel);
		}
		
		public void setText(String erlaueterung){
			beschreibungTextBox.setText(erlaueterung);
		}
	public	String getText(){
			return beschreibungTextBox.getText();
		}
	}



package de.hdm.Gruppe4.Paarsheep.shared.report;

import java.io.Serializable;


public class SimpleParagraph extends Paragraph implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String text = "";
	
	public SimpleParagraph() {
	  }
	
	/**
	 * Inhalt des Absatzes
	 * @param value
	 */
	public SimpleParagraph(String value) {
	    this.text = value;
	  }
	
	/**
	 * Auslesen des Inhaltes
	 * @return this.text
	 */
	public String getText() {
	    return this.text;
	  }
	
	/**
	 * Überschreiben des Inhaltes
	 * @param text
	 */
	 public void setText(String text) {
		    this.text = text;
		  }
	 
	 /**
	  * Umwandeln in ein String Objekt
	  * @return this.text
	  */
	 public String toString() {
		    return this.text;
		  }

}

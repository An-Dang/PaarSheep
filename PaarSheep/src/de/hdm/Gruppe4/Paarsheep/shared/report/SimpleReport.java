package de.hdm.Gruppe4.Paarsheep.shared.report;

import java.util.Vector;



public abstract class SimpleReport extends Report {
	
	 private static final long serialVersionUID = 1L;
	 
	 private ArrayList<Row> Tabelle = new ArrayList<Row>();
	 
	 /**
	  * Zeile wird hinzugefügt
	  * @param r
	  */
	 public void addRow(Row r) {
		    this.Tabelle.addElement(r);
		  }
	 
	 /**
	  * Entfernen einer Zeile
	  * @param r
	  */
	 public void removeRow(Row r) {
		    this.Tabelle.removeElement(r);
		  }
	 
	 /**
	  * Auslesen der Zeilen 
	  * @return Tabelle
	  */
	 public ArrayList<Row> getRows() {
		    return this.Tabelle;
		  }

}

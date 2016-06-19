package de.hdm.Gruppe4.Paarsheep.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author andang
 *
 */
public class Row implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Vector<Column> columns = new Vector<Column>();
	
	/**
	 * Spalte hinzuf�gen
	 * @param c
	 */
	public void addColumn(Column c) {
	    this.columns.addElement(c);
	  }
	
	/**
	 * Spalte entfernen
	 * @param c
	 */
	public void removeColumn(Column c) {
	    this.columns.removeElement(c);
	  }
	
	/**
	 * Auslesen der Spalten
	 * @return columns
	 */
	public Vector<Column> getColumns() {
	    return this.columns;
	  }
	
	/**
	 * Auslesen der Anzahl der Spalten
	 * @return columns.size
	 */
	public int getNumColumns() {
	    return this.columns.size();
	  }
	
	/**
	 * Auslesen der Spalten Objekte
	 * @param i
	 * @return columns.elementAt(i)
	 */
	public Column getColumnAt(int i) {
	    return this.columns.elementAt(i);
	  }
}

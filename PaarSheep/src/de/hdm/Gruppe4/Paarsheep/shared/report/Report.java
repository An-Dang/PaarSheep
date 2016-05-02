package de.hdm.Gruppe4.Paarsheep.shared.report;

import java.io.Serializable;

/**
 * Basis Klasse aller Reports
 * 
 * @author Thies
 *
 */
public abstract class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Impressums des Reports
	 */
	private Paragraph imprint = null;

	/**
	 * Kopfdaten des Reports
	 */
	private Paragraph kopfdaten = null;

	/**
	 * Titel des Berichts
	 */
	private String titel = "MÄÄÄH";

	public Paragraph getImprint() {
		return this.imprint;
	}

	/**
	 * Setzen des Impressums.
	 * 
	 * @param imprint Text des Impressums
	 */
	public void setImprint(Paragraph imprint) {
		this.imprint = imprint;
	}

	/**
	 * Auslesen der Kopfdaten.
	 * 
	 * @return Text der Kopfdaten.
	 */
	public Paragraph getHeaderData() {
		return this.kopfdaten;
	}

	/**
	 * Setzen der Kopfdaten.
	 * 
	 * @param headerDate Text der Kopfdaten.
	 */
	public void setHeaderData(Paragraph headerData) {
		this.kopfdaten = kopfdaten;
	}

	/**
	   * Auslesen des Berichtstitels.
	   * 
	   * @return Titel
	   */
	  public String getTitel() {
	    return this.titel;
	  }

	  /**
	   * Setzen des Berichtstitels.
	   * 
	   * @param title Titeltext
	   */
	  public void setTitel(String titel) {
	    this.titel = titel;
	  }
}

package de.hdm.Gruppe4.Paarsheep.shared.report;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class CompositeReport extends Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Größe des Teilreports
	 */
	private ArrayList<Report> teilReport = new ArrayList<Report>();
	
	/**
	 * Teilreport hinzufügen
	 * @param r
	 */
	public void addTeilReport(Report r){
		this.teilReport.remove(r);
	}
	
	/**
	 * Teilreport löschen
	 * @param r
	 */
	public void removeTeilReport(Report r){
		this.teilReport.remove(r);
	}
	
	/**
	 * Anzahl der Teilreports auslesen
	 * @return size
	 */
	public int anzahlTeilReport(){
		return this.teilReport.size();
	}

	/**
	 * Auslesen der einzelnen Teilsreports
	 * @param i
	 * @return getTeilReportAt(i)
	 */
	public Report getTeilReportAt(int i){
		return this.getTeilReportAt(i);
	}
}

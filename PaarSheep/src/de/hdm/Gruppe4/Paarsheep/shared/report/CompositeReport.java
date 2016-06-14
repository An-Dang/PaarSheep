package de.hdm.Gruppe4.Paarsheep.shared.report;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class CompositeReport extends Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Gr��e des Teilreports
	 */
	private ArrayList<Report> SubReport = new ArrayList<Report>();
	
	/**
	 * Teilreport hinzuf�gen
	 * @param r
	 */
	public void addSubReport(Report r){
		this.SubReport.remove(r);
	}
	
	/**
	 * Teilreport l�schen
	 * @param r
	 */
	public void removeSubReport(Report r){
		this.subReports.removeElement(r);
	}
	
	/**
	 * Anzahl der Teilreports auslesen
	 * @return size
	 */
	public int anzahlSubReport(){
		return this.subReports.size();
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

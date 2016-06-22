package de.hdm.Gruppe4.Paarsheep.shared.report;

/**
 * Diese Klasse wird benoetigt, um auf dem Client die ihm vom Server zur
 * Verfuegung gestellten Report-Objekte in ein menschenlesbares Format zu 
 * ueberfuehren.
 * 
 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
 * das Zielformat Ueberfuehrten Information wird den Subklassen ueberlassen. In
 * dieser Klasse werden die Signaturen der Methoden deklariert, die fuer die
 * Prozessierung der Quellinformation zustaendig sind.
 * 
 * @author Thies
 * ------------------------------------------------------------------------------------------
 * Diese Klasse wurde, wie von Herrn Prof. Dr. Thies in der Vorlesung gewuenscht, als Grundlage 
 * uebernommen und bei Notwendigkeit an die Beduerfnisse des IT-Projekts SS 2016 "Partnerboerse" 
 * angepasst. 
 */

public abstract class ReportWriter {
	
	  /**
	   * Uebersetzen eines InfoObjekteByNutzerReport in das Zielformat.
	   * 
	   * @param r der zu uebersetzende Report
	   */
	
	public abstract void process(InfoObjekteByNutzerReport r);
	
	
	/**
	 * Uebersetzen eines ProfilInfoByNutzerprofilReport in das Zielformat.
	 * 
	 * @param r der zu uebersetzende Report
	 */
	public abstract void process(ProfilInfoByNutzerprofilReport r);
	
	/**
	 * Uebersetzen eines PartnervorschleageBySuchprofilReport in das Zielformat.
	 * 
	 * @param r der zu uebersetzende Report
	 */
	public abstract void process(PartnervorschleageBySuchprofilReport r);

	
	/**
	 * Uebersetzen eines AllPartnervorschlaegeSpReport in das Zielformat.
	 * 
	 * @param r der zu uebersetzende Report
	 */ 
	public abstract void process(PartnervorschleageByUngesehenenNutzerprofilenReport r);
	
	
}
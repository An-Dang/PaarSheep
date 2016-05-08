package de.hdm.Gruppe4.Paarsheep.shared.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;


public class CompositeParagraph extends Paragraph implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Speicherplatz für Unterabschnitte
	 */
	private ArrayList<SimpleParagraph> subParagraphs = new ArrayList<SimpleParagraph>();

	
	/**
	 * Unterabschnitt hinzufügen
	 * @param p
	 */
	public void addSubParagraph(SimpleParagraph p) {
		this.subParagraphs.addElement(p);
	}

	/**
	 * Unterabschnitt entfernen
	 * @param p
	 */
	public void removeSubParagraph(SimpleParagraph p) {
		this.subParagraphs.removeElement(p);
	}

	/**
	 * Auslesen aller Unterabschnnitte
	 * @return subParagraph
	 */
	public ArrayList<SimpleParagraph> getSubParagraphs() {
		return this.subParagraphs;
	}

	/**
	 * Auslesen der Anzahl der Unterabschnitte
	 * @return subParagraph
	 */
	public int getNumParagraphs() {
		return this.subParagraphs.size();
	}

	/**
	 * Auslesen der einzelenen Unterabschnitte
	 * @param i
	 * @return
	 */
	public SimpleParagraph getParagraphAt(int i) {
		return this.subParagraphs.elementAt(i);
	}

	/**
	 * Umwandlung des subParagraph in einen String
	 */
	public String toString() {
		/*
		 * Wir legen einen leeren Buffer an, in den wir sukzessive sämtliche
		 * String-Repräsentationen der Unterabschnitte eintragen.
		 */
		StringBuffer result = new StringBuffer();

		// Schleife über alle Unterabschnitte
		for (int i = 0; i < this.subParagraphs.size(); i++) {
			SimpleParagraph p = this.subParagraphs.elementAt(i);

			/*
			 * den jew. Unterabschnitt in einen String wandeln und an den Buffer
			 * hängen.
			 */
			result.append(p.toString() + "\n");
		}

		/*
		 * Schließlich wird der Buffer in einen String umgewandelt und
		 * zurückgegeben.
		 */
		return result.toString();
	}
}

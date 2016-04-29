package de.hdm.Gruppe4.Paarsheep.shared.bo;

import java.io.Serializable;

/**
 * <p>
 * Die Klasse <code>BusinessObject</code> stellt die Basisklasse aller in diesem
 * Projekt f�r die Umsetzung des Fachkonzepts relevanten Klassen dar.
 * </p>
 * <p>
 * Zentrales Merkmal ist, dass jedes <code>BusinessObject</code> eine Nummer
 * besitzt, die man in einer relationalen Datenbank auch als Prim�rschl�ssel
 * bezeichnen w�rde. Fernen ist jedes <code>BusinessObject</code> als
 * {@link Serializable} gekennzeichnet. Durch diese Eigenschaft kann jedes
 * <code>BusinessObject</code> automatisch in eine textuelle Form �berf�hrt und
 * z.B. zwischen Client und Server transportiert werden. Bei GWT RPC ist diese
 * textuelle Notation in JSON (siehe http://www.json.org/) kodiert.
 * </p>
 * 
 * @author thies
 * @version 1.0
 *
 */

public abstract class BusinessObject implements Serializable {
	
	BusinessObject(){
		
	}

	private static final long serialVErsionUID = 1L;

	/**
	 * Eindeutige Indentifikationsnummer einer Instanz dieser Klasse
	 */
	private int profilID = 0;

	/**
	 * ID auslesen lassen
	 * 
	 * @return
	 */
	public int getID() {
		return this.profilID;
	}

	/**
	 * ID setzen
	 * 
	 * @param id
	 */
	public void setID(int profilID) {
		this.profilID = profilID;
	}

	/**
	 * Textuelle Darstellung der jeweiligen Instanz
	 */
	public String toString() {

		/**
		 * Ausgabe des Klassennamens und der ID
		 */
		return this.getClass().getName() + "  " + this.profilID;
	}

	/**
	 * <p>
	 * Feststellen der <em>inhaltlichen</em> Gleichheit zweier
	 * <code>BusinessObject</code>-Objekte. Die Gleichheit wird in diesem
	 * Beispiel auf eine identische ID beschr�nkt.
	 * </p>
	 * <p>
	 * <b>ACHTUNG:</b> Die inhaltliche Gleichheit nicht mit dem Vergleich der
	 * <em>Identit�t</em> eines Objekts mit einem anderen verwechseln!!! Dies
	 * w�rde durch den Operator <code>==</code> bestimmt. Bei Unklarheit hierzu
	 * k�nnen Sie nocheinmal in die Definition des Sprachkerns von Java schauen.
	 * Die Methode <code>equals(...)</code> ist f�r jeden Referenzdatentyp
	 * definiert, da sie bereits in der Klasse <code>Object</code> in
	 * einfachster Form realisiert ist. Dort ist sie allerdings auf die simple
	 * Bestimmung der Gleicheit der Java-internen Objekt-ID der verglichenen
	 * Objekte beschr�nkt. In unseren eigenen Klassen k�nnen wir diese Methode
	 * �berschreiben und ihr mehr Intelligenz verleihen.
	 * </p>
	 */
	public boolean equals(Object o) {

		if (o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			try {
				if (bo.getID() == this.profilID)
					return true;
			}

			catch (IllegalArgumentException e) {
				/*
				 * Wenn irgendetwas schief geht, dann geben wir
				 * sicherheitshalber false zur�ck.
				 */
				return false;
			}
		}
		/*
		 * Wenn bislang keine Gleichheit bestimmt werden konnte, dann m�ssen
		 * schlie�lich false zur�ckgeben.
		 */
		return false;
	}

	/**
	 * <p>
	 * Erzeugen einer ganzen Zahl, die f�r das <code>BusinessObject</code>
	 * charakteristisch ist.
	 * </p>
	 * <p>
	 * Zusammen mit <code>equals</code> sollte diese Methode immer definiert
	 * werden. Manche Java-Klassen verwendenden <code>hashCode</code>, um
	 * initial ein Objekt (z.B. in einer Hashtable) zu identifizieren. Erst
	 * danach w�rde mit <code>equals</code> festgestellt, ob es sich tats�chlich
	 * um das gesuchte Objekt handelt.
	 */
	public int hashCode() {
		return this.profilID;
	}
}

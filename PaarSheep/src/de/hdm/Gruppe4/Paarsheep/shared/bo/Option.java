package de.hdm.Gruppe4.Paarsheep.shared.bo;

/**
 * Die Klasse Option erweitert die Klasse Eigenschaft.
 * @author andang
 *
 */
public class Option extends Eigenschaft {
	
	private static final long serialVersionUID = 1L;
	
	String optionsBezeichnung;
	
	/**
	 * Die Bezeichnung einer Option wird zurueckgegeben
	 * @return optionsBezeichnung Bezeichnung einer der Optionen einer Eigenscahft
	 */
	public String getOptionsBezeichnung() {
		return optionsBezeichnung;
	}

	/**
	 * Bezeichnung einer Option wird gesetzt
	 * @param optionsBezeichnung Bezeichnung einer Option einer Eigenschaft
	 */
	public void setOptionsBezeichnung(String optionsBezeichnung) {
		this.optionsBezeichnung = optionsBezeichnung;
	}

}

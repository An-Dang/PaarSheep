package de.hdm.Gruppe4.Paarsheep.server.db;
import java.util.ArrayList;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;
public class Test {

	public static void main(String[] args) {
		EigenschaftMapper Mapper = new EigenschaftMapper();
		ArrayList<Beschreibung> Penis = new ArrayList<Beschreibung> ();
		Penis = Mapper.readEigenschaft();
		for (Beschreibung a : Penis){
			System.out.print(a.getErlaeuterung());
			System.out.print(a.getID());
			
		}
	}

}

package de.hdm.Gruppe4.Paarsheep.server.db;
import java.util.ArrayList;
import java.util.List;

import de.hdm.Gruppe4.Paarsheep.shared.bo.*;
public class Test {

	public static void main(String[] args) {
		NutzerprofilMapper Mapper = new NutzerprofilMapper();
		Nutzerprofil Penis = new Nutzerprofil ();
		Penis = Mapper.findByNutzerprofilId(1);
//		for (Nutzerprofil a : Penis){
			System.out.print(Penis.getNachname());
			System.out.print(Penis.getReligion());
			
//		}
	}

}

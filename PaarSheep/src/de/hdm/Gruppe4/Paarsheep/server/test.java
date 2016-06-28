package de.hdm.Gruppe4.Paarsheep.server;

import java.util.ArrayList;
import java.util.List;

import de.hdm.*;
import de.hdm.Gruppe4.Paarsheep.server.db.NutzerprofilMapper;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Aehnlichkeitsmass;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Nutzerprofil tmp = new Nutzerprofil();
		tmp.setID(1);
		
		PartnerboerseAdministrationImpl admin = new PartnerboerseAdministrationImpl();
		
		ArrayList<Nutzerprofil> referenz = new ArrayList<>();
		
		 
		tmp = NutzerprofilMapper.nutzerprofilMapper().findByNutzerprofilId(1);
		System.out.println(tmp.getProfilID());
		referenz = admin.getUnangeseheneNutzerprofile(tmp.getProfilID());
		
		
		//referenz = NutzerprofilMapper.nutzerprofilMapper().findUnangeseheneNutzerprofileByID(tmp.getProfilID());
		//admin.getPartnervorschlaegeNp(tmp, referenz);
		
		
		for (Nutzerprofil test : referenz) {
			Aehnlichkeitsmass a = new Aehnlichkeitsmass();
			
			a.setFremdprofil(test);
			
			
			
			a.setAehnlichkeitsmass(admin.getSimilitaryOfProfile(tmp,test));
			
			System.out.println(a.getAehnlichkeitsmass());
			System.out.println(a.getFremdprofil().getProfilID());
			System.out.println(a.getFremdprofil().getVorname());
		}
		

	}

}



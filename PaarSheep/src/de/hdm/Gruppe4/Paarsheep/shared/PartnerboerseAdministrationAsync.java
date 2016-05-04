package de.hdm.Gruppe4.Paarsheep.shared;

import java.sql.Date;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Auswahloption;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Eigenschaft;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Merkzettel;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Profil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Sperrliste;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

public interface PartnerboerseAdministrationAsync {

	void createAuswahloption(String optionsBezeichnung, AsyncCallback<Auswahloption> callback);

	void createEigenschaft(String erlaeuterung, AsyncCallback<Eigenschaft> callback);

	void createNutzerprofil(String vorname, String nachname, Date geburtsdatum, 
							Boolean raucher, String haarfarbe, String religion, Integer koerpergroesse, String geschlecht, 
							AsyncCallback<Nutzerprofil> callback);

	void createProfil(Boolean raucher, String haarfarbe, String religion, Integer koerpergroesse, String geschlecht,
			AsyncCallback<Profil> callback);

	void createSuchprofil(int altervon, int alterbis, AsyncCallback<Suchprofil> callback);

	void getAllProfils(AsyncCallback<ArrayList<Profil>> callback);

	void init(AsyncCallback<Void> callback);

	void createMerkzettel(int ID, AsyncCallback<Merkzettel> callback);

	void createSperrliste(int ID, AsyncCallback<Sperrliste> callback);

}

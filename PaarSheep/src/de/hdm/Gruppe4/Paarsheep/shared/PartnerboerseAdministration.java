package de.hdm.Gruppe4.Paarsheep.shared;

import java.sql.Date;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Auswahloption;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Eigenschaft;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Merkzettel;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Profil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Sperrliste;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;

@RemoteServiceRelativePath("partnerboerseadministration")
public interface PartnerboerseAdministration extends RemoteService{

	  /**
	   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
	   * {@link BankVerwaltungImpl} notwendig. Bitte diese Methode direkt nach der
	   * Instantiierung aufrufen.
	   * 
	   * @throws IllegalArgumentException
	   * @author Thies
	   * @author Dominik
	   * 
	   */
	  public void init() throws IllegalArgumentException;
	  
	  /**
	   * Ein Profil anlegen.
	   * 
	   * @author Dominik
	   */
	  public Profil createProfil(Boolean raucher, String haarfarbe, String religion, Integer koerpergroesse, String geschlecht)
			  throws IllegalArgumentException;
	  
	  /**
	   * Ein Nutzerprofil anlegen.
	   * 
	   * @author Dominik
	   */
	  public Nutzerprofil createNutzerprofil(String vorname, String nachname, Date geburtsdatum)
			  throws IllegalArgumentException;
	  
	  /**
	   * Eine Eigenschaft anlegen.
	   * 
	   * @author Dominik
	   */
	  public Eigenschaft createEigenschaft(String erlaeuterung)
			  throws IllegalArgumentException;
	  
	  /**
	   * Eine Auswahloption anlegen.
	   * 
	   * @author Dominik
	   */
	  public Auswahloption createAuswahloption(String optionsBezeichnung)
			  throws IllegalArgumentException;
	  
	  /**
	   * Ein Suchprofil anlegen.
	   * 
	   * @author Dominik
	   */
	  public Suchprofil createSuchprofil(int altervon, int alterbis)
			  throws IllegalArgumentException;
	  
	  
	  public Merkzettel createMerkzettel(int ID)
			  throws IllegalArgumentException;
	  
	  public Sperrliste createSperrliste(int ID)
			  throws IllegalArgumentException;


	  
	  
	  /**
	   * Zuerst müssen die Mapperklassen und das Server-Package fertig werden...
	   * @author Dominik
	   */
	  
	  public ArrayList<Profil> getAllProfils()
			  throws IllegalArgumentException;
	  
	  
	  
	  
}






//package de.hdm.Gruppe4.Paarsheep.server;
//
//import java.util.List;
//
//import de.hdm.Gruppe4.Paarsheep.server.db.AuswahloptionMapper;
//import de.hdm.Gruppe4.Paarsheep.server.db.BeschreibungMapper;
//import de.hdm.Gruppe4.Paarsheep.server.db.BesuchteProfilListeMapper;
//import de.hdm.Gruppe4.Paarsheep.server.db.EigenschaftMapper;
//import de.hdm.Gruppe4.Paarsheep.server.db.InformationMapper;
//import de.hdm.Gruppe4.Paarsheep.server.db.MerkzettelMapper;
//import de.hdm.Gruppe4.Paarsheep.server.db.NutzerprofilMapper;
//import de.hdm.Gruppe4.Paarsheep.server.db.SperrlisteMapper;
//import de.hdm.Gruppe4.Paarsheep.server.db.SuchprofilMapper;
//import de.hdm.Gruppe4.Paarsheep.shared.PartnerboerseAdministration;
//import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
//import de.hdm.Gruppe4.Paarsheep.shared.bo.Suchprofil;
//import de.hdm.Gruppe4.Paarsheep.shared.report.InfoObjekteByNutzerReport;
//
//public class ReportGeneratorImpl {
//	
//	
//	private PartnerboerseAdministration get PartnerboerseAdministration(){
//		return.this.partnerboerseadministration;
//	
//	private AuswahloptionMapper auswahlMapper = null;
//	private AuswahloptionMapper auswahloptionMapper = null;
//	private BeschreibungMapper beschreibungMapper = null;
//	private BesuchteProfilListeMapper besuchteProfilListeMapper = null;
//	private EigenschaftMapper eigenschaftMapper = null;
//	private InformationMapper informationMapper = null;
//	private MerkzettelMapper merkzettelMapper = null;
//	private NutzerprofilMapper nutzerprofilMapper = null;
//	private SperrlisteMapper sperrlisteMapper = null;
//	private SuchprofilMapper suchprofilMapper = null;
//
//	
//	/*
//	 * *************************************************************************
//	 * ** ABSCHNITT, Beginn: PartnervorschlaegeSuchprofil
//	 * *************************************************************************
//	 * **
//	 */
//
//	public InfoObjekteByNutzerReport createInfoObjekteByNutzerReport (Nutzerprofil nutzerprofil) throws IllegalArgumentException{
//		
//		if (this.get)
//		
//	}
//	
//	
//	
//	
//	
//	/**
//	 * Aehnlichkeit zwischen den Profilinfos und InfoObjekte eines Suchprofils eines
//	 * Nutzers und den Profilinfos und InfoObjekte anderer Nutzerprofile berechnen.
//	 * 
//	 * @see de.hdm.gruppe7.partnerboerse.shared.PartnerboerseAdministration#berechneAehnlichkeitSpFor(int)
//	 */
//	public void AehnlichkeitForSuchprofilBerechnen(int profilId) throws IllegalArgumentException {
//
//		/**
//		 * Die Aehnlichkeiten werden aus der Datenbank geloescht, damit sie neu
//		 * berechnet und gespeichert werden k�nnen. So sind die Werte immer
//		 * aktuell, da Aenderungen im z.B. Suchprofil in der Berechnung
//		 * ber�cksichtig werden.
//		 */
//	
//	}
//		/**
//		 * Alle Partnervorschlaege anhand von Suchprofilen fuer einen Nutzer
//		 * auslesen. Es werden nur die Nutzerprofile ausgelesen, von denen der
//		 * Nutzer nicht gesperrt wurde.
//		 * 
//		 */
//
//		public List<Nutzerprofil> getPartnervorschlaegeSuchprofil(int profilid, String suchprofilname)
//				throws IllegalArgumentException {
//
//			Suchprofil suchprofil = this.suchprofilMapper.findSuchprofiByName(profilid, suchprofilname);
//
//			int suchprofilid = suchprofil.getProfilID();
//			return this.nutzerprofilMapper.findGeordnetePartnervorschlaegeSp(profilid, suchprofilid);
//		}
//
//		
//	
//	/*
//	 * *************************************************************************
//	 * ** ABSCHNITT, Ende: PartnervorschlaegeSuchprofil
//	 * *************************************************************************
//	 * **
//	 */
//	}	
//}

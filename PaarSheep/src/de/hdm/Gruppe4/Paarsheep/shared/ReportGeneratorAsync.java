package de.hdm.Gruppe4.Paarsheep.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.Gruppe4.Paarsheep.shared.bo.Aehnlichkeitsmass;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Profil;
import de.hdm.Gruppe4.Paarsheep.shared.report.ReportByAllProfile;
import de.hdm.Gruppe4.Paarsheep.shared.report.ReportByProfil;

/**
 * Gegenstück zum Interface
 * @author Manuel Weiler
 *
 */
public interface ReportGeneratorAsync{

	void init(AsyncCallback<Void> callback);
	
	void createReportByAllProfile(AsyncCallback<ReportByAllProfile> callback);
	
	void setNutzerprofil(Nutzerprofil nutzerprofil, AsyncCallback<Void> callback);
	
	void createReportByProfil(Nutzerprofil p, Aehnlichkeitsmass a, AsyncCallback<ReportByProfil> callback);
}

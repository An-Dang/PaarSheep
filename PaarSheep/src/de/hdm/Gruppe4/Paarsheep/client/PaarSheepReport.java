package de.hdm.Gruppe4.Paarsheep.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.Gruppe4.Paarsheep.shared.ReportGeneratorAsync;
import de.hdm.Gruppe4.Paarsheep.shared.bo.Nutzerprofil;

public class PaarSheepReport extends VerticalPanel implements EntryPoint {

	ReportGeneratorAsync reportGenerator = null;
	private ReportGeneratorAsync reportGeneratorAsync;
	
	Nutzerprofil nutzerprofil = new Nutzerprofil();

	private VerticalPanel loginPanel = new VerticalPanel();
	private Anchor signInLink = new Anchor("Jetzt einloggen");
	private Anchor signOutLink = new Anchor();
	
	Button unangesehenePartnervorschlaegeButton = new Button("Unangesehene Partnervorschläge");

	Button partnervorschlaegeSuchprofilButton = new Button("Partnervorschläge anhand von Suchprofilen");

	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
	}

}

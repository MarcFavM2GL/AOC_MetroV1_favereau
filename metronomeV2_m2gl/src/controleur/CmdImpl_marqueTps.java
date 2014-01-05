package controleur;

import moteur.CommandInterf;


public class CmdImpl_marqueTps implements CommandInterf {
	
	GestionEvtsInterf controleur;
	
	public CmdImpl_marqueTps(GestionEvtsInterf ctrl) {
		this.controleur = ctrl;
	}

	@Override
	public Boolean executer() {

		controleur.marquerTemps();

		return true;
	}

}

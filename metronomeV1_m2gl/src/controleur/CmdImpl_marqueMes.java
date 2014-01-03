package controleur;

import moteur.CommandInterf;

public class CmdImpl_marqueMes implements CommandInterf {

	GestionEvtsInterf controleur;
	
	public CmdImpl_marqueMes(GestionEvtsInterf ctrl) {
		this.controleur = ctrl;
	}
	
	@Override
	public Boolean executer() {

		controleur.marquerMesure();

		return true;
	}

}

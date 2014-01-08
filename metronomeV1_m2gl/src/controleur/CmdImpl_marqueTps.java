package controleur;

import moteur.CommandInterf;


/**
 */
public class CmdImpl_marqueTps implements CommandInterf {
	
	GestionEvtsInterf controleur;
	
	/**
	 * Constructor for CmdImpl_marqueTps.
	 * @param ctrl GestionEvtsInterf
	 */
	public CmdImpl_marqueTps(GestionEvtsInterf ctrl) {
		this.controleur = ctrl;
	}

	/**
	 * Method executer.
	 * @return Boolean
	 * @see moteur.CommandInterf#executer()
	 */
	@Override
	public Boolean executer() {

		controleur.marquerTemps();

		return true;
	}

}

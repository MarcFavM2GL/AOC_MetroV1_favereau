package controleur;

import moteur.CommandInterf;

/**
 */
public class CmdImpl_marqueMes implements CommandInterf {

	GestionEvtsInterf controleur;
	
	/**
	 * Constructor for CmdImpl_marqueMes.
	 * @param ctrl GestionEvtsInterf
	 */
	public CmdImpl_marqueMes(GestionEvtsInterf ctrl) {
		this.controleur = ctrl;
	}
	
	/**
	 * Method executer.
	 * @return Boolean
	 * @see moteur.CommandInterf#executer()
	 */
	@Override
	public Boolean executer() {

		controleur.marquerMesure();

		return true;
	}

}

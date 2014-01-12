package presentation;

import moteur.CommandInterf;

/**
 */
public class CmdImpl_lireMat implements CommandInterf{

	private AdaptateurInterf ihm;
	
	/**
	 * Constructor for CmdImpl_lireMat.
	 * @param ihmReelle AdaptateurInterf
	 */
	public CmdImpl_lireMat(AdaptateurInterf ihmReelle){
		
		this.ihm = ihmReelle;
	}
	
	/**
	 * Method executer.
	 * @return Boolean
	 * @see moteur.CommandInterf#executer()
	 */
	@Override
	public Boolean executer() {
		
		ihm.lireEtatIhmReelle();
		
		return true;
	}

}

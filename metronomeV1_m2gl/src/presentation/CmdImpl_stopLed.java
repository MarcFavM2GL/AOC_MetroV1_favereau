package presentation;

import moteur.CommandInterf;

/**
 * @author Favereau
 * @version $Revision: 1.0 $
 */
public class CmdImpl_stopLed implements CommandInterf{

	private IHMImple ihm;
	
	/**
	 * Constructor for CmdImpl_stopLed.
	 * @param ihm IHMImple
	 */
	public CmdImpl_stopLed(IHMImple ihm) {
		this.ihm = ihm;
	}
	
	/**
	 * Method executer.
	
	
	 * @return Boolean * @see moteur.CommandInterf#executer() */
	@Override
	public Boolean executer() {

		ihm.eteindreLesLeds();
		
		return true;
	}

}

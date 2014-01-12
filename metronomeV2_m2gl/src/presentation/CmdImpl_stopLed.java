package presentation;

import moteur.CommandInterf;

/**
 */
public class CmdImpl_stopLed implements CommandInterf{

	private AdaptateurImple ihm;
	
	/**
	 * Constructor for CmdImpl_stopLed.
	 * @param ihm AdaptateurImple
	 */
	public CmdImpl_stopLed(AdaptateurImple ihm) {
		this.ihm = ihm;
	}
	
	/**
	 * Method executer.
	 * @return Boolean
	 * @see moteur.CommandInterf#executer()
	 */
	@Override
	public Boolean executer() {

		ihm.eteindreLesLeds();
		
		return true;
	}

}

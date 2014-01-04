package presentation;

import moteur.CommandInterf;

public class CmdImpl_stopLed implements CommandInterf{

	private IHMImple ihm;
	
	public CmdImpl_stopLed(IHMImple ihm) {
		this.ihm = ihm;
	}
	
	@Override
	public Boolean executer() {

		ihm.eteindreLesLeds();
		
		return true;
	}

}

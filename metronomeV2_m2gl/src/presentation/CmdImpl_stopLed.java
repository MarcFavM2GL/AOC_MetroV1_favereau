package presentation;

import moteur.CommandInterf;

public class CmdImpl_stopLed implements CommandInterf{

	private AdaptateurImple ihm;
	
	public CmdImpl_stopLed(AdaptateurImple ihm) {
		this.ihm = ihm;
	}
	
	@Override
	public Boolean executer() {

		ihm.eteindreLesLeds();
		
		return true;
	}

}

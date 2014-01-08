package presentation;

import moteur.CommandInterf;

public class CmdImpl_lireMat implements CommandInterf{

	private AdaptateurInterf ihm;
	
	public CmdImpl_lireMat(AdaptateurInterf ihmReelle){
		
		this.ihm = ihmReelle;
	}
	
	@Override
	public Boolean executer() {
		
		ihm.lireEtatIhmReelle();
		
		return true;
	}

}

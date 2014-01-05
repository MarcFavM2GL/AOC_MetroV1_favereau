package moteur;

public class CmdImp_topSynchro implements CommandInterf {

	private MoteurMetronomeImple moteur;
	
	public CmdImp_topSynchro(MoteurMetronomeImple receiver) {
		this.moteur = receiver;
	}


	@Override
	public Boolean executer() {

		moteur.marquagePeriodique();
		
		return true;
		
	}

}

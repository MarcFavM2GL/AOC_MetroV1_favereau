package moteur;

/**
 * @author Favereau
 * @version $Revision: 1.0 $
 */
public class CmdImp_topSynchro implements CommandInterf {

	private MoteurMetronomeImple moteur;
	
	/**
	 * Constructor for CmdImp_topSynchro.
	 * @param receiver MoteurMetronomeImple
	 */
	public CmdImp_topSynchro(MoteurMetronomeImple receiver) {
		this.moteur = receiver;
	}


	/**
	 * Method executer.
	
	
	 * @return Boolean * @see moteur.CommandInterf#executer() */
	@Override
	public Boolean executer() {

		moteur.marquagePeriodique();
		
		return true;
		
	}

}

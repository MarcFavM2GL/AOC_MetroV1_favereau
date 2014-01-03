package moteur;

public class CommandTimerImple implements CommandTimerInterf {

	private MoteurMetronomeImple receiver;
	
	public CommandTimerImple(MoteurMetronomeImple receiver) {
		this.receiver = receiver;
	}


	@Override
	public Boolean executer() {

		return receiver.marquagePeriodique();
		
	}

}

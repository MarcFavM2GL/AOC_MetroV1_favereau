package outillageExterne;

import java.util.Timer;
import java.util.TimerTask;
import moteur.CommandInterf;

public class HorlogeImple implements HorlogeInterf {

	private CommandInterf cmdPeriodique;	
	private Timer horlogeTimerPeriod;
	
	@Override
	public void activerPeriodiquement(CommandInterf cmd, Float periodeSec) {
		
		
		
		this.cmdPeriodique = cmd;
		TimerTask maTache = new TimerTask() {
			@Override
			public void run() {
				Boolean retourAction;
				
				retourAction = cmdPeriodique.executer();
			}
		};
		
		horlogeTimerPeriod = new Timer();
		horlogeTimerPeriod.schedule(maTache, 0, ((long) (periodeSec * 1000)));
	}

	@Override
	public void activerApresDelai(CommandInterf cmd, Float delaiMilliSec) {
		

	}

	@Override
	public void arretHorloge() {
		
		horlogeTimerPeriod.cancel();
	}

}

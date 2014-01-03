package outillageExterne;

import java.util.Timer;
import java.util.TimerTask;
import moteur.CommandTimerInterf;

public class HorlogeImpl implements Horloge {

	private CommandTimerInterf cmdPeriodique;	
	private Timer horlogeTimerPeriod;
	
	@Override
	public void activerPeriodiquement(CommandTimerInterf cmd, Float periodeSec) {
		
		
		
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
	public void activerApresDelai(CommandTimerInterf cmd, Float delaiMilliSec) {
		

	}

	@Override
	public void arretHorloge() {
		
		horlogeTimerPeriod.cancel();
	}

}

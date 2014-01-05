package outillageExterne;

import java.util.Timer;
import java.util.TimerTask;
import moteur.CommandInterf;

public class HorlogeImple implements HorlogeInterf {

	private CommandInterf cmdPeriodique;	
	private Timer horlogeTimerPeriod;
	
	private CommandInterf cmdStopLed;	
	
	@Override
	public void activerPeriodiquement(CommandInterf cmd, float periodeSec) {
		
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
	public void activerApresDelai(CommandInterf cmd, float delaiMilliSec) {
		
		Timer horlogeDelai;
		this.cmdStopLed = cmd;
		TimerTask maTache = new TimerTask() {
			@Override
			public void run() {
				Boolean retourAction;
				
				retourAction = cmdStopLed.executer();
			}
		};
		
		horlogeDelai = new Timer();
		horlogeDelai.schedule(maTache, (long)delaiMilliSec);

	}

	@Override
	public void arretHorloge() {
		
		if(horlogeTimerPeriod != null){
			horlogeTimerPeriod.cancel();
		}
	}

}

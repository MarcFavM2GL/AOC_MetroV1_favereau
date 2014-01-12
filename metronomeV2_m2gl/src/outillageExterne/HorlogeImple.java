package outillageExterne;

import java.util.Timer;
import java.util.TimerTask;
import moteur.CommandInterf;

/**
 */
public class HorlogeImple implements HorlogeInterf {

	private CommandInterf cmdPeriodique;	
	private Timer horlogeTimerPeriod;
	
	private CommandInterf cmdStopLed;	
	
	/**
	 * Method activerPeriodiquement.
	 * @param cmd CommandInterf
	 * @param periodeSec float
	 * @see outillageExterne.HorlogeInterf#activerPeriodiquement(CommandInterf, float)
	 */
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

	/**
	 * Method activerApresDelai.
	 * @param cmd CommandInterf
	 * @param delaiMilliSec float
	 * @see outillageExterne.HorlogeInterf#activerApresDelai(CommandInterf, float)
	 */
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

	/**
	 * Method arretHorloge.
	 * @see outillageExterne.HorlogeInterf#arretHorloge()
	 */
	@Override
	public void arretHorloge() {
		
		if(horlogeTimerPeriod != null){
			horlogeTimerPeriod.cancel();
		}
	}

}

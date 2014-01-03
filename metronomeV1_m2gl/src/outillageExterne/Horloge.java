package outillageExterne;

import moteur.CommandTimerInterf;


/**
 * @author Favereau
 *	
 * @version 1.00
 * 
 * @category Interface
 * 
 */

public interface Horloge {
	
	public void activerPeriodiquement(CommandTimerInterf cmd, Float periodeSec);
	public void activerApresDelai(CommandTimerInterf cmd, Float delaiMilliSec);
	public void arretHorloge();
}

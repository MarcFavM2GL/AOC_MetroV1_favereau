package outillageExterne;

import moteur.CommandInterf;


/**
 * @author Favereau
 *	
 * @version 1.00
 * 
 * @category Interface
 * 
 */

public interface HorlogeInterf {
	
	public void activerPeriodiquement(CommandInterf cmd, Float periodeSec);
	public void activerApresDelai(CommandInterf cmd, Float delaiMilliSec);
	public void arretHorloge();
}

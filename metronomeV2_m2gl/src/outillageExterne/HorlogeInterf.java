package outillageExterne;

import moteur.CommandInterf;


/**
 * @author Favereau
 *	
 * @version 1.00
 * 

 * 
 */

public interface HorlogeInterf {
	
	/**
	 * Method activerPeriodiquement.
	 * @param cmd CommandInterf
	 * @param periodeSec float
	 */
	public void activerPeriodiquement(CommandInterf cmd, float periodeSec);
	/**
	 * Method activerApresDelai.
	 * @param cmd CommandInterf
	 * @param delaiMilliSec float
	 */
	public void activerApresDelai(CommandInterf cmd, float delaiMilliSec);
	public void arretHorloge();
}

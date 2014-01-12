package moteur;

/**
 */
public interface MoteurMetronomeInterf {

	/**
	 * Method setEnMarche.
	 * @param val Boolean
	 */
	public void setEnMarche(Boolean val);
	/**
	 * Method getEnMarche.
	 * @return Boolean
	 */
	public Boolean getEnMarche();
	
	/**
	 * Method setNbTempsParMesure.
	 * @param nbTemps Integer
	 */
	public void setNbTempsParMesure(Integer nbTemps);
	/**
	 * Method getNbTempsParMesure.
	 * @return Integer
	 */
	public Integer getNbTempsParMesure();
	
	/**
	 * Method setTempo.
	 * @param tempoBpm Integer
	 */
	public void setTempo(Integer tempoBpm);
	/**
	 * Method getTempo.
	 * @return Integer
	 */
	public Integer getTempo();
	
	/**
	 * Method marquagePeriodique.
	 * @return boolean
	 */
	public boolean marquagePeriodique();
	
	/**
	 * Method setCommandesCtrl.
	 * @param cmdTemps CommandInterf
	 * @param cmdMesure CommandInterf
	 */
	public void setCommandesCtrl(CommandInterf cmdTemps, CommandInterf cmdMesure);
	
}

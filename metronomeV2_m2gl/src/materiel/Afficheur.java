package materiel;

/**
 */
public interface Afficheur {
	/**
	 * Method allumeLed.
	 * @param numLed int
	 */
	void allumeLed(int numLed);
	/**
	 * Method eteindreLed.
	 * @param numLed int
	 */
	void eteindreLed(int numLed);
	
	/**
	 * Method afficherTempo.
	 * @param valeurTempo int
	 */
	void afficherTempo(int valeurTempo);
}

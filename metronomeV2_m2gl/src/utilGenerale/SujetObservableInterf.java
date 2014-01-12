package utilGenerale;

/**
 */
public interface SujetObservableInterf {

	/**
	 * Method ajoutObservateur.
	 * @param obs ObservateurInterf
	 */
	public void ajoutObservateur(ObservateurInterf obs);
	/**
	 * Method suppObservateur.
	 * @param obs ObservateurInterf
	 */
	public void suppObservateur(ObservateurInterf obs);
	public void notification();
}

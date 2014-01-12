package utilGenerale;

/**
 * @author Favereau
 * @version $Revision: 1.0 $
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

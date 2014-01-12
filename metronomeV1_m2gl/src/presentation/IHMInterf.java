package presentation;

/**
 * @author Favereau
 * @version $Revision: 1.0 $
 */
public interface IHMInterf {

	/**
	 * Method flasherLed1.
	 * @param tpsMilliSec float
	 */
	void flasherLed1(float tpsMilliSec);
	/**
	 * Method flasherLed2.
	 * @param tpsMilliSec float
	 */
	void flasherLed2(float tpsMilliSec);
	void emettreSon();
	
	/**
	 * Method getPositionMolette.
	
	 * @return Integer */
	Integer getPositionMolette();
	/**
	 * Method setPositionMolette.
	 * @param position Integer
	 */
	void setPositionMolette(Integer position);
	/**
	 * Method setInfosTempo.
	 * @param position Integer
	 */
	void setInfosTempo(Integer position);
	
	/**
	 * Method getMarche.
	
	 * @return Boolean */
	Boolean getMarche();
	/**
	 * Method setMarche.
	 * @param val Boolean
	 */
	void setMarche(Boolean val);
	
	/**
	 * Method getTempsParMesure.
	
	 * @return Integer */
	Integer getTempsParMesure();
	/**
	 * Method setTempsParMesure.
	 * @param val Integer
	 */
	void setTempsParMesure(Integer val);
}

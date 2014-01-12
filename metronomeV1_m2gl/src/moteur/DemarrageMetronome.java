package moteur;

/**
 * @author Favereau
 * @version $Revision: 1.0 $
 */
public class DemarrageMetronome {

	/**
	 * Method main. 
	 * @param args String[] non utilisés
	 * @throws InterruptedException
	 */
	
	public static void main(String[] args) throws InterruptedException {

		System.out.println("DEMARRAGE !!!");
		
		MoteurMetronomeInterf moteur = new MoteurMetronomeImple();

	}
}

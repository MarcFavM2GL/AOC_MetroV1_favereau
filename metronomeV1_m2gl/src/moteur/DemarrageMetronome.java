package moteur;

public class DemarrageMetronome {

	public static void main(String[] args) throws InterruptedException {


		
		System.out.println("DEMARRAGE !!!");
		Thread.sleep(5000);
		MoteurMetronomeInterf moteur = new MoteurMetronomeImple();

		/*Thread.sleep(15000);
		System.out.println("ARRET !!!");
		moteur.setEnMarche(false);

		Thread.sleep(5000);
		System.out.println("REDEMARRAGE 1");
		moteur.setEnMarche(true);
		
		Thread.sleep(5000);
		System.out.println("REDEMARRAGE 2");
		moteur.setEnMarche(true);
		
		Thread.sleep(15000);
		System.out.println("ARRET !!!");
		moteur.setEnMarche(false);
		
		Thread.sleep(30000);*/
		
		
	}

}

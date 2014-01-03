package moteur;

public class DemarrageMetronome {

	public static void main(String[] args) throws InterruptedException {


		
		System.out.println("DEMARRAGE !!!");
		Thread.sleep(5000);
		MoteurMetronomeInterf moteur = new MoteurMetronomeImple();
		System.out.println("moteur.setEnMarche(true)");
		
		
	}

}

package TestCaseJUnit;

import static org.junit.Assert.*;
import moteur.MoteurMetronomeImple;
import moteur.MoteurMetronomeInterf;


import org.junit.Test;

public class TestDuMoteur {

	@Test
	public void test() {
		MoteurMetronomeInterf moteur = new MoteurMetronomeImple();

		assertEquals(moteur.getEnMarche(), false);
		assertEquals(moteur.getNbTempsParMesure(), (Integer) 4);
		assertEquals(moteur.getTempo(), (Integer) 30);
		
		moteur.setEnMarche(true);
		assertEquals(moteur.getEnMarche(), true);
		
		moteur.setNbTempsParMesure(6);
		assertEquals(moteur.getNbTempsParMesure(), (Integer) 6);
		moteur.setNbTempsParMesure(0);
		assertEquals(moteur.getNbTempsParMesure(), (Integer) 2);
		moteur.setNbTempsParMesure(10);
		assertEquals(moteur.getNbTempsParMesure(), (Integer) 7);
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}

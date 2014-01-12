package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import outillageExterne.HorlogeImple;
import outillageExterne.HorlogeInterf;
import materiel.Materiel;
import moteur.CommandInterf;
import utilGenerale.ObservateurInterf;
import utilGenerale.SujetObservableInterf;
import controleur.ControleurMetronome;

/**
 */
public class AdaptateurImple implements AdaptateurInterf, SujetObservableInterf {

	private final float PERIODE_LECTURE_IHM = (float) 0.1;
		
	private ControleurMetronome monControleur;
	private Boolean estEnMarche = false;
	private Integer nbTemps = 0;
	private int tempo = 0;
	
	private List<ObservateurInterf> observateurs = new ArrayList<ObservateurInterf>();
	
	CommandInterf eteindreLed1;
	CommandInterf eteindreLed2;
	CommandInterf lireEtatMateriel;
	HorlogeInterf horloge;
	
	Materiel materielReel;
	
	/**
	 * Constructor for AdaptateurImple.
	 * @param ctrl ControleurMetronome
	 */
	public AdaptateurImple(ControleurMetronome ctrl) {
		
		super();
		this.monControleur = ctrl;
		
		materielReel = new Materiel();
		
		horloge = new HorlogeImple();
		
		eteindreLed1 = new CmdImpl_stopLed(this);
		eteindreLed2 = new CmdImpl_stopLed(this);
		
		lireEtatMateriel = new CmdImpl_lireMat(this);
		
		horloge.activerPeriodiquement(lireEtatMateriel, PERIODE_LECTURE_IHM);
	}
	
	
	/**
	 * Method flasherLed1.
	 * @param tpsMilliSec float
	 * @see presentation.AdaptateurInterf#flasherLed1(float)
	 */
	@Override
	public void flasherLed1(float tpsMilliSec) {
		
		Materiel.getAfficheur().allumeLed(1);
		horloge.activerApresDelai(eteindreLed1, tpsMilliSec);
	}

	/**
	 * Method flasherLed2.
	 * @param tpsMilliSec float
	 * @see presentation.AdaptateurInterf#flasherLed2(float)
	 */
	@Override
	public void flasherLed2(float tpsMilliSec) {
		
		emettreSon();
		Materiel.getAfficheur().allumeLed(2);
		horloge.activerApresDelai(eteindreLed2, tpsMilliSec);
	}

	/**
	 * Method emettreSon.
	 * @see presentation.AdaptateurInterf#emettreSon()
	 */
	@Override
	public void emettreSon() {
		Materiel.getEmetteurSonore().emettreClic();
	}

	/**
	 * Method getPositionMolette.
	 * @return Integer
	 * @see presentation.AdaptateurInterf#getPositionMolette()
	 */
	@Override
	public Integer getPositionMolette() {
		
		return tempo;
	}

	/**
	 * Method setPositionMolette.
	 * @param position Integer
	 * @see presentation.AdaptateurInterf#setPositionMolette(Integer)
	 */
	@Override
	public void setPositionMolette(Integer position) {
		
		tempo = position;
		Materiel.getAfficheur().afficherTempo(position);
		
		System.out.println("En mode 'Matériel Réel' la modification de la molette");
		System.out.println("par le logiciel est IMPOSSIBLE !!!");
	}

	/**
	 * Method getMarche.
	 * @return Boolean
	 * @see presentation.AdaptateurInterf#getMarche()
	 */
	@Override
	public Boolean getMarche() {
		
		return estEnMarche;
	}

	/**
	 * Method setMarche.
	 * @param val Boolean
	 * @see presentation.AdaptateurInterf#setMarche(Boolean)
	 */
	@Override
	public void setMarche(Boolean val) {
		
		estEnMarche = val;
		
		System.out.println("En mode 'Matériel Réel' la modification de l'état des boutons");
		System.out.println("START/STOP par le logiciel est IMPOSSIBLE !!!");
	}

	/**
	 * Method getTempsParMesure.
	 * @return Integer
	 * @see presentation.AdaptateurInterf#getTempsParMesure()
	 */
	@Override
	public Integer getTempsParMesure() {

		return nbTemps;
	}

	/**
	 * Method setTempsParMesure.
	 * @param val Integer
	 * @see presentation.AdaptateurInterf#setTempsParMesure(Integer)
	 */
	@Override
	public void setTempsParMesure(Integer val) {
		
		nbTemps = val;
		
		System.out.println("En mode 'Matériel Réel' la modification de l'état des boutons");
		System.out.println("INCR/DECR par le logiciel est IMPOSSIBLE !!!");
	}
	
	/**
	 * Method setInfosTempo.
	 * @param position Integer
	 * @see presentation.AdaptateurInterf#setInfosTempo(Integer)
	 */
	@Override
	public void setInfosTempo(Integer position) {

	}
	
	
	/**
	 * Method ajoutObservateur.
	 * @param obs ObservateurInterf
	 * @see utilGenerale.SujetObservableInterf#ajoutObservateur(ObservateurInterf)
	 */
	@Override
	public void ajoutObservateur(ObservateurInterf obs) {
		
		observateurs.add(obs);
	}

	/**
	 * Method suppObservateur.
	 * @param obs ObservateurInterf
	 * @see utilGenerale.SujetObservableInterf#suppObservateur(ObservateurInterf)
	 */
	@Override
	public void suppObservateur(ObservateurInterf obs) {

		observateurs.remove(obs);
	}

	/**
	 * Method notification.
	 * @see utilGenerale.SujetObservableInterf#notification()
	 */
	@Override
	public void notification() {
		
		for(ObservateurInterf observ:observateurs){
			observ.actualiseModifIHM();
		}
	}

	public void eteindreLesLeds() {

		Materiel.getAfficheur().eteindreLed(1);
		Materiel.getAfficheur().eteindreLed(2);
	}


	/**
	 * Method lireEtatIhmReelle.
	 * @see presentation.AdaptateurInterf#lireEtatIhmReelle()
	 */
	@Override
	public void lireEtatIhmReelle() {
		Integer tempoTmp;
		
		if(Materiel.getClavier().touchePressee(1) && !estEnMarche){
			estEnMarche = true;
			notification();
		}
		if(Materiel.getClavier().touchePressee(2) && estEnMarche){
			estEnMarche = false;
			notification();
		}
		if(Materiel.getClavier().touchePressee(3)){
			nbTemps++;
			notification();
		}
		if(Materiel.getClavier().touchePressee(4)){
			nbTemps--;
			notification();
		}
		if(Materiel.getMolette().position() != tempo){
			tempo = Materiel.getMolette().position();
			Materiel.getAfficheur().afficherTempo(tempo);
			notification();
		}	
	}
}

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
	
	
	@Override
	public void flasherLed1(float tpsMilliSec) {
		
		Materiel.getAfficheur().allumeLed(1);
		horloge.activerApresDelai(eteindreLed1, tpsMilliSec);
	}

	@Override
	public void flasherLed2(float tpsMilliSec) {
		
		emettreSon();
		Materiel.getAfficheur().allumeLed(2);
		horloge.activerApresDelai(eteindreLed2, tpsMilliSec);
	}

	@Override
	public void emettreSon() {
		Materiel.getEmetteurSonore().emettreClic();
	}

	@Override
	public Integer getPositionMolette() {
		
		return tempo;
	}

	@Override
	public void setPositionMolette(Integer position) {
		
		tempo = position;
		Materiel.getAfficheur().afficherTempo(position);
		
		System.out.println("En mode 'Matériel Réel' la modification de la molette");
		System.out.println("par le logiciel est IMPOSSIBLE !!!");
	}

	@Override
	public Boolean getMarche() {
		
		return estEnMarche;
	}

	@Override
	public void setMarche(Boolean val) {
		
		estEnMarche = val;
		
		System.out.println("En mode 'Matériel Réel' la modification de l'état des boutons");
		System.out.println("START/STOP par le logiciel est IMPOSSIBLE !!!");
	}

	@Override
	public Integer getTempsParMesure() {

		return nbTemps;
	}

	@Override
	public void setTempsParMesure(Integer val) {
		
		nbTemps = val;
		
		System.out.println("En mode 'Matériel Réel' la modification de l'état des boutons");
		System.out.println("INCR/DECR par le logiciel est IMPOSSIBLE !!!");
	}
	
	@Override
	public void setInfosTempo(Integer position) {

	}
	
	
	@Override
	public void ajoutObservateur(ObservateurInterf obs) {
		
		observateurs.add(obs);
	}

	@Override
	public void suppObservateur(ObservateurInterf obs) {

		observateurs.remove(obs);
	}

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

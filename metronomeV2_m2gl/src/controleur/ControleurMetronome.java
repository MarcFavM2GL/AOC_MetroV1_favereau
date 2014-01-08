package controleur;

import presentation.AdaptateurImple;
import utilGenerale.ObservateurInterf;
import moteur.CommandInterf;
import moteur.MoteurMetronomeImple;
import moteur.MoteurMetronomeInterf;

public class ControleurMetronome implements GestionEvtsInterf, ObservateurInterf {

	MoteurMetronomeImple moteur;
	AdaptateurImple presentation;
	private float dureeAllumageLed = 300; 	// en ms
	
	public ControleurMetronome(MoteurMetronomeImple mot) {

		this.moteur = mot;
		
		CommandInterf cmdTemps = new CmdImpl_marqueTps(this);
		CommandInterf cmdMesure = new CmdImpl_marqueMes(this);
		
		this.moteur.setCommandesCtrl(cmdTemps, cmdMesure);
		this.presentation = new AdaptateurImple(this);
		
		this.presentation.ajoutObservateur(this);
		this.moteur.ajoutObservateur(this);		
	}
	
	@Override
	public Boolean marquerTemps() {

		// vers IHM flash Led1
		presentation.flasherLed1(dureeAllumageLed);
		
		return true;
	}

	public float getDureeAllumageLed() {
		return dureeAllumageLed;
	}

	public void setDureeAllumageLed(float dureeAllumageLed) {
		this.dureeAllumageLed = dureeAllumageLed;
	}

	@Override
	public Boolean marquerMesure() {

		// vers IHM flash Led2
		presentation.flasherLed2(dureeAllumageLed);
		
		return true;
	}

	public MoteurMetronomeInterf getMoteur(){
		return moteur;
	}


	@Override
	public void actualiseModifIHM() {
		Integer valTempo = presentation.getPositionMolette();
		if(valTempo.compareTo(moteur.getTempo()) != 0){
			moteur.setTempo(valTempo);
		}
				
		Integer valMesure = presentation.getTempsParMesure();
		if(valMesure.compareTo(moteur.getNbTempsParMesure()) != 0){
			moteur.setNbTempsParMesure(valMesure);
		}
		
		Boolean valMarche = presentation.getMarche();
		moteur.setEnMarche(valMarche);
		
	}

	@Override
	public void actualiseModifMM() {
		
		Integer valTempo = moteur.getTempo();
		if(valTempo.compareTo(presentation.getPositionMolette()) != 0){
			presentation.setPositionMolette(valTempo);
		}
		presentation.setInfosTempo(valTempo);
		
		Integer valMesure = moteur.getNbTempsParMesure();
		if(valMesure.compareTo(presentation.getTempsParMesure()) != 0){
			presentation.setTempsParMesure(valMesure);
		}
		
	}
}

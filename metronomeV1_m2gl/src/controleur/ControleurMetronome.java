package controleur;

import presentation.IHMImple;
import utilGenerale.ObservateurInterf;
import moteur.CommandInterf;
import moteur.MoteurMetronomeInterf;

public class ControleurMetronome implements GestionEvtsInterf, ObservateurInterf {

	MoteurMetronomeInterf moteur;
	IHMImple presentation;
	
	public ControleurMetronome(MoteurMetronomeInterf mot) {

		this.moteur = mot;
		
		CommandInterf cmdTemps = new CmdImpl_marqueTps(this);
		CommandInterf cmdMesure = new CmdImpl_marqueMes(this);
		
		this.moteur.setCommandesCtrl(cmdTemps, cmdMesure);
		this.presentation = new IHMImple(this);
		this.presentation.ajoutObservateur(this);
		
	}
	
	@Override
	public Boolean marquerTemps() {

		// vers IHM flash Led1

		return true;
	}

	@Override
	public Boolean marquerMesure() {

		// vers IHM flash Led2
		
		return true;
	}

	public MoteurMetronomeInterf getMoteur(){
		return moteur;
	}

	@Override
	public void actualise() {
		Integer val = presentation.getPositionMolette();
		System.out.println("CONTROLEUR : Modif de la molette : " + val);
		
	}
}

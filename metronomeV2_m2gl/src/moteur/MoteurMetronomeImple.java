package moteur;

import java.util.ArrayList;
import java.util.List;

import controleur.ControleurMetronome;
import outillageExterne.HorlogeInterf;
import outillageExterne.HorlogeImple;
import utilGenerale.ObservateurInterf;
import utilGenerale.SujetObservableInterf;

/**
 */
public class MoteurMetronomeImple implements MoteurMetronomeInterf, SujetObservableInterf {

	private final int MESUREMAX = 7;
	private final int MESUREMIN = 2;
	
	private final int TEMPOMAX = 300;
	private final int TEMPOMIN = 20;
	
	private Boolean enMarche = false;
	private Integer nbTempsParMesure = 0; 	//de 2 a 7
	private Integer tempo = 0;				//en battements par minutes
	
	private int cmptLocalTemp = 0;
	
	HorlogeInterf horloge;
	private ControleurMetronome ctrl;
	
	private List<ObservateurInterf> observateurs = new ArrayList<ObservateurInterf>();
	
	CommandInterf commandePeriod;
	CommandInterf marqueTemps;
	CommandInterf marqueMes;
	
	
	
	public MoteurMetronomeImple(){
		System.out.println("Demarrage du moteur !!!");
		
		//init des valeurs par defaut
		//this.enMarche = true;
		//this.nbTempsParMesure = 4;
		//this.tempo = 30; // toute les 2 secondes
		
		ctrl = new ControleurMetronome(this);
		
		horloge = new HorlogeImple();
		commandePeriod = new CmdImp_topSynchro(this);
		
		//init des valeurs par defaut
		setNbTempsParMesure(4);
		setTempo(120);
		
		
		//horloge.activerPeriodiquement(commandePeriod, 60 / (float) this.tempo);
	}
	
	
	
	
	/**
	 * Method setEnMarche.
	 * @param val Boolean
	 * @see moteur.MoteurMetronomeInterf#setEnMarche(Boolean)
	 */
	@Override
	public void setEnMarche(Boolean val) {
		
		this.enMarche = val;
		if(this.enMarche){
			horloge.arretHorloge();
			horloge.activerPeriodiquement(commandePeriod, 60 / (float) this.tempo);
		}else{
			horloge.arretHorloge();
		}
	}

	/**
	 * Method getEnMarche.
	 * @return Boolean
	 * @see moteur.MoteurMetronomeInterf#getEnMarche()
	 */
	@Override
	public Boolean getEnMarche() {
		
		return this.enMarche;
	}

	/**
	 * Method setNbTempsParMesure.
	 * @param nbTemps Integer
	 * @see moteur.MoteurMetronomeInterf#setNbTempsParMesure(Integer)
	 */
	@Override
	public void setNbTempsParMesure(Integer nbTemps) {
			
			this.nbTempsParMesure = Math.max(Math.min(nbTemps, MESUREMAX),MESUREMIN);
			notification();
	}

	/**
	 * Method getNbTempsParMesure.
	 * @return Integer
	 * @see moteur.MoteurMetronomeInterf#getNbTempsParMesure()
	 */
	@Override
	public Integer getNbTempsParMesure() {
		
		return this.nbTempsParMesure;
	}

	/**
	 * Method setTempo.
	 * @param tempoBpm Integer
	 * @see moteur.MoteurMetronomeInterf#setTempo(Integer)
	 */
	@Override
	public void setTempo(Integer tempoBpm) {
		
		this.tempo = Math.max(Math.min(tempoBpm, TEMPOMAX),TEMPOMIN);
		
		if(this.enMarche){
			horloge.arretHorloge();
			horloge.activerPeriodiquement(commandePeriod, 60 / (float) this.tempo);
		}	
		notification();
	}

	/**
	 * Method getTempo.
	 * @return Integer
	 * @see moteur.MoteurMetronomeInterf#getTempo()
	 */
	@Override
	public Integer getTempo() {
		
		return this.tempo;
	}

	/**
	 * Method marquagePeriodique.
	 * @return boolean
	 * @see moteur.MoteurMetronomeInterf#marquagePeriodique()
	 */
	@Override
	public boolean marquagePeriodique() {
		
		
		this.marqueTemps.executer();
		
		cmptLocalTemp++;
		if(cmptLocalTemp % this.nbTempsParMesure == 0){
			this.marqueMes.executer();
		}
		
		return true;
	}




	/**
	 * Method setCommandesCtrl.
	 * @param cmdTemps CommandInterf
	 * @param cmdMesure CommandInterf
	 * @see moteur.MoteurMetronomeInterf#setCommandesCtrl(CommandInterf, CommandInterf)
	 */
	@Override
	public void setCommandesCtrl(CommandInterf cmdTemps, CommandInterf cmdMesure) {
		
		this.marqueTemps = cmdTemps;
		this.marqueMes = cmdMesure;
		
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
			observ.actualiseModifMM();
		}
	}

}

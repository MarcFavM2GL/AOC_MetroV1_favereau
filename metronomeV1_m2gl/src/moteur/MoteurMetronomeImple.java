package moteur;

import controleur.ControleurMetronome;
import outillageExterne.HorlogeInterf;
import outillageExterne.HorlogeImple;

public class MoteurMetronomeImple implements MoteurMetronomeInterf {

	private Boolean enMarche;
	private Integer nbTempsParMesure; 	//de 2 a 7
	private Integer tempo;				//en battements par minutes
	
	private int cmptLocalTemp = 0;
	
	HorlogeInterf horloge;
	private ControleurMetronome ctrl;
	
	CommandInterf commandePeriod;
	CommandInterf marqueTemps;
	CommandInterf marqueMes;
	
	
	public MoteurMetronomeImple(){
		System.out.println("Demarrage du moteur !!!");
		
		//init des valeurs par defaut
		this.enMarche = true;
		this.nbTempsParMesure = 4;
		this.tempo = 30; // toute les 2 secondes
		
		ctrl = new ControleurMetronome(this);
		
		horloge = new HorlogeImple();
		commandePeriod = new CmdImp_topSynchro(this);
		
		horloge.activerPeriodiquement(commandePeriod, 60 / (float) this.tempo);
	}
	
	
	
	
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

	@Override
	public Boolean getEnMarche() {
		
		return this.enMarche;
	}

	@Override
	public void setNbTempsParMesure(Integer nbTemps) {
		
		
		this.nbTempsParMesure = nbTemps;
	}

	@Override
	public Integer getNbTempsParMesure() {
		
		return this.nbTempsParMesure;
	}

	@Override
	public void setTempo(Integer tempoBpm) {
		
		this.tempo = tempoBpm;
	}

	@Override
	public Integer getTempo() {
		
		return this.tempo;
	}




	@Override
	public boolean marquagePeriodique() {
		
		System.out.print(" Tps... ");
		this.marqueTemps.executer();
		
		cmptLocalTemp++;
		if(cmptLocalTemp % this.nbTempsParMesure == 0){
			System.out.println(" et mesure !!!");
			this.marqueMes.executer();
		}
		
		return true;
	}




	@Override
	public void setCommandesCtrl(CommandInterf cmdTemps, CommandInterf cmdMesure) {
		
		this.marqueTemps = cmdTemps;
		this.marqueMes = cmdMesure;
		
	}

	
}

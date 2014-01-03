package moteur;

import outillageExterne.Horloge;
import outillageExterne.HorlogeImpl;

public class MoteurMetronomeImple implements MoteurMetronomeInterf {

	private Boolean enMarche;
	private Integer nbTempsParMesure; 	//de 2 a 7
	private Integer tempo;				//en battements par minutes
	
	private int cmptLocalTemp = 0;
	
	Horloge horloge;
	CommandTimerInterf commandePeriod;
	
	public MoteurMetronomeImple(){
		System.out.println("Demarrage du moteur !!!");
		
		//init des valeurs par defaut
		this.enMarche = false;
		this.nbTempsParMesure = 4;
		this.tempo = 30; // toute les 2 secondes
		
		horloge = new HorlogeImpl();
		commandePeriod = new CommandTimerImple(this);
		
		horloge.activerPeriodiquement(commandePeriod, 60 / (float) this.tempo);
	}
	
	
	
	
	@Override
	public void setEnMarche(Boolean val) {
		
		this.enMarche = val;
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
		
		System.out.print("Top...");
		cmptLocalTemp++;
		if(cmptLocalTemp % 5 == 0){
			System.out.println("  -5-");
		}
		
		return true;
	}

	
}

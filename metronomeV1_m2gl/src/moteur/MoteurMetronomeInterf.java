package moteur;

public interface MoteurMetronomeInterf {

	public void setEnMarche(Boolean val);
	public Boolean getEnMarche();
	
	public void setNbTempsParMesure(Integer nbTemps);
	public Integer getNbTempsParMesure();
	
	public void setTempo(Integer tempoBpm);
	public Integer getTempo();
	
	public boolean marquagePeriodique();
	
}

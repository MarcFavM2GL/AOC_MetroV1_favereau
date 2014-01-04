package presentation;

public interface IHMInterf {

	void flasherLed1(float tpsMilliSec);
	void flasherLed2(float tpsMilliSec);
	void emettreSon();
	
	Integer getPositionMolette();
	void setPositionMolette(Integer position);
	void setInfosTempo(Integer position);
	
	Boolean getMarche();
	void setMarche(Boolean val);
	
	Integer getTempsParMesure();
	void setTempsParMesure(Integer val);
}

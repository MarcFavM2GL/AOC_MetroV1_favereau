package presentation;

public interface IHMInterf {

	void flasherLed1(Integer tpsMilliSec);
	void flasherLed2(Integer tpsMilliSec);
	void emettreSon();
	
	Integer getPositionMolette();
	void setPositionMolette(Integer position);
}

package utilGenerale;

public interface SujetObservableInterf {

	public void ajoutObservateur(ObservateurInterf obs);
	public void suppObservateur(ObservateurInterf obs);
	public void notification();
}

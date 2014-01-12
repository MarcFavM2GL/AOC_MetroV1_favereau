package materiel;

import java.awt.*;

/**
 */
public class EmetteurSonoreImpl implements EmetteurSonore{

	/**
	 * Method emettreClic.
	 * @see materiel.EmetteurSonore#emettreClic()
	 */
	@Override
	public void emettreClic() {
		Toolkit.getDefaultToolkit().beep();
	}
}

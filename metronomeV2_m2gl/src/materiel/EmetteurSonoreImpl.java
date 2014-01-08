package materiel;

import java.awt.*;

public class EmetteurSonoreImpl implements EmetteurSonore{

	@Override
	public void emettreClic() {
		Toolkit.getDefaultToolkit().beep();
	}
}

package materiel;

import java.awt.Color;

import javax.swing.JSlider;

/**
 */
public class MoletteImpl extends JSlider implements Molette{

	public MoletteImpl(){
		
		super();
		setMaximum(500);
		setMinimum(1);
		setBackground(new Color(255, 170, 55));
	
	}
	
	
	/**
	 * Method position.
	 * @return int
	 * @see materiel.Molette#position()
	 */
	@Override
	public int position() {
		
		return getValue();
	}

}

package materiel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class AfficheurImpl extends JPanel implements Afficheur{

	JLabel led1, led2, afficheMolette;
	JPanel contTempo, contTemps;
	
	public AfficheurImpl() {
		
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(new Color(255, 170, 55));			
		Font fontLabel = new Font("Arial",Font.BOLD + Font.ITALIC ,20);
			
		led1 = new JLabel("     Temps     ");
		led1.setBackground(Color.green);
		led1.setOpaque(true);
		led1.setFont(fontLabel);
				
		led2 = new JLabel("     Mesure     ");
		led2.setBackground(Color.green);
		led2.setOpaque(true);
		led2.setFont(fontLabel);
			
		contTempo = new JPanel();
		Border bordureTempo = BorderFactory.createTitledBorder(
						BorderFactory.createLineBorder(Color.BLACK, 1), "Tempo");
		contTempo.setBorder(bordureTempo);
		afficheMolette = new JLabel("0");
		afficheMolette.setSize(90, 20);
		afficheMolette.setPreferredSize(afficheMolette.getSize());
		afficheMolette.setHorizontalAlignment(JLabel.CENTER);
		contTempo.setSize(100, 25);
		contTempo.setPreferredSize(contTempo.getSize());
		contTempo.add(afficheMolette);
		
		add(Box.createRigidArea(new Dimension(20, 70)));
		add(led1);
		add(Box.createRigidArea(new Dimension(20, 70)));
		add(led2);
		add(Box.createRigidArea(new Dimension(20, 70)));
		add(contTempo);
		add(Box.createRigidArea(new Dimension(20, 70)));
						
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void allumeLed(int numLed) {
		
		switch (numLed) {
		case 1:
			led1.setBackground(Color.red);
			break;
			
		case 2:
			led2.setBackground(Color.red);
			break;
			
		default:
			break;
		}	
	}

	@Override
	public void eteindreLed(int numLed) {

		switch (numLed) {
		case 1:
			led1.setBackground(Color.green);
			break;
			
		case 2:
			led2.setBackground(Color.green);
			break;
			
		default:
			break;
		}	
		
	}

	@Override
	public void afficherTempo(int valeurTempo) {

		afficheMolette.setText(Integer.toString(valeurTempo));
	}

}

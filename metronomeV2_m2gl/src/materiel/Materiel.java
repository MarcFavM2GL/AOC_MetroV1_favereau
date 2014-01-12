package materiel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/**
 */
public class Materiel extends JFrame {

	JPanel pnlTempo, pnlAffich, pnlClavier;
	
	static Molette molette;
	static Afficheur afficheur;
	static Clavier clavier;
	static EmetteurSonore emetteurSonore;
	
	public Materiel() {
		
		super();
				
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		getContentPane().setBackground(new Color(255, 170, 55));
		
		molette = new MoletteImpl();
		afficheur = new AfficheurImpl();
		clavier = new ClavierImpl();
		emetteurSonore = new EmetteurSonoreImpl();
		
		initIHM();
		
		pack();
		setLocation(50, 50);
		Dimension tailleFen = new Dimension(500, 350);
		setMinimumSize(tailleFen);
		setSize(tailleFen);
		setResizable(false);
		setVisible(true);
	}
	
	private void initIHM(){
		
		pnlTempo = new JPanel();
		Border bordureTempo = BorderFactory.createTitledBorder(
						BorderFactory.createLineBorder(Color.BLACK, 2), "Tempo (en BPM) : ");
		pnlTempo.setBorder(bordureTempo);
		pnlTempo.setLayout(new BoxLayout(pnlTempo, BoxLayout.Y_AXIS));
		pnlTempo.setSize(400, 70);
		pnlTempo.setPreferredSize(pnlTempo.getSize());
		pnlTempo.setBackground(getContentPane().getBackground());
		
		pnlTempo.add((Component)molette);
				
		pnlAffich = new JPanel();
		Border bordureMesureAff = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 2), "Afficheur : ");
		pnlAffich.setBorder(bordureMesureAff);
		pnlAffich.setLayout(new BoxLayout(pnlAffich, BoxLayout.X_AXIS));
		pnlAffich.setBackground(getContentPane().getBackground());
		
		pnlAffich.add((Component)afficheur);
		
		
		pnlClavier = new JPanel();
		pnlClavier.setLayout(new BoxLayout(pnlClavier, BoxLayout.X_AXIS));
		pnlClavier.setBackground(getContentPane().getBackground());
		pnlClavier.setSize(400, 120);
		pnlClavier.setPreferredSize(pnlClavier.getSize());
		pnlClavier.add((Component)clavier);
		
		getContentPane().add(pnlTempo);
		getContentPane().add(pnlAffich);
		getContentPane().add(pnlClavier);
		
	}
	
	/**
	 * Method getClavier.
	 * @return Clavier
	 */
	public static Clavier getClavier(){
		return clavier;
	}
	
	/**
	 * Method getAfficheur.
	 * @return Afficheur
	 */
	public static Afficheur getAfficheur(){
		return afficheur;
	}
	
	/**
	 * Method getEmetteurSonore.
	 * @return EmetteurSonore
	 */
	public static EmetteurSonore getEmetteurSonore(){
		return emetteurSonore;
	}
	
	/**
	 * Method getMolette.
	 * @return Molette
	 */
	public static Molette getMolette(){
		return molette;
	}
}
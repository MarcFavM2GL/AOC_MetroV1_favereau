package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import outillageExterne.HorlogeImple;
import outillageExterne.HorlogeInterf;
import moteur.CommandInterf;
import utilGenerale.ObservateurInterf;
import utilGenerale.SujetObservableInterf;
import controleur.ControleurMetronome;

/**
 * @author Favereau
 * @version $Revision: 1.0 $
 */
public class IHMImple extends JFrame implements IHMInterf, SujetObservableInterf {

	private final int TEMPOMAX = 500;
	private final int TEMPOMIN = 1;
	
	private ControleurMetronome monControleur;
	private Boolean estEnMarche = false;
	
	private List<ObservateurInterf> observateurs = new ArrayList<ObservateurInterf>();
	
	// Composants graphiques
	JPanel pnlTempo, pnlLeds, pnlMesure, pnlOnOff;
	
	JSlider molette;
	JLabel afficheMolette;
	
	JLabel led1, led2, espace;
	
	JButton btnPlus, btnMoins;
	JLabel infosMesure;
	
	JButton btnStart, btnStop;
	JLabel infosMiseEnMarche;
	
	CommandInterf eteindreLed1;
	CommandInterf eteindreLed2;
	HorlogeInterf horloge;
	
	/**
	 * Constructor for IHMImple.
	 * @param ctrl ControleurMetronome
	 */
	public IHMImple(ControleurMetronome ctrl) {
		
		super();
		this.monControleur = ctrl;
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		getContentPane().setBackground(new Color(255, 170, 55));
		
		initIHM();
		setMarche(false);
		pack();
		setLocation(50, 50);
		Dimension tailleFen = new Dimension(500, 350);
		setMinimumSize(tailleFen);
		setSize(tailleFen);
		setResizable(false);
		setVisible(true);
		
		horloge = new HorlogeImple();
		
		eteindreLed1 = new CmdImpl_stopLed(this);
		eteindreLed2 = new CmdImpl_stopLed(this);
	}
	
	private void initIHM(){
		
		// partie 1 : Tempo *******************************************************
		pnlTempo = new JPanel();
		Border bordureTempo = BorderFactory.createTitledBorder(
						BorderFactory.createLineBorder(Color.BLACK, 2), "Tempo (en BPM) :");
		pnlTempo.setBorder(bordureTempo);
		pnlTempo.setLayout(new BoxLayout(pnlTempo, BoxLayout.Y_AXIS));
		pnlTempo.setSize(400, 70);
		pnlTempo.setPreferredSize(pnlTempo.getSize());
		pnlTempo.setBackground(getContentPane().getBackground());
		
		molette = new JSlider();
		molette.setMaximum(TEMPOMAX);
		molette.setMinimum(TEMPOMIN);
		//molette.setValue(monControleur.getMoteur().getTempo());
		molette.setBackground(getContentPane().getBackground());
		molette.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				notification();
			}
		});
		
		pnlTempo.add(molette);
		
		afficheMolette = new JLabel(String.valueOf(molette.getValue()));
				
		pnlTempo.add(afficheMolette);
		
		getContentPane().add(pnlTempo);
		
		// FIN partie 1 : Tempo *******************************************************
			
		// partie 2 : Mesure *******************************************************
		pnlMesure = new JPanel();
		Border bordureMesure = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 2), "Nb de temps dans une mesure :");
		pnlMesure.setBorder(bordureMesure);
		pnlMesure.setLayout(new BoxLayout(pnlMesure, BoxLayout.X_AXIS));
		pnlMesure.setBackground(getContentPane().getBackground());
		
		btnMoins = new JButton("-"){
	        {
	            setSize(100, 30);
	            setMaximumSize(getSize());
	            setPreferredSize(getSize());
	        }
	    };
	    btnMoins.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.decode(infosMesure.getText());
				val--;
				infosMesure.setText(val.toString());
				notification();
			}
		});
	    
		infosMesure = new JLabel("5"){
			{
				setSize(50, 50);
				setMaximumSize(getSize());
				setPreferredSize(getSize());
			}
		};
		infosMesure.setOpaque(true);
		infosMesure.setBackground(getContentPane().getBackground());
		infosMesure.setHorizontalAlignment(JLabel.CENTER);
		//infosMesure.setText(monControleur.getMoteur().getNbTempsParMesure().toString());
		
		btnPlus = new JButton("+"){
	        {
	            setSize(100, 30);
	            setMaximumSize(getSize());
	            setPreferredSize(getSize());
	        }
	    };
	    btnPlus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.decode(infosMesure.getText());
				val++;
				infosMesure.setText(val.toString());
				notification();
			}
		});		
	    
		pnlMesure.add(btnMoins);
		pnlMesure.add(Box.createRigidArea(new Dimension(20, 0)));
		pnlMesure.add(infosMesure);
		pnlMesure.add(Box.createRigidArea(new Dimension(20, 0)));
		pnlMesure.add(btnPlus);
		
						
		getContentPane().add(pnlMesure);
					
		// FIN partie 2 : Mesure *******************************************************
		
		// partie 3 : Leds *******************************************************
		pnlLeds = new JPanel();
		
		pnlLeds.setLayout(new BoxLayout(pnlLeds, BoxLayout.X_AXIS));
		pnlLeds.setBackground(getContentPane().getBackground());
			
		Font fontLabel = new Font("Arial",Font.BOLD + Font.ITALIC ,20);
			
		led1 = new JLabel("     Temps     ");
		led1.setBackground(Color.green);
		led1.setOpaque(true);
		led1.setFont(fontLabel);
				
		led2 = new JLabel("     Mesure     ");
		led2.setBackground(Color.green);
		led2.setOpaque(true);
		led2.setFont(fontLabel);
						
		pnlLeds.add(led1);
		pnlLeds.add(Box.createRigidArea(new Dimension(40, 100)));
		pnlLeds.add(led2);
						
		getContentPane().add(pnlLeds);
						
		// FIN partie 3 : Leds *******************************************************
		
		// partie 4 : OnOff *******************************************************
		pnlOnOff = new JPanel();
		
		pnlOnOff.setLayout(new BoxLayout(pnlOnOff, BoxLayout.X_AXIS));
		pnlOnOff.setBackground(getContentPane().getBackground());
			
		btnStart = new JButton("Start"){
			{
				setSize(120, 30);
		        setMaximumSize(getSize());
		        setPreferredSize(getSize());
		    }
		};
		btnStart.addActionListener(new ActionListener() {
							
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStart.setEnabled(false);
				btnStop.setEnabled(true);
				estEnMarche = true;
				gestionVoyantOnOff();
				notification();
			}
		});
		
		
		infosMiseEnMarche = new JLabel();		
		infosMiseEnMarche.setOpaque(true);
		infosMiseEnMarche.setBackground(getContentPane().getBackground());
		infosMiseEnMarche.setHorizontalAlignment(JLabel.CENTER);
		
		btnStop = new JButton("Stop"){
			{
				setSize(120, 30);
		        setMaximumSize(getSize());
		        setPreferredSize(getSize());
		    }
		};
		btnStop.addActionListener(new ActionListener() {
							
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
				estEnMarche = false;
				gestionVoyantOnOff();
				notification();
			}
		});
		
		
		pnlOnOff.add(btnStart);
		pnlOnOff.add(Box.createRigidArea(new Dimension(10, 0)));
		pnlOnOff.add(infosMiseEnMarche);
		pnlOnOff.add(Box.createRigidArea(new Dimension(10, 0)));
		pnlOnOff.add(btnStop);
				
		getContentPane().add(pnlOnOff);
							
		// FIN partie 4 : OnOff *******************************************************
		
	}
	
	public void gestionVoyantOnOff(){
		
		Border bordureInfosOn = BorderFactory.createLineBorder(Color.green, 3);
		Border bordureInfosOff = BorderFactory.createLineBorder(Color.red, 3);
		
		if(estEnMarche){
			infosMiseEnMarche.setText("  ON  ");
			infosMiseEnMarche.setBorder(bordureInfosOn);
		}else{
			infosMiseEnMarche.setText("  OFF  ");
			infosMiseEnMarche.setBorder(bordureInfosOff);
		}
	}
	
	/**
	 * Method flasherLed1.
	 * @param tpsMilliSec float
	
	 * @see presentation.IHMInterf#flasherLed1(float) */
	@Override
	public void flasherLed1(float tpsMilliSec) {
		
		led1.setBackground(Color.red);
		horloge.activerApresDelai(eteindreLed1, tpsMilliSec);
	}

	/**
	 * Method flasherLed2.
	 * @param tpsMilliSec float
	
	 * @see presentation.IHMInterf#flasherLed2(float) */
	@Override
	public void flasherLed2(float tpsMilliSec) {
		
		emettreSon();
		led2.setBackground(Color.red);
		horloge.activerApresDelai(eteindreLed2, tpsMilliSec);
	}

	/**
	 * Method emettreSon.
	 * @see presentation.IHMInterf#emettreSon()
	 */
	@Override
	public void emettreSon() {
		java.awt.Toolkit.getDefaultToolkit().beep();
		
	}

	/**
	 * Method getPositionMolette.
	
	
	 * @return Integer * @see presentation.IHMInterf#getPositionMolette() */
	@Override
	public Integer getPositionMolette() {
		
		return molette.getValue();
	}

	/**
	 * Method setPositionMolette.
	 * @param position Integer
	
	 * @see presentation.IHMInterf#setPositionMolette(Integer) */
	@Override
	public void setPositionMolette(Integer position) {
		
		molette.setValue(position);
	}

	/**
	 * Method getMarche.
	
	
	 * @return Boolean * @see presentation.IHMInterf#getMarche() */
	@Override
	public Boolean getMarche() {
		
		return estEnMarche;
	}

	/**
	 * Method setMarche.
	 * @param val Boolean
	
	 * @see presentation.IHMInterf#setMarche(Boolean) */
	@Override
	public void setMarche(Boolean val) {
		
		if(val){
			btnStart.doClick();
		}else{
			btnStop.doClick();
		}
	}

	/**
	 * Method getTempsParMesure.
	
	
	 * @return Integer * @see presentation.IHMInterf#getTempsParMesure() */
	@Override
	public Integer getTempsParMesure() {

		return Integer.decode(infosMesure.getText());
	}

	/**
	 * Method setTempsParMesure.
	 * @param val Integer
	
	 * @see presentation.IHMInterf#setTempsParMesure(Integer) */
	@Override
	public void setTempsParMesure(Integer val) {
		
		infosMesure.setText(val.toString());
	}
	
	/**
	 * Method setInfosTempo.
	 * @param position Integer
	
	 * @see presentation.IHMInterf#setInfosTempo(Integer) */
	@Override
	public void setInfosTempo(Integer position) {

		afficheMolette.setText(position.toString());
	}
	
	
	/**
	 * Method ajoutObservateur.
	 * @param obs ObservateurInterf
	
	 * @see utilGenerale.SujetObservableInterf#ajoutObservateur(ObservateurInterf) */
	@Override
	public void ajoutObservateur(ObservateurInterf obs) {
		
		observateurs.add(obs);
	}

	/**
	 * Method suppObservateur.
	 * @param obs ObservateurInterf
	
	 * @see utilGenerale.SujetObservableInterf#suppObservateur(ObservateurInterf) */
	@Override
	public void suppObservateur(ObservateurInterf obs) {

		observateurs.remove(obs);
	}

	/**
	 * Method notification.
	 * @see utilGenerale.SujetObservableInterf#notification()
	 */
	@Override
	public void notification() {
		
		for(ObservateurInterf observ:observateurs){
			observ.actualiseModifIHM();
		}
	}

	public void eteindreLesLeds() {

		led1.setBackground(Color.green);
		led2.setBackground(Color.green);
	}

}

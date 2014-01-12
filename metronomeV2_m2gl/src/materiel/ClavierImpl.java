package materiel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 */
public class ClavierImpl extends JPanel implements Clavier {

	JButton btnPlus, btnMoins, btnStart, btnStop;
	boolean clicPlus, clicMoins, clicStart, clicStop;
	
	public ClavierImpl() {
		super();
		
		clicPlus = false;
		clicMoins = false;
		clicStart = false;
		clicStop = false;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(new Color(255, 170, 55));			
		
		btnMoins = new JButton("Decr."){
	        {
	            setSize(80, 30);
	            setMaximumSize(getSize());
	            setPreferredSize(getSize());
	        }
	    };
	    btnMoins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clicMoins = true;
			}
		});
	    
	    btnPlus = new JButton("Incr."){
	        {
	            setSize(80, 30);
	            setMaximumSize(getSize());
	            setPreferredSize(getSize());
	        }
	    };
	    btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clicPlus = true;
			}
		});
		
	    btnStart = new JButton("Start"){
	        {
	            setSize(100, 30);
	            setMaximumSize(getSize());
	            setPreferredSize(getSize());
	        }
	    };
	    btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clicStart = true;
			}
		});
	    
	    btnStop = new JButton("Stop"){
	        {
	            setSize(100, 30);
	            setMaximumSize(getSize());
	            setPreferredSize(getSize());
	        }
	    };
	    btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clicStop = true;
			}
		});
	    
	    
		add(btnMoins);
		add(Box.createRigidArea(new Dimension(20, 0)));
		add(btnPlus);
		add(Box.createRigidArea(new Dimension(50, 0)));
		add(btnStart);
		add(Box.createRigidArea(new Dimension(20, 0)));
		add(btnStop);
		
	}
	
	
	
	/**
	 * Method touchePressee.
	 * @param i int
	 * @return boolean
	 * @see materiel.Clavier#touchePressee(int)
	 */
	@Override
	public boolean touchePressee(int i) {
		// #1 >>> START
		// #2 >>> STOP
		// #3 >>> INCR
		// #4 >>> DECR
		boolean retour = false;
		
		switch (i) {
		case 1:
			retour = clicStart;
			clicStart = false;
			break;
		case 2:
			retour = clicStop;
			clicStop = false;
			break;
		case 3:
			retour = clicPlus;
			clicPlus = false;
			break;
		case 4:
			retour = clicMoins;
			clicMoins = false;
			break;
		default:
			break;
		}
		
		return retour;
	}
}

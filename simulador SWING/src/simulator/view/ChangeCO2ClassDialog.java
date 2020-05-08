package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;



public class ChangeCO2ClassDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private JPanel pnlCO2 = new JPanel();
	private JSpinner tic= new JSpinner();
	
	public ChangeCO2ClassDialog(){
		super (new JFrame(), "CO2class", true);
		initGUI();
		
		
	}


	private void initGUI() {
		
		//panel principal
		pnlCO2.setLayout(new BorderLayout());
		pnlCO2.setPreferredSize(new Dimension(350,75));
		
		
		JLabel la = new JLabel ("<html>Schedule an event to change the CO2 class os "
				+ "a vehicle after a given number os simulation ticks from now </html>");
		
		pnlCO2.add(la, BorderLayout.NORTH);
		
		
		JLabel ve= new JLabel ("vehicle: ");
		JLabel co= new JLabel ("CO2 Class; ");
		JLabel ti= new JLabel("Ticks: ");
		tic= new JSpinner (new SpinnerNumberModel(0,0,5,1));
		
		pnlCO2.add(ve);
		pnlCO2.add(co);
		pnlCO2.add(ti);
		pnlCO2.add(tic);
		
		
		
		pnlCO2.setVisible(true);
		this.add(pnlCO2);
	}
	
	
	
	
}

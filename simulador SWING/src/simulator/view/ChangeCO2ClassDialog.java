package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;



public class ChangeCO2ClassDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	public static final int OK_OPTION= 0;
	public static final int CANCEL_OPTION=1;
	private int result= -1;

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
		
		JPanel pnlMedio = new JPanel();
		pnlMedio.setLayout(new FlowLayout());
		
		JLabel ve= new JLabel ("vehicle: ");
		JLabel co= new JLabel ("CO2 Class; ");
		JLabel ti= new JLabel("Ticks: ");
		tic= new JSpinner (new SpinnerNumberModel(1,1,20,1));
		
		pnlMedio.add(ve);
		pnlMedio.add(co);
		pnlMedio.add(ti);
		pnlMedio.add(tic);
		
		pnlCO2.add(pnlMedio, BorderLayout.CENTER);
		
		pnlCO2.setVisible(true);
		this.add(pnlCO2);
	}


	public int showConfirmDialog(String title) {
		setTitle(title);
		setLocationRelativeTo(getParent()); //para que la ventana 
											//salga en el centro
		pack();
		setSize(350,150);
		setVisible(true);
		return result;
	}
	
	
	
	
}

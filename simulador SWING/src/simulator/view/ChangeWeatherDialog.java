package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import simulator.model.Road;
import simulator.model.Weather;

public class ChangeWeatherDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	public static final int OK_OPTION = 0;
	public static final int CANCEL_OPTION = 1;
	private int result = -1;

	private List<Road> listR;
	private Weather w;

	private JPanel pnlWea = new JPanel();
	private JSpinner tic = new JSpinner();
	private JComboBox<Object> comboR;
	private JComboBox<Object> comboWea;
	private JButton aceptar;
	private JButton cancelar;

	public ChangeWeatherDialog(List<Road> listR) {
		super(new JFrame(), "WeatherClass", true);
		this.listR = listR;
		initGUI();
	}

	private void initGUI() {

		//panel principal
				pnlWea.setLayout(new BorderLayout());
				pnlWea.setPreferredSize(new Dimension(350,75));
				
				//parte de arriba
				JLabel la = new JLabel ("<html>Schedule an event to change the weather "
						+ "of a road after a given number os simulation ticks from now </html>");
				
				pnlWea.add(la, BorderLayout.NORTH);
				
				//parte central
				JPanel pnlMedio = new JPanel();
				pnlMedio.setLayout(new FlowLayout());
				
				JLabel ve= new JLabel ("Road: ");
				comboR = new JComboBox<>();
				for (Road r: listR) {
					comboR.addItem(r.getId());
				}
				JLabel co= new JLabel ("Weather: ");
				comboWea= new JComboBox<>();
				for (Weather w : w.values()) {
					comboWea.addItem(w.toString());
				}
				
				
				
				JLabel ti= new JLabel("Ticks: ");
				tic= new JSpinner (new SpinnerNumberModel(1,1,1000,1));
				
				pnlMedio.add(ve);
				pnlMedio.add(comboR);
				pnlMedio.add(co);
				pnlMedio.add(comboWea);
				pnlMedio.add(ti);
				pnlMedio.add(tic);
				
				pnlWea.add(pnlMedio, BorderLayout.CENTER);
				
				//parte final
				aceptar= new JButton("OK");
				cancelar = new JButton("Cancel");
				JPanel sur= new JPanel();
				sur.setLayout(new FlowLayout());
				sur.add(aceptar);
				sur.add(cancelar);
				aceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						result=OK_OPTION;
						setVisible(false);
						dispose();
					}
				});
				
				cancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						result= CANCEL_OPTION;
						setVisible(false);
						dispose();
					}
				});
				
				pnlWea.add(sur,BorderLayout.SOUTH);
				
				pnlWea.setVisible(true);
				this.add(pnlWea);
	}

	public int showConfirmDialog(String title) {
		setTitle(title);
		setLocationRelativeTo(getParent()); // para que la ventana
											// salga en el centro
		pack();
		setSize(550, 150);
		setVisible(true);
		return result;
	}

	public int getTic() {
		return (int) tic.getValue();
	}

	public String getComboWea() {
		return comboWea.getSelectedItem().toString();
	}

	public String getComboR() {
		return comboR.getSelectedItem().toString();
	}
}

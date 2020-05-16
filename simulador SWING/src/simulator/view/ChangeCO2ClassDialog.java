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

import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	public static final int OK_OPTION = 0;
	public static final int CANCEL_OPTION = 1;
	private int result = -1;

	private List<Vehicle> listV;

	private JPanel pnlCO2 = new JPanel();
	private JSpinner tic = new JSpinner();
	private JComboBox<Object> comboV;
	private JComboBox<Object> comboCo2;
	private JButton aceptar;
	private JButton cancelar;

	public ChangeCO2ClassDialog(List<Vehicle> listV) {
		super(new JFrame(), "CO2class", true);
		this.listV = listV;
		initGUI();

	}

	private void initGUI() {

		// panel principal
		pnlCO2.setLayout(new BorderLayout());
		pnlCO2.setPreferredSize(new Dimension(350, 75));

		// parte de arriba
		JLabel la = new JLabel("<html>Schedule an event to change the CO2 class os "
				+ "a vehicle after a given number os simulation ticks from now </html>");

		pnlCO2.add(la, BorderLayout.NORTH);

		// parte central
		JPanel pnlMedio = new JPanel();
		pnlMedio.setLayout(new FlowLayout());

		JLabel ve = new JLabel("Vehicle: ");
		comboV = new JComboBox<>();
		for (Vehicle v : listV) {
			comboV.addItem(v.getId());
		}
		JLabel co = new JLabel("CO2 Class: ");
		comboCo2 = new JComboBox<>();
		int i = 0;
		while (i <= 10) {
			comboCo2.addItem(i);
			i++;
		}
		JLabel ti = new JLabel("Ticks: ");
		tic = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

		pnlMedio.add(ve);
		pnlMedio.add(comboV);
		pnlMedio.add(co);
		pnlMedio.add(comboCo2);
		pnlMedio.add(ti);
		pnlMedio.add(tic);

		pnlCO2.add(pnlMedio, BorderLayout.CENTER);

		// parte final
		aceptar = new JButton("OK");
		cancelar = new JButton("Cancel");
		JPanel sur = new JPanel();
		sur.setLayout(new FlowLayout());
		sur.add(aceptar);
		sur.add(cancelar);
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				result = OK_OPTION;
				setVisible(false);
				dispose();
			}
		});

		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				result = CANCEL_OPTION;
				setVisible(false);
				dispose();
			}
		});

		pnlCO2.add(sur, BorderLayout.SOUTH);

		pnlCO2.setVisible(true);
		this.add(pnlCO2);
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

	public String getComboV() {
		return comboV.getSelectedItem().toString();
	}

	public int getComboCo2() {
		return (int) comboCo2.getSelectedItem();
	}

}

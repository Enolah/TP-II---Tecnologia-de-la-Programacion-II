package simulator.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;

public class ControlPanel extends JPanel {

	private Controller _ctrl;
	private JButton cargaEventos, cambiaClase, cambiaTiempo, play, stop, ticks, exit;
	private boolean _stopped;

	public ControlPanel(Controller ctrl) {
		this._ctrl = ctrl;
		this._stopped = false;
	}

	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// TODO show error message
				_stopped = true;
				return;
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					run_sim(n - 1);
				}
			});
		} else {
			enableToolBar(true);
			_stopped = true;
		}
	}

	private void enableToolBar(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void stop() {
		_stopped = true;
	}

}

package simulator.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;

public class ControlPanel extends JPanel {

	private Controller _ctrl;
	private JPanel ficheros;
	private JButton cargaEventos, cambiaClase, cambiaTiempo, play, stop, ticks, exit;
	private boolean _stopped;

	public ControlPanel(Controller ctrl) {
		this._ctrl = ctrl;
		this._stopped = false;
		this.initGUI();
	}

	private void initGUI() {
		this.ficheros = new JPanel();
		this.cargaEventos = new JButton((Icon) loadImage("open.png"));
		this.cargaEventos.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int respuesta = fc.showOpenDialog(ficheros);
				if (respuesta == JFileChooser.APPROVE_OPTION) {
					File archivoElegido = fc.getSelectedFile();
					System.out.println(archivoElegido.getName());
				}

			}
		});
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

	private void cargaEventos() {

	}

	private void cambiaClase() {

	}

	private void cambiaTiempo() {

	}

	private void exit() {

		System.exit(0);
	}

	// loads an image from a file - Funcion copiada de MapComponent.java
	private Image loadImage(String img) {
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}

}

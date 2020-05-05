package simulator.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;

import simulator.control.Controller;

public class ControlPanel extends JPanel {

	private Controller _ctrl;
	private JPanel ficheros;
	private JButton cargaEventos, cambiaClase, cambiaTiempo, play, stop, exit;
	private JSpinner ticks;
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
				cargaEventos();

			}
		});
		
		this.cambiaClase = new JButton((Icon) loadImage("co2class.png"));
		this.cambiaClase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiaClase();
				
			}
		});
		this.cambiaTiempo = new JButton((Icon) loadImage("weather.png"));
		this.cambiaTiempo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiaTiempo();
				
			}
		});
		this.play = new JButton((Icon) loadImage("run.png"));
		this.play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				run_sim((int) ticks.getValue()); //Provisional - Tiene que coger el valor de ticks
				
			}
		});
		this.stop = new JButton((Icon) loadImage("stop.png"));
		this.stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				stop();
				
			}
		});
		this.ticks = new JSpinner();
		this.exit = new JButton((Icon) loadImage("exit.png"));
		this.exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
				
			}
		});
	}

	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
				enableToolBar(false);
			} catch (Exception e) {
				// TODO show error message
				e.printStackTrace();
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
		
		this.cargaEventos.setEnabled(b);
		this.cambiaClase.setEnabled(b);
		this.cambiaTiempo.setEnabled(b);
		this.play.setEnabled(b);
		this.exit.setEnabled(b);

	}

	private void stop() {
		_stopped = true;
		enableToolBar(true);
	}

	private void cargaEventos() {
		JFileChooser fc = new JFileChooser();
		int respuesta = fc.showOpenDialog(ficheros);
		if (respuesta == JFileChooser.APPROVE_OPTION) {
			File archivoElegido = fc.getSelectedFile();
			System.out.println(archivoElegido.getName());
		}
	}

	private void cambiaClase() {

	}

	private void cambiaTiempo() {

	}

	//Posible cambio cuando se ejecute
	private void exit() {
		JOptionPane.showConfirmDialog(this, "¿Desea salir del programa?");
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

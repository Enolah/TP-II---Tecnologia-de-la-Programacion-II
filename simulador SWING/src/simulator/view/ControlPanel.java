package simulator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.SetContClassEvent;
import simulator.model.SetWeatherEvent;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.Weather;

public class ControlPanel extends JPanel implements TrafficSimObserver {

	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	private JButton btnCargaEventos, btnCambiaClase, btnCambiaTiempo, btnPlay, btnStop, btnExit;
	private JSpinner spinTicks;
	private boolean _stopped;

	// atributos de los ON
	private RoadMap map;
	private List<Event> listE;
	private int ticks;

	// atributos para las clases change
	private List<Vehicle> listV;
	private List<Road> listR;

	public ControlPanel(Controller ctrl) {
		this._ctrl = ctrl;
		this._stopped = false;
		this.listV = new ArrayList<Vehicle>();
		this.listR = new ArrayList<Road>();
		this.initGUI();
		this._ctrl.addObserver(this);
	}

	private void initGUI() {

		// ImageIcon icon = new ImageIcon("resources/icons/open.png");
		this.btnCargaEventos = new JButton(new ImageIcon("resources/icons/open.png"));
		this.btnCargaEventos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cargaEventos();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					onError(ex.getMessage());
				}

			}
		});

		// ImageIcon icon1 = new ImageIcon("resources/icons/co2class.png");
		this.btnCambiaClase = new JButton(new ImageIcon("resources/icons/co2class.png"));
		this.btnCambiaClase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cambiaClase();
				} catch (NullPointerException ex) {
					onError(ex.getMessage());
				}

			}
		});

		// ImageIcon icon2 = new ImageIcon("resources/icons/weather.png");
		this.btnCambiaTiempo = new JButton(new ImageIcon("resources/icons/weather.png"));
		this.btnCambiaTiempo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cambiaTiempo();
				} catch (NullPointerException ex) {
					onError(ex.getMessage());
				}

			}
		});

		// ImageIcon icon3 = new ImageIcon("resources/icons/run.png");
		this.btnPlay = new JButton(new ImageIcon("resources/icons/run.png"));
		this.btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_stopped = false;
				run_sim((int) spinTicks.getValue());

			}
		});

		// ImageIcon icon4 = new ImageIcon("resources/icons/stop.png");
		this.btnStop = new JButton(new ImageIcon("resources/icons/stop.png"));
		this.btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stop();

			}
		});

		// ImageIcon icon5 = new ImageIcon("resources/icons/exit.png");
		JLabel tic = new JLabel("Ticks: ");
		// empieza desde 1, pq es el valor minimo que puedes a�adir a los ticks
		this.spinTicks = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

		this.btnExit = new JButton(new ImageIcon("resources/icons/exit.png"));
		this.btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();

			}
		});

		this.add(btnCargaEventos);
		this.add(btnCambiaClase);
		this.add(btnCambiaTiempo);
		this.add(btnPlay);
		this.add(btnStop);
		this.add(tic);
		this.add(spinTicks);
		this.add(btnExit);

		//Deja deshabilitados los botones para cambiar el tiempo o la clase de contaminacion
		this.enableToolBarStart();
		
	}

	private void run_sim(int n) {
		// System.out.println(_stopped);
		if (n > 0 && !this._stopped) {
			try {
				this._ctrl.run(1, null);
				Thread.sleep(1000);
				this.enableToolBar(false);
			} catch (Exception e) {
				// TODO show error message
				this.onError(e.getMessage());
				this._stopped = true;
				return;
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					run_sim(n - 1);

				}
			});
		} else {
			this.enableToolBar(true);
			this._stopped = true;
		}
	}

	private void enableToolBar(boolean b) {

		this.btnCargaEventos.setEnabled(b);
		this.btnCambiaClase.setEnabled(b);
		this.btnCambiaTiempo.setEnabled(b);
		this.btnPlay.setEnabled(b);
		this.btnExit.setEnabled(b);

	}

	private void enableToolBarStart() {

		this.btnCargaEventos.setEnabled(true);
		this.btnCambiaClase.setEnabled(false);
		this.btnCambiaTiempo.setEnabled(false);
		this.btnPlay.setEnabled(true);
		this.btnExit.setEnabled(true);

	}
	
	private void stop() {
		this._stopped = true;
		this.enableToolBar(true);

	}

	private void cargaEventos() throws IOException {
		// TODO lanzar excepci�n
		JFileChooser fc = new JFileChooser();
		String nombreFichero;
		int respuesta = fc.showOpenDialog(this);

		if (respuesta == JFileChooser.APPROVE_OPTION) {
			File archivoElegido = fc.getSelectedFile();
			nombreFichero = fc.getSelectedFile().getName();

			if (nombreFichero.substring(nombreFichero.lastIndexOf("."), nombreFichero.length())
					.equalsIgnoreCase(".json")) {
				this._ctrl.reset();
				InputStream in = new FileInputStream(archivoElegido);
				this._ctrl.loadEvents(in);
			} else
				throw new IOException("Archivo con formato erroneo");
			//Una vez se ha cargado un fichero valido, habilita el resto de los botones
			this.enableToolBar(true);
		}

	}

	private void cambiaClase() throws NullPointerException {
		this.listV = this.map.getVehicles();

		if (this.listV.size() != 0) {
			ChangeCO2ClassDialog myCo2 = new ChangeCO2ClassDialog(listV);
			int res = myCo2.showConfirmDialog("Change co2 class");
			if (res == 0) {
				// crear un evento nuevo del tipo setContClass
				// System.out.println(myCo2.getComboCo2()+"/"+myCo2.getComboV()+"/"+myCo2.getTic());
				Pair<String, Integer> p = new Pair<String, Integer>(myCo2.getComboV(), myCo2.getComboCo2());
				List<Pair<String, Integer>> cs = new ArrayList<Pair<String, Integer>>();

				if (p != null && cs != null) {
					cs.add(p);
					Event e = new SetContClassEvent(this.ticks + myCo2.getTic(), cs);
					this._ctrl.addEvent(e);
				} else
					throw new NullPointerException("Error al recoger los datos de ChangeCO2ClassDialog.");
			}
		} else
			throw new NullPointerException("Lista de vehiculos vac�a.");

	}

	private void cambiaTiempo() throws NullPointerException {
		this.listR = this.map.getRoads();

		if (this.listR.size() != 0) {
			ChangeWeatherDialog myWea = new ChangeWeatherDialog(this.listR);

			int res = myWea.showConfirmDialog("Change weather class");
			if (res == 0) {
				// crear evento de set weather
				// System.out.println(myWea.getComboR()+"/"+myWea.getComboWea()+"/"+myWea.getTic());
				Pair<String, Weather> p = new Pair<String, Weather>(myWea.getComboR(), myWea.getComboWea());
				List<Pair<String, Weather>> cs = new ArrayList<Pair<String, Weather>>();
				if (p != null && cs != null) {
					cs.add(p);
					Event e = new SetWeatherEvent(this.ticks + myWea.getTic(), cs);
					this._ctrl.addEvent(e);
				} else
					throw new NullPointerException("Error al recoger los datos de ChangeWeatherDialog.");
			}

		} else
			throw new NullPointerException("Lista de carreteras vac�a.");
	}

	// Posible cambio cuando se ejecute
	private void exit() {
		int confirm = JOptionPane.showOptionDialog(null, "�Quieres salir de la aplicacion?", "Confirmacion de salida",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			System.exit(0);
		}
	}

	private void update(RoadMap map, List<Event> events, int time) {
		this.map = map;
		this.listE = events;
		this.ticks = time;

	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		this.update(map, events, time);

	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		this.update(map, events, time);

	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, err);
	}

}

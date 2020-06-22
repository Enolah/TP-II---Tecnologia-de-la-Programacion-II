package simulator.view;


import java.awt.MediaTracker;
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
	private JSpinner spinTicks, spinDelay;
//	private boolean _stopped;
	private volatile Thread _thread;

	// atributos de los ON
	private RoadMap map;
	private int ticks;

	// atributos para las clases change
	private List<Vehicle> listV;
	private List<Road> listR;

	public ControlPanel(Controller ctrl) {
		this._ctrl = ctrl;
	//	this._stopped = false;
		this.listV = new ArrayList<Vehicle>();
		this.listR = new ArrayList<Road>();
		this.initGUI();
		this._ctrl.addObserver(this);
	}

	private void initGUI() {

	
		this.btnCargaEventos=cargarImg("open.png", btnCargaEventos, "Cargar Fichero");
		this.btnCargaEventos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cargaEventos();
				} catch (IOException ex) {
					onError(ex.getMessage());
				}

			}
		});

	
		this.btnCambiaClase=cargarImg("co2class.png", btnCambiaClase, "Cambia CO2");
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

	
		this.btnCambiaTiempo=cargarImg("weather.png", btnCambiaTiempo, "Cambia Tiempo");
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

	
		this.btnPlay=cargarImg("run.png", btnPlay, "Play");
		this.btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			//	_stopped = false;
				play();
				_thread = new Thread(){
					@Override
					public void run() {
						run_sim((int) spinTicks.getValue(),(int) spinDelay.getValue());
						
					}
				};
				_thread.start();

			}
		});
		
		
	
		this.btnStop=cargarImg("stop.png", btnStop, "Stop");
		this.btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		
				stop();

			}
		});

		JLabel tic = new JLabel("Ticks: ");
		// empieza desde 1, pq es el valor minimo que puedes añadir a los ticks
		this.spinTicks = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
		if( _ctrl.getNumPasos()!=0)//comando -t 300 o si es por defecto == 10
			this.spinTicks.setValue(_ctrl.getNumPasos());
		
		
		JLabel delay = new JLabel ("Delay: ");
		this.spinDelay = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
	
		
		this.btnExit = cargarImg("exit.png", btnExit, "Exit");
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
		this.add(delay);
		this.add(spinDelay);
		this.add(btnExit);

		//Deja deshabilitados los botones para cambiar el tiempo o la clase de contaminacion
		this.enableToolBarStart();
		
	}
	
	

	private void run_sim(int n, long delay) {

		/*
		 * while ( n>0 && (the current thread has not been intereptred) ) { //
		 * 1. execute the simulator one step, i.e., call method // _ctrl.run(1)
		 * and handle exceptions if any // 2. sleep the current thread for
		 * ’delay’ milliseconds n--; }
		 */
		
		if (n > 0 && !_thread.isInterrupted()) {
			try {
				this._ctrl.run(1, null);
				_thread.sleep(delay);
				
			} catch (InterruptedException e) {
				this.onError(e.getMessage());
				_thread.interrupt();
			//	this._stopped = true;
				return;
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					run_sim(n - 1,delay);

				}
			});
		} else {
			this.enableToolBar(true);
	//		this._stopped = true;
		}
	}

	private void enableToolBar(boolean b) {

		this.btnCargaEventos.setEnabled(b);
		this.btnCambiaClase.setEnabled(b);
		this.btnCambiaTiempo.setEnabled(b);
		this.btnPlay.setEnabled(b);
		this.btnStop.setEnabled(b);
		this.btnExit.setEnabled(true);

	}

	private void enableToolBarStart() {

		this.btnCargaEventos.setEnabled(true);
		this.btnCambiaClase.setEnabled(false);
		this.btnCambiaTiempo.setEnabled(false);
		this.btnPlay.setEnabled(true);
		this.btnStop.setEnabled(false);
		this.btnExit.setEnabled(true);

	}
	
	private void stop() {
	//	this._stopped = true;
		if(_thread!=null){
			_thread.interrupt();
			this.enableToolBar(true);
		}
	

	}

	private void play() {
		this.btnCargaEventos.setEnabled(false);
		this.btnCambiaClase.setEnabled(false);
		this.btnCambiaTiempo.setEnabled(false);
		this.btnPlay.setEnabled(false);
		this.btnStop.setEnabled(true);
		this.btnExit.setEnabled(true);
	}
	
	private void cargaEventos() throws IOException {
		
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
				//Una vez se ha cargado un fichero valido, habilita el resto de los botones
				this.btnPlay.setEnabled(true);
				this.btnStop.setEnabled(true);
			} else
				throw new IOException("Archivo con formato erroneo");
			
		}

	}

	private void cambiaClase() throws NullPointerException {
		this.listV = this.map.getVehicles();

		if (this.listV.size() != 0) {
			ChangeCO2ClassDialog myCo2 = new ChangeCO2ClassDialog(listV);
			int res = myCo2.showConfirmDialog("Change co2 class");
			if (res == 0) {
				// crear un evento nuevo del tipo setContClass
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
			throw new NullPointerException("Lista de vehiculos vacía.");

	}

	private void cambiaTiempo() throws NullPointerException {
		this.listR = this.map.getRoads();

		if (this.listR.size() != 0) {
			ChangeWeatherDialog myWea = new ChangeWeatherDialog(this.listR);

			int res = myWea.showConfirmDialog("Change weather class");
			if (res == 0) {
				// crear evento de set weather
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
			throw new NullPointerException("Lista de carreteras vacía.");
	}
	


	private JButton cargarImg(String icon, JButton btn, String s){
		
		ImageIcon img= new ImageIcon ("src/icons/" + icon);
		//Complete: Flag indicating that the downloading 
		//of media was completed successfully.
		if(img.getImageLoadStatus()==MediaTracker.COMPLETE){ 
			btn = new JButton(img);
		}
		else
			btn = new JButton(s);
		
		return btn;
	}


	private void exit() {
		int confirm = JOptionPane.showOptionDialog(null, "¿Quieres salir de la aplicacion?", "Confirmacion de salida",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			System.exit(0);
		}
	}

	private void update(RoadMap map, int time) {
		this.map = map;

		this.ticks = time;

	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		this.update(map,time);

	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {

	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		this.update(map, time);

	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {

	}

	@Override
	public void onError(String err) {
		JOptionPane.showMessageDialog(null, err);
	}



}

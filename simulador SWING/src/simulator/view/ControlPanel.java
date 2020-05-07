package simulator.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver{


	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	private JButton btnCargaEventos, btnCambiaClase, btnCambiaTiempo, btnPlay, btnStop, btnExit;
	private JSpinner spinTicks;
	private boolean _stopped;
	

	public ControlPanel(Controller ctrl) {
		this._ctrl = ctrl;
		this._stopped = false;
		initGUI();
		_ctrl.addObserver(this);
	}

	private void initGUI() {
	
		
		
		ImageIcon icon= new ImageIcon("resources/icons/open.png");
		this.btnCargaEventos = new JButton(icon);
		this.btnCargaEventos.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				cargaEventos();

			}
		});
		

		ImageIcon icon1= new ImageIcon("resources/icons/co2class.png");
		this.btnCambiaClase = new JButton(icon1);
		this.btnCambiaClase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiaClase();
				
			}
		});
	
		ImageIcon icon2= new ImageIcon("resources/icons/weather.png");
		this.btnCambiaTiempo = new JButton(icon2);
		this.btnCambiaTiempo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiaTiempo();
				
			}
		});
	
		ImageIcon icon3= new ImageIcon("resources/icons/run.png");
		this.btnPlay = new JButton(icon3);
		this.btnPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				run_sim(10); //Provisional - Tiene que coger el valor de ticks
				//(int) spinTicks.getValue()
				
			}
		});
	
		ImageIcon icon4= new ImageIcon("resources/icons/stop.png");
		this.btnStop = new JButton(icon4);
		this.btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				stop();
				
			}
		});
	
		ImageIcon icon5= new ImageIcon("resources/icons/exit.png");
		JLabel tic= new JLabel("Ticks: ");
		spinTicks= new JSpinner (new SpinnerNumberModel(0,0,5,1));
		
		this.btnExit = new JButton(icon5);
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
		this.add(btnExit);
		this.add(spinTicks);
		
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
		
		this.btnCargaEventos.setEnabled(b);
		this.btnCambiaClase.setEnabled(b);
		this.btnCambiaTiempo.setEnabled(b);
		this.btnPlay.setEnabled(b);
		this.btnExit.setEnabled(b);

	}

	private void stop() {
		_stopped = true;
		enableToolBar(true);
		
	}

	private void cargaEventos() {
		JFileChooser fc = new JFileChooser();
		int respuesta = fc.showOpenDialog(this);
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

	
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		
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
		
	}

}

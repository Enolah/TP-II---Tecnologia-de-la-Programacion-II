package simulator.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import simulator.model.Road;
import simulator.model.RoadMap;

public class ChangeWeatherDialog extends JDialog{


	private static final long serialVersionUID = 1L;
	public static final int OK_OPTION= 0;
	public static final int CANCEL_OPTION=1;
	private int result= -1;

	private List<Road> listR;
	
	private JPanel pnlWea = new JPanel();
	private JSpinner tic= new JSpinner();
	private JComboBox<Object> comboR;
	private JComboBox<Object> comboWea;
	private JButton aceptar;
	private JButton cancelar;
	
	public ChangeWeatherDialog(List<Road> listR){
		super(new JFrame(),"WeatherClass",true);
		
		initGUI();
	}


	public int showConfirmDialog(String string) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getTic() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getComboWea() {
		// TODO Auto-generated method stub
		return 0;
	}


	public String getComboR() {
		// TODO Auto-generated method stub
		return null;
	}}

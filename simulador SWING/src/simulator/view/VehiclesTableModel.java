package simulator.view;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;

public class VehiclesTableModel extends AbstractTableModel implements TrafficSimObserver{


	private static final long serialVersionUID = 1L;

	private List<Vehicle> _vehicles;

	private Controller _ctrl;
	private String[] _colNames = {"id", "Location", "Itinerary", "CO2 Class", "Max. Speed", "Speed", "Total CO2", "Distance"};
	
	public VehiclesTableModel(Controller ctrl) {
		this._ctrl = ctrl;
	//	this._vehicles = null;
		_ctrl.addObserver(this);
	}
	
	public void update(RoadMap map) {
		
		_vehicles= map.getVehicles();
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this._vehicles == null ? 0 : this._vehicles.size();
	}

	@Override
	public int getColumnCount() {
		
		return this._colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = _vehicles.get(rowIndex).getId();
			break;
		case 1:
			s = _vehicles.get(rowIndex).getLocation();
			break;
		case 2:
			s = _vehicles.get(rowIndex).getContClass();
			break;
		case 3:
			s = _vehicles.get(rowIndex).getMaxSpeed();
			break;
		case 4:
			s = _vehicles.get(rowIndex).getSpeed();
			break;
		case 5:
			s = _vehicles.get(rowIndex).getTotalPollution();
			break;
		case 6:
			s = _vehicles.get(rowIndex).getTotalDistance();
			break;
		}
		return s;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		update(map);
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		update(map);
		
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

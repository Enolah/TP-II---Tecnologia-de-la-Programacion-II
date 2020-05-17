package simulator.view;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.VehicleStatus;

public class VehiclesTableModel extends AbstractTableModel implements TrafficSimObserver {

	private static final long serialVersionUID = 1L;

	private List<Vehicle> _vehicles;

	private Controller _ctrl;
	private String[] _colNames = { "id", "Status", "Itinerary", "CO2 Class", "Max. Speed", "Speed", "Total CO2",
			"Distance" };

	public VehiclesTableModel(Controller ctrl) {
		this._ctrl = ctrl;
		this._ctrl.addObserver(this);
	}

	public void update(RoadMap map) {
		this._vehicles = map.getVehicles();
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int col) {
		return this._colNames[col];
	}

	@Override
	public int getRowCount() {
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
			s = this._vehicles.get(rowIndex).getId();
			break;
		case 1:
			VehicleStatus vs = this._vehicles.get(rowIndex).getStatus();
			if (vs == VehicleStatus.PENDING)
				s = vs.toString();
			else if (vs == VehicleStatus.TRAVELING)
				s = this._vehicles.get(rowIndex).getRoad().getId() + ":" + this._vehicles.get(rowIndex).getLocation();
			else if (vs == VehicleStatus.WAITING)
				s = "Waiting:" + this._vehicles.get(rowIndex).getRoad().getDest();
			else if (vs == VehicleStatus.ARRIVED)
				s = "Arrived";
			break;
		case 2:
			s = this._vehicles.get(rowIndex).getItinerary();
			break;
		case 3:
			s = this._vehicles.get(rowIndex).getContClass();
			break;
		case 4:
			s = this._vehicles.get(rowIndex).getMaxSpeed();
			break;
		case 5:
			s = this._vehicles.get(rowIndex).getSpeed();
			break;
		case 6:
			s = this._vehicles.get(rowIndex).getTotalPollution();
			break;
		case 7:
			s = this._vehicles.get(rowIndex).getTotalDistance();
			break;
		}
		return s;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		this.update(map);
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		this.update(map);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onError(String err) {
	}

}

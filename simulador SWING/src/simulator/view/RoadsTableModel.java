package simulator.view;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class RoadsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private static final long serialVersionUID = 1L;
	private List<Road> _roadsList;
	private RoadMap _map;
	private Controller _ctrl;
	private String[] _colNames = {"id", "Length", "Weather", "Max. Speed", "Speed Limit", "Total CO2", "CO2 Limit"};
	
	public RoadsTableModel(Controller _ctrl) {
		this._ctrl = _ctrl;
		//this._roadsList = null;
		_ctrl.addObserver(this);
	}
	
	public void update(RoadMap map) {

		_roadsList = map.getRoads();
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this._roadsList == null ? 0 : this._roadsList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this._colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = _roadsList.get(rowIndex).getId();
			break;
		case 1:
			s = _roadsList.get(rowIndex).getLength();
			break;
		case 2:
			s = _roadsList.get(rowIndex).getWeather(); 
			break;
		case 3:
			s = _roadsList.get(rowIndex).getMaxSpeed();
			break;
		case 4:
			s = _roadsList.get(rowIndex).getLimitSpeed();
			break;
		case 5:
			s = _roadsList.get(rowIndex).getTotalPollution();
			break;
		case 6:
			s = _roadsList.get(rowIndex).getContLimit();
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

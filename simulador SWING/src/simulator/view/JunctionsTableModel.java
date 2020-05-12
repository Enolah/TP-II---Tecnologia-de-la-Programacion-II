package simulator.view;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class JunctionsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private static final long serialVersionUID = 1L;
	private List<Junction> _junctionsList;

	private Controller _ctrl;
	private String[] _colNames = {"id", "Green", "Queues"};
	
	public JunctionsTableModel(Controller ctrl) {
		this._ctrl = ctrl;
		//this._junctionsList = null;
		_ctrl.addObserver(this);
	}
	
	public void update(RoadMap map) {

		_junctionsList = map.getJunctions();
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this._junctionsList == null ? 0 : this._junctionsList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this._colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = _junctionsList.get(rowIndex).getId();
			break;
		case 1:
			if(_junctionsList.get(rowIndex).getGreenLightIndex()<=0)
				s= "NONE";
			else
				s = _junctionsList.get(rowIndex).getGreenLightIndex();
			break;
		case 2:
			s = _junctionsList.get(rowIndex).getMapR_Q();
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

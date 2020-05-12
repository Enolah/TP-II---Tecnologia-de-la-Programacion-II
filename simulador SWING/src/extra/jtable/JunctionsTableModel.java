package extra.jtable;

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
	
	public JunctionsTableModel(Controller _ctrl) {
		this._ctrl = _ctrl;
		this._junctionsList = null;
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
			s = rowIndex;
			break;
		case 1:
			s = _junctionsList.get(rowIndex).getId();
			break;
		case 2:
			s = _junctionsList.get(rowIndex).getGreenLightIndex();
			break;
		case 3:
			s = _junctionsList.get(rowIndex).getInRoads(); //Posiblemente mal
			break;
		}
		return s;
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

package simulator.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class JunctionsTableModel extends AbstractTableModel implements TrafficSimObserver {

	private static final long serialVersionUID = 1L;
	private List<Junction> _junctionsList;

	private Controller _ctrl;
	private String[] _colNames = { "id", "Green", "Queues" };

	public JunctionsTableModel(Controller ctrl) {
		this._ctrl = ctrl;
		this._ctrl.addObserver(this);
	}

	public void update(RoadMap map) {

		this._junctionsList = map.getJunctions();
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int col) {
		return this._colNames[col];
	}

	@Override
	public int getRowCount() {
		return this._junctionsList == null ? 0 : this._junctionsList.size();
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
			s = this._junctionsList.get(rowIndex).getId();
			break;
		case 1:
			if (this._junctionsList.get(rowIndex).getGreenLightIndex() < 0)
				s = "NONE";
			else
				s = this._junctionsList.get(rowIndex).getRoadCurrGreen();
			break;
		case 2:
			if (this._junctionsList == null)
				s = " ";
			else
				s = this._junctionsList.get(rowIndex).getMapR_Q();
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
		this.update(map);
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		this.update(map);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		this.update(map);
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		this.update(map);
	}

	@Override
	public void onError(String err) {
	}

}

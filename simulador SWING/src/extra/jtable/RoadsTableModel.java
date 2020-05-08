package extra.jtable;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class RoadsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private JTable roadsTable;
	private int cols, rows;
	
	public RoadsTableModel(List<Road> roadsList) {
		this.cols = 7;
		this.roadsTable = new JTable(roadsList.size(), this.cols);
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.roadsTable.getRowCount();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.cols;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return this.roadsTable.getValueAt(rowIndex, columnIndex);
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

package extra.jtable;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class EventsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	
	private List<Event> _events;
	private JTable eventsTable;
	private String[] _colNames = { "#", "Time", "Priority"};

	public EventsTableModel(Controller _ctrl) {
		this._ctrl = _ctrl;
		_events=null;
		_ctrl.addObserver(this);
	}
	

	public void update(List<Event> e) {
		// observar que si no refresco la tabla no se carga
		// La tabla es la represantación visual de una estructura de datos,
		// en este caso de un ArrayList, hay que notificar los cambios.
		
		// We need to notify changes, otherwise the table does not refresh.
		_events=e;
		fireTableDataChanged();
	}
	

	public void setEventsList(List<Event> listE){
		_events=listE;
		update(listE);
	}
	public void setEventsList1(List<EventEx> events) { //set de ejemplo, eliminar
		//_events = events;
		//update();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	//si no pongo esto no coge el nombre de las columnas
	//
	//this is for the column header
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}

	@Override
	// método obligatorio, probad a quitarlo, no compila
	//
	// this is for the number of columns
	public int getColumnCount() {
		return _colNames.length;
	}

	@Override
	// método obligatorio
	//
	// the number of row, like those in the events list
	public int getRowCount() {
		return _events == null ? 0 : _events.size();
	}

	@Override
	// método obligatorio
	// así es como se va a cargar la tabla desde el ArrayList
	// el índice del arrayList es el número de fila pq en este ejemplo
	// quiero enumerarlos.
	//
	// returns the value of a particular cell 
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = rowIndex;
			break;
		case 1:
			s = _events.get(rowIndex).getTime();
			break;
		case 2:
			s = _events.get(rowIndex).toString();
			break;
		}
		return s;
	}
	
	

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {	
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(events);
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		
		update(events);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(events);
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(events);
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}
}

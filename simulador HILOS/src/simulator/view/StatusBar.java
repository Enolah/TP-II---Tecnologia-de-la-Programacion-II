package simulator.view;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver {

	private static final long serialVersionUID = 1L;
	private JLabel lblTime, lblEvent;
	private Controller _ctrl;

	public StatusBar(Controller ctrl) {
		this._ctrl = ctrl;
		this.initStatusBar();
		_ctrl.addObserver(this);
	}

	private void initStatusBar() {

		this.lblTime = new JLabel();
		this.lblTime.setText("Time: ");
		this.lblEvent = new JLabel();
		this.lblEvent.setText("Welcome! ");

		this.add(lblTime);
		this.add(lblEvent);

	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {

		this.lblTime.setText("Time " + time);
		this.lblEvent.setText("");
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {

		this.lblTime.setText("Time " + time);
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {

		this.lblTime.setText("Time " + time);
		this.lblEvent.setText("Event added (" + e.toString() + ")");
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {

		this.lblTime.setText("");
		this.lblEvent.setText("");
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onError(String err) {
		this.lblEvent.setText(err);
	}

}

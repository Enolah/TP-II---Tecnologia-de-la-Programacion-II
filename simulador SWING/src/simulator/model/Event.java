package simulator.model;

public abstract class Event implements Comparable<Event> {

	protected int _time;

	Event(int time) {
		if (time < 1)
			throw new IllegalArgumentException("Time must be positive (" + time + ")");
		else
			_time = time;
	}

	int getTime() {
		return _time;
	}

	@Override
	public int compareTo(Event o) {
		// TODO complete the method to compare events according to their _time
		int ok=-1;
		if (this._time<o._time) ok=1;
		else if (this._time==o._time) ok=0;
		else if (this._time>o._time) ok=-1;
		return ok;
	}

	abstract void execute(RoadMap map);

}

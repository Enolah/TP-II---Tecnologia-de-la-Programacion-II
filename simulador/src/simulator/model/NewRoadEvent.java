package simulator.model;

public abstract class NewRoadEvent extends Event {

	NewRoadEvent(int time) {
		super(time);
	
	}

	@Override
	abstract void execute(RoadMap map);
	
}

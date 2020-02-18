package simulator.model;

public abstract class NewRoadEvent extends Event {

	NewRoadEvent(int time) {
		super(time);
		// TODO Auto-generated constructor stub
	}

	@Override
	abstract void execute(RoadMap map);
	//inlcuir las partes comunes a ambas
}

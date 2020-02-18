package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent{

	NewCityRoadEvent(int time, String id, String srcJun, String destJun, int lenght, int co2Limit, int maxSpeed, Weather weather) {
		super(time);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute(RoadMap map) {
		
	}

}

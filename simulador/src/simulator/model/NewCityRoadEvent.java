package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent{

	public NewCityRoadEvent(int time, String id, String srcJun, String destJun, 
			int lenght, int co2Limit, int maxSpeed, Weather weather) {
		super(time,  id, srcJun,  destJun, lenght,  co2Limit,  maxSpeed,  weather);
	
	}


	Road createRoadObject(Junction srcJun, Junction destJun) {
		return new CityRoad(getId(), srcJun, destJun, getMaxSpeed(), getCo2limit(), getLenght(), getWea());
		 
	}

}

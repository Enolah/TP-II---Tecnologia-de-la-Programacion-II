package simulator.model;

public class InterCityRoad extends Road {

	public InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int lenght, Weather weather){
		super(id, srcJunc, destJunc, maxSpeed, contLimit, lenght, weather);
	}
	
	
	//metodos
	
	void reduceTotalContamination(){};
		
	@Override
	void updateSpeedLimit() {
		// TODO Auto-generated method stub

	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		// TODO Auto-generated method stub
		return 0;
	}
}

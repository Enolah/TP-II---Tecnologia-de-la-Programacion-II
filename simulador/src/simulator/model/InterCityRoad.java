package simulator.model;

public class InterCityRoad extends Road {

	public InterCityRoad(String id, Juction srcJunc, Juction destJunc, int maxSpeed, int contLimit, int lenght, Weather weather){
		super(id);
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

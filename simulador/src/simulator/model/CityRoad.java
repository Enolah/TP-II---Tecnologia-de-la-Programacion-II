package simulator.model;

public class CityRoad extends Road{

	public CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int lenght, Weather weather){
		super(id, srcJunc, destJunc, maxSpeed, contLimit, lenght, weather);
	}
	
	//metodos
	
	void reduceTotalContamination(){}
	
	
	

	@Override
	void updateSpeedLimit() {
		// TODO Auto-generated method stub
		
	}

	//velocidad limite no cambia siempre es la velocidad maxima;
	
	@Override
	int calculateVehicleSpeed(Vehicle v) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

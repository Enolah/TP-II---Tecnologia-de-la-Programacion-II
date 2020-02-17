package simulator.model;

public class CityRoad extends Road{

	public CityRoad(String id, Juction srcJunc, Juction destJunc, int maxSpeed, int contLimit, int lenght, Weather weather){
		super(id);
	}
	
	//metodos
	
	reduceTotalContamination();
	
	//velocidad limite no cambia siempre es la velocidad maxima;
	
	calculateVehicleSpeed();
	
}

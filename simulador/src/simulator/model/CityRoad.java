package simulator.model;

public class CityRoad extends Road{

	public CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int lenght, Weather weather){
		super(id, srcJunc, destJunc, maxSpeed, contLimit, lenght, weather);
	}
	
	
	@Override
	public void reduceTotalContamination(){
		int x=0;
		switch (getWeather()) {
			case WINDY: x=10;
				break;
			case STORM : x=10;
				break;
			default: x=2;
				break;
		}
		try{
		setTotalPollution(getTotalPollution()-x);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	@Override
	public void updateSpeedLimit() {
		// La velocidad limite no cambia, siempre es la velocidad maxima
		setLimitSpeed(getMaxSpeed()); //??
	}
	
	@Override
	public int calculateVehicleSpeed(Vehicle v) {
		int s=getLimitSpeed();
		int f=v.getContClass();
		return (int)(((11.0-f)/11.0)*s);
	}
	
}

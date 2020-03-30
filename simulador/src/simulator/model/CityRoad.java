package simulator.model;

public class CityRoad extends Road{

	public CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int lenght, Weather weather){
		super(id, srcJunc, destJunc, maxSpeed, contLimit, lenght, weather);
	}
	
	/*
	 * METODOS
	 */
	
	@Override
	public void reduceTotalContamination(){
		int x=0;
		Weather w=getWeather();
		if(w== w.WINDY || w== w.STORM)
			x=10;
		else
			 x=2;
		
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
		setLimitSpeed(getMaxSpeed());
	}
	
	@Override
	public int calculateVehicleSpeed(Vehicle v) {
		int s=getLimitSpeed();
		int f=v.getContClass();
		return (int)(((11.0-f)/11.0)*s);
	}
	
}

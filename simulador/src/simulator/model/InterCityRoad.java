package simulator.model;

public class InterCityRoad extends Road {

	public InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int lenght,
			Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, lenght, weather);

	}

	/*
	 * METODOS
	 */
	
	@Override
	public void reduceTotalContamination() {
		int tc = getTotalPollution();
		int x = 0;
		// TODO comprobar que con el get directamente funciona
		//Weather w=getWeather();
		switch (getWeather()) {
		case SUNNY:
			x = 2;
			break;
		case CLOUDY:
			x = 3;
			break;
		case RAINY:
			x = 10;
			break;
		case WINDY:
			x = 15;
			break;
		case STORM:
			x = 20;
			break;
		}
		try {
			setTotalPollution((int) ((100.0 - x) / 100 * tc));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void updateSpeedLimit() {
		if (getTotalPollution() > getContLimit())
			setLimitSpeed((int) (getMaxSpeed() * 0.5));
		else {
			setLimitSpeed(getMaxSpeed()); 
		}

	}

	@Override
	public int calculateVehicleSpeed(Vehicle v) {
		Weather w=getWeather();
		int s=0;
			if (w == w.STORM) {
				s=(int)(getLimitSpeed()* 0.8);
			}
			else
				s=getLimitSpeed();
		
		return s;
	}
}

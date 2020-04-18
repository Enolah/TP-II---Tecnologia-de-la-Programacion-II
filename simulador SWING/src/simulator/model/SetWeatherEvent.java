package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event{

	private List<Pair<String,Weather>> ws;
	
	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
		super(time);
		this.ws= ws;
		
		if( ws==null) throw new IllegalArgumentException("Invalid value");
	}

	@Override
	void execute(RoadMap map) {
		
		for (Pair<String, Weather> r : ws) {
			if(map.getRoads().contains(r.getFirst())) throw new IllegalArgumentException("no existe la carretera");
				map.getRoad(r.getFirst()).setWeather(r.getSecond());
		}
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}

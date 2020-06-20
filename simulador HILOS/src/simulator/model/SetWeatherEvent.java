package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event {

	private List<Pair<String, Weather>> ws;

	public SetWeatherEvent(int time, List<Pair<String, Weather>> ws) {
		super(time);
		this.ws = ws;

		if (ws == null)
			throw new IllegalArgumentException("Invalid value");
	}

	@Override
	void execute(RoadMap map) {

		for (Pair<String, Weather> r : ws) {
			if (map.getRoads().contains(map.getRoad(r.getFirst().toString()))) 
				map.getRoad(r.getFirst()).setWeather(r.getSecond());
			else
				throw new NullPointerException("No existe la carretera " + r.getFirst().toString());
				
		
			
		}

	}

	@Override
	public String toString() {
		String s = "Change Weather [";
		for (Pair<String, Weather> pair : ws) {
			s += "(" + pair.getFirst() + ",";
			s += pair.getSecond() + ")";
		}
		s += "]";
		return s;
	}

}

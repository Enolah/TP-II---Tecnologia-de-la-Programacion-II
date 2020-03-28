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
	//	if(map.getRoad(map.))
		for (Pair<String, Weather> w : ws) {
			w.getSecond();
		}
		
	}

}

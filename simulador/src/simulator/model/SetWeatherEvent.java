package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event{

	SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
		super(time);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute(RoadMap map) {
		// TODO Auto-generated method stub
		
	}

}

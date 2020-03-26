package simulator.factories;

import org.json.JSONObject;

import simulator.model.LigthSwitchingStrategy;

public class RoundRobinStrategyBuilder extends Builder<LigthSwitchingStrategy>{

	RoundRobinStrategyBuilder(String type) {
		super(type);
	
	}

	@Override
	protected LigthSwitchingStrategy createTheInstance(JSONObject data) {
		
		JSONObject jo1 = new JSONObject();
		data.put("type", _type);
		jo1.put("timeslot", 1);
		data.put("data", jo1);
		
		return (LigthSwitchingStrategy)data;
	}

}

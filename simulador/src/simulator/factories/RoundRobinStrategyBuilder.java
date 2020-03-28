package simulator.factories;

import org.json.JSONObject;

import simulator.model.LigthSwitchingStrategy;
import simulator.model.RoundRobinStrategy;

public class RoundRobinStrategyBuilder extends Builder<LigthSwitchingStrategy>{

	public RoundRobinStrategyBuilder(String type) {
		super(type);
	
	}

	@Override
	protected LigthSwitchingStrategy createTheInstance(JSONObject data) {
		
		if( data!= null){
			RoundRobinStrategy e = new RoundRobinStrategy(data.getInt("timeslot"));
		}
		JSONObject jo1 = new JSONObject();
		data.put("type", _type);
		jo1.put("timeslot", 1);
		data.put("data", jo1);
		
		return new RoundRobinStrategy(data.getInt("timeslot"));
	}

}

package simulator.factories;

import java.util.List;

import org.json.JSONObject;

import simulator.model.LigthSwitchingStrategy;
import simulator.model.MostCrowdedStrategy;


public class MostCrowdedStrategyBuilder extends Builder<LigthSwitchingStrategy>{

	private MostCrowdedStrategy e;
	public MostCrowdedStrategyBuilder(String type) {
		super(type);
		
	}

	@Override
	protected LigthSwitchingStrategy createTheInstance(JSONObject data) {
	
		if( data!= null){
			if(data.has("timeslot"))
				e= new MostCrowdedStrategy(data.getInt("timeslot"));
			else 
				e= new MostCrowdedStrategy(1);
		}
		
		return e;
	}
	
	

}

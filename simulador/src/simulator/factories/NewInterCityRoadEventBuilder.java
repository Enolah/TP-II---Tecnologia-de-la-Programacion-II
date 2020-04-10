package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.NewInterCityRoadEvent;
import simulator.model.Weather;

public class NewInterCityRoadEventBuilder extends NewRoadEventBuilder{

	
	public NewInterCityRoadEventBuilder(String type) {
		super(type);
		
	}

	@Override
	Event createTheRoad() {
		
		return new NewInterCityRoadEvent(getTime(),getId(),getSrcJun(),getDestJun(),
				getLenght(),getCo2limit(),getMaxSpeed(),getWea());

	}


}

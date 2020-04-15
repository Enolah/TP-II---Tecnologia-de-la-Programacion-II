 package simulator.factories;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;

public class NewCityRoadEventBuilder extends NewRoadEventBuilder{

	
	
	public NewCityRoadEventBuilder(String type) {
		super(type);
		
	}


	@Override
	Event createTheRoad() {
		
		return new NewCityRoadEvent(getTime(),getId(),getSrcJun(),getDestJun(),
				getLenght(),getCo2limit(),getMaxSpeed(),getWea());

	}

}

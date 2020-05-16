package simulator.factories;

import simulator.model.Event;
import simulator.model.NewInterCityRoadEvent;

public class NewInterCityRoadEventBuilder extends NewRoadEventBuilder {

	public NewInterCityRoadEventBuilder(String type) {
		super(type);

	}

	@Override
	Event createTheRoad() {

		return new NewInterCityRoadEvent(getTime(), getId(), getSrcJun(), getDestJun(), getLenght(), getCo2limit(),
				getMaxSpeed(), getWea());

	}

}

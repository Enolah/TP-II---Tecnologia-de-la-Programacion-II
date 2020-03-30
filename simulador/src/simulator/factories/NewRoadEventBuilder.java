package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.Road;

public class NewRoadEventBuilder extends Builder<Event>{

	public NewRoadEventBuilder(String type) {
		super(type);
		
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		return null;
	}

}

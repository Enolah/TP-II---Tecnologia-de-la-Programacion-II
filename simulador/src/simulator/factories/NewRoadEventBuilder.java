package simulator.factories;

import org.json.JSONObject;

import simulator.model.Road;

public class NewRoadEventBuilder extends Builder<Road>{

	public NewRoadEventBuilder(String type) {
		super(type);
		
	}

	@Override
	protected Road createTheInstance(JSONObject data) {
		return null;
	}

}

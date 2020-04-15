package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event>{

	private NewVehicleEvent e;
	public NewVehicleEventBuilder(String type) {
		super(type);
		
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		//comprobamos que no es null
		if(data!=null){
			//parseamos el JSON
			if(data.has("time")&& data.has("id")&& data.has("maxspeed")&&
					data.has("class")&& data.has("itinerary")){
				List<String> listJ = new ArrayList<>();
				List<Object> a =data.getJSONArray("itinerary").toList();
				for (int i = 0; i < a.size(); i++) {
					listJ.add((String) a.get(i));
				}
				e= new NewVehicleEvent(data.getInt("time"),data.getString("id"),data.getInt("maxspeed"), 
						data.getInt("class"),listJ);
			}
		}
		return e;
	}

}

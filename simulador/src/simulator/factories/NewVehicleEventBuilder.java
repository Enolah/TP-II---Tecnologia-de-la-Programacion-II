package simulator.factories;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Junction;
import simulator.model.Vehicle;

public class NewVehicleEventBuilder extends Builder<Vehicle>{

	private Vehicle v;
	public NewVehicleEventBuilder(String type) {
		super(type);
		
	}

	@Override
	protected Vehicle createTheInstance(JSONObject data) {
		
		//comprobamos que no es null
		if(data!=null){
			//parseamos el JSON
			if(data.has("time")&& data.has("id")&& data.has("maxspeed")&&
					data.has("class")&& data.has("itinerary")){
				List<Junction> listJ = null;
				List<Object> a =data.getJSONArray("itinerary").toList();
				for (int i = 0; i < a.size(); i++) {
					listJ.add((Junction) a.get(i));
				}
				v= new Vehicle(data.getString("id"),data.getInt("maxspeed"), 
						data.getInt("class"),listJ);
			}
		}
		return v;
	}

}

package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.Weather;

public class NewCityRoadEventBuilder extends Builder<Event>{

	private NewCityRoadEvent e;
	private Weather w;
	
	public NewCityRoadEventBuilder(String type) {
		super(type);
		
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		if(data!= null){
			if(data.has("time")&& data.has("id")&& data.has("src")&&data.has("dest")&&
					data.has("length")&& data.has("co2limit")&&data.has("maxspeed")&&data.has("weather")){
			
				String posiblew= data.getString("weather").toUpperCase();
				
				e= new NewCityRoadEvent(data.getInt("time"),data.getString("id"),data.getString("src"),data.getString("dest"),
					data.getInt("length"),data.getInt("co2limit"),data.getInt("maxspeed"),w.valueOf(posiblew));
			}
		}
		
		return e;
	}

}

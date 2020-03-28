package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewInterCityRoadEvent;
import simulator.model.Weather;

public class NewInterCityRoadEventBuilder extends Builder<Event>{

	private NewInterCityRoadEvent e;
	private Weather w;
	
	public NewInterCityRoadEventBuilder(String type) {
		super(type);
		
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		if(data!= null){
			if(data.has("time")&& data.has("id")&& data.has("src")&&data.has("dest")&&
					data.has("length")&& data.has("co2limit")&&data.has("maxspeed")&&data.has("weather")){
			
				w= (Weather)data.get("weather");
				e= new NewInterCityRoadEvent(data.getInt("time"),data.getString("id"),data.getString("src"),data.getString("dest"),
					data.getInt("length"),data.getInt("co2limit"),data.getInt("maxspeed"),w);
			}
		}
		
		return e;
	}

}

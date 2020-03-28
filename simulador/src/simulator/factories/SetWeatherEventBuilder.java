package simulator.factories;

import java.util.List;

import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class SetWeatherEventBuilder extends Builder<Event>{

	private SetWeatherEvent e = null;
	private List<Pair<String,Weather>> ws;

	
	SetWeatherEventBuilder(String type) {
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {

		if(data!= null){
			if(data.has("time")&&data.has("info")){
						
				for (int i = 0; i <data.getJSONObject("info").length(); i++) {
					ws.add((Pair)data.getJSONObject("info").toMap());
				}
				try{
				e= new SetWeatherEvent(data.getInt("time"), ws);
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
		
		return e;
	}

}

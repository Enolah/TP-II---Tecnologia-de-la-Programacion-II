package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class SetWeatherEventBuilder extends Builder<Event>{

	private SetWeatherEvent e = null;
	private List<Pair<String,Weather>> ws;
	private Pair<String,Weather> p;
	private Weather wea;

	
	public SetWeatherEventBuilder(String type) {
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {

		if(data!= null){
			if(data.has("time")&&data.has("info")){
						
				for (int i = 0; i <data.getJSONArray("info").length(); i++) {
					JSONObject o= (JSONObject) data.getJSONArray("info").get(i);
					String s= o.getString("road");
					String w= o.getString("weather").toUpperCase();
					ws= new ArrayList<Pair<String,Weather>>();
					ws.add(new Pair(s,wea.valueOf(w)));
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

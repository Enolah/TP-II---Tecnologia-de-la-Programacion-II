package simulator.factories;

import java.util.List;

import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetContClassEvent;


public class SetContClassEventBuilder extends Builder<Event>{

	private SetContClassEvent e = null;
	private List<Pair<String,Integer>> p;
	public SetContClassEventBuilder(String type) {
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		if(data!= null){
			if(data.has("time")&&data.has("info")){
						
				for (int i = 0; i <data.getJSONObject("info").length(); i++) {
					p.add((Pair)data.getJSONObject("info").toMap());
				}
				try{
				e= new SetContClassEvent(data.getInt("time"), p);
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
		
		return e;
	}

}

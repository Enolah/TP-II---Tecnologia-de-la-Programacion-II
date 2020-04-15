package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetContClassEvent;


public class SetContClassEventBuilder extends Builder<Event>{

	private SetContClassEvent e = null;
	private List<Pair<String,Integer>> c;

	
	public SetContClassEventBuilder(String type) {
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		if(data!= null){
			if(data.has("time")&&data.has("info")){
				c= new ArrayList<Pair<String,Integer>>();
				for (int i = 0; i <data.getJSONArray("info").length(); i++) {
					JSONObject o= (JSONObject) data.getJSONArray("info").get(i);
					String s= o.getString("vehicle");
					Integer j= o.getInt("class");
					
					c.add(new Pair(s,j));
				}
				try{
				e= new SetContClassEvent(data.getInt("time"), c);
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
		
		return e;
	}

}

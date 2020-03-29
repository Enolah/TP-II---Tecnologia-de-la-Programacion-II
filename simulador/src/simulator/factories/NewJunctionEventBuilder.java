package simulator.factories;

import java.awt.peer.LightweightPeer;
import java.util.List;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.LigthSwitchingStrategy;
import simulator.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends Builder<Event>{

	private Factory<LigthSwitchingStrategy> lssFactory;
	private LigthSwitchingStrategy ls;
	private Factory<DequeuingStrategy> dqsFactory;
	private DequeuingStrategy dq;
	private Event e= null;
	
	public NewJunctionEventBuilder(String type, Factory<LigthSwitchingStrategy>
	lssFactory, Factory<DequeuingStrategy> dqsFactory) {
		super(type);
		this.lssFactory=lssFactory;
		this.dqsFactory=dqsFactory;	
	}
	

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		if( data!= null){
			if(data.has("time")&& data.has("id") && data.has("coor") && 
					data.has("ls_strategy") && data.has("dq_strategy")){
				//coordenadas
				Integer x = (Integer)data.getJSONArray("coor").toList().get(0);
				Integer y=(Integer)data.getJSONArray("coor").toList().get(1);
				
				//lss
				ls=lssFactory.createInstance(data.getJSONObject("ls_strategy"));
				//dqs 
				dq=dqsFactory.createInstance(data.getJSONObject("dq_strategy"));
				
				e= new NewJunctionEvent(data.getInt("time"), data.getString("id"), ls, dq, x, y);
			}
		}
		
		
		return e;
	}

}

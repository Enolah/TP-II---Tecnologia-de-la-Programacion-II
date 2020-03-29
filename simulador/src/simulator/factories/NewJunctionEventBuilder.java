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
	private Factory<DequeuingStrategy> dqsFactory;
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
				lssFactory= (Factory<LigthSwitchingStrategy>) data.getJSONObject("ls_strategy");
				LigthSwitchingStrategy ls =(LigthSwitchingStrategy) lssFactory;
				//dqs 
				dqsFactory= (Factory<DequeuingStrategy>) data.getJSONObject("dq_strategy");
				DequeuingStrategy dq= (DequeuingStrategy) dqsFactory;
				
				e= new NewJunctionEvent(data.getInt("time"), data.getString("id"), ls, dq, x, y);
			}
		}
		
		
		return e;
	}

}

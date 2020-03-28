package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LigthSwitchingStrategy;

public class NewJunctionEventBuilder extends Builder<Event>{

	private Factory<LigthSwitchingStrategy> lssFactory;
	private Factory<DequeuingStrategy> dqsFactory;
	
	public NewJunctionEventBuilder(String type, Factory<LigthSwitchingStrategy>
	lssFactory, Factory<DequeuingStrategy> dqsFactory) {
		super(type);
		this.lssFactory=lssFactory;
		this.dqsFactory=dqsFactory;	
	}
	

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		
		
		
		return null;
	}

}

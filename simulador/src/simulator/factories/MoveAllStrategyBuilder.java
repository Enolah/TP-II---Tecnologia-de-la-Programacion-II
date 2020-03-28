package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.MoveAllStrategy;

public class MoveAllStrategyBuilder extends Builder<DequeuingStrategy>{

	
	public MoveAllStrategyBuilder(String type) {
		super(type);
	
	}

	@Override
	protected DequeuingStrategy createTheInstance(JSONObject data) {

		//La clave “data” se puede omitir ya que no incluye ninguna información.
		      
		
		return new MoveAllStrategy();
	}

}
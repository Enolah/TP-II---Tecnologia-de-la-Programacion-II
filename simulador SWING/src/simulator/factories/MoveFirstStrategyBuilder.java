package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.MoveFirstStrategy;

public class MoveFirstStrategyBuilder extends Builder<DequeuingStrategy>{

	public MoveFirstStrategyBuilder(String type) {
		super(type);
	
	}

	@Override
	protected DequeuingStrategy createTheInstance(JSONObject data) {
	
		//La clave �data� se puede omitir ya que no incluye ninguna informaci�n.
		
		return new MoveFirstStrategy();
	}

}

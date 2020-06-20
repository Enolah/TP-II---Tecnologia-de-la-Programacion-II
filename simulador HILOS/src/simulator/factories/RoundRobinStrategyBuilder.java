package simulator.factories;

import org.json.JSONObject;

import simulator.model.LigthSwitchingStrategy;
import simulator.model.RoundRobinStrategy;

public class RoundRobinStrategyBuilder extends Builder<LigthSwitchingStrategy> {

	private RoundRobinStrategy e;

	public RoundRobinStrategyBuilder(String type) {
		super(type);

	}

	@Override
	protected LigthSwitchingStrategy createTheInstance(JSONObject data) {

		if (data != null) {
			if (data.has("timeslot"))
				e = new RoundRobinStrategy(data.getInt("timeslot")); // es opcional
			else
				e = new RoundRobinStrategy(1); // por defecto es 1
		}

		return e;
	}

}

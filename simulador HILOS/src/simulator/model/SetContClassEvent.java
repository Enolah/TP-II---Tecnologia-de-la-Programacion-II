package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetContClassEvent extends Event {

	private List<Pair<String, Integer>> cs;

	public SetContClassEvent(int time, List<Pair<String, Integer>> cs) {
		super(time);
		this.cs = cs;
		if (cs == null)
			throw new IllegalArgumentException("Invalid value");
	}

	@Override
	void execute(RoadMap map) {

		for (Pair<String, Integer> c : cs) {
			if (map.getVehicles().contains(map.getVehicle(c.getFirst().toString())))
				map.getVehicle(c.getFirst()).setContaminationClass(c.getSecond());
			else
				throw new IllegalArgumentException("No existe el vehiculo " + c.getFirst().toString());
		}
	}

	@Override
	public String toString() {

		String s = "Change CO2 class: [";
		for (Pair<String, Integer> pair : cs) {
			s += "(" + pair.getFirst() + ",";
			s += pair.getSecond() + ")";

		}
		s += "]";
		return s;
	}

}

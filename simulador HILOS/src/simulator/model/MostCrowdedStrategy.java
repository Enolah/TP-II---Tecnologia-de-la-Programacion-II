package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LigthSwitchingStrategy {

	private int timeSlot;

	public MostCrowdedStrategy(int timeSlot) {
		super();
		this.timeSlot = timeSlot;
	}

	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		int tama = -1;
		int indice = -1;
		if (roads == null)// 1
			indice = -1;
		else {
			if (currGreen == -1) {// 2
				for (List<Vehicle> list : qs) {
					if (qs.get(qs.indexOf(list)).size() > tama)
						indice = qs.indexOf(list);
				}
			} else if ((currTime - lastSwitchingTime) < timeSlot) { // 3
				indice = currGreen;

				int i = (currGreen + 1) % roads.size();
				for (int j = 0; j <= roads.size(); j++) {
					if (qs.get(i).size() > tama)
						indice = i;

					i = (i + 1) % roads.size();
				}
			}
		}
		return indice;
	}
}

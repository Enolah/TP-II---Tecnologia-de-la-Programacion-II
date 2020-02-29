package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LigthSwitchingStrategy {

	public MostCrowdedStrategy(int timeSlot) {
		super();
	}

	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		int tama = -1;
		int indice = -1;
		boolean encontrado = false;
		if (roads == null)
			indice = -1;
		else {
			if (currGreen == -1) {
				
				for (List<Vehicle> list : qs) {
					if (qs.get(qs.indexOf(list)).size()>tama)
						indice = qs.indexOf(list);
				}
			}
			else {

				int i = (currGreen + 1) % qs.size();
				for (int j = 0; j <= qs.size(); j++) {
					if (qs.get(i).size() > tama)
						indice = i;

					i = (i + 1) % qs.size();
				}
			}
		}
		return indice;
	}
}


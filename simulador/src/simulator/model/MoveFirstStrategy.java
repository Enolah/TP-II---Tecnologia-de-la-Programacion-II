package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoveFirstStrategy implements DequeuingStrategy {

	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		List<Vehicle> a = new ArrayList<>();
		Collections.copy(a, q);
		for (int i = 1; i <= a.size(); i++) { // elimino todos los elementos de
												// la lista, excepto el primero
			if (a.get(i) != null) {
				a.remove(i);
			}
		}
		return a;
	}

}

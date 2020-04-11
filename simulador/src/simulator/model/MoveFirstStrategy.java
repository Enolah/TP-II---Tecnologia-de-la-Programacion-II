package simulator.model;

import java.util.LinkedList;
import java.util.List;

public class MoveFirstStrategy implements DequeuingStrategy {

	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		List<Vehicle> a = new LinkedList<Vehicle>();
		if (q.size() != 0) {
			a.add(q.get(0));
	
			for (int i = 1; i < a.size(); i++) { 
		// elimino todos los elementos de la lista, excepto el primero							
				if (a.get(i) != null) {
					a.remove(i);
				}
			}
		}
		return a;
	}

}

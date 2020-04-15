package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MoveAllStrategy implements DequeuingStrategy{

	
	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		List<Vehicle> a = new LinkedList<Vehicle>();
		a.add(q.get(0));
		return a;
	}


}

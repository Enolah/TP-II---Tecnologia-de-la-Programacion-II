package simulator.model;

import java.util.LinkedList;
import java.util.List;

public class MoveAllStrategy implements DequeuingStrategy{

	
	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		List<Vehicle> a = new LinkedList<Vehicle>();
		
		if (q.size() != 0) {
			
			for (int i = 0; i < q.size(); i++) { 
				a.add(q.get(i));			
			}
		}
		return a;
	}


}

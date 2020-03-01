package simulator.model;

import java.util.Collections;
import java.util.List;

public class MoveFirstStrategy implements DequeuingStrategy{

	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		List<Vehicle> a = null;
		Collections.copy(a, q); 
		for (int i=1; i<=a.size(); i++){
			if(a.get(i)!=null){
				a.remove(i);
			}
		}
		return a;
	}

}

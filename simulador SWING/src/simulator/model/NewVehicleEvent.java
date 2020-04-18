package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleEvent extends Event {
	
	private String id;
	private int maxSpeed;
	private int contClass;
	private List<String> itinerary;
	
	
	private Vehicle v;

	public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
		super(time);
		
		this.id=id;
		this.maxSpeed=maxSpeed;
		this.contClass=contClass;
		this.itinerary= itinerary;
	}

	@Override
	void execute(RoadMap map) {
		 List<Junction> iti = new ArrayList<>();
		for (int i = 0; i <itinerary.size(); i++) {
			iti.add(map.getJunction(itinerary.get(i)));
		}
		
		v= new Vehicle(id, maxSpeed, contClass, iti);
		map.addVehicle(v);
		v.moveToNextRoad();
		
	}
	
	@Override
	public String toString() {
		//TODO devuelve su descripcion (imagino que completa)
	return "New Vehicle '" + id + "'" ;
	}

}

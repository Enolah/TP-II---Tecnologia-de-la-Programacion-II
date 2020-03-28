package simulator.model;

import java.util.List;

public class NewVehicleEvent extends Event {
	
	private String id;
	private int maxSpeed;
	private int contClass;
	private List<String> itinerary;

	public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
		super(time);
		
		this.id=id;
		this.maxSpeed=maxSpeed;
		this.contClass=contClass;
		this.itinerary= itinerary;
	}

	@Override
	void execute(RoadMap map) {
		
		Vehicle v= new Vehicle(id, maxSpeed, contClass, map.getVehicle(id).getItinerary());
		map.setVehicle(v);
		
	}

}

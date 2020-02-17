package simulator.model;

import java.util.List;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject {

	private List <Juction> itinerary;
	private int speedMax;
	private int speed;
	private VehicleStatus status;
	private Road road;
	private int location;
	private int levelPollution;
	private int totalPollution;
	private int totalDistance;
	
	
	 Vehicle (String id,int maxSpeed, int contClass, List<Juction> itinerary){
		super(id);
		//TODO complete
	}

	//getters ans setters
	
	public int getLocation() {
		return 0;
	}
	public int getSpeed(){
		return 0;
	}
	public int getContClass(){
		return 0;
	}
	public VehicleStatus getStatus(){
		return null;
	}
	public List<Juction> getItinerary(){
		return null;
	}
	public Road getRoad(){
		return null;
	}
	
	public void setSpeed(int s);
	public void setContaminationClass(int c);
	
	//metodos
	
	void moveToNextRoad(){
		
	}
	
	
	// metodos  de por ahi
	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}
}

package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject {

	private List <Junction> itinerary;
	private int maxSpeed;
	private int actSpeed;
	private VehicleStatus status;
	private Road road; //va ir actualizandose
	private int location;
	private int contClass; //grado de contaminacion del vehiculo
	private int totalPollution;
	private int totalDistance;
	
	
	 Vehicle (String id,int maxSpeed, int contClass, List<Junction> itinerary){
		super(id);
		//TODO lanzar excepciones
		this.maxSpeed= maxSpeed;
		this.contClass=contClass;
		this.itinerary= itinerary;
			if(maxSpeed <= 0)throw new IllegalArgumentException("Invalid value for maxSpeed");
			if (contClass <0 || contClass > 10)throw new IllegalArgumentException ("Invalid value for contClass");
			if (itinerary.size()>= 2)throw new IllegalArgumentException ("Invalid value for itinerary");
				
			
		
		this.itinerary=Collections.unmodifiableList(new ArrayList<>(itinerary));
		
	}

	//getters ands setters
	
	public int getLocation() {
		return location;
	}
	public int getSpeed(){
		return actSpeed;
	}
	public int getContClass(){
		return 0;
	}
	public VehicleStatus getStatus(){
		return null;
	}
	public List<Junction> getItinerary(){
		return null;
	}
	public Road getRoad(){
		return null;
	}
	
	protected void setSpeed(int s) {
		if (s < 0)
			throw new IllegalArgumentException("Invalid value for Speed, cannot be negative");
		else
			actSpeed = Math.min(s, maxSpeed);
	}
	
	public void setContaminationClass(int c){
			if(c<0 || c> 10) 
				throw new IllegalArgumentException("Invalid value for Speed, cannot be negative");
			else
				contClass= c;
	}
	
	//metodos
	
	@Override
	void advance(int time) {
		//TODO advance
		int locationPrev=location;
		if(status!=status.TRAVELING){
			//a) se actualiza la localizacion
			location= Math.min(getLocation() + actSpeed,itinerary.size());			
			//b)c=l*f
			totalDistance= location-locationPrev;
			totalPollution=contClass*(totalDistance);
			road.addContamination(totalPollution);
			//c)
			if (location==road.getLength())
				//vehiculo entra en cola del J
				
				//cambiar estado
				status= status.WAITING;
		}
		
	}
	
	void moveToNextRoad(){
		//TODO moveTONextRoad
		road.exit(this);
		road.enter(this);
		//encontrar siguiente carretera (preguntar al cruce)
		
		
		
	}
	



	@Override
	public JSONObject report() {
		JSONObject jo1 = new JSONObject();
	
		jo1.put("id", _id);
		jo1.put("speed", actSpeed);
		jo1.put("distance", totalDistance);
		jo1.put("co2", totalPollution);
		jo1.put("class", contClass);
		jo1.put("status", status);
		if (status == status.ARRIVED || status == status.PENDING);
		else {
			jo1.put("road", road);
			jo1.put("location", location);
		}
		
		return jo1;
	}
}

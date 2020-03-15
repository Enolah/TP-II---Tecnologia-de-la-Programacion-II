package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;



public class Vehicle extends SimulatedObject {

	private List <Junction> itinerary; //itinerario
	private int maxSpeed; //velocidad maxima
	private int actSpeed; //velocidad actual
	private VehicleStatus status; //estado
	private Road road; //carretera
	private int location; //localizacion
	private int contClass; //grado de contaminacion del vehiculo
	private int totalPollution; //contaminacion total
	private int totalDistance; //distancia total recorrida
	private int indice=0;
	
	 Vehicle (String id,int maxSpeed, int contClass, List<Junction> itinerary){
		super(id);
		
		this.maxSpeed= maxSpeed;
		this.contClass=contClass;
		this.itinerary=Collections.unmodifiableList(new ArrayList<>(itinerary));
			if(maxSpeed <= 0)throw new IllegalArgumentException("Invalid value for maxSpeed");
			if (contClass <0 || contClass > 10)throw new IllegalArgumentException ("Invalid value for contClass");
			if (itinerary.size()< 2)throw new IllegalArgumentException ("Invalid value for itinerary");
					
	}

	 /*
	  * GET & SET
	  */
	
	public int getLocation() {
		return location;
	}
	public int getSpeed(){
		return actSpeed;
	}
	public int getContClass(){
		return contClass;
	}
	public VehicleStatus getStatus(){
		return status;
	}
	public List<Junction> getItinerary(){
		return Collections.unmodifiableList(new ArrayList<>(this.itinerary));
	}
	public Road getRoad(){
		return road;
	}
	
	protected void setSpeed(int s) {
		if (s < 0)
			throw new IllegalArgumentException("Invalid value for Speed, cannot be negative");
		else
			actSpeed = Math.min(s, maxSpeed);
	}
	
	public void setContaminationClass(int c){
		
			if(c<0 || c> 10) 
				throw new IllegalArgumentException("Invalid value for ContaminationClass");
			else
				contClass= c;
	}
	
	/*
	 * METODOS
	 */
	
	@Override
	void advance(int time) {
		//TODO advance
		int locationPrev=location;
		Junction j=null;
		if(status!=status.TRAVELING){
			//a) se actualiza la localizacion
			location= Math.min(location + actSpeed,itinerary.size());			
			//b)
			totalDistance= location-locationPrev; //l
			totalPollution=contClass*(totalDistance);//c=l*f
			setContaminationClass(totalPollution);
			road.addContamination(totalPollution);
			//c)
			if (location==road.getLength()){
				//vehiculo entra en cola del J
				j.enter(this);
				//cambiar estado
				status= status.WAITING;
			}
		}
		
	}
	
	void moveToNextRoad(){
		//TODO moveTONextRoad
		
		Junction j=null;
		//1. sales de la carretera actual
		//2. entras en la siguiente carretera (location=0)
		if(status==status.PENDING) //la primera vez el vehiculo no sale de ninguna carretera
			road=j.roadTo(itinerary.get(indice)); //inidice debe ser =0
		else if( status== status.WAITING){ //la ultima vez no se entra a ninguna carretera
			road.exit(this);
			indice++;
			road=j.roadTo(itinerary.get(indice));
		}
		else
			throw new IllegalArgumentException("Vehicle Status incorrect");
		
		
		this.location=0;
		this.actSpeed=0; //no estoy segura
		road.enter(this);
	
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
			jo1.put("road", road._id);
			jo1.put("location", location);
		}
		
		return jo1;
	}
		
}

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
		this.status= status.PENDING;
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
	
	public int getTotalPollution() {
		return totalPollution+road.getTotalPollution();
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
		int pollution =totalPollution;
		int locationPrev=location;
		if(status==status.TRAVELING){
			//a) se actualiza la localizacion
			location= Math.min((location + actSpeed),road.getLength());			
			//b)
			int c= location-locationPrev; //l
			totalDistance+=c;//sumo la contaminacion a la cont
			totalPollution+=contClass*(c);//c=f*l
			road.addContamination(totalPollution-pollution);
			//c)
			if (location==road.getLength()){
				//vehiculo entra en cola del J
				road.getDest().enter(this);
				//cambiar estado
	//			indice++; //es el estado de su itinerario
				status= status.WAITING;
			}
		}
		
	}
	
	void moveToNextRoad(){
		
		//1. sales de la carretera actual
		//2. entras en la siguiente carretera (location=0)
		if(status==status.PENDING){ //la primera vez el vehiculo no sale de ninguna carretera	
			road=itinerary.get(indice).roadTo(itinerary.get(indice)); //inidice debe ser =0
			road.enter(this);
			this.status=status.TRAVELING;
		}
		else if( status== status.WAITING){ 
			road.exit(this);

			if(indice == (itinerary.size()-1)){//la ultima vez no se entra a ninguna carretera
				status= status.ARRIVED;
			}
			else{
			indice++;
			road=itinerary.get(indice).roadTo(itinerary.get(indice));
			this.location=0;
			this.actSpeed=0; 
			road.enter(this);
			this.status=status.TRAVELING;
			
			}
		}
		else if (status == status.ARRIVED){
			//no se hace nada pq el vehiculo ya ha llegado al final de su itinerario
		}
		else
			throw new IllegalArgumentException("Vehicle Status incorrect");
	
	}
	

	@Override
	public JSONObject report() {
		
		JSONObject jo1 = new JSONObject();
	
		jo1.put("id", getId());
		jo1.put("speed", this.getSpeed());
		jo1.put("distance", this.totalDistance);
		jo1.put("co2",this.totalPollution);
		jo1.put("class", this.getContClass());
		jo1.put("status", this.getStatus());
		if (status == status.ARRIVED || status == status.PENDING);
		else {
			jo1.put("road", road.getId());
			jo1.put("location", this.getLocation());
		}
		
		return jo1;
	}

	
		
}

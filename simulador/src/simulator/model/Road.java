package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import simulator.misc.SortedArrayList;

public abstract class Road extends SimulatedObject {

	private Junction dest; // cruce destino
	private Junction src; // cruce origen
	private int lenght; // longitud
	private int maxSpeed; // velocidad maxima
	private int limitSpeed; // limite actual de velocidad
	private int contLimit; // alarma por contaminacion excesiva
	private Weather wea; // condiciones ambientales
	private int totalPollution; // contaminacion total: suma acumulada del CO2
								// de todos los coches
	private List<Vehicle> listV; // vehiculos que circulan por dicha carretera
	private MiCompi comparador; // Mi comparador
	
	Road (String id, Junction srcJunc, Junction destJunc, int maxSpeed,
			int contLimit, int lenght, Weather weather){
		super(id);
		this.src= srcJunc;
		this.dest= destJunc;
		this.maxSpeed= maxSpeed;
		this.contLimit= contLimit;
		this.lenght= lenght;
		this.wea= weather;
		
		comparador = new MiCompi();
		if (maxSpeed <=0)throw new IllegalArgumentException("Invalid value for maxSpeed");
		if (contLimit<0)throw new IllegalArgumentException("Invalid value for conLimit");
		if (lenght <=0)throw new IllegalArgumentException("Invalid value for lenght");
		if (src==null || dest==null || wea==null)throw new IllegalArgumentException("Invalid value, cannot be NULL");
	}
	
	/*
	 * GET & SET
	 */
	
	public int getLength() {
		return lenght;
	}
	public Junction getDest() {
		return dest;
	}
	public Junction getSrc() {
		return src;
	}
	
	public int getMaxSpeed(){
		return maxSpeed;
	}
	
	public int getContLimit(){
		return contLimit;
	}
	
	public int getLimitSpeed(){
		return limitSpeed;
	}
	
	public int getTotalPollution(){
		return totalPollution;
	}
	public void setLimitSpeed(int s){
		limitSpeed=s;
	}

	public void setTotalPollution(int p){
		if(p<0)throw new IllegalArgumentException("Invalid value, cannot be negative");
		totalPollution=p;
	}
	public void setWeather(Weather w){
		if( w == null)throw new IllegalArgumentException("Invalid value, cannot be NULL");
		else{
			wea=w;
		}
	}
	
	public Weather getWeather(){
		return 	wea;
	}
	public List<Vehicle> getListV() {
		return Collections.unmodifiableList(new ArrayList<>(this.listV));
	}
	
	/*
	 * METODOS
	 */
	
	void enter(Vehicle v){
		if (v.getLocation()==0 && v.getSpeed()==0){
			listV.add(v);
		}
		else throw new IllegalArgumentException("Invalid value, can be zero");
	}
	
	void exit (Vehicle v){
		listV.remove(v);
	}
	
	void addContamination(int c){
		if (c<0)throw new IllegalArgumentException("Invalid value for Speed, cannot be negative");
		else
			totalPollution+=c;
	}
	
	abstract void reduceTotalContamination();
	abstract void updateSpeedLimit();
	abstract int calculateVehicleSpeed(Vehicle v);
	
	public class MiCompi implements Comparator<Vehicle>{

		public int compare(Vehicle v0, Vehicle v1) {
			int ok=-1;
			if (v0.getLocation()<v1.getLocation()) ok=1;
			else if (v0.getLocation()==v1.getLocation()) ok=0;
			else if (v0.getLocation()>v1.getLocation()) ok=-1;
			return ok;
		}
		
	}
	void advance (int time){//cada carretera hace el advance del vehiculo
		//1. llama a reduce totsl contamination
		reduceTotalContamination();
		//2. updateSpeedLimit
		updateSpeedLimit();
		//3. recorre listV , b) advance de vehiculo
		
		for (Vehicle v : listV) {
			try{
				//a) velocidad= calculate
				v.setSpeed(calculateVehicleSpeed(v));
			}
			catch (Exception e) {
				System.out.println(e);
			}
			v.advance(time);
			//lista.sort??
		}
		//RECUERDA ORDENAR LA LISTA
		listV.sort(comparador);
	} 
	

	public JSONObject report() {
		//TODO esto no es vehiculo?
		JSONObject jo1 = new JSONObject();
		
		jo1.put("id", _id);
		jo1.put("speedLimit", limitSpeed);
		jo1.put("weather", wea);
		jo1.put("co2", totalPollution);
		
		JSONArray jo2 = new JSONArray();
		for (Vehicle v : listV) {
			jo2.put(v.getId());
		}
		
		jo1.put("vehicles", jo2);

		return jo1;
	}


	
	

	
		

		
		
	
	
	
}

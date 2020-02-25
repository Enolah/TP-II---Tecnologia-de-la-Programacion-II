package simulator.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Road extends SimulatedObject {

	private Junction dest;
	private Junction src;
	private int lenght;
	private int maxSpeed;
	private int limitSpeed;
	private int contLimit; //alarma por contaminacion excesiva
	private Weather wea;
	private int totalPollution; //suma acumulada de todos los coches
	private List<Vehicle> listV;
	
	Road (String id, Junction srcJunc, Junction destJunc, int maxSpeed,
			int contLimit, int lenght, Weather weather){
		super(id);
		this.src= srcJunc;
		this.dest= destJunc;
		this.maxSpeed= maxSpeed;
		this.contLimit= contLimit;
		this.lenght= lenght;
		this.wea= weather;
		
		if (maxSpeed <=0)throw new IllegalArgumentException("Invalid value for maxSpeed");
		if (contLimit<0)throw new IllegalArgumentException("Invalid value for conLimit");
		if (lenght <=0)throw new IllegalArgumentException("Invalid value for lenght");
		if (src==null || dest==null || wea==null)throw new IllegalArgumentException("Invalid value, cannot be NULL");
	}
	
	//getters and setters
	
	public int getLength() {
		return 0;
	}
	public Junction getDest() {
		return null;
	}
	public Junction getSrc() {
		return null;
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
	public void setLimitSpeed(int s){
		limitSpeed=s;
	}
	
	public int getTotalPollution(){
		return totalPollution;
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
	//getListV { return collectio.unmoasdhfl...}
	
	
	//metodos
	
	void enter(Vehicle v){
		//TODO ordenar lista?
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
	
	void advance (int time){//cada carretera hace el advance del vehiculo
		//1. llama a reduce totsl contamination
		reduceTotalContamination();
		//2. updateSpeedLimit
		updateSpeedLimit();
		//3. recorre listV a) velocidad= calculate, b) advance de vehiculo
		//RECUERDA ORDENAR LA LISTA
		for (Vehicle v : listV) {
			try{
				v.setSpeed(calculateVehicleSpeed(v));
			}
			catch (Exception e) {
				System.out.println(e);
			}
			v.advance(time);
		}
	} 
	
	abstract void reduceTotalContamination();
	abstract void updateSpeedLimit();
	abstract int calculateVehicleSpeed(Vehicle v);
	
	public JSONObject report() {
		JSONObject jo1 = new JSONObject();
		
		jo1.put("id", _id);
		jo1.put("speedLimit", limitSpeed);
		jo1.put("weather", wea);
		jo1.put("co2", contLimit);
		
		JSONArray jo2 = new JSONArray();
		for (Vehicle v : listV) {
			jo2.put(v.getId());
		}
		
		jo1.put("vehicles", jo2);

		return jo1;
	}
	
}

package simulator.model;

import java.util.List;

import org.json.JSONObject;

public abstract class Road extends SimulatedObject {

	private Junction Dest;
	private Junction Src;
	private int length;
	private int limitSpeed;
	private int alarmPollution;
	private Weather wea;
	private int totalPollution;
	private List<Vehicle> vehicle;
	
	Road (String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int lenght, Weather weather){
		super(id);
		//...
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
	public void setWeather(Weather w){}
	
	
	//metodos
	
	void enter(Vehicle v){}
	void exit (Vehicle v){}
	void addContamination(int c){}
	abstract void reduceTotalContamination();
	abstract void updateSpeedLimit();
	abstract int calculateVehicleSpeed(Vehicle v);
	void advance (int time){}
	public JSONObject report(){
		return null;}
	
}

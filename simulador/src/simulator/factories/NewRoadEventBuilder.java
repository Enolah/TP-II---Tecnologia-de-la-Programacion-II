package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.NewRoadEvent;
import simulator.model.Road;
import simulator.model.Weather;

public abstract class NewRoadEventBuilder extends Builder<Event>{

	private int time;
	private String id;
	private String srcJun;
	private String destJun;
	private int lenght;
	private int co2limit;
	private int maxSpeed;

	private Weather w;
	private NewRoadEvent e;
	
	public NewRoadEventBuilder(String type) {
		super(type);
	}

	/*
	 * Getters & setters
	 */
	
	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSrcJun() {
		return srcJun;
	}


	public void setSrcJun(String srcJun) {
		this.srcJun = srcJun;
	}


	public String getDestJun() {
		return destJun;
	}


	public void setDestJun(String destJun) {
		this.destJun = destJun;
	}


	public int getLenght() {
		return lenght;
	}


	public void setLenght(int lenght) {
		this.lenght = lenght;
	}


	public int getCo2limit() {
		return co2limit;
	}


	public void setCo2limit(int co2limit) {
		this.co2limit = co2limit;
	}


	public int getMaxSpeed() {
		return maxSpeed;
	}


	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public Weather getWea(){
		return w;
	}


	
	
	/*
	 * Metodos
	 */
	
	@Override
	protected Event createTheInstance(JSONObject data) {
		
		if(data!= null){
			if(data.has("time")&& data.has("id")&& data.has("src")&&data.has("dest")&&
					data.has("length")&& data.has("co2limit")&&data.has("maxspeed")&&data.has("weather")){
			
				this.time= data.getInt("time");
				this.id=data.getString("id");
				this.srcJun=data.getString("src");
				this.destJun=data.getString("dest");
				this.lenght= data.getInt("length");
				this.co2limit= data.getInt("co2limit");
				this.maxSpeed= data.getInt("maxspeed");
				
				String posiblew= data.getString("weather").toUpperCase();
				this.w= w.valueOf(posiblew);
			
				e= (NewRoadEvent) createTheRoad();
			}
		}
		
		return e;
	}
	
	abstract Event createTheRoad();


	

}

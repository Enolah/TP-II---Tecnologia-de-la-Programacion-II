package simulator.model;

import java.util.List;

import org.json.JSONObject;

public class Simulator {

	private RoadMap mapR;
	private List<Event> listE;
	private int tick; //no se como llamar al paso de la simulacion
	
	public Simulator(RoadMap mapR, List<Event> listE, int tick){
		this.mapR= mapR;
		this.listE= listE;
		this.tick= tick;
	}
	
	public void addEvent(Event e){
		
	}
	public void advance(){
		
	}
	public void reset(){
		
	}
	public JSONObject report(){
		return null;
	}
}

package simulator.model;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class Junction extends SimulatedObject{

	
	private List<Road> list;
	private Map<Junction,Road> map;
	private List<List<Vehicle>> ListQ;
	private int currGreen; //indice semafoto verde??
	private int lastGreen; //ultimo paso de cambio de semaforo?
	private LigthSwitchingStrategy lsStrategy;
	private DequeuingStrategy dqStrategy;
	private int xCoor, yCoor;
	
	Junction(String id, LigthSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		super(id);
		this.lsStrategy=lsStrategy;
		this.dqStrategy=dqStrategy;
		this.xCoor=xCoor;
		this.yCoor=yCoor;
		
		if (lsStrategy == null || dqStrategy==null)throw new IllegalArgumentException("Invalid value, cannot be NULL");
		if(xCoor<0||yCoor<0)throw new IllegalArgumentException("Invalid value,cannot be negative");
	}

	public void getX(){};
	public void getY(){};
	
	//metodos
	
	void addIncommingRoad(Road r){
		list.add(r);
	}
	
	void addOutGoingRoad(Road r){}
	void enter (Vehicle v){
		
	}
	Road roadTo (Junction j){
		
		
		return null;
	}
	public JSONObject report(){
		return null;}

	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		
	};
}

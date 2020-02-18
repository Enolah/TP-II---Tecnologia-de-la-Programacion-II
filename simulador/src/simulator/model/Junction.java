package simulator.model;

import java.util.List;
import java.util.Map;

public class Junction extends SimulatedObject{

	
	private List<Road> list;
	private Map<Junction,Road> map;
	private List<List<Vehicle>> q;
	private int currGreen; //indice semafoto verde??
	private int lastGreen; //ultimo paso de cambio de semaforo?
	private LigthSwitchingStrategy lsStrategy;
	private DequeuingStrategy dqStrategy;
	private int xCoor, yCoor;
	
	Junction(String id, LigthSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public getX(){};
	public getY(){};
	
	//metodos
	
	void addIncommingRoad(Road r){};
	void addOutGoingRoad(Road r){};
	void enter (Vehicle v){};
	Road roadTo (Junction j){};
	void advance(time){};
	public JSONObject report(){}

	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		
	};
}

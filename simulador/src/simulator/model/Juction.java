package simulator.model;

public class Juction extends SimulatedObject{

	private List<Road> list;
	private Map<Juction,Road> map;
	private List<List<Vehicle>> q;
	private int currGreen; //indice semafoto verde??
	private int lastGreen; //ultimo paso de cambio de semaforo?
	private LigthSwitchingStrategy lsStrategy;
	private DequeuingStrategy dqStrategy;
	private int xCoor, yCoor;
	
	Juction(String id, LigthSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public getX(){};
	public getY(){};
	
	//metodos
	
	void addIncommingRoad(Road r){};
	void addOutGoingRoad(Road r){};
	void enter (Vehicle v){};
	Road roadTo (Juction j){};
	void advance(time){};
	public JSONObject report(){};
}

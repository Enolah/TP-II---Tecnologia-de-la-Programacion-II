package simulator.model;

import java.util.List;
import java.util.Map;

public class RoadMap {
	
	private List<Junction> listJ;
	private List<Road> listR;
	private List<Vehicle> listV;
	private Map <String,Junction> mapJ;
	private Map <String,Road> mapR;
	private Map <String,Vehicle> mapV;
	
	public RoadMap(List<Junction> listJ, List<Road> listR,List<Vehicle> listV,Map <String,Junction> mapJ,Map <String,Road> mapR,Map <String,Vehicle> mapV){
		this.listJ=listJ;
		//etc
	}
	//TODO poner public
	void addJunction(Junction j){};
	void addRoad(Road r){};
	void addVehicle (Vehicle v){};
	public Junction getJunction(String id){}
	public Road getROad(String id){}
	public Vehicle getVehicle(String id){}
	public List<Junction> getJunction(){}
	public List<Road> getRoads(){}
	public List <Vehicle> getVehicles(){}
	void reset(){}
	public JSONObject report(){}
}

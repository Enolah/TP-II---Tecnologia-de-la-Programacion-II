package simulator.model;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

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
	public Junction getJunction(String id){
		return null;}
	public Road getROad(String id){
		return null;}
	public Vehicle getVehicle(String id){
		return null;}
	public List<Junction> getJunction(){
		return null;}
	public List<Road> getRoads(){
		return null;}
	public List <Vehicle> getVehicles(){
		return null;}
	void reset(){}
	public JSONObject report(){
		return null;}
}

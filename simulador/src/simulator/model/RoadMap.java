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
		this.listR= listR;
		this.listV= listV;
		this.mapJ= mapJ;
		this.mapR=mapR;
		this.mapV=mapV;
	}
	
	
	void addJunction(Junction j){
		listJ.add(j);
		mapJ.put(j._id, j);
	}
	
	void addRoad(Road r){
		listR.add(r);
		mapR.putIfAbsent(r._id, r);//(i)
		if (mapJ.containsKey(r.getSrc()._id));//(iiççç9
		else if (mapJ.containsKey(r.getDest()._id));
		
	}
	
	void addVehicle (Vehicle v){
		listV.add(v);
		
	}
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

package simulator.model;

import java.util.Collections;
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
		if(mapJ.putIfAbsent(j._id, j)==null) throw new IllegalArgumentException("That id already exists");
		
	}
	
	void addRoad(Road r){
		//TODO hacerlo bien
		listR.add(r);
		//(i)
		if(mapR.putIfAbsent(r._id, r)==null) throw new IllegalArgumentException("That id already exists");
		//(ii)
		if (mapJ.containsKey(r.getSrc()._id)
				||mapJ.containsKey(r.getDest()._id))throw new IllegalArgumentException("");
		
	}
	
	void addVehicle (Vehicle v){
		listV.add(v);
		if(mapV.putIfAbsent(v._id,v)==null)throw new IllegalArgumentException("That id already exists");
//		else{
//		//	v.getItinerary().get(0)
//	
//	}
	
	public Junction getJunction(String id){
		return mapJ.get(id);
	}
	
	public Road getRoad(String id){
		return mapR.get(id);
	}
	
	public Vehicle getVehicle(String id){
		return mapV.get(id);
	}
	
	public List<Junction> getJunction(){
		return Collections.unmodifiableList(listJ);
	}
	
	public List<Road> getRoads(){
		return Collections.unmodifiableList(listR);
	}
	
	public List <Vehicle> getVehicles(){
		return Collections.unmodifiableList(listV);
	}
	
	void reset(){
		//limpiar listas
		listJ.clear();
		listR.clear();
		listV.clear();
		
		//limpiar mapas
		mapJ.clear();
		mapR.clear();
		mapV.clear();
	}
	
	public JSONObject report(){
		
		//JSONObject jo1= new JSONObject();
		
		return null;
	}
}

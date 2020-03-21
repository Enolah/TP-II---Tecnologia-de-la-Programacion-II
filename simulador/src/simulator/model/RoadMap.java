package simulator.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class RoadMap {
	
	private List<Junction> listJ; //lista de cruces
	private List<Road> listR; //lista de carreteras
	private List<Vehicle> listV; //lista de vehiculos
	private Map <String,Junction> mapJ; //mapa de cruces
	private Map <String,Road> mapR; //mapa de carreteras
	private Map <String,Vehicle> mapV; //mapa de vehiculos
	
	public RoadMap(List<Junction> listJ, List<Road> listR,List<Vehicle> listV,Map <String,Junction> mapJ,Map <String,Road> mapR,Map <String,Vehicle> mapV){
		this.listJ=listJ;
		this.listR= listR;
		this.listV= listV;
		this.mapJ= mapJ;
		this.mapR=mapR;
		this.mapV=mapV;
	}
	
	 /*
	  * GET & SET
	  */

	public Junction getJunction(String id){
		return mapJ.get(id);
	}
	public void setJunction(Junction j) {
		mapJ.put(j._id, j);
	}
	
	public Road getRoad(String id){
		return mapR.get(id);
	}
	public void setRoad(Road r) {
		mapR.put(r._id, r);
		
	}
	
	public Vehicle getVehicle(String id){
		return mapV.get(id);
	}
	public void setVehicle(Vehicle v) {
		mapV.put(v._id, v);
		
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

	 /*
	  * METODOS
	  */
	
	void addJunction(Junction j){
		listJ.add(j);
		if(mapJ.putIfAbsent(j._id, j)==null) throw new IllegalArgumentException("That id already exists");
		
	}
	
	void addRoad(Road r){
		
		listR.add(r);
		//(i)
		if(mapR.putIfAbsent(r._id, r)==null) throw new IllegalArgumentException("That id already exists");
		//(ii)
		if (mapJ.containsKey(r.getSrc()._id)
				||mapJ.containsKey(r.getDest()._id))throw new IllegalArgumentException("");
	}
	
	void addVehicle (Vehicle v){
		//TODO duda de los mapas
		//no se como hacer que el itinerario sea valido
		listV.add(v);
		if(mapV.putIfAbsent(v._id,v)==null)throw new IllegalArgumentException("That id already exists");

		for (int i = 0; i < v.getItinerary().size(); i++) {
			boolean encontrado= false;
			int j=0;
			while(!encontrado || listR.size()>=j){
				if(listR.get(j).getSrc()== v.getItinerary().get(i)){
					if(listR.get(j).getDest()== v.getItinerary().get(i++))
						encontrado= true;
					else
						j++;
				}
				else
					j++;
			}
		}
		
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



	public void setMapR(Map<String, Road> mapR) {
		this.mapR = mapR;
	}

	public void setMapV(Map<String, Vehicle> mapV) {
		this.mapV = mapV;
	}

	


}

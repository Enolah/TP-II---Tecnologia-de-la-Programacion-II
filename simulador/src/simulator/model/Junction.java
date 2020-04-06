package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject{

	
	private List<Road> listR; //lista de carreteras entrantes
	private Map<Junction,Road> map; //mapa de carrteras salientes
	private Map<String,List<Vehicle>> mapR_Q ; //mapa carrtera colas
	private List<List<Vehicle>> listQ; //lista de colas
	private int currGreen; //indice semafoto verde (en la lista de carreteras entrantes)
	private int lastGreen; //ultimo paso de cambio de semaforo. valor inicial =0
	private LigthSwitchingStrategy lsStrategy; //estrategia de cambio de semaforo
	private DequeuingStrategy dqStrategy; //estrategia para extraer elementos de la cola
	private int xCoor, yCoor; 
	
	Junction(String id, LigthSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		super(id);
		this.lsStrategy=lsStrategy;
		this.dqStrategy=dqStrategy;
		this.xCoor=xCoor;
		this.yCoor=yCoor;
		
		this.listR= new ArrayList<>();
		this.listQ= new ArrayList<>();
		this.map = new HashMap<>();
		this.mapR_Q = new HashMap<>();
		this.currGreen=-1;
		
		if (lsStrategy == null || dqStrategy==null)throw new IllegalArgumentException("Invalid value, cannot be NULL");
		if(xCoor<0||yCoor<0)throw new IllegalArgumentException("Invalid value,cannot be negative");
	}

	public void getX(){};
	public void getY(){};
	 
	 /*
	  * GET & SET
	  */


	 /*
	  * METODOS
	  */
	
	void addIncommingRoad(Road r){
		
		
		if(r.getDest()!= this) throw new IllegalArgumentException("Invalid value");
		else{
			
			listR.add(r); //lista carreteras entrantes
			List<Vehicle> cola = new LinkedList<Vehicle>();
			//LinkedList<Vehicle> link = new LinkedList<Vehicle>();
			listQ.add(cola);
			//mapa carretera cola
			
			mapR_Q.put(r.getId(), cola);
		}	
		
	}
	
	void addOutGoingRoad(Road r){
	
		//r es una carretera saliente
		if(r.getSrc()!= this) throw new IllegalArgumentException("Invalid value");
		else {
			//comprobar que ninguna carretera va al cruce j
			if(map.containsKey(r.getDest())) throw new IllegalArgumentException("Invalid value");
			else
			//r es una carretera saliente
				map.put(r.getSrc(), r);//src
		}
	}
	
	void enter (Vehicle v){
		List<Vehicle> oldValue = new ArrayList<>();
		List<Vehicle> newValue = new ArrayList<>();
		for (int i = 0; i < listR.size(); i++) {
			if( listR.get(i).getId()== v.getRoad().getId()){
				 oldValue = listQ.get(i);
				listQ.get(i).add(v);
				 newValue = listQ.get(i);
				//actualizar mapa mapR_Q
				mapR_Q.replace(v.getRoad()._id, oldValue, newValue); //remplaza una lista de vehiculos
			}
		}
		
	}
	
	Road roadTo (Junction j){	//actualizar mapa de carreteras salientes
		Road r= map.get(j);
		return r;
	}
	
	@Override
	void advance(int time) {
		// TODO junction
		// 1
		// devuelve uan lista vehiculos de la cola
		List<Vehicle> listV = new ArrayList<>();
		if (currGreen != -1) {
			if (listQ.size() != 0 && (currGreen < listQ.size())) {
				if (listQ.get(currGreen).size() != 0) {

					listV = dqStrategy.dequeue(listQ.get(currGreen));
					// los vehiculos se mueven a sus carreteras
					if (listV != null) { // no hay vehiculos en la cola del
											// cruce

						for (Vehicle vehicle : listV) {
							vehicle.moveToNextRoad();
							// listQ.get(listV.indexOf(vehicle)).remove(vehicle);
							// // elimino v de listV
							listQ.get(listQ.indexOf(listV)).remove(vehicle);
							//listR.remove(vehicle);
						}

					}
				}
			}
		}

		// 2
		// devuelve currGreen
		int indice = lsStrategy.chooseNextGreen(listR, listQ, currGreen, lastGreen, time);
		// si es disctinto al actual
		if (indice != currGreen) {
			currGreen = indice;
			lastGreen = time;
		}
	}

	public JSONObject report(){
		
		JSONObject jo1 = new JSONObject();
	
		JSONArray jaq = new JSONArray();
	//	JSONArray jav = new JSONArray();
		
		
		jo1.put("id", _id);
		if (currGreen==-1)
			jo1.put("green", "none");
		else{
			
			jo1.put("green",listR.get(currGreen).toString());
			
		
			
			for (int i=0; i< listQ.size(); i++) {
				JSONObject jo2= new JSONObject();
				jo2.put("road", listR.get(i).toString());
				//vehiculos en la cola
//				for (Vehicle v :listR.get(i).getListV()) {
//					jav.put(v.toString());
//				}
				
				jo2.put("vehicles",mapR_Q.get(listR.get(i).toString()).toString());
				
				jaq.put(jo2);
			}
		}
			
		
		jo1.put("queues", jaq);
	

		
		return jo1;
	}


}

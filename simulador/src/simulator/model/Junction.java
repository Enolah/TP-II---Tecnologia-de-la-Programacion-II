package simulator.model;

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
			
			listR.add(r);
			List<Vehicle> cola = new LinkedList<Vehicle>();
			//LinkedList<Vehicle> link = new LinkedList<Vehicle>();
			listQ.add(cola);
			//mapa carretera cola
			
			mapR_Q.put(r._id, cola);
		}
		
		
//		//ha llegado un ve de la carretera 1
//		int index=-1;
//		for (Road ve:listR){
//			if(ve._id=="c1")
//				index=listR.indexOf(ve);
//				
//		}
//		List<Vehicle> miColita=listQ.get(index);
//		
		
	}
	
	void addOutGoingRoad(Road r){
	
		//r es una carretera saliente
		if(r.getSrc()!= this) throw new IllegalArgumentException("Invalid value");
		else {
			//comprobar que ninguna carretera va al cruce j
			if(map.containsKey(r.getDest())) throw new IllegalArgumentException("Invalid value");
			else
			//r es una carretera saliente
				map.put(r.getDest(), r);
		}
	}
	
	void enter (Vehicle v){
		
		for (int i = 0; i < listR.size(); i++) {
			if( listR.get(i)._id== v.getRoad()._id){
				List<List<Vehicle>> oldValue = listQ;
				listQ.get(i).add(v);
				List<List<Vehicle>> newValue = listQ;
				//actualizar mapa
				//mapR_Q
				mapR_Q.replace(v.getRoad()._id, oldValue, newValue); //remplaza unaaa lista de vehiculos no todas las listas
																		// que hay en listQ
			}
		}
		
	}
	
	Road roadTo (Junction j){	//actualizar mapa de carreteras salientes
		Road r= map.get(j);
		return r;
	}
	
	@Override
	void advance(int time) {	
		//1
		//devuelve uan lisa vehiculos
		List <Vehicle> listV = dqStrategy.dequeue(listQ.get(currGreen));
		//los vehiculos se mueven a sus carreteras
		for (Vehicle vehicle : listV) {
			vehicle.moveToNextRoad();
		}
		//eliminar de la cola
		listQ.remove(currGreen);

		//2
		//devuelve currGreen
		int indice= lsStrategy.chooseNextGreen(listR, listQ, currGreen,lastGreen,time);
		//si es disctinto al actual
		if(indice!= currGreen){
			currGreen= indice;
			lastGreen= time;
		}
	}
	
	public JSONObject report(){
		
		JSONObject jo1 = new JSONObject();
		jo1.put("id", _id);
		if (currGreen==-1)
			jo1.put("green", "none");
		else
			jo1.put("green", listR.get(currGreen));
		
		//queues lista de colas de las carreteras entrantes
		JSONArray jo2= new JSONArray();
		for (List<Vehicle> v : listQ) {//mal
			
		}
		
		return null;
	}


}

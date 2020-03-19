package simulator.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject{

	
	private List<Road> listR; //lista de carreteras entrantes
	private Map<Junction,Road> map; //mapa de carrteras salientes
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
			for (Road road : listR) {
				if (road.getDest()== this)throw new IllegalArgumentException("Invalid value");
			}
			//r es una carretera saliente
			map.put(r.getDest(), r);
		}
	}
	
	void enter (Vehicle v){
		v.getRoad().enter(v);
		
	}
	
	Road roadTo (Junction j){	//actualizar mapa de carreteras salientes
		Road r= map.get(j);
		return r;
	}
	
	@Override
	void advance(int time) {
		
//		dqStrategy.dequeue(listQ);
//		lsStrategy.chooseNextGreen(list, listQ, time, lastSwitchingTime, currTime);
		
	}
	
	public JSONObject report(){
		
		JSONObject jo1 = new JSONObject();
		jo1.put("id", _id);
		if (currGreen==-1)
			jo1.put("green", listR.get(currGreen));
		else
			jo1.put("green", "none");
		
		JSONArray jo2= new JSONArray();
		for (Road listQ : listR) {//mal
			
		}
		
		return null;
	}


}

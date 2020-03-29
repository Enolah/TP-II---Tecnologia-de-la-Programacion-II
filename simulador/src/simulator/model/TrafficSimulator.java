package simulator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONObject;

import simulator.model.Road.MiCompi;

public class TrafficSimulator {

	private RoadMap mapR; //mapa de carreteras
	private List<Event> listE; //lista de eventos
	private int tick; //timepo (paso) de la simulacion
	private Comp comparador; // Mi comparador
	
	
	public TrafficSimulator(){
		
		
		this.mapR= new RoadMap(null, null, null, null, null, null);
		listE= new ArrayList<Event>();
		//this.listE=null;
		this.tick=0; 
		comparador = new Comp();
			
		}	

	
	
	public class Comp implements Comparator<Event>{

		@Override
		public int compare(Event e0, Event e1) {
			int ok=-1;
			if (e0.getTime()<e1.getTime()) ok=1;
			else if (e0.getTime()==e1.getTime()) ok=0;
			else if (e0.getTime()>e1.getTime()) ok=-1;
			return ok;
		}
	}
	
	public void addEvent(Event e){
		listE.add(e);
		listE.sort(comparador);
	}
	
	public void advance(){
		//1. incrementa el tiempo de la simulación en 1.
		tick++;
		//2. ejecuta todos los eventos cuyo tiempo sea el tiempo actual de la simulación y
		//los elimina de la lista. Después llama a sus correspondientes métodos execute.
		 for (int i = 0; i < listE.size(); i++) {
			if(listE.get(i)._time==tick){
				Event e= listE.get(i);
				listE.remove(i);
				e.execute(mapR);
				i--;
			}
			
		}
		//3. llama al método advance de todos los cruces.
		for (Junction j : mapR.getJunctions()) {
			j.advance(tick);
		}
		//4. llama al método advance de todas las carreteras.
		for (Road r : mapR.getRoads()) {
			r.advance(tick);
		}
	}
	
	public void reset(){
		//mapa de carreteras
		mapR.reset();
		//lista de eventos
		listE.clear();
		tick=0;
		
	}
	public JSONObject report(){
		JSONObject jo1 = new JSONObject();
		jo1.put("time", tick);
		jo1.put("state", mapR.report());
		
		return null;
	}
}

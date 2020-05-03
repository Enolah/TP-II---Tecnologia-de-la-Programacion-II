package simulator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observer;

import org.json.JSONObject;

public class TrafficSimulator implements Observable<TrafficSimObserver>{

	private RoadMap mapR; //mapa de carreteras
	private List<Event> listE; //lista de eventos
	private int tick; //timepo (paso) de la simulacion
	private Comp comparador; // Mi comparador
	private List<Observable> listO; // lista de observadores
	
	
	public TrafficSimulator(){
		
		
		this.mapR= new RoadMap(null, null, null, null, null, null);
		listE= new ArrayList<Event>();
		listO= new ArrayList<Observable>();
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
		onEventAdded(mapR, listE, e, tick);
	}
	
	public void advance(){
		//1. incrementa el tiempo de la simulación en 1.
		tick++;
		
	
		onAdvanceStart(mapR, listE, tick);
		
		//2. ejecuta todos los eventos cuyo tiempo sea el tiempo actual de la simulación y
		//los elimina de la lista. Después llama a sus correspondientes métodos execute.
		 for (int i = 0; i < listE.size(); i++) {
			if(listE.get(i)._time==tick){
				Event e= listE.get(i);
				listE.remove(i);
				try{
				e.execute(mapR);
				} catch (Exception e1){
					System.out.println(e1);
				}
				i--;
			}
			
		}
	//	3. llama al método advance de todos los cruces.
		for (Junction j : mapR.getJunctions()) {
			j.advance(tick);
		}
		//4. llama al método advance de todas las carreteras.
		for (Road r : mapR.getRoads()) {
			r.advance(tick);
		}
		
		onAdvanceEnd(mapR, listE, tick);
	}
	
	public void reset(){
		//mapa de carreteras
		mapR.reset();
		//lista de eventos
		listE.clear();
		tick=0;
		onReset(mapR,listE,tick);
		
	}
	public JSONObject report(){
		JSONObject jo1 = new JSONObject();
		jo1.put("time", tick);
		jo1.put("state", mapR.report());
		
		return jo1;
	}

	//METOOS INTERFAZ
	
	@Override
	public void addObserver(TrafficSimObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(TrafficSimObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	//METODOS NUEVOS
	
	public void onAdvanceStart(RoadMap map , List<Event> events , int time ){
		//TODO 
	}
	public void onAdvanceEnd(RoadMap map , List<Event> events , int time ){
		//TODO
	}
	public void onEventAdded(RoadMap map , List<Event> events , Event e , int time ){
		
	}
	public void onReset(RoadMap map , List<Event> events , int time ){
		//TODO
	}
	public void onRegister(RoadMap map , List<Event> events , int time ){
		//TODO
	}
	public void onError(String err){
		//TODO
	}
	
}

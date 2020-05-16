package simulator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONObject;

public class TrafficSimulator implements Observable<TrafficSimObserver>{

	private RoadMap mapR; //mapa de carreteras
	private List<Event> listE; //lista de eventos
	private int tick; //timepo (paso) de la simulacion
	private Comp comparador; // Mi comparador
	private List<TrafficSimObserver> listO;
	
	
	public TrafficSimulator(){
		
		this.mapR= new RoadMap(null, null, null, null, null, null);
		listE= new ArrayList<Event>();
		listO= new ArrayList<TrafficSimObserver>();
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
		notiOnEventAdded(mapR,listE,e, tick);
	}
	
	public void advance(){
		//1. incrementa el tiempo de la simulación en 1.
		tick++;
		
	
		notiOnAdvanceStart(mapR,listE,tick);
		
		//2. ejecuta todos los eventos cuyo tiempo sea el tiempo actual de la simulación y
		//los elimina de la lista. Después llama a sus correspondientes métodos execute.
		 for (int i = 0; i < listE.size(); i++) {
			if(listE.get(i)._time==tick){
				Event e= listE.get(i);
				listE.remove(i);
				try{
				e.execute(mapR);
				} catch (Exception ex){
					notiOnError(ex.getMessage());
					System.out.println(ex);
				}
				i--;
			}
			
		}
	//	3. llama al método advance de todos los cruces.
		for (Junction j : mapR.getJunctions()) {
			try{
			j.advance(tick);
			} catch(Exception e){
				notiOnError(e.getMessage());
			}
		}
		//4. llama al método advance de todas las carreteras.
		for (Road r : mapR.getRoads()) {
			try {
				r.advance(tick);
			} catch (Exception e) {
				notiOnError(e.getMessage());
			}
		}
		
		notiOnAdvanceEnd(mapR,listE,tick);
	}
	
	public void reset(){
		//mapa de carreteras
		mapR.reset();
		//lista de eventos
		listE.clear();
		tick=0;
		notiOnReset(mapR,listE,tick);
		
	}
	public JSONObject report(){
		JSONObject jo1 = new JSONObject();
		jo1.put("time", tick);
		jo1.put("state", mapR.report());
		
		return jo1;
	}

	//OBSERVADORES DE OBSERVABLE
	
	@Override
	public void addObserver(TrafficSimObserver o) {
		listO.add(o);		
		notiOnRegister(mapR, listE, tick);
	}

	@Override
	public void removeObserver(TrafficSimObserver o) {
		listO.remove(o);
	}
	
	
	//NOTIFICACIONES
	
	public void notiOnAdvanceStart(RoadMap map , List<Event> events , int time ){
		for (TrafficSimObserver o : listO) {
			o.onAdvanceStart(map, events, time);
		}
	}
	
	public void notiOnAdvanceEnd(RoadMap map , List<Event> events , int time ){
		for (TrafficSimObserver o : listO) {
			o.onAdvanceEnd(map, events, time);
		}
	}

	public void notiOnEventAdded(RoadMap map , List<Event> events , Event e , int time ){
		for (TrafficSimObserver o : listO) {
			o.onEventAdded(map, events,e, time);
		}
	}
	
	public void notiOnReset(RoadMap map , List<Event> events , int time ){
		for (TrafficSimObserver o : listO) {
			o.onReset(map, events, time);
		}
	}
	public void notiOnRegister(RoadMap map , List<Event> events , int time ){
		for (TrafficSimObserver o : listO) {
			o.onRegister(map, events, time);
		}
	}
	public void notiOnError(String err){
		for (TrafficSimObserver o : listO) {
			o.onError(err);
		}
	}
}

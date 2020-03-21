package simulator.model;

import java.util.List;

import org.json.JSONObject;

public class TrafficSimulator {

	private RoadMap mapR; //mapa de carreteras
	private List<Event> listE; //lista de eventos
	private int tick; //timepo (paso) de la simulacion
	
	public TrafficSimulator(){
		
		
	}
	public class Simulator {
//
//	
//		public Simulator(RoadMap mapR, List<Event> listE, int tick){
//			this.mapR= mapR;
//			this.listE= listE;
//			this.tick= tick;
//		}	
	}
	
	public void addEvent(Event e){
		listE.add(e);
		//listE.sort(c);
	}
	
	public void advance(){
		
		//1. incrementa el tiempo de la simulaci�n en 1.
		//2. ejecuta todos los eventos cuyo tiempo sea el tiempo actual de la simulaci�n y
		//los elimina de la lista. Despu�s llama a sus correspondientes m�todos execute.
		//3. llama al m�todo advance de todos los cruces.
		//4. llama al m�todo advance de todas las carreteras.
	}
	public void reset(){
		
		//mapa de carreteras
		
		//lista de eventos
		listE.clear();
		tick=0;
		
	}
	public JSONObject report(){
		return null;
	}
}

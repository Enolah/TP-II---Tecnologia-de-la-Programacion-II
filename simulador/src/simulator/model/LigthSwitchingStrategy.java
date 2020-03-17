package simulator.model;

import java.util.List;

public interface LigthSwitchingStrategy {

	/*
	roads: la lista de carreteras entrantes al cruce.
	qs: una lista de vehículos donde las listas internas de vehículos representan colas.
	La cola i-ésima corresponde a la cola de vehículos de la i-ésima carretera de la lista
	roads. Observa que usamos el tipo List<Vehicle> en lugar de Queue<Vehicle> para
	representar una cola, ya que la interfaz Queue en Java no garantiza ningún orden
	cuando se recorre la colección (y lo que queremos es recorrerla en el orden en el cual
	los elementos se añadieron).
	currGreen: el índice (en la lista roads) de la carretera que tiene el semáforo en verde.
	El valor −1 se utiliza para indicar que todos los semáforos están en rojo.
	lastSwitchingTime: el paso de la simulación en el cual el semáforo para la carretera
	currGreen se cambió de rojo a verde. Si currGreen es −1 entonces es el último paso
	en el cual todos cambiaron a rojo.
	currTime: el paso de simulación actual.
	 */
	 int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime, int currTime);
		
	 
}

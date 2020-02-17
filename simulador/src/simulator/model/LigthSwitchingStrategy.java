package simulator.model;

public interface LigthSwitchingStrategy {

	default int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime, int currTime){
		return 0;
		
	}
	
}

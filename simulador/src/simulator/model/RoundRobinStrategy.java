package simulator.model;

import java.util.List;

public class RoundRobinStrategy implements LigthSwitchingStrategy{

	private int timeSlot;
	public RoundRobinStrategy(int timeSlot){
		super();
		this.timeSlot= timeSlot;
	}

	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		int indice =-1;
		if (qs==null); //1
		else{
			if (currGreen==-1){//2
				indice = 0;
			}
			//3 y 4
			else if((currTime-lastSwitchingTime)<timeSlot)
				indice= (currGreen+1)%roads.size();
		}
		return indice;
	}

}

package simulator.model;

public class NewJunctionEvent extends Event {

	public NewJunctionEvent(int time, String id, LigthSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		super(time);
		// TODO Auto-generated constructor stub
	}

	@Override
	void execute(RoadMap map) {
		// TODO crea un cruce correspondiente y lo añade al mapa de carreteras
		
	}

	
	
}

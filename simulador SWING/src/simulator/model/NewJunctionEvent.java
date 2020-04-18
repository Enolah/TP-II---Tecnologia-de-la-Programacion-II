package simulator.model;

public class NewJunctionEvent extends Event {

	private String id;
	 private LigthSwitchingStrategy lsStrategy;
	private DequeuingStrategy dqStrategy;
	private int xCoor;
	private int yCoor;
	
	private Junction j;
	
	public NewJunctionEvent(int time, String id, LigthSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		super(time);
		this.id= id;
		this.lsStrategy= lsStrategy;
		this.dqStrategy= dqStrategy;
		this.xCoor= xCoor;
		this.yCoor= yCoor;
	}

	@Override
	void execute(RoadMap map) {
		
		try{
			j= new Junction(id, lsStrategy, dqStrategy, xCoor, yCoor);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		map.addJunction(j);
	}

	
	@Override
	public String toString() {
		//TODO devuelve su descripcion (imagino que completa)
	return "New Vehicle '" + id + "'" ;
	}
}

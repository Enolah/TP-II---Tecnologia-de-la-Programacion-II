package simulator.model;

public class NewInterCItyRoadEvent extends NewRoadEvent{

	private String id;
	private String srcJun;
	private String destJun;
	private int lenght;
	private int co2limit;
	private int maxSpeed;
	private Weather wea;
	
	
	NewInterCItyRoadEvent(int time, String id, String srcJun, String destJun, int lenght,
			int co2Limit, int maxSpeed, Weather weather) {
		super(time);
		this.id= id;
		this.srcJun= srcJun;
		this.destJun=destJun;
		this.lenght=lenght;
		this.co2limit=co2Limit;
		this.maxSpeed= maxSpeed;
	}

	@Override
	void execute(RoadMap map) {
		// TODO Auto-generated method stub
		
	}

}

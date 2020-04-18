package simulator.model;

public abstract class NewRoadEvent extends Event {

	private String id;
	private String srcJun;
	private String destJun;
	private int lenght;
	private int co2limit;
	private int maxSpeed;
	private Weather wea;

	private Road r;

	public NewRoadEvent(int time, String id, String srcJun, String destJun, int lenght, int co2Limit, int maxSpeed,
			Weather weather) {
		super(time);
		this.id = id;
		this.srcJun = srcJun;
		this.destJun = destJun;
		this.lenght = lenght;
		this.co2limit = co2Limit;
		this.maxSpeed = maxSpeed;
		this.wea = weather;

	}

	/*
	 * Getters
	 */

	public String getId() {
		return id;
	}

	public String getSrcJun() {
		return srcJun;
	}

	public String getDestJun() {
		return destJun;
	}

	public int getLenght() {
		return lenght;
	}

	public int getCo2limit() {
		return co2limit;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public Weather getWea() {
		return wea;
	}

	public Road getR() {
		return r;
	}

	/*
	 * Metodos
	 */

	@Override
	void execute(RoadMap map) {

		try {
			r = createRoadObject(map.getJunction(srcJun), map.getJunction(destJun));
		} catch (Exception e) {
			System.out.println(e);
		}

		map.addRoad(r);
	}

	abstract Road createRoadObject(Junction srcJun, Junction destJun);
	
	@Override
	public String toString() {
		//TODO devuelve su descripcion (imagino que completa)
	return "New Vehicle '" + id + "'" ;
	}

}

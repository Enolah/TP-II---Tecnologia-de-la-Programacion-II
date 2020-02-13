package simulator.model;

public abstract class Road extends SimulatedObject {

	private Juction Dest;
	private Juction Src;
	private int length;
	private int limitSpeed;
	private int alarmPollution;
	private Weather wea;
	private int totalPollution;
	private List<Vehicle> vehicle;
	
	Road (String id, Juction srcJunc, Juction destJunc, int maxSpeed, int contLimit, int lenght, Weather weather){
		super(id);
		//...
	}
	
	//getters and setters
	
	public int getLength();
	public Junction getDest();
	public Juction getSrc();
	public void setWeather(Weather);
	
	
	//metodos
	
	void enter(Vehicle v);
	void exit (Vehicle v);
	void addContamination(int c);
	abstract void reduce TotalContamination();
	abstract void updateSpeedLimit();
	abstract int calculateVehicleSpeed(Vehicle v);
	void advance (int time);
	public JSONObject report();
	
}

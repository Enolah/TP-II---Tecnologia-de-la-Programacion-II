package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {

	private List<Junction> listJ; // lista de cruces
	private List<Road> listR; // lista de carreteras
	private List<Vehicle> listV; // lista de vehiculos
	private Map<String, Junction> mapJ; // mapa de cruces
	private Map<String, Road> mapR; // mapa de carreteras
	private Map<String, Vehicle> mapV; // mapa de vehiculos

	RoadMap(List<Junction> listJ, List<Road> listR, List<Vehicle> listV, Map<String, Junction> mapJ,
			Map<String, Road> mapR, Map<String, Vehicle> mapV) {

		this.listJ = new ArrayList<>();
		this.listR = new ArrayList<>();
		this.listV = new ArrayList<>();
		this.mapJ = new HashMap<>();
		this.mapR = new HashMap<>();
		this.mapV = new HashMap<>();

	}

	/*
	 * GET & SET
	 */

	public Junction getJunction(String id) {
		return mapJ.get(id);
	}

	public void setJunction(Junction j) {
		mapJ.put(j._id, j);
	}

	public Road getRoad(String id) {
		return mapR.get(id);
	}

	public void setRoad(Road r) {
		mapR.put(r._id, r);

	}

	public Vehicle getVehicle(String id) {
		return mapV.get(id);
	}

	public void setVehicle(Vehicle v) {
		mapV.put(v._id, v);

	}

	public List<Junction> getJunctions() {
		return Collections.unmodifiableList(listJ);
	}

	public List<Road> getRoads() {
		return Collections.unmodifiableList(listR);
	}

	public List<Vehicle> getVehicles() {
		return Collections.unmodifiableList(listV);
	}

	/*
	 * METODOS
	 */

	void addJunction(Junction j) {
		listJ.add(j);
		if (mapJ.putIfAbsent(j.getId(), j) != null)
			throw new IllegalArgumentException("That id already exists");

	}

	void addRoad(Road r) {

		listR.add(r);
		// (i)
		if (mapR.putIfAbsent(r.getId(), r) != null)
			throw new IllegalArgumentException("That id already exists");
		// (ii)
		if (mapR.containsKey(r.getSrc().getId()) || mapR.containsKey(r.getDest().getId()))
			throw new IllegalArgumentException("That id no exists");
	}

	void addVehicle(Vehicle v) {

		listV.add(v);
		if (mapV.putIfAbsent(v.getId(), v) != null)
			throw new IllegalArgumentException("That id already exists");

		if (checkItinerary(v) == false)
			throw new IllegalArgumentException("That road no exists");

	}

	boolean checkItinerary(Vehicle v) {
		boolean check = false;
		int cont = 0;
		// comprobar que los junction existen
		for (Road road : listR) {
			if (road.getSrc().getId() == v.getItinerary().get(cont).getId()) {
				if (road.getDest().getId() == v.getItinerary().get(cont++).getId())
					cont++;
			}
		}

		if (cont == v.getItinerary().size() - 1)
			check = true;

		return check;
	}

	void reset() {
		// limpiar listas
		listJ.clear();
		listR.clear();
		listV.clear();

		// limpiar mapas
		mapJ.clear();
		mapR.clear();
		mapV.clear();
	}

	public JSONObject report() {

		JSONObject jo1 = new JSONObject();

		JSONArray j = new JSONArray();
		JSONArray r = new JSONArray();
		JSONArray v = new JSONArray();

		for (Junction junction : listJ) {
			j.put(junction.report());
		}
		jo1.put("junctions", j);

		for (Road road : listR) {
			r.put(road.report());
		}
		jo1.put("roads", r);

		for (Vehicle vehicle : listV) {
			v.put(vehicle.report());
		}
		jo1.put("vehicles", v);

		return jo1;
	}

}

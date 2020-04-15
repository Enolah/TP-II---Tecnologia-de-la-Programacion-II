package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;

public class Controller {
	
	// eventsFactory y sim se crean en “Main”
	private TrafficSimulator _sim;
	private Factory<Event> _eventsFactory;

	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory){
		this._sim=sim;
		this._eventsFactory=eventsFactory;
		
	}
	
	public void reset() {
	_sim.reset();
	}
	
	//convierte la entrada JSON en un objeto JSONObject
	public void loadEvents(InputStream in){
		JSONObject jo= new JSONObject(new JSONTokener(in));
		JSONArray events = jo.getJSONArray("events");//extrae lista de evento
		
		for(int i = 0; i < events.length(); i++){//crea el evento correspondiente utilizando la factoriza de eventos
			_sim.addEvent(_eventsFactory.createInstance(events.getJSONObject(i)));
			//return un evento
		}
	}
	
	public void run(int n, OutputStream out){
		if( out == null){
			out = new OutputStream(){
				@Override
				public void write(int b) throws IOException {}
			};
		}
		PrintStream p= new PrintStream(out);
		p.println("{");
		p.println(" \"states\": [");
		// escribir los n-1 primeros pasos (para que no salga coma
		// en el último paso
		for (int i = 0; i < n-1; i++) {
			_sim.advance();
			 p.print(_sim.report()); p.println(",");
		}
	
		// escribir el último paso
		_sim.advance();
		 p.print(_sim.report()); p.print("\n");
		
		p.println("]");
		p.println("}"); 
		
	}
	
}

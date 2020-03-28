package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetContClassEvent extends Event {

	private List<Pair<String,Integer>> cs;
	
	public SetContClassEvent(int time, List<Pair<String,Integer>> cs) {
		super(time);
		this.cs=cs;
		if( cs==null) throw new IllegalArgumentException("Invalid value");
	}

	@Override
	void execute(RoadMap map) {
		// TODO Auto-generated method stub
		
	}

}

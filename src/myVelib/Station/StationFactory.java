package myVelib.Station;

import myVelib.GPS;

public class StationFactory {
	
	static Station createStation(String StationType,GPS position,StationState state, int capacity) {
			if(StationType.equalsIgnoreCase("PLUS")) {
				return new PlusStation(position,state,capacity);
			}
			else {
				return new StandardStation();
			}
	}
	
	public StationFactory() {
	}
}

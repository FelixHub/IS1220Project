package myVelib.Station;

import myVelib.GPS;

public class StationFactory {
	
	static Station createStation(String StationType,GPS position, int capacity) {
			if(StationType.equalsIgnoreCase("PLUS")) {
				return new PlusStation(position,capacity);
			}
			else {
				return new StandardStation(position,capacity);
			}
	}
}

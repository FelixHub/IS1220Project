package myVelib.Station;

import myVelib.Misc.GPS;
/**
 * 
 *  a factory class to create different type of stations
 *
 */

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

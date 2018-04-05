package myVelib.PathAlgorithm;

import myVelib.MyVelib;
import myVelib.Bicycle.BicycleType;
import myVelib.Misc.GPS;
import myVelib.Station.Station;

/**
 * This is the interface from which the subsequent "algorithm" classes will inherit to find the Start and EndStation according to the chosen method.
 */
public interface PathFinder {

	/**
	 * the path method set StartStation and EndStation accordingly to the method chosen, the GPS, the map and the type of bike.
	 * @param start
	 * @param end
	 * @param velibNW
	 * @param type
	 */
	void path(GPS start,GPS end, MyVelib velibNW, BicycleType type);
	Station getStartStation();
	Station getEndStation();
}

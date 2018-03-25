package myVelib.PathAlgorithm;

import myVelib.MyVelib;
import myVelib.Bicycle.BicycleType;
import myVelib.Misc.GPS;
import myVelib.Station.Station;

public interface PathFinder {

	
	void path(GPS start,GPS end, MyVelib velibNW, BicycleType type);
	Station getStartStation();
	Station getEndStation();
}

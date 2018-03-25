package PathAlgorithm;

import myVelib.GPS;
import myVelib.MyVelib;
import myVelib.Bicycle.BicycleType;
import myVelib.Station.Station;

public interface PathFinder {

	
	void path(GPS start,GPS end, MyVelib velibNW, BicycleType type);
	Station getStartStation();
	Station getEndStation();
}

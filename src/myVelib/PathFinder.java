package myVelib;

import myVelib.Bicycle.BicycleType;
import myVelib.Station.Station;

public interface PathFinder {
	
	void Path(GPS start,GPS end, MyVelib velibNW, BicycleType type);
	Station getStartStation();
	Station getEndStation();

	
}

package myVelib;

import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleType;
import myVelib.Station.Station;

public interface PathFinder {

	
	void Path(GPS start,GPS end, MyVelib velibNW, Bicycle bicycle);
	Station getStartStation();
	Station getEndStation();

	
}

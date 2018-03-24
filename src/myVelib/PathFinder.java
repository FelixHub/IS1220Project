package myVelib;

import myVelib.Station.Station;

public interface PathFinder {
	

	void Path(GPS start,GPS end, MyVelib velibNW);
	Station getStartStation();
	Station getEndStation();

	
}

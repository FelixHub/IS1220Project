package myVelib;

import myVelib.Bicycle.BicycleType;
import myVelib.Station.Station;

public interface PathFinder {
<<<<<<< HEAD


	void Path(GPS start,GPS end, MyVelib velibNW);
<<<<<<< HEAD

=======
=======
	
	void Path(GPS start,GPS end, MyVelib velibNW, BicycleType type);
>>>>>>> branch 'master' of https://github.com/FelixHub/IS1220Project.git
	Station getStartStation();
	Station getEndStation();

	
>>>>>>> branch 'master' of https://github.com/FelixHub/IS1220Project.git
}

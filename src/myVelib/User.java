package myVelib;

import myVelib.Bicycle.Bicycle;
import myVelib.Card.Card;

public class User {
	String name;
	GPS coor;
	Card userCard;
	int ridesNb;
	float timeSpentOnBike;
	float timeCreditBalance;
	


	public void askRideTo(GPS gps, PathFinder pathAlg,Bicycle bicycle) {
		Ride ride = new Ride(coor,gps,bicycle);

	}
	
	
	

}

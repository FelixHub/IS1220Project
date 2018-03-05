package myVelib;

public class User {
	String name;
	GPS coor;
	Card userCard;
	int ridesNb;
	float timeSpentOnBike;
	float timeCreditBalance;
	
	public void askRideTo(GPS gps, PathAlg pathAlg,Bicycle bicycle) {
		Ride ride = new Ride(coor,gps,bicycle);
	}
	
	
	

}

package myVelib.Misc;

import myVelib.Bicycle.Bicycle;
import myVelib.Card.Card;
import myVelib.Station.Station;

public class User {
	
	
	String name;
	GPS position;
	Card userCard;
	public int ridesNb;
	private long  timeSpentOnBike;
	public int timeCreditBalance;
	int ID;
	double chargesAmount;
	static int count_ID;
	public Ride currentRide;
	public long TimeOfLastRenting;
	public Boolean possessBicycle;
	public Bicycle bicycle;
	
	public User(String name, GPS position, Card card) {
		this.bicycle = null;
		this.possessBicycle = false;
		this.name = name;
		this.position = position;
		this.userCard = card;
		this.ridesNb = 0;
		this.chargesAmount = 0;
		this.timeSpentOnBike = 0;
		this.timeCreditBalance = 0;
		this.ID = count_ID;
		User.count_ID ++;
		this.currentRide = null;
		long TimeOfLastRenting=0;
	}
	
	
	public double getChargesAmount(){
		return chargesAmount;
	}
	public int getRidesNb() {
		return ridesNb;
	}
	public long getTimeSpentOnBike() {
		return timeSpentOnBike;
	}
	public void setTimeSpentOnBike(long time) {
		timeSpentOnBike = time;
	}
	
	public int getTimeCreditBalance() {
		return timeCreditBalance;
	}
	public String getName() {
		return name;
	}
	
	public GPS getPosition() {
		return position;
	}
	
	public Card getUserCard() {
		return userCard;
	}
	
	public Ride getCurrentRide() {
		return currentRide;
	}

	public void setUserCard(Card userCard) {
		this.userCard = userCard;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(GPS position) {
		this.position = position;
	}
	
	public void addCharges(double charges){
		chargesAmount = chargesAmount + charges;
	}

	public void setCurrentRide(Ride ride) {
		currentRide = ride;
	}


	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	public static void displayRide(User u) {
		int cityDimension = u.getCurrentRide().getVelibNW().getCityDimension();
		for(int i = 0; i< cityDimension; i ++) {
			String s = "";
			for(int j = 0; j < cityDimension; j ++) {
				boolean flag = true;
				if ( ( u.getPosition().getX() == j) && (u.getPosition().getY() == i)){
					flag = false;
					s = s + "S  ";
				}
				else if ( (u.getCurrentRide().getEnd().getX() == j) && (u.getCurrentRide().getEnd().getY() == i)){
					flag = false;
					s = s + "E  ";
				}
				else{
					for(Station station : u.getCurrentRide().getVelibNW().getStations()) {
					if ( ( station.getPosition().getX() == j) && (station.getPosition().getY() == i)) {
						if(station.getState()=="OFFLINE") {
							s = s + "X  ";
						}
						else if (station.type == "PLUS") {
							s = s + station.getID()+"P ";
						}
						else {
							s = s + station.getID()+"N ";
						}
						flag = false;
						break;
						}
					}
				}
				if (flag) {s = s + "#  ";}
			}
			System.out.println(s);
		}
		System.out.println("startPosition: "+u.getPosition() );
		System.out.println("EndPosition: "+ u.getCurrentRide().getEnd() );
		System.out.println("StartStation: "+u.currentRide.getRidePath().getStartStation());
		System.out.println("EndStation: "+ u.currentRide.getRidePath().getEndStation());
		
	}


	public void setBicycle(Bicycle b) {
		bicycle =b;
		
	}


	public void setGPS(GPS position2) {
		position = position2;
		
	}
}

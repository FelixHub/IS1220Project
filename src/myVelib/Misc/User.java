package myVelib.Misc;

import myVelib.Card.Card;

public class User {
	
	
	String name;
	GPS position;
	Card userCard;
	public int ridesNb;
	float timeSpentOnBike;
	public int timeCreditBalance;
	int ID;
	double chargesAmount;
	static int count_ID;
	Ride currentRide;
	public long TimeOfLastRenting;
	public Boolean possessBicycle;
	
	public User(String name, GPS position, Card card) {
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
	
}

package myVelib;

import myVelib.Card.Card;

public class User {
	String name;
	GPS position;
	Card userCard;
	int ridesNb;
	float timeSpentOnBike;
	float timeCreditBalance;
	int ID;
	static int count_ID;

	public User(String name, GPS position, Card card) {
		this.name = name;
		this.position = position;
		this.userCard = card;
		this.ridesNb = 0;
		this.timeSpentOnBike = 0;
		this.timeCreditBalance = 0;	
		this.ID = count_ID;
		User.count_ID ++;
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

	public void setUserCard(Card userCard) {
		this.userCard = userCard;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(GPS position) {
		this.position = position;
	}
}

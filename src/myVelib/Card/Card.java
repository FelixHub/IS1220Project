package myVelib.Card;

import myVelib.Bicycle.Bicycle;

public abstract class Card {
	
	private int timeCredit;
	
	
	public double rideCost(int time, Bicycle bicycle) {
		if ((bicycle.getType()).equals("Electric")) {
			return 2*(1 + time/60);
		}
		else {
			return (1 + time/60);
		}
	} 
	
	public int getTimeCredit() {
		return timeCredit;
	}
	
	public void setTimeCredit(int time) {
		timeCredit = timeCredit + time;
	}
}

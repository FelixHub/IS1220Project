package myVelib.Card;

import java.util.Random;

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
	
	public void card() {
		Random rn = new Random();
		int randomNum = rn.nextInt(3);
		if (randomNum == 0) {
			new VLIBRE_Card();
		}
		else if (randomNum == 1){
			new VMAX_Card();
		}
		else {
			new NoCard();
		}
	}
}

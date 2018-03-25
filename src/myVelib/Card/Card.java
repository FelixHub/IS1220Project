package myVelib.Card;

import java.util.Random;

import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.ElectricBicycle;

public abstract class Card {
	 
	private long timeCredit;
	
	
	public double rideCost(long time, Bicycle bicycle) {
		if (bicycle instanceof ElectricBicycle) {
			return 2*(1 + time/60);
		}
		else {
			return (1 + time/60);
		}
	} 
	
	public long getTimeCredit() {
		return timeCredit;
	}
	
	public void setTimeCredit(long time) {
		timeCredit = time;
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

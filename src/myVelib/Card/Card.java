package myVelib.Card;

import java.util.Random;
import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.ElectricBicycle;
/**
 *
 * The abstract class for Card has three subclasses for VLIBRE, VMAX cards and No card.
 * @param timeCredit is attribute of Card because the cost of travel is computed by a method of Card.
 */
public abstract class Card {
	
	 
	private long timeCredit;
	
	/**
		 * there's three way to compute the cost of a ride depending of the card possessed by the user.
		 * VLIBRE_Card and VMAX_Card does override rideCost.
		 * The default way is corresponding to an absence of card, NoCard does not override rideCost.
		 *  the cost is 1 Euro per hour (for mechanical bikes) and 2 Euro per hour (for electrical bikes) 
		 */
	
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
	
	public static Card randomCard() {
		/**
		 * this constructor is only to be used at the initialization of a myVelib world. 
		 *  as proposed in the setting up paragraph, it randomly create one of the three possible kind of Card.
		 */
		Random rn = new Random();
		int randomNum = rn.nextInt(3);
		if (randomNum == 0) {
			return new VLIBRE_Card();
		}
		else if (randomNum == 1){
			return new VMAX_Card();
		}
		else {
			return new NoCard();
		}
	}
	
	public static Card Card(String cardtype) {
		if (cardtype.equalsIgnoreCase("VLIBRE")) {
			return new VLIBRE_Card();
		}
		else if(cardtype.equalsIgnoreCase("VMAX")) {
			return new VMAX_Card();
		}
		else { return new NoCard();}
	}
}

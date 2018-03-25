package myVelib.Card;
 
import myVelib.Bicycle.Bicycle;
/**
 * 
 * Subclass for the VMAX type of card
 *
 */

public class VMAX_Card extends Card{
	
	 
	@Override
	public double rideCost(long time,Bicycle bicycle) {
		/**
		 *  0 Euro for the first hour then 1E per 
		 *  each successive hour (independently of the kind of bike).
		 */
		return(time/60);
	}
}

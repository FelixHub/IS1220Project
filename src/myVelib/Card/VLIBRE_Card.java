package myVelib.Card;

import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.ElectricBicycle;
/**
 * 
 * subclass for the VLIBRE type of card
 *
 */

public class VLIBRE_Card extends Card{
	 
	@Override
	public double rideCost(long time,Bicycle bicycle) {
		/*
		 * 0 Euro for the first hour then 1E per each successive hour
		 *  (for mechanical) and 1 Euro for the first hour then 2E per 
		 *  each successive hour (for electrical bikes). If a ride lasts 
		 *  longer than 60min the actual time balance exceeding 60min is 
		 *  computed by deducing from the user’s time-credit (if any).
		 */
		double c = 0;
		long chargedTime = time - 60;
		long timeCred = this.getTimeCredit();
		if (chargedTime > 0) {
			if ( timeCred > chargedTime) {
				this.setTimeCredit(timeCred - chargedTime);
				return 0;
			}
			else {
				this.setTimeCredit(0);
				chargedTime = (chargedTime-timeCred);
						if (bicycle instanceof ElectricBicycle) {
							c = 1 + 2*(chargedTime/60);
						}
						else {c = 1 + chargedTime/60;}
				return c;
			}
		}
		return 0;
	}
	
	public VLIBRE_Card() {
		super();
	}
	public String toString() {
		return "VLIBRE";
	}
}

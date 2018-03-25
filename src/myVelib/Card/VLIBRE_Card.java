package myVelib.Card;

import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.ElectricBicycle;

public class VLIBRE_Card extends Card{

	 
	@Override
	public double rideCost(long time,Bicycle bicycle) {
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

}

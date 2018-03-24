package myVelib.Card;

import myVelib.Bicycle.Bicycle;

public class VLIBRE_Card extends Card{

	 
	@Override
	public double rideCost(int time,Bicycle bicycle) {
		double c = 0;
		int chargedTime = time - 60;
		int timeCred = this.getTimeCredit();
		if (chargedTime > 0) {
			if ( timeCred > chargedTime) {
				this.setTimeCredit(timeCred - chargedTime);
				return 0;
			}
			else {
				this.setTimeCredit(0);
				chargedTime = (chargedTime-timeCred);
						if ((bicycle.getType()).equals("Electric")) {
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

package myVelib.Card;

import myVelib.Bicycle.Bicycle;

public class VMAX_Card extends Card{
	
	@Override
	public double rideCost(int time,Bicycle bicycle) {
		return(time/60);
	}
	
}

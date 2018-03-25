package myVelib.Bicycle;
/**
 * 
 *  class of mechanical Bike, in application of the Factory design pattern.
 *
 */

public class MechanicalBicycle extends Bicycle {
	
	public MechanicalBicycle(){
		super();
		this.ID = Bicycle.count_ID;
		Bicycle.count_ID ++;
		this.type = BicycleType.MECHANICAL;
	}	
}

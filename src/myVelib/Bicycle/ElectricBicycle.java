package myVelib.Bicycle;
/**
 * 
 * class of electric bike, in application of the Factory design pattern
 */
public class ElectricBicycle extends Bicycle {
	
	public ElectricBicycle(){
		super();
		this.ID = Bicycle.count_ID;
		Bicycle.count_ID ++;
		this.type = BicycleType.ELECTRIC;
	}
}

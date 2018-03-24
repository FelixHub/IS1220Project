package myVelib.Bicycle;

public class MechanicalBicycle extends Bicycle {
	
	public MechanicalBicycle(){
		super();
		this.ID = Bicycle.count_ID;
		Bicycle.count_ID ++;
		this.type = BicycleType.MECHANICAL;
	}	
}

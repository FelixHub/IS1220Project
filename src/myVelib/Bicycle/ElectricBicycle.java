package myVelib.Bicycle;

public class ElectricBicycle extends Bicycle {
	
	public ElectricBicycle(){
		super();
		this.ID = Bicycle.count_ID;
		Bicycle.count_ID ++;
		this.type = BicycleType.ELECTRIC;
	}
}

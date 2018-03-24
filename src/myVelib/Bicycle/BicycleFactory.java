package myVelib.Bicycle;

public class BicycleFactory {
	
	public Bicycle createBicycle(String type) {
		if (type.equalsIgnoreCase("ELECTRIC")) {
			return new ElectricBicycle();
		}
		else { 
			return new MechanicalBicycle();
		}
	}
}


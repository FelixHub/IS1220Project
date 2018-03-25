package myVelib.Bicycle;
/**
 * 
 * factory for creating bicycle hidden from the bicycle class.
 *
 */

public class BicycleFactory {
	
	public static Bicycle createBicycle(String type) {
		if (type.equalsIgnoreCase("ELECTRIC")) {
			return new ElectricBicycle();
		}
		else { 
			return new MechanicalBicycle();
		}
	}
}


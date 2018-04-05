package myVelib.Bicycle;

import java.util.Random;
/**
 * 
 * the abstract class bicycle has two subclasses ElectricBicycle and MechanicalBicycle.
 * 
 */

public abstract class Bicycle {
	protected int ID;
	static int count_ID;
	protected BicycleType type;
	
	public int getID() {
		/**
		 *  return the ID of the Bicycle, which is unique.
		 */
		return ID;
	}
	
	public BicycleType getType() {
		/**
		 * return the type of the bicycle, ELECTRIC or MECHANICAL.
		 */
		return type;
		
	}

	public static Bicycle randomBicycle() {
		/**
		 * this constructor is only to be used at the initialization of a myVelib world. 
		 * It randomly generate 70% mechanical bicycle and 30% electrical Bicycle as asked in the project description.
		 */
		Random rn = new Random();
		int randomNum = rn.nextInt(10);
		if ( randomNum < 8 ) {
			return new MechanicalBicycle();
		}
		else {
			return new ElectricBicycle();
		}
	}
}

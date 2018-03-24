package myVelib.Bicycle;

import java.util.Random;

public abstract class Bicycle {
	protected int ID;
	static int count_ID;
	protected String type;
	
	public int getID() {
		return ID;
	}
	
	public String getType() {
		return type;
	}
	
	// pour créer aléatoirement des vélos à l'initialisation du monde de base)
	public Bicycle() {
		Random rn = new Random();
		int randomNum = rn.nextInt(10);
		if ( randomNum < 8 ) {
			new MechanicalBicycle();
		}
		else {
			new ElectricBicycle();
		}
	}
}

package myVelib.Misc;

/**
 * 
 * The class used to simulate time.
 *
 */
public class MyClock {
	
	private final long offset;

    public MyClock(){
        offset = System.currentTimeMillis();
    }
    /**
     * get the actual time of the Velib simulation (since the clock has been instanciated).
     * @return
     */
    public long getTime()
    {
        return ((System.currentTimeMillis() - offset)*60/1000);
    }
    
    


}

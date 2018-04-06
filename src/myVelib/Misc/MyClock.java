package myVelib.Misc;

/**
 * 
 * The class used to simulate time.
 *
 */
public class MyClock {
	
	private long offset;

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
    
    public void addTime(long time) {
    	offset = offset - time ;
    }


}

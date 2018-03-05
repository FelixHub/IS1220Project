package myVelib;

public class MyClock {
	
	private final long offset;

    public MyClock(){
        offset = System.currentTimeMillis();
    }

    public long getSeconds()
    {
        return ((System.currentTimeMillis() - offset));
    }


}

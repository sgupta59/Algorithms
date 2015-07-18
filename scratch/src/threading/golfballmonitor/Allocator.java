package threading.golfballmonitor;


//@author: j.n.magee 11/11/96


public interface Allocator {

    public void get(int n) throws InterruptedException;
    public void put(int n);
}



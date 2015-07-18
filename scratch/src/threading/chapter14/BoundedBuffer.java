package threading.chapter14;

public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
	
	public BoundedBuffer(int capacity) {
		super(capacity);
	}
	
	// Blocks untill not full.
	public synchronized void put(V v) throws InterruptedException {
		while (isFull())
			wait();
		doPut(v);
		notifyAll();
	}
	
	// blocks until not empty
	public synchronized V get() throws InterruptedException {
		while (isEmpty())
			wait();
		V v = doTake();
		notifyAll();
		return v;
	}
}
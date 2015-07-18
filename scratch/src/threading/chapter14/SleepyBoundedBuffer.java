package threading.chapter14;

public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
	
	public SleepyBoundedBuffer(int capacity)
	{
		super(capacity);
	}
	
	public void put(V v) throws InterruptedException {
		// sleep till there is capacity.
		while (true) {
			synchronized(this) {
				if (!isFull()) {
					doPut(v);
					return;
				}
			}
			Thread.sleep(1000);
		}
	}
	
	public V take() throws InterruptedException {
		while (true) {
			synchronized(this) {
				if (!isEmpty())
					return doTake();
			}
			Thread.sleep(1000);
		}
	}
}
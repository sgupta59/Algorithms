package threading.chapter14;

public abstract class BaseBoundedBuffer<V> {
	
	private final V[] buf;
	private int size;
	private int head;
	private int tail;
	@SuppressWarnings("unchecked")
	protected BaseBoundedBuffer(int capacity)
	{
		buf = (V[])new Object[capacity];
	}
	
	public final synchronized void doPut(V v) {
		// add to the tail.
		buf[tail] = v;
		
		if (++tail == buf.length)
			tail = 0;
		++size;
	}
	
	public final synchronized V doTake() {
		V v = buf[head];
		buf[head] = null;
		if (++head == buf.length)
			head = 0;
		--size;
		return v;
	}
	
	public synchronized final boolean isFull() {
		return size == buf.length;
	}
	
	public synchronized final boolean isEmpty() {
		return size == 0;
	}
}
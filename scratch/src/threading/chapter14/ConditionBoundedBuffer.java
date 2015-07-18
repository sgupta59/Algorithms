package threading.chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<V> {
	
	private final V[] buff;
	private int count;
	private int head;
	private int tail;
	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	
	public ConditionBoundedBuffer(int capacity)
	{
		buff = (V[])new Object[capacity];
	}
	
	public void put(V v) throws InterruptedException
	{
		// lock this method.
		lock.lock();
		try
		{
			while (count == buff.length)
				notFull.await();
			
			buff[tail] = v;
			if (++tail == buff.length)
				tail = 0;
			++count;
			notEmpty.signal();
		}
		finally
		{
			lock.unlock();
		}
	}
	
	public V get() throws InterruptedException  {
		// lock first.
		lock.lock();
		try
		{
			while (count == 0)
				notEmpty.await();
			V v = buff[head];
			if (++head == buff.length)
				head = 0;
			notFull.signal();
			--count;
			return v;
		}
		finally
		{
			lock.unlock();
		}
	}
	
}
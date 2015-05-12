package threading.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueConditioned<T> {

	/** Linked list containing the items in the queue */
	Queue<T> _items = new LinkedList<T>();
	/** Maximum size of the queue */
	private int _maxsize = 0;
	
	/** The lock object */
	private final Lock _lock = new ReentrantLock();
	
	/** Condition variables */
	private final Condition _notFull = _lock.newCondition();
	private final Condition _notEmpty = _lock.newCondition();
	 
	public BlockingQueueConditioned(int maxsize)
	{
		_maxsize = maxsize;
	}
	
	public void put(T item) throws InterruptedException
	{
		// first lock this object
		_lock.lock();
		try
		{
			// wait for the queue to have some space in it, i.e wait for the queue
			// to be not full.
			while (_items.size() == _maxsize)
				_notFull.await();
			_items.offer(item);
			_notEmpty.signal();
		}
		finally
		{
			_lock.unlock();
		}
	}
	
	public T get() throws InterruptedException
	{
		// lock first
		_lock.lock();
		try
		{
			// if have the lock, then wait untill a not full condition is triggered.
			while (_items.size() == 0)
				_notEmpty.await();
			T item =  _items.poll();
			_notFull.signal();
			return item;
		}
		finally
		{
			_lock.unlock();
		}
	}
	
	private class Sync extends AbstractQueuedSynchronizer
	{
		
	}
}

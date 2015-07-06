package threading.Chapter12;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author Brian Goetz and Tim Peierls
 *
 */
public class SemaphoreOnLock {

	private final Lock lock = new ReentrantLock();
	private final Condition permitsAvailable = lock.newCondition();
	private int permits;
	private int available = 0;
	public SemaphoreOnLock(int permits)
	{
		this.permits = permits;
		available = permits;
	}
	
	public void acquire() throws InterruptedException
	{
		lock.lock();
		try
		{
			while (available == 0)
				permitsAvailable.await();
			--available;
		}
		finally
		{
			lock.unlock();
		}
	}
	
	public void release()
	{
		lock.lock();
		try
		{
			++available;
			available = (available > permits ? permits : available);
			permitsAvailable.signal();
		}
		finally
		{
			lock.unlock();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

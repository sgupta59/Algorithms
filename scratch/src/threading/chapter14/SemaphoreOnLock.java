package threading.chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreOnLock {

	private  int permits;
	private final Lock lock = new ReentrantLock();
	private final Condition availablePermits = lock.newCondition();
	
	public SemaphoreOnLock(int initialPermits)
	{
		lock.lock();
		try {
			permits = initialPermits;
		}
		finally {
			lock.unlock();
		}
	}
	
	public void acquire() throws InterruptedException {
		lock.lock();
		try {
			while (permits <= 0)
				availablePermits.await();
			++permits;
		}
		finally {
			lock.unlock();
		}
	}
	
	public void release() {
		lock.lock();
		try {
			++permits;
			availablePermits.signal();
		} finally {
			lock.unlock();
		}
	}
}

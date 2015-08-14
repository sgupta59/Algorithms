package concurrency.readwrite;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class RWLock {

	private int readerCount = 0;
	private int pendingCount = 0;
	boolean write = false;
	Lock lock = new ReentrantLock();
	Condition readReady = lock.newCondition();
	Condition writeReady = lock.newCondition();
	public RWLock() {
		
	}
	
	public void acquireRead() throws InterruptedException
	{
		lock.lock();
		++pendingCount;
		while (write)
			readReady.await();
		--pendingCount;
		++readerCount;
		lock.unlock();
	}
	
	public void releaseRead() {
		lock.lock();
		--readerCount;
		if (readerCount == 0 && pendingCount == 0)
			writeReady.signal();
		lock.unlock();
	}
	
	public void acquireWrite() throws InterruptedException
	{
		lock.lock();
		while (write || pendingCount != 0 || readerCount != 0)
			writeReady.await();
		write=true;
		lock.unlock();
	}
	
	public void releaseWrite() {
		lock.lock();
		if (pendingCount != 0)
			readReady.signal();
		else
			writeReady.signal();
	}
}

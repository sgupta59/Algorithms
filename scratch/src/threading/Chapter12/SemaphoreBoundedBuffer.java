package threading.Chapter12;

import java.util.concurrent.Semaphore;

/**
 * @author Brian Goetz and Tim Peierls
 *
 * @param <E>
 */
public class SemaphoreBoundedBuffer<E> {
	
	// a semaphore bounded buffer uses semaphores to return values.
	private int getindex = 0;
	private int putindex = 0;
	private final Semaphore itemSemaphore;
	private final Semaphore spaceSemaphore;
	E[] items;
	public SemaphoreBoundedBuffer(int size)
	{
		itemSemaphore = new Semaphore(0);
		spaceSemaphore = new Semaphore(size);
		items = (E[]) new Object[size];
	}
	
	public boolean isFull()
	{
		// buffer is full if there is no space.
		return spaceSemaphore.availablePermits()==0;
	}

	public boolean isEmpty()
	{
		return itemSemaphore.availablePermits() == 0;
	}
	
	public E take() throws InterruptedException
	{
		// to take an item, first aquire a permit from space semaphore.
		itemSemaphore.acquire();
		E item = doExtract();
		spaceSemaphore.release();
		return item;
	}
	
	public void put(E ele) throws InterruptedException
	{
		// to put an item, first aquire a space permit
		spaceSemaphore.acquire();
		doInsert(ele);
		itemSemaphore.release();
	}
	
	private synchronized void doInsert(E ele)
	{
		int i = putindex;
		items[i] = ele;
		putindex = (++i == items.length ? 0 : i);
		
	}
	private synchronized E doExtract()
	{
		int i = getindex;
		E item = items[i];
		items[i] = null;
		getindex = (++i == items.length ? 0 : i);
		return item;
	}
}

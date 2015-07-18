package threading.Chapter12;

import java.util.concurrent.Semaphore;

public class BoundedBuffer<E> {
	
	private int putIndex;
	
	private int getIndex;
	private E[] items;
	Semaphore availableItems;
	Semaphore availableSpaces;
	public BoundedBuffer(int capacity)
	{
		items = (E[])new Object[capacity];
		putIndex = 0;
		getIndex = 0;
		availableItems = new Semaphore(0);
		availableSpaces = new Semaphore(capacity);
	}
	
	public void put(E item) throws InterruptedException
	{
		// block until a space is available.
		availableSpaces.acquire();
		doInsert(item);
		// item is added, release a permit.
		availableItems.release();
	}
	
	public E get() throws InterruptedException
	{
		E item = null;
		// decrement the count of available items.
		availableItems.acquire();
		item =doExtract();
		// increment the number of spaces available.
		availableSpaces.release();
		return item;
	}
	
	public  boolean isFull()
	{
		return availableSpaces.availablePermits()==0;
	}
	
	public boolean isEmpty()
	{
		return availableItems.availablePermits()==0;
	}
	
	/**
	 * Synchronized as multiple threads could end up here.
	 * @param item
	 */
	private synchronized void doInsert(E item)
	{
		int i = putIndex;
		items[i] = item;
	    putIndex = (++i == items.length ? 0 : i);
	}
	
	private  synchronized E doExtract()
	{
		int i = getIndex;
		E item = items[i];
		items[i] = null;
		getIndex = (++i == items.length ? 0 : i);
		return item;
	}
}
package threading.Chapter5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSet<T> {
	private final Set<T> set;
	private final Semaphore permits;
	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		permits = new Semaphore(bound);
	}
	
	public void add(T item) throws InterruptedException  
	{
		permits.acquire();
		boolean wasAdded = false;
		try {
			wasAdded = set.add(item);	
		} 
		finally 
		{
			if (!wasAdded) 
				permits.release();
		}
		
	}
	
	public boolean remove(T item) 
	{
		boolean status = set.remove(item);
		permits.release();
		return status;
	}
}
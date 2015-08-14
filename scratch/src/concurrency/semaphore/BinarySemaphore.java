package concurrency.semaphore;

public class BinarySemaphore {

	int permits = 1;
	
	public BinarySemaphore() {
		
	}
	
	public synchronized void acquire() {
		while (permits == 0)
			wait();
	}
}

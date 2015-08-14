package concurrency.Asynchronous;

public class Selectable {

	private int ready=0;
	private boolean open = true;
	/**
	 * A selectable will block till a message arrives
	 * It will open the "port"
	 * @throws InterruptedException 
	 */
	public synchronized void block() throws InterruptedException
	{
		open = true;
		while (ready == 0)
			wait();
		--ready;
		open = false;
	}
	
	/**
	 * Will signal once it has read the message?
	 */
	public synchronized void signal()
	{
		++ready;
		if (open) notifyAll();
		
	}
}

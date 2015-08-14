package concurrency.message1;

public class Selectable {

	//private boolean open;
	private int ready;
	public Selectable()
	{
		
	}
	
	public synchronized void block() throws InterruptedException
	{
		//open=true;
		while (ready == 0) wait();
		//open = false;
		--ready;
	}
	
	public synchronized void signal()
	{
		++ready;
		//if (open) notifyAll();
		notifyAll();
	}
}

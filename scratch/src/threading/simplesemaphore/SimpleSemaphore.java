package threading.simplesemaphore;

public class SimpleSemaphore {

	/**
	 * Boolean flag used for signaling
	 */
	private boolean _signal;
	public SimpleSemaphore()
	{
		_signal = false;
	}

	public synchronized void take()
	{
		// send a signal.
		_signal = true;
		notifyAll();
	}
	
	public synchronized void release() throws InterruptedException
	{
		while(!_signal)
			wait();
		_signal = false;
	}
}

package threading.Semaphores.simplesemaphore;

public class SimpleSemaphore {

	/**
	 * Boolean flag used for signaling
	 */
	private boolean _signal;
	public SimpleSemaphore()
	{
		_signal = false;
	}

	/**
	 * UP in Dijkstra's vernacular
	 * acquire in Java's vernacular
	 */
	public synchronized void take()
	{
		// send a signal.
		_signal = true;
		notifyAll();
	}

	/**
	 * Down in Dijkstra's vernacular
	 * release in Java's vernacular
	 * @throws InterruptedException
	 */
	public synchronized void release() throws InterruptedException
	{
		while(!_signal)
			wait();
		_signal = false;
	}
}

package threading.Semaphores.simplesemaphore;

public class SendingThread implements Runnable {

	private final SimpleSemaphore _sem;
	public SendingThread(SimpleSemaphore sem)
	{
		_sem = sem;
	}

	@Override
    public void run()
	{
		while (true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_sem.take();
			System.out.println("Sending thread signal sent");
		}
	}
}

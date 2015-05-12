package threading.Semaphores.simplesemaphore;

public class ReceivingThread implements Runnable {

	private final SimpleSemaphore _sem;

	public ReceivingThread(SimpleSemaphore sem)
	{
		_sem = sem;
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while (true)
		{
			try {
				_sem.release();
				System.out.println("Receiving thread signal received");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}



}

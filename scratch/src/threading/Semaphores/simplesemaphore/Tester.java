package threading.Semaphores.simplesemaphore;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create a semaphore
		SimpleSemaphore sem = new SimpleSemaphore();
		Thread t1 = new Thread(new SendingThread(sem));
		Thread t2 = new Thread(new ReceivingThread(sem));
		t2.start();
		t1.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

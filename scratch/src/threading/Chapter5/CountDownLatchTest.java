package threading.Chapter5;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
		// Create two latches
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(nThreads);
		for (int i = 0; i < nThreads; ++i) {
			new Thread(new Runnable() { public void run() {
				// watit on the start latch.
				try {
					startLatch.await();
					task.run();
					endLatch.countDown();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} 
			});
		}
		long start = System.nanoTime();
		startLatch.countDown();
		endLatch.await();
		long end = System.nanoTime();
		return end-start;
	}
}
